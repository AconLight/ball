package com.redartedgames.ball.dialog;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.database.ConversationsBase;
import com.redartedgames.ball.database.StoryBase;
import com.redartedgames.ball.screen.Consts;

public class StoryText {
	private ArrayList<Combination> elements;
	private ArrayList<Boolean> isOns;
	private ArrayList<Integer> blank;
	private BitmapFont font;
	GlyphLayout layout = new GlyphLayout();
	
	public StoryText() {
		blank = new ArrayList<>();
		load();
		initText();
		font = new BitmapFont();
		font.setColor(0.8f, 0.8f, 0.8f, 1f);
	}
	
	public void load() {
		StoryBase.tryFirstLoad();
		StoryBase.load();
		elements = StoryBase.combinations;
	}
	
	private void initText() {
		isOns = new ArrayList<>();
		for (Combination comb: elements) {
			isOns.add(comb.compare(blank));
		}
	}
	
	public void updateText(ArrayList<Integer> combination) {
		for (int i = 0; i < elements.size(); i++) {
			if (!isOns.get(i))
			isOns.set(i, elements.get(i).compare(combination));
		}
	}
	boolean isF = true;
	public void renderText(SpriteBatch batch) {
		String[] words;
		String text = "";
		float length = 0, oldLength = 0;
		int line = 0;
		for (int i = 0; i < elements.size(); i++) {
			words = elements.get(i).text.replace("\n", "").split(" ");
			for (String word : words) {
				if (length + word.length() + " ".length() < 500) {
					layout.setText(font, word + " ");
					length += layout.width;
					text += word + " ";
				}
				else {
					if (isOns.get(i))
						render(batch, Consts.gameWidth - 600 + oldLength, Consts.gameHeight - 200 - 30*line, text);
					oldLength = 0;
					layout.setText(font, word + " ");
					length = layout.width;
					text = "";
					line++;
					text += word + " ";
				}
			}
			if (isOns.get(i))
				render(batch, Consts.gameWidth - 600 + oldLength, Consts.gameHeight - 200 - 30*line, text);
			oldLength = length;
			text = "";
		}
	}
	
	private void render(SpriteBatch batch, float x, float y, String text) {
		font.draw(batch, text, x, y);
	}
}
