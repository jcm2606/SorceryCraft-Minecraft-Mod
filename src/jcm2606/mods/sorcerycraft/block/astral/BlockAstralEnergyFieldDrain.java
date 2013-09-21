package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.api.IEnergyInfused;
import jcm2606.mods.sorcerycraft.block.SCBlock;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.core.lib.RenderID;
import net.minecraft.block.material.Material;

public class BlockAstralEnergyFieldDrain extends SCBlock implements IEnergyInfused
{
    public BlockAstralEnergyFieldDrain(int par1)
    {
        super(par1, Material.rock, "astralEnergyFieldDrain", Rarities.BASIC);
        this.setHardness(3.0f);
        this.setResistance(5.0f);
        this.renderAsNormalBlock = false;
        this.renderID = RenderID.renderIDAstralEnergyFieldDrain;
    }

    @Override
    public boolean destroyBlockWhenExtracted()
    {
        return false;
    }

    @Override
    public int getCharge()
    {
        return 1;
    }

    @Override
    public int getDestroyChance()
    {
        return 0;
    }
}
