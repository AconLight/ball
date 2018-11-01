package com.redartedgames.ball.graphicgenerators;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redartedgames.ball.game.GameWorld;

public class Mountain {
	
	int colorK = 10;
	public Color changeColor(Color color) {
		return new Color((color.g + color.r*colorK)/(colorK+1), (color.b + color.g*colorK)/(colorK+1), (color.r + color.b*colorK)/(colorK+1), 1f);
	}
	
	public Color brightenColor(Color color, float k) {
		return new Color(color.r + (1-color.r)*k, color.g + (1-color.g)*k, color.b + (1-color.b)*k, 1);
	}
	
	public interface Function {
		float function(float x);
	}
	
	Function createFunctionLine(Biom biom) {
		return (float x) -> {
			float k = 1000;
			//return 10;
			//return (float) (Math.max(0, x/(Math.sin(biom.season + x/100f) + 2) - 40));
			//return x/2;
			return (float) Math.max(0, (x/(Math.sin(41*x/k)+2)/(Math.sin(43*x/k)+2)/2 - 30));
			//return (float) (x/(Math.sin(x/k) + 2)/(Math.sin(2*x/k) + 2)/(Math.sin(3*x/k) + 2)/(Math.sin(5*x/k) + 2)/(Math.sin(7*x/k) + 2)/(Math.sin(11*x/k) + 2));
		};
	}
	
	Function createFunctionElement(Biom biom) {
		float r1 = rand.nextInt(500);
		float r2 = rand.nextInt(300);
		float r3 = rand.nextInt(100);
		return (float x) -> {
			return (float) ((Math.sin(x/(100f + r1))) + (Math.sin(x/(100f + r2))) + (Math.sin(x/(100f + r3))) + 3.2f)*50 + 200 + rand.nextInt(21) - 10;
		};
	}
	
	Function createFunctionWidth(Biom biom, int z) {
		float r1 = rand.nextInt(500);
		return (float x) -> {
			return (float) 6 + 100f / (10 + z + (float) Math.sin(x/(100f+ r1))+1);
		};
	}
	
	public class Line {
		public Line next;
		public float value;
		public int width;
		public int x, y;
		Color color;
		Line prevLine;
		
		public Line getDepthLine(int depth) {
			Line line = this;
			for (int i = 0; i < depth; i++) {
				//line = line.next;
				line = null;
			}
			return line;
		}
		
		public Line(int x, int y, int depth, Function function, float val, float width, Color color, Line prevLine, int fixedDepth) {
			value = val;
			this.prevLine = prevLine;
			this.color = brightenColor(color, (float)(6f/width));
			this.x = x;
			this.y = y;
			this.width = (int)width;
			if (depth > 1) {
				if (prevLine != null)
					next = new Line(x, y, depth-1, function, function.function(val), width, brightenColor(changeColor(color), 0.2f), prevLine.getDepthLine(fixedDepth), fixedDepth + 1);
				else
					next = new Line(x, y, depth-1, function, function.function(val), width, brightenColor(changeColor(color), 0.2f), null, fixedDepth + 1);
			}
		}
		public void render(ShapeRenderer sr) {
			if (prevLine == null)
				render(sr, (int)(y + value), 1);
			else
				render(sr, (int)(y + dxf2*value + (1-dxf2)*prevLine.value), 1);
		}
		
		public void render(ShapeRenderer sr, int ytop, int depth) {
			if (prevLine != null)
				drawShadyRect(sr, x, (int)(ytop-(dxf2*value + (1-dxf2)*prevLine.value)), width, (int) (dxf2*value + (1-dxf2)*prevLine.value), (int)(this.color.r*255), (int)(this.color.g*255), (int)(this.color.b*255), 100, 100, 20 + depth*20);
			else
				drawShadyRect(sr, x, (int)(ytop-value), width, (int) value, (int)(this.color.r*255), (int)(this.color.g*255), (int)(this.color.b*255), 100, 100, 20 + depth*20);
			if(next != null) {
				next.render(sr, ytop, depth+1);
			}
		}
		
		float dxf2;
		
		public void update(float x, float delta, float position) {
			float s1 = 400;
			dxf2 = (position%s1/s1);
			int dx2 = (int)(position/s1);
			float dxfl2 = (position/s1);
			
			if(next != null) {
				next.update(x, delta, position);
			}
		}
	}
	
	private class Element {
		public ArrayList<Line> lines;
		
		int linesNumber = 10;
		int lineDepth = 2;
		int width;
		public Element(int x, int y, Function functionLine, Function functionElement, Function functionWidth, Color color) {
			lines = new ArrayList<>();
			width = 0;
			for (int i = 0; i < linesNumber; i++) {
				if (lines.size()>0)
					lines.add(new Line((int)(x + width), y, lineDepth, functionLine, functionElement.function(x + width), functionWidth.function(x + width), color, lines.get(lines.size()-1), 0));
				else
					lines.add(new Line((int)(x + width), y, lineDepth, functionLine, functionElement.function(x + width), functionWidth.function(x + width), color, null, 0));
				width += functionWidth.function(x + width);
			}
		}
	}
	

	Biom biom;
	int elementsNumber = 250;
	public ArrayList<Element> elements;
	public ArrayList<Line> lines;
	Random 	rand = new Random();
	Color color = new Color(40f/255, 255f/255, 90f/255, 1f);
	
	public Mountain(Biom biom, float x, int z, int y) {

		this.biom = biom;
		int posX = (int) x;
		int posY = (int) y;
		elements = new ArrayList<>();
		lines = new ArrayList<>();
		Function functionLine = createFunctionLine(biom);
		Function functionElement = createFunctionElement(biom);
		Function functionWidth = createFunctionWidth(biom, z);
		for (int i = 0; i < elementsNumber; i++) {
			elements.add(new Element(posX, posY, functionLine, functionElement, functionWidth, color));
			posX += elements.get(i).width;
			for (Line line: elements.get(i).lines) {
				lines.add(line);
			}
		}
	}
	
	public void update(float x, float delta, float position) {
		
		for (Line line : lines) {
			line.update(x, delta, position);
		}
	}
	
	public void render(ShapeRenderer sr) {
		for (Line line : lines) {
			line.render(sr);
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
