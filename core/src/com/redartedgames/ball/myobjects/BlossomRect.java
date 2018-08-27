package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	private static float timeToGrow = 0.1f;
	private float timeToGrowCnt;
	private Vector2 startPosParent, startPos, diff;
	
	public BlossomRect(float x, float y, float width, float height, int hideX, int hideY, GameObject parent) {
		super(x, y, width, height, BehaviorMode.none, parent, 0);
		movement = new Movement(new Vector2(x, y));
		startPosParent = new Vector2(parent.getPosition());
		startPos = new Vector2(x, y);
		diff = new Vector2(x-parent.getPosition().x, y - parent.getPosition().y);
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
	public void checkHideBefore() {
		position.x += hideX*deltaHideX;
		position.y += hideY*deltaHideY;
		hitbox.update(new BigDecimal("" + (parent.getPosition().x- startPosParent.x + position.x)), new BigDecimal("" + (parent.getPosition().y- startPosParent.y + position.y)));
	}
	
	public void checkHideAfter() {
		position.x = parent.getPosition().x- startPosParent.x + position.x;
		position.y =parent.getPosition().y- startPosParent.y + position.y;
		hitbox.update(new BigDecimal("" + position.x), new BigDecimal("" + position.y));
		position.x = startPos.x;
		position.y = startPos.y;
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
			//isGrowing = false;
			//isWaiting = true;
		}
		
		
		
		if (isHiding) {
			
			if (hideX*deltaHideX >= width || hideY*deltaHideY >= height) {
				isHiding = false;
				timeToGrowCnt = 0;
				isWaiting = true;

			}
			else {
				hideALittle(delta);
			}
			if (!isTouched) {
				isHiding = false;
				isWaiting = true;
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
		
		isTouched = false;
	}
	
	private void hideALittle(float delta) {
		deltaHideX += hideX*35f*delta;
		deltaHideY += hideY*35f*delta;
	}
	
	public void render(SpriteBatch sr, int priority) {
		//sr.setColor(20/256f, 20/256f, 20/256f, 1f);
		//sr.rect((parent.getPosition().x- startPosParent.x + position.x - width/2+0.5f + deltaHideX),
			//parent.getPosition().y - startPosParent.y +  position.y - height/2+0.5f + deltaHideY, width+0.5f-hideX*deltaHideX, height+0.5f - hideY*deltaHideY);
		sr.draw(dotTex,(parent.getPosition().x- startPosParent.x + position.x - width/2+0.5f + deltaHideX/2),
				parent.getPosition().y - startPosParent.y +  position.y - height/2+0.5f + deltaHideY/2, width+0.5f-hideX*deltaHideX, height+0.5f - hideY*deltaHideY);
	}

}
