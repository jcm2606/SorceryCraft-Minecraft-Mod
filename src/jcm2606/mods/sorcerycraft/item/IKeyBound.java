package jcm2606.mods.sorcerycraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IKeyBound {

    public abstract void doKeyBindingAction(EntityPlayer player, ItemStack stack, String keyBinding);

}
