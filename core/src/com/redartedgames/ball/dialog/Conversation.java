package com.redartedgames.ball.dialog;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.database.ConversationsBase;
import com.redartedgames.ball.objects.GameObject;

public class Conversation extends GameObject{

	private ArrayList<Combination> combinations;
	String text;
	private static String defaultText = "sorry.. i can't see anything\nspecial about that";
	private BitmapFont font;
	int height, width;
	
	public Conversation(float x, float y, int width, int height, int id, GameObject parent) {
		super(x, y, id, parent);
		load();
		this.width = width;
		this.height = height;
		text = "";
		font = new BitmapFont();
		font.setColor(0.8f, 0.8f, 0.8f, 1f);
	}

	
	public void load() {
		ConversationsBase.tryFirstLoad();
		ConversationsBase.load();
		combinations = ConversationsBase.combinations;
	}
	
	public void showCombination(ArrayList<Integer> intList) {
		text = searchCombinationText(intList);
		String[] words = text.split(" ");
		String t = "";
		int length = 0;
		for (String word: words) {
			if (length + " ".length() + word.length() > 60) {
				t += "\n";
				length = 0;
			}
			t += word + " ";
			length += " ".length() + word.length();
		}
		text = t;
	}
	
	private String searchCombinationText(ArrayList<Integer> intList) {
		for(Combination combination: combinations) {
			if(combination.compare(intList)) {
				return combination.text;
			}
		}
		return defaultText;
	}
	
	public void render(SpriteBatch batch, int priority) {
		super.render(batch, priority);
		font.draw(batch, text, position.x + 80, position.y - 40);
		batch.setColor(0.1f, 0.1f, 0.1f, 0.7f);
		batch.draw(GameObject.dotTex, position.x, position.y - height + 10, 10, height/8); 
		batch.draw(GameObject.dotTex, position.x, position.y - 10 - height/8, 10, height/8);
	}
	
	
	
	
}
