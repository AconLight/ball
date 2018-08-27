package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	Texture tex = new Texture(Gdx.files.internal("graphic/shape/circle.png"));
	Freeze freeze;
	
	public Ball(float x, float y, float radius, float m, BehaviorMode bMode, GameObject parent, int id) {
		super(x, y, parent, id);
		priority = 2;
		this.radius = radius;
		this.m = m;
		setHitbox(new Hitbox(((ReversableMovement) movement).getPositionX(), ((ReversableMovement) movement).getPositionY(), radius-2, bMode));
		if (bMode == BehaviorMode.dynamic) {
			gY = new BigDecimal("-200");
			((ReversableMovement) movement).setGY(new BigDecimal("" + PhysicConsts.G));
		}
		freeze = new Freeze(0 , 0, 0, this, (int)(radius*1.4f), (int)(radius*1.4f));
	}
	
	public void generateFreeze() {
		freeze.generate();
	}
	
	public void setShouldBeStopped(boolean shouldBeStopped) {
		super.setShouldBeStopped(shouldBeStopped);
		generateFreeze();
	}
	
	public void render(SpriteBatch sr, int priority) {
		if (priority == 0) {
		sr.setColor(
				isForwardFac*forwardColor.r + (1-isForwardFac)*backwardColor.r, 
				isForwardFac*forwardColor.g + (1-isForwardFac)*backwardColor.g, 
				isForwardFac*forwardColor.b + (1-isForwardFac)*backwardColor.b, 
				isForwardFac*forwardColor.a + (1-isForwardFac)*backwardColor.a);
		sr.draw(tex, position.x-radius, position.y-radius, radius*2, radius*2);
		}
		else {
			freeze.render(sr, priority);
		}
	}
	
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
		freeze.updateBefore(delta, vx, vy);
	}
	
	@Override
	public void addSize(int a, int b) {
		radius += a;
	}
	
	@Override
	public void moveBig(int x, int y) {
		transform(x*25, y*25);
		//Gdx.app.log("Game Object", "moveBig");
	}
	
	@Override
	public void moveSmall(int x, int y) {
		transform(x, y);
		//Gdx.app.log("Game Object", "moveBig");
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