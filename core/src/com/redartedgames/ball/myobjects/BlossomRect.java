package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;
import com.redartedgames.ball.objects.Movement;

public class BlossomRect extends Rect{

	boolean isTouched;
	private int hideX, hideY;
	private float deltaHideX = 0, deltaHideY = 0;
	private boolean isHiding, isGrowing, isWaiting;
	private static float timeToGrow = 0.2f;
	private float timeToGrowCnt;
	private Vector2 startPosParent;
	
	public BlossomRect(float x, float y, float width, float height, int hideX, int hideY, GameObject parent) {
		super(x, y, width, height, BehaviorMode.none, parent, 0);
		movement = new Movement(new Vector2(x, y));
		startPosParent = new Vector2(parent.getPosition());
		this.hideX = hideX;
		this.hideY = hideY;
	}
	
	protected void generateBlossom() {
		
		
	}
	
	public void checkHide(GameObject rect) {
		if(rect != parent && rect.getHitbox().bMode == BehaviorMode.dynamic) {
			c = hitbox.checkCol(rect.getHitbox());
			isTouched = isTouched || c.isTrue;
		}
	}
	
	public void updateLast(float delta, float vx, float vy) {
		if (isTouched)  {
			isHiding = true;
			isGrowing = false;
			isWaiting = false;
			timeToGrowCnt = 0;
		}
		else {
			//isHiding = false;
		}
		isTouched = false;
		
		
		if (isHiding) {
			
			if (hideX*deltaHideX >= width || hideY*deltaHideY >= height) {
				isHiding = false;
				timeToGrowCnt = 0;
				isWaiting = true;

			}
			else {
				hideALittle(delta);
			}
		}
		else if (isGrowing) {
			if (hideY*deltaHideY <= 0 && hideX*deltaHideX <= 0) {
				//deltaHideX = 0;
				//deltaHideY = 0;
				isGrowing = false;
			}
			else {
				hideALittle(-delta);
			}
		}
		
		
		else if (isWaiting) {
			timeToGrowCnt+=delta;
			if(timeToGrowCnt >= timeToGrow) {
				isGrowing = true;
				isWaiting = false;
				timeToGrowCnt = 0;
			}
		}
	}
	
	private void hideALittle(float delta) {
		deltaHideX += hideX*35f*delta;
		deltaHideY += hideY*35f*delta;
	}
	
	public void render(ShapeRenderer sr, int priority) {
		sr.setColor(20/256f, 20/256f, 20/256f, 1f);
		sr.rect((parent.getPosition().x- startPosParent.x + position.x - width/2+0.5f + deltaHideX),
			parent.getPosition().y - startPosParent.y +  position.y - height/2+0.5f + deltaHideY, width+0.5f-hideX*deltaHideX, height+0.5f - hideY*deltaHideY);
	}

}
