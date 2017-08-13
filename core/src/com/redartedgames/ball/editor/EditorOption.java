package com.redartedgames.ball.editor;

import com.redartedgames.ball.objects.GameObject;

public class EditorOption {

	private EditorOptionInterface editorOptionInterface;
	private String name;
	
	public EditorOption(String name, EditorOptionInterface editorOptionInterface) {
		this.name = name;
		this.editorOptionInterface = editorOptionInterface;
	}
	
	public void execute(GameObject obj) {
		editorOptionInterface.execute(obj);
	}
}
