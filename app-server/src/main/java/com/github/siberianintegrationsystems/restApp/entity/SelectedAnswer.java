package com.github.siberianintegrationsystems.restApp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SelectedAnswer extends BaseEntity{

    @JoinColumn(name = "answer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Answer answer;

    @JoinColumn(name = "session_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Session session;
}
