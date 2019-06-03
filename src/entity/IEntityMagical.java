package entity;

public interface IEntityMagical extends IEntityHealable{
	public int getMana();
	public int getMaxMana();
	public void changeManaBy(int ammount);
	public void changeManaTo(int ammount);
	public void changeMaxManaBy(int ammount);
}
