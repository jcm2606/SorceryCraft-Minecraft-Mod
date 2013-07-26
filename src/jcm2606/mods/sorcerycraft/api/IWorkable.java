package jcm2606.mods.sorcerycraft.api;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IWorkable {
	public Block getBlockForWorking(ItemStack stack);
	public int getWorkCost(ItemStack stack, Block block);
	public void onWorking(ItemStack stack, Block block, EntityPlayer player, World world, int x, int y, int z);
}
