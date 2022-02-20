package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_JavascriptExecutor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	String userID,userPassword,loginPageUrl;
	String customerName,gender, dateOfBirth, addressInput, addressOutput, city, state, pinNumber, phoneNumber, email, password;
	String customerID;
	String editAddressInput, editAddressOutput, editCity, editState, editPin, editPhone, editEmail;
	
	
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
		
		
		//Ép kiểu tường minh - Khởi tạo
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		loginPageUrl = "https://demo.guru99.com/v4/";
		
		customerName = "Brian Tracy";
		gender = "male"; 
		dateOfBirth = "1950-01-31"; 
		addressInput = "123 Los Angeles\nUnited State"; 
		addressOutput = "123 Los Angeles United State"; 
		city = "New York"; 
		state = "California"; 
		pinNumber = "632565"; 
		phoneNumber = "0987569584"; 
		email = "briantracy" + getRandomNumber() + "@mail.net"; 
		password = "123456";
	}
 
	//@Test
	public void TC_01_Example() {
		//jsExecutor : k apply wait/ hoặc chờ element visibile/displayed
		
		//Open Url
		openUrl("http://live.techpanda.org/");
		sleepInSecond(5);
		
		//Get domain of Page - Object ép kiểu qua String, thêm return để lấy dữ liệu
		System.out.println(getPageDomain());
	
	}
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public void openUrl(String url) {
		jsExecutor.executeScript("window.location='" + url + "'");
	}
	
	public String getPageDomain() {
		return (String) jsExecutor.executeScript("return document.domain");
	}
	
	
	//@Test
	public void TC_02() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(5);
		//Click & Verify domain
		String liveGuruDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(liveGuruDomain, "live.techpanda.org");
		
		//Click & Verify Url
		String liveGuruUrl = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(liveGuruUrl, "http://live.techpanda.org/");
		
		//Click Mobile
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);
		
		//Click add to card
		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(2);
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(3);
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		//Click Customer Service
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);
		
		//Get title and compare
		String customerServiceTitle = (String) executeForBrowser("return document.title");
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		
		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnTop("//input[@id='newsletter']");
		sleepInSecond(3);
		
		//Input valid email
		sendkeyToElementByJS("//input[@id='newsletter']", "jsExecutor" + getRandomNumber()+"@hotmai.com");
		//Click Subcriber
		hightlightElement("//button[@title='Subscribe']");
		sleepInSecond(3);
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(3);
		
		//Verify subcriber successful
		String subcribeMessage = getInnerText();
		Assert.assertTrue(subcribeMessage.contains("Thank you for your subscription."));
		//Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
		
		//Navigate to other domain
		navigateToUrlByJS("https://demo.guru99.com/v4/");
		sleepInSecond(5);
		//Verify domain
		//Note : AssertEquals tự ép kiểu : So sánh Object với String
		Assert.assertEquals(executeForBrowser("return document.domain"), "demo.guru99.com");
	}


	//@Test
	public void TC_03() {
		driver.get("https://login.ubuntu.com/");
		sleepInSecond(3);
		String emailTextboxXpath = "//input[@id='id_email']";
		WebElement loginButton = driver.findElement(By.xpath("//button[@data-qa-id='login_button']"));
		
		loginButton.click();
		sleepInSecond(3);
		//Verify message
		Assert.assertEquals(getElementValidationMessage(emailTextboxXpath), "Please fill out this field.");
		
		//Send key to email textbox
		driver.findElement(By.xpath(emailTextboxXpath)).sendKeys("123@123@123");
		loginButton.click();
		sleepInSecond(3);
		//Verify message
		if (driver.toString().contains("chrome")) {
			Assert.assertEquals(getElementValidationMessage(emailTextboxXpath), "A part following '@' should not contain the symbol '@'.");
		}else if (driver.toString().contains("firefox")) {
			Assert.assertEquals(getElementValidationMessage(emailTextboxXpath), "Please enter an email address.");
		}
		
		
	}

	//@Test
	public void TC_04_Remove_Attribute() {
		driver.get(loginPageUrl);
		driver.findElement(By.xpath("//a[text()='here']")).click();
		
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		userPassword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(userPassword);
		driver.findElement(By.name("btnLogin")).click();
		
		//Verify xem đã login thành công hay chưa
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@behavior='alternate']")).getText(), "Welcome To Manager's Page of Guru99 Bank");
		Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText(), "Manger Id : " + userID);
		
		//Add new customer
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(By.name("name")).sendKeys(customerName);
		
		//Remove Attribute type = jsExecutor
		removeAttributeInDOM("//input[@name='dob']", "type");
		sleepInSecond(5);
		driver.findElement(By.name("dob")).sendKeys(dateOfBirth);
		
		driver.findElement(By.name("addr")).sendKeys(addressInput);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("pinno")).sendKeys(pinNumber);
		driver.findElement(By.name("telephoneno")).sendKeys(phoneNumber);
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		
		//submit
		driver.findElement(By.name("sub")).click();
		
		
		//Verify
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pinNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phoneNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		
		
	}
	
	//@Test
	public void TC_05_Validation_Message() {
		driver.get("https://automationfc.github.io/html5/index.html");
		
		String name = "//input[@id='fname']";
		String pass = "//input[@id='pass']";
		String email = "//input[@id='em']";
		String address = "//select";
		WebElement submit = driver.findElement(By.xpath("//input[@type='submit']"));
		
		//Click Submit to Verify Name Textbox
		submit.click();
		sleepInSecond(1);
		Assert.assertEquals(getElementValidationMessage(name), "Please fill out this field.");
		
		//Click submit to verify pass textbox
		driver.findElement(By.xpath(name)).sendKeys("Luongdeptrai");
		submit.click();
		sleepInSecond(1);
		Assert.assertEquals(getElementValidationMessage(pass), "Please fill out this field.");

		//Click submit to verify email textbox
		driver.findElement(By.xpath(name)).sendKeys("Luongdeptrai");
		driver.findElement(By.xpath(pass)).sendKeys("Luongdeptrai");
		submit.click();
		sleepInSecond(1);
		Assert.assertEquals(getElementValidationMessage(email), "Please fill out this field.");
		
		//Click submit to verify invalid email textbox 1
		driver.findElement(By.xpath(name)).sendKeys("Luongdeptrai");
		driver.findElement(By.xpath(pass)).sendKeys("Luongdeptrai");
		driver.findElement(By.xpath(email)).sendKeys("123@123@123");
		submit.click();
		sleepInSecond(1);
		Assert.assertEquals(getElementValidationMessage(email), "Please enter an email address.");
		
		//Click submit to verify invalid email textbox 2
		driver.findElement(By.xpath(name)).sendKeys("Luongdeptrai");
		driver.findElement(By.xpath(pass)).sendKeys("Luongdeptrai");
		driver.findElement(By.xpath(email)).clear();
		driver.findElement(By.xpath(email)).sendKeys("123@123");
		submit.click();
		sleepInSecond(1);
		Assert.assertEquals(getElementValidationMessage(email), "Please match the requested format.");
		
		//Click submit to verify address textbox
		driver.findElement(By.xpath(name)).sendKeys("Luongdeptrai");
		driver.findElement(By.xpath(pass)).sendKeys("Luongdeptrai");
		driver.findElement(By.xpath(email)).clear();
		driver.findElement(By.xpath(email)).sendKeys("luong123@gmail.com");
		submit.click();
		sleepInSecond(1);
		Assert.assertEquals(getElementValidationMessage(address), "Please select an item in the list.");
		
	}
	
	//@Test
	public void TC_06_Create_An_Account() {
		openUrl("http://live.techpanda.org/");
		sleepInSecond(3);
		
		//Click my account hidden
		hightlightElement("//div[@id='header-account']//a[@title='My Account']");
		clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");
		sleepInSecond(2);
		
		//Click create an account
		hightlightElement("//a[@title='Create an Account']");
		clickToElementByJS("//a[@title='Create an Account']");
		sleepInSecond(3);
		
		//Input valid data
		sendkeyToElementByJS("//input[@id='firstname']", "Luong");
		sendkeyToElementByJS("//input[@id='lastname']", "Nguyen");
		sendkeyToElementByJS("//input[@id='email_address']", "NguyenLuong"+getRandomNumber()+"@hotmail.com");
		sendkeyToElementByJS("//input[@id='password']", "NguyenLuong123");
		sendkeyToElementByJS("//input[@id='confirmation']", "NguyenLuong123");
		sleepInSecond(5);
		
		//Click register
		hightlightElement("//button[@title='Register']");
		clickToElementByJS("//button[@title='Register']");
		sleepInSecond(5);
		
		//Verify message register success
		areExpectedTextInInnerText("Thank you for registering with Main Website Store.");
		
		//Click logout
		hightlightElement("//div[@id='header-account']//a[@title='Log Out']");
		clickToElementByJS("//div[@id='header-account']//a[@title='Log Out']");
		sleepInSecond(2);
		
		//Verify to homepage
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
	}
	
	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
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