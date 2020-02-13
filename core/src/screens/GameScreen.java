package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.pongplus.game.Application;

import gameobjects.Ball;
import gameobjects.Paddle;
/**
 * TODO: add ball movement, add limits to ball( hitting walls) add logic
 * @author tonipikkarainen
 * @date 12.2.2020
 */
public class GameScreen extends AbstractScreen {
	OrthographicCamera camera;
//	Constants:
	final float PADDLE_WIDTH = 20;
	final float PADDLE_HEIGHT = 70;
	final float PADDLE_DIST_FROM_WALL = 20;
	final float FONT_X = 100;
	final float FONT_Y = Application.V_HEIGHT - 20;
	
	// Objects:
	Paddle paddleA, paddleB;
	Ball ball;
	BitmapFont font;	
	Skin skin;
	// Score
	int playerAscore = 0;
	int playerBscore = 0;
	
	// TODO: cameras position goes wrong when initialized..
	public GameScreen(Application app) {
		super(app);
		camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Application.V_WIDTH, Application.V_HEIGHT);
		font = new BitmapFont();
		font.getData().setScale(2);
		createObjects(); // create all disposable objects in constructor!!	
		skin = new Skin(Gdx.files.internal("uiskin.json"));
	}
	// show is called after every setscreen so we have to think
	// what to do there.
	@Override
	public void show() {
		app.batch.setProjectionMatrix(camera.combined);
		app.shapeRenderer.setProjectionMatrix(camera.combined);		
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void update(float delta) {
		stage.act(delta);
		camera.update();
	}
	
	// now we dont need camera.update??
	@Override
	public void render(float delta) {
		super.render(delta);
		//paddleA.
		stage.draw();
//		app.shapeRenderer.begin(ShapeType.Filled);
//		app.shapeRenderer.setColor(1, 1, 1, 1);
//		app.shapeRenderer.rect(10, 10, 20, 10);
//		//app.shapeRenderer.circle(x, y, radius);
//		app.shapeRenderer.end();
		ball.checkCollisions(paddleA);
		ball.checkCollisions(paddleB);
		ball.move(delta);

		handleInput(delta);
		
		checkScore();
		checkWinning(delta);
		
		app.batch.begin();
		app.batch.draw(paddleA.getImg(), paddleA.x, paddleA.y);
		app.batch.draw(paddleB.getImg(), paddleB.x, paddleB.y);
		app.batch.draw(ball.getImg(), ball.x, ball.y);
		font.draw(app.batch, ""+playerAscore, Application.V_WIDTH/2 - FONT_X, FONT_Y);
		font.draw(app.batch, ""+playerBscore, Application.V_WIDTH/2 + FONT_X
				, FONT_Y);
		
		app.batch.end();
	}
	@Override
	public void dispose() {
		super.dispose();
		paddleA.dispose();
		paddleB.dispose();
		ball.dispose();
		font.dispose();
		
	}
	
	
	private void checkWinning(float delta) {
		if(playerAscore == 2) {
			Dialog d = new Dialog("player A wins",skin);
			d.show(stage);
			ball.setSPEED(0);
			ball.setCenter(200,20);
		}
		if (playerBscore == 2) {
			//do winscreen with input player B
			Dialog d = new Dialog("player B wins", skin);
			d.show(stage);
			ball.setSPEED(0);
			ball.setCenter(200,20);
		}
		
	}
	

	private void handleInput(float delta) {
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			paddleB.movePaddle(-delta);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			paddleB.movePaddle(delta);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.X)) {
			paddleA.movePaddle(-delta);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			paddleA.movePaddle(delta);
		}
	}
	
	private void checkScore() {
		if (ball.x > Application.APP_DESKTOP_WIDTH - (ball.width + PADDLE_DIST_FROM_WALL)) {
			 ball.init();
			 playerAscore++;
			 
		}
		
		if (ball.x < PADDLE_DIST_FROM_WALL + PADDLE_WIDTH /2) {
			ball.init();
			playerBscore++;
		}
	}
	
	private void createObjects() {
		paddleA = new Paddle(PADDLE_DIST_FROM_WALL, "left", PADDLE_WIDTH, PADDLE_HEIGHT);
		paddleB = new Paddle(Application.V_WIDTH - PADDLE_DIST_FROM_WALL - PADDLE_WIDTH
				, "right", PADDLE_WIDTH, PADDLE_HEIGHT);
		ball = new Ball(Application.V_WIDTH/2 , Application.V_HEIGHT/2);
	}
	

}
