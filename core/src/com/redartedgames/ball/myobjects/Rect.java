package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.objects.ColSpriteObject;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class Rect extends ColSpriteObject{

	protected float width;
	protected float height;
	
	public Rect(float x, float y, float width, float height, GameObject parent, int id) {
		super(x, y, parent, id);
		this.width = width;
		this.height = height;
		setHitbox(new Hitbox(positionX, positionY, width, height, BehaviorMode.kinematic));
	}
	
	
	public void render(ShapeRenderer sr, int priority) {
		sr.setColor(150/256f, 150/256f, 150/256f, 0.9f);
		sr.rect((position.x - width/2+0.5f), position.y - height/2+0.5f, width+0.5f, height+0.5f);
	}
}