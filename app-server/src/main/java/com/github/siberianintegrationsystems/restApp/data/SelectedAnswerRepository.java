package com.github.siberianintegrationsystems.restApp.data;

import com.github.siberianintegrationsystems.restApp.entity.Question;
import com.github.siberianintegrationsystems.restApp.entity.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SelectedAnswerRepository
        extends CrudRepository<Session, Long> {

    List<Question> findByNameContainingIgnoreCase(String search);
}
