package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_03_Group {
	
	@Test(groups = "user")
	public void TC_01() {
		System.out.println("TC01");
	}
	
	@Test(groups = "user")
	public void TC_02() {
		System.out.println("TC02");

	}
	
	@Test(groups = {"user","admin"})
	public void TC_03() {
		System.out.println("TC03");

	}
	
	@Test(groups = {"user","super"})
	public void TC_04() {
		System.out.println("TC04");

	}

	@Test(groups = {"super"})
	public void TC_05() {
		System.out.println("TC05");

	}
}
