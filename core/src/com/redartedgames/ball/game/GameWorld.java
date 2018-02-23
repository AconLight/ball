package com.redartedgames.ball.game;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
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
	 
	public float timeTime, timeBar, timeVel, timeAcc;
	
	public GameWorld() {
		super();

		
		
		time = 0;
		timeNum = timeNumNormal;
		
		impsCollection = new ImpsCollection();
		impsCollection.addStaticImp();
		impsCollection.addActiveImp();
		//impsCollection.addActiveImp();
		
		//impsCollection.addStaticImp();
		//impsCollection.addStaticImp();
		//impsCollection.addStaticImp();
		timeBar = 0.1f;
		timeVel = 0f;
		timeTime = 0;
		time = 0;
		t = 1;
		i = 0;
		reversableObjects = new ArrayList<ReversableObject>();
		isForward = true;
		
		player = new Player(70, 150, 1f, null, 10);
		
		reversableObjects.addAll(LevelLoader.getLevel(1));
		reversableObjects.add(player);
		reversableObjects.addAll(impsCollection.getImps());
		
		//impsCollection.spawnNextImpPressDown(new ReversableMovement(new Vector2(245, 500)));
		gameObjects.addAll(reversableObjects);
		
		for (GameObject obj : reversableObjects) {
			for (GameObject obj2 : reversableObjects) {
				if(obj != obj2) {
					obj.collidableObjects.add(obj2);
				}
			}
		}
		player.collidableObjects.removeAll(impsCollection.getImps());
		//player.collidableObjects.remove(si);
		for (GameObject obj : impsCollection.getImps()) {
			obj.collidableObjects.remove(player);
		}
		
		
		
		
		/*ball.collidableObjects.add(ball1);
		ball.collidableObjects.add(but);
		ball.collidableObjects.add(rect);
		ball.collidableObjects.add(ball2);
		but.collidableObjects.add(ball);
		but.setTrigger(rect);
		*/
		
		
	}
	
	@Override
	public void update(float delta) {
		timeManagerUpdate(delta);
		
		
			

			
			
	
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
