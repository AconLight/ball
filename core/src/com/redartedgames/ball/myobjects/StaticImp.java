package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Movement;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.ReversableObject;

public class StaticImp extends Imp{

	public StaticImp(float x, float y, float m, GameObject parent, int id) {
		super(x, y, 1, parent, id);
		type = STATIC_TYPE;
	}
	
	public void render(SpriteBatch sr, int priority) {
		if (isSpawned || justToRender) {
			sr.setColor(0/256f, 162/256f, 156/256f, 1f);
			sr.draw(tex, position.x-radius, position.y-radius, radius*2, radius*2);
		}
	}
	
	public void spawn(Movement movement) {
		super.spawn(movement);
	}
	
	public void activate() {
		super.activate();
		shouldBeStopped2 = false;
	}
	
	public void deactivate() {
		super.deactivate();
		shouldBeStopped2 = true;
	}	
	
	@Override
	public void collide(GameObject obj) {
		super.collide(obj);
		if(isSpawned && c.isTrue)  {
			shouldBeStopped = true;
			((ReversableObject)obj).setShouldBeStopped(true);
			Gdx.app.log("StaticImp", "cath ya");
		}
	}
}
