package tile.type;

public class Type {
	
	private String name;
	
	public Type(String name)
	{
		this.name = name;
	}
	
	public String getTypeName()
	{
		return name;
	}
	
	@Override
	public boolean equals(Object x)
	{
		if(x instanceof Type && ((Type) x).getTypeName().equals(name)) return true;
		return false;
		
	}
}
