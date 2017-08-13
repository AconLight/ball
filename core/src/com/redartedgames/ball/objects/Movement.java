package com.redartedgames.ball.objects;

import java.math.BigDecimal;

import com.badlogic.gdx.math.Vector2;

public class Movement {
	
	protected Vector2 position;

	private Vector2 velocity, acceleration;
	
	private Vector2 collisionAcc, g;

	public Movement(Vector2 position) {
		this.position = position;
		velocity = new Vector2();
		acceleration = new Vector2();
		g = new Vector2();
		collisionAcc = new Vector2();
	}
	
	public void updateBefore(float delta) {
		
	}
	public void updateAfter(float delta) {
		velocity.add(acceleration.scl(delta));
		position.add(velocity.scl(delta));
	}
	
	public void addCollisionAcc(Vector2 q) {
		collisionAcc.add(q);
	}
	
	//getters & setters
	
	public void setAccToG() {
		acceleration.set(g);
	}
	
	public void setColToZero() {
		collisionAcc.set(0, 0);
	}
	
	public void addColToAcc() {
		acceleration = acceleration.add(collisionAcc);
	}
	
	public Vector2 getCollisionAcc() {
		return collisionAcc;
	}

	public void setCollisionAcc(Vector2 collisionAcc) {
		this.collisionAcc = collisionAcc;
	}

	public Vector2 getG() {
		return g;
	}

	public void setG(Vector2 g) {
		this.g = g;
	}
	
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
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

}
