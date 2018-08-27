package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
		forwardColor = new Color(20/256f, 50/256f, 60/256f, 0.6f);
		backwardColor = new Color(0/256f, 20/256f, 30/256f, 0.6f);
		
		setColor(external, 125/255f, 230/255f, 255/255f, 1f);
		frozenAlfa = 0f;
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
			//Gdx.app.log("StaticImp", "cath ya");
		}
	}
}
