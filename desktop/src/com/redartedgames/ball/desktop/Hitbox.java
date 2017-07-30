package com.redartedgames.ball.desktop;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Hitbox {
	public enum ShapeMode { Circle, Polygon, Rectangle, none };
	public enum BehaviorMode { none, kinematic, dynamic };
	public static BehaviorMode none = BehaviorMode.none;
	public static BehaviorMode kinematic = BehaviorMode.kinematic;
	public static BehaviorMode dynamic = BehaviorMode.dynamic;
	public ShapeMode sMode;
	public BehaviorMode bMode;
	private Circle circle;
	private Polygon polygon;
	private Rectangle rectangle;
	private float colRadius;
	private Vector2 position, oldPosition;
	private BigDecimal positionX, positionY;
	
	public Hitbox() {
		sMode = ShapeMode.none;
	}
	public Hitbox(BigDecimal positionX, BigDecimal positionY, float radius, BehaviorMode mode){
		sMode = ShapeMode.Circle;
		bMode = mode;
		circle = new Circle(position.x, position.y, radius);
		colRadius = radius;
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	public Hitbox(BigDecimal positionX, BigDecimal positionY, float width, float height, BehaviorMode mode){
		sMode = ShapeMode.Rectangle;
		bMode = mode;
		rectangle = new Rectangle(position.x - width/2, position.y - height/2, width, height);
		colRadius = (float) (Math.sqrt(width*width + height*height)/2);
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	public Hitbox(BigDecimal positionX, BigDecimal positionY, float[] verticles, BehaviorMode mode, float colRadius){
		sMode = ShapeMode.Polygon;
		bMode = mode;
		polygon = new Polygon(verticles);
		this.colRadius = colRadius; // TODO
		this.positionX = positionX;
		this.positionY = positionY;
		oldPosition = new Vector2(position);
	}	
	
	public void update() {
		switch (sMode) {
			case none: {
				break;
			}
			case Rectangle: {
				rectangle.set(position.x - rectangle.width/2, position.y - rectangle.height/2, rectangle.width, rectangle.height);
				break;
			}
			case Circle: {
				circle.set(positionX.floatValue(), positionY.floatValue(), circle.radius);
				break;
			}
			case Polygon: {
				polygon.translate(position.x - oldPosition.x, position.y - oldPosition.y);
				oldPosition.set(position);
				break;
			}
		}
	}
	
	private void circleRect(Circle circle, Rectangle rectangle, Vector2 dis) {
		Vector2 myDis = new Vector2();
		float v;
		float dr, dx, dy;
		boolean flaga = true;
		float k = 1500;
		
		if (flaga || !flaga) {
			if (circle.x + circle.radius > rectangle.x && circle.x < rectangle.x) {
				dx = circle.x - rectangle.x;
				myDis.add(k, 0);
			}
			else if (circle.x > rectangle.x + rectangle.width && circle.x - circle.radius < rectangle.x + rectangle.width) {
				dx = circle.x - (rectangle.x + rectangle.width);
				myDis.add(-k, 0);
			}
			else if (circle.y + circle.radius > rectangle.y && circle.y < rectangle.y) {
				dy = circle.y - rectangle.y;
				myDis.add(0, k);
			}
			else if (circle.y > rectangle.y + rectangle.height && circle.y - circle.radius < rectangle.y + rectangle.height) {
				dy = circle.y - (rectangle.y + rectangle.height);
				myDis.add(0, -k);
			}
			else {
				
				
				dx = circle.x - rectangle.x;
				dy = circle.y - rectangle.y;
				dr = dx*dx + dy*dy;
				if (dr < circle.radius*circle.radius) {
					myDis.add((float) (k*dx/Math.sqrt(dr)), (float) (k*dy/Math.sqrt(dr)));
					flaga = false;
				}
				
				dx = circle.x - (rectangle.x + rectangle.width);
				dy = circle.y - rectangle.y;
				dr = dx*dx + dy*dy;
				if (dr < circle.radius*circle.radius) {
					myDis.add((float) (k*dx/Math.sqrt(dr)), (float) (k*dy/Math.sqrt(dr)));
					flaga = false;
				}
				
				dx = circle.x - (rectangle.x + rectangle.width);
				dy = circle.y - (rectangle.y + rectangle.height);
				dr = dx*dx + dy*dy;
				//Gdx.app.log("Hitbox", "" + dx + ", " + dy);
				//Gdx.app.log("Hitbox, circle", "" + circle.x + ", " + circle.y);
				//Gdx.app.log("Hitbox, rect", "" + (rectangle.x+rectangle.width) + ", " + (rectangle.y+rectangle.height));
				if (dr < circle.radius*circle.radius) {
					myDis.add((float) (k*dx/Math.sqrt(dr)), (float) (k*dy/Math.sqrt(dr)));
					flaga = false;
				}
				
				dx = circle.x - rectangle.x;
				dy = circle.y - (rectangle.y + rectangle.height);
				dr = dx*dx + dy*dy;
				
				if (dr < circle.radius*circle.radius) {
					myDis.add((float) (k*dx/Math.sqrt(dr)), (float) (k*dy/Math.sqrt(dr)));
					flaga = false;
				}
				
			}
		}
		
		dis.set(myDis);
		
	}
	
	private void circleCircle(BigDecimal disX, BigDecimal disY, BigDecimal circleX, BigDecimal circleY, BigDecimal circleR, BigDecimal circle2X, BigDecimal circle2Y, BigDecimal circle2R) {
		BigDecimal dx = circleX.subtract(circle2X);
		BigDecimal dy = circleY.subtract(circle2Y);
		BigDecimal dr2 = dx.multiply(dx).add(dy.multiply(dy));
		
		
	}
	private static final BigDecimal TWO = new BigDecimal("2");
	public static BigDecimal sqrt(BigDecimal A, final int SCALE) {
	    BigDecimal x0 = new BigDecimal("0");
	    BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
	    while (!x0.equals(x1)) {
	        x0 = x1;
	        x1 = A.divide(x0, SCALE, RoundingMode.HALF_UP);
	        x1 = x1.add(x0);
	        x1 = x1.divide(TWO, SCALE, RoundingMode.HALF_UP);

	    }
	    return x1;
	}
	public boolean checkCol(Hitbox hitbox, BigDecimal disX, BigDecimal disY) {
		switch (sMode) {
			case Rectangle: {
				switch (hitbox.sMode) {
					case Rectangle: {
						return Intersector.overlaps(rectangle, hitbox.rectangle);
					}
					case Circle: {
						if (Intersector.overlaps(hitbox.circle, rectangle)) {
							//circleRect(hitbox.circle, rectangle, dis);
							return true;
						}
					}
					case Polygon: {
						float [] verts1 = {rectangle.x, rectangle.y, rectangle.x+rectangle.width, rectangle.y+rectangle.height};
						return Intersector.overlapConvexPolygons(verts1, hitbox.polygon.getTransformedVertices(), null);
					}
				}
			}
			case Circle: {
				
				switch (hitbox.sMode) {
					case Rectangle: {
						if (Intersector.overlaps(circle, hitbox.rectangle)) {
							//circleRect(circle, hitbox.rectangle, dis);
							return true;
						}
						else {
							return false;
						}
					}
					case Circle: {
						return Intersector.overlaps(circle, hitbox.circle);
					}
					case Polygon: {
						//TODO
						return false;
					}
				}
			}
			case Polygon: {
				
				switch (hitbox.sMode) {
					case Rectangle: {
						float [] verts1 = {hitbox.rectangle.x, hitbox.rectangle.y, hitbox.rectangle.x+hitbox.rectangle.width, hitbox.rectangle.y+hitbox.rectangle.height};
						return Intersector.overlapConvexPolygons(verts1, polygon.getTransformedVertices(), null);
					}
					case Circle: {
						//TODO
						return false;
		
					}
					case Polygon: {
						return Intersector.overlapConvexPolygons(hitbox.polygon.getTransformedVertices(), polygon.getTransformedVertices(), null);
					}
				}
			}
			default : return false;
		}
	}
}