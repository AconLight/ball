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
		if (levelId < 0) return;
		breakCombinations = new ArrayList<>();
		
		String breakText;
		if (levelId < breakExample.length)
			breakText = breakExample[levelId];
		else
			breakText = "";
		
		breakText = breakText.replace("œ", "s");
		breakText = breakText.replace("¹", "a");
		breakText = breakText.replace("¿", "z");
		breakText = breakText.replace("ó", "o");
		breakText = breakText.replace("³", "l");
		breakText = breakText.replace("ñ", "n");
		breakText = breakText.replace("æ", "c");
		breakText = breakText.replace("ê", "e");
		
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
		story = story.replace("œ", "s");
		story = story.replace("¹", "a");
		story = story.replace("¿", "z");
		story = story.replace("ó", "o");
		story = story.replace("³", "l");
		story = story.replace("ñ", "n");
		story = story.replace("æ", "c");
		story = story.replace("ê", "e");
		
		
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
			
			"Erron jest gr¹, która w zamian za pozostawienie swoich wspomnieñ pozwala poznaæ wspomnienia losowego gracza, dziêki czemu ka¿dy gracz doœwiadcza unikalnej zawartoœci. Podczas rozwi¹zywania zagadek czasoprzestrzennych gracz zbiera symbole przesz³oœci, które póŸniej wykorzystuje do odczytania wspomnieñ. Im wiêcej symboli gracz zbiera, tym wiêcej informacji na temat wspomnieñ bêdzie móg³ uzyskaæ. Gra pozostawia graczowi wolnoœæ wyboru oraz interpretacji, dlatego zachêcam do wyrobienia sobie swojego w³asnego podejœcia.\r\n" + 
			" Sterowanie: A - lewo, D - prawo, W - skok, SPACJA - cofanie czasu, R - restart poziomu, ENTER - rozpoczêcie poziomu\n" + 
			"",
			
			"Wszystkie elementy, które zobaczysz, a zw³aszcza symbole przesz³oœci zosta³y wytworzone na podstawie wspomnieñ losowego gracza. Zdobyte wskazówki mog¹ byæ wykorzystane podczas dialogu z Erronianami, mieszkañcami Erron, których stworzono by przechowywaæ informacje. Zostali oni zaprojektowani w taki sposób, by móc przekazywaæ ró¿ne czêœci wspomnienia w zale¿noœci od podanych symboli. Przyk³adowo, podaj¹c czas, dowiesz siê o ogólnych realiach dotycz¹cych tego czasu. Podaj¹c miejsce, poznasz ogólne szczegó³y dotycz¹ce danego miejsca. Natomiast podaj¹c jednoczeœnie czas i miejsce, dowiesz siê, co konkretnie siê wydarzy³o. Dobrze jest zatem mieæ jak najwiêcej symboli, by móc sprawdziæ jak najwiêcej kombinacji, w celu odkrycia, co naprawdê siê sta³o.\r\n" + 
			"",
			
			"Warto zauwa¿yæ, ¿e skok w tej grze nie jest zwyk³ym skokiem... Dzia³a bardziej, jak plecak rakietowy. Oznacza to tyle, ¿e mo¿esz zamiast jednego skoku wykonaæ na przyk³ad dwa ma³e w ramach jednego wzbicia siê w powietrze. Mo¿esz równie¿ wykonaæ skok po zsuniêciu siê z platformy.",
			
			"W grze mo¿esz spotkaæ lampy. Kiedy coœ takiej lampy dotknie, wtedy zazwyczaj któraœ z platform siê przesuwa.",
			
			"Nie musisz skakaæ na maksymaln¹ wysokoœæ, czasem lepiej jest wykonaæ mniejszy skok, by zyskaæ na czasie.",
			
			"Uda³o ci siê ju¿ czegoœ dowiedzieæ o poprzednim graczu, który zostawi³ tu swoje wspomnienia?",
			
			"No tak, zapomnia³em, ¿e s¹ jeszcze duszki... Je¿eli w prawym górnym rogu ekranu zobaczysz kolejkê duszków, to wiedz, ¿e cofanie czasu zadzia³a trochê inaczej... Na przyk³ad niebieski duszek pojawia siê w miejscu, w którym zaczniesz siê cofaæ, dzia³a tak samo, jak twoja postaæ z jednym wyj¹tkiem: kiedy zetknie siê z jakimœ obiektem, zatrzymuje jego czas i swój... Sprowadza siê to do tego, ¿e mo¿esz tym duszkiem dotkn¹æ lampy, albo zatrzymaæ jak¹œ platformê. S¹ te¿ inne duszki: czerwony, zielony... Ale to kiedy indziej o nich pogadamy.",
			
			"Pamiêtaj, ¿eby cofn¹æ siê w jakieœ miejsce, musisz tam najpierw byæ.",
			
			"Nie wszystko wydaje siê oczywiste. Czasem trzeba wpaœæ na rozwi¹zanie przypadkiem.",
			
			"Wszystko ju¿ by³o tylko w innej formie.",
			
			"Mam nadziejê, ¿e pamiêtasz, ¿e niebieski duszek zatrzymuje czas obiektu, którego dotknie.",
			
			"Z jednym te¿ sobie poradzisz.",
			
			"Jak daleko jesteœ w stanie siê cofn¹æ, aby osi¹gn¹æ swój cel?",
			
			"Niektóre lampy zatrzymuj¹ czas tak samo, jak niebieski duszek.",
			
			"Umiesz popsuæ czas?",
			
			"",
			
	};
	
	static String[] stories2 = {
			"first conv",
			"sec conv"
			
					
	};
	
	static String[] stories = {
			"To wspomnienie dotyczy ostatniego wieczoru. (1)Jak zwykle w pi¹tek oko³o godziny osiemnastej pi³eœ piwo i gra³eœ na komputerze. @"
			+ "Trwa³o to mniej wiêcej cztery godziny. (1 2 3)Po skoñczeniu 7 butelki nie wytrzyma³eœ i zacz¹³eœ siê kreciæ po pokoju. W koñcu stan¹³eœ przed szaf¹. @"
			+ " (2 3)Otworzy³eœ szafê i skierowa³eœ swój pijany wzrok na sznur. @(2)Zawi¹zanie wêz³a by³o zaskakuj¹co proste. @(3) Spojrza³eœ na szafê, wydawa³a siê doœc wysoka, ale miêdzy ni¹ a sufitem by³o du¿o miejsca. @"
			+ "Chcia³eœ po sobie coœ zostawiæ... Niestety nic ci nie przychodzi³o do g³owy, wiêc napisa³eœ tylko: g³upia gra.",
			
			"(2)W³aœnie skoñczy³eœ grê. (5)Nie by³o to nic specjalnego, ale trochê czasu nad tym spêdzi³eœ. (6)Wspomnienia gracza, które pozna³eœ, by³y zadziwiaj¹co znajome. @Tak jakby by³a to osoba, któr¹ zna³eœ. "
			+ "(2 6)Co wiêcej nie by³y to zabawne wspomnienia, a gracz skoñczy³ doœæ nieciekawie. (4 6)Jednak najbardziej ciekawi³ ciê fakt, ¿e gracz wspomnia³, ¿e nie jest mo¿liwe, aby gra udostêpnia³a czyjeœ wspomnienia, bo gdyby tak by³o, to i tak pierwszy gracz nie mia³by od kogo tych wspomnieñ uzyskaæ i zosta³o by to w jego wspomnieniach zaznaczone, a kolejni gracze z pewnoœci¹ by przekazywali ten fakt dalej. @"
			+ "No chyba, ¿e pierwszy gracz uzyska³ by wspomnienia ostatniego gracza.... (5 6)Ale wtedy musia³by przenieœæ siê w czasie, co nie jest mo¿liwe... Musia³by tak¿e wiedzieæ, kto zostanie ostatnim graczem.. @"
			+ "W ka¿dym razie czu³eœ siê mocno zagubiony i nie by³o to wcale przyjemne, raczej do³uj¹ce."
					
	};
}
