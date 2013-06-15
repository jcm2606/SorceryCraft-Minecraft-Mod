package jcm2606.mods.sorcerycraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerAlchBook extends Container {
	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}
}
