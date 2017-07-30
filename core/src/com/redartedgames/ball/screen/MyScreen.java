package com.redartedgames.ball.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyScreen {
	
	private Viewport viewport;
	
	public World getWorld() {
		return world;
	}

	protected OrthographicCamera camera;
	
	protected ScreenRenderer screenRenderer;
	
	protected World world;
	
	public MyScreen(int width, int height) {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		viewport = new ScreenViewport();
		viewport.setWorldSize(width, height);
		viewport.setCamera(camera);
		viewport.setScreenSize(width, height);
		world = new World();
		screenRenderer = new ScreenRenderer(world, camera);
	}
	
	public void render() {
		screenRenderer.render();
	}
	
	public void update(float delta) {
		world.update(delta);
	}

	public OrthographicCamera getCamera() {
		return camera;
	}


	
}
