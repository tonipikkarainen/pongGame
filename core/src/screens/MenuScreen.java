package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pongplus.game.Application;

import managers.GameScreenManager.GAMESTATE;

public class MenuScreen extends AbstractScreen{
	OrthographicCamera camera;
	
	TextButton playButton;
	TextButton exitButton;
	TextButton.TextButtonStyle buttonStyle;
	BitmapFont font ;	
	Table table;
	
	public MenuScreen(Application app) {
		super(app);
		camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Application.V_WIDTH, Application.V_HEIGHT);
		font = new BitmapFont();
		table = new Table();
		
		buttonStyle = new TextButton.TextButtonStyle();
		buttonStyle.font = font;
		playButton = new TextButton("Play", buttonStyle);
		exitButton = new TextButton("Exit", buttonStyle);
		
		table.setPosition(Application.V_WIDTH/2, Application.V_HEIGHT/2);
		table.add(playButton).padBottom(30);
		table.row();
		table.add(exitButton);
	
		stage.addActor(table);
	}

	@Override
	public void show() {
		
		playButton.addListener(new ClickListener() {
			 @Override
			public void clicked(InputEvent event, float x, float y) {
				 app.gsm.setGameScreen(GAMESTATE.PLAY);
				 
				 // dispose after changing screen??
			 }
			 
		});
		exitButton.addListener(new ClickListener() {
			 @Override
			public void clicked(InputEvent event, float x, float y) {
				 Gdx.app.exit();
				 // dispose after changing screen??
			 }
			 
		});
		
		
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		stage.draw();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
		//this.dispose();
	}

	@Override
	public void update(float delta) {
		stage.act(delta);
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		font.dispose();
	}
	
	
}
