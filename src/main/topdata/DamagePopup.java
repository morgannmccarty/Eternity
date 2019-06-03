package main.topdata;

import util.Position;

public class DamagePopup extends DisplayText {

	public DamagePopup(Position pos, int damage) {
		super(pos.getX(), pos.getY(), 1.0F, 0.0F, 0.0F, Integer.toString(damage));
		
	}
	
	public void moveUp()
	{
		super.move(0F, 0.25F);
	}

}
