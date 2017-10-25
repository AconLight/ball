package com.redartedgames.ball.game;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.myobjects.Ball;
import com.redartedgames.ball.myobjects.ButtonRect;
import com.redartedgames.ball.myobjects.ImpsCollection;
import com.redartedgames.ball.myobjects.LavaRect;
import com.redartedgames.ball.myobjects.MovingRect;
import com.redartedgames.ball.myobjects.Player;
import com.redartedgames.ball.myobjects.Rect;
import com.redartedgames.ball.myobjects.ShiftedRect;
import com.redartedgames.ball.myobjects.StaticButton;
import com.redartedgames.ball.objects.ColSpriteObject;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.screen.World;

public class GameWorld extends World{

	public ButtonRect but;
	public Ball ball, ball1, ball2;
	public ShiftedRect rect;
	private float time;
	public int t, i;
	public boolean isPaused;
	public Player player;
	public ArrayList <ReversableObject> reversableObjects;
	private boolean isForward;
	public ImpsCollection impsCollection;
	 
	public float timeTime, timeBar, timeVel, timeAcc;
	
	public GameWorld() {
		super();
		impsCollection = new ImpsCollection();
		//impsCollection.addStaticImp();
		//impsCollection.addStaticImp();
		//impsCollection.addActiveImp();
		
		impsCollection.addStaticImp();
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
		
		player = new Player(70, 350, 1f, null, 10);
		
		reversableObjects.add(new LavaRect(465, 0, 350, 50, null, 0));
		reversableObjects.add(new Rect(100, 130, 200, 300, BehaviorMode.kinematic, null, 1));
		reversableObjects.add(new Rect(50, 600, 400, 300, BehaviorMode.kinematic, null, 2));
		reversableObjects.add(new Rect(250, 0, 100, 100, BehaviorMode.kinematic, null, 3));
		reversableObjects.add(new Rect(480, 0, 1700, 100, BehaviorMode.kinematic, null, 4));
		//reversableObjects.add(new Rect(730, 75, 200, 50, BehaviorMode.kinematic, null, 5));
		reversableObjects.add(new Rect(640, 720, 1480, 100, BehaviorMode.kinematic, null, 6));
		
		reversableObjects.add(new Rect(130 + 75, 460, 350, 20, BehaviorMode.kinematic, null, 6));
		reversableObjects.add(new Rect(290 + 95, 490, 40, 80, BehaviorMode.kinematic, null, 6));
		//reversableObjects.add(new Rect(500, 350, 100, 100, BehaviorMode.kinematic, null, 7));
		MovingRect r = new MovingRect(1050, 300, 1200, 300, 80, 80, true, BehaviorMode.kinematic, null, 7);
		r.setV(50f, 0);
		
		MovingRect r2 = new MovingRect(290 + 95, 690, 290 + 95, 790, 40, 170, true, BehaviorMode.kinematic, null, 7);
		r2.setV(0, 50f);
		
		//StaticButton b = new StaticButton(250, 100, 40, 20, r2, null, 9);
		StaticButton b = new StaticButton(300, 510, 40, 20, r, null, 9);
		//reversableObjects.add(new Ball(250, 150, 15, 1f, BehaviorMode.dynamic, null, 9));
		StaticButton b2 = new StaticButton(700, 40, 40, 20, r, null, 9);
		reversableObjects.add(r);
		reversableObjects.add(b);
		//reversableObjects.add(r2);
		//reversableObjects.add(b2);
		reversableObjects.add(new Rect(1180, 250, 200, 400, BehaviorMode.kinematic, null, 8));
		//reversableObjects.add(new Rect(860, 600, 50, 150, BehaviorMode.dynamic, null, 8));
		//reversableObjects.add(new Rect(330, 200, 50, 150, BehaviorMode.dynamic, null, 8));
		//reversableObjects.add(new Ball(670, 600, 50, 1f, BehaviorMode.dynamic, null, 9));
		
		reversableObjects.add(new Ball(670, 600, 45, 1f, BehaviorMode.dynamic, null, 9));
		reversableObjects.add(new Ball(770, 600, 45, 1f, BehaviorMode.dynamic, null, 9));
		//reversableObjects.add(new Ball(630, 400, 45, 1f, BehaviorMode.kinematic, null, 9));
		
		//reversableObjects.add(new Ball(650, -400, 600, 1f, BehaviorMode.kinematic, null, 9));
		
		//reversableObjects.add(new Ball(650 + 600, 100, 300, 1f, BehaviorMode.kinematic, null, 9));
		

		reversableObjects.add(new Rect(550, 250, 180, 80, BehaviorMode.kinematic, null, 6));
		
		reversableObjects.add(player);
		reversableObjects.addAll(impsCollection.getImps());
		
		//impsCollection.spawnNextImpPressDown(new ReversableMovement(new Vector2(245, 500)));
		gameObjects.addAll(reversableObjects);
		
		for (GameObject obj : gameObjects) {
			for (GameObject obj2 : gameObjects) {
				if(obj != obj2) {
					obj.collidableObjects.add(obj2);
				}
			}
		}
		player.collidableObjects.removeAll(impsCollection.getImps());
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
		for(ReversableObject r : reversableObjects) {
			r.setIsForward(isForward);
		}
		super.update(0.01f);
			

			
			
	
	}
	
	public void setIsForward(boolean isForward) {
		this.isForward = isForward;
	}
}
