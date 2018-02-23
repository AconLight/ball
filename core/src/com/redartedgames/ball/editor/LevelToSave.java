package com.redartedgames.ball.editor;

import java.util.ArrayList;

import com.redartedgames.ball.objects.GameObject;

public class LevelToSave {

	public static String printLevel(ArrayList<GameObject> objects, int levelId) {
		String s = "\n\nLevel: " + levelId + "\n\n";
		for(GameObject obj : objects) {
			s += "objects.add(" + obj.newObjectToString() + ");" + "\n";
		}
		s += "End of level";
		return s;
	}
}
