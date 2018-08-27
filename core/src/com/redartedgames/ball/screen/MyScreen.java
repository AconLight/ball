package com.redartedgames.ball.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redartedgames.ball.objects.ScreenShaker;

public class MyScreen {
	
	private Viewport viewport;
	public ScreenShaker screenShaker;
	public float visibility;
	public Vector2 camPosition;
	private Vector2 screenPosition;
	
	public World getWorld() {
		return world;
	}

	protected OrthographicCamera camera;
	
	protected ScreenRenderer screenRenderer;
	
	protected World world;
	
	public MyScreen(int x, int y, int width, int height) {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		screenShaker = new ScreenShaker(new Vector3(0.92f, 0.99f, 0.92f), 500, 20);
		screenPosition = new Vector2(x, y);
		camPosition = new Vector2(-x + width/2, -y + height/2);
		viewport = new ScreenViewport();
		viewport.setWorldSize(width, height);
		viewport.setCamera(camera);
		viewport.setScreenSize(width, height);
		world = new World();
		screenRenderer = new ScreenRenderer(world, camera);
		updateCam();
		
	}
	
	public void restart() {
		world.restart();
	}
	
	public void render() {
		screenRenderer.render();
	}
	
	public void update(float delta) {
		world.update(delta);
		screenShaker.update(delta);
		updateCam();
	}
	
	public void updateCam() {
		//Gdx.app.log("MyScreen", "" + screenShaker.getPosition().y);
		camera.position.x = -screenPosition.x + camPosition.x - screenShaker.getPosition().x;
		camera.position.y = -screenPosition.y + camPosition.y - screenShaker.getPosition().y;
		camera.direction.set(0, 0, -1);
		camera.up.set(0, 1, 0);
		camera.rotate(screenShaker.getAlfaDeep().x);

		camera.zoom = 1 + (screenShaker.getAlfaDeep().y)/(Math.abs(screenShaker.getAlfaDeep().y)+1);

		camera.update();
		screenRenderer.batch.setProjectionMatrix(camera.combined);
		screenRenderer.sr.setProjectionMatrix(camera.combined);
	}

	public OrthographicCamera getCamera() {
		return camera;
	}
	
	public void dispose() {
		screenRenderer.dispose();
	}


	
}
