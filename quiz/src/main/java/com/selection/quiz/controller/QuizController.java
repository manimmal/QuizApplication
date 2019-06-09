package com.selection.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.selection.quiz.bean.LoginRequest;
import com.selection.quiz.bean.LoginResponse;
import com.selection.quiz.bean.QuestionAnswerRequest;
import com.selection.quiz.bean.QuestionAnswerResponse;
import com.selection.quiz.service.QuizService;

/**
 * Quiz application controller
 *
 */
@RestController
@RequestMapping(value = "/quiz/v1")
public class QuizController {
  
    @Autowired
    QuizService quizService;
  
    @PostMapping(value = "/user/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return quizService.login(request);
    }
    
    @PostMapping(value = "/user/getFirstQuestion")
    public QuestionAnswerResponse getFirstQuestion(@RequestBody QuestionAnswerRequest request) {
        return quizService.getFirstQuestionAnswer(request);
    }
    
    @PostMapping(value = "/user/getNextQuestion")
    public QuestionAnswerResponse getNextQuestion(@RequestBody QuestionAnswerRequest request) {
        return quizService.getNextQuestionAnswer(request);
    }
    
    @PostMapping(value = "/user/getPreviousQuestion")
    public QuestionAnswerResponse getPreviousQuestion(@RequestBody QuestionAnswerRequest request) {
        return quizService.getPreviousQuestionAnswer(request);
    }
    
    @PostMapping(value = "/user/getFinalResult")
    public QuestionAnswerResponse getFinalResult(@RequestBody QuestionAnswerRequest request) {
        return quizService.getFinalResult(request);
    }
    
}
