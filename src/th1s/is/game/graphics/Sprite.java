package th1s.is.game.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	private int width, height;
	public int pixels[];
	protected SpriteSheet sheet;
	
	
	public static Sprite grass = new Sprite(16, 1, 0, SpriteSheet.tile);
	public static Sprite styro = new Sprite(16, 0, 0, SpriteSheet.tile);
	public static Sprite wood = new Sprite(16, 2, 0, SpriteSheet.tile);
	public static Sprite path = new Sprite(16, 0, 1, SpriteSheet.tile);
	public static Sprite pathl = new Sprite(16, 1, 1, SpriteSheet.tile);
	public static Sprite pathr = new Sprite(16, 2, 1, SpriteSheet.tile);
	public static Sprite pathb = new Sprite(16, 3, 1, SpriteSheet.tile);
	public static Sprite patht = new Sprite(16, 4, 1, SpriteSheet.tile);
	public static Sprite pathobl = new Sprite(16, 5, 1, SpriteSheet.tile);
	public static Sprite pathobr = new Sprite(16, 6, 1, SpriteSheet.tile);
	public static Sprite pathotr = new Sprite(16, 7, 1, SpriteSheet.tile);
	public static Sprite pathotl = new Sprite(16, 8, 1, SpriteSheet.tile);
	public static Sprite pathitl = new Sprite(16, 0, 2, SpriteSheet.tile);
	public static Sprite pathibl = new Sprite(16, 1, 2, SpriteSheet.tile);
	public static Sprite pathibr = new Sprite(16, 2, 2, SpriteSheet.tile);
	public static Sprite pathitr = new Sprite(16, 3, 2, SpriteSheet.tile);
	public static Sprite door = new Sprite(32, 0, 2, SpriteSheet.tile);
	
	public static Sprite voidSprite = new Sprite(16, 0xFF000000);
	
	public static Sprite playerf = new Sprite(32, 0, 5, SpriteSheet.tile);
	public static Sprite playerb = new Sprite(32, 2, 5, SpriteSheet.tile);
	public static Sprite players = new Sprite(32, 1, 5, SpriteSheet.tile);
	public static Sprite playerf1 = new Sprite(32, 0, 6, SpriteSheet.tile);
	public static Sprite playerf2 = new Sprite(32, 0, 7, SpriteSheet.tile);
	public static Sprite playerb1 = new Sprite(32, 2, 6, SpriteSheet.tile);
	public static Sprite playerb2 = new Sprite(32, 2, 7, SpriteSheet.tile);
	public static Sprite players1 = new Sprite(32, 1, 6, SpriteSheet.tile);
	
	
	public static Sprite bolt = new Sprite(16, 0, 0, SpriteSheet.projectile_mage);
	
	public static Sprite particle_normal = new Sprite(3, 0xfffc9403);
	
	public static Sprite dummy = new Sprite(32, 0, 0, SpriteSheet.player_down);
	
	
	protected Sprite(SpriteSheet sheet, int width, int height){
		if (width == height)
			SIZE = width;
		else 
			SIZE = -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
	public Sprite(int [] pixels, int width, int height){
		if (width == height)
			SIZE = width;
		else 
			SIZE = -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
		//for(int i = 0; i < pixels.length; i++)
			//System.out.println("i : " + pixels[i]);
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet){
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}

	public Sprite(int width, int height, int color){
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}
	
	public Sprite(int size, int color){
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	private void setColor(int color)
	{
		for(int i = 0; i < width * height; i++){
			pixels[i] = color;
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	private void load(){
		for(int y = 0; y < height; y++){
			for(int x = 0; x< width; x++){
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH ];
			}
		}
	}
}
