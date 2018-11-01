package com.redartedgames.ball.myobjects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.objects.GameObject;

public class Bush extends GameObject{
	int thickness = 3;
	float sclV = 0.995f;
	float playerR = 150;
	boolean shouldGrow = true;
	Player player;
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	Random rand;

	class BushElement {
		float sizeScl = 1;
		public Vector2 position1, position2, size;
		
		ArrayList<BushElement> nexts;
		int a;
		public BushElement(Vector2 position1, float r, int nextsNumber, int nodesLeft) {
			this.position1 = new Vector2(position1);
			int dx = rand.nextInt((int) r) - (int)(r/2);
			int dy = rand.nextInt((int) r);
			a = rand.nextInt(2);
			size = new Vector2(dx*a, dy*(1-a));
			position2 = new Vector2(position1.x + size.x, position1.y + size.y);
			nexts = new ArrayList<BushElement>();
			if (nodesLeft > 0)
			for (int i = 0; i < nextsNumber; i++) {
				nexts.add(new BushElement(position2, r, nextsNumber, nodesLeft-1));
			}
		}
		
		public void setSizeScl(float scl) {
			sizeScl = scl;
			for (BushElement e: nexts) {
				e.setSizeScl(scl*scl);
			}
		}
		
		public void render(SpriteBatch batch, Vector2 position) {
			batch.draw(GameObject.dotTex, position.x - (1-a)*thickness/2f, position.y - (a)*thickness/2f, size.x*sizeScl + (1-a)*thickness, size.y*sizeScl + a*thickness);
			for (BushElement e: nexts) {
				e.render(batch, new Vector2(position.x + size.x*sizeScl, position.y + size.y*sizeScl));
			}
		}
	}
	
	BushElement root;
	float scl = 1;
	public Bush(float x, float y, int id, GameObject parent, float r, Random rand) {
		super(x, y, id, parent);
		this.rand = rand;
		root = new BushElement(new Vector2(x, y), r, 2, 4);
	}
	
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
		if (player != null)
		if ((player.getPosition().y - root.position1.y)*(player.getPosition().y - root.position1.y) + (player.getPosition().x - root.position1.x)*(player.getPosition().x - root.position1.x) > playerR*playerR) {
			shouldGrow = true;
		}
		else {
			shouldGrow = false;
			
		}
		if (shouldGrow) {
			scl += 0.0005f;
			if (scl > 1) scl = 1;
		}
		else {
			scl *= sclV;
		}
		
		root.setSizeScl(scl);
	}
	
	public void render(SpriteBatch batch, int priority) {
		root.render(batch, root.position1);
	}
	
	

}
