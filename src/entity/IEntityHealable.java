package entity;

public interface IEntityHealable extends IKillable{
	public int getHealth();
	public int getMaxHealth();
	public void changeHealthBy(int ammount);
	public void changeHealthTo(int ammount);
	public void changeMaxHealthBy(int ammount);
}
