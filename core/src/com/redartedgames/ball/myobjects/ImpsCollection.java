package com.redartedgames.ball.myobjects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.redartedgames.ball.objects.Movement;

public class ImpsCollection {
	private ArrayList<Imp> imps;
	private Imp lastUsed;
	private MovesData playerMovesData;
	
	public ImpsCollection() {
		imps = new ArrayList<Imp>();
		playerMovesData = new MovesData();
	}
	
	public Imp getLastUsed() {
		return lastUsed;
	}
	
	public void addStaticImp() {
		imps.add(new StaticImp(0, 0, 1, null, 0));
	}
	
	public void addActiveImp() {
		imps.add(new ActiveImp(0, 0, 1, null, 0));
	}
	
	public void spawnNextImpPressDown(Player player) {
		boolean flaga = true;
		for(Imp imp : imps) {
			Gdx.app.log("ImpsCollection", "imp type: " + imp.type);
			if(imp.type == Imp.STATIC_TYPE && !imp.isUsed && flaga) {
				imp.spawn(player.getMovement());
				imp.deactivate();
				flaga = false;
				//Gdx.app.log("ImpsCollection", "static down");
			}
			if(imp.type == Imp.ACTIVE_TYPE && !imp.isUsed && flaga) {
				flaga = false;
				imp.playerMovesData.pastePlayerMoves(player.getMovesData());
				//player.setReversedMovesNumb(0);
				//Gdx.app.log("ImpsCollection", "active down");
			}
			
		}
	}
	
	public void spawnNextImpPressUp(Player player) {
		boolean flaga = true;
		for(Imp imp : imps) {
			if(imp.type == Imp.STATIC_TYPE && !imp.isUsed && flaga) {
				imp.activate();
				flaga = false;
				imp.isUsed = true;
				//Gdx.app.log("ImpsCollection", "static up");
			}
			if(imp.type == Imp.ACTIVE_TYPE && !imp.isUsed && flaga) {
				imp.spawn(player.getMovement());
				imp.playerMovesData.shortenMovesDataToNumb(player.getReversedMovesNumb());
				flaga = false;
				imp.isUsed = true;
				//Gdx.app.log("ImpsCollection", "active up");
			}
			
		}
		
	}
	
	public void activate(Movement movement) {
		for(Imp imp : imps) {
			if(imp.type == Imp.STATIC_TYPE && imp.isSpawned) {
				imp.activate();
			}
			if(imp.type == Imp.ACTIVE_TYPE && !imp.isSpawned) {
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
	
	
	
	public ArrayList<Imp> getImps() {
		return imps;
	}
}
