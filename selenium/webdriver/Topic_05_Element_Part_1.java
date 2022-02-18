package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
 
	@Test
	public void TC_01() {
		
		//Nếu element chỉ dùng 1 lần thì không cần khai báo biến, 2 lần thì khai báo để tái sử dụng
		//Các hàm(function/method)/câu lệnh(command):
		//Tương tác với Browser
		//driver.
		
		//Tương tác với element
		driver.findElement(By.name("login")).click();//**
		
		//Khai báo biến
		
		WebElement element =driver.findElement(By.cssSelector("#email"));
		
		//Xóa dữ liệu trong textbox/textarea/editable dropdown(cho phép edit/cho phép nhập dữ liệu)
		element.clear(); //*
		
		//Nhập dữ liệu trong textbox/textarea/editable dropdown(cho phép edit/cho phép nhập dữ liệu)
		element.sendKeys("automationtesting@gmail.com"); //**
		
		//Click vào button/radio button/ checkbox/ link/ image....
		element.click(); //**
		
		driver.getCurrentUrl();
		driver.getPageSource();
		driver.getTitle();
		driver.getWindowHandle();
		driver.getWindowHandles();
		
		
		
		driver.manage().window().getPosition();
		driver.manage().window().getSize();
		
		// lấy ra giá trị Attribute 
		element.getAttribute("placeholder"); //*
		
		//lấy ra text của element
		element.getText(); //**

		//thường dùng để test GUI : font/color/ size/ location/position
		//Đều lấy ra các thuộc tính của CSS được
		element.getCssValue("");//*
		
		
		element.getLocation();
		element.getRect();
		Dimension emailSize = element.getSize();

		//Chụp hình của element lưu lại bằng format nào đó
		element.getScreenshotAs(OutputType.BASE64);//*
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.FILE);
		
		//lấy ra tên thẻ html của element
		element = driver.findElement(By.xpath("//input[@id='email']"));
		element = driver.findElement(By.cssSelector("input[id='email']"));
		String elementTagname = element.getTagName();
		
		//Kiểm tra 1 element có hiển thị hay không - tất cả các loại element
		//người dùng có thể nhìn thấy được
		
		//Mong đợi nó hiển thị
		Assert.assertTrue(element.isDisplayed());//**
		
		//Mong đợi nó không hiển thị
		Assert.assertFalse(element.isDisplayed());

		element.isDisplayed();
		
		
		//Kiểm tra 1 element có thể thao tác được hay không
		//Read-only hoặc disable element
		element.isEnabled();
		
		//Mong đợi nó thao tác được (enable)
		Assert.assertTrue(element.isEnabled());//*
				
		//Mong đợi nó bị disable/read-only/ không thao tác được
		Assert.assertFalse(element.isEnabled());
		
		//Kiểm tra 1 element đã được chọn hay chưa
		//chỉ dùng cho Radio button/check box/drop down
		element.isSelected();
		
		//Mong đợi nó chọn rồi
		Assert.assertTrue(element.isSelected());//*
				
		//Mong đợi nó chưa được chọn/bỏ chọn
		Assert.assertFalse(element.isSelected());		
		
		//tương ứng với hành động nhấn phím Enter,
		//chỉ dùng với element là form hoặc nằm trong form
		element.submit();
		
		
		
		element.getText();
		
		
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Chạy được nhưng không phổ biến
		driver.findElement(By.xpath("//div[@class='footer']")).findElement(By.xpath("a[@title='My Account']")).click();
		
		element.getCssValue("background-color");
		//driver.findElement(By.cssSelector("#email")).clear();
		//driver.findElement(By.cssSelector("#email")).sendKeys("automationtesting@gmail.com");
		
		//Khai báo biến
		String homePagUrl = driver.getCurrentUrl();
		
		
		
		
		}

	@Test
	public void TC_02() {
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