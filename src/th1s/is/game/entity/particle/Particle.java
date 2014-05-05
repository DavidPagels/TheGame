package th1s.is.game.entity.particle;

import th1s.is.game.entity.Entity;
import th1s.is.game.graphics.Screen;
import th1s.is.game.graphics.Sprite;

public class Particle extends Entity {
	private Sprite sprite;
	private int life;
	private int time = 0;
	
	protected double xx, yy, zz;
	protected double xa, ya, za;
	
	public Particle(int x, int y, int life){
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(20) - 5);
		sprite = Sprite.particle_normal;
		this.xa = random.nextGaussian() + 2;
		if(this.xa < 0){
			xa = 0.1;
		}
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 7;
	}
	
	public void update(){
		time++;
		if(time > 9999 - 1)
			time = 0;
		if(time > life)
			remove();
		za -= 0.1;
		if(zz < 0){
			zz = 0;
			za *= -.65;
			xa *= .45;
			ya *= .45;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}
	
	public void render(Screen screen){
		screen.renderSprite((int)xx - 10, (int)yy - (int)zz, sprite, true);
	}
	
}

