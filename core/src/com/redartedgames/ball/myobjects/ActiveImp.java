package com.redartedgames.ball.myobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableMovement;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;

public class ActiveImp extends Imp{

	public ActiveImp(float x, float y, float m, GameObject parent, int id) {
		super(x, y, m, parent, id);
		type = ACTIVE_TYPE;
		forwardColor = new Color(60/256f, 20/256f, 20/256f, 1);
		backwardColor = new Color(60/256f, 0/256f, 0/256f, 1);
	}
	

	
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
		
		if (((ReversableMovement)movement).getIsForward() && playerMovesData.accelerationsX.size() > 0) {
			((ReversableMovement) movement).addCollisionAcc(playerMovesData.accelerationsX.get(0), playerMovesData.accelerationsY.get(0));
			playerMovesData.accelerationsX.remove(0);
			playerMovesData.accelerationsY.remove(0);
		}
	}

}
