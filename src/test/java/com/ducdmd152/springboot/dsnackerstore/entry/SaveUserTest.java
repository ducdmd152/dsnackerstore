package com.ducdmd152.springboot.dsnackerstore.entry;

import static org.testng.Assert.assertTrue;

import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.testng.MockitoTestNGListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ducdmd152.springboot.dsnackerstore.users.User;
import com.ducdmd152.springboot.dsnackerstore.users.UserJpaRepository;
import com.ducdmd152.springboot.dsnackerstore.users.UserServiceImpl;

@SpringBootTest // to set up an application context for tests that is very close the one we'll have in production.
@Listeners(MockitoTestNGListener.class)
public class SaveUserTest { // UNIT TEST FOR METHODS IN UserServiceImpl: saveUser_(user)
	@Mock
	// @Mock is used to create mocks that are needed to support the testing of the class to be tested.
	private UserJpaRepository userRepository;
	@InjectMocks
	// @InjectMocks is used to create class instances that need to be tested in the test class.
	private UserServiceImpl userService;
	// Goal to test userService
	// => To test for userService
	// => Need to solve dependency problem with userRepository
	// => Can test userService independently

	private User user;

	@BeforeClass // runs before the execution of test methods in a current class.
	public void setup() {
		user = new User();
		user.setUsername("user" + System.currentTimeMillis());
		user.setPassword("123456");
		user.setEnabled(true);
	}

	@AfterClass // runs after the execution of all test methods in a current class.
	public void finish() {
	}

	// TestNG test for init/inject mock resources
	@Test(priority = 0)
	public void injectedComponentsAreNotNull() {
		assertTrue(userRepository != null);
		assertTrue(userService != null);
		System.out.println("Init Mock resources successfully!");
	}

	// TestNG test for save(user) method for UserServiceImpl class
	@Test(priority = 1)
	public void givenUserObject_whenSaveUser_thenReturnUserObject() {
		// 1. Presumption => userRepository OK with expected
		// 1.1 precondition
		Mockito.lenient().when(userRepository.findById(user.getUsername()))
						.thenReturn(Optional.empty());

		// 1.2 Define return value for method save(user)
		Mockito.lenient().when(userRepository.save(user))
						.thenReturn(user);

		// 2. Check/test saveUser_(user)
		// Test match after saved return for SERVICE
		User savedUser = userService.saveUser_(user);

		System.out.println(savedUser);

		// then - verify the output
		assertTrue(savedUser != null);
		assertTrue(savedUser.getUsername().equals(user.getUsername()));
	}

	// Appendix: @BeforeMethod / @AfterMethod : will be invoked before/after every test method
}
