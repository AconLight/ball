package com.redartedgames.ball.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.myobjects.Player;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.ReversableObject;

public class DialogHero extends ReversableObject{

	private int dialogHeroType;
	
	private DialogHeroSprite dialogHeroSprite;
	
	private DialogConversationWindow window;
	
	private DialogBackground textBg;
	
	private ConversationIntro text;
	
	private boolean isTextShown;
	
	Player player;
	
	public DialogHero(float x, float y, int id, GameObject parent, int dialogHeroType) {
		super(x, y, parent, id);
		this.dialogHeroType = dialogHeroType;
		dialogHeroSprite = new DialogHeroSprite(x, y, this, 0, dialogHeroType);
		
		textBg = new DialogBackground(x + 25, y + 100, 0, this, 150, 50);
		text = new ConversationIntro(x - 25, y + 100 + 7, 0, this, "Press F to talk");
		window = new DialogConversationWindow(textBg.getPosition().x, textBg.getPosition().y + textBg.height/2, 0, this);
		gameObjects.add(dialogHeroSprite);
		gameObjects.add(window);
		hideText();
		
		setHitbox(new Hitbox(((ReversableMovement) movement).getPositionX(), ((ReversableMovement) movement).getPositionY(), 100, 100, Hitbox.none));
	}
	
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
		//Gdx.app.log("DialogHero", "" + isStopped);
		window.updateBefore(delta, vx, vy);
		hideText();
	}
	
	public void setCollision(Player player) {
		this.player = player;
	}
	
	@Override
	public void collide(GameObject obj) {
		super.collide(obj);
		if (obj == player && c.isTrue) {
			showText();
		}
	}
	
	public void showText() {textBg.isOn = true; text.isOn = true; isTextShown = true;}
	public void hideText() {textBg.isOn = false; text.isOn = false; isTextShown = false;}
	
	public boolean showOrHideWindow() {
		boolean flaga = false;
		if (window.isOn) hideWindow();
		else flaga = showWindow();
		return flaga;
		
	}
	
	private boolean showWindow() {
		if (isTextShown) {
			window.show();
			text.text = "Press F to exit";
		}
		return isTextShown;
	}
	
	private void hideWindow() {
		window.hide();
		text.text = "Press F to talk";
	}
	
	public void render(SpriteBatch batch, int priority) {
		for(int i=0; i<gameObjects.size();i++)
			gameObjects.get(i).render(batch, priority);
		
		textBg.render(batch, priority);
		text.render(batch, priority);
	}
	
	public void acceptDialogOptions() {
		window.acceptDialogOptions();
	}
	
	public void dialogUp() {
		window.moveChosenUp();
	}
	
	public void dialogDown() {
		window.moveChosenDown();
	}
	
	public void dialogMark() {
		window.markChosenOption();
	}
	

}
