package th1s.is.game.entity.projectile;

import th1s.is.game.entity.Entity;
import th1s.is.game.graphics.Sprite;

public abstract class Projectile extends Entity {
	
	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y, nx, ny;
	protected double speed, range, damage;
	
	public Projectile(int x, int y, double dir){
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	protected void move(){
		
	}
	
	
}
