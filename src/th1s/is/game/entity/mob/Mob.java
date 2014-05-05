package th1s.is.game.entity.mob;


import java.util.ArrayList;
import java.util.List;

import th1s.is.game.entity.Entity;
import th1s.is.game.entity.particle.Particle;
import th1s.is.game.entity.projectile.Projectile;
import th1s.is.game.entity.projectile.magic;
import th1s.is.game.graphics.Sprite;
import th1s.is.game.level.Level;

public abstract class Mob extends Entity{
	protected Sprite sprite;
	protected int dir = 0; // default direction - up
	protected boolean moving = false;
	
	
	public void move(int xa, int ya){
		if (xa != 0 && ya != 0){
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		//set the direction
		if(xa > 0)
			dir = 1; // right
		if(xa < 0)
			dir = 3; // left
		if(ya > 0)
			dir = 2; // down
		if(ya < 0)
			dir = 0; // up
		
		// if there isn't a collision
		if(!collision(xa, ya)){
			x += xa;
			y += ya;
		}			
	}
	
	public void update(){
		
	}
	
	protected void shoot(int x, int y, double dir){
		Projectile p = new magic(x, y, dir);
		level.add(p);
	}
	
	private boolean collision(int xa, int ya){
		boolean solid = false;
		for(int c = 0; c < 4; c++){
			int xt = ((x + xa) + c % 2 * 13 - 6) / 16;
			int yt = ((y + ya) + c / 2 * 11 + 5) / 16;
			
			if(level.getTile(xt, yt).solid())
				solid = true;
		}
		return solid;
	}
	
	public void render(){
		
	}
}
