package com.redartedgames.ball.dialog;

import java.util.ArrayList;
import java.util.Comparator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redartedgames.ball.objects.GameObject;

public class DialogWindow extends GameObject{
	ArrayList<DialogOption> options;
	public boolean isOn;
	private int chosenOption;
	
	public DialogWindow() {
		super(0, 0, 0, null);
		options = new ArrayList<>();
		isOn = false;
		chosenOption = 0;
	}
	
	public void updateBefore(float delta, float vx, float vy) {
		super.updateBefore(delta, vx, vy);
		for (DialogOption opt: options) {
			opt.updateBefore(delta, vx, vy);
		}
	}
	
	public ArrayList<Integer> getCombination() {
		ArrayList<Integer> markedOptions = new ArrayList<>();
		for(DialogOption option: options) {
			if(option.isMarked) {
				markedOptions.add(option.optionId);
			}
		}
		markedOptions.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer arg0, Integer arg1) {
				if (arg0 > arg1) {
					return 0;
				}
				else {
					return 1;
				}
				
			}
		});
		
		//Gdx.app.log("DialogWindow", "" + markedOptions);
		return markedOptions;
	}
	
	public void addOption(String text, int id) {
		options.add(new DialogOption(this, text, options.size(), id));
	}
	
	public void render(SpriteBatch batch, int priority) {
		for (DialogOption o : options) {
			o.updatePositions(options.size());
			if(isOn) o.render(batch, priority);
		}
	}
	
	public void markChosenOption() {
		options.get(chosenOption).setMarked();
	}
	
	public void moveChosenUp() {
		if(chosenOption > 0) {
			options.get(chosenOption).removeChosen();
			chosenOption--;
			options.get(chosenOption).setChosen();
		}
	}
	
	public void moveChosenDown() {
		if(chosenOption < options.size()-1) {
			options.get(chosenOption).removeChosen();
			chosenOption++;
			options.get(chosenOption).setChosen();
		}
	}
	
	public void show() {
		isOn = true;
		chosenOption = 0;
		if (options.size() > 0) {
			options.get(chosenOption).setChosen();
		}
	}
	
	public void hide() {
		isOn = false;
	}
	
	
}
