package com.redartedgames.ball.objects;

import com.badlogic.gdx.math.Vector2;

public class ReversableObject extends ColSpriteObject{
		
		protected boolean shouldBeStopped; // collisions
		protected boolean shouldBeStopped2; // spawn imp
		
		public ReversableObject(float x, float y, GameObject parent, int id) {
			super(x, y, parent, id);
			movement = new ReversableMovement(new Vector2(x, y));
			shouldBeStopped = false;
			shouldBeStopped2 = false;
		}		
		
		public void updateBefore(float delta, float vx, float vy) {
			if(shouldBeStopped || shouldBeStopped2) setIsStopped(true);
			else setIsStopped(false);
			shouldBeStopped = false;
			movement.updateBefore(delta);
			hitbox.update(((ReversableMovement) movement).getPositionX(), ((ReversableMovement) movement).getPositionY());
			movement.setColToZero();
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
			//Gdx.app.log("ReversableObject", "updateAfter - col: " + collisionAccY );
			((ReversableMovement) movement).addCollisionAcc(c.disX, c.disY);
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
		
}
