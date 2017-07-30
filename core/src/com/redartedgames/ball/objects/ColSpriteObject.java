package com.redartedgames.ball.objects;

public class ColSpriteObject extends SpriteObject{

	public ColSpriteObject(float x, float y, GameObject parent, int id) {
		super(x, y, parent, id);
	}

	@Override
	public void collide(GameObject obj) {
		super.collide(obj);
		CollisionHandle c = hitbox.checkCol(obj.getHitbox());
		collisionAccX = collisionAccX.add(c.disX);
		collisionAccY = collisionAccY.add(c.disY);
	}
}
