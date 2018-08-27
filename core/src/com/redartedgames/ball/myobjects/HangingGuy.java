package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.SpriteObject;

public class HangingGuy extends ReversableObject{

	SpriteObject sprite;
	Player player;
	float k = 100;
	float dx = 0, dy = 110;
	boolean isAttached;
	Vector2 tmp;
	
	public HangingGuy(float x, float y, int id, GameObject parent) {
		super(x, y, parent, id);
		isAttached = true;
		sprite = new SpriteObject(x, y, this, 0);
		sprite.addTexture("graphic/hungingguy.png");
		gameObjects.add(sprite);
		sprite.sclX = 1.4f;
		sprite.sclY = 1.4f;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
		
	}

	
	public void applyPhysicsToAcceleration() {
		super.applyPhysicsToAcceleration();
		if(isAttached) {
			
			tmp = new Vector2((position.x + dx - player.getPosition().x)*k, (position.y + dy - player.getPosition().y)*k);
			((ReversableMovement) (player.getMovement())).addCollisionAcc(new BigDecimal("" + tmp.x), new BigDecimal("" + tmp.y));
			
			if (tmp.x*tmp.x + tmp.y*tmp.y > 15*15*k*k) {
				isAttached = false;
			}
		}
		
		
		
	}
	
	public Vector2 getHeadPos() {
		return new Vector2(position.x + dx, position.y + dy);
	}
	
	public void render(SpriteBatch batch, int priority) {
		batch.setColor(20/256f, 20/256f, 20/256f, 1);
		batch.draw(GameObject.dotTex, position.x + dx, position.y + dy - 80, 4, 1000);
		sprite.render(batch, priority);
	}
	

}
