package jcm2606.mods.sorcerycraft.item.main;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.item.SCItem;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemProtectionStone extends SCItem {
	public ItemProtectionStone(int par1) {
		super(par1, "aegisStone");
		this.setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack)
	{
		return RarityHelper.getCustomRarityType(Rarities.MAGICAL);
	}
}
