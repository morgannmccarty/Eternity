package entity;

import java.util.Random;

import main.Main;
import tile.type.Type;
import util.Position;

public class EntityRogue extends AbstractMonster{


	public EntityRogue(Position pos) {
		super(pos, new Type("rogue"), 70, 1.0F, 0.0F, 0.0F);
	}
	
	@Override
	public int getSpeed()
	{
		return 2;
	}
	
	public void attack()
	{
		if((this.getPosition().getX() + 1 == Main.player.getPosition().getX()  && this.getPosition().getY() == Main.player.getPosition().getY())|| (this.getPosition().getX() - 1 == Main.player.getPosition().getX() && this.getPosition().getY() == Main.player.getPosition().getY()) || (this.getPosition().getY() + 1 == Main.player.getPosition().getY() && this.getPosition().getX() == Main.player.getPosition().getX()) || (this.getPosition().getY() - 1 == Main.player.getPosition().getY() && this.getPosition().getX() == Main.player.getPosition().getX()))
		{
			Random rand = new Random();
			Main.player.changeHealthBy(-((rand.nextInt(5)) + 5));
		}
	}
	
}
