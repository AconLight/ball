package com.redartedgames.ball.myobjects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Vector3;

public class RectParticles {
	public ArrayList<Vector3> rects;
	public float width = 8, height = 4;
	public float time = 1;
	Random rand;
	
	public RectParticles(int numb) {
		rand = new Random();
		rects = new ArrayList<>();
		for (int i = 0; i < numb; i++) {
			rects.add(new Vector3(0, i*8, rand.nextInt((int)time*10)/10f));
		}
	}
	
	public void update(float delta) {
		for (Vector3 v: rects) {
			v.add(0, 0, delta);
		}
	}
}
