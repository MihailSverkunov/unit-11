package com.github.siberianintegrationsystems.restApp.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FinishedQuestionDTO {

    private String id;

    private List<FinishedAnswerDTO> answersList;

}
