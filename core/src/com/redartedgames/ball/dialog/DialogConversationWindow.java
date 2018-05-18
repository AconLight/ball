package com.redartedgames.ball.dialog;

import java.math.BigDecimal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.database.EasterEggsBase;
import com.redartedgames.ball.myobjects.EasterEgg;
import com.redartedgames.ball.objects.GameObject;

public class DialogConversationWindow extends GameObject{

	public boolean isOn;
	private DialogWindow dialogWindow;
	private Conversation conversation;
	private DialogBackground bg;

	public DialogConversationWindow(float x, float y, int id, GameObject parent) {
		super(x, y, id, parent);
		dialogWindow = new DialogWindow();
		for(EasterEgg egg: EasterEggsBase.easterEggs) {
			Gdx.app.log("GameWorld", "id: " + egg.id + ", name: " + egg.name + ", isTrue: " + egg.isTrue);
			if (egg.isTrue)
				dialogWindow.addOption(egg.name, egg.id);
		}	
		conversation = new Conversation(500, 500, 0, this);
		bg = new DialogBackground(x, y, id, this, 600, 200);
	}
	
	public void show() {
		isOn = true;
		dialogWindow.show();
	}
	
	public void hide() {
		isOn = false;
		dialogWindow.hide();
	}
	
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
		dialogWindow.getPosition().set(parent.getPosition().x -300 + 50, parent.getPosition().y + 200);
		conversation.getPosition().set(parent.getPosition().x +50, parent.getPosition().y + 200);
		bg.updateBefore(delta, vx, vy);
	}
	
	public void render(SpriteBatch batch, int priority) {
		if (isOn) {
			bg.render(batch, priority);
			dialogWindow.render(batch, priority);
			conversation.render(batch, priority);
		}
	}
	
	public void acceptDialogOptions() {
		conversation.showCombination(dialogWindow.getCombination());
	}
	
	public void markChosenOption() {
		if (isOn) {
			dialogWindow.markChosenOption();
		}
	}
	
	public void moveChosenUp() {
		if (isOn) {
			dialogWindow.moveChosenUp();
		}
	}
	
	public void moveChosenDown() {
		if (isOn) {
			dialogWindow.moveChosenDown();
		}
	}
	
	

}
