package com.github.siberianintegrationsystems.restApp.service;

import com.github.siberianintegrationsystems.restApp.controller.dto.FinishedAnswerDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.FinishedSessionDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.SessionItemDTO;
import com.github.siberianintegrationsystems.restApp.data.AnswerRepository;
import com.github.siberianintegrationsystems.restApp.data.SelectedAnswerRepository;
import com.github.siberianintegrationsystems.restApp.data.SessionRepository;
import com.github.siberianintegrationsystems.restApp.entity.Answer;
import com.github.siberianintegrationsystems.restApp.entity.SelectedAnswer;
import com.github.siberianintegrationsystems.restApp.entity.Session;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final AnswerRepository answerRepository;
    private final SelectedAnswerRepository selectedAnswerRepository;

    public SessionServiceImpl(
            SessionRepository sessionRepository,
            AnswerRepository answerRepository, SelectedAnswerRepository selectedAnswerRepository) {
        this.sessionRepository = sessionRepository;
        this.answerRepository = answerRepository;
        this.selectedAnswerRepository = selectedAnswerRepository;
    }

    @Override
    public String createSession(FinishedSessionDTO dto) {
        Session session = new Session();
        session.setName(dto.getName());
//        sessionRepository.save(session);

        List<Boolean> answerResults = dto.getQuestionsList()
                .stream()
                .flatMap(finishedQuestionDTO -> finishedQuestionDTO.getAnswersList().stream())
                .map(answer -> createSelectedAnswer(answer, session))
                .collect(Collectors.toList());

        Double percent = (double)answerResults.stream().filter(Boolean.TRUE::equals).count() / (double)answerResults.size() * 100;
        session.setPercent(percent);


        sessionRepository.save(session);
        return percent.toString();

    }

    @Override
    public List<SessionItemDTO> findAll() {

        return sessionRepository.findAll().stream().map(SessionItemDTO::new).collect(Collectors.toList());
    }

    private boolean createSelectedAnswer(FinishedAnswerDTO answerDTO, Session session) {

        SelectedAnswer selectedAnswer = new SelectedAnswer();
        Answer answer = answerRepository.findById(Long.parseLong(answerDTO.getId()))
                .orElseThrow(() -> new RuntimeException(String.format("Не найден ответ с id '%s'", answerDTO.getId())));

        selectedAnswer.setAnswer(answer);

        selectedAnswer.setSession(session);

        selectedAnswerRepository.save(selectedAnswer);
        
        return answer.getCorrect();

    }
}
