package th1s.is.game.level.tile;

import th1s.is.game.graphics.Screen;
import th1s.is.game.graphics.Sprite;

public class WoodTile extends Tile{
	public WoodTile(Sprite sprite){
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4,  y << 4,  this);
	}
	
	public boolean solid(){ // method for collision
		return true;
	}
}
