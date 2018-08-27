package com.redartedgames.ball.dialog;

import java.util.ArrayList;

import com.redartedgames.ball.objects.GameObject;

public class StoryWindow extends ConversationIntro {

	public StoryText storyText;
	
	public StoryWindow(float x, float y, int id, GameObject parent) {
		super(x, y, id, parent, "");
		storyText = new StoryText();
	}
	
	public void updateText(ArrayList<Integer> combination) {
		storyText.updateText(combination);
	}
	

}
