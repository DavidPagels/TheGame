package th1s.is.game.entity.spawner;

import th1s.is.game.entity.Entity;
import th1s.is.game.entity.particle.Particle;
import th1s.is.game.level.Level;

public abstract class Spawner extends Entity {
	
	public enum Type {MOB, PARTICLE}
	
	private Type type;
	
	public Spawner(int x, int y, Type type, int amount, Level level){
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
