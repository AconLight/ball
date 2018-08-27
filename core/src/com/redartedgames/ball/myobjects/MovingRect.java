package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;

import com.badlogic.gdx.Gdx;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class MovingRect extends Rect{
	private BigDecimal centerPositionX, centerPositionY, rX, rY;
	
	private BigDecimal movingX, movingY;
	
	private boolean isOn;
	
	private BigDecimal lastPositionX, lastPositionY;
	private BigDecimal firstPositionX, firstPositionY;
	
	private MovesData movesData;
	
	public MovingRect(float x, float y, float x2, float y2, float width, float height, BehaviorMode bMode, GameObject parent, int id) {
		super(x, y, width, height, bMode, parent, id);
		firstPositionX = ((ReversableMovement) movement).getPositionX();
		firstPositionY = ((ReversableMovement) movement).getPositionY();
		movingX = BigDecimal.ZERO;
		movingY = BigDecimal.ZERO;
		lastPositionX = new BigDecimal("" + x2);
		lastPositionY = new BigDecimal("" + y2);
		rX = lastPositionX.subtract(firstPositionX);
		rY = lastPositionY.subtract(firstPositionY);
		centerPositionX = firstPositionX.add(lastPositionX).divide(new BigDecimal("2"));
		centerPositionY = firstPositionX.add(lastPositionY).divide(new BigDecimal("2"));
		
		((ReversableMovement) movement).replacementI = 1;
		movesData = new MovesData();
		gY = BigDecimal.ZERO;
	}
	
	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}
	
	public void applyPhysicsToAcceleration() {
		
		super.applyPhysicsToAcceleration();

		BigDecimal dx, dy, dx2, dy2;
			dx = new BigDecimal(firstPositionX.subtract(((ReversableMovement) movement).getPositionX()).floatValue());
			dy = new BigDecimal(firstPositionY.subtract(((ReversableMovement) movement).getPositionY()).floatValue());
			//dx = dx.abs();
			//dy = dy.abs();
			if (isOn) {
				dx = new BigDecimal(lastPositionX.subtract(((ReversableMovement) movement).getPositionX()).floatValue());
				dy = new BigDecimal(lastPositionY.subtract(((ReversableMovement) movement).getPositionY()).floatValue());
			}
			if (!((ReversableMovement) movement).getIsForward()) {
				//dx = new BigDecimal("" + 0);
				//dy = new BigDecimal("" + 0);
				System.out.println("do tylu");
			}

			((ReversableMovement) movement).addCollisionAcc(dx, dy);


		
		/*if (movingX.floatValue() > lastPositionX.subtract(centerPositionX).floatValue()) { 
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
		
		if (movingY.floatValue() > lastPositionY.subtract(centerPositionY).floatValue()) { 
			//	movingY.floatValue() > firstPositionY.subtract(lastPositionY).floatValue()) {
			((ReversableMovement) movement).addCollisionAcc(
					BigDecimal.ZERO, 
					lastPositionY.subtract(((ReversableMovement) movement).getPositionY()));
			movingY = rY;
		}
		else if (movingY.floatValue() < centerPositionY.subtract(lastPositionY).floatValue()){
			((ReversableMovement) movement).addCollisionAcc(
					BigDecimal.ZERO, 
					centerPositionY.subtract(rY).subtract(((ReversableMovement) movement).getPositionY()));
			movingY = rY.negate();
		}
		//Gdx.app.log("asdsa", "asd");
		*/
	}
	
	
	@Override
	public void setSpot(int i) {
		
		if (i == 1) {
			lastPositionX = new BigDecimal("" + position.x);
			lastPositionY = new BigDecimal("" + position.y);
		}
		else if (i == 2){
			centerPositionX = new BigDecimal("" + position.x);
			centerPositionY = new BigDecimal("" + position.y);
		}
	}
	
	@Override
	public GameObject createCopy() {
		return new MovingRect(centerPositionX.floatValue(), centerPositionY.floatValue(), lastPositionX.floatValue(), lastPositionY.floatValue(), width, height, hitbox.bMode, parent, 0);
	}
	
	@Override
	public String label() {
		// TODO Auto-generated method stub
		return "MovingRect " + id;
	}
	
	@Override
	public String newObjectToString() {
		return "new MovingRect(" + (int)centerPositionX.floatValue() + ", " +(int)centerPositionY.floatValue() + ", " + (int)lastPositionX.floatValue() + ", " + (int)lastPositionY.floatValue() + ", " + (int)width + ", " + (int)height + ", BehaviorMode." + hitbox.bMode + ", " + parent + ", " + 0 + ")";
	}

}
