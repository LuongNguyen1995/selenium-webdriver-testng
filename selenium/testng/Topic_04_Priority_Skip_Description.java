package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_04_Priority_Skip_Description {
	
	@Test(priority = 1)
	public void TC_01() {
		System.out.println("TC01");
	}
	
	@Test(priority = 2)
	public void TC_02() {
		System.out.println("TC02");

	}
	
	@Test(priority = 2)
	public void TC_03() {
		System.out.println("TC03");

	}
	
	@Test(priority = 5)
	public void TC_04() {
		System.out.println("TC04");

	}

	@Test(priority = 3)
	public void TC_05() {
		System.out.println("TC05");

	}
}
