package item;

public class ItemShield extends Item implements IArmor, IItemSellable, IItemBuyable {

	private float value;
	private int preventionAmount;

	public ItemShield(String name, float value, int preventionAmount) {
		super(name);
		this.value = value;
		this.preventionAmount = preventionAmount;
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

	@Override
	public int getPreventionAmount() {
		return preventionAmount;
	}

	@Override
	public void preventDamage() {
		// TODO Auto-generated method stub

	}

}
