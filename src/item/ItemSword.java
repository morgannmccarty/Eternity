package item;

public class ItemSword extends Item implements IWeapon, IItemSellable, IItemBuyable{

	private float value;
	
	public ItemSword(String name, float value) {
		super(name);
		this.value = value;
	}

	@Override
	public int getDurability() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void degrade() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDamageDealt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void dealDamage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getBuyValue() {
		return value * 0.8F;
	}

	@Override
	public void sell() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getSellValue() {
		return value * 1.2F;
	}

}
