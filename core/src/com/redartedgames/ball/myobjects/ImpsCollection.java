package com.redartedgames.ball.myobjects;

import java.util.ArrayList;

import com.redartedgames.ball.objects.Movement;

public class ImpsCollection {
	private ArrayList<Imp> imps;
	
	public ImpsCollection() {
		imps = new ArrayList<Imp>();
	}
	
	public void addStaticImp() {
		imps.add(new StaticImp(0, 0, 1, null, 0));
	}
	
	public void spawnNextImpPressDown(Movement movement) {
		if(imps.size() > 0 && imps.get(imps.size()-1).type == Imp.STATIC_TYPE) {
			imps.get(imps.size()-1).spawn(movement);
			imps.remove(imps.size()-1);
		}
	}
	
	public void spawnNextImpPressUp(Movement movement) {
		if(imps.size() > 0 && imps.get(imps.size()-1).type == Imp.ACTIVE_TYPE) {
			//TODO
			imps.get(imps.size()-1).spawn(movement);
			imps.remove(imps.size()-1);
		}
	}
	
	public ArrayList<Imp> getImps() {
		return imps;
	}
}
