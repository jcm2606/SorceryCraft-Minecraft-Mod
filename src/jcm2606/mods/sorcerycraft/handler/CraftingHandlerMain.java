package jcm2606.mods.sorcerycraft.handler;

import jcm2606.mods.jccore.util.CraftingHandlerBase;
import jcm2606.mods.sorcerycraft.SCObjects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingHandlerMain extends CraftingHandlerBase {
    @Override
    public void onCraft(ItemStack stack, Object result, EntityPlayer player)
    {}

    @Override
    public void onCraftingMatrixChange(EntityPlayer player, ItemStack stack, Item item, ItemStack resultStack, int slot, IInventory iinv)
    {
        if(item == SCObjects.creationtablet)
        {
            keepItemInMatrix(SCObjects.creationtablet, slot, iinv);
        }
        
        if(item == SCObjects.vordictool)
        {
            if(stack.getItemDamage() >= stack.getMaxDamage())
            {
                return;
            }
            
            keepItemInMatrixAndDamage(stack, slot, iinv);
        }
        
        if(item == SCObjects.ringmagma)
        {
            if(stack.getItemDamage() >= stack.getMaxDamage())
            {
                return;
            }
            
            if(stack.itemID == Item.gunpowder.itemID || stack.itemID == Item.redstone.itemID || stack.itemID == SCObjects.firepowder.itemID)
            {
                keepItemInMatrixAndDamage(stack, slot, iinv);
            }
        }
    }

    @Override
    public void onSmelt(Object result, EntityPlayer player)
    {}
}
