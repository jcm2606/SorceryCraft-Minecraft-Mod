package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.SCBlockMultiblock;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralKineticGenerator;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralKineticGenerator extends SCBlockMultiblock
{
    public BlockAstralKineticGenerator(int par1)
    {
        super(par1, Material.iron, "astralKineticGenerator", Rarities.ADVANCED);
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAstralKineticGenerator();
    }
}
