package com.github.siberianintegrationsystems.restApp.data;

import com.github.siberianintegrationsystems.restApp.entity.Question;
import com.github.siberianintegrationsystems.restApp.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessionRepository
        extends CrudRepository<Session, Long> , JpaRepository<Session, Long> {

    List<Session> findByNameContainingIgnoreCase(String search);
}
