package jcm2606.mods.sorcerycraft.handler;

import java.util.ArrayList;
import java.util.Random;

import jcm2606.mods.sorcerycraft.block.ITransmutable;
import jcm2606.mods.sorcerycraft.config.Settings;
import jcm2606.mods.sorcerycraft.item.wand.ItemWandCasting;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Class that handles the various alchemical mechanics within SorceryCraft
 * 
 * @author jcm2606
 */
public class AlchemyHandler {
	public static ArrayList transBlocks = new ArrayList();
	
	public static void loadTransmutableBlocksEntries()
	{
		for(Block block : Block.blocksList)
		{
			if(block instanceof ITransmutable)
			{
				transBlocks.add(block);
			}
		}
	}
	
	/**
	 * Tries to perform an in-world transmutation at the given coordinates.
	 * 
	 * @param stack The item performing the transmutation
	 * @param player The player performing the transmutation
	 * @param world The world the transmutation is being performed in
	 * @param block The block the transmutaton will be creating
	 * @param x The X coord the transmutation is being performed at
	 * @param y The Y coord the transmutation is being performed at
	 * @param z The Z coord the transmutation is being performed at
	 */
	public static boolean performInWorldTransmutation(ItemStack stack, ItemStack stone, EntityPlayer player, World world, int x, int y, int z) {
		ITransmutable i = null;
		Block block = null;
		
		for(int j = 0; j < transBlocks.size(); j++)
		{
			Block var1 = (Block) transBlocks.get(j);
			ITransmutable var2 = null;
			
			if(var1 instanceof ITransmutable)
			{
				 var2 = (ITransmutable) var1;
			}
			else
			{
				// TODO: Create some sort of fail mechanic. Probably configurable...
			}
			
			if(world.getBlockId(x, y, z) == var2.getRequiredBlock(stack).blockID)
			{
				block = var1;
			}
		}
		
		if(block instanceof ITransmutable)
		{
			i = (ITransmutable) block;
			
			if (stone != null) {
				if(i.getRequiredBlock(stone).blockID == world.getBlockId(x, y, z))
				{
					if(stone.getItemDamage() == stone.getMaxDamage())
					{
						player.inventory.consumeInventoryItem(stone.itemID);
					}
					
					world.setBlock(x, y, z, block.blockID, 0, 0x02);
					i.onTransmute(stone, block, player, world, x, y, z);
					
					if(!(stack.getItem() instanceof ItemWandCasting))
					{
						world.playSoundAtEntity(player, "sorcerycraft.transmutation", 0.3f + (world.rand.nextFloat() / 4), 1.0f + (world.rand.nextFloat() / 8));
					}
					
					stone.damageItem(i.getTransmuteCost(stone, block), player);
					
					Random rand = new Random();
					
					world.spawnParticle("largeexplode", x + 0.5,
							y + 0.5,
							z + 0.5, 0, 0, 0);
					
					return true;
				}
			}
		}
		
		if(!(stack.getItem() instanceof ItemWandCasting))
		{
			world.playSoundAtEntity(player, "sorcerycraft.magic_fail", 0.2f, 1.0f);
		}
		
		return false;
	}
}
