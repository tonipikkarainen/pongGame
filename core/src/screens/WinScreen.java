package screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.pongplus.game.Application;

public class WinScreen extends AbstractScreen {
	BitmapFont font;
	String winner;
	
	
	public WinScreen(Application app, String player) {
		super(app);
		font = new BitmapFont();
		this.winner = player;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
//		this.app.batch.begin();
		font.draw(this.app.batch,winner, Application.V_WIDTH/2, Application.V_HEIGHT/2);
		
	}
	
	@Override
	public void dispose() {
		super.dispose();
		font.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}


}
