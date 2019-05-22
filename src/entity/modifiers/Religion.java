package entity.modifiers;

import entity.IEntity;

public class Religion {
	private String name;
	private IEntity deity;
	public Religion(String name, IEntity deity)
	{
		this.name = name;
		this.deity = deity;
	}
	public String getName()
	{
		return name;
	}
	public IEntity getDeity()
	{
		return deity;
	}
}
