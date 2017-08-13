package com.redartedgames.ball.game;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.redartedgames.ball.myobjects.Ball;
import com.redartedgames.ball.myobjects.ButtonRect;
import com.redartedgames.ball.myobjects.LavaRect;
import com.redartedgames.ball.myobjects.Player;
import com.redartedgames.ball.myobjects.Rect;
import com.redartedgames.ball.myobjects.ShiftedRect;
import com.redartedgames.ball.objects.ColSpriteObject;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;
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
	public GameWorld() {
		super();
		time = 0;
		t = 1;
		i = 0;
		reversableObjects = new ArrayList<ReversableObject>();

		
		player = new Player(70, 600, 50, 1f, BehaviorMode.dynamic, null, 10);
		reversableObjects.add(player);
		reversableObjects.add(new LavaRect(465, 0, 350, 50, null, 0));
		reversableObjects.add(new Rect(100, 130, 200, 300, BehaviorMode.kinematic, null, 1));
		reversableObjects.add(new Rect(100, 600, 300, 400, BehaviorMode.kinematic, null, 2));
		reversableObjects.add(new Rect(250, 0, 100, 100, BehaviorMode.kinematic, null, 3));
		reversableObjects.add(new Rect(980, 0, 700, 100, BehaviorMode.kinematic, null, 4));
		reversableObjects.add(new Rect(730, 75, 200, 50, BehaviorMode.kinematic, null, 5));
		reversableObjects.add(new Rect(640, 720, 1480, 100, BehaviorMode.kinematic, null, 6));
		reversableObjects.add(new Rect(600, 250, 100, 100, BehaviorMode.kinematic, null, 7));
		reversableObjects.add(new Rect(1180, 250, 200, 400, BehaviorMode.kinematic, null, 8));
		reversableObjects.add(new Rect(860, 600, 50, 150, BehaviorMode.dynamic, null, 8));
		reversableObjects.add(new Ball(670, 600, 50, 1f, BehaviorMode.dynamic, null, 9));

		gameObjects.addAll(reversableObjects);
		
		for (GameObject obj : gameObjects) {
			for (GameObject obj2 : gameObjects) {
				if(obj != obj2) {
					obj.collidableObjects.add(obj2);
				}
			}
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
		if (!isPaused) super.update(delta);		
	}
}
