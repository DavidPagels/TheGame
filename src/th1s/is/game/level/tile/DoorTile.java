package th1s.is.game.level.tile;

import th1s.is.game.graphics.Screen;
import th1s.is.game.graphics.Sprite;

public class DoorTile extends Tile{
	
	public DoorTile(Sprite sprite){
		super(sprite);
		
	}
	
	public void render(int x, int y, Screen screen){
		path.render(x, y, screen);
		screen.renderTile((x << 4) - 7,  (y << 4) - 32, this);
	}

	public boolean solid(){
		return false;
	}
	
	public void isdoor(int x, int y){
			
	}
}
