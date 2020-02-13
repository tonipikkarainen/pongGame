package com.pongplus.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import managers.GameScreenManager;
import managers.GameScreenManager.GAMESTATE;

/**
 * Main application class.
 * All others call this in the beginning.
 * 
 * Now when we extend Game class we have 
 * a protected Screen as a attribute and 
 * we can do setScreen etc.. for Application
 * @author tonipikkarainen
 * @date 11.2.2020 
 */
public class Application extends Game {
	// Config variables/ attributes
	public static String APP_TITLE = "Pong Plus";
	public static double APP_VERSION = 0.1;
	public static int APP_DESKTOP_WIDTH = 720;
	public static int APP_DESKTOP_HEIGHT= 420;
	public static int APP_FPS = 60;
	public static int V_WIDTH = 720;
	public static int V_HEIGHT= 420;
	
	// Managers
	public AssetManager assetManager;
	public GameScreenManager gsm;
	
	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;
	
//	Texture img;
	
	@Override
	public void create () {
		assetManager = new AssetManager();
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		
		// So here we create gamescreenmanager
		// which starts the game logic. 
		// gsm also has application as an attribute.
		gsm = new GameScreenManager(this);
		
//		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		super.render();
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
//		
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.F5)) {
			gsm.setGameScreen(GAMESTATE.MAIN_MENU);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.F6)) {
			this.dispose();
			this.create();
		}
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		assetManager.dispose();
		super.dispose();
		gsm.dispose();
//		img.dispose();
	}
}
