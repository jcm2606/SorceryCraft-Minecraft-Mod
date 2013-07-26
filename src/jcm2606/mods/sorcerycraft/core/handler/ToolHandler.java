package jcm2606.mods.sorcerycraft.core.handler;

import java.util.ArrayList;

import jcm2606.mods.sorcerycraft.api.IWorkable;
import jcm2606.mods.sorcerycraft.api.compat.CompatContainerSC;
import jcm2606.mods.sorcerycraft.api.compat.HandlerMethodID;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ToolHandler {
public static ArrayList blocksList = new ArrayList();
	
	public static void loadWorkableBlockEntries()
	{
		for(Block block : Block.blocksList)
		{
		    if(block instanceof IWorkable)
		    {
		        blocksList.add(block);
		    }
		}
	}
	
	/**
	 * Tries to perform an in-world block working at the given coordinates.
	 * 
	 * @param stack The item performing the block working
	 * @param player The player performing the block working
	 * @param world The world the block working is being performed in
	 * @param x The X coord the block working is being performed at
	 * @param y The X coord the block working is being performed at
	 * @param z The X coord the block working is being performed at
	 */
	public static void performBlockWorking(ItemStack stack, EntityPlayer player, World world, int x, int y, int z) {
		IWorkable i = null;
		Block block = null;
		
		for(int j = 0; j < blocksList.size(); j++)
		{
			Block var1 = (Block) blocksList.get(j);
			IWorkable var2 = null;
			
			if(var1 instanceof IWorkable)
			{
				 var2 = (IWorkable) var1;
			}
			else
			{
				
			}
			
			if(world.getBlockId(x, y, z) == var2.getBlockForWorking(stack).blockID && var1 instanceof Block)
			{
				block = var1;
			}
		}
		
		if(block instanceof IWorkable)
		{
			i = (IWorkable) block;
			
			if (stack != null)
			{
				if(i.getBlockForWorking(stack).blockID == world.getBlockId(x, y, z))
				{
					if(stack.getItemDamage() <= stack.getMaxDamage() - 16)
					{
						world.setBlock(x, y, z, block.blockID, 0, 0x02);
						i.onWorking(stack, block, player, world, x, y, z);
					
						CompatContainerSC.postUpdateToSubContainers(HandlerMethodID.VORDIC_WORKING_TOOL_USE, null);
						
//					    world.playSoundAtEntity(player, "sorcerycraft.transmutation", 1.0f, 1.0f);
						
						stack.damageItem(i.getWorkCost(stack, block), player);
						return;
					}
				}
			}
		}
		
//		world.playSoundAtEntity(player, "sorcerycraft.magic_fail", 1.0f, 1.0f);
	}
}
