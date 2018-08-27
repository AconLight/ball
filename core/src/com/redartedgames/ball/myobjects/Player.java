package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.redartedgames.ball.consts.PlayerConsts;
import com.redartedgames.ball.database.EasterEggsBase;
import com.redartedgames.ball.dialog.Conversation;
import com.redartedgames.ball.dialog.DialogConversationWindow;
import com.redartedgames.ball.dialog.DialogWindow;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.SpriteObject;

public class Player extends Ball{

	private int xAxis;
	
	private int reversedMovesNumb;
	
	private float fuel;
	
	private boolean isJumping;
	
	public boolean hasAPressed, hasDPressed;
	
	private MovesData movesData;
	
	private static float jumpBlockTime = PlayerConsts.JUMP_BLOCK_TIME;
	private float jumpBlockTimer;
	
	public SpriteObject playerSprite, playerEyes, playerEyesImp, playerSmile, playerHat, playerFrozen;
	
	private RectParticles particles;
	
	public boolean isAlive;
	
	Random rand;
	
	public boolean isConversation;
	
	protected float frozenAlfa = 0.5f;
	
	protected ArrayList<SpriteObject> core, hat, external;
	
	public float scl = 1;
	
	public Player(float x, float y, float m, GameObject parent, int id) {
		super(x, y, PlayerConsts.PLAYER_HITBOX_R, m, BehaviorMode.dynamic, parent, id);
		isAlive = true;
		hasAPressed = false;
		hasDPressed = false;
		rand = new Random();
		particles = new RectParticles(12);
		isFrozening = true;
		movesData = new MovesData();
		xAxis = 0;
		isJumping = false;
		jumpBlockTimer = 0;
		reversedMovesNumb = 0;
		playerSprite = new SpriteObject(x, y, this, id);
		playerSprite.addTexture("graphic/player/player0.png");
		playerSprite.addTexture("graphic/player/player1.png");
		playerSprite.addTexture("graphic/player/player2.png");
		playerSprite.addTexture("graphic/player/player3.png");
		playerSprite.addTexture("graphic/player/player4.png");
		playerSprite.addTexture("graphic/player/player5.png");
		playerSprite.addTexture("graphic/player/player6.png");
		playerSprite.addTexture("graphic/player/player7.png");
		playerSprite.setFrameTime(0);
		playerEyes = new SpriteObject(x, y, this, id);
		playerEyes.addTexture("graphic/player/playereyes.png");
		playerEyes.addTexture("graphic/player/playereyesup.png");
		playerEyesImp = new SpriteObject(x, y, this, id);
		playerEyesImp.addTexture("graphic/player/playereyesblue.png").visibility = 0;
		playerEyesImp.addTexture("graphic/player/playereyesupblue.png").visibility = 0;
		playerSmile = new SpriteObject(x, y, this, id);
		playerSmile.addTexture("graphic/player/playersmile.png");
		playerSmile.addTexture("graphic/player/playersmileup.png");
		playerHat = new SpriteObject(x, y, this, id);
		playerHat.addTexture("graphic/player/playerhat.png");
		playerFrozen = new SpriteObject(x, y, this, id);
		playerFrozen.addTexture("graphic/player/player0frozen0.png").visibility = 0f;
		playerFrozen.addTexture("graphic/player/player0frozen1.png");
		playerFrozen.addTexture("graphic/player/player0frozen2.png");
		playerFrozen.addTexture("graphic/player/player0frozen3.png");
		playerFrozen.addTexture("graphic/player/player0frozen4.png");
		playerFrozen.addTexture("graphic/player/player0frozen5.png");
		playerFrozen.addTexture("graphic/player/player0frozen6.png");
		playerFrozen.addTexture("graphic/player/player0frozen7.png");
		playerFrozen.addTexture("graphic/player/player0frozen8.png");
		playerFrozen.addTexture("graphic/player/player0frozen9.png");
		playerFrozen.addTexture("graphic/player/player0frozen10.png");
		core = new ArrayList<>();
		hat = new ArrayList<>();
		external = new ArrayList<>();
		core.add(playerEyes);
		core.add(playerSprite);
		core.add(playerSmile);
		hat.add(playerHat);
		external.add(playerEyesImp);
		
		updateBefore(0, 0, 0);

	}
	
