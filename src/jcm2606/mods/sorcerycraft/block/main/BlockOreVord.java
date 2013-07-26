package jcm2606.mods.sorcerycraft.block.main;

import java.util.Random;

import jcm2606.mods.sorcerycraft.api.ITransmutable;
import jcm2606.mods.sorcerycraft.block.SCOre;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.lib.RenderID;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOreVord extends SCOre implements ITransmutable {
    public Icon textureGlow;
    
	public BlockOreVord(int par1) {
		super(par1, "oreVord");
		this.setResistance(5.0F);
		this.setHardness(2.2f);
		this.renderAsNormalBlock = false;
		this.renderID = RenderID.renderIDVordicOre;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("SorceryCraft:" + name);
        this.textureGlow = par1IconRegister.registerIcon("SorceryCraft:oreVordGlow");
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
