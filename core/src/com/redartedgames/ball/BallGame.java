package com.redartedgames.ball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.redartedgames.ball.game.GameScreen;
import com.redartedgames.ball.game.GameWorld;
import com.redartedgames.ball.game.InputHandler;
import com.redartedgames.ball.screen.Consts;


public class BallGame extends Game{
	
	private GameScreen gameScreen;
	
	@Override
	public void create () {
		gameScreen = new GameScreen(Consts.gameWidth, Consts.gameHeight);
		Gdx.input.setInputProcessor(new InputHandler((GameWorld)gameScreen.getWorld()));
		Gdx.gl.glClearColor(240f/256, 240f/256, 240f/256, 1);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(256f/256, 0f/256, 0f/256, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		for(int i = 0; i < 20; i++)
		gameScreen.update(Gdx.graphics.getDeltaTime());
		
		gameScreen.render();
		
		//if(Gdx.input.isKeyJustPressed(Input.Keys.K)){
			//gameScreen.screenShaker.shake(50);
		//}
	}
	
	@Override
	public void dispose () {

	}
}
