package entity;

import tile.type.Type;
import util.Position;

public class EntityMage extends AbstractMonster implements IEntityMagical{

	private int mana = 5;
	private int maxMana = 5;
	
	public EntityMage(Position pos) {
		super(pos, new Type("mage"), 50, 0F, 1.0F, 0F);
	}

	@Override
	public int getMana() {
		return mana;
	}

	@Override
	public int getMaxMana() {
		return maxMana;
	}

	@Override
	public void changeManaBy(int ammount) {
		if(Math.abs(mana + ammount) >= 0)
		{
			if(mana + ammount > maxMana) mana = maxMana;
			else mana += ammount;
			
		}
	}

	@Override
	public void changeManaTo(int ammount) {
		if(ammount > 0) mana = ammount;
	}
	
	public void fireFireball()
	{
		if(mana >= 5)
		{
			//EntityProjectileRegistry.createProjectile(this, Main.player.getDirectionToEntity(this));
			changeManaBy(-5);
		}
	}
	
	@Override
	public void onTick()
	{
		changeManaBy(1);
	}

}
