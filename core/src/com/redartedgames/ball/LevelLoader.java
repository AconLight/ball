package com.redartedgames.ball;

import java.util.ArrayList;

import com.redartedgames.ball.myobjects.Ball;
import com.redartedgames.ball.myobjects.ButtonRect;
import com.redartedgames.ball.myobjects.ImpsCollection;
import com.redartedgames.ball.myobjects.LavaRect;
import com.redartedgames.ball.myobjects.MovingRect;
import com.redartedgames.ball.myobjects.Player;
import com.redartedgames.ball.myobjects.Rect;
import com.redartedgames.ball.myobjects.StaticButton;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.screen.Consts;

public class LevelLoader {

	public static ArrayList<ReversableObject> getLevel(int levelId, Player player, ImpsCollection impsCollection) {
		ArrayList<ReversableObject> objects = new ArrayList<ReversableObject>();		
		switch (levelId) {
		case 1: {
			objects.add(new Rect(138, 81, 288, 204, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(367, 51, 180, 135, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(500, 89, 90, 192, BehaviorMode.kinematic, null, 0));
			
			objects.add(new Rect(909, 320, 90, 55, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1005, 48, 285, 142, BehaviorMode.kinematic, null, 0));
			LavaRect lava = new LavaRect(1000, 140, 278, 71, null, 0);
			lava.setPlayer(player);
			objects.add(lava);
			objects.add(new Rect(705, 152, 320, 392, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1500, 84, 720, 202, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1870, 305, 160, 642, BehaviorMode.kinematic, null, 0));

			MovingRect m1 = new MovingRect(1476, 310, 1476, 78, 100, 100, BehaviorMode.kinematic, null, 0);
			MovingRect m2 = new MovingRect(1672, 450, 1672, 51, 100, 100, BehaviorMode.kinematic, null, 0);
			objects.add(m1);
			objects.add(m2);
			
			MovingRect m3 = new MovingRect(100, 600, 400, 600, 50, 50, BehaviorMode.kinematic, null, 0);
			MovingRect m4 = new MovingRect(200, 500, 500, 500, 50, 50, BehaviorMode.kinematic, null, 0);

			objects.add(m3);
			objects.add(m4);
			objects.add(new StaticButton(920, 240, m1, null, 0));
			objects.add(new StaticButton(920, 240, m2, null, 0));
			objects.add(new StaticButton(420, 340, m4, null, 0));
			objects.add(new StaticButton(420, 340, m3, null, 0));
			
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			
			break;	
		}
		}
		
		objects.add(new Rect(-100-20, Consts.gameHeight/2, 200, Consts.gameHeight, BehaviorMode.kinematic, null, 0));
		
		
		
		
		return objects;
	}
}
