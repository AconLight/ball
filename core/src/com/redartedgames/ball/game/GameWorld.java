package com.redartedgames.ball.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.redartedgames.ball.LevelLoader;
import com.redartedgames.ball.consts.LauncherSettings;
import com.redartedgames.ball.dialog.BreakWindow;
import com.redartedgames.ball.dialog.DialogHero;
import com.redartedgames.ball.graphicgenerators.BlackScreenAnimation;
import com.redartedgames.ball.myobjects.Ball;
import com.redartedgames.ball.myobjects.ButtonRect;
import com.redartedgames.ball.myobjects.ImpsCollection;
import com.redartedgames.ball.myobjects.Player;
import com.redartedgames.ball.myobjects.ShiftedRect;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.screen.Consts;
import com.redartedgames.ball.screen.World;
import com.redartedgames.ball.sound.SoundHandler;

public class GameWorld extends World{

	public ButtonRect but;
	public Ball ball, ball1, ball2;
	public ShiftedRect rect;
	private float time;
	private float timeNum, timeNumNormal = 0.15f;
	public int t, i;
	public boolean isPaused;
	public Player player;
	public ArrayList <ReversableObject> reversableObjects;
	public ArrayList <GameObject> collidableObjects;
	boolean isForward;
	public static  boolean isForwardStatic;
	public ImpsCollection impsCollection;
	private float timeNextLvl = 0;
	public boolean isNextLvl = true, isBreak = false, breakContinue = false;
	float cloudT = 0;
	public BlackScreenAnimation blackScreenAnimation;
	
	public BreakWindow breakWindow;
	
	public DialogHero dialogHero;
	
	public float conversationShade;
	public boolean isConversation;
	
	public SpriteObject nextLvlRect;
	 
	public float timeTime, timeBar, timeVel, timeAcc;
	
	int levelId = 1;
	
	public void restart() {
		restart(levelId);
	}
	
	public void restart(int levelId) {
		breakWindow = new BreakWindow(0, 0, 0, null, levelId);
		isConversation = false;
		conversationShade = 0.4f;
		this.levelId = levelId;
		time = 0;
		timeNum = timeNumNormal;
		timeBar = 0.1f;
		timeVel = 0f;
		timeTime = 0;
		time = 0;
		t = 1;
		i = 0;
		reversableObjects = new ArrayList<ReversableObject>();
		isForward = true;	
		player = new Player(0, 250, 1f, null, 10);	
		impsCollection = new ImpsCollection();
		reversableObjects.addAll(LevelLoader.getLevel(levelId, player, impsCollection, this));
		reversableObjects.add(player);
		gameObjects.addAll(reversableObjects);
		
		for (GameObject obj : reversableObjects) {
			for (GameObject obj2 : reversableObjects) {
				if(obj != obj2) {
					obj.collidableObjects.add(obj2);
				}
			}
		}
		player.collidableObjects.removeAll(impsCollection.getImps());
		for (GameObject obj : impsCollection.getImps()) {
			obj.collidableObjects.remove(player);
		}
		
		nextLvlRect.visibility = 1f;
	}
	
	public GameWorld() {
		super(); 
		nextLvlRect = new SpriteObject(0, 0, null, 0);
		Texture t = GameObject.dotTex;
		nextLvlRect.addTexture(t);
		nextLvlRect.sclX = Consts.gameWidth;
		nextLvlRect.sclY = Consts.gameHeight;
		nextLvlRect.visibility = 1f;
		nextLvlRect.R = 0.08f;
		nextLvlRect.G = 0.08f;
		nextLvlRect.B = 0.08f;
		gameObjects.add(nextLvlRect);

		restart(LauncherSettings.startLvl);
		blackScreenAnimation = new BlackScreenAnimation();
		
	}
	
	public void restartLvl() {
		timeNextLvl = 0;
		isNextLvl  = true;
	}
	
	@Override
	public void update(float delta) {
		if (!isNextLvl) {
			nextLvlRect.visibility-=delta/10;
			if (nextLvlRect.visibility < 0) nextLvlRect.visibility = 0;
			
			timeManagerUpdate(delta);
			if (player.getPosition().x > Consts.gameWidth) {
				timeNextLvl = 0;
				isNextLvl  = true;
				levelId++;
				SoundHandler.nextLvl();
			}
			
			if (!player.isAlive) {
				restartLvl();
			}
				
		}
		else {
			nextLvlRect.visibility+=delta/10;
			if (nextLvlRect.visibility > 1) nextLvlRect.visibility = 1;
			timeNextLvl += delta;
			if (timeNextLvl > 10 && !isBreak) {
				gameObjects.clear();
				gameObjects.add(nextLvlRect); ///  +1
				restart(levelId);
				gameObjects.remove(nextLvlRect);
				gameObjects.add(nextLvlRect); 
				//isNextLvl = false;
				isBreak = true;
				blackScreenAnimation.reset();
				breakContinue = false;
				breakWindow.storyText.isOn = true;
			}
			if (isBreak) {
				blackScreenAnimation.update(delta);
				if (blackScreenAnimation.hasEnded && blackScreenAnimation.direction < 0 && breakContinue) blackScreenAnimation.animateOn();
				if (blackScreenAnimation.hasEnded && blackScreenAnimation.direction > 0) {
					blackScreenAnimation.isOn = false;
					isBreak = false;
					isNextLvl = false;
					breakWindow.storyText.isOn = false;
				}
			}
		}

		if (isConversation) {
			conversationShade += delta/5;
			if (conversationShade > 1) conversationShade = 1;
		}
		else {
			conversationShade -= delta/5;
			if (conversationShade < 0.4f) conversationShade = 0.4f;
		}
		
		cloudT += delta;
			
			
	
	}
	
	private void timeManagerUpdate(float delta) {
		for(ReversableObject r : reversableObjects) {
			r.setIsForward(isForward);
		}
		time += delta;
		//Gdx.app.log("gameWorld", time + "");
		
		
		
		if (!isForward)  {
			timeNum -= timeNumNormal*delta/4;
			if (timeNum < timeNumNormal*2) timeNum = timeNumNormal*2;
		}
		else {
			timeNum = timeNumNormal;
		}
		if (time >= timeNum) {
			time -= timeNum;
			super.update(0.01f);
		}
	}
	
	public void setIsForward(boolean isForward) {
		this.isForward = isForward;
		isForwardStatic = isForward;
		if (!isForward) timeNum = timeNumNormal*8;
	}
}
