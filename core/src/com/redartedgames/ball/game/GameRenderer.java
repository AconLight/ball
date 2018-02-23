package com.redartedgames.ball.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.screen.Consts;
import com.redartedgames.ball.screen.ScreenRenderer;
import com.redartedgames.ball.screen.World;

public class GameRenderer extends ScreenRenderer{
	SpriteObject tloswiatlo;
	GameObject bg;
	public GameRenderer(World world, OrthographicCamera camera) {
		super(world, camera);
		tloswiatlo = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0);
		tloswiatlo.addTexture("graphic/bg/tloswiatlo.png").visibility = 0.5f;
		bg = new GameObject(0, 0, 0, null);
		bg.addSprite(tloswiatlo);
	}
	public void render() {
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		
		sr.begin(ShapeType.Filled);
		
		//rysowanie t³a
		sr.setColor(213f/255, 213f/255, 213f/255, 1);
		sr.rect(0, 0, Consts.gameWidth, Consts.gameHeight);
		sr.setColor(231f/255, 98f/255, 0f/255, 0.7f);
		sr.rect(0, 0, Consts.gameWidth, Consts.gameHeight);
		sr.end();
		
		batch.begin();
		
		
		bg.render(batch, 0);
		batch.end();
		
		
		sr.begin(ShapeType.Filled);
		for (int i = 0; i < GameObject.priorities; i++) {
			for (int j = 0; j < world.getGameObjects().size(); j++) {
				//world.getGameObjects().get(j).render(batch, i);
				world.getGameObjects().get(j).render(sr, i);
			}
		}
		sr.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}

}
