package com.redartedgames.ball.dialog;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.objects.GameObject;

public class DialogOption extends GameObject{

	BitmapFont font;
	String text;
	public DialogOption(GameObject parent, String text, int id) {
		super(0, 30 + id*30, 0, parent);
		this.text = text;
		font = new BitmapFont();
	}
	
	public void render(SpriteBatch batch, int priority) {
		font.draw(batch, text, parent.getPosition().x + position.x, parent.getPosition().y + position.y);
	}
	
}
