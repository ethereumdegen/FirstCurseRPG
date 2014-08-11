package com.mygdx.game.AssetMGMT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;

public class MusicController {

	
	
	static String[] musicFiles = {"RealmofFantasy.mp3","Dungeon.ogg","Danger.ogg"};
	
	
	
	public static void playMusic()
	{
		
		int index = (int) (Math.random() * musicFiles.length);
		Music music = Gdx.audio.newMusic(Gdx.files.internal("music/"+musicFiles[index]));
		 music.play();
		 music.setVolume(1.0f);
		 music.setOnCompletionListener(
				 new OnCompletionListener(){
					@Override
					public void onCompletion(Music music) {
						playMusic();
					}					 					 
				 });
	}
	
	//mp3Music.pause();
	//mp3Music.stop();
	//mp3Music.play();
	//Gdx.app.log("SONG",Float.toString(mp3Music.getPosition()));
	
	
}
