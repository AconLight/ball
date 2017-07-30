package com.redartedgames.ball.objects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameObject {
		public static int priorities = 1;
		private int id;
		protected Vector2 position, velocity, acceleration, oldAcc, oldVel, collisionAcc;
		public BigDecimal delta2, positionX, velocityX, accelerationX, collisionAccX, positionY, dragK, delta21,
		velocityY, accelerationY, collisionAccY, oldAccX, oldAccY, gX, gY;
		private ArrayList<GameObject> gameObjects;
		int objectViewPriority;
		protected Hitbox hitbox;
		public ArrayList<GameObject> collidableObjects;
		
		public Hitbox getHitbox() {
			return hitbox;
		}

		private GameObject parent;
		
		
		public GameObject(float x, float y, int id, GameObject parent) {
			this.id = id;
			this.parent = parent;
			objectViewPriority = 1;
			position = new Vector2(x, y);
			positionX = new BigDecimal("" + x);
			positionY = new BigDecimal("" + y);
			velocityX = new BigDecimal("0");
			velocityY = new BigDecimal("0");
			accelerationX = new BigDecimal("0");
			accelerationY = new BigDecimal("0");
			collisionAccX = new BigDecimal("0");
			collisionAccY = new BigDecimal("0");
			oldAccX = new BigDecimal("0");
			oldAccY = new BigDecimal("0");
			gX = new BigDecimal("0");
			gY = new BigDecimal("0");
			delta2 = new BigDecimal("0.01");
			delta21 = new BigDecimal("100");
			dragK = new BigDecimal("1");
			velocity = new Vector2();
			acceleration = new Vector2();
			oldAcc = new Vector2();
			oldVel = new Vector2();
			collisionAcc = new Vector2();
			gameObjects = new ArrayList<GameObject>();
			collidableObjects = new ArrayList<GameObject>();
			hitbox = new Hitbox();
		}
		
		public void setHitbox(Hitbox hitbox) {
			this.hitbox = hitbox;
		}
		
		public void transformOnlyThis(float x, float y) {
			position.x += x;
			position.y += y;
			for(int i=0; i<gameObjects.size();i++)
				gameObjects.get(i).transform(-x, -y);
		}
		
		public void transform(float x, float y) {
			position.x += x;
			position.y += y;
		}
		
		
		public void updateBefore(float delta, float vx, float vy) {
			if (delta >= 0) {
				delta2 = new BigDecimal("0.01");
			}
			else {
				//delta2 = new BigDecimal("-0.01");
					
								
				
				
				
			}
		}
		public void updateAfter(float delta, float vx, float vy) {
			

			
			if (delta >= 0) {
				
				hitbox.update(positionX, positionY);
				applyPhysicsToAcceleration();
				
				accelerationX = accelerationX.subtract(velocityX.multiply(dragK));
				accelerationY = accelerationY.subtract(velocityY.multiply(dragK));
				
				velocityX = velocityX.add(accelerationX.multiply(delta2));
				velocityY = velocityY.add(accelerationY.multiply(delta2));				
				
				positionX = positionX.add(velocityX.multiply(delta2));
				positionY = positionY.add(velocityY.multiply(delta2));
				
			}
			else {
				
				positionX = positionX.subtract(velocityX.multiply(delta2));
				positionY = positionY.subtract(velocityY.multiply(delta2));
				
				hitbox.update(positionX, positionY);
				applyPhysicsToAcceleration();
				
				velocityX = accelerationX.subtract(velocityX.multiply(delta21)).divide(dragK.subtract(delta21), RoundingMode.HALF_UP);
				velocityY = accelerationY.subtract(velocityY.multiply(delta21)).divide(dragK.subtract(delta21), RoundingMode.HALF_UP);

			}
			
			position.set(positionX.floatValue(), positionY.floatValue());
			oldAccX = collisionAccX;
			oldAccY = collisionAccY;
			
			
			for(int i=0; i<gameObjects.size();i++)
				gameObjects.get(i).updateAfter(delta, velocity.x, velocity.y);
			velocity.sub(vx, vy);

		}
		
		public void updateLast(float delta, float vx, float vy) {

		}
		
		public GameObject getSuperParent() {
			GameObject itr = this;
			while (this.parent != null) itr = itr.parent; 
			return itr;
		}
		
		public void collide(GameObject obj) {
			
		}
		
		public void applyPhysicsToAcceleration() {
			collisionAccX = BigDecimal.ZERO;
			collisionAccY = BigDecimal.ZERO;
			
			for(int i = 0; i < collidableObjects.size(); i++) {
				collide(collidableObjects.get(i));
			}
			
			accelerationX = gX;
			accelerationY = gY;
			
			accelerationX = accelerationX.add((collisionAccX));
			accelerationY = accelerationY.add((collisionAccY));
		}
		
		public Vector2 getVelocity() {
			return velocity;
		}

		public void setVelocity(Vector2 velocity) {
			this.velocity = velocity;
		}

		public Vector2 getAcceleration() {
			return acceleration;
		}

		public void setAcceleration(Vector2 acceleration) {
			this.acceleration = acceleration;
		}

		public void setPosition(Vector2 position) {
			this.position = position;
		}

		public GameObject addSprite(SpriteObject e) {
			gameObjects.add(e);
			return gameObjects.get(gameObjects.size()-1);
		}
		
		public GameObject addGameObject(float x, float y) {
			gameObjects.add(new GameObject(x, y, gameObjects.size(), this));
			return gameObjects.get(gameObjects.size()-1);
		}
		
		public void render(SpriteBatch batch, int priority) {
			for(int i=0; i<gameObjects.size();i++)
				gameObjects.get(i).render(batch, priority);
		}
		
		public void render(ShapeRenderer batch, int priority) {
			for(int i=0; i<gameObjects.size();i++)
				gameObjects.get(i).render(batch, priority);
		}
		
		public Vector2 getPosition() {
			return position;
		}

		public void dispose(){
			for(int i=0; i<gameObjects.size();i++)
				gameObjects.get(i).dispose();
		}
}
