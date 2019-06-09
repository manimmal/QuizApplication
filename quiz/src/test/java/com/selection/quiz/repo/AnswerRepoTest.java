package com.selection.quiz.repo;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.selection.quiz.entity.Answer;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRepo.class)
public class AnswerRepoTest {

	@MockBean
    private AnswerRepo answerRepo;

    @Test
    public void retrieveAnswersForQuestion() throws Exception {
    	Answer answer = new Answer();
    	answer.setAid(1);
    	answer.setAnswer("test");
    	answer.setCorrect("Y");
    	answer.setQid(1);
    	Mockito.when(answerRepo.retrieveAnswersForQuestion(answer.getQid())).thenReturn(Collections.singletonList(answer));
    	List<Answer> answerList = answerRepo.retrieveAnswersForQuestion(answer.getQid());
    	Assert.assertTrue(answer.getAnswer().equalsIgnoreCase(answerList.get(0).getAnswer()));
    }
}