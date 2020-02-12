package gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.pongplus.game.Application;

public class Paddle extends Rectangle{
	private final static float SPEED = 400;
	
	String name;
	Texture img;
	
	public Texture getImg() {
		return img;
	}

	public void setImg(Texture img) {
		this.img = img;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Paddle(float x, String name, float width, float height) {
		this.name = name;
		
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = Application.V_HEIGHT/2 - this.height / 2;
		Pixmap p = new Pixmap( (int)this.width, (int)this.height, Format.RGBA8888 );
		p.setColor(Color.WHITE);
		p.fill();
		
		this.img = new Texture(p);
		p.dispose();
		
	}
	
	public void movePaddle(float timeStep) {
		this.setPosition(this.x, this.y + SPEED*timeStep);
		// Limits for moving
		if(this.y > Application.V_HEIGHT - this.height ) {
			this.setPosition(this.x, Application.V_HEIGHT - this.height);
		}
		else if(this.y < 0){
			this.setPosition(this.x, 0);
		}
			
	}
	
	public void dispose() {
		img.dispose();
	}
	
	public float getCenterX() {
		return this.x + this.width/2;
	}
	
	public float getCenterY() {
		return this.y + this.height/2;
	}

}
