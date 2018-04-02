package com.redartedgames.ball.game;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.LevelLoader;
import com.redartedgames.ball.myobjects.Ball;
import com.redartedgames.ball.myobjects.ButtonRect;
import com.redartedgames.ball.myobjects.ImpsCollection;
import com.redartedgames.ball.myobjects.LavaRect;
import com.redartedgames.ball.myobjects.MovingRect;
import com.redartedgames.ball.myobjects.Player;
import com.redartedgames.ball.myobjects.Rect;
import com.redartedgames.ball.myobjects.ShiftedRect;
import com.redartedgames.ball.myobjects.StaticButton;
import com.redartedgames.ball.myobjects.StaticImp;
import com.redartedgames.ball.objects.ColSpriteObject;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.screen.Consts;
import com.redartedgames.ball.screen.World;

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
	private boolean isForward;
	public ImpsCollection impsCollection;
	private float timeNextLvl = 0;
	private boolean isNextLvl = false;
	
	private SpriteObject nextLvlRect;
	 
	public float timeTime, timeBar, timeVel, timeAcc;
	
	int levelId;
	
	public void restart(int levelId) {
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
		reversableObjects.addAll(LevelLoader.getLevel(levelId, player, impsCollection));
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
	}
	
	public GameWorld() {
		super();

		restart(1);
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
			}
			
			if (!player.isAlive) {
				restartLvl();
			}
				
		}
		else {
			nextLvlRect.visibility+=delta/10;
			if (nextLvlRect.visibility > 1) nextLvlRect.visibility = 1;
			timeNextLvl += delta;
			if (timeNextLvl > 10) {
				gameObjects.clear();
				gameObjects.add(nextLvlRect); ///  +1
				restart(levelId);
				gameObjects.remove(nextLvlRect);
				gameObjects.add(nextLvlRect); 
				isNextLvl = false;
			}
		}

		
		

			
			
	
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
		if (!isForward) timeNum = timeNumNormal*8;
	}
}
