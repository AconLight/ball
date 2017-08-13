package com.redartedgames.ball.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class ColSpriteObject extends SpriteObject{

	protected CollisionHandle c;
	
	public ColSpriteObject(float x, float y, GameObject parent, int id) {
		super(x, y, parent, id);
	}

	@Override
	public void collide(GameObject obj) {
		super.collide(obj);
		c = hitbox.checkCol(obj.getHitbox());
		collisionAccX = collisionAccX.add(c.disX);
		collisionAccY = collisionAccY.add(c.disY);
		//Gdx.app.log("ColSpriteObject", "collide - col: " + collisionAccY );
		movement.addCollisionAcc(new Vector2(c.disX.floatValue(), c.disY.floatValue()));
	}
}
