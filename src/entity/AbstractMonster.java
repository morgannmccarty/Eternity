package entity;

import tile.type.Type;
import util.Position;

public abstract class AbstractMonster extends AbstractEntity implements IMonster, IKillable, IEntityHealable{

	private Type type;
	
	private int health;
	private int maxHealth;
	
	public AbstractMonster(Position pos, Type type, int health, float red, float green, float blue) {
		super(pos, red, green, blue);
		this.health = health; this.maxHealth = health;
		this.type = type;
	}
	
	@Override
	public Type getType()
	{
		return type;
	}
	
	@Override
	public int getHealth()
	{
		return health;
	}
	
	@Override
	public int getMaxHealth()
	{
		return maxHealth;
	}

}
