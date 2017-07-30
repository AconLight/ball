package com.redartedgames.ball.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.redartedgames.ball.screen.World;

public class InputHandler implements InputProcessor{

	private GameWorld world;
	public InputHandler(GameWorld world) {
		this.world = world;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		switch(keycode) {
		case Keys.D: {
			world.ball.getVelocity().add(50, 0);
			break;
		}
		case Keys.A: {
			world.ball.getVelocity().add(-50, 0);
			break;
		}
		case Keys.W: {
			world.ball.getVelocity().add(0, 50);
			break;
		}
		case Keys.S: {
			world.ball.getVelocity().add(0, -50);
			break;
		}
		case Keys.SPACE: {
			world.t = -world.t;
			break;
		}
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
