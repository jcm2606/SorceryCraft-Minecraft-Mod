package jcm2606.mods.sorcerycraft.item.main;

import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGlassFlask extends SCItem {
	public ItemGlassFlask(int par1) {
		super(par1, "flaskGlass");
		this.setMaxStackSize(16);
	}
	
	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		if(par7 == 5)
		{
			int id = world.getBlockId(x + 1, y, z);
			
			if(id == Block.waterStill.blockID || id == Block.waterMoving.blockID)
			{
				world.setBlock(x + 1, y, z, 0, 0, 0x02);
				
				stack.stackSize--;
				player.inventory.addItemStackToInventory(new ItemStack(SCObjects.waterflask, 1));
			}
		}
		
		if(par7 == 2)
		{
			int id = world.getBlockId(x, y, z - 1);
			
			if(id == Block.waterStill.blockID || id == Block.waterMoving.blockID)
			{
				world.setBlock(x, y, z - 1, 0, 0, 0x02);
				
				stack.stackSize--;
				player.inventory.addItemStackToInventory(new ItemStack(SCObjects.waterflask, 1));
			}
		}
		
		if(par7 == 4)
		{
			int id = world.getBlockId(x - 1, y, z);
			
			if(id == Block.waterStill.blockID || id == Block.waterMoving.blockID)
			{
				world.setBlock(x - 1, y, z, 0, 0, 0x02);
				
				stack.stackSize--;
				player.inventory.addItemStackToInventory(new ItemStack(SCObjects.waterflask, 1));
			}
		}
		
		if(par7 == 3)
		{
			int id = world.getBlockId(x, y, z + 1);
			
			if(id == Block.waterStill.blockID || id == Block.waterMoving.blockID)
			{
				world.setBlock(x, y, z + 1, 0, 0, 0x02);
				
				stack.stackSize--;
				player.inventory.addItemStackToInventory(new ItemStack(SCObjects.waterflask, 1));
			}
		}
		
		return true;
	}
}
