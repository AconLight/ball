package com.redartedgames.ball.database;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.redartedgames.ball.dialog.Combination;
import com.redartedgames.ball.myobjects.EasterEgg;

public class StoryBase {

	public static Preferences prefs = Gdx.app.getPreferences("story");
	
	public static ArrayList<Combination> combinations, breakCombinations;
	
	private static int recordNumber = 8;
	
	public static void loadBreak(int levelId) {
		breakCombinations = new ArrayList<>();
		
		String breakText;
		if (levelId < breakExample.length)
			breakText = breakExample[levelId];
		else
			breakText = "";
		
		breakText = breakText.replace("�", "s");
		breakText = breakText.replace("�", "a");
		breakText = breakText.replace("�", "z");
		breakText = breakText.replace("�", "o");
		breakText = breakText.replace("�", "l");
		breakText = breakText.replace("�", "n");
		breakText = breakText.replace("�", "c");
		breakText = breakText.replace("�", "e");
		
		convertStoryBreak(breakText);
		
	}
	
	public static int StoryId = 0;
	
	public static void load() {
		load(StoryId);
	}
	
	public static void load(int id) {
		combinations = new ArrayList<>();
		String story;
		if (id < stories.length) story = stories[id];
		else story = "dddd";
		
		Gdx.app.log("StoryBase", "load" + id);
		story = story.replace("�", "s");
		story = story.replace("�", "a");
		story = story.replace("�", "z");
		story = story.replace("�", "o");
		story = story.replace("�", "l");
		story = story.replace("�", "n");
		story = story.replace("�", "c");
		story = story.replace("�", "e");
		
		
		convertStory(story);
		
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
	
	private static void convertStoryBreak(String s) {
		s.replace("\n", "");
		String[] elements = s.split(COMBINATION_START);
		String[] elementParts;
		String[] elementParts2;
		for (String element: elements) {
			elementParts = element.split(COMBINATION_END);
			if (elementParts.length > 1) {
				elementParts2 = elementParts[1].split(END_OF_ELEMENT);
				breakCombinations.add(new Combination(elementParts[0], elementParts2[0]));
				if (elementParts2.length > 1) {
					breakCombinations.add(new Combination("", elementParts2[1]));
				}
			}
			else {
				breakCombinations.add(new Combination("", element));
			}

		}
	}
	
	static String[] breakExample = {
			"",
			
			"Erron jest gr�, kt�ra w zamian za pozostawienie swoich wspomnie� pozwala pozna� wspomnienia losowego gracza, dzi�ki czemu ka�dy gracz do�wiadcza unikalnej zawarto�ci. Podczas rozwi�zywania zagadek czasoprzestrzennych gracz zbiera symbole przesz�o�ci, kt�re p�niej wykorzystuje do odczytania wspomnie�. Im wi�cej symboli gracz zbiera, tym wi�cej informacji na temat wspomnie� b�dzie m�g� uzyska�. Dla jednych jest to zwyk�y zbi�r zagadek logicznych, a dla innych okazja do zg��bienia ludzkiej natury. Gra pozostawia graczowi wolno�� wyboru oraz interpretacji, dlatego zach�cam do wyrobienia sobie swojego w�asnego podej�cia.\r\n" + 
			" Sterowanie: A - lewo, D - prawo, W - skok, SPACJA - cofanie czasu, R - restart poziomu\n" + 
			"",
			
			"Wszystkie elementy, kt�re zobaczysz, a zw�aszcza symbole przesz�o�ci zosta�y wytworzone na podstawie wspomnie� losowego gracza. Zdobyte wskaz�wki mog� by� wykorzystane podczas dialogu z Erronianami, mieszka�cami Erron, kt�rych stworzono by przechowywa� informacje. Zostali oni zaprojektowani w taki spos�b, by m�c przekazywa� r�ne cz�ci wspomnienia w zale�no�ci od podanych symboli. Przyk�adowo, podaj�c czas, dowiesz si� o og�lnych realiach dotycz�cych tego czasu. Podaj�c miejsce, poznasz og�lne szczeg�y dotycz�ce danego miejsca. Natomiast podaj�c jednocze�nie czas i miejsce, dowiesz si�, co konkretnie si� wydarzy�o. Dobrze jest zatem mie� jak najwi�cej symboli, by m�c sprawdzi� jak najwi�cej kombinacji, w celu odkrycia, co naprawd� si� sta�o.\r\n" + 
			"",
			
			"Warto zauwa�y�, �e skok w tej grze nie jest zwyk�ym skokiem... Dzia�a bardziej, jak plecak rakietowy. Oznacza to tyle, �e mo�esz zamiast jednego skoku wykona� na przyk�ad dwa ma�e w ramach jednego wzbicia si� w powietrze. Mo�esz r�wnie� wykona� skok po zsuni�ciu si� z platformy.",
			
			"W grze mo�esz spotka� lampy. Kiedy co� takiej lampy dotknie, wtedy zazwyczaj kt�ra� z platform si� przesuwa.",
			
			"Nie musisz skaka� na maksymaln� wysoko��, czasem lepiej jest wykona� mniejszy skok, by zyska� na czasie.",
			
			"Zastanawiam si�, jaki wp�yw na gracza mo�e mie� czyja� historia... Zazwyczaj skupiamy si� na wielkich, epickich zdarzeniach, kt�re maj� jakie� przes�anie. Ale co si� stanie, kiedy do�wiadczymy czego� zwyk�ego? Czy brak wniosk�w jest a� taki z�y?",
			
			"No tak, zapomnia�em, �e s� jeszcze duszki... Je�eli w prawym g�rnym rogu ekranu zobaczysz kolejk� duszk�w, to wiedz, �e cofanie czasu zadzia�a troch� inaczej... Na przyk�ad niebieski duszek pojawia si� w miejscu, w kt�rym zaczniesz si� cofa�, dzia�a tak samo, jak twoja posta� z jednym wyj�tkiem: kiedy zetknie si� z jakim� obiektem, zatrzymuje jego czas i sw�j... Sprowadza si� to do tego, �e mo�esz tym duszkiem dotkn�� lampy, albo zatrzyma� jak�� platform�. S� te� inne duszki: czerwony, zielony... Ale to kiedy indziej o nich pogadamy.",
			
			"Pami�taj, �eby cofn�� si� w jakie� miejsce, musisz tam najpierw by�.",
			
			"Pewnie ju� zapomnia�e� o graczu i jego wspomnieniach... W takim razie czuj� si� w obowi�zku poci�gn�� troch� fabu�� do przodu. No wi�c my�l�, �e mog� powiedzie�, �e by�a to osoba niewyr�niaj�ca si�. Lubi�a gra� w rzadkie, niezale�ne gry, zreszt� dlatego pewnie wog�le zagra�a w t� gr�. W dniu, kt�rego wiecz�r uda�o ci si� ju� cz�ciowo pozna�, mia�a swoje urodziny, jednak nie mog� powiedzie� kt�re. Jak si� pewnie domy�lasz, lubi�a sobie wypi�, ale nie zawsze tak by�o... Wcze�niej wypicie jednego czy dw�ch piw na spotkaniu ze znajomymi by�o maksimum. Jednak pewnego dnia... CIEKAWI CI�, CO BY�O DALEJ? JU� TERAZ WYKUP WERSJ� PREMIUM!",
			
			"Ju� wykupi�e� wersj� premium? Ciesz� si�, b�d� mia�, co je��. Wdzi�czno�� jest spoko, ale ma zero kalorii - to troch� k�opotliwe... �artuj�, nie wezm� ani grosza za t� gr�. potrzebowa�em jakiej� deus ex machiny.",
			
			""
			+ "You can hold W to jump 11.",
			
			""
			+ "You can hold W to jump 12.",
			
			""
			+ "You can hold W to jump 13.",
			
			""
			+ "You can hold W to jump 14.",
			
			""
			+ "You can hold W to jump 15.",
			
			""
			+ "You can hold W to jump 16.",
			
	};
	
	static String[] stories2 = {
			"first conv",
			"sec conv"
			
					
	};
	
	static String[] stories = {
			"To wspomnienie dotyczy ostatniego wieczoru. (1)Jak zwykle w pi�tek oko�o godziny osiemnastej pi�e� piwo i gra�e� na komputerze. @"
			+ "Trwa�o to mniej wi�cej cztery godziny. (1 2 3)Po sko�czeniu 7 butelki nie wytrzyma�e� i zacz��e� si� kreci� po pokoju. W ko�cu stan��e� przed szaf�. @"
			+ " (2 3)Otworzy�e� szaf� i skierowa�e� sw�j pijany wzrok na sznur. @(2)Zawi�zanie w�z�a by�o zaskakuj�co proste. @(3) Spojrza�e� na szaf�, wydawa�a si� do�c wysoka, ale mi�dzy ni� a sufitem by�o du�o miejsca. @"
			+ "Chcia�e� po sobie co� zostawi�... Niestety nic ci nie przychodzi�o do g�owy, wi�c napisa�e� tylko: g�upia gra.",
					
	};
}
