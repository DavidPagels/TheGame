package th1s.is.game.level;

import java.util.Random;

public class RandomLevel extends Level{
	
	private static final Random random = new Random();
	public RandomLevel(int w, int h){
		super(w, h);
	}
	
	protected void generateLevel(){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				if(x < 10 || x > width - 10 || y < 10 || y > height - 10)
					tilesInt[x + y * width] = 2;
				else
					//make a random array containing ints 1-2
					tilesInt[x + y * width] = random.nextInt(6); 
			}
		}
		
				
	}
}
