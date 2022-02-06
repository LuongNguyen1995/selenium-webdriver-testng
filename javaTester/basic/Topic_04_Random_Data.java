package basic;

import java.util.Random;

public class Topic_04_Random_Data {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		
		System.out.println(rand.nextInt(9999));
		
		//0-999 = 1000 số
		// 1-1000 = 1000 số
		System.out.println(rand.nextInt(999));
		System.out.println(rand.nextDouble());
		System.out.println(rand.nextFloat());
		System.out.println(rand.nextLong());
		
		System.out.println("luong.epu.dtvt"+rand.nextInt(99)+"@gmail.com");
	}

}
