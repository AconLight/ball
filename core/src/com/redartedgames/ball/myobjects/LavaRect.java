package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class LavaRect extends Rect{
	
	Player player;

	public LavaRect(float x, float y, float width, float height, GameObject parent, int id) {
		super(x, y, width, height, BehaviorMode.kinematic, parent, id);
		// TODO Auto-generated constructor stub
		
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void render(SpriteBatch sr, int priority) {
		sr.setColor(114/256f, 19/256f, 0/256f, 1f);
		sr.draw(dotTex, (position.x - width/2+0.5f), position.y - height/2+0.5f, width+0.5f, height+0.5f);
	}
	
	public void collide(GameObject obj) {
		super.collide(obj);
		if(obj == player && c.isTrue) {
			player.isAlive = false;
		}
	}
	
	@Override
	public GameObject createCopy() {
		return new LavaRect(position.x, position.y, width, height, parent, 0);
	}
	
	@Override
	public String label() {
		// TODO Auto-generated method stub
		return "LavaRect " + id;
	}
	
	@Override
	public String newObjectToString() {
		return "new LavaRect(" +(int)position.x + ", " + (int)position.y + ", " + (int)width + ", " + (int)height + ", " + parent + ", " + 0 + ")";
	}
}
