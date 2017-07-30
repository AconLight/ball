package com.redartedgames.ball.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.redartedgames.ball.BallGame;
import com.redartedgames.ball.screen.Consts;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Consts.screenWidth;
		config.height = Consts.screenHeight;
		new LwjglApplication(new BallGame(), config);
	}
}
