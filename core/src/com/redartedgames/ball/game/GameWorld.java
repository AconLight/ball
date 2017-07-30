package com.redartedgames.ball.game;

import java.math.BigDecimal;

import com.redartedgames.ball.myobjects.Ball;
import com.redartedgames.ball.myobjects.ButtonRect;
import com.redartedgames.ball.myobjects.Rect;
import com.redartedgames.ball.myobjects.ShiftedRect;
import com.redartedgames.ball.objects.ColSpriteObject;
import com.redartedgames.ball.objects.Hitbox;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;
import com.redartedgames.ball.screen.World;

public class GameWorld extends World{

	public ButtonRect but;
	public Ball ball, ball1, ball2;
	public ShiftedRect rect;
	private float time;
	public int t, i;
	public GameWorld() {
		super();
		time = 0;
		t = 1;
		i = 0;

		ball1 = new Ball(970 , 100, 50, 1f, null, 0);
		addGameObject(ball1);
		
		but = new ButtonRect(880 , 100, null, 0);
		addGameObject(but);
		
		ball2 = new Ball(250 , 100, 50, 1f, null, 0);
		addGameObject(ball2);
		
		rect = new ShiftedRect(400 , 300, 250, 150, 400, 300, 600, 150, null, 0);
		addGameObject(rect);
		
		
		ball = new Ball(450 , 550, 50, 1f, null, 0);
		ball.velocityX = new BigDecimal("60");
		ball.gY = new BigDecimal("-200");
		ball.gX = new BigDecimal("-20");

		addGameObject(ball);
		
		ball.collidableObjects.add(ball1);
		ball.collidableObjects.add(but);
		ball.collidableObjects.add(rect);
		ball.collidableObjects.add(ball2);
		but.collidableObjects.add(ball);
		but.setTrigger(rect);
		
		
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);		
	}
}
