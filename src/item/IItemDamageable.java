package item;

public interface IItemDamageable extends IItem{
	public int getDurability();
	public void degrade();
}
