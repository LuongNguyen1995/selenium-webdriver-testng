package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_4_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, emailAddress, password, nonExistedEmailPassword;

	// Global variable
	By emailLoginBy = By.id("email");
	By passwordLoginBy = By.id("pass");

	By verifyEmailLogin = By.xpath("//ul[@class='form-list']//input[@type='email']/following-sibling::div");
	By verifypasswordLogin = By.xpath("//ul[@class='form-list']//input[@type='password']/following-sibling::div");

	By login = By.id("send2");

	@BeforeClass // Test class, chạy trước cho testcase đầu tiên
	public void beforeClass() { //pre-condition
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		firstName = "Nguyen";
		lastName = "Luong"; 
		emailAddress = "luong.epu.dtvt"+ getRandomNumber() +"@gmail.com";
		password = "123456";
		nonExistedEmailPassword = "luong.epu.dtvt"+ getRandomNumber() +"@hotmail.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@BeforeMethod // Chung precondition cho từng testcase- Test method
	public void beforeMethod() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	}

	@Test
	public void Login_01_Empty_Data() {
		driver.findElement(emailLoginBy).clear();
		driver.findElement(passwordLoginBy).clear();
		driver.findElement(login).click();

		// Verify error message xuất hiện tại 2 field
		Assert.assertEquals(driver.findElement(verifyEmailLogin).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(verifypasswordLogin).getText(), "This is a required field.");

	}

	@Test
	public void Login_02_Invalid_Email() {
		driver.findElement(emailLoginBy).sendKeys("12344234@12312.123123");
		driver.findElement(passwordLoginBy).sendKeys("123456");
		driver.findElement(login).click();

		Assert.assertEquals(driver.findElement(verifyEmailLogin).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void Login_03_Password_Under6_Char() {
		driver.findElement(emailLoginBy).sendKeys("automation@gmail.com");
		driver.findElement(passwordLoginBy).sendKeys("123");
		driver.findElement(login).click();

		Assert.assertEquals(driver.findElement(verifypasswordLogin).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void Login_04_Create_New_Account() {
		// Existed email

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);

		driver.findElement(By.xpath("//button[@title='Register']")).click();

		// Verify mess xuất hiện đăng kí thành công : Thank you....
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(),"MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"Thank you for registering with Main Website Store.");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(),"Hello, "+firstName+" "+lastName+"!");
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		
		//Assert tương đối (chứa cái gì)
		Assert.assertTrue(contactInformation.contains(firstName+" "+lastName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
		

		// Logout
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']/span[text()]")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

		// verify logout to homepage, hàm isDisplay() có thể dùng để wait được
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title img[src$='logo.png']")).isDisplayed());

	}

	@Test
	public void Login_05_Incorrect_Email_Or_Password() {
		// Existed Email + incorrect password -> Unsuccess
		driver.findElement(emailLoginBy).sendKeys(emailAddress);
		driver.findElement(passwordLoginBy).sendKeys("123123123");
		driver.findElement(login).click();

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),
				"Invalid login or password.");

		// Non Existed Email + correct password -> Unsuccess
		driver.findElement(emailLoginBy).clear();
		driver.findElement(emailLoginBy).sendKeys(nonExistedEmailPassword);
		
		driver.findElement(passwordLoginBy).clear();
		driver.findElement(passwordLoginBy).sendKeys(password);
		driver.findElement(login).click();

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),
				"Invalid login or password.");

	}

	@Test
	public void Login_06_Invalid_Phone() {

		driver.findElement(emailLoginBy).sendKeys(emailAddress);
		driver.findElement(passwordLoginBy).sendKeys(password);
		driver.findElement(login).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(),"MY DASHBOARD");
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(),"Hello, "+firstName+" "+lastName+"!");
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		
		//Assert tương đối (chứa cái gì)
		Assert.assertTrue(contactInformation.contains(firstName+" "+lastName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
		
	}

	@AfterClass //post-condition
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedExcepton e) {
			e.printStackTrace();
		}

	}
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
		
	}
}