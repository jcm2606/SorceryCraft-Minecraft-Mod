package jcm2606.mods.sorcerycraft.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IWand
{
    public void onWandUse(ItemStack stack, EntityPlayer player);
}
