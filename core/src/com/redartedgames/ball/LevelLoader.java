package com.redartedgames.ball;

import java.util.ArrayList;

import com.redartedgames.ball.myobjects.Ball;
import com.redartedgames.ball.myobjects.Rect;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;
import com.redartedgames.ball.objects.ReversableObject;

public class LevelLoader {

	public static ArrayList<ReversableObject> getLevel(int levelId) {
		ArrayList<ReversableObject> objects = new ArrayList<ReversableObject>();
		switch (levelId) {
		case 1: {
			objects.add(new Rect(70, 30, 256, 160, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(268, 30, 156, 100, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(377, 43, 80, 140, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(550, 123, 270, 290, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(750, 28, 160, 100, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(997, 54, 340, 140, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(717, 238, 90, 60, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1217, 188, 140, 440, BehaviorMode.kinematic, null, 0));
			
			break;	
		}
		}
		
		
		
		
		return objects;
	}
}
