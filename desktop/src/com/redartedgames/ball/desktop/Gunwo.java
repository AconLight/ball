package com.redartedgames.ball.desktop;

import java.math.BigDecimal;

public class Gunwo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BigDecimal num1 = new BigDecimal("0.1");
		BigDecimal num2 = new BigDecimal("10");
		BigDecimal num3 = new BigDecimal("30");
		BigDecimal num4 = new BigDecimal("40");
		
		BigDecimal num2b = new BigDecimal("10.000000000");
		BigDecimal num3b = new BigDecimal("30.000000000");
		BigDecimal num4b = new BigDecimal("40.000000000");

		for(int i = 0; i < 1000; i++) {
			num3 = num3.add(num2.multiply(num1));
			num4 = num4.add(num3.multiply(num1));
			//System.out.println(num4);
		}
		System.out.println(num4);
		num4 = Hitbox.sqrt(num4, 5);
		System.out.println(num4);
		System.out.println(num4.multiply(num4));
		
		
		
		
		
		num1 = BigDecimal.valueOf(-0.1);
		for(int i = 0; i < 1000; i++) {
			num4 = num4.add(num3.multiply(num1));
			num3 = num3.add(num2.multiply(num1));
			
			//System.out.println(num4);
		}
		
		num4 = num4.add(num4b);
		num4b = num3.multiply(num1);
		num3 = num3.add(num3b);
		num3b = num2.multiply(num1);
		
		
	}

}
