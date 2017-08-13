package com.redartedgames.ball.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.screen.Consts;
import com.redartedgames.ball.screen.World;

public class InputHandler implements InputProcessor{

	private GameWorld world;
	public InputHandler(GameWorld world) {
		this.world = world;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		switch(keycode) {
		
		case Keys.A: {
			world.player.addXAxis(-300);
			break;
		}
		case Keys.D: {
			world.player.addXAxis(300);
			break;
		}
		case Keys.SPACE: {
			for (ReversableObject obj : world.reversableObjects) {
				obj.setIsForward(false);
			}
			break;
		}
		case Keys.F: {
			world.rect.move();
			break;
		}
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		switch(keycode) {
		case Keys.A: {
			world.player.addXAxis(300);
			break;
		}
		case Keys.D: {
			world.player.addXAxis(-300);
			break;
		}
		case Keys.SPACE: {
			for (ReversableObject obj : world.reversableObjects) {
				obj.setIsForward(true);
			}
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
