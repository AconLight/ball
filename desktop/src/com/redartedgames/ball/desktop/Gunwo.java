package com.redartedgames.ball.desktop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Gunwo {

	public static void main(String[] args) {
		
		BigDecimal a = new BigDecimal("123");
		BigDecimal b = a;
		
		
		
		a = new BigDecimal("3456");
		
		System.out.println(a);
		System.out.println(b);
		
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
	


