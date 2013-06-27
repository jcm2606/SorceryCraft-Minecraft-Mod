package jcm2606.mods.sorcerycraft.block;

import net.minecraft.block.material.Material;

public class BlockDarkQuartz extends SCBlock {
    public BlockDarkQuartz(int par1) {
        super(par1, Material.rock, "darkQuartz");
        this.setHardness(2.75f);
        this.setResistance(6.0f);
    }
}
