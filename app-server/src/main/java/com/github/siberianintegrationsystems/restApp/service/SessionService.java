package com.github.siberianintegrationsystems.restApp.service;

import com.github.siberianintegrationsystems.restApp.controller.dto.FinishedSessionDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.SessionItemDTO;

import java.util.List;

public interface SessionService {

    String createSession(FinishedSessionDTO dto);

    List<SessionItemDTO> findAll();


}
