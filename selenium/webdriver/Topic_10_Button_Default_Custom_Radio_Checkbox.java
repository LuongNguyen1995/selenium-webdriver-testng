package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_Default_Custom_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
	//@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
		driver.findElement(By.xpath("//ul[@class='popup-login-tab']//a[text()='Đăng nhập']")).click();
		
		By loginButtonBy = By.xpath("//div[@class='fhs-btn-box']/button[@title='Đăng nhập' and @class='fhs-btn-login']");
		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
		
		//isEnable : Nếu 1 element mà enable -> true
		//isEnable : Nếu 1 element mà disable -> false
		
		driver.findElement(By.id("login_username")).sendKeys("AutomationFC@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("AutomationFC");
		sleepInSecond(1);
		
		//Verify
		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
		//Verify background = RED
		String loginButtonBackgroundColorRGB = driver.findElement(loginButtonBy).getCssValue("background-color");
		System.out.println("RGB color = "+loginButtonBackgroundColorRGB);
		Assert.assertEquals(loginButtonBackgroundColorRGB, "rgb(201, 33, 39)");
		
		//Convert RGB to Hexa
		String loginButtonBackgroundColorHexa = Color.fromString(loginButtonBackgroundColorRGB).asHex();
		System.out.println("Hexa color = "+loginButtonBackgroundColorHexa);
		Assert.assertEquals(loginButtonBackgroundColorHexa.toUpperCase(), "#C92127");

		
		driver.navigate().refresh();
		driver.findElement(By.xpath("//ul[@class='popup-login-tab']//a[text()='Đăng nhập']")).click();

		//remove disable attribute
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled');",driver.findElement(loginButtonBy));
		sleepInSecond(2);
		
		driver.findElement(loginButtonBy).click();
		
		//Verify notice
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	
	
	}

	//@Test
	public void TC_02_Default_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		
		//default - the input
		//Action : Click
		//Verify
		
		
		//Custom -the input
		//Action : Can not click
		//Verify ok
		By oneDotEightPetrolRadio = By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input");
		By twoDotZeroPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		By threeDotSixPetrolRadio = By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::input");
		Assert.assertFalse(driver.findElement(oneDotEightPetrolRadio).isSelected());
				
		driver.findElement(oneDotEightPetrolRadio).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(oneDotEightPetrolRadio).isSelected());
	
		driver.findElement(twoDotZeroPetrolRadio).click();
		sleepInSecond(2);
		
		//De-selected
		Assert.assertFalse(driver.findElement(oneDotEightPetrolRadio).isSelected());
		//Selected
		Assert.assertTrue(driver.findElement(twoDotZeroPetrolRadio).isSelected());
		//Enable
		Assert.assertTrue(driver.findElement(oneDotEightPetrolRadio).isEnabled());
		Assert.assertTrue(driver.findElement(twoDotZeroPetrolRadio).isEnabled());
		//Disable
		Assert.assertFalse(driver.findElement(threeDotSixPetrolRadio).isEnabled());

		
	}

	//@Test
	public void TC_03_Default_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		By luggageCheckbox = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");
		By heatedCheckbox = By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input");
		By towbarCheckbox  = By.xpath("//label[text()='Towbar preparation']/preceding-sibling::input");	
		By leatherCheckbox  = By.xpath("//label[text()='Leather trim']/preceding-sibling::input");	
		
		//Select
		//Nếu chưa chọn thì click chọn
		checkToCheckbox(luggageCheckbox);
		checkToCheckbox(heatedCheckbox);
		
		//Verify Selected
		Assert.assertTrue(isElementSelected(luggageCheckbox));
		Assert.assertTrue(isElementSelected(heatedCheckbox));
		Assert.assertTrue(isElementSelected(leatherCheckbox));
		
		//Verify disable
		Assert.assertFalse(isElementEnable(towbarCheckbox));
		Assert.assertFalse(isElementEnable(leatherCheckbox));
		
		//De-Select
		//Nếu chọn thì click bỏ chọn
		unCheckToCheckbox(luggageCheckbox);
		unCheckToCheckbox(heatedCheckbox);
		
		//Verify De-Selected
		Assert.assertFalse(isElementSelected(luggageCheckbox));
		Assert.assertFalse(isElementSelected(heatedCheckbox));
		Assert.assertFalse(isElementSelected(towbarCheckbox));
		
		
	}
	
	//@Test
	public void TC_04_Multiple_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		System.out.println("Checkbox size = "+ checkboxes.size());
		
		//Action - select
		for (WebElement checkbox : checkboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
		}
		
		//Verify - selected
		for (WebElement checkbox : checkboxes) {
			Assert.assertTrue(checkbox.isSelected());
		}
		
		//Action - de-select
		for (WebElement checkbox : checkboxes) {
			if (checkbox.isSelected()) {
				checkbox.click();
			}
		}
		
		//Verify - de-selected
		for (WebElement checkbox : checkboxes) {
			Assert.assertFalse(checkbox.isSelected());
		}
	}
	
	//@Test
	public void TC_05_Custom_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		//Case 1 : Dùng thẻ input : 
		//Selenium click : -> ElementNotInteractableException
		//isSelected() => work
		//Default Radio/Checkbox
		By winterCheckboxInput = By.xpath("//input[@value='Winter']");
		
		//Case 2 : Dùng thẻ span
		//Selenium click : -> work
		//isSelected() => NOT work
		By winterCheckboxSpan = By.xpath("//input[@value='Winter']/preceding-sibling::span[@class='mat-radio-outer-circle']");
		
		
		//Case 3 : Dùng thẻ span để click() & Dùng thẻ input - isSelected()
		//1 element phải defind 2 locator
		//Dễ bị nhầm lẫn : Team member họ dùng -> Hiểu nhầm
		//Bảo trì : Code nhiều -> NOT OK
		
		
		//Case 4 : Dùng thẻ input
		//Javascript - click (không quan tâm element ẩn/hiện)
		//isSelected - verify
		
		clickByJavascript(winterCheckboxInput);
		sleepInSecond(2);
		//Verify : isSelected()
		Assert.assertTrue(driver.findElement(winterCheckboxInput).isSelected());
		
	}
	
	//@Test
	public void TC_06_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		By indeterminateCheckbox = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");

		//Chọn
		clickByJavascript(checkedCheckbox);
		sleepInSecond(1);

		clickByJavascript(indeterminateCheckbox);
		sleepInSecond(1);

		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());

		//Bỏ chọn
		clickByJavascript(checkedCheckbox);
		sleepInSecond(1);

		clickByJavascript(indeterminateCheckbox);
		sleepInSecond(1);

		Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());		
		
	}
	
	//@Test
	public void TC_07_Custom_Radio() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		By dangKyBanThanRadio = By.xpath("//div[text()='Đăng ký bản thân']/preceding-sibling::div/input");
		By dangKyChoNguoiThanRadio = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		
		clickByJavascript(dangKyChoNguoiThanRadio);
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(dangKyChoNguoiThanRadio).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='registerFullname']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='registerPhoneNumber']")).isDisplayed());
		
		clickByJavascript(dangKyBanThanRadio);
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(dangKyBanThanRadio).isSelected());
		//Nếu số lượng element là 0 thì sẽ trả về kết quả true, assertFalse không sài được
		Assert.assertEquals(driver.findElements(By.xpath("//input[@formcontrolname='registerFullname']")).size(), 0);
		Assert.assertEquals(driver.findElements(By.xpath("//input[@formcontrolname='registerPhoneNumber']")).size(), 0);
	}
	
	@Test
	public void TC_08_Custom_Radio_Checkbox_Goolge_Doc() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By haiPhongCityRadio = By.xpath("//div[@aria-label='Hải Phòng']");
		By quangNamCityCheckbox = By.xpath("//div[@aria-label='Quảng Nam']");
		
		driver.findElement(haiPhongCityRadio).click();
		driver.findElement(quangNamCityCheckbox).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(haiPhongCityRadio).getAttribute("aria-checked"), "false");	
		Assert.assertEquals(driver.findElement(quangNamCityCheckbox).getAttribute("aria-checked"), "false");

		//C1 : Verify : Dựa trên sự thay đổi trạng thái của element
		Assert.assertEquals(driver.findElement(haiPhongCityRadio).getAttribute("aria-checked"), "true");	
		Assert.assertEquals(driver.findElement(quangNamCityCheckbox).getAttribute("aria-checked"), "true");

		//C2 : Dùng isDisplay thì phải thêm điều kiện
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Hải Phòng'] and aria-checked = true")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam'] and aria-checked = true")).isDisplayed());
	}
	
	public void clickByJavascript(By by) {
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(by));
	}
	
	public void checkToCheckbox(By by) {
		if(!driver.findElement(by).isSelected()){
			driver.findElement(by).click();
		}
	}
	
	public void unCheckToCheckbox(By by) {
		if(driver.findElement(by).isSelected()){
			driver.findElement(by).click();
		}
	}

	public boolean isElementSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isElementEnable(By by) {
		if (driver.findElement(by).isEnabled()) {
			return true;
		} else {
			return false;
		}
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