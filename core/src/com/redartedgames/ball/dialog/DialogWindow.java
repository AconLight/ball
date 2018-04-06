package com.redartedgames.ball.dialog;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.objects.GameObject;

public class DialogWindow extends GameObject{
	ArrayList<DialogOption> options;
	private boolean isOn;
	
	public DialogWindow() {
		super(0, 0, 0, null);
		options = new ArrayList<>();
		isOn = false;
	}
	
	public void addOption(String text) {
		options.add(new DialogOption(this, text, options.size()));
	}
	
	public void render(SpriteBatch batch, int priority) {
		for (DialogOption o : options) {
			if(isOn) o.render(batch, priority);
		}
	}
	
	public void show() {
		isOn = true;
	}
	
	public void hide() {
		isOn = false;
	}
	
	
}
