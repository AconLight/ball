package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redartedgames.ball.objects.ColSpriteObject;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.ReversableObject;

public class Ball extends ReversableObject{

	private float radius;
	private float m;
	
	public Ball(float x, float y, float radius, float m, BehaviorMode bMode, GameObject parent, int id) {
		super(x, y, parent, id);
		this.radius = radius;
		this.m = m;
		setHitbox(new Hitbox(((ReversableMovement) movement).getPositionX(), ((ReversableMovement) movement).getPositionY(), radius, bMode));
		if (bMode == BehaviorMode.dynamic) {
			gY = new BigDecimal("-200");
			((ReversableMovement) movement).setGY(new BigDecimal("-600"));
		}
	}
	
	public void render(ShapeRenderer sr, int priority) {

		if (hitbox.bMode == BehaviorMode.kinematic) {
			sr.setColor(20/256f, 20/256f, 20/256f, 1f);
		}
		else if (hitbox.bMode == BehaviorMode.dynamic) {
			sr.setColor(60/256f, 60/256f, 60/256f, 1f);
		}
		else {
			sr.setColor(100/256f, 100/256f, 100/256f, 1f);
		}
		
		sr.circle(position.x, position.y, radius);
		
		
		if (isMarked) {
			sr.setColor(10/256f, 10/256f, 200/256f, 1f);
			sr.circle(position.x, position.y, 5);
		}
	}
}