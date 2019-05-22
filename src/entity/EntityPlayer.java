package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import main.Library;
import main.Main;
import main.TopData;
import util.Position;

public class EntityPlayer extends AbstractEntity implements IEntityMagical, IPlayable{
	
	private static List<Integer> ids = new ArrayList<Integer>();
	private int mana, health;
	private int maxMana = 10;
	private int maxHealth = 100;
	
	@SuppressWarnings("static-access")
	public EntityPlayer(Position pos, int id, int mana, int health)
	{
		super(pos, 0.0F, 0.0F, 0.5F);
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
	
	public void changeHealthBy(int health)
	{
		this.health+=health;
	}
	
	public void changeHealthTo(int health)
	{
		this.health = health;
	}
	
	public int getMaxHealth()
	{
		return maxHealth;
	}
	
	public void changeManaBy(int mana)
	{
		if(this.mana < maxMana || mana < 0) this.mana += mana;
		Library.LOGGER.log(Level.INFO, "Player mana is " + this.mana);
		TopData.changeManaDisplay();
	}
	
	public int getMaxMana()
	{
		return maxMana;
	}
	
	public void chageManaTo(int mana)
	{
		this.mana = mana;
		Library.LOGGER.log(Level.INFO, "Player mana is " + this.mana);
		TopData.changeManaDisplay();
	}

}
