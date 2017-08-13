package com.redartedgames.ball.myobjects;

import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Movement;

public class Imp extends Player{

	protected boolean isActive;
	
	public static int type;
	
	public static int STATIC_TYPE = 1;
	
	public static int ACTIVE_TYPE = 2;
	
	public Imp(float x, float y, float m, GameObject parent, int id) {
		super(x, y, 1, parent, id);
		isActive = false;
		type = 0;
	} 
	
	public void spawn(Movement movement) {
		this.movement = movement;
		isActive = true;
		isStopped = true;
	}

}
