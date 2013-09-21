package jcm2606.mods.sorcerycraft.api;

import jcm2606.mods.jccore.core.util.Coord;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralLinkingCard;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ILinkCardCoord
{
    /**
     * Called when this blocks coords are saved to the card. Return null to use
     * default code.
     * 
     * @param card
     * @param stack
     * @param player
     * @param world
     * @param x
     * @param y
     * @param z
     */
    public Coord getCoords(ItemAstralLinkingCard card, ItemStack stack, EntityPlayer player, World world, int x, int y, int z);
}
