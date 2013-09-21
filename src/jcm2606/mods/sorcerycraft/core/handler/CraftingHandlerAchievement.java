package jcm2606.mods.sorcerycraft.core.handler;

import jcm2606.mods.jccore.core.util.CraftingHandlerBase;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingHandlerAchievement extends CraftingHandlerBase
{
    @Override
    public void onCraft(ItemStack stack, Object result, EntityPlayer player)
    {
    }
    
    @Override
    public void onCraftingMatrixChange(EntityPlayer player, ItemStack stack, Item item, ItemStack resultStack, int slot, IInventory iinv)
    {
    }
    
    @Override
    public void onSmelt(Object result, EntityPlayer player)
    {
        if (result == SCObjects.dustVordicStabilised)
        {
            // player.addStat(SCAchievements.refVordicDustGet, 1);
        }
    }
}
