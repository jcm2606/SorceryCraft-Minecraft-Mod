package jcm2606.mods.sorcerycraft.item;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SCItemShine extends SCItem {

	public SCItemShine(int par1, String par2) {
		super(par1, par2);
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}
}
