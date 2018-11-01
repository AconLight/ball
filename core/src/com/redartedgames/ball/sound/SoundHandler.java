package com.redartedgames.ball.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundHandler {

	public static Sound szsz; public static long szszId;
	public static Music sd;
	
	public static void load() {
		szsz = Gdx.audio.newSound(Gdx.files.internal("sounds/szsz.mp3"));
		szszId = szsz.play(0f);
		szsz.setLooping(szszId, true);
		
		sd =  Gdx.audio.newMusic(Gdx.files.internal("sounds/sd.wav"));
	}
	
	public static void nextLvl() {
		szsz.setVolume(szszId, 0f);
	}
	
	public static void playMenuSd() {
		sd.stop();
		sd.play();
		sd.setLooping(true);
		sd.setVolume(1f);
	}
	
	public static void playGameSdNostalgic() {
		sd.stop();
		sd.play();
		sd.setLooping(true);
		sd.setVolume(0.4f);
	}
}
