package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redartedgames.ball.objects.ColSpriteObject;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class Ball extends ColSpriteObject{

	private float radius;
	private float m;
	
	public Ball(float x, float y, float radius, float m, GameObject parent, int id) {
		super(x, y, parent, id);
		this.radius = radius;
		this.m = m;
		setHitbox(new Hitbox(positionX, positionY, radius, BehaviorMode.kinematic));
	}
	
	public void applyPhysicsToAcceleration() {
		super.applyPhysicsToAcceleration();
		//collisionAccX = collisionAccX.subtract(velocityX.multiply(new BigDecimal("" + 0.1)));
		//collisionAccY = collisionAccY.subtract(velocityY.multiply(new BigDecimal("" + 0.1)));
	}
	
	public void render(ShapeRenderer sr, int priority) {
		sr.setColor(110/256f, 110/256f, 110/256f, 1f); //227/256f, 135/256f, 22/256f, 0.9f);
		sr.circle(position.x, position.y, radius);
	}
}