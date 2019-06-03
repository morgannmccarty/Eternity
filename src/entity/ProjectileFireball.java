package entity;

import java.util.Random;

import util.Position;

public class ProjectileFireball extends AbstractProjectile{
	
	private int damage;
	
	public ProjectileFireball(Position pos, int direction, IEntity controllingEntity, int damage)
	{
		super(pos, direction, controllingEntity, 10);
		this.damage = damage;
	}
	
	@Override
	public void launch() {
		if(super.getDirection() == 0) super.move(0, .5F);
		else if(super.getDirection() == 1) super.move(0, -.5F);
		else if(super.getDirection() == 2) super.move(.5F, 0);
		else if(super.getDirection() == 3) super.move(-.5F, 0);
		hitEntity();
	}

	@Override
	public boolean hitEntity() {
		Random rand = new Random();
		int damageModified = rand.nextInt(damage)+(damage/2);
		for(int i = 0; i<EntityRegistry.entities.size(); ++i)
		{
			if(EntityRegistry.entities.get(i) instanceof IEntityHealable && EntityRegistry.entities.get(i).getPosition().equals(this.getPosition().getPostionAsInt()) && EntityRegistry.entities.get(i) != super.getControllingEntity())
			{
				((IEntityHealable) EntityRegistry.entities.get(i)).changeHealthBy(-damageModified);
				return true;
			}
		}
		return false;
	}

	@Override
	public int getDirectionToEntity(IEntity entity) {
		return 0;
	}
	
	@Override
	public void onTick()
	{
		
	}

}
