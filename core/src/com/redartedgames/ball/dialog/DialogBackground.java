package com.redartedgames.ball.dialog;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.myobjects.NormalRect;
import com.redartedgames.ball.objects.GameObject;

public class DialogBackground extends GameObject{

	ArrayList<NormalRect> rects;
	int width, height;
	
	
	public DialogBackground(float x, float y, int id, GameObject parent, int width, int height) {
		super(x, y, id, parent);
		rects = new ArrayList<>();
		this.width = width;
		this.height = height;
		generateRects();
	}
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
		position.set(parent.parent.getPosition().x, parent.parent.getPosition().y + 200);
		for(NormalRect rect: rects) {
			rect.getPosition().set(position);
		}
	}
	
	private void generateRects() {
		rects.add(new NormalRect(position.x, position.y, 0, this, width, height));
	}
	
	public void render(SpriteBatch batch, int priority) {
		batch.setColor(0.3f, 0.3f, 0.3f, 0.6f);
		for(NormalRect rect: rects)
			rect.render(batch, priority);
	}

}
