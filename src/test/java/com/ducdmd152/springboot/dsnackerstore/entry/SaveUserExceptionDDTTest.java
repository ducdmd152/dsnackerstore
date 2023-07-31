package com.ducdmd152.springboot.dsnackerstore.entry;

import static org.testng.Assert.assertTrue;

import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.testng.MockitoTestNGListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ducdmd152.springboot.dsnackerstore.users.User;
import com.ducdmd152.springboot.dsnackerstore.users.UserJpaRepository;
import com.ducdmd152.springboot.dsnackerstore.users.UserServiceImpl;

@SpringBootTest
@Listeners(MockitoTestNGListener.class)
public class SaveUserExceptionDDTTest {
    @Mock
    private UserJpaRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    // DDT - Data Driven Testing
    @DataProvider(name = "users")
    public static Object[][] initUsers() {
        final int NUMBER_OF_TESTS = 10;
        Object[][] ddtMatrix = new Object[NUMBER_OF_TESTS][2];

        for (int i = 0; i < NUMBER_OF_TESTS; i++) {
            User user = new User();
            user.setUsername("user" + System.currentTimeMillis() + i);
            user.setPassword("" + System.currentTimeMillis());
            user.setEnabled(true);

            ddtMatrix[i][0] = user;
            ddtMatrix[i][1] = user;
        }

        return ddtMatrix;
    }
    @DataProvider(name = "exist_users")
    public static Object[][] initExistUsers() {
        final int NUMBER_OF_TESTS = 10;
        Object[][] ddtMatrix = new Object[NUMBER_OF_TESTS][2];

        for (int i = 0; i < NUMBER_OF_TESTS; i++) {
            User user = new User();
            user.setUsername("user" + System.currentTimeMillis() + i);
            user.setPassword("" + System.currentTimeMillis());
            user.setEnabled(true);

            ddtMatrix[i][0] = user;
            ddtMatrix[i][1] = null;
        }

        return ddtMatrix;
    }

    @BeforeClass
    public void setup() {
        // do sth to setup
    }

    // TestNG test suite for init/inject mock resources
    @Test(priority = 0)
    public void injectedComponentsAreNotNull() {
        assertTrue(userRepository != null);
        assertTrue(userService != null);
        System.out.println("Init Mock resources successfully!");
    }

    // TestNG test suite for saveUser_ method for UserServiceImpl class
    @Test(priority = 1, dataProvider = "users")
    public void givenUserObject_whenSaveUser_thenReturnUserObject(User user, User expected) {
        // precondition
        Mockito.lenient().when(userRepository.findById(user.getUsername()))
                        .thenReturn(Optional.empty());

        // Define return value for method save(user)
        Mockito.lenient().when(userRepository.save(user))
                        .thenReturn(expected);

        // Test match after saved return for SERVICE
        User savedUser = userService.saveUser_(user);

        System.out.println("After saved: " + savedUser);

        // then - verify the output
        assertTrue(savedUser != null);
        assertTrue(savedUser.getUsername().equals(expected.getUsername()));
    }
    
    // TestNG test suite for saveUserWithCatchException method
    @Test(priority = 2, dataProvider = "exist_users")
    public void givenUserObject_whenSaveUserWithCatchException_thenReturnNull(User user, User expected) {
        // precondition
        Mockito.lenient().when(userRepository.findById(user.getUsername()))
                        .thenReturn(Optional.of(user));

        // Define return value for method save(user)
        Mockito.lenient().when(userRepository.save(user))
                        .thenThrow(DuplicateKeyException.class);

        // Test match after saved return for SERVICE
        User savedUser = userService.saveUserWithCatchException(user);
        
        assert(savedUser == expected);
    }
    
    // TestNG test suite for saveUserWithoutCatchException method
    @Test(priority = 3, dataProvider = "exist_users", expectedExceptions = DuplicateKeyException.class)
    public void givenUserObject_whenSaveUserWithoutCatchException_thenReturnException(User user, User expected) {
        // precondition
        Mockito.lenient().when(userRepository.findById(user.getUsername()))
                        .thenReturn(Optional.of(user));

        // Define return value for method save(user)
        Mockito.lenient().when(userRepository.save(user))
                        .thenThrow(DuplicateKeyException.class);

        // Test match after saved return for SERVICE
        User savedUser = userService.saveUserWithoutCatchException(user);
        
//        assert(savedUser == expected);
    }
}
