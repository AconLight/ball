package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redartedgames.ball.consts.PhysicConsts;
import com.redartedgames.ball.objects.ColSpriteObject;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.ReversableObject;

public class Ball extends ReversableObject{

	protected float radius;
	private float m;
	
	public Ball(float x, float y, float radius, float m, BehaviorMode bMode, GameObject parent, int id) {
		super(x, y, parent, id);
		this.radius = radius;
		this.m = m;
		setHitbox(new Hitbox(((ReversableMovement) movement).getPositionX(), ((ReversableMovement) movement).getPositionY(), radius, bMode));
		if (bMode == BehaviorMode.dynamic) {
			gY = new BigDecimal("-200");
			((ReversableMovement) movement).setGY(new BigDecimal("" + PhysicConsts.G));
		}
	}
	
	public void render(ShapeRenderer sr, int priority) {

		sr.setColor(20/256f, 20/256f, 20/256f, 1f);
		sr.circle(position.x, position.y, radius);
	}
	
	@Override
	public void addSize(int a, int b) {
		radius += a;
	}
	
	@Override
	public void moveBig(int x, int y) {
		transform(x*25, y*25);
		Gdx.app.log("Game Object", "moveBig");
	}
	
	@Override
	public void moveSmall(int x, int y) {
		transform(x, y);
		Gdx.app.log("Game Object", "moveBig");
	}

	
	@Override
	public GameObject createCopy() {
		return new Ball(position.x, position.y, radius, m, hitbox.bMode, parent, 0);
	}
	
	@Override
	public String label() {
		// TODO Auto-generated method stub
		return "Ball " + id;
	}
	
	@Override
	public String newObjectToString() {
		return "new Ball(" + (int)position.x + ", " + (int)position.y + ", " + (int)radius + ", " + (int)m + ", BehaviorMode." + hitbox.bMode + ", " + parent + ", " + 0 + ")";
	}
}