package jcm2606.mods.sorcerycraft.block;

import net.minecraft.block.material.Material;

public class BlockDarkQuartzBrick extends SCBlock {
    public BlockDarkQuartzBrick(int par1) {
        super(par1, Material.rock, "darkQuartzBrick");
        this.setHardness(3.0f);
        this.setResistance(6.0f);
    }
}
