package com.redartedgames.ball.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.redartedgames.ball.BallGame;
import com.redartedgames.ball.EditorGame;
import com.redartedgames.ball.screen.Consts;

public class EditorLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Consts.editorScreenWidth;
		config.height = Consts.editorScreenHeight;
		new LwjglApplication(new EditorGame(), config);
	}
}
