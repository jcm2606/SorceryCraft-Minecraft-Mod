package jcm2606.mods.sorcerycraft.astral;

import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.item.astral.ItemPsyonicEnergyCell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class AstralManager
{
    public static int getTotalEnergy(EntityPlayer player)
    {
        int currentEnergy = 0;
        
        for (int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack = player.inventory.mainInventory[i];
            
            if (stack != null)
            {
                if (stack.getItem() == SCObjects.psyonicCellEnergy)
                {
                    ItemPsyonicEnergyCell cell = (ItemPsyonicEnergyCell) stack.getItem();
                    
                    currentEnergy += cell.getEnergy(stack);
                }
            }
        }
        
        return currentEnergy;
    }
    
    public static int getChargedCellCount(EntityPlayer player)
    {
        int fullCellsInInv = 0;
        
        for (int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack = player.inventory.mainInventory[i];
            
            if (stack != null)
            {
                if (stack.getItem() == SCObjects.psyonicCellEnergy)
                {
                    ItemPsyonicEnergyCell cell = (ItemPsyonicEnergyCell) stack.getItem();
                    
                    if (cell.getEnergy(stack) > 0)
                    {
                        fullCellsInInv++;
                    }
                }
            }
        }
        
        return fullCellsInInv;
    }
    
    public static int getEmptyCellCount(EntityPlayer player)
    {
        int fullCellsInInv = 0;
        
        for (int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack = player.inventory.mainInventory[i];
            
            if (stack != null)
            {
                if (stack.getItem() == SCObjects.psyonicCellEnergy)
                {
                    ItemPsyonicEnergyCell cell = (ItemPsyonicEnergyCell) stack.getItem();
                    
                    if (cell.getEnergy(stack) == 0)
                    {
                        fullCellsInInv++;
                    }
                }
            }
        }
        
        return fullCellsInInv;
    }
    
    public static int getMaxChargedCellCount(EntityPlayer player)
    {
        int fullCellsInInv = 0;
        
        for (int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack = player.inventory.mainInventory[i];
            
            if (stack != null)
            {
                if (stack.getItem() == SCObjects.psyonicCellEnergy)
                {
                    ItemPsyonicEnergyCell cell = (ItemPsyonicEnergyCell) stack.getItem();
                    
                    if (cell.getEnergy(stack) >= 1000)
                    {
                        fullCellsInInv++;
                    }
                }
            }
        }
        
        return fullCellsInInv;
    }
    
    public static int getCellCount(EntityPlayer player)
    {
        int cellsInInv = 0;
        
        for (int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack = player.inventory.mainInventory[i];
            
            if (stack != null)
            {
                if (stack.getItem() == SCObjects.psyonicCellEnergy)
                {
                    ItemPsyonicEnergyCell cell = (ItemPsyonicEnergyCell) stack.getItem();
                    
                    cellsInInv++;
                }
            }
        }
        
        return cellsInInv;
    }
    
    public static int getMaxEnergy(EntityPlayer player)
    {
        int cells = getCellCount(player);
        
        int energy = 0;
        
        energy += cells * 1000;
        
        return energy;
    }
    
    public static int getEnergyPercentage(EntityPlayer player)
    {
        int energy = getTotalEnergy(player);
        int maxEnergy = getMaxEnergy(player);
        
        int i = (int) Math.round(energy * 100.0 / maxEnergy);
        return i;
    }
    
    public static void takeEnergyFrom(EntityPlayer player, int amount)
    {
        if (getChargedCellCount(player) > 0)
        {
            for (int i = 0; i < player.inventory.mainInventory.length; i++)
            {
                ItemStack stack = player.inventory.mainInventory[i];
                
                if (stack != null)
                {
                    if (stack.getItem() == SCObjects.psyonicCellEnergy)
                    {
                        ItemPsyonicEnergyCell cell = (ItemPsyonicEnergyCell) stack.getItem();
                        
                        if ((cell.getEnergy(stack)) >= amount)
                        {
                            cell.setEnergy(stack, cell.getEnergy(stack) - amount);
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public static void addEnergyTo(EntityPlayer player, int amount)
    {
        if (getMaxChargedCellCount(player) < getCellCount(player))
        {
            for (int i = 0; i < player.inventory.mainInventory.length; i++)
            {
                ItemStack stack = player.inventory.mainInventory[i];
                
                if (stack != null)
                {
                    if (stack.getItem() == SCObjects.psyonicCellEnergy)
                    {
                        ItemPsyonicEnergyCell cell = (ItemPsyonicEnergyCell) stack.getItem();
                        
                        if (cell.getEnergy(stack) < 1000 && cell.getEnergy(stack) + amount <= 1000)
                        {
                            cell.setEnergy(stack, cell.getEnergy(stack) + amount);
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public static void setEnergy(EntityPlayer player, int amount)
    {
        if(amount <= getMaxEnergy(player))
        {
            for (int i = 0; i < player.inventory.mainInventory.length; i++)
            {
                ItemStack stack = player.inventory.mainInventory[i];
                
                if (stack != null)
                {
                    if (stack.getItem() == SCObjects.psyonicCellEnergy)
                    {
                        ItemPsyonicEnergyCell cell = (ItemPsyonicEnergyCell) stack.getItem();
                        
                        if (cell.getEnergy(stack) < 1000 && cell.getEnergy(stack) + amount <= 1000)
                        {
                            cell.setEnergy(stack, amount / getCellCount(player));
                            break;
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < player.inventory.mainInventory.length; i++)
            {
                ItemStack stack = player.inventory.mainInventory[i];
                
                if (stack != null)
                {
                    if (stack.getItem() == SCObjects.psyonicCellEnergy)
                    {
                        ItemPsyonicEnergyCell cell = (ItemPsyonicEnergyCell) stack.getItem();
                        
                        if (cell.getEnergy(stack) < 1000 && cell.getEnergy(stack) + amount <= 1000)
                        {
                            cell.setEnergy(stack, 1000);
                            break;
                        }
                    }
                }
            }
        }
    }
}
