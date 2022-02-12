package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_Textarea_Part_1 {
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
	public void TC_01_Add_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		//Textbox
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		
		//Button
		driver.findElement(By.id("btnLogin")).click();
		
		sleepInSecond(5);
		
		//At Dashboard page : 'Add Employee' sub-menu is not display
		Assert.assertFalse(driver.findElement(By.cssSelector("a#menu_pim_addEmployee")).isDisplayed());
		
		//Open 'Add Employee' Page
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		
		//At Add Employee page : 'Add Employee' sub-menu link is display
		Assert.assertTrue(driver.findElement(By.cssSelector("a#menu_pim_addEmployee")).isDisplayed());

		
		String firstName = "Luon";
		String lastName = "VuiTuoi";
		String editFirstName = "Steven";
		String editLastName = "Gerrard";			
		
		//Enter to FirstName/LastName
		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		
		String employeeID = driver.findElement(By.id("employeeId")).getAttribute("value");
		//employeeID = 0315
		
		//Click Save
		driver.findElement(By .id("btnSave")).click();
		sleepInSecond(3);
	
		
		By firstNameTextboxBy = By.id("personal_txtEmpFirstName");
		By lastNameTextboxBy = By.id("personal_txtEmpLastName");
		By employeeIDTextboxBy = By.id("personal_txtEmployeeId");
		
		
		
		//Verify 'FirstName/LastName/EmployeeID' textbox are disable
		Assert.assertFalse(driver.findElement(firstNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(lastNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(employeeIDTextboxBy).isEnabled());
		
		//Verify 'FirstName/LastName/EmployeeID' value matching with input value
		Assert.assertEquals(driver.findElement(firstNameTextboxBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameTextboxBy).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(employeeIDTextboxBy).getAttribute("value"), employeeID);
		
		//Click Edit Button
		driver.findElement(By.xpath("//form[@id='frmEmpPersonalDetails']//input[@id='btnSave']")).click();
		
		
		//Verify 'FirstName/LastName/EmployeeID' textbox are enable
		Assert.assertTrue(driver.findElement(firstNameTextboxBy).isEnabled());
		Assert.assertTrue(driver.findElement(lastNameTextboxBy).isEnabled());
		Assert.assertTrue(driver.findElement(employeeIDTextboxBy).isEnabled());
		
		//Edit FirstName/LastName
		driver.findElement(firstNameTextboxBy).clear();
		driver.findElement(firstNameTextboxBy).sendKeys(editFirstName);
		
		driver.findElement(lastNameTextboxBy).clear();
		driver.findElement(lastNameTextboxBy).sendKeys(editLastName);
		
		//Click Save Button
		driver.findElement(By.xpath("//form[@id='frmEmpPersonalDetails']//input[@id='btnSave']")).click();
		sleepInSecond(3);
		
		//Verify 'FirstName/LastName/EmployeeID' textbox are disable
		Assert.assertFalse(driver.findElement(firstNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(lastNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(employeeIDTextboxBy).isEnabled());	
	
		//Verify 'FirstName/LastName' value matching with input value
		Assert.assertEquals(driver.findElement(firstNameTextboxBy).getAttribute("value"), editFirstName);
		Assert.assertEquals(driver.findElement(lastNameTextboxBy).getAttribute("value"), editLastName);
		
		
		//Click to Immigration
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		
		//Click to Add
		driver.findElement(By.id("btnAdd")).click();
		
		//Enter to 'Immigration' number textbox
		driver.findElement(By.id("immigration_number")).sendKeys("31195855");
		
		//Enter to Comment
		driver.findElement(By.id("immigration_comments")).sendKeys("Steven's\nPassport\nID");
		sleepInSecond(5);
		//Save
		driver.findElement(By.id("btnSave")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//td[@class='document']/a[text()='Passport']")).click();
		
		//Verify
		Assert.assertEquals(driver.findElement(By.id("immigration_number")).getAttribute("value"), "31195855");
		Assert.assertEquals(driver.findElement(By.id("immigration_comments")).getAttribute("value"), "Steven's\nPassport\nID");
		
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