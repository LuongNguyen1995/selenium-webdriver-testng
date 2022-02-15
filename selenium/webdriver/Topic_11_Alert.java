package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String authenFirefox = projectPath + "";

	@BeforeClass
	public void beforeClass() {
		if (osName.startsWith("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else if (osName.startsWith("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_mac");
		} else  {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_linux");
		}
		
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//@Test
	public void TC_00_Login_Empty_Data() {
		driver.get("https://demo.guru99.com/v4/");
		driver.findElement(By.name("btnLogin")).click();
		
		//Chờ cho alert xuất hiện, thấy xuất hiện thì switch qua luôn -> Chính xác hơn cách làm bên dưới 
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(3);
		Assert.assertEquals(alert.getText(), "User or Password is not valid");
		alert.accept();
	}
	
	//@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
		sleepInSecond(3);
		//Switch to :Alert/(Frame/Iframe)/tab/windows thì mới thao tác được 
		
		//Switch to alert
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		//In ra màn hình dòng chữ alert
		System.out.println(alert.getText());
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
		
	}

	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
		sleepInSecond(3);
		
		//Switch to alert : Accept Alert
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Ok");
		
		//Cancel
		driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
		sleepInSecond(3);
		
		//Switch to alert : Cancel
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
		
	}

	//@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
		sleepInSecond(3);
		
		//Switch to alert : Accept Alert
		String sendText = "AutomationFC";
		alert = driver.switchTo().alert();
		alert.sendKeys(sendText);
		sleepInSecond(2);
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: "+ sendText);
		
		//Cancel
		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
		sleepInSecond(3);
		
		//Switch to alert : Cancel
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: null");
		
	}
	
	//@Test
	public void TC_04_Authenication_Alert_1() {
		//Thư viện Alert của Selenium không hoạt động với Authenication
		//-> Truyền thẳng username & password vào url
		
		String username = "admin";
		String password = "admin";
		driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
		sleepInSecond(3);
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials')]")).isDisplayed());
	}
	
	@Test
	public void TC_04_Authenication_Alert_2() {
		String username = "admin";
		String password = "admin";
		
		driver.get("https://the-internet.herokuapp.com/");

		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

		driver.get(getAuthenicateLink(basicAuthenLink, username, password));
		sleepInSecond(3);
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials')]")).isDisplayed());
	}

	public String getAuthenicateLink(String url,String username, String password) {
		String[] links  = url.split("//");
		url = links[0] +"//" + username + ":" + password + "@" + links[1];
		return url;
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