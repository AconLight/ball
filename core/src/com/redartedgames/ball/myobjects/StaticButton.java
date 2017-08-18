package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
	
	public void render(ShapeRenderer sr, int priority) {

		sr.setColor(50/256f, 200/256f, 50/256f, 1f);	
		sr.rect((position.x - width/2+0.5f), position.y - height/2+0.5f, width+0.5f, height+0.5f);
		
		
	}
	
	
}
