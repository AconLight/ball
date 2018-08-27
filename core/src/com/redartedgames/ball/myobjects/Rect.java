package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.consts.PhysicConsts;
import com.redartedgames.ball.game.GameWorld;
import com.redartedgames.ball.objects.ColSpriteObject;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class Rect extends ReversableObject{

	private static int blossomMaxH1 = 12, blossomMaxH2 = 12;
	protected float width;
	protected float height;
	private ArrayList<BlossomRect> blossom;
	Random r = new Random();
	Freeze freeze;
	
	public Bush bush;
	
	public Rect(float x, float y, float width, float height, BehaviorMode bMode, GameObject parent, int id) {
		super(x, y, parent, id);
		priority = 2;
		this.width = width;
		this.height = height;
		freeze = new Freeze(0 , 0, 0, this, (int)width, (int)height);
		setHitbox(new Hitbox(positionX, positionY, width, height, bMode));
		if (bMode == BehaviorMode.dynamic) {
			gY = new BigDecimal("-200");
			((ReversableMovement) movement).setGY(new BigDecimal("" + PhysicConsts.G));
		}
		blossom = new ArrayList<BlossomRect>();
		generateBlossom();
		
		if (bMode == BehaviorMode.kinematic) {
			bush = new Bush(x, y + height/2, 0, null, 50);
		}
	}
	
	public void generateFreeze() {
		freeze.generate();
	}
	
	public void setShouldBeStopped(boolean shouldBeStopped) {
		super.setShouldBeStopped(shouldBeStopped);
		generateFreeze();
	}
	
	
	
	protected void generateBlossom() {
		int i = 8;
		int h = 0;
		//góra
		while(i < width/2+4) {
			h += r.nextInt(3)-1;
			if (h < 0) h = 1;
			if (h > blossomMaxH1) h = blossomMaxH1;
			blossom.add(new BlossomRect(position.x + i - width/2-4, position.y + height/2 + 1, 8, 2*(h/2*2), 0, -1, this));
			h += r.nextInt(3)-1;
			if (h < 0) h = 0;
			if (h > blossomMaxH1) h = blossomMaxH1;
			blossom.add(new BlossomRect(position.x - i + width/2-4, position.y + height/2 + 1, 8, 2*(h/2*2), 0, -1, this));
			i+=8;
		}
		
		h = 0;
		i = 4;
		int z = 0;
		while(i < width/2) {
			z = r.nextInt(3)-1;
			h += r.nextInt(3)-1;
			if (h < 0) h = 2;
			if (h > blossomMaxH2) h = blossomMaxH2;
			h = (int)((Math.sin((i+z)/40.0)+1)*blossomMaxH2/2);
			blossom.add(new BlossomRect(position.x + i - width/2-2, position.y + height/2, 4, 4*((h-z)/2*2), 0, -1, this));
			z = r.nextInt(3)-1;
			z *= 2;
			h += r.nextInt(3)-1;
			if (h < 0) h = 2;
			if (h > blossomMaxH2) h = blossomMaxH2;
			h = (int)((Math.sin((i+z)/40.0)+1)*(Math.sin((i+z)/80.0)+1)*blossomMaxH2/4);
			blossom.add(new BlossomRect(position.x - i + width/2-2, position.y + height/2, 4, 4*((h-z)/2*2), 0, -1, this));
			i+=8;
		}
		
		
		
		//lewo
		i = 8;
		h = 0;
		while(i < height/2+4) {
			h += r.nextInt(4)-1;
			if (h < 0) h = 0;
			if (h > blossomMaxH1) h = blossomMaxH1;
			blossom.add(new BlossomRect(position.x - width/2, position.y - i + height/2, 2*(h/2*2), 8, 1, 0, this));
			h += r.nextInt(4)-1;
			if (h < 0) h = 0;
			if (h > blossomMaxH1) h = blossomMaxH1;
			blossom.add(new BlossomRect(position.x - width/2, position.y + i - height/2, 2*(h/2*2), 8, 1, 0, this));
			i+=8;
		}
		
		//prawo
				i = 8;
				h = 0;
				while(i < height/2+4) {
					h += r.nextInt(4)-1;
					if (h < 0) h = 0;
					if (h > blossomMaxH1) h = blossomMaxH1;
					blossom.add(new BlossomRect(position.x + width/2, position.y - i + height/2, 2*(h/2*2), 8, -1, 0, this));
					h += r.nextInt(4)-1;
					if (h < 0) h = 0;
					if (h > blossomMaxH1) h = blossomMaxH1;
					blossom.add(new BlossomRect(position.x + width/2, position.y + i - height/2, 2*(h/2*2), 8, -1, 0, this));
					i+=8;
				}
		
	}
	
	public void applyPhysicsToAcceleration() {
		super.applyPhysicsToAcceleration();
		for(BlossomRect rect: blossom) {
			rect.checkHideBefore();
			for(GameObject obj: collidableObjects)
			rect.checkHide(obj);
			
			rect.checkHideAfter();
		}
	}
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
		freeze.updateBefore(delta, vx, vy);
		if (bush != null) {
			bush.updateBefore(delta, vx, vy);
		}
	}
	
	public void updateLast(float delta, float vx, float vy) {
		super.updateLast(delta, vx, vy);
		for(BlossomRect rect: blossom) {
			rect.updateLast(delta, vx, vy);
		}
	}
	
	
	
	public void render(SpriteBatch sr, int priority) {
		if (priority == 0) {
		sr.setColor(
				isForwardFac*forwardColor.r + (1-isForwardFac)*backwardColor.r, 
				isForwardFac*forwardColor.g + (1-isForwardFac)*backwardColor.g, 
				isForwardFac*forwardColor.b + (1-isForwardFac)*backwardColor.b, 
				isForwardFac*forwardColor.a + (1-isForwardFac)*backwardColor.a);
		//sr.rect((position.x - width/2+0.5f), position.y - height/2+0.5f, width+0.5f, height+0.5f);
		sr.draw(dotTex,(position.x - width/2+0.5f), position.y - height/2+0.5f, width+0.5f, height+0.5f);
		for(BlossomRect rect: blossom) {
			rect.render(sr, priority);
		}
		if (bush != null) {
			bush.render(sr, priority);
		}
		}
		else {
			freeze.render(sr, priority);
		}

		
		
	}
	
	@Override
	public void moveBig(int x, int y) {
		transform(x*25, y*25);
		Gdx.app.log("Game Object", "moveBig");
	}
	
	@Override
	public void moveSmall(int x, int y) {
		transform(x, y);
		Gdx.app.log("Game Object", "moveBig");
	}
	
	@Override
	public void addSize(int a, int b) {
		width += a;
		height += b;
	}
	
	@Override
	public GameObject createCopy() {
		return new Rect(position.x, position.y, width, height, hitbox.bMode, parent, 0);
	}
	
	@Override
	public String label() {
		// TODO Auto-generated method stub
		return "Rect " + id;
	}
	
	@Override
	public String newObjectToString() {
		return "new Rect(" +(int)position.x + ", " + (int)position.y + ", " + (int)width + ", " + (int)height + ", BehaviorMode." + hitbox.bMode + ", " + parent + ", " + 0 + ")";
	}
}