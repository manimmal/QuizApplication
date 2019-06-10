package com.selection.quiz.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.selection.quiz.bean.LoginRequest;
import com.selection.quiz.bean.LoginResponse;
import com.selection.quiz.bean.QuestionAnswerRequest;
import com.selection.quiz.bean.QuestionAnswerResponse;
import com.selection.quiz.entity.Answer;
import com.selection.quiz.entity.Question;
import com.selection.quiz.entity.User;
import com.selection.quiz.entity.UserAnswer;
import com.selection.quiz.logging.MethodLoggable;
import com.selection.quiz.repo.AnswerRepo;
import com.selection.quiz.repo.QuestionRepo;
import com.selection.quiz.repo.UserAnswerRepo;
import com.selection.quiz.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

/**
 * Quiz application service
 *
 */
@Slf4j
@Service
public class QuizService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	QuestionRepo questionRepo;
	
	@Autowired
	AnswerRepo answerRepo;
	
	@Autowired
	UserAnswerRepo userAnswerRepo;
	
	/**
	 * Retrieve and Cache all questions
	 * @return
	 */
	@Cacheable(value="questionList")
    private List<Question> getAllQuestions () {
    	return questionRepo.findAll();
    }
	
	/**
	 * Retrieve and Cache answers for each question
	 * @return
	 */
	@Cacheable(value="questionAnswerList", key="#questionId")
	List<Answer> retrieveAnswersForQuestion (Integer questionId) {
		return answerRepo.retrieveAnswersForQuestion(questionId);
	}
	/**
	 * Validate login credentials.
	 * @param request
	 * @return
	 */
	@MethodLoggable
	public LoginResponse login(LoginRequest request) {
		log.debug(request.getUsername());
		String statusCode = "200";
		String id = "";
		User user = userRepo.getUser(request.getUsername());
		if (user == null || !user.getPassword().equalsIgnoreCase(request.getPassword())) {
			statusCode = "401";
		} else {
			id = String.valueOf(user.getUid());
		}
		LoginResponse response = LoginResponse.builder()
				.requestId(request.getRequestId())
				.statusCode(statusCode)
				.id(id)
				.build();
		return response;		
	}
	
	/**
	 * shuffle questions and display the first question and answers
	 * @param request
	 * @return
	 */
    @MethodLoggable
	public QuestionAnswerResponse getFirstQuestionAnswer (QuestionAnswerRequest request) {
    	log.debug(String.valueOf(request.getUserId()));
		
		userAnswerRepo.deleteUserData(request.getUserId());
		List<Question> questionList = shuffuleAndSave(request.getUserId());
		List<Answer> answerList = retrieveAnswersForQuestion(questionList.get(0).getQid());
		Map<Integer, String> answerMap = new LinkedHashMap<Integer, String> ();
		answerList.forEach( (value) -> {
			answerMap.put(value.getAid(), value.getAnswer());
		});
		
		QuestionAnswerResponse response = QuestionAnswerResponse.builder()
			.requestId(request.getRequestId())
			.questionId(1)
			.question(questionList.get(0).getQuestion())
			.maxQuestions(questionList.size())
			.answerMap(answerMap).build();
		
		return response;
	}
	
    /**
	 * Display next question and answers
	 * @param request
	 * @return
	 */
    @MethodLoggable
	public QuestionAnswerResponse getNextQuestionAnswer (QuestionAnswerRequest request) {
    	log.debug(String.valueOf(request.getUserId()));
    	log.debug(String.valueOf(request.getQuestionId()));
    	log.debug(String.valueOf(request.getAnswerId()));
		UserAnswer userAnswer = userAnswerRepo.retrieveQuestion(request.getUserId(), request.getQuestionId());
		userAnswer.setAid(request.getAnswerId());
		userAnswerRepo.save(userAnswer);
		
		userAnswer = userAnswerRepo.retrieveSecondQuestion(request.getUserId(), request.getQuestionId() + 1);
		Question question = questionRepo.getOne(userAnswer.getQid());
		List<Answer> answerList = retrieveAnswersForQuestion(question.getQid());
		Map<Integer, String> answerMap = new LinkedHashMap<Integer, String> ();
		answerList.forEach( (value) -> {
			answerMap.put(value.getAid(), value.getAnswer());
		});
		QuestionAnswerResponse response = QuestionAnswerResponse.builder()
				.requestId(request.getRequestId())
				.questionId(request.getQuestionId() + 1)
				.question(question.getQuestion())
				.answerMap(answerMap)
				.answerSelected(userAnswer.getAid())
				.build();
			
			return response;
	}
	
    /**
	 * Display previous question and answers
	 * @param request
	 * @return
	 */
    @MethodLoggable
	public QuestionAnswerResponse getPreviousQuestionAnswer (QuestionAnswerRequest request) {
    	log.debug(String.valueOf(request.getUserId()));
    	log.debug(String.valueOf(request.getQuestionId()));
    	log.debug(String.valueOf(request.getAnswerId()));
		UserAnswer userAnswer = userAnswerRepo.retrieveQuestion(request.getUserId(), request.getQuestionId());
		userAnswer.setAid(request.getAnswerId());
		userAnswerRepo.save(userAnswer);
		
		userAnswer = userAnswerRepo.retrieveSecondQuestion(request.getUserId(), request.getQuestionId() - 1);
		Question question = questionRepo.getOne(userAnswer.getQid());
		List<Answer> answerList = retrieveAnswersForQuestion(question.getQid());
		Map<Integer, String> answerMap = new LinkedHashMap<Integer, String> ();
		answerList.forEach( (value) -> {
			answerMap.put(value.getAid(), value.getAnswer());
		});
		QuestionAnswerResponse response = QuestionAnswerResponse.builder()
				.requestId(request.getRequestId())
				.questionId(request.getQuestionId() - 1)
				.question(question.getQuestion())
				.answerMap(answerMap)
				.answerSelected(userAnswer.getAid())
				.build();
			
			return response;
	}
	
    /**
	 * Persist last question answer and retrieve the final results
	 * @param request
	 * @return
	 */
    @MethodLoggable
	public QuestionAnswerResponse getFinalResult (QuestionAnswerRequest request) {
    	log.debug(String.valueOf(request.getUserId()));
    	log.debug(String.valueOf(request.getQuestionId()));
    	log.debug(String.valueOf(request.getAnswerId()));
		UserAnswer userAnswer = userAnswerRepo.retrieveQuestion(request.getUserId(), request.getQuestionId());
		userAnswer.setAid(request.getAnswerId());
		userAnswerRepo.save(userAnswer);
		QuestionAnswerResponse response = QuestionAnswerResponse.builder()
				.requestId(request.getRequestId())
				.correctAnswers(userAnswerRepo.CountCorrectAnswers(request.getUserId()))
				.build();
		return response;
	}
    
    private List<Question> shuffuleAndSave (Integer userId) {
    	int index = 1;
    	List<Question> questionList =  getAllQuestions();
		Collections.shuffle(questionList);
		List<UserAnswer> allUserAnswers = new ArrayList<> ();
		for(Question quesShuffule : questionList) {
			UserAnswer userAnswer = new UserAnswer();
			userAnswer.setOrderNum(index);
			userAnswer.setUid(userId);
			userAnswer.setQid(quesShuffule.getQid());
			index++;
			allUserAnswers.add(userAnswer);
		}
		//save in batch
		userAnswerRepo.saveAll(allUserAnswers);
		return questionList;
    }
}
