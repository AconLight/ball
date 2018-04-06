package com.redartedgames.ball.database;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.redartedgames.ball.myobjects.EasterEgg;

public class EasterEggsBase {
	
	public static Preferences prefs = Gdx.app.getPreferences("eastereggs");
	
	public static ArrayList<EasterEgg> easterEggs;
	
	private static int recordNumber = 3;
	
	public static void load() {
		easterEggs = new ArrayList<>();
		boolean temp;
		for(int i = 0; i < recordNumber; i++) {
			Gdx.app.log("EasterEggsBase", "iteration");
			if(prefs.contains("" + (i+1) + " isTrue")) {
				Gdx.app.log("EasterEggsBase", "contains");
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
	
	//TODO
	public static void update(EasterEgg easterEgg) {
		
	}
	
	public static void tryFirstLoad() {
		prefs.clear();
		if (!prefs.contains("firstLoad")) {
			Gdx.app.log("EasterEggsBase", "tryFirstLoad");
			prefs.putString("firstLoad", "true");
			prefs.putString("1 isTrue", "true");
			prefs.putString("1 name", "wazonik");
			prefs.putString("2 isTrue", "false");
			prefs.putString("2 name", "okno");
			prefs.putString("3 isTrue", "true");
			prefs.putString("3 name", "lustro");
			prefs.flush();
		}
		
	}
}
