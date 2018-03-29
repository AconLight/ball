package com.redartedgames.ball.myobjects;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.redartedgames.ball.colors.ColorGenerator;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;
import com.redartedgames.ball.screen.Consts;

public class Planet extends GameObject{
	SpriteObject planet1;
	ColorGenerator cg;
	int rectNum;
	SpriteObject[] rects;
	float r;
	Random rand;
	public Planet(float x, float y, Vector3 cg, float r) {
		super(x, y, 0, null);
		this.r = r;
		planet1 = new SpriteObject(x, y, null, 0);
		planet1.addTexture("graphic/bg/planet_grey.png").visibility = 1;
		planet1.R = cg.x; planet1.G = cg.y; planet1.B = cg.z;
		planet1.sclX = r/100f;
		planet1.sclY = r/100f;
		rectNum = (int) (2*r/6);
		rects = new SpriteObject[rectNum];
		rand = new Random();
		
		for (int i = 0; i < rectNum; i++) {
			rects[i] = new SpriteObject(x + (i-rectNum/2) * 6, y + rand.nextInt(-i*(i-rectNum) + 2), null, 0);
			rects[i].addTexture("graphic/shape/dot.png").visibility = 0.3f;
			rects[i].R = cg.x; rects[i].G = cg.y; rects[i].B = cg.z;
			rects[i].sclX  = 3;
			rects[i].sclY  = 5 + rand.nextInt(10);
			
		}
		//this.cg = cg;
	}
	public void updateColor(Vector3 v) {
		planet1.R = v.x; planet1.G = v.y; planet1.B = v.z;
		for (int i = 0; i < rectNum; i++) {
			rects[i].R = v.x; rects[i].G = v.y; rects[i].B = v.z;
		}
	}
	
	public void updateLast(float delta, float vx, float vy) {
		planet1.getPosition().y += -1500*delta/r;
		float dx;
		for (int i = 0; i < rectNum; i++) {
			dx = rects[i].getPosition().x - planet1.getPosition().x;
			if (rects[i].getPosition().y - planet1.getPosition().y > (2*r + 60 - dx*dx/r)/2) {
				rects[i].getPosition().y -= (2*r + 60 - dx*dx/r)/2 + rand.nextInt(-i*(i-rectNum)+2)/2;
			}
			else {
				rects[i].visibility = 0.5f - ((rects[i].getPosition().y - planet1.getPosition().y)/((2*r + 60 - dx*dx/r)/2))/2;
			}
			
		}
		if (planet1.getPosition().y < 300) {
			
			for (int i = 0; i < rectNum; i++) {
				rects[i].getPosition().y += Consts.gameHeight + r - planet1.getPosition().y;
			}
			planet1.getPosition().y = Consts.gameHeight + r; 
		}
	}
	
	public void render(SpriteBatch batch, int priority) {
		super.render(batch, priority);
		
		for (int i = 0; i < rectNum; i++) {
			rects[i].render(batch, priority);
		}
		planet1.render(batch, priority);
	}
	


}
