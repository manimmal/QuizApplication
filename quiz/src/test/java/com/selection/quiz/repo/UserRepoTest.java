package com.selection.quiz.repo;

import org.mockito.Mockito;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.selection.quiz.entity.User;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRepo.class)
public class UserRepoTest {

	@MockBean
    private UserRepo userRepo;

    @Test
    public void getUser() throws Exception {
    	User user = new User();
    	user.setUid(1);
    	user.setUserName("tom");
    	user.setPassword("123");
    	user.setRole("Admin");
    	Mockito.when(userRepo.getUser("tom")).thenReturn(user);
    	User user1 = userRepo.getUser("tom");
    	Assert.assertTrue(user.getUserName().equalsIgnoreCase(user1.getUserName()));
    }
}