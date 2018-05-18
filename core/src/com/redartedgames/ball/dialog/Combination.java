package com.redartedgames.ball.dialog;

import java.util.ArrayList;
import java.util.Comparator;

public class Combination {

	public ArrayList<Integer> combination;
	public String text;
	
	public Combination(String comb, String text) {
		combination = new ArrayList<>();
		this.text = text;
		String[] values;
		values = comb.split(" ");
		for(int i = 0; i < values.length; i++) {
			combination.add(Integer.parseInt(values[i]));
		}
		
		combination.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer arg0, Integer arg1) {
				if (arg0 > arg1) {
					return 0;
				}
				else {
					return 1;
				}
				
			}
		});
		
	}
	
	public boolean compare(ArrayList<Integer> combination) {
		if(combination.size() == this.combination.size()) {
			for(int i = 0; i < combination.size(); i++) {
				if (!(combination.get(i) == this.combination.get(i))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
