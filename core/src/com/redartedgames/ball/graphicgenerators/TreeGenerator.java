package com.redartedgames.ball.graphicgenerators;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class TreeGenerator {

	static int width = 1500;
	static int height = 1500;
	static Color treeColor = new Color(1, 1, 1, 1f);
	public static ArrayList<Vector2> tab;
	public static Texture generate() {

		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		Color color = new Color(0.9f, 0.2f, 0.3f, 0.9f);

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				//pixmap.drawPixel(i, j, Color.argb8888(color.r, color.g, color.b, color.a));
			}
		color = new Color(0.9f, 0.2f, 0.3f, 1);
		
		pixmap.setBlending(Blending.SourceOver);
		pixmap.setColor(color);
		
		tab = tree(pixmap, width/2, 3*height/4, -(float)Math.PI/3*2, 7
				, alfaDelta, thickness2, new ArrayList<Vector2>());
		
		//drawC(pixmap, new Vector3(150, 150, 30), 0.2f, (float)Math.PI/2, 60, 20);
		
		
		
		return new Texture(pixmap);
	}
	
	
	
	
	static float doGory = 1;
	static float thickness2 = 10;
	static float thicknessRate = 0.8f;
	static float radiusLengthRate = 1.15f; // mnoznik promienia  dla > 1 r maleje   ()
	static float radiusSmoothRate = 1.8f; // wyg³adzenie skracania - d¹¿y do 1 przy inf, do n przy 1
	static float radiusScale = 10; //d³ugoœæ ga³êzi (ostatniej)
	static float alfaDelta = 1f;
	static float alfaRate = 1.1f;
	static float wazkosc = 2.2f; //w¹zkoœæ ca³ego drzewa         (2.2 - 3.5)
	static float losowosc2 = 150f; //rozpiêtoœæ na dole          (5 - 150)
	static float losowoscDelta = 1.5f; //rozpiêtoœæ na górze   (1.1 - 1.5)
	
	static Random rand = new Random();
	
	private static ArrayList<Vector2> tree(Pixmap pixmap, int x, int y, float alfa, int n, float alfaDelta, float prevThickness, ArrayList<Vector2> tab) {
		if (n < 3)
		tab.add(new Vector2(x, height-y));
		if (n <= 0) return tab;
		boolean b1 = true;
		if (n%2 == 0) b1 = false;
		int r = (int) (Math.pow(n, 1/radiusSmoothRate)*radiusScale * Math.pow(radiusLengthRate, n));
		float losowosc = (float) (losowosc2*Math.pow(losowoscDelta, 10f/n));
		float alfa1 = alfa + n%2*alfaDelta;
		float alfa2 = alfa - n%2*alfaDelta;
		if (b1) {
			alfa1 = alfa + alfaDelta*(rand.nextInt((int)losowosc)/(losowosc/2f)+1)/wazkosc;
			alfa2 = alfa + alfaDelta*(rand.nextInt((int)losowosc)/(losowosc/2f)+1)/wazkosc;
		}
		else {
			alfa1 = alfa - alfaDelta*(rand.nextInt((int)losowosc)/(losowosc/2f)+1)/wazkosc;
			alfa2 = alfa - alfaDelta*(rand.nextInt((int)losowosc)/(losowosc/2f)+1)/wazkosc;
		}
		Pixmap pixmap2 = new Pixmap(width, height, Format.RGBA8888);
		pixmap2.setBlending(Blending.None);
		float prevThickness1 = prevThickness*thicknessRate;// + rand.nextInt(10);

		Vector3 v = drawC(pixmap2, new Vector3(x, y, prevThickness),alfa, alfa1,  r, prevThickness1, b1);
		pixmap2.setBlending(Blending.SourceOver);

		pixmap.drawPixmap(pixmap2, 0, 0);
		
		pixmap2.setBlending(Blending.None);
		float prevThickness2 = prevThickness*thicknessRate;// + rand.nextInt(10);
		Vector3 v2 = drawC(pixmap2, new Vector3(x, y, prevThickness),alfa, alfa2, r, prevThickness2, b1);
		pixmap2.setBlending(Blending.SourceOver);

		pixmap.drawPixmap(pixmap2, 0, 0);

		
		
		//pixmap.fillCircle(x, y, r);
		tree(pixmap, (int)v.x, (int) v.y, alfa1, n-1, alfaDelta*alfaRate, prevThickness1, tab);
		tree(pixmap,(int)v2.x, (int) v2.y, alfa2, n-1, alfaDelta*alfaRate, prevThickness2, tab);
		
		return tab;
	}
	
	
	private static Vector3 drawC(Pixmap pixmap, Vector3 firstPos, float firstAngle, float lastAngle, float radius, float lastThickness, boolean isLeft) {
		int g = 1;
		if (!isLeft) g = -1;
		float centerAngle = (float) (firstAngle - g*Math.PI/2);


		Vector2 center = new Vector2 ((float) (firstPos.x - Math.cos(centerAngle)*radius), (float) (firstPos.y - Math.sin(centerAngle)*radius));
		
		float centerLastAngle = (float) (lastAngle - g*Math.PI/2);
		Vector2 last = new Vector2 ((float) (center.x + Math.cos(centerLastAngle)*radius), (float) (center.y + Math.sin(centerLastAngle)*radius));
		Vector2 centerLast = new Vector2 ((float) (center.x - Math.cos(centerLastAngle)*(lastThickness - firstPos.z)), (float) (center.y - Math.sin(centerLastAngle)*(lastThickness - firstPos.z)));
		pixmap.setColor(treeColor);
		pixmap.fillCircle((int)center.x, (int)center.y, (int)((radius) + firstPos.z/2));
		pixmap.setColor(0.9f, 0.2f, 0.3f, 0f);
		
		float mycenterAngle;
		
		float angle = 0.5f;
		int forI;
		if(isLeft) {
			mycenterAngle = centerAngle;
			forI = (int)((Math.PI*2-(lastAngle-firstAngle))/angle);
			for (int i = 0; i < forI; i++) {
				brushTriangle(pixmap, center, mycenterAngle, radius, -angle);
				mycenterAngle -= angle;
				//mycenterAngle = (float) (mycenterAngle%Math.PI*2);
			}
			mycenterAngle += angle;
			brushTriangle(pixmap, center, mycenterAngle, radius, centerLastAngle- mycenterAngle);
		}
		else {
			mycenterAngle = centerLastAngle;
			forI = (int)((Math.PI*2+(lastAngle-firstAngle))/angle);
			for (int i = 0; i < forI; i++) {
				brushTriangle(pixmap, center, mycenterAngle, radius, -angle);
				mycenterAngle -= angle;
				//mycenterAngle = (float) (mycenterAngle%Math.PI*2);
			}
			mycenterAngle += angle;
			brushTriangle(pixmap, center, mycenterAngle, radius, centerAngle- mycenterAngle);
		}
		Vector2 a = new Vector2((float)(center.x + Math.cos(centerAngle)*(radius - firstPos.z/2)),  (float) (center.y + Math.sin(centerAngle)*(radius - firstPos.z/2)));
		Vector2 b = new Vector2((float)(center.x + Math.cos(centerLastAngle)*(radius - lastThickness/2)),  (float) (center.y + Math.sin(centerLastAngle)*(radius - lastThickness/2)));
		//Vector2 a = new Vector2(100, 200);
		//Vector2 b = new Vector2(200, 200);
		//Vector3 brushC = generateCircleFromPoints(a, b, centerAngle, centerLastAngle);
		Vector3 brushC = generateCircleFromPoints(a, b, centerAngle + firstPos.z/100f, g);
		pixmap.setColor(0.9f, 0.2f, 0.3f, 0.6f);
		
		//pixmap.fillCircle((int)center.x, (int)center.y, 3);
		pixmap.setColor(0.1f, 0.9f, 0.1f, 0f);
		pixmap.fillCircle((int)brushC.x, (int)brushC.y, (int)brushC.z);
		pixmap.setColor(treeColor);
		pixmap.fillCircle((int)firstPos.x, (int)firstPos.y, (int)firstPos.z/2);
		pixmap.setColor(0.1f, 0.1f, 0.9f, 0.6f);
		//pixmap.fillCircle((int)a.x, (int)a.y, 3);
		//pixmap.fillCircle((int)b.x, (int)b.y, 3);
		//pixmap.fillCircle((int)brushC.x, (int)brushC.y, 4);
		
		
		Vector3 lastPos = new Vector3 (last.x, last.y, lastThickness);
		
		return lastPos;
		
	}
	
	private static void brushTriangle(Pixmap pixmap, Vector2 center, float centerAngle, float radius, float angle) {
		pixmap.fillTriangle((int)center.x, (int)center.y,
				(int)(center.x + Math.cos(centerAngle+angle)*radius*2), (int)(center.y + Math.sin(centerAngle+angle)*radius*2),
				(int)(center.x + Math.cos(centerAngle)*radius*2), (int)(center.y + Math.sin(centerAngle)*radius*2));
	}
	
	
	private static Vector3 generateCircleFromPoints(Vector2 a, Vector2 b, float centerAngle, int g) {
		Vector3 circle = new Vector3();
		float x1, y1, b1;
		float x2, y2, b2;
		Vector2 d = new Vector2(1, 0);
		Vector2 s = new Vector2(b.x - a.x, b.y - a.y);
		float ab = (float) (s.angleRad(d));

		float centerLastAngle;

		
		
		double delta = -ab - (centerAngle + g*Math.PI/2);
		//Gdx.app.log("delta", delta+ "");

		
		centerAngle = (float) (-ab - delta - g*Math.PI/2);
		centerLastAngle = (float) (-ab + delta -  g*Math.PI/2);
		x1 = (float) (Math.tan(centerAngle));
		
		x2 = (float) (Math.tan(centerLastAngle));
		//Gdx.app.log("x1", ""+x1);
		//Gdx.app.log("x2", ""+x2);
		y1 = -1;
		y2 = -1;
		b1 = a.y - x1*a.x;
		b2 = b.y - x2*b.x;
		
		float x, y;
		if (x1 == 0) {
			y = -b1/y1;
			x = -(b2+y2*y)/x2;
		}
		else if (x2 == 0) {
			y = -b2/y2;
			x = -(b1+y1*y)/x1;
		}
		else {
			y = (b1 - x1/x2*b2)/(y1-x1/x2*y2);
			x = -(b2+y2*y)/x2;
		}
		
		
		
		float myX = (b1-b2)/(x2-x1);
		float myY = x1*myX+b1;
		x = myX;
		y = myY;
		//Gdx.app.log("myX", ""+myX);
		//Gdx.app.log("b1", ""+b1);
		//Gdx.app.log("b2", ""+b2);
		//Gdx.app.log("x", ""+x);
		//Gdx.app.log("y", ""+y);
		
		float dx = a.x - x, dy = a.y - y;
		
		
		circle.set(x, y, (float) Math.sqrt(dx*dx + dy*dy));
		
		return circle;
	}
}
