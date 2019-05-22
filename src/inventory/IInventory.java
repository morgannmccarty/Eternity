package inventory;

import entity.IEntity;
import inventory.slot.Slot;

public interface IInventory {
	public IEntity getEntity();
	public Slot getSlot(); //TODO Create a slot system
}
