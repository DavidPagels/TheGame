package th1s.is.game.entity.mob;

import th1s.is.game.graphics.AnimatedSprite;
import th1s.is.game.graphics.Screen;
import th1s.is.game.graphics.Sprite;
import th1s.is.game.graphics.SpriteSheet;

public class Dummy extends Mob{
	
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 2);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 2);
	
	private AnimatedSprite animSprite = down;
	
	public Dummy (int x, int y){
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
	}
	
	public void update() {
		int xa = 0;
		int ya = 0;
		
		if(walking) 
			animSprite.update();
		else
			animSprite.setFrame(0);
		
		if(ya > 0){ // move the corresponding direction
			animSprite = down;
			dir = Direction.UP;
		}
		if(ya < 0){
			animSprite = up;
			dir = Direction.DOWN;
		}
		if(xa < 0){
			animSprite = left;
			dir = Direction.LEFT;
		}
		if(xa > 0){
			animSprite = right;
			dir = Direction.RIGHT;
		}
		if(xa != 0 || ya != 0){ // if not standing still
			move(xa, ya); // move
			walking = true; // set walking to true
		}
		else{
			walking = false;
		}
		
	}
	
	public void render(Screen screen){
		sprite = animSprite.getSprite();
		screen.renderMob((int)x - 16,(int) y - 24, sprite, 0); //offset to middle of player
	}

}
