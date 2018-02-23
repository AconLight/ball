package com.redartedgames.ball.game;

import com.redartedgames.ball.editor.EditorPicker;
import com.redartedgames.ball.editor.ObjectPick;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.screen.MyScreen;
import com.redartedgames.ball.screen.ScreenRenderer;

public class GameScreen extends MyScreen{

	public GameScreen(int width, int height) {
		super(0, 0, width, height);
		world = new GameWorld();
		screenRenderer = new GameRenderer(world, camera);
	}
	
	
	//nwm, czy to wgl bedzie uzywane... chyba lepiej add jakis zrobic
	public void loadObjectsToEditorOptions(EditorPicker ep) {
		for (GameObject obj : world.getGameObjects()) {
			//ep.add(new ObjectPick("object", obj, null));
		}
	}
	
	public void addGameObject(GameObject object) {
		world.getGameObjects().add(object);
	}
	
	public void removeGameObject(GameObject object) {
		world.getGameObjects().remove(object);
	}

}
