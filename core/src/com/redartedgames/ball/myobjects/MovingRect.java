package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;

import com.badlogic.gdx.Gdx;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class MovingRect extends Rect{
	private BigDecimal centerPositionX, centerPositionY, rX, rY;
	
	private BigDecimal movingX, movingY;
	
	private BigDecimal vX, vY;
	
	private boolean isMovingToLast;
	
	private BigDecimal lastPositionX, lastPositionY;
	
	private MovesData movesData;
	
	private boolean isLeft;
	
	public MovingRect(float x, float y, float x2, float y2, float width, float height, boolean isLeft, BehaviorMode bMode, GameObject parent, int id) {
		super(x, y, width, height, bMode, parent, id);
		centerPositionX = ((ReversableMovement) movement).getPositionX();
		centerPositionY = ((ReversableMovement) movement).getPositionY();
		movingX = BigDecimal.ZERO;
		movingY = BigDecimal.ZERO;
		lastPositionX = new BigDecimal("" + x2);
		lastPositionY = new BigDecimal("" + y2);
		rX = lastPositionX.subtract(centerPositionX);
		rY = lastPositionY.subtract(centerPositionY);
		if (!isLeft) {
			((ReversableMovement) movement).setPositionX(centerPositionX.subtract(rX));
			isMovingToLast = true;
		}	
		else {
			((ReversableMovement) movement).setPositionX(centerPositionX.add(rX));
			isMovingToLast = false;
		}
			
		//((ReversableMovement) movement).setPositionY(centerPositionX.subtract(rY));
		movesData = new MovesData();
		gY = BigDecimal.ZERO;
		isMovingToLast = true;
		this.isLeft = isLeft;
	}
	
	public void setV(float vx, float vy) {
		vX = new BigDecimal(""+vx);
		vY = new BigDecimal(""+vy);
	}
	
	public void startLast() {
		isMovingToLast = !isLeft;
	}
	
	public void startFirst() {
		isMovingToLast = isLeft;
	}
	
	public void start(boolean isOn) {
		if (isOn) startLast();
		else startFirst();
	}
	
	public void applyPhysicsToAcceleration() {
		super.applyPhysicsToAcceleration();
		if(((ReversableMovement) movement).getIsForward())
			if (isMovingToLast) {
				movingX = movingX.add(vX);
				movingY = movingY.add(vY);	
			}
			else {
				movingX = movingX.subtract(vX);
				movingY = movingY.subtract(vY);
			}
		else {
			if (isMovingToLast) {
				movingX = movingX.subtract(vX);
				movingY = movingY.subtract(vY);	
			}
			else {
				movingX = movingX.add(vX);
				movingY = movingY.add(vY);
			}
		}
		
		if (movingX.floatValue() > lastPositionX.subtract(centerPositionX).floatValue()) { 
			//	movingX.floatValue() > firstPositionX.subtract(lastPositionX).floatValue()) {
			((ReversableMovement) movement).addCollisionAcc(
					lastPositionX.subtract(((ReversableMovement) movement).getPositionX()),
					BigDecimal.ZERO);
			movingX = rX;
		}
		else if (movingX.floatValue() < centerPositionX.subtract(lastPositionX).floatValue()){
			((ReversableMovement) movement).addCollisionAcc(
					centerPositionX.subtract(rX).subtract(((ReversableMovement) movement).getPositionX()),
					BigDecimal.ZERO);
			movingX = rX.negate();
		}
		
		if (movingY.floatValue() < lastPositionY.subtract(centerPositionY).floatValue()) {
			//	movingY.floatValue() > firstPositionY.subtract(lastPositionY).floatValue()) {
			((ReversableMovement) movement).addCollisionAcc(
					BigDecimal.ZERO, 
					movingY.add(centerPositionY).subtract(((ReversableMovement) movement).getPositionY()));
		}
		
	}

}
