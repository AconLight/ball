package com.redartedgames.ball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.redartedgames.ball.colors.ColorGenerator;
import com.redartedgames.ball.database.EasterEggsBase;
import com.redartedgames.ball.game.GameScreen;
import com.redartedgames.ball.game.GameWorld;
import com.redartedgames.ball.game.InputHandler;
import com.redartedgames.ball.screen.Consts;


public class BallGame extends Game{
	
	private GameScreen gameScreen;
	private ColorGenerator cg;
	private Color bg;
	private Vector3 v;
	private float time;
	@Override
	public void create () {
		EasterEggsBase.tryFirstLoad();
		EasterEggsBase.load();
		gameScreen = new GameScreen(Consts.gameWidth, Consts.gameHeight);
		Gdx.input.setInputProcessor(new InputHandler((GameWorld)gameScreen.getWorld()));
		Gdx.gl.glClearColor(240f/256, 240f/256, 240f/256, 1);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		cg = new ColorGenerator(4.5f);
		bg = new Color(cg.generateNextColor(0.3f, 0.7f, 0));
		time = 0;
		

	}

	@Override
	public void render () {
		time += Gdx.graphics.getDeltaTime()*10;
		v = ColorGenerator.hsvToRgb((90 + time)%360, 0.8f, 0.1f);
		Gdx.gl.glClearColor(v.x, v.y, v.z, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		for(int i = 0; i < 40; i++)
		gameScreen.update(0.01f);
		
		gameScreen.render();
		
		//if(Gdx.input.isKeyJustPressed(Input.Keys.K)){
			//gameScreen.screenShaker.shake(50);
		//}
	}
	
	@Override
	public void dispose () {
		gameScreen.dispose();
	}
}
