package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.SCBlock;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.block.material.Material;

public class BlockAstralSteel extends SCBlock {
    public BlockAstralSteel(int par1) {
        super(par1, Material.iron, "astralSteelBlock", Rarities.BASIC);
        this.setHardness(4.0f);
        this.setResistance(32.0f);
    }
}
