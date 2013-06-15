package jcm2606.mods.sorcerycraft.item.astral;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.item.SCItemShine;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemAstralCrystal extends SCItemShine {
	public ItemAstralCrystal(int par1) {
		super(par1, "astralCrystal");
	}
	
	public EnumRarity getRarity(ItemStack stack)
	{
		return RarityHelper.getCustomRarityType(Rarities.BASIC);
	}
}
