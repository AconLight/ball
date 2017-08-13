package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.objects.ColSpriteObject;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class Rect extends ReversableObject{

	protected float width;
	protected float height;
	
	public Rect(float x, float y, float width, float height, BehaviorMode bMode, GameObject parent, int id) {
		super(x, y, parent, id);
		this.width = width;
		this.height = height;
		setHitbox(new Hitbox(positionX, positionY, width, height, bMode));
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
		
		sr.rect((position.x - width/2+0.5f), position.y - height/2+0.5f, width+0.5f, height+0.5f);
		
		if (isMarked) {
			sr.setColor(10/256f, 10/256f, 200/256f, 1f);
			sr.circle(position.x, position.y, 5);
		}
	}
}