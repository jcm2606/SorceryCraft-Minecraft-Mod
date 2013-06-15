package jcm2606.mods.sorcerycraft.block;

import java.util.Random;

import jcm2606.mods.sorcerycraft.tile.TileEntitySkull;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSkull extends SCBlockContainer {
	
	public BlockSkull(int par1) {
		super(par1, Material.rock, "skull");
		this.setTickRandomly(true);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySkull();
	}
	
	/**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving living, ItemStack stack) {
    	if(world.getBlockTileEntity(x, y, z) != null)
    	{
    		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
    		
    		if(tileentity instanceof TileEntitySkull)
    		{
    			TileEntitySkull teskull = (TileEntitySkull) tileentity;
    			
    			if(living instanceof EntityPlayer)
    			{
    				EntityPlayer player = (EntityPlayer) living;
    				
    				teskull.setOwner(player.username);
    			}
    		}
    	}
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        EntityPlayer player = world.getClosestPlayer(x, y, z, 7);
        
        world.playSoundEffect(x, y, z, "sorcerycraft.transmutation", 1.0f, 1.0f);
    }
}
