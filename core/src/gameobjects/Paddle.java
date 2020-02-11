package gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.pongplus.game.Application;

public class Paddle extends Rectangle{
	
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
	
	public void movePaddle(float amount) {
		if(this.y <= Application.V_HEIGHT - this.height && 
				this.y >= 0 ) {
			this.setPosition(this.x, this.y + amount);
		}
		else{
			this.setPosition(this.x, Application.V_HEIGHT - this.height);
		}
			
	}
	
	public void dispose() {
		img.dispose();
	}

}
