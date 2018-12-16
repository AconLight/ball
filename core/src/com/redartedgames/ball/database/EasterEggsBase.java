package com.redartedgames.ball.database;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.redartedgames.ball.myobjects.EasterEgg;

public class EasterEggsBase {
	
	public static Preferences prefs = Gdx.app.getPreferences("eastereggs");
	
	public static ArrayList<EasterEgg> easterEggs;
	
	private static int recordNumber = 7;
	
	public static void load(int a, int b) {
		easterEggs = new ArrayList<>();
		boolean temp;
		for(int i = 0; i < recordNumber; i++) {
			//Gdx.app.log("EasterEggsBase", "iteration");
			if(i >= a && i <= b)
			if(prefs.contains("" + (i+1) + " isTrue")) {
				//Gdx.app.log("EasterEggsBase", "contains");
				if (prefs.getString("" + (i+1) + " isTrue") == "true") {
					temp = true;
				}
				else {
					temp = false;
				}
				
				easterEggs.add(new EasterEgg(i+1, prefs.getString("" + (i+1) + " name"), temp));
			}
			
		}
	}
	
	public static void loadAdd(int id) {
		boolean temp;
		if(prefs.contains("" + (id+1) + " isTrue")) {
			//Gdx.app.log("EasterEggsBase", "contains");
			if (prefs.getString("" + (id+1) + " isTrue") == "true") {
				temp = true;
			}
			else {
				temp = false;
			}
			
			easterEggs.add(new EasterEgg(id+1, prefs.getString("" + (id+1) + " name"), temp));
		}
	}
	
	//TODO
	public static void update(EasterEgg easterEgg) {
		
	}
	
	public static void enableEgg(int id) {
		prefs.putString(id +" isTrue", "true");
		prefs.flush();
	}
	
	public static void tryFirstLoad() {
		prefs.clear();
		prefs.flush();
		if (!prefs.contains("firstLoad")) {
			//Gdx.app.log("EasterEggsBase", "tryFirstLoad");
			prefs.putString("firstLoad", "true");
			prefs.putString("1 isTrue", "false");
			prefs.putString("1 name", "butelka");
			prefs.putString("2 isTrue", "false");
			prefs.putString("2 name", "sznur");
			prefs.putString("3 isTrue", "false");
			prefs.putString("3 name", "szafa");
			prefs.putString("4 isTrue", "false");
			prefs.putString("4 name", "gra");
			prefs.putString("5 isTrue", "false");
			prefs.putString("5 name", "czas");
			prefs.putString("6 isTrue", "false");
			prefs.putString("6 name", "gracz");
			
			prefs.flush();
		}
		
	}
}
