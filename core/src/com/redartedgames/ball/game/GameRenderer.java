package com.redartedgames.ball.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.redartedgames.ball.colors.ColorGenerator;
import com.redartedgames.ball.consts.LauncherSettings;
import com.redartedgames.ball.graphicgenerators.TreeGenerator;
import com.redartedgames.ball.myobjects.Cloud;
import com.redartedgames.ball.myobjects.Freeze;
import com.redartedgames.ball.myobjects.Imp;
import com.redartedgames.ball.myobjects.Planet;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.screen.Consts;
import com.redartedgames.ball.screen.ScreenRenderer;
import com.redartedgames.ball.screen.World;

public class GameRenderer extends ScreenRenderer{
	
	int f = LauncherSettings.frames;
	
	int currentLevelId;
	
	int treePosX = 0;
	
	
	SpriteObject tloswiatlo, desen, bgshade;
	Planet planet, planet2, planet3;
	Cloud cloud1, cloud2, cloud3;
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
	Texture treeTex, motylek, impsCollectionTex;
	float levelDelta = 10;
	
	ArrayList<Planet> planets;
	
	GameWorld world;
	
	int impsDx;
	
	public GameRenderer(World world, OrthographicCamera camera) {
		super(world, camera);

		if (LauncherSettings.AdvancedGrapghic) initializeAdvancedGraphic();
		
		this.world = (GameWorld) world;
		currentLevelId = this.world.levelId;

	} 
	
	public void render() {
		if (LauncherSettings.AdvancedGrapghic) renderAdvancedGraphic();
		else renderPrimitiveGraphic();
	}
	
	public void renderPrimitiveGraphic() {
		batch.begin();
		for (int i = 0; i < GameObject.priorities; i++) {
			for (int j = 0; j < world.getGameObjects().size(); j++) {
				//world.getGameObjects().get(j).render(batch, i);
				world.getGameObjects().get(j).render(batch, i);
			}
		}
		world.breakWindow.storyText.renderText(batch, 100, Consts.gameHeight-100);
		world.blackScreenAnimation.render(batch);
		batch.end();
	}
	
