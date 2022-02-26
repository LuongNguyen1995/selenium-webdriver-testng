package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_26_Wait_Part_7_FluentWait {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	FluentWait<WebDriver> fluentWaitDriver;
	FluentWait<WebElement> fluentWaitElement;
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
 
	//@Test
	public void TC_01_C1() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countDownTime = driver.findElement(By.id("javascript_countdown_time"));
		
		fluentWaitElement = new FluentWait<WebElement>(countDownTime);
		
		//Wait với tổng thời gian là 15s
		fluentWaitElement.withTimeout(Duration.ofSeconds(15))
		//Cơ chế tìm lại nếu chưa thỏa mãn điều kiện là 0.5s tìm lại 1 lần
		.pollingEvery(Duration.ofMillis(500))
		//Nếu như trong thời gian tìm lại mà không thấy element, Import NoSuchElementException của Selenium (k dùng java.until)
		.ignoring(NoSuchElementException.class)
		//Xử lí điều kiện :  Dùng hàm com.google.common.page
				.until(new Function<WebElement, Boolean>() {

					@Override //Điều kiện
					public Boolean apply(WebElement element) {
						String text = element.getText();
						System.out.println("Time =  " + text);
						return text.endsWith("00");
					}
				});
		
		
	}
	
	//@Test
	public void TC_02_C2() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		fluentWaitDriver = new FluentWait<WebDriver>(driver);
		
		fluentWaitDriver.withTimeout(Duration.ofSeconds(6))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class);
		WebElement helloworldText = fluentWaitDriver.until(new Function<WebDriver, WebElement>() {
			
			@Override
			public WebElement apply(WebDriver driver) {
				
				return driver.findElement(By.cssSelector("div#finish>h4"));
			}
		});
		Assert.assertEquals(helloworldText.getText(), "Hello World!");
	}
	
	//@Test
	public void TC_03() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		fluentWaitDriver = new FluentWait<WebDriver>(driver);
		
		fluentWaitDriver.withTimeout(Duration.ofSeconds(6))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class)
		.until(new Function<WebDriver, Boolean>() {
			
			@Override
			public Boolean apply(WebDriver driver) {
				
				return driver.findElement(By.cssSelector("div#finish>h4")).getText().equals("Hello World!");
			}
		});
		
	}

	//@Test
	public void TC_04() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		waitForElementAndClick(By.cssSelector("div#start>button"));
		
		//Assert.assertEquals(getWebElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");	
		
		Assert.assertTrue(waitForElementAndDisplayed(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
	}

	@Test
	public void TC_05() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		getElement("//input[@id='txtUsername']").sendKeys("Admin");
		getElement("//input[@id='txtPassword']").sendKeys("admin123");
		getElement("//input[@id='btnLogin']").click();
		
		Assert.assertTrue(isJQueryLoadedSuccess(driver));
		
		
	}
	
	
	public WebElement getWebElement (By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(locator);
			}
		});
		return element;
	}

	public void waitForElementAndClick(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
	
		});
		element.click();
	}
	
	//Find Element (Custom with polling time = 1s)
	public boolean waitForElementAndDisplayed(By locator) {
		WebElement element = getWebElement(locator);
				return element.isDisplayed();
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}


	public boolean isJQueryLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 20);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				
				return (Boolean)jsExecutor.executeScript("return (window.jQuery!=null) && (jQuery.active ===0);");
			}
			
		};
		return explicitWait.until(jQueryLoad);
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