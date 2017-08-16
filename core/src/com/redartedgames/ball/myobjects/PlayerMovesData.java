package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PlayerMovesData {
	private ArrayList<BigDecimal> accelerationsX, accelerationsY;
	
	public PlayerMovesData() {
		accelerationsX = new ArrayList<BigDecimal>();
		accelerationsY = new ArrayList<BigDecimal>();
	}
	
	public void addMove(BigDecimal accX, BigDecimal accY) {
		accelerationsX.add(accX);
		accelerationsY.add(accY);
	}
	
	public void removeMove() {
		accelerationsX.remove(accelerationsX.get(accelerationsX.size()-1));
		accelerationsY.remove(accelerationsY.get(accelerationsY.size()-1));
	}
	
	public BigDecimal getLastAccX() {
		return accelerationsX.get(accelerationsX.size()-1);
	}
	
	public BigDecimal getLastAccY() {
		return accelerationsY.get(accelerationsY.size()-1);
	}
}
