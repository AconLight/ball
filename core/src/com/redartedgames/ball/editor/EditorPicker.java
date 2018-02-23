package com.redartedgames.ball.editor;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.objects.GameObject;

public class EditorPicker extends EditorOption{
	
	private int x, y, width, height;
	private int selectHeight = 5;
	private int selectId = 0;
	private ArrayList<ObjectPick> objectPicks;
	private boolean isOn;
	
	public EditorPicker(EditorNode editorNode, int x, int y, int width, int height, int selectHeight) {
		super(editorNode);
		objectPicks = new ArrayList<ObjectPick>();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.selectHeight = selectHeight;
		isOn = false;
	}
	
	public void render(SpriteBatch batch) {
		if(isOn) {
			int i = 0;
			int d = 0;
			int myHeight = (int) (1f*height/selectHeight); 
			if (selectId > objectPicks.size()-1 - myHeight/2) d = selectId - (objectPicks.size()-1 - myHeight/2);
			else if (selectId < myHeight/2) d = selectId - myHeight/2;
			for(ObjectPick obj : objectPicks) {
				if((selectId-i-d)*selectHeight > -height/2 && (selectId-i-d)*selectHeight < height/2) {
					if(i == selectId) {
						obj.setFontColor(Color.GREEN);
					}
					else {
						obj.setFontColor(Color.RED);
					}
					obj.renderLabel(batch, x, y + (selectId-i-d)*selectHeight);
				}
				i++;
			}
		}
	}
	public void show() {
		isOn = true;
	}
	
	public void hide() {
		isOn = false;
	}
	public void add(ObjectPick obj) {
		objectPicks.add(obj);
	}
	
	public void moveSelect(int i) {
		selectId += i;
		if (selectId < 0) selectId = 0;
		if (selectId >= objectPicks.size()) selectId = objectPicks.size()-1;
	}

	@Override
	public void moveBig(int x, int y) {
		moveSelect(y);
	}

	@Override
	public void addSize(int a, int b) {

	}

	@Override
	public void setSpot(int i) {
		
	}

	@Override
	public void enter() {
		editorNode.currentEditorable = objectPicks.get(selectId);
		Gdx.app.log("Editor Picker", "enter");
	}

	@Override
	public GameObject createCopy() {
		Gdx.app.log("Editor Picker", "create Copy - null");
		return null;
	}

	@Override
	public String label() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveSmall(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String newObjectToString() {
		// TODO Auto-generated method stub
		return null;
	}
}
