package com.ducdmd152.springboot.dsnackerstore.selenium;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ducdmd152.springboot.dsnackerstore.users.UserInfo;


public class DDTSeleniumWithTestNGFramework {

	private static WebDriver myBrowser;
	// DDT - Data Driven Testing
	@DataProvider(name = "users")
	public static Object[][] initUsers() {
		final int NUMBER_OF_TESTS = 3;
		Object[][] ddtMatrix = new Object[NUMBER_OF_TESTS][2];
		
	    for(int i=0;i<NUMBER_OF_TESTS;i++) {
	    	UserInfo userInfo = new UserInfo();
			userInfo.setName("Duy Đức");
			userInfo.setUsername("duyduc" + (System.currentTimeMillis()%1000000000 + i));
			userInfo.setPassword("123456");
			userInfo.setEmail("user" + System.currentTimeMillis() + "@gmail.com");
			
			ddtMatrix[i][0] = userInfo;
			ddtMatrix[i][1] = true;
	    }
	    	
	    return ddtMatrix;
	}
		
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe"); // ~ câu lệnh Class.forName() trong DBUtil của JavaWeb
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        myBrowser = new ChromeDriver(options);
        
        myBrowser.manage().window().maximize();
	}
	
	@Test(dataProvider = "users", description = "This method validates the register & login functionality")
	public void testAuthentication(UserInfo userInfo, boolean expected) throws InterruptedException {
        
		//1. Go to DSnacker
		myBrowser.get("http://localhost:8084/");
		Thread.sleep(2000);
		
		//2. Go to Register		
		myBrowser.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		
		
        
		// Sign Up
        myBrowser.findElement(By.id("fullname")).sendKeys(userInfo.getName());  
        Thread.sleep(1000);
        myBrowser.findElement(By.id("username")).sendKeys(userInfo.getUsername());
        Thread.sleep(1000);
        myBrowser.findElement(By.id("password")).sendKeys(userInfo.getPassword());
        Thread.sleep(1000);
        myBrowser.findElement(By.id("email")).sendKeys(userInfo.getEmail());
        Thread.sleep(1000);
        myBrowser.findElement(By.cssSelector("button[type='submit']")).click();
        
        // loading req <-> res => redirect to login
        Thread.sleep(2000);
        
        // Login
        myBrowser.findElement(By.id("username")).sendKeys(userInfo.getUsername());
        Thread.sleep(1000);
        myBrowser.findElement(By.id("password")).sendKeys(userInfo.getPassword());
        Thread.sleep(1000);
        myBrowser.findElement(By.cssSelector("button[type='submit']")).click();
        
        // loading req <-> res => redirect to welcome
        Thread.sleep(3000);
        
        // check expected
        WebElement lbWelcome =  myBrowser.findElement(By.id("lbWelcome"));              
                
        assertEquals(lbWelcome.getText().equals(userInfo.getUsername()), expected);  
        
        // logout
        myBrowser.findElement(By.id("btnLogout")).click();
	}

	@AfterMethod
	public void tearDownMethod() throws InterruptedException {
//		Thread.sleep(3000);
	}
	
	@AfterClass
	public void tearDownClass() throws InterruptedException {
		Thread.sleep(3000);
        myBrowser.close(); // tắt trình duyệt sau khi @Test đc chạy
	}
}