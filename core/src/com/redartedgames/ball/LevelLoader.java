package com.redartedgames.ball;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.database.EasterEggsBase;
import com.redartedgames.ball.database.StoryBase;
import com.redartedgames.ball.dialog.DialogHero;
import com.redartedgames.ball.game.GameWorld;
import com.redartedgames.ball.myobjects.Ball;
import com.redartedgames.ball.myobjects.HangingGuy;
import com.redartedgames.ball.myobjects.ImpsCollection;
import com.redartedgames.ball.myobjects.LavaRect;
import com.redartedgames.ball.myobjects.MovingRect;
import com.redartedgames.ball.myobjects.Player;
import com.redartedgames.ball.myobjects.Rect;
import com.redartedgames.ball.myobjects.StaticButton;
import com.redartedgames.ball.myobjects.TimeBackItem;
import com.redartedgames.ball.objects.Hitbox.BehaviorMode;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.screen.Consts;

public class LevelLoader {

	public static ArrayList<ReversableObject> getLevel(int levelId, Player player, ImpsCollection impsCollection, GameWorld gameWorld) {
		ArrayList<ReversableObject> objects = new ArrayList<ReversableObject>();		
		switch (levelId) {
		case 0: {
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 4; j++) {
					objects.add(new Ball(1200+ i*45, 800-j*50, 30, 1, BehaviorMode.dynamic, null, 0));
				}
				
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(975, 30, 2060, 160, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(350, 130, 770, 80, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(175, 180, 580, 140, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(-75, 280, 580, 240, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1675, 755, 60, 560, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1100, 755, 60, 560, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(2002, 333, 310, 200, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1125, 460, 80, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1650, 460, 80, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1625, 410, 90, 80, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1154, 410, 90, 80, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1204, 335, 120, 80, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1576, 335, 120, 80, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1801, 106, 310, 130, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1901, 182, 310, 200, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1575, 380, 50, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1657, 480, 50, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1200, 380, 50, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1121, 480, 50, 50, BehaviorMode.kinematic, null, 0));
			
			break;
		}
		case 1: {
			objects.add(new Rect(1920/2, 100, 1920, 200, BehaviorMode.kinematic, null, 0));
			HangingGuy h = new HangingGuy(100, 400, 0, null);
			TimeBackItem e = new TimeBackItem(700, 300, null, 0);
			e.setItem("graphic/sznur.png", 700, 300, 2);
			player.easterEggs.add(e);
			objects.add(e);
			Gdx.app.log("lvlLoader", "lvl1 load");
			player.setPosition(h.getHeadPos());
			h.setPlayer(player);
			objects.add(h);
			break;
		}
		case 2: {
			objects.add(new Rect(1920/2, 100, 1920, 200, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920/2, 100, 1920/4, 400, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920/2, 100, 1920/8, 500, BehaviorMode.kinematic, null, 0));
			TimeBackItem e = new TimeBackItem(200, 200, null, 0);
			e.setItem("graphic/bottle.png", 200, 200, 1);
			player.easterEggs.add(e);
			objects.add(e);
			TimeBackItem e2 = new TimeBackItem(1200, 300, null, 0);
			e2.setItem("graphic/bottle.png", 1200, 300, 1);
			player.easterEggs.add(e2);
			objects.add(e2);
			StoryBase.StoryId = 0;
			EasterEggsBase.load(0, 2);
			DialogHero dh = new DialogHero(600, 290, 0, null, 0);
			dh.setCollision(player);
			objects.add(dh);
			gameWorld.dialogHero = dh;

			break;
		}
		case 3: {
			LavaRect lava = new LavaRect(1920/2, 50, 1920, 100, null, 0);
			lava.setPlayer(player);
			objects.add(lava);
			objects.add(new Rect(1920/16, 100, 1920/8, 200, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920-1920/16, 100, 1920/8, 200, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920/2, 200, 400, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920/4, 150, 100, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920-1920/4, 150, 100, 50, BehaviorMode.kinematic, null, 0));
			break;
		}
		case 4: { // guziki - wprowadzenie
			LavaRect lava = new LavaRect(925, 5, 1390, 120, null, 0);
			lava.setPlayer(player);
			objects.add(lava);
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(129, 80, 330, 230, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1776, 80, 410, 204, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1348, 130, 200, 60, BehaviorMode.kinematic, null, 0));
			MovingRect m1 = new MovingRect(1054, 130, 574, 130, 270, 60, BehaviorMode.kinematic, null, 0);
			objects.add(m1);
			
			objects.add(new StaticButton(400, 130, m1, null, 0));
			break;
		}
		case 5: {
			
			LavaRect lava = new LavaRect(925, 55, 1630, 120, null, 0);
			lava.setPlayer(player);
			MovingRect m1, m2, m3;
			objects.add(m1 = new MovingRect(575, 48, 575, 200, 120, 110, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(975, 48, 975, 500, 120, 110, BehaviorMode.kinematic, null, 0));
			objects.add(m3 = new MovingRect(1375, 48, 1375, 800, 120, 110, BehaviorMode.kinematic, null, 0));
			objects.add(new StaticButton(350, 155, m1, null, 0));
			objects.add(new StaticButton(350, 155, m2, null, 0));
			objects.add(new StaticButton(350, 155, m3, null, 0));
			objects.add(lava);
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1825, 55, 290, 230, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(-125, 80, 640, 180, BehaviorMode.kinematic, null, 0));
			
			
			break;
		}
		case 6: {
			
			LavaRect lava = new LavaRect(925, 55, 1630, 120, null, 0);
			lava.setPlayer(player);
			MovingRect m1, m2, m3, m4;
			objects.add(m1 = new MovingRect(575, 270, 575, 500, 120, 230, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(875, 270, 875, 500, 120, 230, BehaviorMode.kinematic, null, 0));
			objects.add(m3 = new MovingRect(725, 120, 725, 680, 120, 70, BehaviorMode.kinematic, null, 0));
			objects.add(m4 = new MovingRect(350, 720, 300, 720, 50, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(350, 750, 20, 20, BehaviorMode.dynamic, null, 0));
			objects.add(new Rect(1025, 120, 120, 70, BehaviorMode.kinematic, null, 0));
			objects.add(new StaticButton(350, 155, m1, null, 0));
			objects.add(new StaticButton(350, 155, m2, null, 0));

			objects.add(new StaticButton(1110, 270, m4, null, 0));

			objects.add(new Rect(900, 850, 630, 50, BehaviorMode.kinematic, null, 0));
			objects.add(lava);
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1825, 255, 290, 630, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(-125, 80, 640, 180, BehaviorMode.kinematic, null, 0));
			
			
			break;
		}
		
		// wiêcej platformowych leveli jak w powy¿szych
		
		case 7: { // statyczny imp - wprowadzenie
			LavaRect lava = new LavaRect(1920/2, 50, 1920, 100, null, 0);
			lava.setPlayer(player);
			objects.add(lava);
			objects.add(new Rect(1920/8 + 225, 100, 1920/4 + 450, 200, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920/4, 200, 100, 200, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920/4+100, 250, 100, 300, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920/4+200, 300, 100, 450, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920/4+400, 300, 100, 450, BehaviorMode.kinematic, null, 0));
			
			MovingRect m1 = new MovingRect(1920, 500, 1920-400, 500, 800, 100, BehaviorMode.kinematic, null, 0);
			objects.add(m1);
			
			objects.add(new StaticButton(1920/4+300 , 250, m1, null, 0));
			
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			lava.setStaticImps(impsCollection);
			break;
		}
		
		//wiêcej leveli ze statycznym impem jak w powy¿szym
		
		case 8: { //cofanie z platformy
			LavaRect lava = new LavaRect(925, 5, 1390, 120, null, 0);
			lava.setPlayer(player);
			objects.add(lava);
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(129, 80, 330, 230, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1776, 80, 410, 204, BehaviorMode.kinematic, null, 0));
			MovingRect m1 = new MovingRect(654, 130, 1254, 130, 270, 60, BehaviorMode.kinematic, null, 0);
			objects.add(m1);
			
			objects.add(new StaticButton(400, 130, m1, null, 0));
			
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			lava.setStaticImps(impsCollection);
			break;
		}
		case 9: { //platforma + cofanie z platformy
			LavaRect lava = new LavaRect(1022, 30, 1290, 70, null, 0);
			lava.setPlayer(player);
			objects.add(lava);
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(183, 81, 430, 247, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1800, 230, 320, 470, BehaviorMode.kinematic, null, 0));
			MovingRect m1, m2;
			objects.add(m1 = new MovingRect(1150, 140, 725, 140, 210, 60, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(900, 300, 1375, 300, 210, 60, BehaviorMode.kinematic, null, 0));
			
			objects.add(new StaticButton(525, 120, m1, null, 0));
			objects.add(new StaticButton(525, 120, m2, null, 0));
			
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			lava.setStaticImps(impsCollection);
			break;
		}
		
		case 10: { //cofanie z platformy x2
			LavaRect lava =new LavaRect(925, 5, 1790, 120, null, 0);
			lava.setPlayer(player);
			objects.add(lava);
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(129, 80, 330, 230, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1876, 80, 210, 204, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0)); 
			MovingRect m1 = new MovingRect(701, 111, 701, 316, 180, 57, BehaviorMode.kinematic, null, 0);
			objects.add(m1);
			
			MovingRect m2 = new MovingRect(976, 355, 1326, 355, 159, 56, BehaviorMode.kinematic, null, 0);
			objects.add(m2);
			
			objects.add(new StaticButton(502, 130, m2, null, 0));
			objects.add(new StaticButton(400, 130, m1, null, 0));

			impsCollection.addStaticImp();
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			lava.setStaticImps(impsCollection);
			break;
		}		
		case 11: { //zamra¿anie
			LavaRect lava;
			objects.add(lava = new LavaRect(936, 30, 1520, 70, null, 0));
			lava.setPlayer(player);
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));

			objects.add(new Rect(100, 66, 260, 290, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1825, 80, 460, 510, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(583, 270, 100, 70, BehaviorMode.kinematic, null, 0));
			
			MovingRect m1, m2;
			objects.add(m1 = new MovingRect(1699, 165, 474, 165, 400, 90, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(583, 541, 583, 250 + 90, 100, 90, BehaviorMode.kinematic, null, 0));
			objects.add(new StaticButton(422, 283, m1, null, 0));
			objects.add(new StaticButton(422, 283, m2, null, 0));
			
			
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			lava.setStaticImps(impsCollection);
			break;
		}
		case 12: { //zamra¿anie cd 
			
			MovingRect m1, m2;
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1920, 300, 200, 200, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(975, 80, 2030, 240, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1526, 405, 850, 120, BehaviorMode.kinematic, null, 0));
			objects.add(m1 = new MovingRect(1041, 475, 1041, 334, 120, 260, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(941, 405, 941, 253, 120, 120, BehaviorMode.kinematic, null, 0));
			objects.add(new StaticButton(350, 241, m1, null, 0));
			objects.add(new StaticButton(200, 241, m2, null, 0));
			
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			
			break;
		}
		case 13: { //zamra¿anie cd 2
			
			MovingRect m1, m2, m3;
			player.setPosition(new Vector2(10, 550));
			
			objects.add(new Rect(-120, 540, 200, 1080, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(975, 20, 2030, 120, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(50, 230, 180, 340, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1847, 230, 180, 340, BehaviorMode.kinematic, null, 0));
			
			objects.add(m1 = new MovingRect(-193, 335, 697, 335, 480, 80, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(2100, 335, 1185, 335, 480, 80, BehaviorMode.kinematic, null, 0));
			objects.add(m3 = new MovingRect(951, 963, 951, 540, 130, 310, BehaviorMode.kinematic, null, 0));
			
			objects.add(new StaticButton(950, 130, m1, null, 0));
			objects.add(new StaticButton(950, 130, m2, null, 0));
			objects.add(new StaticButton(950, 130, m3, null, 0));
			
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			
			break;
		}
		case 14: {
			player.setPosition(new Vector2(10, 550));
			LavaRect lava;
			objects.add(lava = new LavaRect(950, 55, 1720, 150, null, 0));
			lava.setPlayer(player);
			objects.add(new Rect(1825, 155, 270, 410, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(125, 155, 270, 410, BehaviorMode.kinematic, null, 0));
			MovingRect m1, m2;
			objects.add(m1 = new MovingRect(-50, 265, 625, 265, 790, 50, BehaviorMode.kinematic, null, 0));
			objects.add(m2 = new MovingRect(1997, 265, 1325, 265, 790, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new StaticButton(974, 280, m2, null, 0, true));
			objects.add(new StaticButton(200, 430, m1, null, 0));
			
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			lava.setStaticImps(impsCollection);
			break;
		}
		case 15: {
			player.setPosition(new Vector2(10, 250));
			objects.add(new Rect(950, 55, 1960, 150, BehaviorMode.kinematic, null, 0));
			objects.add(new Ball(775, 180, 50, 50, BehaviorMode.dynamic, null, 0));
			objects.add(new Ball(860, 855, 50, 50, BehaviorMode.dynamic, null, 0));
			objects.add(new Rect(626, 255, 297, 50, BehaviorMode.kinematic, null, 0));
			MovingRect m1;
			objects.add(m1 = new MovingRect(422, 305, 422, 205, 110, 151, BehaviorMode.kinematic, null, 0));
			objects.add(new StaticButton(650, 405, m1, null, 0, true));
			
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			
			break;
		}
		case 16: {
			objects.add(new Rect(75, 55, 240, 220, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(200, 55, 50, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(275, 67, 110, 100, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(424, 72, 190, 50, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(600, 105, 170, 150, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(625, 205, 60, 170, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(650, 280, 150, 80, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(800, 80, 230, 80, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(975, 80, 240, 130, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1200, 80, 240, 130, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1375, 154, 206, 179, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1700, 154, 616, 109, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(275, -21, 46, 189, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1700, 54, 66, 189, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1075, -21, 66, 189, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(575, -21, 66, 189, BehaviorMode.kinematic, null, 0));
			
			StoryBase.StoryId = 1;
			EasterEggsBase.load(1, 5);
			
			DialogHero dh = new DialogHero(660, 400, 0, null, 1);
			dh.setCollision(player);
			objects.add(dh);
			gameWorld.dialogHero = dh;
			
			
			break;
		}
		case -10: { 
			objects.add(new Rect(138, 81, 288, 204, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(367, 51, 180, 135, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(500, 89, 90, 192, BehaviorMode.kinematic, null, 0));
			
			objects.add(new Rect(909, 320, 90, 55, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1005, 48, 285, 142, BehaviorMode.kinematic, null, 0));
			LavaRect lava = new LavaRect(1010, 125, 290, 71, null, 0);
			lava.setPlayer(player);
			objects.add(lava);
			objects.add(new Rect(705, 152, 320, 392, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1500, 84, 720, 202, BehaviorMode.kinematic, null, 0));
			objects.add(new Rect(1870, 305, 160, 642, BehaviorMode.kinematic, null, 0));

			MovingRect m1 = new MovingRect(1476, 78, 1476, 310, 100, 100, BehaviorMode.kinematic, null, 0);
			MovingRect m2 = new MovingRect(1672, 51, 1672, 450, 100, 100, BehaviorMode.kinematic, null, 0);
			objects.add(m1);
			objects.add(m2);
			
		//	MovingRect m3 = new MovingRect(100, 600, 400, 600, 50, 50, BehaviorMode.kinematic, null, 0);
		//	MovingRect m4 = new MovingRect(200, 500, 500, 500, 50, 50, BehaviorMode.kinematic, null, 0);

		//	objects.add(m3);
		//	objects.add(m4);
			objects.add(new StaticButton(920, 240, m1, null, 0));
			objects.add(new StaticButton(920, 240, m2, null, 0));
		//	objects.add(new StaticButton(420, 340, m4, null, 0));
		//	objects.add(new StaticButton(420, 340, m3, null, 0));
			
			
			impsCollection.addStaticImp();
			impsCollection.addStaticImp();
			objects.addAll(impsCollection.getImps());
			objects.add(impsCollection);
			lava.setStaticImps(impsCollection);
			
			
			break;	
		}
		
		}
		
		objects.add(new Rect(-100-20, Consts.gameHeight/2, 200, Consts.gameHeight, BehaviorMode.kinematic, null, 0));
		for (ReversableObject obj: objects) {
			if (obj instanceof Rect) {
				if (((Rect)obj).bush != null)
				((Rect)obj).bush.setPlayer(player);
			}
		}
		
		
		
		return objects;
	}
}
