package com.redartedgames.ball.dialog;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.myobjects.NormalRect;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class DialogBackground extends GameObject{

	ArrayList<NormalRect> rects;
	int width, height;
	public boolean isOn;
	
	
	public DialogBackground(float x, float y, int id, GameObject parent, int width, int height) {
		super(x, y, id, parent);
		rects = new ArrayList<>();
		this.width = width;
		this.height = height;
		generateRects();
		isOn = true;
	}
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
		for(NormalRect rect: rects) {
			rect.getPosition().set(position);
		}
	}
	
	private void generateRects() {
		rects.add(new NormalRect(position.x, position.y, 0, this, width, height));
	}
	
	public void render(SpriteBatch batch, int priority) {
		if (isOn) {
		batch.setColor(0.1f, 0.1f, 0.1f, 0.7f);
		for(NormalRect rect: rects)
			rect.render(batch, priority);
		
		batch.setColor(0.1f, 0.1f, 0.1f, 0.9f);
		batch.draw(GameObject.dotTex, position.x - width/2 + 10, position.y - height/2, width-20, 10); 
		batch.draw(GameObject.dotTex, position.x - width/2 + 10, position.y + height/2 - 10, width-20, 10); 
		batch.draw(GameObject.dotTex, position.x - width/2, position.y - height/2, 10, height/4); 
		batch.draw(GameObject.dotTex, position.x + width/2 - 10, position.y - height/2, 10, height/4); 
		batch.draw(GameObject.dotTex, position.x - width/2, position.y + height/2-height/4, 10, height/4); 
		batch.draw(GameObject.dotTex, position.x + width/2 - 10, position.y + height/2-height/4, 10, height/4); 
		}
	}

}
