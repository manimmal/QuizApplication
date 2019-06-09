package com.selection.quiz.bean;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionAnswerRequest {
	@NotEmpty(message = "requestId must not be empty")
	private String requestId; 
	
	@NotEmpty(message = "userId must not be empty")
	private Integer userId;
	
	@NotEmpty(message = "questionId must not be empty")
	private Integer questionId;
	
	@NotEmpty(message = "answerId must not be empty")
	private Integer answerId;	
	
}
