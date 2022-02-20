package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Upload_Sendkey {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String separatorChar = File.separator;
	String uploadFoloderLocation = projectPath + separatorChar + "uploadFiles" + separatorChar;
	
	//Image name : Verify
	String seleniumImage = "Selenium.jpg";
	String appiumImage = "Appium.png";
	String apiImage = "API.png";
	
	//Image location : Sendkey
	String seleniumImageLocation = uploadFoloderLocation + seleniumImage;
	String appiumImageLocation = uploadFoloderLocation + appiumImage;
	String apiImageLocation = uploadFoloderLocation + apiImage;
	
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
	//@Test
	public void TC_01_One_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		By uploadFileBy = By.xpath("//input[@type='file']");
		//Load file
		//send property/location not path
		driver.findElement(uploadFileBy).sendKeys(seleniumImageLocation);
		sleepInSecond(1);
		driver.findElement(uploadFileBy).sendKeys(appiumImageLocation);
		sleepInSecond(1);
		driver.findElement(uploadFileBy).sendKeys(apiImageLocation);
		sleepInSecond(1);
		
		//Uploading
		List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']")); 
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(1);
		}
		
		//Verify upload success
		//upload xong thì là thẻ a, chưa upload xong là thành thẻ p
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ seleniumImage +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ appiumImage +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ apiImage +"']")).isDisplayed());
		
		
	}
	@Test
	public void TC_02_Multiple_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		By uploadFileBy = By.xpath("//input[@type='file']");
		//Load file
		//send property/location not path
		driver.findElement(uploadFileBy).sendKeys(seleniumImageLocation + "\n" + appiumImageLocation + "\n" + apiImageLocation);
		sleepInSecond(1);
		
		
		//Uploading
		List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']")); 
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(1);
		}
		
		//Verify upload success
		//upload xong thì là thẻ a, chưa upload xong là thành thẻ p
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ seleniumImage +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ appiumImage +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ apiImage +"']")).isDisplayed());
		
		
	}

	@Test
	public void TC_02() {
	}

	@Test
	public void TC_03() {
		
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