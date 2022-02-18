package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Fixed_Popup {
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
		
		//Trạng thái của element
		explicitWait = new WebDriverWait(driver,30);
		//Để tìm element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
	//@Test
	public void TC_01_Fix_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		
		//Nên define thành 1 biến By
		By modalLoginPopupBy = By.xpath("//div[@id='modal-login-v1']");
		
		//Không nên define thành 1 biến WebElement
		//Vì tại thời điểm tìm element thì nó đang mang trạng thái lúc tìm là không hiển thị
		
		//Kiểm tra k hiển thị 
		Assert.assertFalse(driver.findElement(modalLoginPopupBy).isDisplayed());
		
		//Click vào đăng nhập
		driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
		sleepInSecond(3);
		
		
		//Kiểm tra hiển thị
		Assert.assertTrue(driver.findElement(modalLoginPopupBy).isDisplayed());

		//Tìm lại element để cập nhật lại status đó (Displayed/Undisplayed/Presence/Staleness)
		
		
		//Nhập thông tin mk & pw
		driver.findElement(By.name("account-input")).sendKeys("automationfc");
		driver.findElement(By.name("password-input")).sendKeys("automationfc");
		driver.findElement(By.xpath("//button[@onclick='onLoginUser(this)']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//button[@onclick='onLoginUser(this)']/preceding-sibling::div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");
		
		//Close popup
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		sleepInSecond(2);
		//Kiểm tra k hiển thị 
		Assert.assertFalse(driver.findElement(modalLoginPopupBy).isDisplayed());
	}

	//@Test
	public void TC_02_Fixed_Popup() {
		driver.get("https://bizbooks.vn/");

		By loginPopup = By.cssSelector("div#md-signin");
		By registerPopup = By.cssSelector("div#md-signup");
		
		//Cả 2 đều không hiển thị
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		Assert.assertFalse(driver.findElement(registerPopup).isDisplayed());
		
		driver.findElement(By.xpath("//span[text()='ĐĂNG NHẬP']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//div[@class='header__elements']//a[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		
		//login popup hiển thị
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		//register popup không hiển thị
		Assert.assertFalse(driver.findElement(registerPopup).isDisplayed());
		
		driver.findElement(By.xpath("//div[@id='md-signin']//input[@name='email']")).sendKeys("automationfc@hotmail.com");
		driver.findElement(By.xpath("//div[@id='md-signin']//input[@name='password']")).sendKeys("123123");
		driver.findElement(By.xpath("//div[@id='md-signin']//button[@class='btn btn-secondary btn-block js-btn-member-login']")).click();
		
		//Verify
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='md-signin']//span[@class='text-danger']")).getText(), "Tài khoản không tồn tại");
		//Close = ESC
		driver.findElement(By.xpath("//div[@id='md-signin']//button[@class='btn btn-secondary btn-block js-btn-member-login']")).sendKeys(Keys.ESCAPE);
		sleepInSecond(2);
		
		//Cả 2 đều không hiển thị
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		Assert.assertFalse(driver.findElement(registerPopup).isDisplayed());
	}
	
	@Test
	public void TC_03_Fixed_Popup() {
		driver.get("https://jtexpress.vn/");
		
		By homePagePopup = By.cssSelector("div#homepage-popup");
		
		//Chờ cho nó hiển thị sau đó mới verify
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(homePagePopup));
		
		//Verify
		Assert.assertTrue(driver.findElement(homePagePopup).isDisplayed());
		driver.findElement(By.cssSelector("div#homepage-popup button.close")).click();
		
		//Chờ cho nó không hiển thị sau đó mới verify
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(homePagePopup));
		Assert.assertFalse(driver.findElement(homePagePopup).isDisplayed());

		driver.findElement(By.cssSelector("input#billcodes")).sendKeys("841000072647");
		driver.findElement(By.cssSelector("form#formTrack button[type='submit']")).click();
		sleepInSecond(5);
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