package item;

public class ItemStackable extends Item{

	private int count;
	
	public ItemStackable(String name, int initialCount) {
		super(name);
		this.count = initialCount;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public void changeCountBy(int changeAmount)
	{
		this.count += changeAmount;
	}
	
	public void changeCountTo(int newCount)
	{
		this.count = newCount;
	}

}
