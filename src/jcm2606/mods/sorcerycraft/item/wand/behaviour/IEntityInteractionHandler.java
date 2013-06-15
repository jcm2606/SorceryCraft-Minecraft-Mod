package jcm2606.mods.sorcerycraft.item.wand.behaviour;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IEntityInteractionHandler {
	/**
	 * Handler method for actual interaction.
	 * 
	 * @param wand
	 * @param stack
	 * @param player
	 * @param world
	 * @return
	 */
	public void onEntityInteract(ItemStack stack, World world, EntityLiving living);
	
	/**
	 * Get the required {@link Entity} for this handler. Return null for any.
	 * 
	 * @param stack
	 * @param world
	 * @return
	 */
	public Entity getEntity(ItemStack stack, World world);
}
