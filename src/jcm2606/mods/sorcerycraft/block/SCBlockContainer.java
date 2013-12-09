package jcm2606.mods.sorcerycraft.block;

import jcm2606.mods.jccore.block.tile.TileEntityJC;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class SCBlockContainer extends SCBlock implements ITileEntityProvider
{
    public SCBlockContainer(int par1, Material par2Material, String name)
    {
        super(par1, par2Material, name);
        this.isBlockContainer = true;
    }
    
    public SCBlockContainer(int par1, Material par2Material, String name, String par4)
    {
        super(par1, par2Material, name, par4);
        this.isBlockContainer = true;
    }
    
    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
    }
    
    /**
     * ejects contained items into the world, and notifies neighbours of an
     * update, as appropriate
     */
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
        par1World.removeBlockTileEntity(par2, par3, par4);
    }
    
    /**
     * Called when the block receives a BlockEvent - see World.addBlockEvent. By
     * default, passes it on to the tile entity at this location. Args: world,
     * x, y, z, blockID, EventID, event parameter
     */
    @Override
    public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
        TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
        return tileentity != null ? tileentity.receiveClientEvent(par5, par6) : false;
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourBlock)
    {
        if (world.getBlockTileEntity(x, y, z) != null)
        {
            if (world.getBlockTileEntity(x, y, z) instanceof TileEntityJC)
            {
                ((TileEntityJC) world.getBlockTileEntity(x, y, z)).onNeighborBlockChange(world, x, y, z, neighbourBlock);
            }
        }
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9)
    {
        if (world.getBlockTileEntity(x, y, z) != null)
        {
            if (world.getBlockTileEntity(x, y, z) instanceof TileEntityJC)
            {
                return ((TileEntityJC) world.getBlockTileEntity(x, y, z)).onBlockActivated(world, x, y, z, player, side, par7, par8, par9);
            }
        }
        
        return false;
    }
    
    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity)
    {
        if (world.getBlockTileEntity(x, y, z) != null)
        {
            if (world.getBlockTileEntity(x, y, z) instanceof TileEntityJC)
            {
                ((TileEntityJC) world.getBlockTileEntity(x, y, z)).onEntityWalking(world, x, y, z, entity);
            }
        }
    }
    
    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
    {
        if (world.getBlockTileEntity(x, y, z) != null)
        {
            if (world.getBlockTileEntity(x, y, z) instanceof TileEntityJC)
            {
                ((TileEntityJC) world.getBlockTileEntity(x, y, z)).onBlockClicked(world, x, y, z, player);
            }
        }
    }
    
    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side)
    {
        if (world.getBlockTileEntity(x, y, z) != null)
        {
            if (world.getBlockTileEntity(x, y, z) instanceof TileEntityJC)
            {
                return ((TileEntityJC) world.getBlockTileEntity(x, y, z)).isProvidingWeakPower(world, x, y, z, side);
            }
        }
        
        return 0;
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (world.getBlockTileEntity(x, y, z) != null)
        {
            if (world.getBlockTileEntity(x, y, z) instanceof TileEntityJC)
            {
                ((TileEntityJC) world.getBlockTileEntity(x, y, z)).onEntityCollidedWithBlock(world, x, y, z, entity);
            }
        }
    }
    
    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
    {
        if (world.getBlockTileEntity(x, y, z) instanceof TileEntityJC)
        {
            ((TileEntityJC) world.getBlockTileEntity(x, y, z)).onBlockDestroyedByPlayer(world, x, y, z, meta);
            ;
        }
    }
}
