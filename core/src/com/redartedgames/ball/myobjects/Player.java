package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;

import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class Player extends Ball{

	private int xAxis;
	
	public Player(float x, float y, float radius, float m, BehaviorMode bMode, GameObject parent, int id) {
		super(x, y, radius, m, bMode, parent, id);
		xAxis = 0;
	}
	
	public void addXAxis(float x) {
		xAxis += x;
	}
	
	public void applyPhysicsToAcceleration() {
		super.applyPhysicsToAcceleration();
		accelerationX = accelerationX.add(new BigDecimal("" + xAxis));
	}
	
	

}
