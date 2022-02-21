package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Wait_Part_2_Find_Element {
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
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
	}
	
	//@Test
	public void TC_01_Find_Element() {
		//WebElement element = null;
		
		//1 - Không có element nào được tìm thấy
		
		//element = driver.findElement(By.id("Selenium"));
		//Khi 1 Step fail sẽ fail cả TC và k chạy các step còn lại nữa
		
		//2 - Có 1 element được tìm thấy
		
		//element = driver.findElement(By.id("email"));
		//element.sendKeys("123123112");
		
		//3- Có nhiều hơn 1 element được tìm thấy : k quan tâm element visible hoặc invisible
		
		//element = driver.findElement(By.xpath("//input[@id='email' or @id='pass']"));
		//element.sendKeys("123123112");
		//sleepInSecond(2);
		//element.clear();
	}
	
	//@Test
	public void TC_02_Find_Elements() {
		//List<WebElement> links = null;
		
		//1 - Không có element nào được tìm thấy
		
		//links = driver.findElements(By.id("Selenium"));
		//System.out.println("Total element = " + links.size());
		
		//2 - Có 1 element được tìm thấy
		
		//links = driv er.findElements(By.id("email"));
		//System.out.println("Total element = " + links.size());
		//links.get(0).clear();
		//links.get(0).sendKeys("1231123321");
		
		//3- Có nhiều hơn 1 element được tìm thấy : Lấy toàn bộ đưa vào list
		
		//links = driver.findElements(By.xpath("//input[@id='email' or @id='pass']"));
		//System.out.println("Total element = " + links.size());
		//links.get(0).sendKeys("vsdfsdfs");
		//links.get(1).sendKeys("123123123321312312");
		//sleepInSecond(2);
	}
	
	@Test
	public void TC_03() {
		driver.findElement(By.id("email")).sendKeys("luong@gmail.com");
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.findElement(By.id("pass")).sendKeys("123123123");
		
	}

	@Test
	public void TC_04() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("send2")).click();
		
		
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