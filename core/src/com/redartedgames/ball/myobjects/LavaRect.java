package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class LavaRect extends Rect{

	public LavaRect(float x, float y, float width, float height, GameObject parent, int id) {
		super(x, y, width, height, BehaviorMode.kinematic, parent, id);
		// TODO Auto-generated constructor stub
	}
	
	public void render(ShapeRenderer sr, int priority) {
		sr.setColor(240/256f, 40/256f, 40/256f, 1f);
		sr.rect((position.x - width/2+0.5f), position.y - height/2+0.5f, width+0.5f, height+0.5f);
	}

}
