package com.redartedgames.ball.menu;

import java.util.ArrayList;

import com.badlogic.gdx.assets.loaders.resolvers.AbsoluteFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.redartedgames.ball.LevelLoader;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.objects.SpriteObject;
import com.redartedgames.ball.screen.Consts;
import com.redartedgames.ball.screen.World;

public class MenuWorld extends World{
	int time;
	boolean isForward;
	ArrayList<ReversableObject> reversableObjects;
	SpriteObject menu, menu_play, menu_map, menu_reset;
	Color selectedColor = new Color(0.6f, 0, 0, 0.6f);
	Color notSelectedColor = new Color(0.6f, 0.6f, 0.6f, 0.6f);
	Color playColor, mapColor, resetColor;
	int selectedId;
	float selectedTime, selectedTimeMax = 0.6f;
	
	public void restart() {
		gameObjects.clear();
		restart(0);
		time = -600;
	}
	
	public void restart(int levelId) {
		menu = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0); menu.addTexture("graphic/menu/menu.png");
		menu_play = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0); menu_play.addTexture("graphic/menu/menu_play.png");
		menu_map = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0); menu_map.addTexture("graphic/menu/menu_map.png");
		menu_reset = new SpriteObject(Consts.gameWidth/2, Consts.gameHeight/2, null, 0); menu_reset.addTexture("graphic/menu/menu_reset.png");
		gameObjects.add(menu);
		gameObjects.add(menu_play);
		gameObjects.add(menu_map);
		gameObjects.add(menu_reset);
		selectedTime = 0;
		selectedId = 1;
		setSelected(selectedId);
		playColor = new Color(selectedColor);
		mapColor = new Color(notSelectedColor);
		resetColor = new Color(notSelectedColor);
		reversableObjects = new ArrayList<>();
		reversableObjects.addAll(LevelLoader.getLevel(0, null, null, null));
		gameObjects.addAll(reversableObjects);
		for (GameObject obj : reversableObjects) {
			for (GameObject obj2 : reversableObjects) {
				if(obj != obj2) {
					obj.collidableObjects.add(obj2);
				}
			}
		}
		isForward = true;
	}
	
	public void moveSelectedUp() {
		if (selectedId > 1) {
			setSelected(selectedId -1);
			selectedTime = 0;
		}
		
	}
	
	public void moveSelectedDown() {
		if (selectedId < 3) {
			setSelected(selectedId +1);
			selectedTime = 0;
		}
	}
	
	void setSelected(int id) {
		selectedId = id;
	}
	
	public MenuWorld() {
		super(); 
		restart();
	}
	
	@Override
	public void update(float delta) {
		if (selectedTime < selectedTimeMax) {
			selectedTime += 0.01f;
		} else selectedTime = selectedTimeMax;
		
		float t = (selectedTime/selectedTimeMax)*(selectedTime/selectedTimeMax);
		
		if (selectedId == 1) playColor.lerp(selectedColor, t);
		else playColor.lerp(notSelectedColor, t);
		
		if (selectedId == 2) mapColor.lerp(selectedColor, t);
		else mapColor.lerp(notSelectedColor, t);
		
		if (selectedId == 3) resetColor.lerp(selectedColor, t);
		else resetColor = resetColor.lerp(notSelectedColor, t);
		
		menu_play.setColor(playColor);
		menu_map.setColor(mapColor);
		menu_reset.setColor(resetColor);
		
		
		
		for(ReversableObject r : reversableObjects) {
			r.setIsForward(isForward);
		}
		if ((time >= 0 || isForward) && (time < 1500 || !isForward)){
			if (time >= 0) {
				super.update(0.01f);
			}
			time++;
			if (!isForward) time -= 2;
		}
		else {
			isForward = !isForward;
			if (time < 0) time++;
			else time--;
		}
	}

}
