package com.redartedgames.ball.objects;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class ScreenShaker {
	// movement obious, alfaDeepMovement x for alfa, y for deep ;___; 
	//alfa in radians
	
	private Movement movement, alfaDeepMovement;
	private Vector3 drag;
	private Random g;
	private float k;
	private int randomness;
	private float time, timePos, timeAlfa, timeDeep, duration, period, timePeriod, autostrength;
	private boolean isAuto;
	private boolean isRunning;
	
	public ScreenShaker(Vector3 drag, float k, int randomness) {
		movement = new Movement(new Vector2(0, 0));
		alfaDeepMovement = new Movement(new Vector2(0, 0));
		g = new Random();
		set(drag, k, randomness);
	}
	
	public void start(float strength, float duration, float period) {
		isRunning = true;
		autostrength = strength;
		this.duration = duration;
		this.period = period;
	}
	
	public void stop() {
		isRunning = false;
	}
	
	public void set(Vector3 drag, float k, int randomness) {
		this.drag = drag;
		this.k = k;
		this.randomness = randomness;
	}
	
	public void shakePosition(int strength) {
		strength*=50;
		float alfa = (float) (g.nextInt(180)/Math.PI);
		movement.getVelocity().x += Math.cos(alfa)*(strength + g.nextInt(randomness) - randomness/2);
		movement.getVelocity().y += Math.sin(alfa)*(strength + g.nextInt(randomness) - randomness/2);
	}
	
	public void shakeAlfa(int strength) {
		strength*=20;
		float a = (float)(strength/180f*Math.PI*(g.nextInt(randomness) - randomness/2)/(alfaDeepMovement.getPosition().x/50+1));
		if (alfaDeepMovement.getVelocity().x > 0) alfaDeepMovement.getVelocity().x += a;
		else alfaDeepMovement.getVelocity().x += a;
	}
	
	public void shakeDeep(int strength) {
		float a = (float)(strength/180f*Math.PI*(g.nextInt(randomness) - randomness/2)/(alfaDeepMovement.getPosition().y/50+1));
		if (alfaDeepMovement.getVelocity().y > 0) alfaDeepMovement.getVelocity().y += a;
		else alfaDeepMovement.getVelocity().y += a;
	}
	
	public void shake(int strength) {
		shakePosition(strength);
		shakeAlfa(strength);
		shakeDeep(strength);
	}
	
	public void update(float delta) {
		movement.getAcceleration().x = -movement.getPosition().x*k;
		movement.getAcceleration().y = -movement.getPosition().y*k;
		alfaDeepMovement.getAcceleration().x = (float) (-alfaDeepMovement.getPosition().x*k*3);
		alfaDeepMovement.getAcceleration().y = -alfaDeepMovement.getPosition().y*k;
		
		movement.updateBefore(delta);
		movement.updateAfter(delta);
		alfaDeepMovement.updateBefore(delta);
		alfaDeepMovement.updateAfter(delta);
		movement.getVelocity().x *= drag.x;
		movement.getVelocity().y *= drag.x;
		alfaDeepMovement.getVelocity().x *= drag.y;
		alfaDeepMovement.getVelocity().y *= drag.z;
		//TODO
		if (isRunning) {
			time += delta;
			if (timePeriod > period) {
				timePeriod -= period;
			}
		}
	}
	
	public Vector2 getPosition() {
		return movement.position;
	}
	public Vector2 getAlfaDeep() {
		return alfaDeepMovement.position;
	}
}
