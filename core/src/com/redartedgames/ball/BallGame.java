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
import com.redartedgames.ball.consts.LauncherSettings;
import com.redartedgames.ball.database.ConversationsBase;
import com.redartedgames.ball.database.EasterEggsBase;
import com.redartedgames.ball.game.GameScreen;
import com.redartedgames.ball.game.GameWorld;
import com.redartedgames.ball.game.InputHandler;
import com.redartedgames.ball.menu.MenuInputHandler;
import com.redartedgames.ball.menu.MenuScreen;
import com.redartedgames.ball.menu.MenuWorld;
import com.redartedgames.ball.screen.Consts;
import com.redartedgames.ball.sound.SoundHandler;


public class BallGame extends Game{
	
	private int screenId;
	private GameScreen gameScreen;
	private MenuScreen menuScreen;
	private GameWorld gameWorld;
	private MenuWorld menuWorld;
	private InputHandler gameHandler;
	private MenuInputHandler menuHandler;
	private StateMachine state;
	private ColorGenerator cg;
	private Color bg;
	private Vector3 v;
	private float time;
	@Override
	public void create () {
		SoundHandler.load();
		EasterEggsBase.tryFirstLoad();
		state = new StateMachine() {
			@Override public void setMenu() { setMenuMy(); }
			@Override public void setGame() { setGameMy(); }
		};
		
		screenId = 1;
		gameScreen = new GameScreen(Consts.gameWidth, Consts.gameHeight);
		menuScreen = new MenuScreen(Consts.gameWidth, Consts.gameHeight);
		gameWorld = (GameWorld)gameScreen.getWorld();
		menuWorld = (MenuWorld)menuScreen.getWorld();
		gameWorld.setStateMachine(state);
		menuWorld.setStateMachine(state);
		gameHandler = new InputHandler(gameWorld);
		menuHandler = new MenuInputHandler(menuWorld);
		Gdx.input.setInputProcessor(menuHandler);
		Gdx.gl.glClearColor(240f/256, 240f/256, 240f/256, 1);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.input.setCursorCatched(true);
		cg = new ColorGenerator(4.5f);
		bg = new Color(cg.generateNextColor(0.3f, 0.7f, 0));
		time = 0;
		setMenuMy();

	}

	@Override
	public void render () {
		switch(screenId) {
		case 1: {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			Gdx.gl.glClearColor(62/255f, 136/255f, 133/255f, 1);
			for(int i = 0; i < 5; i++)
			menuScreen.update(0.01f);
			menuScreen.render();
			break;
		}
		case 2: {
			//time += Gdx.graphics.getDeltaTime()*10;
			v = ColorGenerator.hsvToRgb((90 + time)%360, 0.8f, 0.1f);
			if (LauncherSettings.AdvancedGrapghic) Gdx.gl.glClearColor(v.x, v.y, v.z, 1);
			else Gdx.gl.glClearColor(1, 1, 1, 1);
			
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			for(int i = 0; i < 40; i++)
			gameScreen.update(0.01f);
			
			gameScreen.render();
			break;
		}
		}
		
		
		//if(Gdx.input.isKeyJustPressed(Input.Keys.K)){
			//gameScreen.screenShaker.shake(50);
		//}
	}
	
	public void setMenuMy() {
		screenId = 1;
		menuScreen.restart();
		Gdx.input.setInputProcessor(menuHandler);
		SoundHandler.playMenuSd();
	}
	
	public void setGameMy() {
		screenId = 2;
		gameWorld.restartLvl();
		gameWorld.nextLvlRect.visibility = 1f;
		Gdx.input.setInputProcessor(gameHandler);
		time = 0;
		SoundHandler.playGameSdNostalgic();
	}
	
	@Override
	public void dispose () {
		gameScreen.dispose();
	}
}