	public void setColor(ArrayList<SpriteObject> list, float r, float g, float b, float alfa) {
		for (SpriteObject obj: list) {
			obj.setColor(r, g, b, alfa);
		}
	}
	
	public void setColor(ArrayList<SpriteObject> list, Color color) {
		for (SpriteObject obj: list) {
			obj.setColor(color);
		}
	}
	
	public void setScl(float scl) {
		this.scl = scl;
		playerSprite.sclX = scl; playerSprite.sclY = scl;
		playerEyes.sclX = scl; playerEyes.sclY = scl;
		playerSmile.sclX = scl; playerSmile.sclY = scl;
		playerHat.sclX = scl; playerHat.sclY = scl;
	}
	

	
	public void render(SpriteBatch sr, int priority) {
		int i = 0;
		sr.setColor(
				isForwardFac*forwardColor.r + (1-isForwardFac)*backwardColor.r, 
				isForwardFac*forwardColor.g + (1-isForwardFac)*backwardColor.g, 
				isForwardFac*forwardColor.b + (1-isForwardFac)*backwardColor.b, 
				isForwardFac*forwardColor.a + (1-isForwardFac)*backwardColor.a);
		int a;
		float dx = 0;
		for(Vector3 v: particles.rects) {
			i++;
			if (v.z > particles.time) {
				a = (int) Math.signum(((ReversableMovement)movement).getVelocityX().floatValue());
				if (a == 0) a = 1;
				v.z = rand.nextInt((int)particles.time*10)/10f;
				v.x = position.x- a*
						(float)Math.sin(i*4*Math.PI/50)*20;
				v.y = position.y + (i-6)*4;
				
				
				
			}
			dx = position.x - v.x;
			dx = Math.abs(dx);
			dx = (float) ((Math.sin(dx*Math.PI/100f - Math.PI/2) + 1f)/2.5f);
			dx *= (particles.time-v.z)/particles.time;
			sr.setColor(
					isForwardFac*forwardColor.r + (1-isForwardFac)*backwardColor.r, 
					isForwardFac*forwardColor.g + (1-isForwardFac)*backwardColor.g, 
					isForwardFac*forwardColor.b + (1-isForwardFac)*backwardColor.b, 
					dx);
			sr.draw(GameObject.dotTex, v.x, v.y, particles.width, particles.height);
		}
		sr.setColor(
				isForwardFac*forwardColor.r + (1-isForwardFac)*backwardColor.r, 
				isForwardFac*forwardColor.g + (1-isForwardFac)*backwardColor.g, 
				isForwardFac*forwardColor.b + (1-isForwardFac)*backwardColor.b, 
				isForwardFac*forwardColor.a + (1-isForwardFac)*backwardColor.a);
		playerSprite.getPosition().set(((int)this.position.x), this.position.y);
		playerEyes.getPosition().set(((int)this.position.x+(int)side*scl), this.position.y);
		playerEyesImp.getPosition().set(((int)this.position.x+(int)side*scl), this.position.y);
		playerSmile.getPosition().set(((int)this.position.x+(int)side*scl), this.position.y);
		playerFrozen.getPosition().set(((int)this.position.x+(int)side*scl), this.position.y);
		if(isJumping)
			playerHat.getPosition().set(this.position.x, this.position.y+48*scl);
		else
			playerHat.getPosition().set(this.position.x, this.position.y+48*scl);
		//sr.setColor(20/256f, 20/256f, 20/256f, 1f);
		//sr.draw(tex, position.x-radius, position.y-radius, radius*2, radius*2);
		playerSprite.setColor(sr.getColor().r, sr.getColor().g, sr.getColor().b, sr.getColor().a);
		playerHat.setColor(sr.getColor().r, sr.getColor().g, sr.getColor().b, sr.getColor().a);
		playerSmile.setColor(sr.getColor().r, sr.getColor().g, sr.getColor().b, sr.getColor().a);
		playerEyes.setColor(sr.getColor().r, sr.getColor().g, sr.getColor().b, sr.getColor().a);
		
		playerSprite.render(sr, priority);
		playerHat.render(sr, priority);
		playerSmile.render(sr, priority);
		playerEyesImp.render(sr, priority);		
		playerEyes.render(sr, priority);
		playerFrozen.render(sr, priority);
	}
	
