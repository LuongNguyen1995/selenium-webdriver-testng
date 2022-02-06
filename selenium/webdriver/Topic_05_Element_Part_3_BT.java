package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_3_BT {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
 
	@Test
	public void TC_01() {
		
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//kiểm tra các phần tử sau hiển thị trên trang
		//email
		WebElement emailID = driver.findElement(By.xpath("//input[@id='mail']"));
		Assert.assertTrue(emailID.isDisplayed());
		//in ra màn hình
		//truyền giá trị vào email
		emailID.sendKeys("Automation Testing");
		//in ra màn hình
		if(emailID.isDisplayed()) {
			System.out.println("Element is displayed");
		}
		else {
			System.out.println("Element is not displayed");
		}
		
		
		
		//age(under18)
		WebElement ageUnder18 = driver.findElement(By.xpath("//label[@for='under_18']"));
		Assert.assertTrue(emailID.isDisplayed());
		//chọn age(under18)
		ageUnder18.click();
		if(ageUnder18.isDisplayed()) {
			System.out.println("Element is displayed");
		}
		else {
			System.out.println("Element is not displayed");
		}
		
		
		//Education
		WebElement education = driver.findElement(By.xpath("//textarea[@id='edu']"));
		Assert.assertTrue(education.isDisplayed());		
		//truyền giá trị vào education
		education.sendKeys("Automation Testing");
		//in ra màn hình
		if(education.isDisplayed()) {
			System.out.println("Element is displayed");
		}
		else {
			System.out.println("Element is not displayed");
		}

		
		
		
		//Kiểm tra User5 không hiển thị trên trang (Chưa làm được)
		WebElement nameUser5 =  driver.findElement(By.name("User5"));
		Assert.assertFalse(nameUser5.isDisplayed());
		//in ra màn hình
		if(nameUser5.isDisplayed()) {
			System.out.println("Element is displayed");
		}
		else {
		System.out.println("Element is not displayed");	
		}
	}
		
	

	@Test
	public void TC_02() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Kiểm tra enable
		//email
		WebElement emailID = driver.findElement(By.xpath("//input[@id='mail']"));
		Assert.assertTrue(emailID.isEnabled());
		if(emailID.isEnabled()) {
			System.out.println("Element is enabled");
		}
		else {
			System.out.println("Element is disabled");
		}
		
		//age(under18)
		WebElement ageUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		Assert.assertTrue(ageUnder18.isEnabled());
		if(ageUnder18.isEnabled()) {
			System.out.println("Element is enabled");
		}
		else {
			System.out.println("Element is disabled");
		}
		//Education
		WebElement education = driver.findElement(By.xpath("//textarea[@id='edu']"));
		Assert.assertTrue(education.isEnabled());	
		if(education.isEnabled()) {
			System.out.println("Elemnt is enabled");
		}
		else {
			System.out.println("Element is disabled");
		}
		
		//Job Role 1
		WebElement jobRole1 = driver.findElement(By.xpath("//select[@id='job1']"));
		Assert.assertTrue(jobRole1.isEnabled());	
		if(jobRole1.isEnabled()) {
			System.out.println("Elemnt is enabled");
		}
		else {
			System.out.println("Element is disabled");
		}
		
		//Job Role 2
		WebElement jobRole2 = driver.findElement(By.xpath("//select[@id='job2']"));
		Assert.assertTrue(jobRole2.isEnabled());	
		if(jobRole2.isEnabled()) {
		System.out.println("Elemnt is enabled");
		}
		else {
			System.out.println("Element is disabled");
		}

		//Interest - Development
		WebElement interestDevelopment = driver.findElement(By.xpath("//label[@for='development']"));
		Assert.assertTrue(interestDevelopment.isEnabled());	
		if(interestDevelopment.isEnabled()) {
		System.out.println("Elemnt is enabled");
		}
		else {
			System.out.println("Element is disabled");
		}

		//Slider-01
		WebElement slider01 = driver.findElement(By.xpath("//input[@id='slider-1']"));
		Assert.assertTrue(slider01.isEnabled());	
		if(slider01.isEnabled()) {
		System.out.println("Elemnt is enabled");
		}
		else {
			System.out.println("Element is disabled");
		}
		
		
		//Kiểm tra disable
		//password
		WebElement password = driver.findElement(By.xpath("//input[@id='disable_password']"));
		Assert.assertFalse(password.isEnabled());	
		if(password.isEnabled()) {
		System.out.println("Elemnt is enabled");
		}	
		else {
			System.out.println("Element is disabled");
		}
		//age radio button
		WebElement radioButton = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
		Assert.assertFalse(radioButton.isEnabled());	
		if(radioButton.isEnabled()) {
		System.out.println("Elemnt is enabled");
		}			
		else {
			System.out.println("Element is disabled");
		}
		//Biography
		WebElement biography = driver.findElement(By.xpath("//textarea[@id='bio']"));
		Assert.assertFalse(biography.isEnabled());	
		if(biography.isEnabled()) {
		System.out.println("Elemnt is enabled");
		}			
		else {
			System.out.println("Element is disabled");
		}
		//Job Role 3
		WebElement jobRole3 = driver.findElement(By.xpath("//select[@id='job3']"));
		Assert.assertFalse(jobRole3.isEnabled());	
		if(jobRole3.isEnabled()) {
		System.out.println("Elemnt is enabled");
		}			
		else {
			System.out.println("Element is disabled");
		}
		//interest checkbox is disable
		WebElement interestCheckbox = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
		Assert.assertFalse(interestCheckbox.isEnabled());	
		if(interestCheckbox.isEnabled()) {
		System.out.println("Element is enabled");
		}
		else {
			System.out.println("Element is disabled");
		}
		
		//slider02
		WebElement slider02 = driver.findElement(By.xpath("//input[@id='slider-2']"));
		Assert.assertFalse(slider02.isEnabled());	
		if(slider02.isEnabled()) {
			System.out.println("Element is enabled");
		}			
		else {
			System.out.println("Element is disabled");
		}	
		
	}

	@Test
	public void TC_03() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//age(under18)
		WebElement ageUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));

		//chọn age(under18)
		ageUnder18.click();
		//Kiểm tra xem đã chọn hay chưa
		Assert.assertTrue(ageUnder18.isSelected());			
		ageUnder18.isSelected();
		//In ra màn hình
		if(ageUnder18.isSelected()) {
			System.out.println("Element is selected");
		}
		else {
			System.out.println("Element is not selected");
		}
		
		//language java check box
		WebElement languageJava = driver.findElement(By.xpath("//input[@id='java']"));
		//Chọn Java
		languageJava.click();
		//Kiểm tra xem đã chọn hay chưa
		Assert.assertTrue(languageJava.isSelected());			
		//in ra màn hình
		if(languageJava.isSelected()) {
			System.out.println("Element is selected");
		}
		else {
			System.out.println("Element is not selected");
		}
		//Bỏ chọn Java
		languageJava.click();
		//Kiểm tra đã bỏ chọn
		Assert.assertFalse(languageJava.isSelected());
		//in ra màn hình
		if(languageJava.isSelected()) {
			System.out.println("Element is selected");
		}
		else {
			System.out.println("Element is de-selected");
		}
		
	}
	
	@Test
	public void TC_04() {
		driver.get("https://login.mailchimp.com/signup/");
		WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
		WebElement username = driver.findElement(By.xpath("//input[@id='new_username']"));
		WebElement password = driver.findElement(By.xpath("//input[@id='new_password']"));
		
		//Nhap data valid Email/Username
		email.sendKeys("luong.epu.dtvt1@gmail.com");
		username.sendKeys("Luongepudtvt");
		
		//Show password
		driver.findElement(By.xpath("//label[@for='show-password']")).click();
		//Nhập mật khẩu không hợp lệ
		//Nhập số 
		password.sendKeys("123");
		
	
		
		//Tích vào check box
		WebElement checkbox =  driver.findElement(By.xpath("//input[@name='marketing_newsletter']"));
		checkbox.click();
		//Verify checkbox
		Assert.assertTrue(checkbox.isSelected());
		
	
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
}