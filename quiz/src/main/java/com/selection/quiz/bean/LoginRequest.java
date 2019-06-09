package com.selection.quiz.bean;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
	@NotEmpty(message = "requestId must not be empty")
	private String requestId; 
	 
	@NotEmpty(message = "username must not be empty") 
	private String username;
	 
	@NotEmpty(message = "password must not be empty")
	private String password;
	
}
