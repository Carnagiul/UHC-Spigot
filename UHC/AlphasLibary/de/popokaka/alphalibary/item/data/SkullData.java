package de.popokaka.alphalibary.item.data;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullData extends ItemData{
	
	public SkullData(String name) {
		ownerName = name;
	}
	
	private String ownerName = null;

	@Override
	public void applyOn(ItemStack applyOn) throws WrongDataException{
		try {
			if(!(applyOn.getType() == Material.SKULL_ITEM)) {
				return;
			}
			
			applyOn.setDurability((short) 3);
			
			SkullMeta skullMeta = (SkullMeta) applyOn.getItemMeta();
			skullMeta.setOwner(ownerName);
			applyOn.setItemMeta(skullMeta);
			
		} catch (Exception e) {
			try {
				throw new WrongDataException(this);
			} catch (WrongDataException ignored) {
				
			}
		}
	}
}