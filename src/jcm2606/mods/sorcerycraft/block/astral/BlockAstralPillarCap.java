package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.SCBlockContainer;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralPillarCap;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralPillarCap extends SCBlockContainer
{
    public BlockAstralPillarCap(int par1)
    {
        super(par1, Material.rock, "psyaicTotemTop");
        this.renderAsNormalBlock = false;
        this.renderID = -1;
        this.isOpaqueCube = false;
        this.useIconIndex = false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAstralPillarCap();
    }
}
