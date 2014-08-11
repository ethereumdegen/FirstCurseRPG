package com.mygdx.game.story;

public class StoryController {


	StoryChapter[] chapters = 
		{
			new Chapter1()
						
		};
	
	
	public StoryController()
	{
		
		
	}
	
	int currentChapter = 0;
	
	public void update(float delta)
	{
		chapters[currentChapter].update(delta);
		
		
	}
	
	
	
	
	
}
