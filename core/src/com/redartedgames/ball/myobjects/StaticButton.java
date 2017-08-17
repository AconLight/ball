package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.Gdx;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class StaticButton extends Rect{

	private MovingRect movingRect;
	
	private boolean isOn;
	
	public StaticButton(float x, float y, float width, float height, MovingRect movingRect, GameObject parent, int id) {
		super(x, y, width, height, BehaviorMode.kinematic, parent, id);
		this.movingRect = movingRect;
		isOn = false;
	}
	
	public void applyPhysicsToAcceleration() {
		isOn = false;
		super.applyPhysicsToAcceleration();
		movingRect.start(isOn);
		Gdx.app.log("StaticButton", "col tru" + isOn);
	}

	@Override
	public void collide(GameObject obj) {
		super.collide(obj);
		if(c.isTrue) {
			isOn = true;
			//Gdx.app.log("StaticButton", "col tru");
		}
	}
	
	
}
