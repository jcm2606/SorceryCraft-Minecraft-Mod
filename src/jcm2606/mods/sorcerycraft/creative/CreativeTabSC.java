package jcm2606.mods.sorcerycraft.creative;

import jcm2606.mods.sorcerycraft.SCObjects;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabSC extends CreativeTabs {
	public CreativeTabSC(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return "SorceryCraft";
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * the itemID for the item to be displayed on the tab
	 */
	public int getTabIconItemIndex() {
		return SCObjects.alchbook.itemID;
	}
}
