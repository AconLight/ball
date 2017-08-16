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
		boolean flaga = true;
		for(Imp imp : imps) {
		if(imp.type == Imp.STATIC_TYPE && !imp.isSpawned && flaga) {
			imp.spawn(movement);
			flaga = false;
		}
			imp.deactivate();
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
		boolean flaga = true;
		for(Imp imp : imps) {
		if(imp.type == Imp.ACTIVE_TYPE && !imp.isSpawned && flaga) {
			imp.spawn(movement);
			flaga = false;
		}
			imp.activate();
		}
	}
	
	public ArrayList<Imp> getImps() {
		return imps;
	}
}
