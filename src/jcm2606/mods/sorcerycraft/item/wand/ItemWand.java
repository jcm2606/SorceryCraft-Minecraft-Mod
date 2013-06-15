package jcm2606.mods.sorcerycraft.item.wand;

import jcm2606.mods.sorcerycraft.item.SCItem;

public class ItemWand extends SCItem {
	public ItemWand(int par1, String par2) {
		super(par1, par2);
		this.setFull3D();
		this.setMaxStackSize(1);
		this.setNoRepair();
	}
	
    @Override
    public boolean shouldRotateAroundWhenRendering()
    {
    	return true;
    }
}
