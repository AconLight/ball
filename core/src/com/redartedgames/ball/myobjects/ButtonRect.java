package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;

import com.redartedgames.ball.objects.CollisionHandle;
import com.redartedgames.ball.objects.GameObject;

public class ButtonRect extends ShiftedRect{

	private ShiftedRect rect;
	
	private boolean isFrozen;
	
	private float time;
	
	private static final float freezeTime = 1f;
	
	private static final float buttonWidth = 30, buttonHeight = 40;
	
	public ButtonRect(float x, float y, float width, float height, float x2, float y2, float width2, float height2,
			GameObject parent, int id) {
		super(x, y, width, height, x2, y2, width2, height2, parent, id);
		isFrozen = false;
		time = 0;
		gY = new BigDecimal("270");
	}
	
	public ButtonRect(float x, float y, GameObject parent, int id) {
		super(x, y, buttonWidth, buttonHeight, x, y - buttonHeight/4, buttonWidth, buttonHeight/2, parent, id);
		isFrozen = false;
		time = 0;
		gY = new BigDecimal("270");
	}
	
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
	}
	
	public void updateAfter(float delta, float vx, float vy) {
		
		if (isFrozen) {
			time += 0.01f;
			if (time > freezeTime) {
				isFrozen = false;
				time = 0;
			}
		}
		else {

			super.updateAfter(delta, vx, vy);

			position.set(firstPos.x - (firstWidth + secondWidth - width)/2, firstPos.y - (firstHeight + secondHeight - height)/2);
			
			positionX = new BigDecimal("" + position.x);
			positionY = new BigDecimal("" + position.y);
			height += velocityY.floatValue()*0.001f;
			if (height > firstHeight) {
				height = firstHeight;
				velocityY = new BigDecimal("" + 0);
				rect.move(true);
			}
			if (height < secondHeight) {
				height = secondHeight;
				velocityY = new BigDecimal("" + 0);
				rect.move(false);
				isFrozen = true;
				time = 0;
			}
			

			
			hitbox.rectangle.height = height;
			hitbox.rectangle.width = width;
		}
	}
	

	
	public void setTrigger(ShiftedRect rect) {
		this.rect = rect;
	}
	
	@Override
	public void collide(GameObject obj) {
		super.collide(obj);
		CollisionHandle c = hitbox.checkCol(obj.getHitbox());
		collisionAccY = collisionAccY.add(c.disY);
		if (c.isTrue && isFrozen) {
			time = 0;
		}
	}

}
