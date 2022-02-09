package webdriver;

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

public class Topic_05_Element_Part_4_Register {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	By fullNameTextboxBy = By.id("txtFirstname");
	By emailTextboxBy = By.id("txtEmail");
	By confirmEmailTextboxBy = By.id("txtCEmail");
	By passwordTextboxBy = By.id("txtPassword");
	By confirmPasswordTextboxBy = By.id("txtCPassword");
	By phoneTextboxBy = By.id("txtPhone");
	
	By firstnameErrorMessage = By.id("txtFirstname-error");
	By emailErrorMessage = By.id("txtEmail-error");
	By confirmEmailErrorMessage = By.id("txtCEmail-error");
	By passwordErrorMessage = By.id("txtPassword-error");
	By confirmPasswordErrorMessage = By.id("txtCPassword-error");
	By phoneErrorMessage = By.id("txtPhone-error");
	
	By registerButtonBy = By.xpath("//button[@type='submit']");
	
	@BeforeClass // Test class
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	@BeforeMethod //Chung precondition - Test method
	public void beforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
	}
	
 
	@Test
	public void Register_01_Empty_Data() {
		driver.findElement(fullNameTextboxBy).clear();
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(confirmEmailTextboxBy).clear();
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(confirmPasswordTextboxBy).clear();
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(registerButtonBy).click();
		
		//Kiểm tra xem có trùng thông tin hay không (assert Equal)
		Assert.assertEquals(driver.findElement(firstnameErrorMessage).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailErrorMessage).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMessage).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passwordErrorMessage).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMessage).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneErrorMessage).getText(), "Vui lòng nhập số điện thoại.");		
		
		}

	@Test
	public void Register_02_Invalid_Email() {
		driver.findElement(fullNameTextboxBy).sendKeys("Nguyễn Đình Lương");
		driver.findElement(emailTextboxBy).sendKeys("121221@324@32");
		driver.findElement(confirmEmailTextboxBy).sendKeys("121221@324");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123456");
		driver.findElement(phoneTextboxBy).sendKeys("0987654321");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(emailErrorMessage).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMessage).getText(), "Email nhập lại không đúng");
	}
	
	@Test
	public void Register_03_Incorrect_Confirm_Email() {
		driver.findElement(fullNameTextboxBy).sendKeys("Nguyễn Đình Lương");
		driver.findElement(emailTextboxBy).sendKeys("luong@gmail.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("121221@324d");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123456");
		driver.findElement(phoneTextboxBy).sendKeys("0987654321");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(confirmEmailErrorMessage).getText(), "Email nhập lại không đúng");
	
	}
	
	@Test
	public void Register_04_Invalid_Password() {
		driver.findElement(fullNameTextboxBy).sendKeys("Nguyễn Đình Lương");
		driver.findElement(emailTextboxBy).sendKeys("luong@gmail.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("luong@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("12345");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("12345");
		driver.findElement(phoneTextboxBy).sendKeys("0987654321");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(passwordErrorMessage).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		
	}
	
	@Test
	public void Register_05_Incorrect_Confirm_Password() {
		driver.findElement(fullNameTextboxBy).sendKeys("Nguyễn Đình Lương");
		driver.findElement(emailTextboxBy).sendKeys("luong@gmail.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("luong@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123457");
		driver.findElement(phoneTextboxBy).sendKeys("0987654321");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMessage).getText(), "Mật khẩu bạn nhập không khớp");
		
	}
	
	@Test
	public void Register_06_Invalid_Phone() {
		driver.findElement(fullNameTextboxBy).sendKeys("Nguyễn Đình Lương");
		driver.findElement(emailTextboxBy).sendKeys("luong@gmail.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("luong@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123456");
		
		
		driver.findElement(phoneTextboxBy).sendKeys("0987651");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMessage).getText(), "Số điện thoại phải từ 10-11 số. ");	
		
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("0287654321");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(phoneErrorMessage).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");		
		
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond (long second) {
		try {
			Thread.sleep( second * 1000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
}