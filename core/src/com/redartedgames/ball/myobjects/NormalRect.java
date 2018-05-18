package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.objects.GameObject;

public class NormalRect extends GameObject{

	Vector2 size;
	public NormalRect(float x, float y, int id, GameObject parent, int width, int height) {
		super(x, y, id, parent);
		size = new Vector2(width, height);
	}
	
	public void render(SpriteBatch batch, int priority) {
		batch.draw(GameObject.dotTex, position.x - size.x/2, position.y-size.y/2, size.x, size.y);
	}
	
}
