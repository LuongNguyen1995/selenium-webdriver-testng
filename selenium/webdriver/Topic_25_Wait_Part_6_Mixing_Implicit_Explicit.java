package webdriver;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_25_Wait_Part_6_Mixing_Implicit_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
		driver.manage().window().maximize();
	}
 
	@Test //Happy path case
	public void TC_01_Found() {
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		explicitWait = new WebDriverWait(driver, 15);
		
		System.out.println("1.1 - Start implicit wait : " + getDateTimeNow() );
		driver.findElement(By.id("email"));
		System.out.println("1.2 - End implicit wait : " + getDateTimeNow() );
		
		System.out.println("2.1 - Start explicit wait : " + getDateTimeNow() );
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		System.out.println("2.2 - End explicit wait : " + getDateTimeNow() );

	}

	@Test //Unhappy path case
	public void TC_02_Not_Found_Only_Implicit() {
		//Nếu không tìm thấy thì hết 15s trong implicitWait
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		System.out.println("1.1 - Start implicit wait : " + getDateTimeNow() );
		//Thêm try catch thì sẽ không fail testcase, k chạy đc phần trong try sẽ vào trong catch đếm thời gian chạy
		try {
			driver.findElement(By.id("selenium"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("1.2 - End implicit wait : " + getDateTimeNow() );
	}

	@Test //Unhappy path case
	public void TC_03_Not_Found_Mix_Implicit_And_Explicit() {
		//Time Explicit = Time Implicit
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		explicitWait = new WebDriverWait(driver, 15);
		
		System.out.println("1.1 - Start implicit wait : " + getDateTimeNow() );
		try {
			driver.findElement(By.id("selenium"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("1.2 - End implicit wait : " + getDateTimeNow() );
		
		System.out.println("2.1 - Start explicit wait : " + getDateTimeNow() );
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selenium")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("2.2 - End explicit wait : " + getDateTimeNow() );
	}

	@Test //Unhappy path case
	public void TC_04_Not_Found_Only_Explicit_By() {
		driver.get("https://www.facebook.com/");
		explicitWait = new WebDriverWait(driver, 7);
		
		System.out.println("2.1 - Start explicit wait : " + getDateTimeNow() );
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selenium")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("2.2 - End explicit wait : " + getDateTimeNow() );
	}
	
	@Test //Unhappy path case
	public void TC_05_Not_Found_Only_Explicit_WebElement() {
		driver.get("https://www.facebook.com/");
		explicitWait = new WebDriverWait(driver, 7);
		
		System.out.println("2.1 - Start explicit wait : " + getDateTimeNow() );
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("selenium"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("2.2 - End explicit wait : " + getDateTimeNow() );		
		
		
	}
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
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
	public void sleepInMinisecond(long second) {
		try {
			Thread.sleep(second);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}