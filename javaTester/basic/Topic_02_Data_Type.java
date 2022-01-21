package basic;

import java.util.ArrayList;
import java.util.List;

public class Topic_02_Data_Type {

	public static void main(String[] args) {
		// 2 loại kiểu dữ liệu
		
		// kiểu nguyên thủy (primitive Type)
		// 8 loại
		//char - kí tự - 16 bit
		char c = 'A';
		System.out.println(c);
		//byte - số nguyên - 8
		byte bNumber = 15;
		System.out.println(bNumber);
		//short - số nguyên - 16
		short number = -30000;
		System.out.println(number);
		//int  - số nguyên  -32
		 
		//float - số thực - 32 bit
		float fNumber = 35.5f;
		System.out.println(fNumber);
		//double - số thực - 64 bit
		double dNumber = 23.5d;
		System.out.println(dNumber);
		//boolean - logic - 1 (true/false)
		boolean mariedStatus = true;
		System.out.println(mariedStatus);
		
		
		//kiểu tham chiếu (reference type)
		//object
		Object o= new Object();
		
		//array
		String[] address = {"Ha Noi", "Sai Gon"};
		Integer phone[] = {0123312,030123};
		
		//class
		Topic_02_Data_Type topic = new Topic_02_Data_Type();
		
		
		//collection : List/ set/queue
		
		List<String> addresses = new ArrayList<String>();
		
		//string - Chuỗi kí tự
		String name = "Automation bdfsbdf jk 1213";
		System.out.println(name);
		String cityName = new String("Ha Noi");

	}

}
