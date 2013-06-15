package jcm2606.mods.sorcerycraft.item.main;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.item.SCItemShine;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemFirePowder extends SCItemShine {
	public ItemFirePowder(int par1) {
		super(par1, "dustFire");
		this.setMaxStackSize(16);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return RarityHelper.getCustomRarityType(Rarities.BASIC);
	}
}
