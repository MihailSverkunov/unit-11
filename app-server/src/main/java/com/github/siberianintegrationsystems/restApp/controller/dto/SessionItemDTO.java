package com.github.siberianintegrationsystems.restApp.controller.dto;

import com.github.siberianintegrationsystems.restApp.entity.Session;

public class SessionItemDTO extends JournalItemDTO {
    public String name;

    public Double percent;

    public SessionItemDTO() {

    }

    public SessionItemDTO(Session session) {
        this.id = session.getId().toString();
        this.name = session.getName();
        this.percent = session.getPercent();
    }
}
