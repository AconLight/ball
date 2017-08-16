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
	
	private float fuel;
	
	private boolean isJumping;
	
	private static float jumpBlockTime = PlayerConsts.JUMP_BLOCK_TIME;
	private float jumpBlockTimer;
	
	public Player(float x, float y, float m, GameObject parent, int id) {
		super(x, y, PlayerConsts.PLAYER_HITBOX_R, m, BehaviorMode.dynamic, parent, id);
		xAxis = 0;
		isJumping = false;
		jumpBlockTimer = 0;
	}
	
	public void addXAxis(float x) {
		xAxis += x;
	}
	
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
		if (isJumping) fuel -= 0.02f;
	}
	
	public void applyPhysicsToAcceleration() {
		super.applyPhysicsToAcceleration();
		((ReversableMovement) movement).addCollisionAcc(new BigDecimal("" + xAxis), BigDecimal.ZERO);
		if(isJumping && fuel > 0) ((ReversableMovement)movement).addCollisionAcc(BigDecimal.ZERO, PlayerConsts.JUMP_ACC);
		accelerationX = accelerationX.add(new BigDecimal("" + xAxis));
	}
	
	@Override
	public void collide(GameObject obj) {
		super.collide(obj);
		if (c.isTrue) {
			((ReversableObject) obj).setShouldBeStopped(true);
			if (c.disY.abs().floatValue() > c.disX.abs().floatValue()) fuel = 1;
			else fuel = 0.05f;			
		}
		
	}
	
	public void setIsJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
	
	

}
