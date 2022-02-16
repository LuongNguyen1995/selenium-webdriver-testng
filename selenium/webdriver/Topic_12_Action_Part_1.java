package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.Colors;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Action_Part_1 {
	WebDriver driver;
	Actions action;
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
		
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
	//@Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");
		
	}

	//@Test
	public void TC_02_Hover() {
		driver.get("https://www.fahasa.com/?attempt=1");
		sleepInSecond(5);
		action.moveToElement(driver.findElement(By.xpath("//div[@class='row custom-menu-homepage']//span[text()='Sách Trong Nước']"))).perform();
		sleepInSecond(5);
		
		//C1 : Click dùng thư viện Action (Khác ở chỗ là phải move chuột qua, chức năng vẫn thế)
		//action.click(driver.findElement(By.xpath("//div[@class='row custom-menu-homepage']//a[text()='Kỹ Năng Sống']"))).perform();
		
		//C2 : Click dùng thư viện WebElement 
		driver.findElement(By.xpath("//div[@class='row custom-menu-homepage']//a[text()='Kỹ Năng Sống']")).click();
		
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Kỹ năng sống']")).isDisplayed());
	}

	//@Test
	public void TC_03_Click_And_Hold_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> rectangleNumbers = driver.findElements(By.xpath("//li"));
		System.out.println("Tổng element = " +rectangleNumbers.size());
		
		//Muốn chọn từ 1 - 8
		// - ClickAndHold vào element 1
		// - MoveToElement đến element 8
		// - Nhả chuột trái ra (release()) -> cuối cùng thì perform
		action.clickAndHold(rectangleNumbers.get(0)).moveToElement(rectangleNumbers.get(7)).release().perform();
		sleepInSecond(3);
		
		//Verify
		List<WebElement> rectangleNumberSelected = driver.findElements(By.cssSelector("li[class$='ui-selected']"));
		System.out.println("Tổng element đã chọn = " + rectangleNumberSelected.size());
	
		Assert.assertEquals(rectangleNumberSelected.size(), 8);
	}
	
	//@Test
	public void TC_04_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> rectangleNumbers = driver.findElements(By.xpath("//li"));
		System.out.println("Tổng element = " +rectangleNumbers.size());
		
		//Nhấn phím Ctrl xuống
		action.keyDown(Keys.CONTROL).perform();
		//Click nhiều element 1 lúc
		action.click(rectangleNumbers.get(2)).click(rectangleNumbers.get(3)).click(rectangleNumbers.get(10)).perform();
		
		//Nhả phím Ctrl lên
		action.keyUp(Keys.CONTROL).perform();
		sleepInSecond(5);
		
		List<WebElement> rectangleNumberSelected = driver.findElements(By.cssSelector("li[class$='ui-selected']"));
		System.out.println("Tổng element đã chọn = " + rectangleNumberSelected.size());
	
		Assert.assertEquals(rectangleNumberSelected.size(), 3);
		}
	
	//@Test
	public void TC_05_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Trên firefox không tự scroll xuống element được (ngoài viewport)
		//Chrome có thể tự click mà không cần xuống element (k quan tâm viewport)
		
		//Scroll xuống element
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		sleepInSecond(2);	
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
		
	}
	
	//@Test
	public void TC_06_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		//Click and Hover
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(3);
		
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-copy"))).perform();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-copy.context-menu-hover.context-menu-visible")).isDisplayed());
		//Click
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-copy"))).perform();
		//Verify alert
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: copy");
		sleepInSecond(2);
		driver.switchTo().alert().accept();	
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-copy")).isDisplayed());
		}
	
	//@Test
	public void TC_07_Drag_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement sourceCircle = driver.findElement(By.id("draggable"));
		WebElement targetCircle = driver.findElement(By.id("droptarget"));
		
		//Không dùng drag and drop by do thay đổi tọa độ thì sai luôn
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		sleepInSecond(3);
		
		//Verify
		Assert.assertEquals(targetCircle.getText(), "You did great!");
		String rgbColor = targetCircle.getCssValue("background-color");
		System.out.println("RGB Color = "+rgbColor);
		//Convert rgb to hexa
		String hexaColor = Color.fromString(rgbColor).asHex().toLowerCase();
		System.out.println("Hexa Color = "+hexaColor);
		Assert.assertEquals(hexaColor, "#03a9f4");
		
	}
	
	@Test
	public void TC_08_Drag_Drop_HTML5_Css() {
		
	}
	
	@Test
	public void TC_09_Drag_Drop_HTML5_Xpath() {
		
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