package com.redartedgames.ball.myobjects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.redartedgames.ball.objects.GameObject;
import com.redartedgames.ball.objects.Movement;
import com.redartedgames.ball.objects.ReversableObject;
import com.redartedgames.ball.screen.Consts;

public class ImpsCollection extends ReversableObject{
	private ArrayList<Imp> imps;
	private Imp lastUsed;
	private MovesData playerMovesData;
	
	public ImpsCollection() {
		super(0, 0, null, 0);
		imps = new ArrayList<Imp>();
		playerMovesData = new MovesData();
	}
	
	public void render(SpriteBatch batch, int priority) {
		super.render(batch, priority);
		/*batch.setColor(0.7f, 0.7f, 0.7f, 0.1f);
		batch.draw(GameObject.dotTex, Consts.gameWidth - (imps.size()+1)*100, Consts.gameHeight-100, (imps.size()+1)*100, 100);
		batch.setColor(1, 1, 1, 1);
		Vector2 tempPos = new Vector2();
		boolean tempVisible;
		int i = 0;
		for (Imp imp: imps) {
			if (imp.isUsed) {
				continue;
			}
			i++;
			imp.justToRender = true;
			tempPos.set(imp.getPosition());
			imp.getPosition().set(Consts.gameWidth - i*100 , Consts.gameHeight - 60);
			imp.render(batch, priority);
			imp.getPosition().set(tempPos);
			imp.justToRender = false;
		}*/
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
