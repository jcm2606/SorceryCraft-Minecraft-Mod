package jcm2606.mods.sorcerycraft.block;

import java.util.Random;

import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.SCParticle;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOreVord extends SCOre implements ITransmutable {
	public BlockOreVord(int par1) {
		super(par1, "oreVord");
		this.setHardness(2.2f);
	}
	
	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return SCObjects.dustvordic.itemID;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random par1Random) {
		return 1 + par1Random.nextInt(2);
	}
	
	/**
	 * Called when the block is attempted to be harvested
	 */
	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int par5, EntityPlayer player) {
		
	}
	
	@Override
    @SideOnly(Side.CLIENT)
	  public boolean addBlockHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
	  {
	    for(int i = 0; i < 1 + worldObj.rand.nextInt(3); i++)
        {
            Random rand = new Random();
        
            SCParticle.spawnVordicEnergyFX(target.blockX + 0.5 + (rand.nextGaussian() / 3), target.blockY + 0.5 + (rand.nextGaussian() / 3), target.blockZ + 0.5 + (rand.nextGaussian() / 3), 0, -0.05, 0, false);
        }
	    return super.addBlockHitEffects(worldObj, target, effectRenderer);
	  }
	
	/**
	 * Return true from this function if the player with silk touch can harvest
	 * this block directly, and not it's normal drops.
	 * 
	 * @param world
	 *            The world
	 * @param player
	 *            The player doing the harvesting
	 * @param x
	 *            X Position
	 * @param y
	 *            Y Position
	 * @param z
	 *            Z Position
	 * @param metadata
	 *            The metadata
	 * @return True if the block can be directly harvested using silk touch
	 */
	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		return true;
	}
	
	@Override
	public Block getRequiredBlock(ItemStack stack) {
		return Block.stone;
	}

	@Override
	public int getTransmuteCost(ItemStack stack, Block block) {
		return 1;
	}

	@Override
	public void onTransmute(ItemStack stack, Block block, EntityPlayer player,
			World world, int x, int y, int z) {
		double randomChance = Math.random();
		
		if(randomChance < 0.32 && !world.isRemote)
		{
			EntityItem i = new EntityItem(world, x, y, z, new ItemStack(SCObjects.vordicgemblock, world.rand.nextInt(3)));
			
			i.motionX = 0.0;
			i.motionZ = 0.0;
			
			world.spawnEntityInWorld(i);
		}
	}
}
