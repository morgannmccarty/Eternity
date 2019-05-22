package entity;

import tile.type.Type;
import util.Position;

public class EntityRogue extends AbstractMonster{


	public EntityRogue(Position pos) {
		super(pos, new Type("rogue"), 200, 1.0F, 0.0F, 0.0F);
	}

	@Override
	public void die() {
	}
	
	@Override
	public int getSpeed()
	{
		return 2;
	}
	
	
}
