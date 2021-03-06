package th1s.is.game.entity.projectile;

import th1s.is.game.entity.spawner.ParticleSpawner;
import th1s.is.game.graphics.Screen;
import th1s.is.game.graphics.Sprite;

public class magic extends Projectile{
	
	public static final int FIRE_RATE = 1;

	public magic(int x, int y, double dir){
		super(x, y, dir);
		range = 100;
		speed = 5;
		damage = 20;
		sprite = Sprite.bolt;
		
		nx = Math.cos(angle) * speed;
		ny = Math.sin(angle) * speed;
	}
	
	public void update(){
		if(level.tileCollision((int)(x + nx), (int)(y + ny), 12, 2, 2)){
			level.add(new ParticleSpawner((int)x, (int)y, 44, 50, level));
			remove();
		}
		move();
	}
	
	protected void move(){
		x += nx;
		y += ny;
		
		if(distance() > range) 
			remove();
	}
	
	private double distance(){
		double dist = 0;
		dist = Math.sqrt((xOrigin - x) * (xOrigin - x) + (yOrigin - y)*(yOrigin - y));
		return dist;
	}
	
	public void render(Screen screen){
		screen.renderProjectile((int)x - 7, (int)y - 10, this);
	}
}
