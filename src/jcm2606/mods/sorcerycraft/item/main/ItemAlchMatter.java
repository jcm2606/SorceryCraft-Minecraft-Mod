package jcm2606.mods.sorcerycraft.item.main;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.item.SCItemShine;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAlchMatter extends SCItemShine {
	public ItemAlchMatter(int par1) {
		super(par1, "matterAlch");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return RarityHelper.getCustomRarityType(Rarities.BASIC);
	}
}
