package com.redartedgames.ball.objects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.consts.PhysicConsts;

public class ReversableMovement extends Movement
{
	private long framesI;
	
	private ArrayList<ReversableMovement> prevMoves;

	private BigDecimal delta2, positionX, velocityX, accelerationX, collisionAccX, positionY, dragKX, dragKX2, dragKY, dragKY2, delta21,
	velocityY, accelerationY, collisionAccY, gX, gY;
	
	private boolean isForward, isForwardTransaction;
	
	private static int replacementI = 10000000;
	private static float dokladnosc = 0.01f;
	private static float v = 1000, j = 4; // do funkcji sigmoid
	
	public ReversableMovement(Vector2 position) {
		super(position);
		framesI = 0;
		prevMoves = new ArrayList<ReversableMovement>();
		isForwardTransaction = true;
		isForward = true;
		delta2 = new BigDecimal("0.01");
		positionX = new BigDecimal("" + position.x);
		positionY = new BigDecimal("" + position.y);
		velocityX = new BigDecimal("0");
		velocityY = new BigDecimal("0");
		accelerationX = new BigDecimal("0");
		accelerationY = new BigDecimal("0");
		collisionAccX = new BigDecimal("0");
		collisionAccY = new BigDecimal("0");
		gX = new BigDecimal("0");
		gY = new BigDecimal("0");
		delta21 = new BigDecimal("100");
		float dragX = PhysicConsts.DRAG_X;
		float dragY = PhysicConsts.DRAG_Y;
		dragKX = new BigDecimal("" + dragX);//18.08");
		dragKX2 = new BigDecimal("" + (-(100-dragX)));//-0.01220703125");
		dragKY = new BigDecimal("" + dragY);//18.08");
		dragKY2 = new BigDecimal("" + (-(100-dragY)));//-0.01220703125");
	}
	
	private void addMovement() {
		ReversableMovement r = new ReversableMovement(position);
		r.positionX = positionX;
		r.positionY = positionY;
		r.velocityX = velocityX;
		r.velocityY = velocityY;
		prevMoves.add(r);
		positionX = new BigDecimal("" + positionX.floatValue());
		positionY = new BigDecimal("" + positionY.floatValue());
		velocityX = new BigDecimal("" + velocityX.floatValue());
		velocityY = new BigDecimal("" + velocityY.floatValue());
		accelerationX = new BigDecimal("" + accelerationX.floatValue());
		accelerationY = new BigDecimal("" + accelerationY.floatValue());
		//Gdx.app.log("ReversableMovement", "add");
	}
	
	private void replaceMovement() {
		//Gdx.app.log("ReversableMovement", "rep try");
		ReversableMovement r = prevMoves.get(prevMoves.size()-1);
		BigDecimal positionX2 = new BigDecimal("" + r.positionX.floatValue());
		BigDecimal positionY2 = new BigDecimal("" + r.positionY.floatValue());
		BigDecimal positionX1 = new BigDecimal("" + positionX.floatValue());
		BigDecimal positionY1 = new BigDecimal("" + positionY.floatValue());
		//if(positionX2.equals(positionX1) && positionY2.equals(positionY1)) {
		if (r.positionX.subtract(positionX).multiply(r.positionX.subtract(positionX)).floatValue() < dokladnosc &&
				r.positionY.subtract(positionY).multiply(r.positionY.subtract(positionY)).floatValue() < dokladnosc) {
			//Gdx.app.log("ReversableMovement", "rep suc");
			positionX = r.positionX;
			positionY = r.positionY;
			velocityX = r.velocityX;
			velocityY = r.velocityY;
		}
		if (!prevMoves.isEmpty())prevMoves.remove(r);
	}
	
	public void updateBefore(float delta) {
		isForward = isForwardTransaction;
		isStopped = isStoppedTransation;
		if (!isStopped) {
			if(isForward) {
				framesI++; 
				if (framesI == framesI/replacementI*replacementI) {
					addMovement();
					Gdx.app.log("rev mov", "replacement!!!");
				}
			}
			else {
				framesI--;     
				if (framesI == framesI/replacementI*replacementI && framesI >0) {
					replaceMovement();
				}
				positionX = positionX.subtract(sigmoid(velocityX, v).multiply(delta2));
				positionY = positionY.subtract(sigmoid(velocityY, v).multiply(delta2));	
				position.set(positionX.floatValue(), positionY.floatValue());
			}
		}
	}
	
