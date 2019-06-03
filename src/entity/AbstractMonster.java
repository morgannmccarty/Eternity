package entity;

import main.Library;
import main.Main;
import main.Map;
import tile.type.Type;
import util.Position;

public abstract class AbstractMonster extends AbstractEntityLevelable implements IMonster, IKillable, IEntityHealable{

	private Type type;
	
	private int health;
	private int maxHealth;
	
	public AbstractMonster(Position pos, Type type, int health, float red, float green, float blue) {
		super(pos, red, green, blue, 1, 1);
		this.health = health; this.maxHealth = health;
		this.type = type;
	}
	
	@Override
	public void die() {
		Main.player.changeGoldBy(this.getLevel());
		Main.player.addExperience(this.getLevel() * 10);
		//System.out.println(Main.player.getPlayerGold());
		EntityRegistry.entities.remove(this);
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
	
	@Override
	public void changeHealthBy(int ammount) {
		this.health += ammount;
		if(health <= 0) this.die();
	}

	@Override
	public void changeHealthTo(int ammount) {
		this.health = ammount;
		if(health <= 0) this.die();
	}
	
	@Override
	public void moveUp() {
		if(super.getPosition().getY() + 1 >= Library.MAX_POSITION.getY()) return;
		if(!(Map.checkAdjacentTileType(new Type("wall"), 0, 1, super.getPosition())) && !(Map.checkAdjacentTileType(new Type("border"), 0, 1, super.getPosition())) && !Map.adjacentTile(0, 1, super.getPosition()).isSafe())
			move(0, 1);
	}

	@Override
	public void moveDown() {
		if(super.getPosition().getY() - 1 <= Library.MIN_POSITION.getY()) return;
		if(!(Map.checkAdjacentTileType(new Type("wall"), 0, -1, super.getPosition())) && !(Map.checkAdjacentTileType(new Type("border"), 0, -1, super.getPosition())) && !Map.adjacentTile(0, -1, super.getPosition()).isSafe())
			move(0, -1);
	}

	@Override
	public void moveLeft() {
		if(super.getPosition().getY() - 1 <= Library.MIN_POSITION.getX()) return;
		if(!(Map.checkAdjacentTileType(new Type("wall"), -1, 0, super.getPosition())) && !(Map.checkAdjacentTileType(new Type("border"), -1, 0, super.getPosition())) && !Map.adjacentTile(1, 0, super.getPosition()).isSafe())
			move(-1, 0);
	}

	@Override
	public void moveRight() {
		if(super.getPosition().getY() + 1 <= Library.MIN_POSITION.getX()) return;
		if(!(Map.checkAdjacentTileType(new Type("wall"), 1, 0, super.getPosition())) && !(Map.checkAdjacentTileType(new Type("border"), 1, 0, super.getPosition())) && !Map.adjacentTile(-1, 0, super.getPosition()).isSafe())
			move(1, 0);
	}

}
