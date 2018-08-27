package com.redartedgames.ball.objects;

import java.math.BigDecimal;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class ReversableObject extends ColSpriteObject{
		
		protected boolean shouldBeStopped; // collisions
		protected boolean shouldBeStopped2; // spawn imp
		
		protected boolean isFrozening; // player will be frozening
		
		public float isForwardFac;
		protected Color forwardColor;
		protected Color backwardColor;
		
		public ReversableObject(float x, float y, GameObject parent, int id) {
			super(x, y, parent, id);
			movement = new ReversableMovement(new Vector2(x, y));
			shouldBeStopped = false;
			shouldBeStopped2 = false;
			isForwardFac = 1;
			forwardColor = new Color(20/256f, 20/256f, 20/256f, 1f);
			backwardColor = new Color(0/256f, 0/256f, 0/256f, 1f);
		}		
		
		public void updateBefore(float delta, float vx, float vy) {
			//if (((ReversableMovement)movement).getIsForward()) {
				if(shouldBeStopped || shouldBeStopped2) setIsStopped(true);
				else setIsStopped(false);
				shouldBeStopped = false;
			//}

			movement.updateBefore(delta);
			hitbox.update(((ReversableMovement) movement).getPositionX(), ((ReversableMovement) movement).getPositionY());
			movement.setColToZero();
			if (!((ReversableMovement) movement).getIsForward()) {
				isForwardFac -= 20*delta;
				if (isForwardFac < 0) isForwardFac = 0;
			}
			else {
				isForwardFac += 5*delta;
				if (isForwardFac > 1) isForwardFac = 1;
			}
			
		}
		
		public void applyPhysicsToAcceleration() {
			
			if (!isStopped) super.applyPhysicsToAcceleration();
			
		}
		

		public void updateAfter(float delta, float vx, float vy) {
			movement.setAccToG();
			movement.addColToAcc();			
			movement.updateAfter(delta);
			position.set(movement.position);
			
		}
		
		public void updateLast(float delta, float vx, float vy) {

		}
		
		@Override
		public void collide(GameObject obj) {
			super.collide(obj);
			if (getHitbox().bMode == BehaviorMode.dynamic && obj.hitbox != null && obj.hitbox.bMode != BehaviorMode.none) {
				if (!((ReversableObject)obj).isFrozening) {
					((ReversableMovement) movement).addCollisionAcc(c.disX.pow(5), c.disY.pow(5));
				}
				else {
					if (c.isTrue) {
						//setShouldBeStopped(true);
					}
				}
				
			
			}
		}
		
		public void setPosition(Vector2 position) {
			this.position = position;
			((ReversableMovement) movement).setPositionX(new BigDecimal("" + position.x));
			((ReversableMovement) movement).setPositionY(new BigDecimal("" + position.y));
		}
		
		public void setIsForward(boolean isForward) {
			((ReversableMovement) movement).setIsForward(isForward);
		}
		
		public void setIsStopped(boolean isStopped) {
			((ReversableMovement) movement).setIsStopped(isStopped);
		}
		
		public void setShouldBeStopped(boolean shouldBeStopped) {
			this.shouldBeStopped = shouldBeStopped;
		}
		
		@Override
		public void moveBig(int x, int y) {
			movement.
			transform(x, y);
		}
		
}
