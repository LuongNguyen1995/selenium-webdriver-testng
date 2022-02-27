package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_01_Annotation {
	@Test (groups = "admin")
	public void TC_01() {
		System.out.println("Run TC 01");
	}
	@Test
	public void TC_02() {
		System.out.println("Run TC 02");
	}
	@Test
	public void TC_03() {
		System.out.println("Run TC 03");
	}
  @Test
  public void TC_04() {
	  System.out.println("Run TC 04");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Run Before Method");

  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("Run After Method");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("Run Before Class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Run After Class");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Run Before Test");
  
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Run After Test");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("Run Before Suit");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("Run After Suit");
  }

}
