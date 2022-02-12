 package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

public class Topic_08_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	Select select;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Actions action;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Khởi tạo sau khi driver firefox được sinh ra
		//JavascriptExecutor/WebDriverWait/Actions/...
		jsExecutor = (JavascriptExecutor) driver; //ép kiểu(tường minh) cho driver sang kiểu JavascriptExecutor
		explicitWait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
	@Test
	public void TC_01_Rode() {
		driver.get("https://www.rode.com/wheretobuy");
		//Khởi tạo select khi sử dụng(Element xuất hiện)
		//Khởi tạo select để thao tác với element(country dropdown)
		select = new Select(driver.findElement(By.id("where_country")));
		
		//Afghanistan (Index)
		//select.selectByIndex(2);
		
		//Argentina (value)
		//select.selectByValue("Argentina");
		
		//Belarus (Visible Text)
		//select.selectByVisibleText("Belarus");
		
		//Không support multiple select
		Assert.assertFalse(select.isMultiple());
		
		//Select giá trị việt nam
		select.selectByVisibleText("Vietnam");
		sleepInSecond(5);
		
		//Verify Vietnam selected success
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		
		//Click button search
		driver.findElement(By.id("search_loc_submit")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result_count']/span")).getText(), "32");
		
		//Java Generic
		List<WebElement> storeName = driver.findElements(By.xpath("//div[@id='search_results']//div[@class='store_name']"));
		
		//Verify total storeName = 32
		Assert.assertEquals(storeName.size(), 32);
		
		for (WebElement store : storeName) {
			System.out.println(store.getText());
		}
	}

	@Test
	public void TC_02_NopCommerce() {
		String firstName = "John";
		String lastName = "Wick";
		String day = "1";
		String month = "May";
		String year = "1980";
		String emailAdress = "keane" + getRandomNumber()+ "@hotmail.com";
		String password = "123456";
		
		
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		//date/month/year
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(day);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		
		
		driver.findElement(By.id("Email")).sendKeys(emailAdress);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		
		//click register
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		//click My account
		driver.findElement(By.cssSelector("a.ico-account")).click();
		
		//Page HTML đã đc render lại
		//Verify
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		//date/month/year
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);		
		
		
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAdress);
		
		
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
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
		
	}
}