package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {

	@Test
	public void TC_01_Assert() {
		
		//Tham số nhận vào của assert true/false là boolean
		//Assert True : Mong đợi 1 điều kiện trả về là đúng
		//Assert False : Mong đợi 1 điều kiện trả về là sai
		
		//Hàm có kiểu dữ liệu trả về là boolean
		//Selenium : isDisplayed / isSelected / isEnabled / isMultiple (Builtin)
		//Custom : Hàm tự viết có kiểu trả về là boolean
		boolean status = 3>5;
		System.out.println("status : " + status);
		//Assert.assertTrue(status,"3 less than 5"); //ít dùng
		Assert.assertTrue(status);

		Assert.assertFalse(status);
		
		//Assert Equal : Mong đợi 2 điều kiện như nhau
		//Các kiểu dữ liệu còn lại : Number / String / List / Collection (List  / Set / ..)/ Array
		//Selenium : getText / getTagname / getAttribute / getUrl / getTitle / size() / ..  (Builtin)
		
	}
}
