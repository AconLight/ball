package com.redartedgames.ball.database;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.redartedgames.ball.dialog.Combination;
import com.redartedgames.ball.myobjects.EasterEgg;

public class ConversationsBase {

	public static Preferences prefs = Gdx.app.getPreferences("conversation");
	
	public static ArrayList<Combination> combinations;
	
	private static int recordNumber = 2;
	
	public static void load() {
		combinations = new ArrayList<>();
		boolean temp;
		for(int i = 0; i < recordNumber; i++) {
			Gdx.app.log("ConversationBase", "iteration");
			if(prefs.contains("" + (i+1)) && prefs.contains((i+1) + "v")) {
				Gdx.app.log("ConversationBase", "contains");
				
				combinations.add(new Combination(prefs.getString("" + (i+1)), prefs.getString((i+1) + "v")));
			}
			
		}
	}
	
	//TODO
	public static void update(EasterEgg easterEgg) {
		
	}
	
	public static void tryFirstLoad() {
		prefs.clear();
		prefs.flush();
		if (!prefs.contains("firstLoad")) {
			Gdx.app.log("ConversationBase", "tryFirstLoad");
			prefs.putString("firstLoad", "true");
			prefs.putString("1", "1 2");
			prefs.putString("1v", "the window was broken and some \nglass fell outside");
			prefs.putString("2", "1 2 6");
			prefs.putString("2v", "actually you felt very stressed \nafter breaking a window with your ball");
			prefs.flush();
		}
		
	}
}
