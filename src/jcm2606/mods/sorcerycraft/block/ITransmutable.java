package jcm2606.mods.sorcerycraft.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ITransmutable {
	public Block getRequiredBlock(ItemStack stack);
	public int getTransmuteCost(ItemStack stack, Block block);
	public void onTransmute(ItemStack stack, Block block, EntityPlayer player, World world, int x, int y, int z);
}
