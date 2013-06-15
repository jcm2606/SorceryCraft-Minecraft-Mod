package jcm2606.mods.sorcerycraft.item.wand.behaviour;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IBlockInteractionHandler {
	/**
	 * Get the required {@link Block} for this handler. Return null for any.
	 * 
	 * @param stack
	 * @param player
	 * @return
	 */
	public Block getBlock(ItemStack stack, EntityPlayer player);
	
	/**
	 * Handler method for actual interaction.
	 * 
	 * @param wand
	 * @param stack
	 * @param player
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public boolean onBlockInteract(ItemStack wand, ItemStack stack, EntityPlayer player, World world, int x, int y, int z);
	
	/**
	 * Required item for this interaction. Return null for any.
	 * 
	 * @param wand
	 * @return
	 */
	public Item reqItem(ItemStack wand);
}
