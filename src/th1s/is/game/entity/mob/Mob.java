package th1s.is.game.entity.mob;


import th1s.is.game.entity.Entity;
import th1s.is.game.entity.projectile.Projectile;
import th1s.is.game.entity.projectile.magic;
import th1s.is.game.graphics.Screen;
import th1s.is.game.graphics.Sprite;

public abstract class Mob extends Entity{
	protected Sprite sprite;
	protected boolean moving = false;
	protected boolean walking = false;
	
	protected enum Direction { UP, DOWN, LEFT, RIGHT } 
	
	protected Direction dir;
	
	public void move(int xa, int ya){
		if (xa != 0 && ya != 0){
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		//set the direction
		if(xa > 0)
			dir = Direction.RIGHT; // right
		if(xa < 0)
			dir = Direction.LEFT; // left
		if(ya > 0)
			dir = Direction.DOWN; // down
		if(ya < 0)
			dir = Direction.UP; // up
		
		// if there isn't a collision
		if(!collision(xa, ya)){
			x += xa;
			y += ya;
		}			
	}
	
	public abstract void update();
	
	public abstract void render(Screen screen);
	
	protected void shoot(int x, int y, double dir){
		Projectile p = new magic(x, y, dir);
		level.add(p);
	}
	
	private boolean collision(int xa, int ya){
		boolean solid = false;
		for(int c = 0; c < 4; c++){
			int xt = ((x + xa) + c % 2 * 13 - 7) / 16;
			int yt = ((y + ya) + c / 2 * 16 - 9) / 16;
			
			if(level.getTile(xt, yt).solid())
				solid = true;
		}
		return solid;
	}
}
