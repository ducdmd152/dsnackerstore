package com.ducdmd152.springboot.dsnackerstore.entry;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.testng.MockitoTestNGListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ducdmd152.springboot.dsnackerstore.users.User;
import com.ducdmd152.springboot.dsnackerstore.users.UserJpaRepository;
import com.ducdmd152.springboot.dsnackerstore.users.UserServiceImpl;

@SpringBootTest
@Listeners(MockitoTestNGListener.class)
public class GetUserExceptionTest {
	@Mock
	private UserJpaRepository userRepository;
	@InjectMocks
    private UserServiceImpl userService;
 
	private User user;

	@BeforeClass
	public void setup() {		
		user = new User();
		user.setUsername("user" + System.currentTimeMillis());
		user.setPassword("123456");
		user.setEnabled(true);
	}

	// TestNG test for init/inject mock resources
	@Test(priority = 0)
	public void injectedComponentsAreNotNull() {
		assertTrue(userRepository != null);
		assertTrue(userService != null);
		System.out.println("Init Mock resources successfully!");
	}
		
	// TestNG test for save(user) method for both Repository & Service
	@Test(priority = 1, expectedExceptions = RuntimeException.class)
	public void givenUserObject_whenSaveUser_thenThrowException() {		
		userService.getUserByUsername(user.getUsername());
	}
	
	@AfterClass
	public void finish() {
	}
}
