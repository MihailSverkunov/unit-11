package com.github.siberianintegrationsystems.restApp.service;

import com.github.siberianintegrationsystems.restApp.controller.dto.QuestionsItemDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.SessionItemDTO;

public interface SessionService {

    SessionItemDTO createSession(SessionItemDTO dto);


}
