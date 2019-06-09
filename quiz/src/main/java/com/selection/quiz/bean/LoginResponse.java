package com.selection.quiz.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class LoginResponse {

	private String requestId; 
	private String id;
	private String statusCode;
	
}
