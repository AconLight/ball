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
		example = example.replace("œ", "s");
		example = example.replace("¹", "a");
		example = example.replace("¿", "z");
		example = example.replace("ó", "o");
		example = example.replace("³", "l");
		example = example.replace("ñ", "n");
		example = example.replace("æ", "c");
		example = example.replace("ê", "e");
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
			prefs.putString("1v", "Lubi³eœ napiæ siê dobrego piwa. Mo¿e nie zna³eœ siê na gatunkach, ale mia³eœ talent do zapamiêtywania smaków i potrafi³eœ je szczegó³owo porównywaæ na wielu p³aszczyznach.");
			prefs.putString("2", "2");
			prefs.putString("2v", "Sznur do æwieczenia wêz³ów ¿eglarskich by³ ciekawym prezentem, zachêca³ do spróbowania czegoœ nowego.");
			prefs.putString("3", "3");
			prefs.putString("3v", "Szafa to szafa - nic szczególnego");
			prefs.putString("4", "2 3");
			prefs.putString("4v", "Mo¿e sznur w szafie to nie jest codzienny widok, ale naprawdê nie by³o dla niego lepszego miejsca.");
			prefs.putString("5", "1 2 3");
			prefs.putString("5v", "Mówi³eœ na to \"plan B w szafie\". Gdyby nie alkohol, nigdy by to nie wysz³o z formy ¿artu.");
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
	
	static String example = "(1)Lubi³eœ napiæ siê dobrego piwa. Mo¿e nie zna³eœ siê na gatunkach, ale mia³eœ talent do zapamiêtywania smaków i potrafi³eœ je szczegó³owo porównywaæ na wielu p³aszczyznach. " 
			+ "(2)Sznur do æwieczenia wêz³ów ¿eglarskich by³ ciekawym prezentem, zachêca³ do spróbowania czegoœ nowego."
			+ "(3)Szafa to szafa - nic szczególnego "
			+ "(2 3)Mo¿e sznur w szafie to nie jest codzienny widok, ale naprawdê nie by³o dla niego lepszego miejsca. "
			+ "(1 2 3)Mówi³eœ na to \"plan B w szafie\". Gdyby nie alkohol, nigdy by to nie wysz³o z formy ¿artu. ";
}
