package item;

public class Item implements IItem{

	private String name;
	
	public Item(String name)
	{
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

}
