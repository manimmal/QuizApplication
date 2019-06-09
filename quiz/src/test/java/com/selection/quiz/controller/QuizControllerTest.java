package com.selection.quiz.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.selection.quiz.bean.LoginRequest;
import com.selection.quiz.bean.LoginResponse;
import com.selection.quiz.bean.QuestionAnswerRequest;
import com.selection.quiz.bean.QuestionAnswerResponse;
import com.selection.quiz.service.QuizService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuizControllerTest {
	
	@Autowired
	private QuizController quizController;
	
	@MockBean
	private QuizService quizService;

	@Test
	public void login() {
	    LoginRequest request = new LoginRequest();
	    request.setRequestId("12324");
	    request.setUsername("tom");
	    request.setPassword("123");
	    LoginResponse response = new LoginResponse();
	    response.setId("1");
	    Mockito.when(quizService.login(request)).thenReturn(response);
	    LoginResponse response2 = quizController.login(request);
	    Assert.assertTrue(response.getId().equalsIgnoreCase(response2.getId()));
	}
	
	@Test
	public void getFirstQuestion() {
	    QuestionAnswerRequest request = new QuestionAnswerRequest();
	    request.setRequestId("12345");
	    QuestionAnswerResponse response =  QuestionAnswerResponse.builder().requestId("12345").build();
	    Mockito.when(quizService.getFirstQuestionAnswer(request)).thenReturn(response);
	    QuestionAnswerResponse response2 = quizController.getFirstQuestion(request);
	    Assert.assertTrue(response.getRequestId().equalsIgnoreCase(response2.getRequestId()));
	}
	
	@Test
	public void getNextQuestion() {
	    QuestionAnswerRequest request = new QuestionAnswerRequest();
	    request.setRequestId("12345");
	    QuestionAnswerResponse response =  QuestionAnswerResponse.builder().requestId("12345").build();
	    Mockito.when(quizService.getNextQuestionAnswer(request)).thenReturn(response);
	    QuestionAnswerResponse response2 = quizController.getNextQuestion(request);
	    Assert.assertTrue(response.getRequestId().equalsIgnoreCase(response2.getRequestId()));
	}
	
	@Test
	public void getPreviousQuestion() {
	    QuestionAnswerRequest request = new QuestionAnswerRequest();
	    request.setRequestId("12345");
	    QuestionAnswerResponse response =  QuestionAnswerResponse.builder().requestId("12345").build();
	    Mockito.when(quizService.getPreviousQuestionAnswer(request)).thenReturn(response);
	    QuestionAnswerResponse response2 = quizController.getPreviousQuestion(request);
	    Assert.assertTrue(response.getRequestId().equalsIgnoreCase(response2.getRequestId()));
	}
	
	@Test
	public void getFinalResult() {
	    QuestionAnswerRequest request = new QuestionAnswerRequest();
	    request.setRequestId("12345");
	    QuestionAnswerResponse response =  QuestionAnswerResponse.builder().requestId("12345").build();
	    Mockito.when(quizService.getFinalResult(request)).thenReturn(response);
	    QuestionAnswerResponse response2 = quizController.getFinalResult(request);
	    Assert.assertTrue(response.getRequestId().equalsIgnoreCase(response2.getRequestId()));
	}
}
