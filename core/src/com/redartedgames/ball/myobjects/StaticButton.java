package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.objects.ColSpriteObject;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class StaticButton extends Rect{

	private MovingRect movingRect;
	
	private boolean isOn;
	
	private static final float buttonWidth = 44, buttonHeight = 60;
	
	private boolean autoOff = true;
	
	private SpriteObject lampa, fire;
	
	public StaticButton(float x, float y, float width, float height, MovingRect movingRect, GameObject parent, int id) {
		super(x, y, width, height, BehaviorMode.kinematic, parent, id);
		lampa = ((SpriteObject) addSprite(new SpriteObject(x, y, this, 0 ))).addTexture("graphic/obj/lampa.png");
		lampa.R = 20/256f; lampa.G = 20/256f; lampa.B = 20/256f;
		fire = ((SpriteObject) addSprite(new SpriteObject(x, y, this, 0 ))).addTexture("graphic/obj/lampa_fire.png");
		fire.R = 114/256f; fire.G = 19/256f; fire.B = 20/256f;
		this.movingRect = movingRect;
		isOn = false;
	}
	
	public StaticButton(float x, float y, MovingRect movingRect, GameObject parent, int id) {
		super(x, y, buttonWidth, buttonHeight, BehaviorMode.kinematic, parent, id);
		lampa = ((SpriteObject) addSprite(new SpriteObject(x, y, this, 0 ))).addTexture("graphic/obj/lampa.png");
		lampa.R = 20/256f; lampa.G = 20/256f; lampa.B = 20/256f;
		fire = ((SpriteObject) addSprite(new SpriteObject(x, y, this, 0 ))).addTexture("graphic/obj/lampa_fire.png");
		fire.R = 114/256f; fire.G = 19/256f; fire.B = 20/256f;
		this.movingRect = movingRect;
		isOn = false;
	}
	
	public void applyPhysicsToAcceleration() {
		if (!isOn) {
			fire.R = 114/256f; fire.G = 19/256f; fire.B = 20/256f;
		}
		if(autoOff)isOn = false;
		super.applyPhysicsToAcceleration();
		if (movingRect != null)movingRect.setOn(isOn);
		
	}

	@Override
	public void collide(GameObject obj) {
		super.collide(obj);
		
		if(c.isTrue && obj.getHitbox().bMode != BehaviorMode.kinematic) {
			isOn = true;
			fire.R = 0/256f; fire.G = 162/256f; fire.B = 156/256f;
			//zatrzymywanie obiektu przy zderzeniu
			//((ReversableObject)obj).setShouldBeStopped(true);
			
		}
		else {
			//fire.R = 114/256f; fire.G = 19/256f; fire.B = 20/256f;
		}
	}
	
	public void render(SpriteBatch sr, int priority) {
		lampa.setPosition(new Vector2(position.x, position.y));
		lampa.render(sr, priority);
		fire.setPosition(new Vector2(position.x, position.y));
		fire.render(sr, priority);		
	}
	
	@Override
	public GameObject createCopy() {
		return new StaticButton(position.x, position.y, null, parent, 0);
	}
	
	@Override
	public String label() {
		// TODO Auto-generated method stub
		return "StaticButton " + id;
	}
	
	@Override
	public String newObjectToString() {
		return "new StaticButton(" +(int)position.x + ", " + (int)position.y + ", " + movingRect + ", " + parent + ", " + 0 + ")";
	}
	
	
}
