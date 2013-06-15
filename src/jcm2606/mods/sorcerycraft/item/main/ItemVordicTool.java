package jcm2606.mods.sorcerycraft.item.main;

import jcm2606.mods.sorcerycraft.block.IWorkable;
import jcm2606.mods.sorcerycraft.handler.ToolHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemVordicTool extends ItemVordicDevice {
	public ItemVordicTool(int par1) {
		super(par1, "toolVordic");
		this.setMaxDamage(128);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		int blockIdRequired = -1;
		
		for(int i = 0; i < ToolHandler.blocksList.size(); i++)
		{
			Block entry = (Block) ToolHandler.blocksList.get(i);
			IWorkable workableEntry = (IWorkable) entry;
			
			if(world.getBlockId(x, y, z) == workableEntry.getBlockForWorking(stack).blockID)
			{
				blockIdRequired = workableEntry.getBlockForWorking(stack).blockID;
			}
		}
		
		if(blockIdRequired == -1)
		{
			return false;
		}
		
		ToolHandler.performBlockWorking(stack, player, world, x, y, z);
		return true;
	}
}
