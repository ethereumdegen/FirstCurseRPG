package com.mygdx.game.audio;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.mygdx.game.UnitStats;

public class MusicController {

	 MusicalEmotion currentEmotion = MusicalEmotion.CALM;
	
	 static HashMap<MusicalEmotion,  String[]> musicFiles = new HashMap<MusicalEmotion,  String[]>();
	 
	 
	static{		
		musicFiles.put(MusicalEmotion.CALM, new String[]{"RealmofFantasy.mp3","Dungeon.ogg","Victory.ogg","Town.ogg","Sanctuary.ogg"});
		musicFiles.put(MusicalEmotion.BATTLE, new String[]{"Danger.ogg","CloudsShadow.ogg","battleaction.ogg"});
				
	}	
	
	public MusicController()
	{

		playMusic();		
		
	}
	
	Music music;
	
	public  void playMusic()
	{
		
		int index = (int) (Math.random() * musicFiles.get(currentEmotion).length);
		music = Gdx.audio.newMusic(Gdx.files.internal("music/"+musicFiles.get(currentEmotion)[index]));
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



	public  void setMusicalEmotion(MusicalEmotion emotion) {
		currentEmotion = emotion;
		
		music.stop();
		playMusic(); //refresh
	}
	
	//mp3Music.pause();
	//mp3Music.stop();
	//mp3Music.play();
	//Gdx.app.log("SONG",Float.toString(mp3Music.getPosition()));
	
	
}
