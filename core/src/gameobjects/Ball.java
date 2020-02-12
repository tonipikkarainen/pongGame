package gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pongplus.game.Application;

@SuppressWarnings("serial")
public class Ball extends Rectangle {
	// Constants
	private final static int WIDTH = 10;
	
	// Attributes:
	Texture img;
	

	float SPEED = 600;
	Vector2 SPEED_VECTOR =  new Vector2(1f,2f).nor();
	
	public Texture getImg() {
		return img;
	}
	
	
	public Vector2 getSPEED_VECTOR() {
		return SPEED_VECTOR;
	}


	public void setSPEED_VECTOR(Vector2 sPEED_VECTOR) {
		SPEED_VECTOR = sPEED_VECTOR;
	}


	public Ball(float x, float y) {
		this.width = WIDTH;
		this.height = WIDTH;
		this.x = x - WIDTH / 2;
		this.y = y - WIDTH / 2;
		Pixmap p = new Pixmap( (int)this.width, (int)this.height, Format.RGBA8888 );
		p.setColor(Color.WHITE);
		p.fill();
		
		this.img = new Texture(p);
		p.dispose();
	} 
	
	public void move(float delta) {
		this.setPosition(this.x+(SPEED*SPEED_VECTOR.x*delta)
				,this.y + (SPEED*SPEED_VECTOR.y*delta));
		
		// checking collisions
		// y  - direction
		if(this.y > Application.V_HEIGHT - this.height) {
			SPEED_VECTOR.y = -SPEED_VECTOR.y;
			this.y = Application.V_HEIGHT - this.height;
		}
		if (this.y <  0) {
			SPEED_VECTOR.y = -SPEED_VECTOR.y;
			this.y = 0;
		}
		// x - direction
		if(this.x > Application.V_WIDTH - this.width) {
			SPEED_VECTOR.x = -SPEED_VECTOR.x;
			this.x = Application.V_WIDTH - this.width;
		}
		if (this.x < 0) {
			SPEED_VECTOR.x = -SPEED_VECTOR.x;
			this.x = 0;
		}
		
	}
	
	public void checkCollisions(Paddle paddleA) {
//		if (this.overlaps(paddleA)){
//			if (this.y > paddleA.y && this.y < paddleA.y + paddleA.height) {
//				
//				SPEED_VECTOR.x = -SPEED_VECTOR.x;
//			}
//			else if(this.y <  paddleA.y ) {
//				this.y = paddleA.y - this.height;
//				SPEED_VECTOR.y = - SPEED_VECTOR.y;
//			}
//			else {
//				this.y = paddleA.y + paddleA.height + 10 ;
//				SPEED_VECTOR.y = - SPEED_VECTOR.y;
//			}
		// collision horizontally
		if (this.y > paddleA.y - this.height && this.y < paddleA.y + paddleA.height 
				&& (Math.abs(this.getCenterX() - paddleA.getCenterX()) < 
				(this.width / 2 + paddleA.width / 2) )){
			// should we add changing the position here also?
			
			this.x = this.x > paddleA.x ? paddleA.x+paddleA.width :
				paddleA.x - this.width; 
			SPEED_VECTOR.x = -SPEED_VECTOR.x;
		}
		// collisions vertically
		// Not working properly but maybe dont need to??
		if (this.x > paddleA.x - this.width && this.x < paddleA.x + paddleA.width 
				&& (Math.abs(this.getCenterY() - paddleA.getCenterY()) < 
				(this.height / 2 + paddleA.height/ 2) )){
			// should we add changing the position here also?
			
			this.y = this.y > paddleA.y ? paddleA.y+paddleA.height :
				paddleA.y - this.height; 
			SPEED_VECTOR.y = -SPEED_VECTOR.y;
		}
	}
	
	public float getCenterX() {
		return this.x + this.width/2;
	}
	
	private float getCenterY() {
		return this.y + this.height/2;
	}
	
	public void dispose() {
		img.dispose();
	}
	
	public void init() {
		this.setPosition(Application.V_WIDTH/2, Application.V_HEIGHT/2);
	}
}

