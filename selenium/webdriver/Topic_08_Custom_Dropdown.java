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
		
		selectItemInCustomDropdownList("//span[@id='number-button']/span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']", "//ul[@id='number-menu']//div", "3");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "3");

	}

	@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdownList("//i[@class='dropdown icon']", "//div[@style='pointer-events:all']/span", "Matt");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Matt");
		
		selectItemInCustomDropdownList("//i[@class='dropdown icon']", "//div[@style='pointer-events:all']/span", "Stevie Feliciano");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Stevie Felician");		
		
		selectItemInCustomDropdownList("//i[@class='dropdown icon']", "//div[@style='pointer-events:all']/span", "Justen Kitsune");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Justen Kitsune");
	}
	
	
	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdownList("//li[@class='dropdown-toggle']", "//a[@href='javascript:void(0)']", "Third Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");
		
		selectItemInCustomDropdownList("//li[@class='dropdown-toggle']", "//a[@href='javascript:void(0)']", "First Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");		
		
		selectItemInCustomDropdownList("//li[@class='dropdown-toggle']", "//a[@href='javascript:void(0)']", "Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");	
	}
	
	@Test
	public void TC_04_Angular_Select() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		selectItemInCustomDropdownList("//ng-select[@bindvalue='provinceCode']//span[@class='ng-arrow-wrapper']", "//div[@role='option']/span", "Thành phố Hồ Chí Minh");
		
		//C1 : getText
		Assert.assertEquals(driver.findElement(By.xpath("//ng-select[@bindvalue='provinceCode']//span[@class='ng-value-label ng-star-inserted']")).getText(), "Thành phố Hồ Chí Minh");
		
		//C2 : getAttribute
		Assert.assertEquals(driver.findElement(By.xpath("//ng-select[@bindvalue='provinceCode']//span[@class='ng-value-label ng-star-inserted']")).getAttribute("innerText"), "Thành phố Hồ Chí Minh");
		
		//C3 : Text không nằm ở trong HTML
		String actualText = (String) jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='provinceCode'] span.ng-value-label\").innerText");
		Assert.assertEquals(actualText, "Thành phố Hồ Chí Minh");
		
		selectItemInCustomDropdownList("//ng-select[@bindvalue='districtCode']//span[@class='ng-arrow-wrapper']", "//div[@role='option']/span", "Huyện Củ Chi");
		
		
		selectItemInCustomDropdownList("//ng-select[@bindvalue='wardCode']//span[@class='ng-arrow-wrapper']", "//div[@role='option']/span", "Xã Tân Thông Hội");

	}
	
	@Test
	public void TC_05_Angular_Enter() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		enterToCustomDropdownList("//ng-select[@bindvalue='provinceCode']//input[@role='combobox']","//div[@role='option']/span" ,"Thành phố Hồ Chí Minh" );
		enterToCustomDropdownList("//ng-select[@bindvalue='districtCode']//input[@role='combobox']","//div[@role='option']/span" ,"Huyện Củ Chi" );
		enterToCustomDropdownList("//ng-select[@bindvalue='wardCode']//input[@role='combobox']","//div[@role='option']/span" ,"Xã Tân Thông Hội" );
	}	
	
	public void enterToCustomDropdownList(String parentLocator, String childLocator, String expectedText ) {
		//Step 1 : Phải lấy đến thẻ input (textbox) thì mới sendkey vào
				driver.findElement(By.xpath(parentLocator)).sendKeys(expectedText);;
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