	public void loadNextLvl() {
		currentLevelId = world.levelId;
		rand = new Random(2137);
		int z = 0;
		int v = 2;
		for(int i2 = 0; i2 < h1l; i2++) {
			z += rand.nextInt(8) - 4;
			if (z < 0) z = 0;
			h1[i2] = (int) (Math.sin((i2 + currentLevelId*levelDelta+rand.nextInt(2)-1)/150f*v)*50 + Math.sin((i2 + currentLevelId*levelDelta+rand.nextInt(2)-1)/15f*v)*23) + (int)(Math.sqrt(i2))*5 + z*2 + 73;
		}
		z = 0;
		for(int i2 = 0; i2 < h2l; i2++) {
			z += rand.nextInt(8) - 4;
			if (z < 0) z = 0;
			h2[i2] = (int) (Math.sin((i2 + currentLevelId*levelDelta+rand.nextInt(2)-1)/90f*v)*70 + Math.sin((i2 + currentLevelId*levelDelta+rand.nextInt(2)-1)/15f*v)*40)  + (int)(Math.sqrt(i2))*8 + z*2 + 110;
		}
		
		if ((int)(750/12f - currentLevelId*levelDelta + treePosX/12f) < 0) {
			treePosX += Consts.gameWidth;
			//  treeTex = TreeGenerator.generate();
		}
		
	}
	
	
	private void initializeAdvancedGraphic() {
		treeTex = TreeGenerator.generate();
		planets = new ArrayList<>();
		motylek = new Texture("graphic/motylek.png");
		impsCollectionTex = new Texture("graphic/impscollection.png");
		time = 0;
		cv1 = ColorGenerator.hsvToRgb((180+time)%360, 0.5f, 0.5f);
		c1 = new Color(cv1.x, cv1.y, cv1.z, 1);
		cv21 = ColorGenerator.hsvToRgb((180+time)%360, 0.5f, 0.5f);
		c22 = new Color(cv1.x, cv1.y, cv1.z, 1);
		cv22 = ColorGenerator.hsvToRgb((180+time)%360, 0.5f, 0.5f);
		c22 = new Color(cv1.x, cv1.y, cv1.z, 1);
		cv23 = ColorGenerator.hsvToRgb((180+time)%360, 0.5f, 0.5f);
		c23 = new Color(cv1.x, cv1.y, cv1.z, 1);
		
		cloud1 = new Cloud(Consts.gameWidth/2, Consts.gameHeight/2, 0, null);
		cloud2 = new Cloud(Consts.gameWidth/2, Consts.gameHeight/2 - 200, 0, null);
		cloud3 = new Cloud(Consts.gameWidth/2, Consts.gameHeight/2 - 400, 0, null);
		
		bgshade = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0);
		bgshade.addTexture("graphic/bg/bgshade.png").visibility = 1f;
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
		scr_I = -200;
		h1l = Consts.gameWidth;
		h2l = Consts.gameWidth;
		h1 = new int[h1l];
		h2 = new int[h2l];
		rand = new Random(2137);
		int z = 0;
		int v = 2;
		for(int i2 = 0; i2 < h1l; i2++) {
			z += rand.nextInt(8) - 4;
			if (z < 0) z = 0;
			h1[i2] = (int) (Math.sin((i2 + currentLevelId*levelDelta+rand.nextInt(2)-1)/150f*v)*50 + Math.sin((i2 + currentLevelId*levelDelta+rand.nextInt(2)-1)/15f*v)*23) + (int)(Math.sqrt(i2))*5 + z*2 + 73;
		}
		z = 0;
		for(int i2 = 0; i2 < h2l; i2++) {
			z += rand.nextInt(8) - 4;
			if (z < 0) z = 0;
			h2[i2] = (int) (Math.sin((i2 + currentLevelId*levelDelta+rand.nextInt(2)-1)/90f*v)*70 + Math.sin((i2 + currentLevelId*levelDelta+rand.nextInt(2)-1)/15f*v)*40)  + (int)(Math.sqrt(i2))*8 + z*2 + 110;
		}
	}
	

	
	public void renderAdvancedGraphic() {
		Gdx.gl.glEnable(GL20.GL_BLEND);
		
		if (world.levelId > currentLevelId && !world.isNextLvl) {
			loadNextLvl();
		}
		cloud1.getPosition().x = (float) (-world.player.getPosition().x/60 + Consts.gameWidth/2 + 10*Math.sin(world.cloudT/100));
		cloud2.getPosition().x = (float) (-world.player.getPosition().x/40 + Consts.gameWidth/2 + 20*Math.sin(world.cloudT/100 + 2));
		cloud3.getPosition().x = (float) (-world.player.getPosition().x/20 + Consts.gameWidth/2 + 30*Math.sin(world.cloudT/100 + 1));
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
		
		
		bgshade.visibility = ((GameWorld)world).conversationShade;
		
		planet.updateColor(cv21);
		planet2.updateColor(cv22);
		planet3.updateColor(cv23);
		int d = 1;
		if (!((GameWorld) world).isForward) {
			d = -1;
		}
		planet.updateLast(d*0.01f, 0, 0);
		planet2.updateLast(d*0.01f, 0, 0);
		planet3.updateLast(d*0.01f, 0, 0);
		cg.generateNextColor(0, 0, 0.01f/10);
		
		for (Planet p: planets) {
			//p.updateLast(0.01f, 0, 0);
		}


		batch.begin();
		
		planet.render(batch, 0);
		planet2.render(batch, 0);
		planet3.render(batch, 0);
		desenObj.render(batch, 0);

		for (Planet p: planets) {
			p.render(batch, 0);
		}
		
		cloud1.render(batch);
		
		batch.end();

		sr.begin(ShapeType.Filled);
		
		planet.render(sr, 0);
		planet2.render(sr, 0);
		planet3.render(sr, 0);
		//Gdx.app.log("gr", planets.size() + "");

		
		float s1 = 400;
		float s2 = 200;
		float dxf = (((GameWorld)world).player.getPosition().x%s2/s2);
		float dxf2 = (((GameWorld)world).player.getPosition().x%s1/s1);
		int dx = (int)(((GameWorld)world).player.getPosition().x/s2);
		int dx2 = (int)(((GameWorld)world).player.getPosition().x/s1);
		float dxfl = (((GameWorld)world).player.getPosition().x/s2);
		float dxfl2 = (((GameWorld)world).player.getPosition().x/s1);
		
		drawShadyRect(sr, 0, 100, Consts.gameWidth, 200, (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 100, 60);
		for(int i = 1; i < h2l/2 - dx2-1; i++) {
			drawShadyRect(sr, -20+i*10, 300, 10, (int)(dxf2*h2[i + dx2] + (1-dxf2)*h2[i + dx2-1]), (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 60, (int) (60-(dxf2*h2[i + dx2] + (1-dxf2)*h2[i + dx2-1])/5));
		}
		sr.end();
		
		batch.begin();
		
		float q = 2.5f;
		batch.setColor(c1.r/q, c1.g/q, c1.b/q, 1);
		float treeHeight = 0;
		treeHeight = h1[(int)(750/12f - currentLevelId*levelDelta + treePosX/12f)] -190;
		
		batch.draw(treeTex, -dxfl*12 - currentLevelId*levelDelta*12 + treePosX, treeHeight);
		batch.setColor(0.4f, 0.6f, 0.7f, 0.1f);
		float k = 1.6f;
		for(Vector2 v: TreeGenerator.tab) {
			
			batch.draw(GameObject.dotTex, treePosX-dxfl*12 - currentLevelId*levelDelta*12+v.x, v.y+treeHeight, 6*k, 6*k);
			batch.draw(GameObject.dotTex, treePosX-dxfl*12 - currentLevelId*levelDelta*12+v.x-6*k, v.y-6*k+treeHeight,6*k, 6*k);
			batch.draw(GameObject.dotTex, treePosX-dxfl*12 - currentLevelId*levelDelta*12+v.x-3*k, v.y+treeHeight, 3*k, 3*k);
			batch.draw(GameObject.dotTex, treePosX-dxfl*12 - currentLevelId*levelDelta*12+v.x, v.y-3*k+treeHeight, 3*k, 3*k);
			batch.draw(GameObject.dotTex, treePosX-dxfl*12 - currentLevelId*levelDelta*12+v.x- 1*k, v.y-1*k+treeHeight, 2*k, 2*k);
			
		}
		
		
		cloud2.render(batch);		
		batch.end();

		
		sr.begin(ShapeType.Filled);
		
		drawShadyRect(sr, 0, 0, Consts.gameWidth, 200, (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 100, 70);
		for(int i = 1; i < h1l-dx-1; i++) {
			drawShadyRect(sr, -20+i*12, 200, 12, (int)(dxf*h1[i + dx] + (1-dxf)*h1[i + dx-1]), (int)(c1.r*256), (int)(c1.g*256), (int)(c1.b*256), 100, 70, (int) (70-(dxf*h1[i + dx] + (1-dxf)*h1[i + dx-1])/5));
		}
		sr.end();
		
		batch.begin();
		cloud3.render(batch);
		
		batch.setColor(1-((GameWorld) world).player.playerSprite.R, 
				1-((GameWorld) world).player.playerSprite.G, 
				1-((GameWorld) world).player.playerSprite.B, (1-((GameWorld) world).player.isForwardFac)*0.4f);
		batch.draw(GameObject.dotTex, 0, 0, Consts.gameWidth, Consts.gameHeight);
		//batch.setColor(0.7f, 0.7f, 0.7f, 1);
		//batch.draw(GameObject.dotTex, 0, 0, Consts.gameWidth, Consts.gameHeight);
		for (int i = 0; i < GameObject.priorities; i++) {
			for (int j = 0; j < world.getGameObjects().size(); j++) {
				if (world.getGameObjects().get(j).getPriority() >= i) {
				//world.getGameObjects().get(j).render(batch, i);
				world.getGameObjects().get(j).render(batch, i);
				}
			}
		}
		
		impsDx = 60;
		float scl = 0.8f;
		int size = ((GameWorld) world).impsCollection.getImps().size();
		if (size != 0) {
			batch.setColor(1f, 1f, 1f, 1f);
			//batch.draw(impsCollectionTex, Consts.gameWidth- size*108*scl - 100*scl, Consts.gameHeight-200*scl, 650*scl, 200*scl);
			batch.setColor(1, 1, 1, 1);
		}
		batch.setColor(0, 0, 0, 0.5f);
		batch.draw(GameObject.dotTex, Consts.gameWidth-size*108, Consts.gameHeight-150, size*108, 150);
		
		for(Imp imp: ((GameWorld) world).impsCollection.getImps()) {
			batch.setColor(1f, 1f, 1f, 1f);
			batch.draw(motylek, Consts.gameWidth-(size*108 + 68 -impsDx)*scl, Consts.gameHeight-(150)*scl, 68*scl, 170*scl);
			batch.setColor(1f, 1f, 1f, 1f);
			if(!imp.isSpawned) {
				imp.setScl(scl);
				imp.render(batch, 0, Consts.gameWidth-(size*108 + 34 -impsDx)*scl, Consts.gameHeight-(120)*scl);
				imp.setScl(1);
			}
			impsDx += 68 + 40;
		}
		
		bgshade.render(batch, 0);
		world.breakWindow.storyText.renderText(batch, 100, Consts.gameHeight-100);
		world.blackScreenAnimation.render(batch);
		batch.end();
		
		
		Gdx.gl.glDisable(GL20.GL_BLEND);
		if(scr_I < 350*f && LauncherSettings.MakeGIF) {
		if (scr_I >= 0) {
			if (pixels2 == null) {
				pixels2 = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);
				length = pixels2.length;
				pixels = new byte[350][length];
			}
			else if (scr_I%f == 0){
				pixels[scr_I/f] = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);
			}

			
		}
		scr_I++;
		}
		
	}
	
	public void dispose() {
		if (LauncherSettings.MakeGIF) saveScrs();
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
