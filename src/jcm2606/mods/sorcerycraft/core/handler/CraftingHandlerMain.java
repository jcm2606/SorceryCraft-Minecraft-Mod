package jcm2606.mods.sorcerycraft.core.handler;

import jcm2606.mods.jccore.core.util.CraftingHandlerBase;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingHandlerMain extends CraftingHandlerBase
{
    @Override
    public void onCraft(ItemStack stack, Object result, EntityPlayer player)
    {
    }
    
    @Override
    public void onCraftingMatrixChange(EntityPlayer player, ItemStack stack, Item item, ItemStack resultStack, int slot, IInventory iinv)
    {
        if (item == SCObjects.tabletCrafting)
        {
            keepItemInMatrix(SCObjects.tabletCrafting, slot, iinv);
        }
        
        if (item == SCObjects.toolVordic)
        {
            if (stack.getItemDamage() >= stack.getMaxDamage())
            {
                return;
            }
            
            keepItemInMatrixAndDamage(stack, slot, iinv);
        }
        
        if (item == SCObjects.ringMagma)
        {
            if (stack.getItemDamage() >= stack.getMaxDamage())
            {
                return;
            }
            
            if (stack.itemID == Item.gunpowder.itemID || stack.itemID == Item.redstone.itemID || stack.itemID == SCObjects.dustSear.itemID)
            {
                keepItemInMatrixAndDamage(stack, slot, iinv);
            }
        }
    }
    
    @Override
    public void onSmelt(Object result, EntityPlayer player)
    {
    }
}
