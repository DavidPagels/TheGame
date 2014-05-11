package th1s.is.game.entity.mob;

import th1s.is.game.Game;
import th1s.is.game.entity.projectile.Projectile;
import th1s.is.game.entity.projectile.magic;
import th1s.is.game.graphics.AnimatedSprite;
import th1s.is.game.graphics.Screen;
import th1s.is.game.graphics.Sprite;
import th1s.is.game.graphics.SpriteSheet;
import th1s.is.game.input.Keyboard;
import th1s.is.game.input.Mouse;

public class Player extends Mob{
	
	private Keyboard input;
	private Sprite sprite;
	private boolean walking = false;
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 2);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 2);
	private AnimatedSprite animSprite = down;
	
	private int fireRate = 0;
	
	public Player(Keyboard input){
		this.input = input;
		sprite = Sprite.playerb; // default - player facing forward 
		animSprite = down;
	}

	public Player(int x, int y, Keyboard input){
		this.x = x;
		this.y = y;
		this.input = input;
		fireRate = magic.FIRE_RATE;
		
	}
	
	public void update(){
		if(walking) 
			animSprite.update();
		else
			animSprite.setFrame(0);
		if(fireRate > 0)
			fireRate--;
		int xa = 0,
			ya = 0;
		
		
		if(input.up){ // move the corresponding direction
			ya--;
			animSprite = up;
		}
		if(input.down){
			ya++;
			animSprite = down;
		}
		if(input.left){
			xa--;
			animSprite = left;
		}
		if(input.right){
			xa++;
			animSprite = right;
		}
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
		/*
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
			flip = 1;*/
		sprite = animSprite.getSprite();
		screen.renderMob((int)x - 16,(int) y - 24, sprite, flip); //offset to middle of player
	}
	
}
