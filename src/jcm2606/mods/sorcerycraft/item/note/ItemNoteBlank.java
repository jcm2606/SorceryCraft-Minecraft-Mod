package jcm2606.mods.sorcerycraft.item.note;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;


public class ItemNoteBlank extends ItemNote {
	public ItemNoteBlank(int par1) {
		super(par1, "noteBlank");
		this.isBlank = true;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return EnumRarity.common;
	}
}
