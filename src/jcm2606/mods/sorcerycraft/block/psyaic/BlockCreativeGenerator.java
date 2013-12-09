package jcm2606.mods.sorcerycraft.block.psyaic;

import jcm2606.mods.sorcerycraft.block.SCBlockContainer;
import jcm2606.mods.sorcerycraft.block.tile.psyaic.TileCreativeGenerator;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCreativeGenerator extends SCBlockContainer
{
    public BlockCreativeGenerator(int par1)
    {
        super(par1, Material.iron, "creativeGenerator");
        this.setHardness(0.5f);
        this.setResistance(0.5f);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileCreativeGenerator();
    }
}
