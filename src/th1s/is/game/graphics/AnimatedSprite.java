package th1s.is.game.graphics;

public class AnimatedSprite extends Sprite {
	
	private int frame = 0;
	private Sprite sprite;
	private int rate = 10;
	private int length = -1;
	private int time = 0;
	
	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length){
		super(sheet, width, height);
		this.length = length;
		sprite = sheet.getSprites()[0];
		if(length > sheet.getSprites().length)
			System.err.println("Too many frames!");
	}
	
	public void update() {
		time++;
		if(time % rate ==0){
			if(frame >= length - 1)
				frame = 0;
			else
				frame++;
			sprite = sheet.getSprites()[frame];
		}
		
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setFrameRate(int frames){
		
	}
	public void setFrame(int index){
		if(index > sheet.getSprites().length - 1){
			System.err.println("Index out of bounds: " + this);
			return;
		}
		sprite = sheet.getSprites()[index];
	}
}