	public int getReversedMovesNumb() {
		return reversedMovesNumb;
	}
	
	public void addXAxis(float x) {
		if (!isConversation)
		xAxis += x;
	}
	
	private float side = 7.5f;
	private float animationSide = 7.5f;
	private float animationK = 0.1f;
	public void updateAfter(float delta, float vx, float vy) {
		if (!isConversation) {
			super.updateAfter(delta, vx, vy);
		}
	}
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
		
		//particles
		particles.update(delta);
		
		//animation
		if (((ReversableMovement)movement).getVelocityX().floatValue() > 0) {
			animationSide = 7;
		}
		else if (((ReversableMovement)movement).getVelocityX().floatValue() < 0) {
			animationSide = 0;
		}
		side += animationSide*animationK;
		side /= (animationK+1);
		playerSprite.frameNum = (int)(side);
		
		if (isJumping) {
			playerEyes.frameNum = 1;
			playerSmile.frameNum = 1;
		}
		else {
			playerEyes.frameNum = 0;
			playerSmile.frameNum = 0;
		}
		
		
		
		if (isJumping) fuel -= 0.02f;
		
		if (((ReversableMovement) movement).isStopped) {
			playerFrozen.setColor( 125/255f, 230/255f, 255/255f, (playerFrozen.visibility*39 + frozenAlfa)/40f);
			playerFrozen.frameNum = (int) (playerFrozen.visibility*10.5f*2);
		}
	}
	
	public void applyPhysicsToAcceleration() {
		super.applyPhysicsToAcceleration();
		
			
			if (((ReversableMovement)movement).getIsForward()) {
				reversedMovesNumb = 0;
				if(isJumping && fuel > 0)  {
					movesData.addMove(new BigDecimal("" + xAxis), PlayerConsts.JUMP_ACC);
					((ReversableMovement) movement).addCollisionAcc(movesData.getLastAccX(), movesData.getLastAccY());
				}
				else {
					movesData.addMove(new BigDecimal("" + xAxis), BigDecimal.ZERO);
					((ReversableMovement) movement).addCollisionAcc(movesData.getLastAccX(), movesData.getLastAccY());
				}
				//Gdx.app.log("Player Y", "" + movesData.getLastAccY());
				//Gdx.app.log("Player X", "" + movesData.getLastAccX());           
			}
			else {
				/*for(int i =  movesData.accelerationsX.size(); i >= 0 ; i--  ) {
					Gdx.app.log("Player Y R", "" + movesData.accelerationsX.get(i-1));
					Gdx.app.log("Player X R", "" + movesData.accelerationsY.get(i-1));
				}*/
				BigDecimal accX = movesData.getLastAccX();
				BigDecimal accY = movesData.getLastAccY();
				((ReversableMovement) movement).addCollisionAcc(accX, accY);
				movesData.removeMove();
				reversedMovesNumb++;
				  
			}
		//accelerationX = accelerationX.add(new BigDecimal("" + xAxis));
	}
	
	@Override
	public void collide(GameObject obj) {
		super.collide(obj);
		if (c.isTrue && obj.getHitbox() != null && obj.getHitbox().bMode != BehaviorMode.none) {
			//Gdx.app.log("Player", "" + c.disY);
			
			if (c.disY.floatValue() >= 0 && c.disY.abs().floatValue() > c.disX.abs().floatValue()) fuel = PlayerConsts.JUMP_BLOCK_TIME;
			else ;//fuel = 0;			
		}
		
	}
	
	public void setIsJumping(boolean isJumping) {
		if (!isConversation)
			this.isJumping = isJumping;
		else
			this.isJumping = false;
	}
	
	public MovesData getMovesData() {
		return movesData;
	}
	
	public void setReversedMovesNumb(int x) {
		reversedMovesNumb = x;
	}
	
	

}
