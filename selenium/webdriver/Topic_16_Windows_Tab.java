package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Windows_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	
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
		System.out.println("Driver ID = "+driver.toString());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
 
	//@Test
	public void TC_01_Only_Two_Window_Or_Tab() {
		//Page A : Parent
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Lấy ra ID của 1 tab/windown đang active
		String parentTabID = driver.getWindowHandle();

		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		
		//Click to Google ink
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		//Lấy ra ID của tất cả tab/window đang active
		Set<String> allTabIDs = driver.getWindowHandles();
		
		//Switch qua goole tab  thành công
		SwitchToTabByID(parentTabID);
		Assert.assertEquals(driver.getTitle(), "Google");
		
		
		String googleTabId = driver.getWindowHandle();
		//switch to parent Url
		SwitchToTabByID(googleTabId);
		sleepInSecond(2);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");

	}
	
	//Hàm này chỉ đúng với 2 tab/window
	public void SwitchToTabByID(String expectedID) {
		//Lấy hết tất cả ID của tab/window đang có
		Set<String> allTabIds = driver.getWindowHandles();
		
		//Dùng vòng lặp để duyệt qua từng ID
		for (String id : allTabIds) { //id : 1 biến tạm dùng để duyệt (so sánh)
			///id nào mà khác với expectedID thì switch qua
			if (!id.equals(expectedID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	//Viết hàm đúng cho tất cả trường hợp : 2 hoặc nhiều hơn 2 tab/window đều đúng
	public void SwitchToTabByTitle(String expectedTitle) {
		//Lấy hết tất cả ID của tab/window đang có
		Set<String> allTabIds = driver.getWindowHandles();
				
		//Dùng vòng lặp để duyệt qua từng ID
			for (String id : allTabIds) { //id : 1 biến tạm dùng để duyệt (so sánh)
				//Switch vào từng tab trước rồi kiểm tra sau
				driver.switchTo().window(id);
				
				//Lấy ra title của tab vừa switch vào
				String actualTitle = driver.getTitle();
				//Kiểm tra nếu title bằng title mong muốn
				if (actualTitle.equals(expectedTitle)) {
					//thoát khỏi vòng lặp
					break;
				}	
			}
	}
	
	public void SwitchToTabByUrl(String Url) {
		Set<String> allTabIds = driver.getWindowHandles();
		for (String id : allTabIds) {
			driver.switchTo().window(id);
			String actualUrl = driver.getCurrentUrl();
			if (actualUrl.equals(Url)) {
				break;
			}
		}
	}
	
	//@Test
	public void TC_02_Greater_Than_One_Window_Or_Tab_BT08() {
		//Page A : Parent
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Lấy ra ID của 1 tab/windown đang active
		String parentTabID = driver.getWindowHandle();

		//Click to Google ink
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		
		//Switch vào Google tab
		//Lấy title = cách vào Consolse : document.title
		SwitchToTabByTitle("Google"); 
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		
		//switch to parent Url
		SwitchToTabByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		
		//Click to Facebook link
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);
		
		//Switch vào Facebook tab
		SwitchToTabByTitle("Facebook – log in or sign up"); 
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
		
		//switch to parent Url
		SwitchToTabByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		
		//Click to Lazada link
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		sleepInSecond(2);
		
		//Switch vào Lazada tab
		SwitchToTabByTitle("Shopping online - Buy online on Lazada.vn"); 
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.lazada.vn/");
	}

	//@Test
	public void TC_03_Greater_Than_One_Window_Or_Tab_BT10() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		//Add to compare Samsung Galaxy
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		//Verify text display
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		
		//Add to compare Sony Experia
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		//Verify text display
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");
		
		//Click Compare button
		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		//Switch to new window
		SwitchToTabByTitle("Products Comparison List - Magento Commerce");
		sleepInSecond(2);
		
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
		//Verify new tab
		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "COMPARE PRODUCTS");
		//Close new tab
		driver.findElement(By.xpath("//button[@title='Close Window']")).click();
		sleepInSecond(2);
		
		//Switch to parent window
		SwitchToTabByTitle("Mobile");
		sleepInSecond(2);
		
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
		//Clear All - Fail 
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		sleepInSecond(2);
		driver.switchTo().alert().accept();
		sleepInSecond(2);
		//Verify clear
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The comparison list was cleared.");
	}
	
	//@Test
	public void TC_04_Greater_Than_One_Window_Or_Tab_BT11() {
		driver.get("https://dictionary.cambridge.org/vi/");
		//Login
		driver.findElement(By.xpath("//span[text()='Đăng nhập']")).click();
		sleepInSecond(3);
		//Switch to new window
		SwitchToTabByTitle("Login");
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
		//Verify Login fail
		Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Email *']/following-sibling::span")).getText(), "This field is required");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Password *']/following-sibling::span")).getText(), "This field is required");
		
		//input data to login
		driver.findElement(By.xpath("//input[@placeholder='Email *']")).sendKeys("luong.epu.dtvt1@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Password *']")).sendKeys("Luong1995@");
		
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		sleepInSecond(5);
		
		//Back to parent tab
		SwitchToTabByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		sleepInSecond(2);
		//Verify login successfully
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'username')]")).getText(), "Luong Nguyen");

	}
	
	@Test
	public void TC_05_Greater_Than_One_Window_Or_Tab_BT11() {
		
		//Chưa verify được trùng title Page
		driver.get("https://kyna.vn/");
		String titleParentTab = driver.getTitle();
		sleepInSecond(5);
		
		//Scroll to Bottom Page
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
		//Click Element in End Page
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='facebook']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='youtube']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[1]/img[@alt='Khóa học trực tuyến' and @class]")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[2]/img[@alt='Khóa học trực tuyến' and @class]")).click();
		sleepInSecond(2);
		
		
		//Switch each Tab to verify
		SwitchToTabByTitle("Kyna.vn - Home | Facebook");
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");
		SwitchToTabByTitle("Kyna.vn - YouTube");
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");
		//Tên công ty
		SwitchToTabByUrl("http://online.gov.vn/Home/WebDetails/61473");
		System.out.println(driver.getCurrentUrl());
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-sm-8']//p[text()='Tên Doanh nghiệp:']")).isDisplayed());
		
		//Loại hình dịch vụ
		SwitchToTabByUrl("http://online.gov.vn/Home/WebDetails/60140");
		System.out.println(driver.getCurrentUrl());
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-sm-8']//p[text()='Loại hình cung cấp dịch vụ:']")).isDisplayed());

		//Close các tab phụ
		Set <String> allTabIds = driver.getWindowHandles();
		for (String id : allTabIds) {
			//Switch qua từng page để kiểm tra
			driver.switchTo().window(id);
			String titleActualPage = driver.getTitle();
			
			//Kiểm tra nếu không đúng parent page thì close
			if (!titleActualPage.contentEquals(titleParentTab)) {
				sleepInSecond(2);
				driver.close();
			}
		}
		//Sau khi đóng các window còn lại thì quay về trang chính
		SwitchToTabByTitle(titleParentTab);
		//Kiểm tra chỉ còn đúng 1 tab chính
		Assert.assertEquals(driver.getWindowHandles().size(), 1);
	}
	
	public void clickToElementByJS(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("argument[0].click();", element);
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