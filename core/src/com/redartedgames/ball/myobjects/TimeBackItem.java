package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.SpriteObject;

public class TimeBackItem extends ReversableObject{
	int time = 100;
	ArrayList<TimeBackElement> elements;
	Random rand = new Random();
	boolean stop;
	int loadEtap = 0;
	
	public TimeBackItem(float x, float y, GameObject parent, int id) {
		super(x, y, parent, id);
		elements =  new ArrayList<>();
		Gdx.app.log("TimeBackItem", "constructor");
		stop = false;
		fadeAway = false;
	}
	
	public boolean fadeAway = false;
	
	public void fadeAway(float x, float y) {
		for (TimeBackElement el: elements) {
			((ReversableMovement) el.getMovement()).setVelocityX(new BigDecimal("" + (x + rand.nextInt(101) - 50)));
			((ReversableMovement) el.getMovement()).setVelocityY(new BigDecimal("" + (y + rand.nextInt(101) - 50)));
			el.updateBefore(0.01f, 0, 0);
			el.applyPhysicsToAcceleration();
			el.updateAfter(0.01f, 0, 0);
		}
		stop = false;
		fadeAway = true;
	}
	
	public void addElement(float x, float y, String path, Vector2 velocity, float spin) {
		TimeBackElement el = new TimeBackElement(x, y, path, this);
		elements.add(el);
		el.launch(velocity, spin);
		loadEtap = 0;
		for (int i = 0; i < time; i++) {
			el.updateBefore(0.01f, 0, 0);
			el.applyPhysicsToAcceleration();
			el.updateAfter(0.01f, 0, 0);
		}
		el.setIsForward(false);

	}
	
	public void addElement(TimeBackElement el, Vector2 velocity, float spin) {
		elements.add(el);
		el.launch(velocity, spin);
		for (int i = 0; i < time; i++) {
			el.updateBefore(0.01f, 0, 0);
			el.applyPhysicsToAcceleration();
			el.updateAfter(0.01f, 0, 0);
		}
		el.setIsForward(false);

		
	}
	
	public void load(String path, int dx, int dy) {
		Vector2 v = new Vector2();
		addElement(position.x + dx, position.y + dy, path, v.add(new Vector2(rand.nextInt(4000) + 1000 , rand.nextInt(100)+1000)), rand.nextInt(100)-50);
	}
	
	public void load(TimeBackElement el, float x, float y) {
		Vector2 v = new Vector2(x, y);
		addElement(el, v, rand.nextInt(10)+45);
	}
	
	int asd = 0;
	public void updateBefore(float delta, float vx, float vy) {
		if (loadEtap == 1) loadEtap = 2;
		if(!stop) {
			super.updateBefore(delta, vx, vy);
			for (TimeBackElement e: elements) {
				e.updateBefore(delta, vx, vy);
			}
		}
	}
	
	public void applyPhysicsToAcceleration() {
		if(!stop) {
			super.applyPhysicsToAcceleration();
			for (TimeBackElement e: elements) {
				e.applyPhysicsToAcceleration();
			}
		}
	}
	
	public void updateAfter(float delta, float vx, float vy) {
		//Gdx.app.log("TimeBackItem", "isStopped: " + movement.isStopped);
		if(!stop) {
			super.updateAfter(delta, vx, vy);
			for (TimeBackElement e: elements) {
				e.updateAfter(delta, vx, vy);
			}
		}
	}
	
	
	
	public void stop() {
		stop = true;
	}
	
	public void render(SpriteBatch batch, int priority) {
		super.render(batch, priority);
		if (loadEtap == 2)
		for (TimeBackElement e: elements) {
			e.render(batch, priority);
		}
	}
	
	public int eggId;
	
	public void setItem(String path, float x, float y, int eggId) {
		this.eggId = eggId;
		Pixmap pixmap = new Pixmap(Gdx.files.internal(path));
		Pixmap p;
		TimeBackElement el;
		
		for (int i = -1; i < 11; i++) {
			for (int j = -1; j < 11; j++) {
				p = new Pixmap(pixmap.getWidth()/10, pixmap.getHeight()/10, pixmap.getFormat());
				p.drawPixmap(pixmap, -pixmap.getWidth()/10*i, -pixmap.getHeight()/10*j);
				SpriteObject object = new SpriteObject(x+pixmap.getWidth()/10*i, pixmap.getHeight() + y-pixmap.getHeight()/10*j, null, 0);
				object.addTexture(new Texture(p));
				el = new TimeBackElement(object, this);
				load(el, (i-5)*(10+rand.nextInt(10)-5), -(j-5)*(10+rand.nextInt(10)-5)); 
			}
		}
		loadEtap = 1;
		
		
		
	}

}
