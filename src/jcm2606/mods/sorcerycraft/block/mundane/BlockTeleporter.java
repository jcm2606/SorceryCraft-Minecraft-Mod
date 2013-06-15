package jcm2606.mods.sorcerycraft.block.mundane;

import java.util.Random;

import jcm2606.mods.sorcerycraft.SorceryCraft;
import jcm2606.mods.sorcerycraft.lib.Reference;
import jcm2606.mods.sorcerycraft.tile.TileEntityTeleporter;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTeleporter extends BlockContainer {
	
	private static int teleX;
	private static int teleY;
	private static int teleZ;

	public BlockTeleporter(int par1, Material par3Material) {
		super(par1, par3Material);
		this.setCreativeTab(SorceryCraft.tab);
	}

	/**
     * Ticks the block if it's been scheduled
     */
    @Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
    	
    }
    
    /**
	 * Called whenever an entity is walking on top of this block. Args: world,
	 * x, y, z, entity
	 */
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
/*		if(entity instanceof EntityLiving)
		{
			EntityLiving living = (EntityLiving) entity;
			if(entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) living;
				
				TileEntity tile = world.getBlockTileEntity(x, y, z);
		    	
		    	if(tile instanceof TileEntityTeleporter)
		    	{
		    		TileEntityTeleporter teleTile = (TileEntityTeleporter) tile;
		    		
		    		player.setPositionAndUpdate(teleTile.getXCoord(), teleTile.getYCoord(), teleTile.getZCoord());
		    	}
			}
		}*/
	}
	
	/**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    @Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
/*    	TileEntity tile = par1World.getBlockTileEntity(par2, par3, par4);
    	
    	if(tile instanceof TileEntityTeleporter)
    	{
    		TileEntityTeleporter teleTile = (TileEntityTeleporter) tile;
    		
    		teleTile.setCoords(par2, par3, par4);
    		
    	}*/
    	
        return par9;
    }

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityTeleporter();
	}
}
