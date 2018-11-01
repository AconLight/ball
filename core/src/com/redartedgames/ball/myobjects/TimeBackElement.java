package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.consts.PhysicConsts;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.SpriteObject;

public class TimeBackElement extends ReversableObject {
	
	SpriteObject elementSprite;
	
	private ReversableObject alfa;
	
	TimeBackItem parentBack;
	
	Vector2 velocity;
	
	float spin;

	public TimeBackElement(float x, float y, String path, TimeBackItem parent) {
		super(x, y, parent, 0);
		parentBack = parent;
		elementSprite = new SpriteObject(x, y, null, 0);
		elementSprite.addTexture(path);
		addSprite(elementSprite);
		alfa = new ReversableObject(0, 0, null, 0);
		((ReversableMovement)movement).setVelocityX(new BigDecimal("" + 500));
		((ReversableMovement)movement).setVelocityY(new BigDecimal("" + 500));
		((ReversableMovement)alfa.getMovement()).setVelocityX(new BigDecimal("" + 40));
		Gdx.app.log("TimeBackElement", "ssxzcxz: " + ((ReversableMovement)alfa.getMovement()).getVelocityX().floatValue());
		Gdx.app.log("TimeBackElement", "position update0: " + ((ReversableMovement)alfa.getMovement()).getPositionX().floatValue());
		((ReversableMovement)movement).setDrag(0.2f, 0.2f);
	}
	
	public TimeBackElement(SpriteObject object, TimeBackItem parent) {
		super(object.getPosition().x, object.getPosition().y, parent, 0);
		parentBack = parent;
		elementSprite = object;
		addSprite(elementSprite);
		alfa = new ReversableObject(0, 0, null, 0);
		((ReversableMovement)movement).setVelocityX(new BigDecimal("0"));
		((ReversableMovement)movement).setVelocityY(new BigDecimal("0"));
		((ReversableMovement)alfa.getMovement()).setVelocityX(new BigDecimal("0"));
		Gdx.app.log("TimeBackElement", "ssxzcxz: " + ((ReversableMovement)alfa.getMovement()).getVelocityX().floatValue());
		Gdx.app.log("TimeBackElement", "position update0: " + ((ReversableMovement)alfa.getMovement()).getPositionX().floatValue());
		((ReversableMovement)movement).setDrag(0.2f, 0.2f);
		((ReversableMovement)movement).setGY(new BigDecimal("" + PhysicConsts.G/20));
	}
	
	public void launch(Vector2 velocity, float spin) {
		((ReversableMovement)movement).setVelocityX(new BigDecimal("" + velocity.x));
		((ReversableMovement)movement).setVelocityY(new BigDecimal("" + velocity.y));
		((ReversableMovement)alfa.getMovement()).setVelocityX(new BigDecimal("" + spin));
		this.velocity = velocity;
		this.spin = spin;
	}
	
	public void updateBefore(float delta, float vx, float vy) {
		alfa.setIsForward(((ReversableMovement)movement).isForward);
		elementSprite.setPosition(new Vector2(((ReversableMovement)movement).getPositionX().floatValue(), ((ReversableMovement)movement).getPositionY().floatValue()));
		alfa.updateBefore(delta, vx, vy);
		elementSprite.alfa = ((ReversableMovement)alfa.getMovement()).getPositionX().floatValue();
		Gdx.app.log("TimeBackElement", "position update: " + ((ReversableMovement)alfa.getMovement()).getPositionX().floatValue());
		Gdx.app.log("TimeBackElement", "vel update: " + ((ReversableMovement)alfa.getMovement()).getVelocityX().floatValue());

		super.updateBefore(delta, vx, vy);
	}
	
	public void applyPhysicsToAcceleration() {
		Gdx.app.log("TimeBackElement", "position apply: " + ((ReversableMovement)alfa.getMovement()).getPositionX().floatValue());
		if (!isStopped) {
			super.applyPhysicsToAcceleration();
			alfa.applyPhysicsToAcceleration();
		}
		Gdx.app.log("TimeBackElement", "parent isStopped: " + parent.getMovement().isStopped);
	}
	
	public void render(SpriteBatch batch, int priority) {
		Gdx.app.log("TimeBackElement", "position render: " + ((ReversableMovement)alfa.getMovement()).getPositionX().floatValue());
		super.render(batch, priority);
		elementSprite.render(batch, priority);
	}
	
	public void updateAfter(float delta, float vx, float vy) {
		Gdx.app.log("TimeBackElement", "position after: " + ((ReversableMovement)alfa.getMovement()).getPositionX().floatValue());
		alfa.updateAfter(delta, vx, vy);
		super.updateAfter(delta, vx, vy);
		Gdx.app.log("TimeBackElement", "frame: " + ((ReversableMovement)movement).framesI);
		if(((ReversableMovement)movement).framesI <= -1 && !((ReversableMovement)movement).isForward) {
			//(movement).setIsStopped(true);
			//alfa.getMovement().setIsStopped(true);
			
			parentBack.stop();
		}
		
	}
	
	

}
