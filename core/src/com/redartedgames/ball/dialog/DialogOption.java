package com.redartedgames.ball.dialog;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.objects.GameObject;

public class DialogOption extends GameObject{

	BitmapFont font;
	String text;
	boolean isMarked;
	boolean isChosen;
	boolean isNeutral;
	static Color chosenColor = new Color(0.4f, 0.4f, 1f, 1);
	static Color neutralColor = new Color(0.8f, 0.8f, 0.8f, 1);
	static Color markedColor = new Color(0.8f, 0.8f, 0.8f, 1);
	public int optionId;
	public static int gap = 30;
	private float tab;
	private float tabDirection;
	private float tabMax = 50;
	
	public DialogOption(GameObject parent, String text, int id, int optionId) {
		super(0, gap + id*gap, id, parent);
		this.text = text;
		tab = 0;
		font = new BitmapFont();
		setNeutral();
		this.optionId = optionId;
	}
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
		tab += (tabDirection - tab)*delta;
	}
	
	public void render(SpriteBatch batch, int priority) {
		font.draw(batch, text, tab + parent.getPosition().x + position.x, parent.getPosition().y + position.y);
		if (isChosen) {
			batch.setColor(0.4f, 0.4f, 1f, 1);
			batch.draw(GameObject.dotTex, parent.getPosition().x + position.x - 20 - 4, parent.getPosition().y + position.y-4 -5, 8, 8); 
		}
		if (isMarked) {
			batch.setColor(1f, 1f, 0.4f, 1);
			batch.draw(GameObject.dotTex, parent.getPosition().x + position.x - 20 - 2, parent.getPosition().y + position.y-2 -5, 4, 4); 
		}
	}
	
	public void updatePositions(int size) {
		position.y = (id-(size-1)/2f)*30;
	}
	
	public void setMarked() {
		if(!isMarked) {
			tabDirection = tabMax;
			isMarked = true;
			font.setColor(markedColor);
		}
		else {
			tabDirection = 0;
			isMarked = false;
			if (isChosen) {
				font.setColor(chosenColor);
			}
			else {
				font.setColor(neutralColor);
			}
		}
		
		isNeutral = false;
	}
	
	public void setNeutral() {
		isMarked = false;
		isChosen = false;
		isNeutral = true;
		font.setColor(neutralColor);
		tabDirection = 0;
	}
	
	public void setChosen() {
		isChosen = true;
		isNeutral = false;
		font.setColor(chosenColor);
	}
	
	public void removeChosen() {
		isChosen = false;
		if (isMarked) {
			font.setColor(markedColor);
		}
		else {
			font.setColor(neutralColor);
		}
	}
	
	
}
