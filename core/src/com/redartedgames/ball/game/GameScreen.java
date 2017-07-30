package com.redartedgames.ball.game;

import com.redartedgames.ball.screen.MyScreen;
import com.redartedgames.ball.screen.ScreenRenderer;

public class GameScreen extends MyScreen{

	public GameScreen(int width, int height) {
		super(width, height);
		world = new GameWorld();
		screenRenderer = new ScreenRenderer(world, camera);
	}

}
