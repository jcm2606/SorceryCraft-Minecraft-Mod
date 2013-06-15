package jcm2606.mods.sorcerycraft.item.main;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.item.SCItem;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemExoticBlend extends SCItem {
	public ItemExoticBlend(int par1) {
		super(par1, "exoticBlend");
	}
	
	public EnumRarity getRarity(ItemStack stack)
	{
		return RarityHelper.getCustomRarityType(Rarities.EXOTIC);
	}
}
