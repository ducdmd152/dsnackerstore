package com.ducdmd152.springboot.dsnackerstore.selenium;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.ducdmd152.springboot.dsnackerstore.users.UserInfo;
public class FirstSeleniumWithTestNGFramework {

	private static WebDriver myBrowser;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe"); // ~ câu lệnh Class.forName() trong DBUtil của JavaWeb
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        myBrowser = new ChromeDriver(options);
        
        myBrowser.manage().window().maximize();
	}
	
	@Test(description = "This method validates the login functionality")
	public void testLogin() throws InterruptedException {
        
		//1. Go to DSnacker
		myBrowser.get("http://localhost:8084/");
		Thread.sleep(2000);
		
		//2. Go to Register		
		myBrowser.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		
		UserInfo userInfo = new UserInfo();
		userInfo.setName("Duy Đức");
		userInfo.setUsername("duyduc" + (System.currentTimeMillis()%1000));
		userInfo.setPassword("123456");
		userInfo.setEmail("user" + System.currentTimeMillis() + "@gmail.com");
        
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
        Thread.sleep(2000);
        
        // check expected
        WebElement lbWelcome =  myBrowser.findElement(By.id("lbWelcome"));              
                
        assertEquals(lbWelcome.getText().equals(userInfo.getUsername()), true);
	}
	
	@AfterClass
	public void tearDownClass() throws InterruptedException {
		Thread.sleep(3000);
        myBrowser.close(); // tắt trình duyệt sau khi @Test đc chạy
	}

}



