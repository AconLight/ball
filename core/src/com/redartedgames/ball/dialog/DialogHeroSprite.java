package com.redartedgames.ball.dialog;

import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.SpriteObject;

public class DialogHeroSprite extends SpriteObject{

	public DialogHeroSprite(float x, float y, GameObject parent, int id, int dialogHeroType) {
		super(x, y, parent, id);
		loadType(dialogHeroType);
	}
	
	private void loadType(int dialogHeroType) {
		//TODO
		switch (dialogHeroType) {
		case 0 : {
			addTexture("graphic/mrgamecreator.png");
			break;
		}
		case 1 : {
			addTexture("graphic/mraku.png");
			break;
		}
		}
	}

}
