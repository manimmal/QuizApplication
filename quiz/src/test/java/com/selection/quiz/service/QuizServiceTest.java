package com.selection.quiz.service;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.selection.quiz.bean.LoginRequest;
import com.selection.quiz.bean.LoginResponse;
import com.selection.quiz.bean.QuestionAnswerRequest;
import com.selection.quiz.bean.QuestionAnswerResponse;
import com.selection.quiz.entity.Answer;
import com.selection.quiz.entity.Question;
import com.selection.quiz.entity.User;
import com.selection.quiz.entity.UserAnswer;
import com.selection.quiz.repo.AnswerRepo;
import com.selection.quiz.repo.QuestionRepo;
import com.selection.quiz.repo.UserAnswerRepo;
import com.selection.quiz.repo.UserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class QuizServiceTest {
	
	@MockBean
	UserRepo userRepo;
	
	@MockBean
	QuestionRepo questionRepo;
	
	@MockBean
	AnswerRepo answerRepo;
	
	@MockBean
	UserAnswerRepo userAnswerRepo;
	
	@Autowired
	QuizService quizService;
	
	@Test
	public void login() {
		User user = new User();
    	user.setUid(1);
    	user.setUserName("tom");
    	user.setPassword("123");
    	user.setRole("Admin");
	    LoginResponse response = new LoginResponse();
	    response.setId("1");
	    Mockito.when(userRepo.getUser("tom")).thenReturn(user);
	    
	    LoginRequest request = new LoginRequest();
	    request.setRequestId("12324");
	    request.setUsername("tom");
	    request.setPassword("123");
	    
	    LoginResponse response2 = quizService.login(request);
	    Assert.assertTrue(response.getId().equalsIgnoreCase(response2.getId()));
	}
	
	@Test
	public void loginUserNameError() {
		User user = new User();
    	user.setUid(1);
    	user.setUserName("tom1");
    	user.setPassword("123");
    	user.setRole("Admin");

	    Mockito.when(userRepo.getUser("tom")).thenReturn(user);
	    
	    LoginRequest request = new LoginRequest();
	    request.setRequestId("12324");
	    request.setUsername("tom1");
	    request.setPassword("123");
	    
	    LoginResponse response2 = quizService.login(request);
	    Assert.assertTrue(request.getRequestId().equalsIgnoreCase(response2.getRequestId()));
	}
	
	@Test
	public void loginPasswordError() {
		User user = new User();
    	user.setUid(1);
    	user.setUserName("tom");
    	user.setPassword("123");
    	user.setRole("Admin");
	    LoginResponse response = new LoginResponse();
	    response.setId("1");
	    Mockito.when(userRepo.getUser("tom")).thenReturn(user);
	    
	    LoginRequest request = new LoginRequest();
	    request.setRequestId("12324");
	    request.setUsername("tom");
	    request.setPassword("1234");
	    
	    LoginResponse response2 = quizService.login(request);
	    Assert.assertTrue(request.getRequestId().equalsIgnoreCase(response2.getRequestId()));
	}
	
	@Test
	public void getFirstQuestion() {
		Question question = new Question();
		question.setQid(1);
		question.setQuestion("What is the capital of Karnataka ? ");
		
		Answer answer = new Answer ();
		answer.setAid(1);
		answer.setAnswer("Bangalore");
		answer.setQid(1);
		answer.setCorrect("Y");
    	
    	QuestionAnswerResponse response = QuestionAnswerResponse.builder().requestId("12345").build();
	    Mockito.when(questionRepo.findAll()).thenReturn(Collections.singletonList(question));
	    Mockito.when(userAnswerRepo.save(new UserAnswer())).thenReturn(new UserAnswer());
	    Mockito.when(answerRepo.retrieveAnswersForQuestion(1)).thenReturn(Collections.singletonList(answer));
	    
	    QuestionAnswerRequest request = new QuestionAnswerRequest();
	    request.setRequestId("12345");
	    
	    QuestionAnswerResponse response2 = quizService.getFirstQuestionAnswer(request);
	    Assert.assertTrue(response.getRequestId().equalsIgnoreCase(response2.getRequestId()));
	}
	
	@Test
	public void getNextQuestion() {
		Question question = new Question();
		question.setQid(2);
		question.setQuestion("What is the capital of Karnataka ? ");
		
		UserAnswer userAnswer = new UserAnswer();
		userAnswer.setAid(1);
		userAnswer.setOrderNum(1);
		userAnswer.setQid(1);
		userAnswer.setUaid(1);
		userAnswer.setUid(1);
		
		Answer answer = new Answer ();
		answer.setAid(1);
		answer.setAnswer("Bangalore");
		answer.setQid(1);
		answer.setCorrect("Y");

		Mockito.when(questionRepo.getOne(1)).thenReturn(question);
	    Mockito.when(userAnswerRepo.retrieveQuestion(1, 2)).thenReturn(userAnswer);
	    Mockito.when(userAnswerRepo.retrieveSecondQuestion(1, 3)).thenReturn(userAnswer);
	    Mockito.when(userAnswerRepo.save(userAnswer)).thenReturn(userAnswer);
	    Mockito.when(answerRepo.retrieveAnswersForQuestion(1)).thenReturn(Collections.singletonList(answer));
	    
	    QuestionAnswerRequest request = new QuestionAnswerRequest();
	    request.setRequestId("12345");
	    request.setUserId(1);
	    request.setQuestionId(2);
	    request.setAnswerId(1);

	    QuestionAnswerResponse response2 = quizService.getNextQuestionAnswer(request);
	    Assert.assertTrue(request.getRequestId().equalsIgnoreCase(response2.getRequestId()));
	}
	
	@Test
	public void getPreviousQuestion() {
		Question question = new Question();
		question.setQid(2);
		question.setQuestion("What is the capital of Karnataka ? ");
		
		UserAnswer userAnswer = new UserAnswer();
		userAnswer.setAid(1);
		userAnswer.setOrderNum(1);
		userAnswer.setQid(1);
		userAnswer.setUaid(1);
		userAnswer.setUid(1);
		
		Answer answer = new Answer ();
		answer.setAid(1);
		answer.setAnswer("Bangalore");
		answer.setQid(1);
		answer.setCorrect("Y");

		Mockito.when(questionRepo.getOne(1)).thenReturn(question);
	    Mockito.when(userAnswerRepo.retrieveQuestion(1, 2)).thenReturn(userAnswer);
	    Mockito.when(userAnswerRepo.retrieveSecondQuestion(1, 1)).thenReturn(userAnswer);
	    Mockito.when(userAnswerRepo.save(userAnswer)).thenReturn(userAnswer);
	    Mockito.when(answerRepo.retrieveAnswersForQuestion(1)).thenReturn(Collections.singletonList(answer));
	    
	    QuestionAnswerRequest request = new QuestionAnswerRequest();
	    request.setRequestId("12345");
	    request.setUserId(1);
	    request.setQuestionId(2);
	    request.setAnswerId(1);

	    QuestionAnswerResponse response2 = quizService.getPreviousQuestionAnswer(request);
	    Assert.assertTrue(request.getRequestId().equalsIgnoreCase(response2.getRequestId()));
	}
	
	@Test
	public void getFinalResult() {
		
		UserAnswer userAnswer = new UserAnswer();
		userAnswer.setAid(1);
		userAnswer.setOrderNum(1);
		userAnswer.setQid(1);
		userAnswer.setUaid(1);
		userAnswer.setUid(1);

	    Mockito.when(userAnswerRepo.retrieveQuestion(1, 2)).thenReturn(userAnswer);
	    Mockito.when(userAnswerRepo.save(userAnswer)).thenReturn(userAnswer);
	    Mockito.when(userAnswerRepo.CountCorrectAnswers(1)).thenReturn(10);
	    
	    QuestionAnswerRequest request = new QuestionAnswerRequest();
	    request.setRequestId("12345");
	    request.setUserId(1);
	    request.setQuestionId(2);
	    request.setAnswerId(1);
	    
	    QuestionAnswerResponse response2 = quizService.getFinalResult(request);
	    Assert.assertTrue(request.getRequestId().equalsIgnoreCase(response2.getRequestId()));
	}
}
