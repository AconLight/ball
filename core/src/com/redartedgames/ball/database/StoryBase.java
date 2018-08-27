package com.redartedgames.ball.database;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.redartedgames.ball.dialog.Combination;
import com.redartedgames.ball.myobjects.EasterEgg;

public class StoryBase {

	public static Preferences prefs = Gdx.app.getPreferences("story");
	
	public static ArrayList<Combination> combinations;
	
	private static int recordNumber = 8;
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
				Gdx.app.log("ConversationBase", "contains: " + prefs.getString("" + (i+1)));
				
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
			prefs.putString("1v", "So yeah, you should definetly go right to the end of the screen.");
			prefs.putString("2", "2");
			prefs.putString("2v", "You will be able to turn back the time to overcome obstacles. \n"
					+ "However that will be happening during further levels... \n"
					+ "You aren't ready yet.");
			prefs.putString("3", "3");
			prefs.putString("3v", "Well, I don't know, but I guess you have just\n"
					+ " died or you are close to do it.");
			prefs.putString("4", "1 2");
			prefs.putString("4v", "Your ability of turning back the time will make\n"
					+ " you be able to undo all your moves. Pretty cool.");
			prefs.putString("5", "2 3");
			prefs.putString("5v", "Time travel is more than just a mechanic, \n"
					+ "it's connected with your life, but i can't say anything more.");
			prefs.putString("6", "1 3");
			prefs.putString("6v", "I'm pretty sure you will find out something about\n you by going right to the next levels");
			prefs.putString("7", "1 2 3");
			prefs.putString("7v", "Heh. I'm glad you were so curious as to mark all the options. \n"
					+ "I may award you and tell this curiosity will be \n"
					+ "helpfull for you to find out why you are here and \n"
					+ "what your real goal is.");
			prefs.putString("8", "");
			prefs.putString("8v", "THIS IS THE END");
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
	
	static String example = "Szef ze spokojem oznajmi� ci, �e dalsza wsp�praca nie ma sensu. "
			+ "Z tysi�cem my�li w g�owie wyszed�e� szybkim, wzburzonym krokiem. Wsiad�e� do "
			+ "samochodu i przekr�ci�e� kluczyk. (3)Auto ruszy�o gwa�townie. Jecha�e� szybko "
			+ "i nie zwa�a�e� na wilgotn� nawierzchni�. @Chcia�e� jak najszybciej dotrze� do domu. "
			+ "(1)Nagle dostrzeg�e� pi�k�,kt�ra odbiwszy si� od latarni lecia�a prosto na ciebie. @"
			+ "Spanikowa�e�, zakr�ci�e� mocno kierownic� i wpad�e� w po�lizg. (1 2)Pi�ka "
			+ "poszybowa�a wysoko w g�r�, po czym spad�a na pobliski trawnik, na kt�rym le�a�y "
			+ "kawa�ki st�uczonej szyby. (4)Niedaleko r�s� du�y, okaza�y d�b, kt�ry znacznie "
			+ "przewy�sza� okoliczne domy. (2 4)Znaczna cz�� kawa�k�w by�a wbita w jego grub�, "
			+ "star� kor�.";
}
