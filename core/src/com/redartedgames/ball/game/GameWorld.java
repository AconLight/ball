package com.redartedgames.ball.game;

import java.math.BigDecimal;

import com.redartedgames.ball.myobjects.Ball;
import com.redartedgames.ball.myobjects.Rect;
import com.redartedgames.ball.objects.ColSpriteObject;
import com.redartedgames.ball.objects.Hitbox;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;
import com.redartedgames.ball.screen.World;

public class GameWorld extends World{

	public Ball ball, ball1, ball2;
	public Rect rect, rect2;
	private float time;
	public int t, i;
	public GameWorld() {
		super();
		time = 0;
		t = 1;
		i = 0;

		ball1 = new Ball(560 , 100, 50, 1f, null, 0);
		addGameObject(ball1);
		
		ball2 = new Ball(700 , 100, 50, 1f, null, 0);
		addGameObject(ball2);
		
		rect = new Rect(399 , 300, 250, 150, null, 0);
		addGameObject(rect);
		
		rect2 = new Rect(300 , 550, 400, 50, null, 0);
		rect2.gY = new BigDecimal("-200");
		rect2.gX = new BigDecimal("-20");
		addGameObject(rect2);
		
		ball = new Ball(630 , 300 + 150, 50, 1f, null, 0);
		ball.velocityX = new BigDecimal("250");
		ball.gY = new BigDecimal("-200");

		addGameObject(ball);
		
		ball.collidableObjects.add(ball1);
		ball.collidableObjects.add(rect);
		ball.collidableObjects.add(ball2);
		
		rect2.collidableObjects.add(rect);
		
		
	}
	
	@Override
	public void update(float delta) {
		delta*=t;
		super.update(delta);		
	}
}
