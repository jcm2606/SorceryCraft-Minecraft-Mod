package jcm2606.mods.sorcerycraft.block.psyonic;

import jcm2606.mods.sorcerycraft.block.SCBlockContainer;
import jcm2606.mods.sorcerycraft.block.tile.psyonic.TilePsyonicConduit;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPsyonicConduit extends SCBlockContainer
{
    public BlockPsyonicConduit(int par1)
    {
        super(par1, Material.rock, "psyonicConduit", Rarities.BASIC);
        this.isOpaqueCube = false;
        this.renderAsNormalBlock = false;
        this.renderID = -1;
        this.useIconIndex = false;
        this.setBlockBounds(0.25f, 0.25f, 0.25f, 0.75f, 0.75f, 0.75f);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TilePsyonicConduit();
    }
}
