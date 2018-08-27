package com.redartedgames.ball.menu;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.game.GameWorld;
import com.redartedgames.ball.graphicgenerators.TreeGenerator;
import com.redartedgames.ball.myobjects.Planet;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.screen.Consts;
import com.redartedgames.ball.screen.ScreenRenderer;
import com.redartedgames.ball.screen.World;

public class MenuRenderer extends ScreenRenderer{

	Color c1 = new Color(107/255f, 224/255f, 190/255f, 1);
	int h1[], h2[];
	int h1l, h2l;
	Random rand;
	
	SpriteObject tloswiatlo, desen, bgshade;
	GameObject bg, desenObj;
	
	public MenuRenderer(World world, OrthographicCamera camera) {
		super(world, camera);
		bgshade = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0);
		bgshade.addTexture("graphic/bg/bgshade.png").visibility = 1f;
		tloswiatlo = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0);
		tloswiatlo.addTexture("graphic/bg/tloswiatlo.png").visibility = 0.2f;
		desen = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0);
		desen.addTexture("graphic/bg/desen.png").visibility = 0.7f;
		desen.sclX = 4; desen.sclY = 2.2f;
		desenObj = new GameObject(0, 0, 0, null);
		desenObj.addSprite(desen);
		bgshade.setColor(1, 1, 1, 0f);

		h1l = Consts.gameWidth;
		h2l = Consts.gameWidth;
		h1 = new int[h1l];
		h2 = new int[h2l];
		rand = new Random();
		int z = 0;
		int v = 2;
		for(int i2 = 0; i2 < h1l; i2++) {
			z += rand.nextInt(8) - 4;
			if (z < 0) z = 0;
			h1[i2] = (int) (Math.sin((i2+rand.nextInt(2)-1)/150f*v)*50 + Math.sin((i2+rand.nextInt(2)-1)/15f*v)*23) + (int)(Math.sqrt(i2))*5 + z*2 + 73-100;
		}
		z = 0;
		for(int i2 = 0; i2 < h2l; i2++) {
			z += rand.nextInt(8) - 4;
			if (z < 0) z = 0;
			h2[i2] = (int) (Math.sin((i2+rand.nextInt(2)-1)/90f*v)*70 + Math.sin((i2+rand.nextInt(2)-1)/15f*v)*40)  + (int)(Math.sqrt(i2))*8 + z*2 + 110-100;
		}
	}
	
	public void render() {
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		float s1 = 400;
		float s2 = 200;
		float dxf = 0;
		float dxf2 = 0;
		int dx = 0;
		int dx2 = 0;
		
		
		batch.begin();
		desenObj.render(batch, 0);

		
		batch.end();
		
		
		sr.begin(ShapeType.Filled);
		drawShadyRect(sr, 0, 100, Consts.gameWidth, 200, (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 100, 60);
		for(int i = 1; i < h2l/2 - dx2-1; i++) {
			drawShadyRect(sr, -20+i*10, 300, 10, (int)(dxf2*h2[i + dx2] + (1-dxf2)*h2[i + dx2-1]), (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 60, (int) (60-(dxf2*h2[i + dx2] + (1-dxf2)*h2[i + dx2-1])/5));
		}
		
		drawShadyRect(sr, 0, 0, Consts.gameWidth, 200, (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 100, 70);
		for(int i = 1; i < h1l-dx-1; i++) {
			drawShadyRect(sr, -20+i*12, 200, 12, (int)(dxf*h1[i + dx] + (1-dxf)*h1[i + dx-1]), (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 70, (int) (70-(dxf*h1[i + dx] + (1-dxf)*h1[i + dx-1])/5));
		}

		sr.end();
		
		super.render();
		
	}
	
	private void drawShadyRect(ShapeRenderer sr, int x, int y, int width, int height, int r, int g, int b, int alfa, int shadeUp, int shadeDown) {
		sr.setColor(r/256f, g/256f, b/256f, alfa/100f);
		sr.rect(x, y, x + width/2, y+height/2, width, height, 1, 1, 0,
				new Color(r/256f*shadeUp/100f, g/256f*shadeUp/100f, b/256f*shadeUp/100f, 1),
				new Color(r/256f*shadeUp/100f, g/256f*shadeUp/100f, b/256f*shadeUp/100f, 1),
				new Color(r/256f*shadeDown/100f, g/256f*shadeDown/100f, b/256f*shadeDown/100f, 1),
				new Color(r/256f*shadeDown/100f, g/256f*shadeDown/100f, b/256f*shadeDown/100f, 1));
		
		//(x, y, Consts.gameWidth, Consts.gameHeight);
	}

}
