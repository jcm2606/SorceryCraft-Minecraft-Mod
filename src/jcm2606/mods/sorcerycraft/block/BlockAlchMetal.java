package jcm2606.mods.sorcerycraft.block;

import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.block.material.Material;

public class BlockAlchMetal extends SCBlock {
	public BlockAlchMetal(int par1) {
		super(par1, Material.iron, "alchMetalBlock", Rarities.BASIC);
		this.setHardness(7.0f);
		this.setResistance(4.0f);
	}
}
