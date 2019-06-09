package com.selection.quiz.bean;

import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class QuestionAnswerResponse {
	
	private String requestId;
	private String question;
	private Integer questionId;
	private Integer maxQuestions;
	private Map<Integer, String> answerMap;
	private Integer answerSelected;
	private Integer correctAnswers;
}
