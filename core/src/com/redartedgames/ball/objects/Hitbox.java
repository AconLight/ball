package com.redartedgames.ball.objects;

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
	private BigDecimal positionX, positionY, circleR;
	
	private static final float k = 100;
	
	public Hitbox() {
		sMode = ShapeMode.none;
	}
	public Hitbox(BigDecimal positionX, BigDecimal positionY, float radius, BehaviorMode mode){
		sMode = ShapeMode.Circle;
		bMode = mode;
		circle = new Circle(positionX.floatValue(), positionY.floatValue(), radius);
		colRadius = radius;
		circleR = new BigDecimal(""+radius);
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	public Hitbox(BigDecimal positionX, BigDecimal positionY, float width, float height, BehaviorMode mode){
		sMode = ShapeMode.Rectangle;
		bMode = mode;
		rectangle = new Rectangle(positionX.floatValue() - width/2, positionY.floatValue() - height/2, width, height);
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
	
	public void update(BigDecimal x, BigDecimal y) {
		switch (sMode) {
			case none: {
				break;
			}
			case Rectangle: {
				rectangle.set(x.floatValue() - rectangle.width/2, y.floatValue() - rectangle.height/2, rectangle.width, rectangle.height);
				break;
			}
			case Circle: {
				positionX = x;
				positionY = y;
				circle.set(x.floatValue(), y.floatValue(), circle.radius);
				break;
			}
			case Polygon: {
				polygon.translate(position.x - oldPosition.x, position.y - oldPosition.y);
				oldPosition.set(position);
				break;
			}
		}
	}
	
	private CollisionHandle circleRect(Circle circle, Rectangle rectangle) {
		CollisionHandle c = new CollisionHandle();
		c.disX = BigDecimal.ZERO;
		c.disY = BigDecimal.ZERO;
		c.isTrue = true;
		float v;
		float dr, dx, dy;
		boolean flaga = true;
		
		if ((circle.x > rectangle.x && circle.x < rectangle.x+rectangle.width) || (circle.y > rectangle.y && circle.y < rectangle.y+rectangle.height)) {
			if (circle.x + circle.radius > rectangle.x && circle.x < rectangle.x) {
				dx = circle.x + circle.radius - rectangle.x;
				c.disX = new BigDecimal("" + (-dx*k));
			}
			else if (circle.x > rectangle.x + rectangle.width && circle.x - circle.radius < rectangle.x + rectangle.width) {
				dx = (circle.x - circle.radius) - (rectangle.x + rectangle.width);
				c.disX = new BigDecimal("" + (-dx*k));
			}
			else if (circle.y + circle.radius > rectangle.y && circle.y < rectangle.y) {
				dy = circle.y + circle.radius - rectangle.y;
				c.disY = new BigDecimal("" + (-dy*k));
			}
			else if (circle.y > rectangle.y + rectangle.height && circle.y - circle.radius < rectangle.y + rectangle.height) {
				dy = (circle.y - circle.radius) - (rectangle.y + rectangle.height);
				c.disY = new BigDecimal("" + (-dy*k));
			}
		}
			else {
				
				
				dx = circle.x - rectangle.x;
				dy = circle.y - rectangle.y;
				dr = dx*dx + dy*dy;
				if (dr < circle.radius*circle.radius) {
					c.disX = c.disX.add(new BigDecimal("" + (k*dx)));
					c.disY = c.disY.add(new BigDecimal("" + (k*dy)));
					flaga = false;
				}
				
				dx = circle.x - (rectangle.x + rectangle.width);
				dy = circle.y - rectangle.y;
				dr = dx*dx + dy*dy;
				if (dr < circle.radius*circle.radius) {
					c.disX = c.disX.add(new BigDecimal("" + (k*dx)));
					c.disY = c.disY.add(new BigDecimal("" + (k*dy)));
					flaga = false;
				}
				
				dx = circle.x - (rectangle.x + rectangle.width);
				dy = circle.y - (rectangle.y + rectangle.height);
				dr = dx*dx + dy*dy;
				//Gdx.app.log("Hitbox", "" + dx + ", " + dy);
				//Gdx.app.log("Hitbox, circle", "" + circle.x + ", " + circle.y);
				//Gdx.app.log("Hitbox, rect", "" + (rectangle.x+rectangle.width) + ", " + (rectangle.y+rectangle.height));
				if (dr < circle.radius*circle.radius) {
					//Gdx.app.log("Hitbox", "" + dx + ", " + dy);
					c.disX = c.disX.add(new BigDecimal("" + (k*dx)));
					c.disY = c.disY.add(new BigDecimal("" + (k*dy)));
					flaga = false;
				}
				
				dx = circle.x - rectangle.x;
				dy = circle.y - (rectangle.y + rectangle.height);
				dr = dx*dx + dy*dy;
				
				if (dr < circle.radius*circle.radius) {
					c.disX = c.disX.add(new BigDecimal("" + (k*dx)));
					c.disY = c.disY.add(new BigDecimal("" + (k*dy)));
					flaga = false;
				}
				
			}
		
		
		return c;
		
	}
	
	private CollisionHandle circleCircle(BigDecimal circleX, BigDecimal circleY, BigDecimal circleR, BigDecimal circle2X, BigDecimal circle2Y, BigDecimal circle2R) {
		CollisionHandle c = new CollisionHandle();
		c.disX = BigDecimal.ZERO;
		c.disY = BigDecimal.ZERO;
		BigDecimal dx = circleX.subtract(circle2X);
		BigDecimal dy = circleY.subtract(circle2Y);
		BigDecimal dr2 = dx.multiply(dx).add(dy.multiply(dy));
		BigDecimal dr = sqrt(dr2, 50);
		BigDecimal d = circleR.add(circle2R).subtract(dr);
		
		if (d.floatValue() > 0) {
			c.disX = dx.divide(dr, 5, RoundingMode.HALF_UP).multiply(d).multiply(new BigDecimal("" + k));
			c.disY = dy.divide(dr, 5, RoundingMode.HALF_UP).multiply(d).multiply(new BigDecimal("" + k));
		}
		else {
			c.disX = BigDecimal.ZERO;
			c.disY = BigDecimal.ZERO;
		}
		//Gdx.app.log("HdisX"+disX, "HdisY"+disY);
		return c;
	}
	
	private CollisionHandle rectRect(Rectangle rect, Rectangle rect2, Rectangle intersection) {
		CollisionHandle c = new CollisionHandle();
		c.disX = BigDecimal.ZERO;
		c.disY = BigDecimal.ZERO;
		
		if (rect.y + rect.height/2 > intersection.y ) {
			if (rect.x + rect.width/2 > intersection.x ) {
				if (intersection.width > intersection.height) {
					c.disY = new BigDecimal("" + (intersection.height*k));
				}
				else {
					c.disX = new BigDecimal("" + (intersection.width*k));
				}
			}
			else {
				if (intersection.width > intersection.height) {
					c.disY = new BigDecimal("" + (intersection.height*k));
				}
				else {
					c.disX = new BigDecimal("" + (-intersection.width*k));
				}
			}
		}
		else {
			if (rect.x + rect.width/2 > intersection.x ) {
				if (intersection.width > intersection.height) {
					c.disY = new BigDecimal("" + (-intersection.height*k));
				}
				else {
					c.disX = new BigDecimal("" + (intersection.width*k));
				}
			}
			else {
				if (intersection.width > intersection.height) {
					c.disY = new BigDecimal("" + (-intersection.height*k));
				}
				else {
					c.disX = new BigDecimal("" + (-intersection.width*k));
				}
			}
		}

		return c;
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
	public CollisionHandle checkCol(Hitbox hitbox) {
		CollisionHandle c = new CollisionHandle();
		c.disX = BigDecimal.ZERO;
		c.disY = BigDecimal.ZERO;
		c.isTrue = false;
		switch (sMode) {
			case Rectangle: {
				switch (hitbox.sMode) {
					case Rectangle: {
						Rectangle intersection = new Rectangle();
						if (Intersector.intersectRectangles(rectangle, hitbox.rectangle, intersection)) {
							c = rectRect(rectangle, hitbox.rectangle, intersection);
							Gdx.app.log("" + intersection, "asd");
						}
						break;
					}
					case Circle: {
						if (Intersector.overlaps(hitbox.circle, rectangle)) {
							c = circleRect(hitbox.circle, rectangle);
							c.isTrue = true;
						}
						break;
					}
					case Polygon: {
						float [] verts1 = {rectangle.x, rectangle.y, rectangle.x+rectangle.width, rectangle.y+rectangle.height};
						c.isTrue = Intersector.overlapConvexPolygons(verts1, hitbox.polygon.getTransformedVertices(), null);
						break;
					}
				}
				break;
			}
			case Circle: {
				
				switch (hitbox.sMode) {
					case Rectangle: {
						if (Intersector.overlaps(circle, hitbox.rectangle)) {
							c = circleRect(circle, hitbox.rectangle);
							c.isTrue = true;
						}
						else {
							c.isTrue = false;
						}
						break;
					}
					case Circle: {						
							c = circleCircle(positionX, positionY, circleR, hitbox.positionX, hitbox.positionY, hitbox.circleR);
							c.isTrue = true;
						
						break;
					}
					case Polygon: {
						//TODO
						c.isTrue = false;
						break;
					}
				}
				break;
			}
			case Polygon: {
				
				switch (hitbox.sMode) {
					case Rectangle: {
						float [] verts1 = {hitbox.rectangle.x, hitbox.rectangle.y, hitbox.rectangle.x+hitbox.rectangle.width, hitbox.rectangle.y+hitbox.rectangle.height};
						//c.isTrue = Intersector.overlapConvexPolygons(verts1, polygon.getTransformedVertices(), null);
						break;
					}
					case Circle: {
						//TODO
						c.isTrue = false;
						break;
					}
					case Polygon: {
						c.isTrue = Intersector.overlapConvexPolygons(hitbox.polygon.getTransformedVertices(), polygon.getTransformedVertices(), null);
						break;
					}
				}
				break;
			}
			default : {
				c.isTrue = false;
			}
			
			
		}
		return c;
	}
}