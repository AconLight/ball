package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;

import com.badlogic.gdx.Gdx;
import com.redartedgames.ball.consts.PlayerConsts;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.ReversableObject;

public class Player extends Ball{

	private int xAxis;
	
	public Player(float x, float y, float m, GameObject parent, int id) {
		super(x, y, PlayerConsts.PLAYER_HITBOX_R, m, BehaviorMode.dynamic, parent, id);
		xAxis = 0;
	}
	
	public void addXAxis(float x) {
		xAxis += x;
	}
	
	public void applyPhysicsToAcceleration() {
		super.applyPhysicsToAcceleration();
		((ReversableMovement) movement).addCollisionAcc(new BigDecimal("" + xAxis), BigDecimal.ZERO);
		accelerationX = accelerationX.add(new BigDecimal("" + xAxis));
	}
	
	@Override
	public void collide(GameObject obj) {
		super.collide(obj);
		((ReversableObject) obj).setIsStopped(c.isTrue);
	}
	
	

}
