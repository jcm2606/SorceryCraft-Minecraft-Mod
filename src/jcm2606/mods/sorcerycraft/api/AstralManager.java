package jcm2606.mods.sorcerycraft.api;

import jcm2606.mods.jccore.compat.container.CompatibilityContainer;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.EnumUseType;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.GauntletMode;
import jcm2606.mods.sorcerycraft.api.compat.HandlerMethodID;
import jcm2606.mods.sorcerycraft.astral.gauntlet.ModeManualEmpowerment;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyCell;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AstralManager
{
    public static GauntletMode[] modeList = new GauntletMode[256];
    
    public static int getTotalEnergy(EntityPlayer player)
    {
        int currentEnergy = 0;
        
        for (int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack = player.inventory.mainInventory[i];
            
            if (stack != null)
            {
                if (stack.getItem() == SCObjects.astralCellEnergy)
                {
                    ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack.getItem();
                    
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
                if (stack.getItem() == SCObjects.astralCellEnergy)
                {
                    ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack.getItem();
                    
                    if (cell.getEnergy(stack) > 0)
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
                if (stack.getItem() == SCObjects.astralCellEnergy)
                {
                    ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack.getItem();
                    
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
    
    public void loadCoreModes()
    {
        registerMode(new ModeManualEmpowerment());
    }
    
    public static void registerMode(GauntletMode mode)
    {
        int id = mode.id;
        
        if (modeList[id] == null)
        {
            modeList[id] = mode;
        } else
        {
            throw new RuntimeException("Slot " + id + " is already occupied by mode " + modeList[id].name + " when adding mode " + mode.name + ".");
        }
    }
    
    public static GauntletMode getMode(int id)
    {
        return modeList[id];
    }
    
    public static boolean useGauntlet(EnumUseType useType, int id, ItemStack stack, EntityPlayer player, EntityLivingBase living, World world, int x,
            int y, int z, int side)
    {
        GauntletMode mode = getMode(id);
        
        if (mode.energyRequired(useType, player) == 0)
        {
            CompatibilityContainer.postUpdate(HandlerMethodID.ASTRAL_GAUNTLET_MODE_USE, null);
            return mode.onUse(useType, stack, world, player, living, x, y, z, side);
        }
        
        if (mode != null)
        {
            if (getTotalEnergy(player) >= mode.energyRequired(useType, player))
            {
                if (mode.onUse(useType, stack, world, player, living, x, y, z, side))
                {
                    CompatibilityContainer.postUpdate(HandlerMethodID.ASTRAL_GAUNTLET_MODE_USE, null);
                    
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static void onGauntletUpdateTick(int id, ItemStack stack, Entity entity, World world, int slot, boolean isCurrentItem)
    {
        GauntletMode mode = getMode(id);
        
        if (mode != null)
        {
            mode.onGauntletItemUpdateTick(stack, (EntityPlayer) (EntityLivingBase) entity, world, slot, isCurrentItem);
        }
    }
    
    public static int getNextAvailableId()
    {
        if (modeList[255] != null)
        {
            throw new RuntimeException("No more available mode ids.");
        }
        
        for (int i = 0; i < 256; i++)
        {
            if (modeList[i] == null)
            {
                return i;
            }
        }
        
        return 0;
    }
}
