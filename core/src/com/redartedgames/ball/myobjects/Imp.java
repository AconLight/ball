package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Movement;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.ReversableObject;

public class Imp extends Player{
	
	public boolean isSpawned, isUsed;
	
	public boolean justToRender;
	
	protected MovesData playerMovesData;
	
	public int type;
	
	public static int STATIC_TYPE = 1;
	
	public static int ACTIVE_TYPE = 2;
	
	public Imp(float x, float y, float m, GameObject parent, int id) {
		super(x, y, 1, parent, id);
		isSpawned = false;
		isUsed = false;
		justToRender = false;
		type = 0;
		playerMovesData = new MovesData();
	} 
	
	public void spawn(Movement movement) {
		((ReversableMovement)this.movement).set((ReversableMovement) movement);
		this.movement.getPosition().set(((ReversableMovement)this.movement).getPositionX().floatValue(), 
				((ReversableMovement)this.movement).getPositionY().floatValue());
		//Gdx.app.log("Imp", message);
		isSpawned = true;
	}
	
	public void render(SpriteBatch sr, int priority) {
		if (isSpawned || justToRender) {
			super.render(sr, priority);
			//sr.setColor(0/256f, 162/256f, 156/256f, 1f);
			//sr.draw(tex, position.x-radius, position.y-radius, radius*2, radius*2);
		}
	}
	
	public void render(SpriteBatch sr, int priority, float x, float y) {
			Vector2 old = new Vector2(position);
			position.set(x, y);
			super.render(sr, priority);
			position.set(old);
			//sr.setColor(0/256f, 162/256f, 156/256f, 1f);
			//sr.draw(tex, position.x-radius, position.y-radius, radius*2, radius*2);
	}
	
	public void spawn() {
		isSpawned = true;
	}
	
	public void activate() {
		
	}
	
	public void deactivate() {
		
	}	
	
	@Override
	public void collide(GameObject obj) {
		if (isSpawned) super.collide(obj);
	}

}
