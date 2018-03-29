package com.redartedgames.ball.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.redartedgames.ball.colors.ColorGenerator;
import com.redartedgames.ball.myobjects.Planet;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.screen.Consts;
import com.redartedgames.ball.screen.ScreenRenderer;
import com.redartedgames.ball.screen.World;

public class GameRenderer extends ScreenRenderer{
	
	int f = 2;
	
	
	SpriteObject tloswiatlo, desen;
	Planet planet, planet2, planet3;
	GameObject bg, planet1obj, planet2obj, desenObj;
	ArrayList<byte[]> pixelslist;
	byte[][] pixels;
	byte[] pixels2;
	byte[] pixels3;
	Pixmap pixmap;
	int length;
	int scr_I;
	Random rand;
	int h1[], h2[];
	int h1l, h2l;
	ColorGenerator cg;
	Vector3 cv1, cv21, cv22, cv23;
	Color c1, c21, c22, c23;
	float time;
	
	public GameRenderer(World world, OrthographicCamera camera) {
		super(world, camera);
		time = 0;
		cv1 = ColorGenerator.hsvToRgb((180+time)%360, 0.5f, 0.5f);
		c1 = new Color(cv1.x, cv1.y, cv1.z, 1);
		cv21 = ColorGenerator.hsvToRgb((180+time)%360, 0.5f, 0.5f);
		c22 = new Color(cv1.x, cv1.y, cv1.z, 1);
		cv22 = ColorGenerator.hsvToRgb((180+time)%360, 0.5f, 0.5f);
		c22 = new Color(cv1.x, cv1.y, cv1.z, 1);
		cv23 = ColorGenerator.hsvToRgb((180+time)%360, 0.5f, 0.5f);
		c23 = new Color(cv1.x, cv1.y, cv1.z, 1);
		tloswiatlo = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0);
		tloswiatlo.addTexture("graphic/bg/tloswiatlo.png").visibility = 0.2f;
		desen = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0);
		desen.addTexture("graphic/bg/desen.png").visibility = 0.4f;
		desen.sclX = 4; desen.sclY = 2.2f;
		desenObj = new GameObject(0, 0, 0, null);
		desenObj.addSprite(desen);
		cg = new ColorGenerator(4.5f);
		planet = new Planet((int)(Consts.gameWidth*0.2f), (int)(Consts.gameHeight*0.83f), cv21, 50);
		planet2 = new Planet((int)(Consts.gameWidth*0.5f), (int)(Consts.gameHeight*0.93f), cv22, 80);
		planet3 = new Planet((int)(Consts.gameWidth*0.8f), (int)(Consts.gameHeight*0.77f), cv23, 100);
		cg.generateNextColorGenerator(0.4f, 0.2f, -0.6f);
		bg = new GameObject(0, 0, 0, null);
		bg.addSprite(tloswiatlo);
		planet1obj = new GameObject(0, 0, 0, null);
		planet2obj = new GameObject(0, 0, 0, null);
		scr_I = 0;
		h1l = Consts.gameWidth/6;
		h2l = Consts.gameWidth/5;
		h1 = new int[h1l];
		h2 = new int[h2l];
		rand = new Random();
		int z = 0;
		for(int i2 = 0; i2 < h1l; i2++) {
			z += rand.nextInt(8) - 4;
			if (z < 0) z = 0;
			h1[i2] = (int) (Math.sin((i2+rand.nextInt(2)-1)/150f)*50 + Math.sin((i2+rand.nextInt(2)-1)/15f)*23) + (int)(Math.sqrt(i2))*5 + z*2 + 73;
		}
		z = 0;
		for(int i2 = 0; i2 < h2l; i2++) {
			z += rand.nextInt(8) - 4;
			if (z < 0) z = 0;
			h2[i2] = (int) (Math.sin((i2+rand.nextInt(2)-1)/90f)*70 + Math.sin((i2+rand.nextInt(2)-1)/15f)*40)  + (int)(Math.sqrt(i2))*8 + z*2 + 110;
		}
		
		//wwwwpixels = new byte[200][1280*720*100];
	}
	public void render() {
		Gdx.gl.glEnable(GL20.GL_BLEND);
		
		//time += Gdx.graphics.getDeltaTime()*10;
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		cv1 = ColorGenerator.hsvToRgb((30+time)%360, 0.3f, 0.9f);
		c1 = new Color(cv1.x, cv1.y, cv1.z, 1);
		cv21 = ColorGenerator.hsvToRgb((120 - 30+time)%360, 0.9f, 0.6f);
		c22 = new Color(cv1.x, cv1.y, cv1.z, 1);
		cv22 = ColorGenerator.hsvToRgb((120 + 0 +time)%360, 0.9f, 0.6f);
		c22 = new Color(cv1.x, cv1.y, cv1.z, 1);
		cv23 = ColorGenerator.hsvToRgb((120 + 30 +time)%360, 0.9f, 0.6f);
		c23 = new Color(cv1.x, cv1.y, cv1.z, 1);
		
		planet.updateColor(cv21);
		planet2.updateColor(cv22);
		planet3.updateColor(cv23);
		
		planet.updateLast(0.01f, 0, 0);
		planet2.updateLast(0.01f, 0, 0);
		planet3.updateLast(0.01f, 0, 0);
		cg.generateNextColor(0, 0, 0.01f/10);
		


		batch.begin();
		
		planet.render(batch, 0);
		planet2.render(batch, 0);
		planet3.render(batch, 0);
		desenObj.render(batch, 0);
		
		batch.end();

		sr.begin(ShapeType.Filled);
		
		planet.render(sr, 0);
		planet2.render(sr, 0);
		planet3.render(sr, 0);
		
		
		
		drawShadyRect(sr, 0, 100, Consts.gameWidth, 200, (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 100, 60);
		for(int i = 0; i < h2l/2; i++) {
			drawShadyRect(sr, 0+i*10, 300, 10, (int)(h2[i*2]*1), (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 60, 60-h2[i*2]/5);
		}
		
		drawShadyRect(sr, 0, 0, Consts.gameWidth, 200, (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 100, 70);
		for(int i = 0; i < h1l/2; i++) {
			drawShadyRect(sr, 0+i*12, 200, 12, h1[i*2], (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 70, 70-h1[i*2]/5);
		}
		sr.end();

		batch.begin();

		for (int i = 0; i < GameObject.priorities; i++) {
			for (int j = 0; j < world.getGameObjects().size(); j++) {
				//world.getGameObjects().get(j).render(batch, i);
				world.getGameObjects().get(j).render(batch, i);
			}
		}
		batch.end();
		
		
		Gdx.gl.glDisable(GL20.GL_BLEND);
		if(false) {
		if (scr_I < 450*f) {
			if (pixels2 == null) {
				pixels2 = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);
				length = pixels2.length;
				pixels = new byte[250][length];
			}
			else if (scr_I%f == 0){
				pixels[scr_I/f] = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);
			}

			scr_I++;
		}
		}
	}
	
	public void dispose() {
		//saveScrs();
	}
	
	
	private void makeAndSaveScr() {
		
	}
	
	private void saveScrs() {
		int i2 = 0;
		//scr_I = 0;
	for (i2 = 0; i2 < scr_I/f; i2++) {
			pixmap = new Pixmap(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), Pixmap.Format.RGBA8888);
			BufferUtils.copy(pixels[i2], 0, pixmap.getPixels(), pixels[i2].length);
			PixmapIO.writePNG(Gdx.files.local("screenshot/mypixmap" + i2 + ".png"), pixmap);
			pixmap.dispose();
		}
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
