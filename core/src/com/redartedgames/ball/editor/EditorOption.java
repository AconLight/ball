package com.redartedgames.ball.editor;

public abstract class EditorOption implements Editorable {
	protected EditorNode editorNode;
	
	EditorOption(EditorNode editorNode) {
		this.editorNode = editorNode;
	}

	@Override
	public void escape() {
		editorNode.currentEditorable = editorNode.escapeEditorable;
	}
	
}
