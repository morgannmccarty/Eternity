package entity;

import util.Position;

public abstract class AbstractEntityLevelable extends AbstractEntity implements ILevelable{

	private int level;
	private int experience;
	
	public AbstractEntityLevelable(Position pos, float red, float green, float blue, int level, int startingExperience)
	{
		super(pos, red, green, blue);
		this.level = level;
		this.experience = startingExperience;
	}
	
	@Override
	public int getLevel()
	{
		return level;
	}
	
	@Override
	public void levelUp()
	{
		if(experience >= this.getExperienceReq())
		{
			setLevel(this.level + 1);
			levelUp();
		}
	}
	
	@Override
	public int getExperience()
	{
		return experience;
	}
	
	@Override
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	@Override
	public void addExperience(int experience)
	{
		this.experience += experience;
		levelUp();
	}
	
	@Override
	public void setExperience(int experience)
	{
		this.experience = experience;
		levelUp();
	}
	
	@Override
	public int getExperienceReq()
	{
		return this.level*this.level * 100;
	}

}
