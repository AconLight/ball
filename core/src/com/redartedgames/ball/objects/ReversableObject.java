package com.redartedgames.ball.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class ReversableObject extends ColSpriteObject{
		
		
		
		public ReversableObject(float x, float y, GameObject parent, int id) {
			super(x, y, parent, id);
			movement = new ReversableMovement(new Vector2(x, y));
		}		
		
		public void updateBefore(float delta, float vx, float vy) {
			movement.updateBefore(delta);
			hitbox.update(((ReversableMovement) movement).getPositionX(), ((ReversableMovement) movement).getPositionY());
			movement.setColToZero();
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
		
}
