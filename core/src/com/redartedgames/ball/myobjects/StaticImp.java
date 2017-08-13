package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redartedgames.ball.objects.GameObject;

public class StaticImp extends Imp{

	public StaticImp(float x, float y, float m, GameObject parent, int id) {
		super(x, y, 1, parent, id);
		type = STATIC_TYPE;
	}
	
	public void render(ShapeRenderer sr, int priority) {
		if (isActive) {
			sr.setColor(20/256f, 20/256f, 120/256f, 1f);
			sr.circle(position.x, position.y, radius);
		}
	}
}
