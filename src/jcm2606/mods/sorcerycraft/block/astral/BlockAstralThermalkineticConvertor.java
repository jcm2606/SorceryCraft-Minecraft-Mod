package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.SCBlockContainer;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralThermalkineticConvertor;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralThermalkineticConvertor extends SCBlockContainer
{
    public BlockAstralThermalkineticConvertor(int par1)
    {
        super(par1, Material.iron, "astralThermalkineticConvertor", Rarities.ADVANCED);
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAstralThermalkineticConvertor();
    }
}
