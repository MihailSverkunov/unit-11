package com.github.siberianintegrationsystems.restApp.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FinishedSessionDTO {

    private String name;

    private List<FinishedQuestionDTO> questionsList;

}
