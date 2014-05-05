package th1s.is.game.entity.mob;

import th1s.is.game.Game;
import th1s.is.game.entity.projectile.Projectile;
import th1s.is.game.entity.projectile.magic;
import th1s.is.game.graphics.Screen;
import th1s.is.game.graphics.Sprite;
import th1s.is.game.input.Keyboard;
import th1s.is.game.input.Mouse;

public class Player extends Mob{
	
	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	
	private int fireRate = 0;
	
	public Player(Keyboard input){
		this.input = input;
		sprite = Sprite.playerb; // default - player facing forward 
	}

	public Player(int x, int y, Keyboard input){
		this.x = x;
		this.y = y;
		this.input = input;
		fireRate = magic.FIRE_RATE;
		
	}
	
	public void update(){
		if(fireRate > 0)
			fireRate--;
		int xa = 0,
			ya = 0;
		
		if(anim < 1000000000) // reset the anim so anim stays under 2.1b
			anim++;
		else 
			anim = 0;
		
		if(input.up) // move the corresponding direction
			ya--;
		if(input.down)
			ya++;
		if(input.left)
			xa--;
		if(input.right)
			xa++;
		if(xa != 0 || ya != 0){ // if not standing still
			move(xa, ya); // move
			walking = true; // set walking to true
		}
		else{
			walking = false;
		}
		
		clear();
		updateShooting();
	}
	
	private void clear(){
		for(int i = 0; i < level.getProjectiles().size(); i++){
			Projectile p = level.getProjectiles().get(i);
			if(p.isRemoved())
				level.getProjectiles().remove(i);
		}
	}
	private void updateShooting() {
		if(Mouse.getButton() == 1 && fireRate <= 0){
			double dx = Mouse.getX() - Game.getWindowWidth()/ 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = magic.FIRE_RATE;
		}
	}

	public void render(Screen screen){
		int flip = 0;
		if(dir == 0){
			sprite = Sprite.playerf; // set sprite to corresponding direction
			if(walking){ // if walking
				if(anim % 20 > 10) // animate the sprite
					sprite = Sprite.playerf1;
				else
					sprite = Sprite.playerf2;
			}
		}
		if(dir == 1 || dir == 3){
			sprite = Sprite.players;
			if(walking){
				if(anim % 20 > 10)
					sprite = Sprite.players1;
				else
					sprite = Sprite.players;
			}
		}
			
		if(dir == 2){
			sprite = Sprite.playerb;
			if(walking){
				if(anim % 20 > 10)
					sprite = Sprite.playerb1;
				else
					sprite = Sprite.playerb2;
			}
		}
		
		if(dir == 3)
			flip = 1;
		screen.renderPlayer((int)x - 16,(int) y - 16, sprite, flip); //offset to middle of player
	}
	
}
