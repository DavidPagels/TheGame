package th1s.is.game.level;

import java.util.ArrayList;
import java.util.List;

import th1s.is.game.entity.Entity;
import th1s.is.game.entity.particle.Particle;
import th1s.is.game.entity.projectile.Projectile;
import th1s.is.game.graphics.Screen;
import th1s.is.game.level.tile.Tile;

public class Level {
	protected int width, height;
	public static int tilesInt[];
	protected int tiles[];
	protected int tile_size;
	
	private List<Entity> entities = new ArrayList<Entity>();
	public List<Projectile> projectiles = new ArrayList<Projectile>();
	public List<Particle> particles = new ArrayList<Particle>();
	
	public Level(int width, int height){
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level(String path){
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel() {
		
	}
	
	protected void loadLevel(String path){
		
	}
	
	public void update(){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).update();
		}
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).update();
		}
		for(int i = 0; i < particles.size(); i++){
			particles.get(i).update();
		}
		remove();
	}
	
	private void remove(){
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i).isRemoved())
				entities.remove(i);
		}
		for(int i = 0; i < projectiles.size(); i++){
			if(projectiles.get(i).isRemoved())
				projectiles.remove(i);
		}
		for(int i = 0; i < particles.size(); i++){
			if(particles.get(i).isRemoved())
				particles.remove(i);
		}
	}
	
	public List<Projectile> getProjectiles(){
		return projectiles;
	}
	
	private void time(){
		
	}
	
	public boolean tileCollision(double x, double y, double xa, double ya, int size){
		boolean solid = false;
		for(int c = 0; c < 4; c++){
			int xt = (((int)x + (int)xa) + c % 2 * size - 10) / 16;
			int yt = (((int)y + (int)ya) + c / 2 * size) / 16;
			if(getTile((int)xt, (int)yt).solid())
				solid = true;
		}
		return solid;
	}
	
	public void render(int xScroll, int yScroll, Screen screen){
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4,
			x1 = (xScroll + screen.width + 16) >> 4,
			y0 = yScroll >> 4, 
			y1 = (yScroll + screen.height + 16) >> 4;
			for(int y = y0; y < y1; y++){
				for(int x = x0; x < x1; x++){
					getTile(x, y).render(x, y, screen);
				}
			}
			
			for(int i = 0; i < entities.size(); i++){
				entities.get(i).render(screen);
			}
			
			for(int i = 0; i < projectiles.size(); i++){
				projectiles.get(i).render(screen);
			}
			for(int i = 0; i < particles.size(); i++){
				particles.get(i).render(screen);
			}
	}
	
	public void add(Entity e) {
		e.init(this);
		if(e instanceof Particle){
			particles.add((Particle)e);
		}
		else if (e instanceof Projectile){
			projectiles.add((Projectile)e);
		}
		else{
			entities.add(e);
		}
	}

	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		switch(tiles[x + y * width]){
			case 0xFF4CFF00:
				return Tile.grass;
			case 0xFFA5FFFA:
				return Tile.styro;
			case 0xFF7F3300:
				return Tile.wood;
			case 0xFF404040:
				return Tile.path;
			case 0xFF808080:
				return Tile.pathl;
			case 0xFF808081:
				return Tile.pathr;
			case 0xFF808082:
				return Tile.pathb;
			case 0xFF808083:
				return Tile.patht;
			case 0xFF808084:
				return Tile.pathobl;
			case 0xFF808085:
				return Tile.pathobr;
			case 0xFF808086:
				return Tile.pathotr;
			case 0xFF808087:
				return Tile.pathotl;
			case 0xFF808088:
				return Tile.pathitl;
			case 0xFF808089:
				return Tile.pathibl;
			case 0xFF80808A:
				return Tile.pathibr;
			case 0xFF80808B:
				return Tile.pathitr;
			case 0xFFFF6A00:
				return Tile.door;
			default:
				return Tile.voidTile;
		}
	}
}
