package jcm2606.mods.sorcerycraft.api;

import jcm2606.mods.sorcerycraft.item.astral.ItemAstralLinkingCard;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ILinkable
{
    /**
     * Called when an attempt has been made to apply an
     * {@link ItemAstralLinkingCard} to this block
     * 
     * @param stack
     * @param player
     * @param world
     * @param x
     * @param y
     * @param z
     * @param sideClicked
     */
    public void applyCard(ItemAstralLinkingCard card, ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int sideClicked);
}