	private BigDecimal sigmoid(BigDecimal x, float k) {
		return new BigDecimal("" + k*(1.0/(1.0 + Math.pow(Math.E, -x.floatValue()/k*j)) -0.5));
	}
	public void updateAfter(float delta) {
		if(!isStopped) {
			if(isForward) {
				accelerationX = accelerationX.subtract(velocityX.multiply(dragKX));
				accelerationY = accelerationY.subtract(velocityY.multiply(dragKY));
				
				velocityX = velocityX.add(accelerationX.multiply(delta2));
				velocityY = velocityY.add(accelerationY.multiply(delta2));				
				
				positionX = positionX.add(sigmoid(velocityX, v).multiply(delta2));
				positionY = positionY.add(sigmoid(velocityY, v).multiply(delta2));
				position.set(positionX.floatValue(), positionY.floatValue());
			}
			else {
				velocityX = accelerationX.subtract(velocityX.multiply(delta21)).divide(dragKX2, RoundingMode.HALF_DOWN);
				velocityY = accelerationY.subtract(velocityY.multiply(delta21)).divide(dragKY2, RoundingMode.HALF_DOWN);
				//velocityX = accelerationX.subtract(velocityX.multiply(delta21)).multiply(dragKX2);
				//velocityY = accelerationY.subtract(velocityY.multiply(delta21)).multiply(dragKY2);
			}
		}
		
	}
	
	
	
	//getters & setters
		
	public void setAccToG() {
		accelerationX = gX;
		accelerationY = gY;
	}
	
	public void setColToZero() {
		collisionAccX = BigDecimal.ZERO;
		collisionAccY = BigDecimal.ZERO;
	}
	
	public void addCollisionAcc(BigDecimal x, BigDecimal y) {
		collisionAccX = collisionAccX.add(x);
		collisionAccY = collisionAccY.add(y);
	}
	
	public void addColToAcc() {
		accelerationX = accelerationX.add(collisionAccX);
		accelerationY = accelerationY.add(collisionAccY);
	}
	
	public void setCollisionAccX(BigDecimal collisionAccX) {
		this.collisionAccX = collisionAccX;
	}

	public void setCollisionAccY(BigDecimal collisionAccY) {
		this.collisionAccY = collisionAccY;
	}

	public void setGX(BigDecimal gX) {
		this.gX = gX;
	}

	public void setGY(BigDecimal gY) {
		this.gY = gY;
	}
	
	public void setVelocityX(BigDecimal velocityX) {
		this.velocityX = velocityX;
	}

	public void setVelocityY(BigDecimal velocityY) {
		this.velocityY = velocityY;
	}
	
	public void setAccelerationX(BigDecimal accelerationX) {
		this.accelerationX = accelerationX;
	}

	public void setAccelerationY(BigDecimal accelerationY) {
		this.accelerationY = accelerationY;
	}
	
	public void setPositionX(BigDecimal positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(BigDecimal positionY) {
		this.positionY = positionY;
	}
	
	public BigDecimal getPositionX() {
		return positionX;
	}
	
	public BigDecimal getPositionY() {
		return positionY;
	}
	
	public BigDecimal getVelocityX() {
		return velocityX;
	}
	
	public BigDecimal getVelocityY() {
		return velocityY;
	}
	
	public BigDecimal getAccelerationX() {
		return positionX;
	}
	
	public BigDecimal getAccelerationY() {
		return positionY;
	}
	
	public void setIsForward(boolean isForward) {
		this.isForwardTransaction = isForward;
	}
	
	public void set(ReversableMovement movement) {
		setPositionX(movement.getPositionX());
		setVelocityX(movement.getVelocityX());
		setPositionX(movement.getAccelerationX());
		setPositionY(movement.getPositionY());
		setVelocityY(movement.getVelocityY());
		setPositionY(movement.getAccelerationY());
	}
	
	public boolean getIsForward() {
		return isForward;
	}

}
