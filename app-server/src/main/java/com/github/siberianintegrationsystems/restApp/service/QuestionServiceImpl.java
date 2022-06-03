package com.github.siberianintegrationsystems.restApp.service;

import com.github.siberianintegrationsystems.restApp.controller.dto.AnswerItemDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.QuestionsItemDTO;
import com.github.siberianintegrationsystems.restApp.data.AnswerRepository;
import com.github.siberianintegrationsystems.restApp.data.QuestionRepository;
import com.github.siberianintegrationsystems.restApp.entity.Answer;
import com.github.siberianintegrationsystems.restApp.entity.Question;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionServiceImpl(
        QuestionRepository questionRepository,
        AnswerRepository answerRepository
    ) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public QuestionsItemDTO createQuestion(QuestionsItemDTO dto) {
        Question question = new Question();
        question.setName(dto.name);
        questionRepository.save(question);

        createAnswers(dto, question);

        return new QuestionsItemDTO(
            question,
            answerRepository.findByQuestion(question)
        );
    }


    @Override
    public QuestionsItemDTO editQuestion(QuestionsItemDTO dto) {
        Question question = questionRepository.findById(Long.parseLong(dto.id))
                                              .orElseThrow(() -> new RuntimeException(String.format(
                                                  "Не найден вопрос с id '%s'",
                                                  dto.id
                                              )));
        question.setName(dto.name);
        questionRepository.save(question);

        //TODO Возможно старые ответы пригодятся для сессий, если нет - удалить
        answerRepository.findByQuestion(question).forEach(answer -> answer.setQuestion(null));

        createAnswers(dto, question);

        return new QuestionsItemDTO(
            question,
            answerRepository.findByQuestion(question)
        );
    }


    private void createAnswers(QuestionsItemDTO dto, Question question) {
        for (AnswerItemDTO answerDTO : dto.answers) {
            Answer answer = new Answer();
            answer.setName(answerDTO.answerText);
            answer.setCorrect(answerDTO.isCorrect);
            answer.setQuestion(question);

            answerRepository.save(answer);
        }
    }

}
