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
			prefs.putString("1v", "Odk�d sta�e� si� doros�y przesta�e� przepada� za sportem. \n"
								+ "Nic cie tak nie denerwowalo, jak dzieci kopiace pilke.");
			prefs.putString("2", "2");
			prefs.putString("2v", "Rozbita szyba zawsze wzbudzala w tobie emocje oraz mysli \n"
								+ "detektywistyczne. Cos przeciez musialo ja zbic...");
			prefs.putString("3", "3");
			prefs.putString("3v", "Twoje auto bylo dla ciebie bardzo cenne, zawsze \n"
								+ "towarzyszylo ci w trudnych chwilach.");
			prefs.putString("4", "4");
			prefs.putString("4v", "W dniu pierwszysch urodzin twojej corki posadziles drzewo \n"
					+ "w waszym ogrodku. Tak samo jak twoja matka posadzila drzewo w dniu \n"
					+ "twoich pierwszych urodzin.");
			prefs.putString("5", "1 2");
			prefs.putString("5v", "Kiedys, bedac jeszcze malym chlopcem, rozbiles szybe, grajac w pilke. Uciekles i nigdy \n"
					+ "nie odzyskales pilki spowrotem.");
			prefs.putString("6", "2 4");
			prefs.putString("6v", "W swoim zyciu miales tylko jeden wypadek samochodowy. \n"
								+ "Nie doznales wiekszych obrazen, ale pietno tego zdarzenia \n"
								+ "sprawilo, ze twoje zycie juz nigdy nie bylo takie, jak \n"
								+ "wczesniej.");
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
	
	static String example = "(1)Odk�d sta�e� si� doros�y przesta�e� przepada� za sportem. Nic ci� tak nie denerwowa�o, jak dzieci kopi�ce pi�k�. " 
			+ "(2)Rozbite szk�o zawsze wzbudza�o w tobie emocje oraz mysli detektywistyczne. Co� przecie� musia�o je zbi�... "
			+ "(3)Twoje auto by�o dla ciebie bardzo cenne, zawsze towarzyszy�o ci w trudnych chwilach. "
			+ "(4)W dniu pierwszych urodzin twojej c�rki posadzi�e� drzewo w waszym ogr�dku. Tak samo jak twoja matka posadzi�a drzewo w dniu twoich pierwszych urodzin. "
			+ "(1 2)Kiedy�, b�d�c jeszcze ma�ym ch�opcem, rozbi�e� szyb�, graj�c w pi�k�. Uciek�e� i nigdy nie odzyska�e� pi�ki spowrotem. "
			+ "(2 4)W swoim �yciu mia�es tylko jeden wypadek samochodowy. Nie dozna�e� wi�kszych obra�e�, ale pi�tno tego zdarzenia sprawi�o, �e twoje �ycie ju� nigdy nie by�o takie, jak wcze�niej. ";
}
