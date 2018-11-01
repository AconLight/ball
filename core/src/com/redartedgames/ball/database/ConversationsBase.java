package com.redartedgames.ball.database;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.redartedgames.ball.dialog.Combination;
import com.redartedgames.ball.myobjects.EasterEgg;

public class ConversationsBase {

	public static Preferences prefs = Gdx.app.getPreferences("conversation");
	
	public static ArrayList<Combination> combinations;
	
	private static int recordNumber = 7;
	public static void load() {
		combinations = new ArrayList<>();
		example = example.replace("�", "s");
		example = example.replace("�", "a");
		example = example.replace("�", "z");
		example = example.replace("�", "o");
		example = example.replace("�", "l");
		example = example.replace("�", "n");
		example = example.replace("�", "c");
		example = example.replace("�", "e");
		convertStory(example);
	}
	
	public static void load2() {
		combinations = new ArrayList<>();
		boolean temp;
		for(int i = 0; i < recordNumber; i++) {
			//Gdx.app.log("ConversationBase", "iteration");
			if(prefs.contains("" + (i+1)) && prefs.contains((i+1) + "v")) {
				//Gdx.app.log("ConversationBase", "contains");
				
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
			//Gdx.app.log("ConversationBase", "tryFirstLoad");
			prefs.putString("firstLoad", "true");
			prefs.putString("1", "1");
			prefs.putString("1v", "Lubi�e� napi� si� dobrego piwa. Mo�e nie zna�e� si� na gatunkach, ale mia�e� talent do zapami�tywania smak�w i potrafi�e� je szczeg�owo por�wnywa� na wielu p�aszczyznach.");
			prefs.putString("2", "2");
			prefs.putString("2v", "Sznur do �wieczenia w�z��w �eglarskich by� ciekawym prezentem, zach�ca� do spr�bowania czego� nowego.");
			prefs.putString("3", "3");
			prefs.putString("3v", "Szafa to szafa - nic szczeg�lnego");
			prefs.putString("4", "2 3");
			prefs.putString("4v", "Mo�e sznur w szafie to nie jest codzienny widok, ale naprawd� nie by�o dla niego lepszego miejsca.");
			prefs.putString("5", "1 2 3");
			prefs.putString("5v", "M�wi�e� na to \"plan B w szafie\". Gdyby nie alkohol, nigdy by to nie wysz�o z formy �artu.");
			prefs.flush();
		}
		
	}
	
	static String END_OF_ELEMENT = "@";
	static String COMBINATION_START = "\\(";
	static String COMBINATION_END = "\\)";
	
	private static void convertStory(String s) {
		s.replace("\n", "");
		String[] elements = s.split(COMBINATION_START);
		String[] elementParts;
		String[] elementParts2;
		for (String element: elements) {
			elementParts = element.split(COMBINATION_END);
			if (elementParts.length > 1) {
				elementParts2 = elementParts[1].split(END_OF_ELEMENT);
				combinations.add(new Combination(elementParts[0], elementParts2[0]));
				if (elementParts2.length > 1) {
					combinations.add(new Combination("", elementParts2[1]));
				}
			}
			else {
				combinations.add(new Combination("", element));
			}

		}
	}
	
	static String example = "(1)Lubi�e� napi� si� dobrego piwa. Mo�e nie zna�e� si� na gatunkach, ale mia�e� talent do zapami�tywania smak�w i potrafi�e� je szczeg�owo por�wnywa� na wielu p�aszczyznach. " 
			+ "(2)Sznur do �wieczenia w�z��w �eglarskich by� ciekawym prezentem, zach�ca� do spr�bowania czego� nowego."
			+ "(3)Szafa to szafa - nic szczeg�lnego "
			+ "(2 3)Mo�e sznur w szafie to nie jest codzienny widok, ale naprawd� nie by�o dla niego lepszego miejsca. "
			+ "(1 2 3)M�wi�e� na to \"plan B w szafie\". Gdyby nie alkohol, nigdy by to nie wysz�o z formy �artu. ";
}
