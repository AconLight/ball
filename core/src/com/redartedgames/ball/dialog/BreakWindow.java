package com.redartedgames.ball.dialog;

import java.util.ArrayList;

import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.screen.Consts;

public class BreakWindow extends ConversationIntro {

	public StoryText storyText;
	
	public BreakWindow(float x, float y, int id, GameObject parent, int levelId) {
		super(x, y, id, parent, "");
		storyText = new StoryText(Consts.gameWidth-200);
		storyText.setAsBreak(levelId);
	}
}