package jcm2606.mods.sorcerycraft.block.mundane;

import jcm2606.mods.sorcerycraft.block.SCBlock;
import net.minecraft.block.material.Material;

public class BlockMechanismBase extends SCBlock {
	public BlockMechanismBase(int par1, Material par3Material) {
		super(par1, par3Material, "mundaneMechanismBase");
		this.setHardness(2.5f);
		this.setResistance(0.1f);
	}
}
