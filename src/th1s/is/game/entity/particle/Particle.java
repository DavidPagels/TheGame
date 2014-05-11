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
			za *= -.675;
			xa *= .45;
			ya *= .45;
		}
		move(xx + xa, yy + ya + zz + za);
	}
	
	private void move(double x, double y) {
		if(collision(x, y)){
			this.xa *= -.5;
			this.ya *= -.5;
			this.za *= -.5;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
		
	}
	
	public boolean collision(double x, double y){
		boolean solid = false;
		for(int c = 0; c < 4; c++){
			double xt = (x - c % 2 * 16) / 16;
			double yt = (y - c / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if(c % 2 == 0) ix = (int) xt;
			if(c / 2 == 0) iy = (int) yt;
			if(level.getTile(ix, iy).solid())
				solid = true;
		}
		return solid;
	}

	public void render(Screen screen){
		screen.renderSprite((int)xx, (int)yy - (int)zz, sprite, true);
	}
	
}

