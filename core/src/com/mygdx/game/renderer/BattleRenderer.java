package com.mygdx.game.renderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Unit;
import com.mygdx.game.camera.BattleCameraManager;
import com.mygdx.game.camera.WorldCameraManager;
import com.mygdx.game.controller.Player;
import com.mygdx.game.entities.BattleController;
import com.mygdx.game.entities.World;
import com.mygdx.game.utility.TransitionalColor;

public class BattleRenderer {

	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 7f;
	private static final float RUNNING_FRAME_DURATION = 0.06f;
	
	private BattleController battle;
	private OrthographicCamera cam;

	private OrthogonalTiledMapRenderer maprenderer;
	
	private BattleCameraManager camManager;
	


	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();

	/** Textures 
	private TextureRegion bobIdleLeft;
	private TextureRegion bobIdleRight;
	private TextureRegion blockTexture;
	private TextureRegion bobFrame;
	
	 Animations 
	private Animation walkLeftAnimation;
	private Animation walkRightAnimation;
	**/
	
	
//	private SpriteBatch spriteBatch;
	private boolean debug = false;
	private int width;
	private int height;
	private float ppuX;	// pixels per unit on the X axis
	private float ppuY;	// pixels per unit on the Y axis
	public void setSize (int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float)width / CAMERA_WIDTH;
		ppuY = (float)height / CAMERA_HEIGHT;
	}
	
	public BattleRenderer(BattleController battle, boolean debug) {
		this.battle = battle;
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		camManager = new BattleCameraManager(cam);
		
		
	
		cam.setToOrtho(false, 30, 20);
		
		
		
		this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);	
		this.cam.zoom = 0.5f;
		this.cam.update();
		this.debug = debug;
		//spriteBatch = new SpriteBatch();
		loadTextures();
		
		maprenderer = new OrthogonalTiledMapRenderer(battle.getMap(), 1 / 16f);
			}
	
	
	private void loadTextures() {
		
	}
	
	static final Color DEFAULT_ENVIRONMENT_COLOR = new Color(0.5f, 1f, 0.8f, 1f);
	
	Color unitColor = new Color(1f, 1f, 1f, 1f);
	
	TransitionalColor terrainColor = new TransitionalColor(0.5f, 1f, 0.8f, 1f);	

	public void update(float delta)
	{
		terrainColor.update(delta);
		camManager.update(delta);
		
		cam.position.set(11, 13, 0);
		this.cam.update();
	}
	
	
	float camCatchUpSpeed = 0.1f;
	public void render() {  
		
				
		if(Player.getRegion() != null && Player.getRegion().getTint()!=null)
		{			
			terrainColor.set(Player.getRegion().getTint(),1.0f);
			
		}else{
			terrainColor.set(DEFAULT_ENVIRONMENT_COLOR,1.0f);
			
		}
		
	
		
		maprenderer.setView(cam);
		maprenderer.getSpriteBatch().setColor(terrainColor);
		maprenderer.render();
		
		
		
		/*spriteBatch.setColor(unitColor);
		spriteBatch.setProjectionMatrix(cam.combined);
		
		spriteBatch.begin();
			//drawTiles();
			drawUnits();
		spriteBatch.end();
		*/
		drawUnits();
		
		if (debug)
			drawDebug();
	}


	

	private void drawUnits() {
		
		for(int team = 0; team < 2;team++)
		{
			for(int unit=0;unit < 3;unit++)
			{
				
				if(battle.getUnits()[team][unit] != null && battle.getUnits()[team][unit].getSprite()!=null){
					
					battle.getUnits()[team][unit].getBattleModel().draw(unitColor, cam.combined );
					
				}
				
				
			}
			
		}
		
				
		
		/*if(bob.getState().equals(State.WALKING)) {
			bobFrame = bob.isFacingLeft() ? walkLeftAnimation.getKeyFrame(bob.getStateTime(), true) : walkRightAnimation.getKeyFrame(bob.getStateTime(), true);
		}
		spriteBatch.draw(bobFrame, bob.getPosition().x * ppuX, bob.getPosition().y * ppuY, Bob.SIZE * ppuX, Bob.SIZE * ppuY);
	*/
	
	}

	private void drawDebug() {
		// render blocks
		/*debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
		for (Block block : world.getBlocks()) {
			Rectangle rect = block.getBounds();
			float x1 = block.getPosition().x + rect.x;
			float y1 = block.getPosition().y + rect.y;
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}
		// render Bob
		Bob bob = world.getBob();
		Rectangle rect = bob.getBounds();
		float x1 = bob.getPosition().x + rect.x;
		float y1 = bob.getPosition().y + rect.y;
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(x1, y1, rect.width, rect.height);
		debugRenderer.end();*/
	}

	public OrthographicCamera getCam() {
		return cam;
	}
	
	public BattleCameraManager getCameraManager() {
		return camManager;
	}

	public void initBattle() {
		// TODO Auto-generated method stub
		
	}

	public void endBattle() {
		// TODO Auto-generated method stub
		
	}

}