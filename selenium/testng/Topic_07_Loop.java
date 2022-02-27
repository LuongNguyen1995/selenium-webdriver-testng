package testng;

import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_07_Loop {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	String emailAddress = "afc";
	String password = "afc123";
	
	
	@BeforeClass
	public void beforeClass() {

	}
	


	@Test (invocationCount = 100)
	public void TC_01_Register_To_System(String emailAddress, String password) {
		System.out.println(emailAddress + getRandomNumber() + "@mail.com");
		System.out.println(password);
	}

	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
		
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
