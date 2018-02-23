package com.redartedgames.ball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.redartedgames.ball.editor.EditorNode;
import com.redartedgames.ball.editor.EditorOption;
import com.redartedgames.ball.editor.EditorPicker;
import com.redartedgames.ball.editor.Editorable;
import com.redartedgames.ball.editor.LevelToSave;
import com.redartedgames.ball.editor.ObjectPick;
import com.redartedgames.ball.game.GameScreen;
import com.redartedgames.ball.game.GameWorld;
import com.redartedgames.ball.myobjects.Ball;
import com.redartedgames.ball.myobjects.Rect;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;
import com.redartedgames.ball.screen.Consts;

public class EditorGame extends Game {
	
	private EditorPicker levelItemCreator, levelItemPicker;
	private SpriteBatch batch;
	private ShapeRenderer renderer;
	private GameScreen gameScreen;
	private EditorNode editorNode;
	
	private InputProcessor inputProcessor = new InputProcessor() {
		
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
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean keyTyped(char character) {
			switch (character) {
			case 'a' : {
				editorNode.currentEditorable.moveSmall(-1, 0);
				break;
			}
			case 'd' : {
				editorNode.currentEditorable.moveSmall(1, 0);
				break;
			}
			case 'w' : {
				editorNode.currentEditorable.moveSmall(0, 1);
				break;
			}
			case 's' : {
				editorNode.currentEditorable.moveSmall(0, -1);
				break;
			}
			case 'A' : {
				editorNode.currentEditorable.moveBig(-1, 0);
				break;
			}
			case 'D' : {
				editorNode.currentEditorable.moveBig(1, 0);
				break;
			}
			case 'W' : {
				editorNode.currentEditorable.moveBig(0, 1);
				break;
			}
			case 'S' : {
				editorNode.currentEditorable.moveBig(0, -1);
				break;
			}
			case 'o' : {
				editorNode.currentEditorable.addSize(1, 0);
				break;
			}
			case 'p' : {
				editorNode.currentEditorable.addSize(0, 1);
				break;
			}
			case 'O' : {
				editorNode.currentEditorable.addSize(10, 0);
				break;
			}
			case 'P' : {
				editorNode.currentEditorable.addSize(0, 10);
				break;
			}
			}
			return false;
		}
		
		@Override
		public boolean keyDown(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}
	};
	
	@Override
	public void create () {
		editorNode = new EditorNode();
		Gdx.input.setInputProcessor(inputProcessor);
		gameScreen = new GameScreen(Consts.screenWidth, Consts.screenHeight);
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		
		levelItemPicker = new EditorPicker(editorNode, Consts.editorScreenWidth-200 + 20,Consts.editorScreenHeight-400, 200, 15*5, 15);
		levelItemPicker.show();
		
		levelItemCreator = new EditorPicker(editorNode, Consts.editorScreenWidth-200 + 20,Consts.editorScreenHeight-300, 200, 15*5, 15);
		levelItemCreator.add(new ObjectPick(editorNode, "Kinematic Rectangle", new Rect(400, 130, 50, 50, BehaviorMode.kinematic, null, 1))); 
		levelItemCreator.add(new ObjectPick(editorNode, "Kinematic Circle", new Ball(400, 130, 50, 50, BehaviorMode.kinematic, null, 1))); 
		levelItemCreator.show();
		
		editorNode.currentEditorable = levelItemCreator;
		editorNode.escapeEditorable = levelItemCreator;
	}
	
	@Override
	public void render() {
		keyListen();
		Gdx.gl.glClearColor( 0, 0, 0, 1 );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		batch.begin();
		//render level
		gameScreen.render();
		batch.end();
		
		//render picker
		//render creator
		renderer.begin(ShapeType.Filled);
		renderer.setColor(0.2f, 0.2f, 0.3f, 1);
		renderer.rect(Consts.screenWidth, 0, Consts.editorScreenWidth- Consts.screenWidth, Consts.editorScreenHeight);
		renderer.end();
		batch.begin();
		levelItemPicker.render(batch);
		levelItemCreator.render(batch);
		batch.end();
	}
	
	private void keyListen() { //some input int inputProcessor
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
			editorNode.currentEditorable.moveBig(0, -1);
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
			editorNode.currentEditorable.moveBig(0, 1);
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
			editorNode.currentEditorable.enter();
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			editorNode.currentEditorable.escape();
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.C)){
			GameObject obj = editorNode.currentEditorable.createCopy();
			if (obj != null) {
				gameScreen.addGameObject(obj);
				levelItemPicker.add(new ObjectPick(editorNode, obj)); 
				Gdx.app.log("EditorGame", "copy");
			}
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
			editorNode.currentEditorable = levelItemCreator;
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
			editorNode.currentEditorable = levelItemPicker;
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)){
			Gdx.app.log("Editor", "level printer\n" + LevelToSave.printLevel(gameScreen.getWorld().getGameObjects(), 0));
		}
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
