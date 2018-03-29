package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.redartedgames.ball.consts.PlayerConsts;
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
	
	private SpriteObject playerSprite, playerEyes, playerSmile, playerHat;
	
	private RectParticles particles;
	
	public boolean isAlive;
	
	Random rand;
	
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
		playerSmile = new SpriteObject(x, y, this, id);
		playerSmile.addTexture("graphic/player/playersmile.png");
		playerSmile.addTexture("graphic/player/playersmileup.png");
		playerHat = new SpriteObject(x, y, this, id);
		playerHat.addTexture("graphic/player/playerhat.png");
	}
	

	

	
	public void render(SpriteBatch sr, int priority) {
		int i = 0;
		sr.setColor(0, 0, 0, 1);
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
			sr.setColor(0, 0, 0, dx);
			sr.draw(GameObject.dotTex, v.x, v.y, particles.width, particles.height);
		}
		
		playerSprite.getPosition().set(this.position.x, this.position.y);
		playerEyes.getPosition().set(this.position.x+side, this.position.y);
		playerSmile.getPosition().set(this.position.x+side, this.position.y);
		if(isJumping)
			playerHat.getPosition().set(this.position.x, this.position.y+48);
		else
			playerHat.getPosition().set(this.position.x, this.position.y+48);
		sr.setColor(20/256f, 20/256f, 20/256f, 1f);
		//sr.draw(tex, position.x-radius, position.y-radius, radius*2, radius*2);
		playerSprite.render(sr, priority);
		playerHat.render(sr, priority);
		playerSmile.render(sr, priority);
		playerEyes.render(sr, priority);
	}
	
	public int getReversedMovesNumb() {
		return reversedMovesNumb;
	}
	
	public void addXAxis(float x) {
		xAxis += x;
	}
	
	private float side = 7;
	private float animationSide = 7;
	private float animationK = 0.1f;
	
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
		playerSprite.frameNum = (int)(side+0.5f);
		
		if (isJumping) {
			playerEyes.frameNum = 1;
			playerSmile.frameNum = 1;
		}
		else {
			playerEyes.frameNum = 0;
			playerSmile.frameNum = 0;
		}
		
		
		
		if (isJumping) fuel -= 0.02f;
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
		if (c.isTrue) {
			//Gdx.app.log("Player", "" + c.disY);
			
			if (c.disY.floatValue() >= 0 && c.disY.abs().floatValue() > c.disX.abs().floatValue()) fuel = PlayerConsts.JUMP_BLOCK_TIME;
			else ;//fuel = 0;			
		}
		
	}
	
	public void setIsJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
	
	public MovesData getMovesData() {
		return movesData;
	}
	
	public void setReversedMovesNumb(int x) {
		reversedMovesNumb = x;
	}
	
	

}
