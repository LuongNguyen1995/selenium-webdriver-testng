package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		
		//wait cho các trạng thái của element : visible/PRESENCE/invisible/staleness
		explicitWait = new WebDriverWait(driver,15);
		
		//ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;
		
		//Wait cho việc tìm element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		
		selectItemInCustomDropdownList("//span[@id='number-button']/span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']", "//ul[@id='number-menu']//div", "5");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "5");
		
		selectItemInCustomDropdownList("//span[@id='number-button']/span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']", "//ul[@id='number-menu']//div", "15");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "15");

		selectItemInCustomDropdownList("//span[@id='number-button']/span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']", "//ul[@id='number-menu']//div", "19");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "19");
		
		selectItemInCustomDropdownList("//span[@id='number-button']/span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']", "//ul[@id='number-menu']//div", "19");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "3");

	}



	public void selectItemInCustomDropdownList(String parentLocator, String childLocator, String expectedText ) {
		//Step 1 : Click vào 1 element cho nó xổ hết toàn bộ item 
				driver.findElement(By.xpath(parentLocator)).click();
				sleepInSecond(2);
				
				//Step 2 : Chờ cho các item load hết ra 
				//Lưu ý 1 : (Lấy locator chứa hết tất cả các item)
				//Lưu ý 2 : Locator phải đến node cuối cùng chứa text
				explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childLocator)));
				
				
				//Step 3 : Tìm các item cần chọn

				
				//Lấy hết tất cả các element (item) sau đó duyệt qua từng item

				List<WebElement> allItems =  driver.findElements(By.xpath(childLocator));

				//Duyệt qua từng item, getText từng item ra
				
				for (WebElement item : allItems) {
					String actualText = item.getText();
					System.out.println("Actual Text = "+ actualText);
					
					//Nếu text = text mình mong muốn (item cần click vào) thì click vào đó
					if (actualText.equals(expectedText)) {
						//Step 3.1 : Nếu item cần chọn nằm trong vùng có sẵn  thì k cần scroll xuống tìm
						//Step 3.2 : Nếu item cần chọn không nằm trong vùng có sẵn thì phải scroll tới element
						
						jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
						sleepInSecond(2);
						
						//Step 4 : Click chọn item đó
						item.click();
						sleepInSecond(2);
						//Thoát khỏi vòng lặp, k kiểm tra element tiếp theo
						break;
					}
				}
				sleepInSecond(10);
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