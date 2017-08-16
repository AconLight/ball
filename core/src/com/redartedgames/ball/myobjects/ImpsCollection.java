package com.redartedgames.ball.myobjects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.redartedgames.ball.objects.Movement;

public class ImpsCollection {
	private ArrayList<Imp> imps;
	private Imp lastUsed;
	
	public ImpsCollection() {
		imps = new ArrayList<Imp>();
	}
	
	public Imp getLastUsed() {
		return lastUsed;
	}
	
	public void addStaticImp() {
		imps.add(new StaticImp(0, 0, 1, null, 0));
	}
	
	public void spawnNextImpPressDown(Movement movement) {
		for(Imp imp : imps)
		if(imp.type == Imp.STATIC_TYPE && !imp.isSpawned) {
			imps.get(imps.size()-1).spawn(movement);
			Gdx.app.log("ImpsCollection", "message");
		}
	}
	
	public void activate() {
		for(Imp imp : imps) {
			if(imp.type == Imp.STATIC_TYPE && imp.isSpawned) {
				imp.activate();
			}
		}
	}
	
	public void deactivate() {
		for(Imp imp : imps) {
			if(imp.type == Imp.STATIC_TYPE && imp.isSpawned) {
				imp.deactivate();
			}
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
