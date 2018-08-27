package com.redartedgames.ball.myobjects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class LavaRect extends Rect{
	
	Player player;
	boolean isFrozen;
	Float[] freeze;
	float freezeStart;
	Random rand;
	ArrayList<StaticImp> imps;

	public LavaRect(float x, float y, float width, float height, GameObject parent, int id) {
		super(x, y, width, height, BehaviorMode.kinematic, parent, id);
		// TODO Auto-generated constructor stub
		priority = 0;
		isFrozen = false;
		freeze = new Float[(int) (width/4)];
		int idx;
		for (int i = 0; i < freeze.length; i++) {
			idx = i*4;
			freeze[i] = -5f;
		}
		rand = new Random();
		imps = new ArrayList<>();
	}
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
		
		if (isFrozen) {
			int idx = 0;
			for (int i = 0; i < freeze.length; i++) {
				idx = i*4;
				freeze[i] +=(float) ( 10*(20 + rand.nextInt(1000))*delta/  (  (    Math.abs(freezeStart - idx) + 40)*(Math.sin((freezeStart - idx+rand.nextInt(1000)/10f)/20)+2f)  ) );
			}
		}
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	public void setStaticImps(ImpsCollection imps) {
		for (Imp imp: imps.getImps()) {
			if (imp.type == Imp.STATIC_TYPE) {
				this.imps.add((StaticImp) imp);
			}
		}
	}
	
	public void render(SpriteBatch sr, int priority) {
		Gdx.app.log("LavaRect", priority + ", " + this.priority);
		sr.setColor(114/256f, 19/256f, 0/256f, 1f);
		sr.draw(dotTex, (position.x - width/2+0.5f), position.y - height/2+0.5f, width+0.5f, height+0.5f);
		if (isFrozen) {
			sr.setColor(0/256f, 150/256f, 160/256f, 1f);
			int idx;
			for (int i = 0; i < freeze.length; i++) {
				idx = i*4;
				if (freeze[i] > 0)
					sr.draw(dotTex, (position.x - width/2+0.5f + idx), position.y + height/2+0.5f - freeze[i], 4, freeze[i]);
				idx += 4;
			}
		}
	}
	
	public void collide(GameObject obj) {
		super.collide(obj);
		if(obj == player && c.isTrue) {
			player.isAlive = false;
		}
		for (Imp imp: imps) {
			if (obj == imp && c.isTrue && imp.isSpawned) {
				isFrozen = true;
				freezeStart = imp.getPosition().x - position.x + width/2;
			}
		}
	}
	
	@Override
	public GameObject createCopy() {
		return new LavaRect(position.x, position.y, width, height, parent, 0);
	}
	
	@Override
	public String label() {
		// TODO Auto-generated method stub
		return "LavaRect " + id;
	}
	
	@Override
	public String newObjectToString() {
		return "new LavaRect(" +(int)position.x + ", " + (int)position.y + ", " + (int)width + ", " + (int)height + ", " + parent + ", " + 0 + ")";
	}
}
