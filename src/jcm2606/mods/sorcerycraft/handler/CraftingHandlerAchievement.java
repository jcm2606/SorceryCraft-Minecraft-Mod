package jcm2606.mods.sorcerycraft.handler;

import jcm2606.mods.jccore.CraftingHandlerBase;
import jcm2606.mods.sorcerycraft.SCObjects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingHandlerAchievement extends CraftingHandlerBase {
    @Override
    public void onCraft(ItemStack stack, Object result, EntityPlayer player)
    {}

    @Override
    public void onCraftingMatrixChange(EntityPlayer player, ItemStack stack, Item item, ItemStack resultStack, int slot, IInventory iinv)
    {}

    @Override
    public void onSmelt(Object result, EntityPlayer player)
    {
        if(result == SCObjects.dustvordicrefined)
        {
//            player.addStat(SCAchievements.refVordicDustGet, 1);
        }
    }
}
