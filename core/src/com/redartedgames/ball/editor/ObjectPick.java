package com.redartedgames.ball.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.objects.GameObject;

public class ObjectPick extends EditorOption{

	private GameObject object;
	private String name;
	BitmapFont font;
    
	public ObjectPick(EditorNode editorNode, String name, GameObject object) {
		super(editorNode);
		this.object = object;
		this.name = name;
		font = new BitmapFont();
	    font.setColor(Color.RED);
	}
	
	public ObjectPick(EditorNode editorNode, GameObject object) {
		super(editorNode);
		this.object = object;
		this.name = object.label();
		font = new BitmapFont();
	    font.setColor(Color.RED);
	}
	
	public void setFontColor(Color color) {
		font.setColor(color);
	}
	
	public void renderLabel(SpriteBatch batch, float x, float y) {
		font.draw(batch, name, x, y);
	}

	@Override
	public void moveBig(int x, int y) {
		object.moveBig(x, y);
		Gdx.app.log("Object Pick", "moveBig");
	}
	
	@Override
	public void moveSmall(int x, int y) {
		object.moveSmall(x, y);
		Gdx.app.log("Object Pick", "moveSmall");
	}

	@Override
	public void addSize(int a, int b) {
		object.addSize(a, b);
	}

	@Override
	public void setSpot(int i) {
		object.setSpot(i);
	}

	@Override
	public void enter() {
		Gdx.app.log("Object Pick", "enter - no enter");
		//no enter
	}

	@Override
	public GameObject createCopy() {
		Gdx.app.log("Object Pick", "copy");
		return object.createCopy();
	}

	@Override
	public String label() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String newObjectToString() {
		// TODO Auto-generated method stub
		return null;
	}

}
