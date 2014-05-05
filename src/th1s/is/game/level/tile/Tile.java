package th1s.is.game.level.tile;

import th1s.is.game.graphics.Screen;
import th1s.is.game.graphics.Sprite;

public class Tile {
	public int x, y;
	public Sprite sprite;
	//making the sprites into tiles
	public static Tile grass= new GrassTile(Sprite.grass);
	public static Tile styro = new StyroTile(Sprite.styro);
	public static Tile wood = new WoodTile(Sprite.wood);
	public static Tile path = new PathTile(Sprite.path);
	public static Tile pathl = new PathTile(Sprite.pathl);
	public static Tile pathr = new PathTile(Sprite.pathr);
	public static Tile pathb = new PathTile(Sprite.pathb);
	public static Tile patht = new PathTile(Sprite.patht);
	public static Tile pathobl = new PathTile(Sprite.pathobl);
	public static Tile pathobr = new PathTile(Sprite.pathobr);
	public static Tile pathotr = new PathTile(Sprite.pathotr);
	public static Tile pathotl = new PathTile(Sprite.pathotl);
	public static Tile pathitl = new PathTile(Sprite.pathitl);
	public static Tile pathibl = new PathTile(Sprite.pathibl);
	public static Tile pathibr = new PathTile(Sprite.pathibr);
	public static Tile pathitr = new PathTile(Sprite.pathitr);
	public static Tile door = new DoorTile(Sprite.door);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen){
		
	}
	
	public boolean solid(){ // method for collision
		return false;
	}
}
