package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class ActiveImp extends Imp{

	public ActiveImp(float x, float y, float radius, float m, BehaviorMode bMode, GameObject parent, int id) {
		super(x, y, m, parent, id);
		type = ACTIVE_TYPE;
	}
	
	public void render(ShapeRenderer sr, int priority) {
		if(isActive) {
			sr.setColor(20/256f, 120/256f, 20/256f, 1f);
			sr.circle(position.x, position.y, radius);
		}
	}

}
