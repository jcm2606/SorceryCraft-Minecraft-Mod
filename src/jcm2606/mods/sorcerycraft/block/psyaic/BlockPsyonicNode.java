package jcm2606.mods.sorcerycraft.block.psyaic;

import jcm2606.mods.sorcerycraft.block.SCBlockContainer;
import jcm2606.mods.sorcerycraft.block.tile.psyaic.TileAstralEnergyNode;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPsyonicNode extends SCBlockContainer
{
    public BlockPsyonicNode(int par1)
    {
        super(par1, Material.iron, "astralEnergyNode", Rarities.BASIC);
        this.isOpaqueCube = false;
        this.renderAsNormalBlock = false;
        this.renderID = -1;
        this.useIconIndex = false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAstralEnergyNode();
    }
}
