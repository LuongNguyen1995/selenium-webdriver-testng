package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Browser_Part_1 {
	WebDriver driver;
	WebElement element;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
 
	@Test
	public void TC_01_Browser() {
		
		//Phần nào hay sử dụng thì đánh dấu //** , ít sử dụng thì đánh dấu //*
		
		//Các hàm/method để thao tác vs Browser là thông qua biến driver
		//Mở ra 1 Url
		driver.get("https://www.w3schools.com/"); //**
		
		driver.get("https://www.facebook.com/");
		
		
		//Đóng browser
		driver.quit(); //đóng browser //**
		
		driver.close(); //đóng browser nếu chỉ 1 tab, đóng tab đang mở nếu có nhiều tab//bài học xử lí window,tabs //*
		
		// Tìm 1 element trên page
		// trả vè data type là WebElement
		WebElement emailTextbox = driver.findElement(By.id("email")); //**
		
		//TÌm nhiều hơn 1 element trên page
		//Trả về data type là List <WebElement>
		driver.findElements(By.xpath("//a")); //**
		
		//Trả về Url của page hiện tại
		String homePageUrl = driver.getCurrentUrl(); //*
		System.out.println(homePageUrl);
		
		//Verify dữ liệu này đúng như mong đợi, verify tuyệt đối
		Assert.assertEquals(homePageUrl, "https://www.w3schools.com/");
		
		//lấy ra source code của trang hiện tại (HTML/CSS/JS/JQuery/..)
		//Verify tương đối 1 giá trị nào đó có trong trang
		String homePageSource = driver.getPageSource();
		Assert.assertTrue(homePageSource.contains("Learn to Code"));
		
		//Lấy ra/trả về title cua Page hiện tại
		String homePageTitle = driver.getTitle(); //*
		Assert.assertEquals(homePageTitle, "W3Schools Online Web Tutorials");
		
		//WebDriver API - Windows/Tabs
		//Trả về 1 ID của Tab hiện tại
		String signUpTabID = driver.getWindowHandle(); //*
		
		//Trả về ID của tất cả các tab đang có (n)
		Set<String> allTabID = driver.getWindowHandles(); //*
		
		//Xử lí Cookie (Framework)
		driver.manage().getCookies(); //*
		
		//Lấy log của browser ra
		//driver.manage().logs()
		
		//Time của việc findElement
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //**
		
		//Time page được load xong, ít dùng
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		//Time để cho 1 đoạn async Script được thực thi thành công (Javascript Excutor), ít dùng
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		
		//Fullscreen Browser (F11)
		driver.manage().window().fullscreen();
		
		//End User hay dùng maximize, mở rộng web
		driver.manage().window().maximize(); //*
		
		//Test GUI
		
		//Lấy ra vị trí của browser so với độ phân giải màn hình
		Point browserPosition = driver.manage().window().getPosition();
		
		//Set vị trí của browser tại điểm 0 x 250
		driver.manage().window().setPosition(new Point(0, 250));
		
		//Lấy ra chiều rộng/chiều cao của browser
		Dimension browserSize = driver.manage().window().getSize();
		
		//Set browser mở với kích thước nào
		//Test responsive
		driver.manage().window().setSize(new Dimension(1366, 768));
		driver.manage().window().setSize(new Dimension(1920, 1080));
		
		
		//Quay lại trang trước đó
		driver.navigate().back();
		
		//Chuyển tiếp tới trang trước đó
		driver.navigate().forward();
		
		//Tải lại trang
		driver.navigate().refresh();
		
		
		driver.navigate().to("https://www.w3schools.com/");
		
		//WebDriver API - Alert / Authentication Alert
		driver.switchTo().alert(); //**
		
		//WebDriver API - Frame/Iframe
		driver.switchTo().frame(1); //**
		
		//WebDriver API - Windows/Tabs
		driver.switchTo().window(""); //**
		
		
		
		
		
		
		
		
		
		
		
		
		
		//không nên lưu thành 1 biến - tương tác trực tiếp luôn (khi dùng duy nhất 1 lần)
		List<WebElement> links = driver.findElements(By.id("email"));
		
		// Nên lưu thành 1 biến - tương tác sử dụng lại nhiều lần;
		//WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.clear();
		emailTextbox.sendKeys("");
		emailTextbox.getAttribute("value");
		
	}

	@Test
	public void TC_02_Element() {
		//Các hàm/method để thao tác vs element là thông qua biến driver
		
		
		
		
	}

	@Test
	public void TC_03() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecon (long second) {
		try {
			Thread.sleep( second * 1000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
}