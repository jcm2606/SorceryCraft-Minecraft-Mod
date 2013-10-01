package jcm2606.mods.sorcerycraft.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IItemUseTickHandler
{
    public void serverItemUseTick(ItemStack stack, EntityPlayer player, int count);
}
