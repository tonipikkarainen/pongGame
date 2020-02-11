package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.pongplus.game.Application;

import gameobjects.Paddle;

public class GameScreen extends AbstractScreen {
	OrthographicCamera camera;
	// Objects:
	Paddle paddleA, paddleB;
	float PADDLE_WIDTH = 10;
	float PADDLE_HEIGHT = 40;
	float PADDLE_DIST_FROM_WALL = 100;
	
	
	public GameScreen(Application app) {
		super(app);
		camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Application.V_WIDTH, Application.V_HEIGHT);		
	}
	@Override
	public void show() {
		app.batch.setProjectionMatrix(camera.combined);
		app.shapeRenderer.setProjectionMatrix(camera.combined);
		
		createPaddles();
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
//		stage.act(delta);
	}
	
	
	@Override
	public void render(float delta) {
		super.render(delta);
		//paddleA.
//		stage.draw();
//		app.shapeRenderer.begin(ShapeType.Filled);
//		app.shapeRenderer.setColor(1, 1, 1, 1);
//		app.shapeRenderer.rect(10, 10, 20, 10);
//		//app.shapeRenderer.circle(x, y, radius);
//		app.shapeRenderer.end();
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			paddleA.movePaddle(-3);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			paddleA.movePaddle(3);
		}
	
		app.batch.begin();
		app.batch.draw(paddleA.getImg(), paddleA.x, paddleA.y);
		app.batch.draw(paddleB.getImg(), paddleB.x, paddleB.y);
		app.batch.end();
	}
	

	private void createPaddles() {
		paddleA = new Paddle(PADDLE_DIST_FROM_WALL, "left", PADDLE_WIDTH, PADDLE_HEIGHT);
		paddleB = new Paddle(Application.V_WIDTH - PADDLE_DIST_FROM_WALL - PADDLE_WIDTH/2
				, "right", PADDLE_WIDTH, PADDLE_HEIGHT);
		
	}
	
	@Override
	public void dispose() {
		super.dispose();
		paddleA.dispose();
	}

}
