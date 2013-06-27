package jcm2606.mods.sorcerycraft.item.wand;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WandManager {
	public static boolean performCastingWandBlockInteractionEffect(ItemStack stack, World world, EntityPlayer player, int x, int y, int z)
	{
		return false;
	}
	
	public static boolean performCastingWandEntityInteractionEffect(ItemStack stack, World world, EntityLiving entity)
	{
		return false;
	}
}
