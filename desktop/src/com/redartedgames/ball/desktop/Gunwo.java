package com.redartedgames.ball.desktop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Gunwo {

	public static void main(String[] args) {
		
		BigDecimal a = new BigDecimal("123");
		BigDecimal b = new BigDecimal("123");
		float aa = a.floatValue();
		float bb = b.floatValue();
		
		
		
		
		//System.out.println(a);
		//System.out.println(b);
		
		float c = 0,d = 0;
		
		for (int i = 0; i < 10000; i++) {
			aa = a.floatValue();
			bb = b.floatValue();
			
			c = (aa+bb*2)/(2*aa+bb);
			d = (2*aa+bb)/(aa+2*bb);
			
			a = a.add(new BigDecimal("" + c));
			
			b = b.add(new BigDecimal("" + d));
			
		}
		
		float cc = c;
		float dd = d;
		
		for (int i = 0; i < 10000; i++) {
			
			
			a = a.subtract(new BigDecimal("" + cc));
			
			b = b.subtract(new BigDecimal("" + dd));
			
			
			
			
			cc = (aa+bb*2)/(2*aa+bb);
			dd = (2*aa+bb)/(aa+2*bb);
			
			aa = a.floatValue();
			bb = b.floatValue();
			
		}
		
		System.out.println(aa);
		System.out.println(bb);
		System.out.println("asd");
		
		
		
		ArrayList<Person> persons = new ArrayList<Person>();
		persons.add(new Person(4));
		persons.add(new Person(5));
		persons.add(new Person(6));
		persons.add(new Person(7));
		
		print(persons, new checkablePerson() {
			@Override
			public boolean check(Person p) {
				return (p.x > 6);
			}
		});
		
		print(
				persons,
				(Person p) -> p.x > 4
				);
		
	}
	
	static void print(ArrayList<Person> r, checkablePerson c) {
		for (Person p : r) {
			if (c.check(p)) {
				System.out.println(p.x);
			}
		}
	}
}
	
	class Person {
		int x;
		public Person(int x) {
			this.x = x;
		}

	}
	
	interface checkablePerson {
		public boolean check(Person p);
	}
	


