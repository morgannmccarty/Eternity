package entity;

import java.util.ArrayList;
import java.util.List;

import item.ItemStackable;
import main.Main;
import main.topdata.TopData;
import util.Position;

public class EntityPlayer extends AbstractEntityLevelable implements IEntityMagical, ILevelable {
	
	private static List<Integer> ids = new ArrayList<Integer>();
	private int mana, health;
	private int maxMana = 10;
	private int maxHealth = 100;
	private int damageAmount = 10;
	private ItemStackable gold = new ItemStackable("gold", 10);
	
	
	@SuppressWarnings("static-access")
	public EntityPlayer(Position pos, int id, int mana, int health)
	{
		super(pos, 0.0F, 0.0F, 0.5F, 1, 0);
		if(ids.contains(id)) throw new IllegalArgumentException("ID already used.");
		else this.ids.add(id);
		this.mana = mana;
		this.health = health;
	}
	
	@Override
	public void die() {
		Main.gameOver = true;
	}

	@Override
	public int getMana() {
		return mana;
	}

	@Override
	public int getHealth() {
		return health;
	}
	
	@Override
	public void changeHealthBy(int health)
	{
		this.health+=health;
		if(this.health <= 0) die();
		TopData.changeHealthDisplay();
	}
	
	@Override
	public void setLevel(int level)
	{
		super.setLevel(level);
		TopData.changeExperienceCounter();
	}
	
	@Override
	public void addExperience(int experience)
	{
		super.addExperience(experience);
		TopData.changeExperienceCounter();
	}
	
	@Override
	public void setExperience(int experience)
	{
		super.setExperience(experience);
		TopData.changeExperienceCounter();
	}
	
	
	@Override
	public void changeHealthTo(int health)
	{
		this.health = health;
		TopData.changeHealthDisplay();
	}
	
	@Override
	public int getMaxHealth()
	{
		return maxHealth;
	}
	
	@Override
	public void changeManaBy(int mana)
	{
		if(this.mana < maxMana || mana < 0) this.mana += mana;
		//Library.LOGGER.log(Level.INFO, "Player mana is " + this.mana);
		TopData.changeManaDisplay();
	}

	public int getPlayerGold()
	{
		return gold.getCount();
	}
	
	public void changeGoldBy(int ammount)
	{
		gold.changeCountBy(ammount);
		TopData.changeGoldCounter();
	}
	
	@Override
	public int getMaxMana()
	{
		return maxMana;
	}
	
	@Override
	public void changeManaTo(int mana)
	{
		this.mana = mana;
		//Library.LOGGER.log(Level.INFO, "Player mana is " + this.mana);
		TopData.changeManaDisplay();
	}

	public void slash(int lastDirection) {
		if(mana > 0) changeManaBy(-1);
		else changeHealthBy(-10);
		List<IEntity> entitiesAdjacent = lastDirection == 0 ? EntityRegistry.getEntitiesAtPos(new Position(getPosition().getX(), getPosition().getY()+1)) : lastDirection == 1 ? EntityRegistry.getEntitiesAtPos(new Position(getPosition().getX() + 1, getPosition().getY())) : lastDirection == 2 ? EntityRegistry.getEntitiesAtPos(new Position(getPosition().getX(), getPosition().getY() - 1)) : EntityRegistry.getEntitiesAtPos(new Position(getPosition().getX() - 1, getPosition().getY()));
		for(int i = 0; i<entitiesAdjacent.size(); ++i)
		{
			if(entitiesAdjacent.get(i) instanceof IEntityHealable) ((IEntityHealable)(entitiesAdjacent.get(i))).changeHealthBy(damageAmount);
		}
	}

	
	

}