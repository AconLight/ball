package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class ShiftedRect extends Rect{
	
	private float rectV = 90;
	
	private float rectVsize = 90;
	
	protected Vector2 firstPos, secondPos;
	
	protected float firstWidth, firstHeight, secondWidth, secondHeight;
	
	private boolean isFloating, isFirst;

	public ShiftedRect(float x, float y, float width, float height, float x2, float y2, float width2, float height2, GameObject parent, int id) {
		super(x, y, width, height, BehaviorMode.kinematic, parent, id);
		firstPos = new Vector2(x, y);
		secondPos = new Vector2(x2, y2);
		isFirst = true;
		isFloating = false;
		this.firstHeight = height;
		this.firstWidth = width;
		this.secondHeight = height2;
		this.secondWidth = width2;
	}
	
	public void setV(float v, float vSize) {
		rectV = v;
		rectVsize = vSize;
	}
	
	public void move() {
		isFloating = true;
		isFirst = !isFirst;
	}
	
	public void move(boolean isFirst) {
		if(!isFloating) {
			isFloating = true;
			this.isFirst = isFirst;
		}
	}
	
	public void updateAfter(float delta, float vx, float vy) {
		if (isFloating) {
			float dx, dy, dr, dw, dh, dr2;
			
			
			if (isFirst) {
				if ((width - firstWidth)*(width - firstWidth) < 1f && 
						(height - firstHeight)*(height - firstHeight) < 1f &&
						(position.x - firstPos.x)*(position.x - firstPos.x) < 1f &&
						(position.y - firstPos.y)*(position.y - firstPos.y) < 1f) {
					isFloating =  false;
					velocityX = new BigDecimal("0");
					velocityY = new BigDecimal("0");
					positionX = new BigDecimal("" + firstPos.x);
					positionY = new BigDecimal("" + firstPos.y);
					width = firstWidth;
					height = firstHeight;
				}
				else {
					dx = firstPos.x - position.x;
					dy = firstPos.y - position.y;
					dr = (float) Math.sqrt(dx*dx + dy*dy)+0.0001f;
					velocityX = new BigDecimal("" + (rectV*dx/dr));
					velocityY = new BigDecimal("" + (rectV*dy/dr));
					velocityX = new BigDecimal("0");
					velocityY = new BigDecimal("0");
					positionX = new BigDecimal("" + (positionX.floatValue() + (rectV*dx/dr*0.01f)));
					positionY = new BigDecimal("" + (positionY.floatValue() + (rectV*dy/dr*0.01f)));
					
					dw = firstWidth - width;
					dh = firstHeight - height;
					dr2 = (float) Math.sqrt(dw*dw + dh*dh)+0.0001f;
					
					width += rectVsize*dw*0.01f/dr2;
					height += rectVsize*dh*0.01f/dr2;
				}
			}
			else {
				if ((width - secondWidth)*(width - secondWidth) < 1f && 
						(height - secondHeight)*(height - secondHeight) < 1f &&
						(position.x - secondPos.x)*(position.x - secondPos.x) < 1f &&
						(position.y - secondPos.y)*(position.y - secondPos.y) < 1f) {
					isFloating =  false;
					velocityX = new BigDecimal("0");
					velocityY = new BigDecimal("0");
					positionX = new BigDecimal("" + secondPos.x);
					positionY = new BigDecimal("" + secondPos.y);
					width = secondWidth;
					height = secondHeight;
				}
				else {
					dx = secondPos.x - position.x;
					dy = secondPos.y - position.y;
					dr = (float) Math.sqrt(dx*dx + dy*dy)+0.0001f;
					velocityX = new BigDecimal("" + (rectV*dx/dr));
					velocityY = new BigDecimal("" + (rectV*dy/dr));
					velocityX = new BigDecimal("0");
					velocityY = new BigDecimal("0");
					positionX = new BigDecimal("" + (positionX.floatValue() + (rectV*dx/dr*0.01f)));
					positionY = new BigDecimal("" + (positionY.floatValue() + (rectV*dy/dr*0.01f)));
					dw = secondWidth - width;
					dh = secondHeight - height;
					dr2 = (float) Math.sqrt(dw*dw + dh*dh)+0.0001f;
					
					width += rectVsize*dw*0.01f/dr2;
					height += rectVsize*dh*0.01f/dr2;
				}
			}
		}
		hitbox.rectangle.height = height;
		hitbox.rectangle.width = width;
		super.updateAfter(delta, vx, vy);
	}
	

}
