package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Random_Popup {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
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
		
		jsExecutor = (JavascriptExecutor) driver;
		//Trạng thái của element
		explicitWait = new WebDriverWait(driver,30);
		//Để tìm element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
	@Test
	public void TC_01_Random_Popup_In_DOM() {
		driver.get("https://www.kmplayer.com/home");
		
		WebElement supportHomePopup = driver.findElement(By.cssSelector("img#support-home"));
		sleepInSecond(10);
		//Nếu như popup hiển thị thì close đi hoặc là tương tác lên popup đó
		if (supportHomePopup.isDisplayed()) {
			System.out.println("Case 1 : Popup hiển thị - thì vào close đi");
			//Close 
			//jsExecutor thì không quan tâm đến việc element đó có ẩn hay không
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//area[@alt='close']")));
			sleepInSecond(2);
		}
		//Expected Homepopup not display
		Assert.assertFalse(supportHomePopup.isDisplayed());
		
		//Click download 64x
		driver.findElement(By.xpath("//div[@class='mv_btn']/a[text()='PC 64X']")).click();
		sleepInSecond(2);
		
		//Expected layerpopup display
		WebElement layerPopup = driver.findElement(By.xpath("//div[@class='pop-conts']"));
		Assert.assertTrue(layerPopup.isDisplayed());
		
		//Click download in popup
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//area[@id='down-url']")));
		sleepInSecond(2);
	}

	@Test
	public void TC_02_Random_Popup_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);
		
		//popup ads - Dùng findElements để đếm cả trường hợp nếu không hiển thị
		List<WebElement> couponPopup = driver.findElements(By.cssSelector("div.popup-content"));
		System.out.println("Popup size = "+couponPopup.size());
		
		//Popup hiển thị thì xuất hiện element trong DOM (1 node)
		//Popup bị đóng (k hiển thị) thì k xuất hiện trong DOM (No matching node)
		
		//Nếu như popup hiển thi (size element > 0) thì close đi hoặc tương tác lên nó
		if (couponPopup.size()>0) {
			System.out.println("Case 1 - Có hiển thị popup");
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(2);
		}
		
		//Click vào mua khóa học thiết kế
		driver.findElement(By.xpath("//h4[text()='Khóa học thiết kế hệ thống M&E - Tòa nhà']")).click();
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