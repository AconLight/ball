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
	
	public Conversation(float x, float y, int id, GameObject parent) {
		super(x, y, id, parent);
		load();
		text = "tell me, what you want to know.\n<mark options>";
		font = new BitmapFont();
		font.setColor(Color.GREEN);
	}

	
	public void load() {
		ConversationsBase.tryFirstLoad();
		ConversationsBase.load();
		combinations = ConversationsBase.combinations;
	}
	
	public void showCombination(ArrayList<Integer> intList) {
		text = searchCombinationText(intList);
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
		font.draw(batch, text, position.x, position.y);
	}
	
	
	
	
}
