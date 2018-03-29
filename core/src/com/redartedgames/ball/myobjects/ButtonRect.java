package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.objects.CollisionHandle;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.SpriteObject;

public class ButtonRect extends ShiftedRect{

	private ShiftedRect rect;
	
	private SpriteObject lampa, fire;
	
	private boolean isFrozen;
	
	private float time;
	
	private static final float freezeTime = 1f;
	
	private static final float buttonWidth = 44, buttonHeight = 60;
	
	public ButtonRect(float x, float y, float width, float height, float x2, float y2, float width2, float height2,
			GameObject parent, int id) {
		super(x, y, width, height, x2, y2, width2, height2, parent, id);
		lampa = ((SpriteObject) addSprite(new SpriteObject(x, y, this, 0 ))).addTexture("graphic/obj/lampa.png");
		lampa.R = 20/256f; lampa.G = 20/256f; lampa.B = 20/256f;
		fire = ((SpriteObject) addSprite(new SpriteObject(x+buttonWidth/2, y+buttonHeight/2, this, 0 ))).addTexture("graphic/obj/lampa_fire.png");
		fire.R = 114/256f; fire.G = 19/256f; fire.B = 20/256f;
		isFrozen = false;
		time = 0;
		gY = new BigDecimal("270");
	}
	
	public ButtonRect(float x, float y, GameObject parent, int id) {
		super(x, y, buttonWidth, buttonHeight, x, y - buttonHeight/4, buttonWidth, buttonHeight/2, parent, id);
		lampa = ((SpriteObject) addSprite(new SpriteObject(x, y, this, 0 ))).addTexture("graphic/obj/lampa.png");
		lampa.R = 20/256f; lampa.G = 20/256f; lampa.B = 20/256f;
		fire = ((SpriteObject) addSprite(new SpriteObject(x+buttonWidth/2, y+buttonHeight/2, this, 0 ))).addTexture("graphic/obj/lampa_fire.png");
		fire.R = 114/256f; fire.G = 19/256f; fire.B = 20/256f;
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
	
	public void render(SpriteBatch sr, int priority) {
		lampa.setPosition(new Vector2(position.x+buttonWidth/2, position.y+buttonHeight/2));
		lampa.render(sr, priority);
		fire.setPosition(new Vector2(position.x+buttonWidth/2, position.y+buttonHeight/2));
		fire.render(sr, priority);
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
			fire.R = 0/256f; fire.G = 162/256f; fire.B = 156/256f;
		}
	}
	
	@Override
	public GameObject createCopy() {
		return new ButtonRect(position.x, position.y, parent, 0);
	}
	
	@Override
	public String label() {
		// TODO Auto-generated method stub
		return "ButtonRect " + id;
	}
	
	@Override
	public String newObjectToString() {
		return "new ButtonRect(" +(int)position.x + ", " + (int)position.y + ", " + parent + ", " + 0 + ")";
	}

}
