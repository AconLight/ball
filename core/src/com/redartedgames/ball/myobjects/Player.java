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
	
	private int reversedMovesNumb;
	
	private float fuel;
	
	private boolean isJumping;
	
	private MovesData movesData;
	
	private static float jumpBlockTime = PlayerConsts.JUMP_BLOCK_TIME;
	private float jumpBlockTimer;
	
	public Player(float x, float y, float m, GameObject parent, int id) {
		super(x, y, PlayerConsts.PLAYER_HITBOX_R, m, BehaviorMode.dynamic, parent, id);
		movesData = new MovesData();
		xAxis = 0;
		isJumping = false;
		jumpBlockTimer = 0;
		reversedMovesNumb = 0;
	}
	
	public int getReversedMovesNumb() {
		return reversedMovesNumb;
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
		
			
			if (((ReversableMovement)movement).getIsForward()) {
				reversedMovesNumb = 0;
				if(isJumping && fuel > 0)  {
					movesData.addMove(new BigDecimal("" + xAxis), PlayerConsts.JUMP_ACC);
					((ReversableMovement) movement).addCollisionAcc(movesData.getLastAccX(), movesData.getLastAccY());
				}
				else {
					movesData.addMove(new BigDecimal("" + xAxis), BigDecimal.ZERO);
					((ReversableMovement) movement).addCollisionAcc(movesData.getLastAccX(), movesData.getLastAccY());
				}
				//Gdx.app.log("Player Y", "" + movesData.getLastAccY());
				//Gdx.app.log("Player X", "" + movesData.getLastAccX());           
			}
			else {
				/*for(int i =  movesData.accelerationsX.size(); i >= 0 ; i--  ) {
					Gdx.app.log("Player Y R", "" + movesData.accelerationsX.get(i-1));
					Gdx.app.log("Player X R", "" + movesData.accelerationsY.get(i-1));
				}*/
				BigDecimal accX = movesData.getLastAccX();
				BigDecimal accY = movesData.getLastAccY();
				((ReversableMovement) movement).addCollisionAcc(accX, accY);
				movesData.removeMove();
				reversedMovesNumb++;
				
			}
		//accelerationX = accelerationX.add(new BigDecimal("" + xAxis));
	}
	
	@Override
	public void collide(GameObject obj) {
		super.collide(obj);
		if (c.isTrue) {
			((ReversableObject) obj).setShouldBeStopped(true);
			if (c.disY.floatValue() >= 0 && c.disY.abs().floatValue() > c.disX.abs().floatValue()) fuel = 1;
			else fuel = 0.05f;			
		}
		
	}
	
	public void setIsJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
	
	public MovesData getMovesData() {
		return movesData;
	}
	
	public void setReversedMovesNumb(int x) {
		reversedMovesNumb = x;
	}
	
	

}
