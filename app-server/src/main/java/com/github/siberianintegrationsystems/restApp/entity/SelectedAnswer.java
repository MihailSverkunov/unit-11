package com.github.siberianintegrationsystems.restApp.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SelectedAnswer extends BaseEntity{

    @JoinColumn(name = "answer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Answer answer;

    @JoinColumn(name = "session_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Session session;

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
