package com.redartedgames.ball.editor;

import com.redartedgames.ball.objects.GameObject;

public interface Editorable {
	void moveBig(int x, int y);
	void moveSmall(int x, int y);
	void addSize(int a, int b);
	void setSpot(int i);
	void escape();
	void enter();
	GameObject createCopy();
	String label();
	String newObjectToString();
}
