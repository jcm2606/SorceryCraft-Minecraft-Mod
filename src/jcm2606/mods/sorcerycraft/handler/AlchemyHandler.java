package jcm2606.mods.sorcerycraft.handler;

import java.util.ArrayList;
import java.util.Random;

import jcm2606.mods.sorcerycraft.block.ITransmutable;
import jcm2606.mods.sorcerycraft.compat.CompatContainerSC;
import jcm2606.mods.sorcerycraft.compat.HandlerMethodID;
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
	public static boolean performInWorldTransmutation(ItemStack stack, EntityPlayer player, World world, int x, int y, int z) {
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
		
		if(block != null)
		{
		    if(block instanceof ITransmutable)
	        {
	            i = (ITransmutable) block;
	            
	            if(i.getRequiredBlock(stack).blockID == world.getBlockId(x, y, z))
	            {
	                if(stack.getItemDamage() == stack.getMaxDamage())
	                {
	                    player.inventory.consumeInventoryItem(stack.itemID);
	                }
	                
	                world.setBlock(x, y, z, block.blockID, 0, 0x02);
	                i.onTransmute(stack, block, player, world, x, y, z);
	                CompatContainerSC.postUpdateToSubContainers(HandlerMethodID.ALCH_STONE_TRANSMUTE, null);
	                
	                world.playSoundAtEntity(player, "sorcerycraft.transmutation", 0.3f + (world.rand.nextFloat() / 4), 1.0f + (world.rand.nextFloat() / 8));
	                
	                stack.damageItem(i.getTransmuteCost(stack, block), player);
	                
	                Random rand = new Random();
	                
	                world.spawnParticle("largeexplode", x + 0.5,
	                        y + 0.5,
	                        z + 0.5, 0, 0, 0);
	                
	                return true;
	            }
	        }
		}
		
		world.playSoundAtEntity(player, "sorcerycraft.magic_fail", 0.2f, 1.0f);
		CompatContainerSC.postUpdateToSubContainers(HandlerMethodID.ALCH_STONE_TRANSMUTE_FAIL, null);
		
		return false;
	}
}
