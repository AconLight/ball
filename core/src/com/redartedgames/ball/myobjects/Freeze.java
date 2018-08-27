package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.redartedgames.ball.objects.GameObject;

public class Freeze extends GameObject{

	int[][] pixels;
	ArrayList<Vector3> nextGen;
	ArrayList<Vector3> tmpNextGen;
	Random rand = new Random();
	int width, height;
	float time, delay;
	boolean hasStarted;
	int thickness = 5;
	public Freeze(float x, float y, int id, GameObject parent, int width, int height) {
		super(x, y, id, parent);
		priority = 2;
		nextGen = new ArrayList<>();
		tmpNextGen = new ArrayList<>();
		hasStarted = false;
		this.width = width/thickness;
		this.height = height/thickness;
		width = Math.abs(width/thickness);
		height = Math.abs(height/thickness);
		pixels = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pixels[i][j] = 0;
				if (i == 0) pixels[i][j] = 2;
				if (i == width-1) pixels[i][j] = 2;
				if (j == 0) pixels[i][j] = 2;
				if (j == height-1) pixels[i][j] = 2;
			}
		}
		time = 0;
		delay = 0.06f;
	}
	
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
		
		if (hasStarted) time += delta;
		if (time > delay) {
			
			time -= delay;
			delay *= 1.05f;
			tmpNextGen.clear();
			for (Vector3 v: nextGen) {
				//Gdx.app.log("Freeze update",  "" + (int)v.x + ", " + (int)v.y + ", " + (int)v.z);
				generateOne((int)v.x, (int)v.y, (int)v.z);
			}
			nextGen.clear();
			nextGen.addAll(tmpNextGen);
			
		}
	}
	
	public void generate() {
		hasStarted = true;
		int r = Math.min(width/2, height/2);
		generateOne(width/2, height/2, r*2);
		nextGen.clear();
		nextGen.addAll(tmpNextGen);
		pixels[width/2][height/2] = 1;
	}
	
	public void generateOne(int x, int y, int r) {
		if (r <= 0) return;
		int direction = 0;
		int dx = 0, dy = 0;
		int[] colX = new int[5];
		int[] colY = new int[5];

		
		for (direction = 0; direction < 4; direction++) {

			if (direction == 0) {
				dx = 1; dy = 0;
				colX[0] = 2; colY[0] = 0;
				colX[1] = 2; colY[1] = -1;
				colX[2] = 2; colY[2] = 1;
				colX[3] = 1; colY[3] = -1;
				colX[4] = 1; colY[4] = 1;
			}
			if (direction == 1) {
				dx = 0; dy = 1;
				colX[0] = 0; colY[0] = 2;
				colX[1] = -1; colY[1] = 2;
				colX[2] = 1; colY[2] = 2;
				colX[3] = -1; colY[3] = 1;
				colX[4] = 1; colY[4] = 1;
			}
			if (direction == 2) {
				dx = -1; dy = 0;
				colX[0] = -2; colY[0] = 0;
				colX[1] = -2; colY[1] = -1;
				colX[2] = -2; colY[2] = 1;
				colX[3] = -1; colY[3] = -1;
				colX[4] = -1; colY[4] = 1;
			}
			if (direction == 3) {
				dx = 0; dy = -1;
				colX[0] = -1; colY[0] = -2;
				colX[1] = 0; colY[1] = -2;
				colX[2] = 1; colY[2] = -2;
				colX[3] = 1; colY[3] = -1;
				colX[4] = -1; colY[4] = -1;
			}
			
			boolean flaga = true;
			for (int i = 0; i < 5; i++) {
				if (pixels[x + colX[i]][y + colY[i]] == 1 || pixels[x + colX[i]][y + colY[i]] == 2) {
					
					flaga = false;
				}
				//pixels[x + colX[i]][y + colY[i]] = 1;
			}
			
			if (flaga) {
				pixels[x+dx][y+dy] = 1;
				if (!(rand.nextInt(4) == 0)) {
					//generateOne(x + dx, y + dy, r-1);
					tmpNextGen.add(new Vector3(x + dx, y + dy, r-1));
				}
			}
			
		}
		
		
		
			
	}
	
	
	
	public void render(SpriteBatch batch, int priority) {
		batch.setColor(125/255f, 230/255f, 255/255f,  1);
		float alfa;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (pixels[i][j] == 1) {
					alfa = (i-width/2)*(i-width/2) + (j-height/2)*(j-height/2);
					alfa /= 100;
					alfa = 1/(alfa+1);
					alfa *= 0.8f;
					alfa += 0.2f;
					batch.setColor(125/255f, 230/255f, 255/255f,  alfa);
					batch.draw(GameObject.dotTex, parent.getPosition().x + position.x+i*thickness - thickness*width/2, parent.getPosition().y + position.y+j*thickness - thickness*height/2, thickness, thickness);
				}
			}
		}
	}
		

}
