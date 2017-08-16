package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Movement;
import com.redartedgames.ball.objects.ReversableMovement;

public class StaticImp extends Imp{

	public StaticImp(float x, float y, float m, GameObject parent, int id) {
		super(x, y, 1, parent, id);
		type = STATIC_TYPE;
	}
	
	public void render(ShapeRenderer sr, int priority) {
		if (isSpawned) {
			sr.setColor(20/256f, 20/256f, 120/256f, 1f);
			sr.circle(position.x, position.y, radius);
		}
	}
	
	public void spawn(Movement movement) {
		super.spawn(movement);
	}
	
	public void activate() {
		super.activate();
		shouldBeStopped2 = false;
	}
	
	public void deactivate() {
		super.deactivate();
		shouldBeStopped2 = true;
	}	
	
	@Override
	public void collide(GameObject obj) {
		super.collide(obj);
		if(isSpawned && ((ReversableMovement) movement).getIsForward() &&c.isTrue) shouldBeStopped = true;
	}
}
