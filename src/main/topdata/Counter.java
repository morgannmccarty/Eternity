package main.topdata;

import util.Position;

public class Counter extends DisplayText{

	public Counter(Position pos, int count) {
		super(pos.getX(), pos.getY(), 0.9F, 0.9F, .15F, Integer.toString(count));
	}
	public Counter(Position pos, int count, int maxCount, float red, float green, float blue)
	{
		super(pos.getX(), pos.getY(), red, green, blue, Integer.toString(count) + " / " + Integer.toString(maxCount));
	}
	
	public void changeCount(int count)
	{
		changeText(Integer.toString(count));
	}
	
	public void changeCount(int count, int maxCount)
	{
		changeText(Integer.toString(count) + " / " + Integer.toString(maxCount));
	}

}
