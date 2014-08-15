package com.mygdx.game.audio;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.audio.Sound;

public class SoundManager {

	public void update(float delta) {
		QueuedSound[] array = new QueuedSound[queuedSound.size()];
		queuedSound.toArray(array);
		
		for(QueuedSound sound : array)
		{
			if(sound.update(delta))
			{
				queuedSound.remove(sound);
			}
			
		}
		
		
		
	}

	
	List<QueuedSound> queuedSound = new ArrayList<QueuedSound>();
	
	
	public void queueSound(Sound sound, float volume, float delay) {
		queuedSound.add(new QueuedSound(sound,volume,delay));
		
	}
	
	
	class QueuedSound
	{
		Sound sound;
		float volume; 
		float delay;
		float counter;
		
		QueuedSound(Sound sound, float volume, float delay)
		{
			this.sound=sound;
			this.volume=volume;
			this.delay=delay;
		}
		
		public Sound getSound() {
			return sound;
		}

		public float getVolume() {
			return volume;
		}

		public float getDelay() {
			return delay;
		}
		
		
		public boolean update(float delta)
		{
			counter+=delta;
			if(counter >= delay)
			{
				
				sound.play(volume);
				return true;
			}
			return false;
		}
		
	}

}
