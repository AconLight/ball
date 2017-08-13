package com.redartedgames.ball.screen;

import java.util.ArrayList;

import com.redartedgames.ball.objects.GameObject;

public class World {

	protected ArrayList<GameObject> gameObjects;
	
	public World() {
		gameObjects = new ArrayList<GameObject>();
	}
	
	public void update(float delta) {
		for (int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).updateBefore(delta, 0, 0);
		}
		for (int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).applyPhysicsToAcceleration();
		}
		for (int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).updateAfter(delta, 0, 0);
		}
		for (int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).updateLast(delta, 0, 0);
		}
	}
	
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	protected void addGameObject(GameObject obj) {
		gameObjects.add(obj);
	}
	
	
}
