package com.redartedgames.ball.menu;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.redartedgames.ball.consts.PlayerConsts;

public class MenuInputHandler implements InputProcessor {

	MenuWorld menuWorld;
	
	public MenuInputHandler(MenuWorld menuWorld) {
		this.menuWorld = menuWorld;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode) {
		
		case Keys.ENTER: {
			menuWorld.state.setGame();
			break;
		}
		case Keys.SPACE: {
			menuWorld.isForward = false;
			break;
		}
		case Keys.W: {
			menuWorld.moveSelectedUp();
			break;
		}
		case Keys.S: {
			menuWorld.moveSelectedDown();
			break;
		}
		
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.SPACE: {
			menuWorld.isForward = true;
			break;
		}
		}
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
