package jcm2606.mods.sorcerycraft.block.main;

import jcm2606.mods.sorcerycraft.block.SCBlock;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;

public class BlockStoneResistant extends SCBlock {
	public BlockStoneResistant(int par1) {
		super(par1, Material.rock, "stoneResistant", Rarities.BASIC);
		setHardness(5f);
		setResistance(2000.0F);
	}
}
