package jcm2606.mods.sorcerycraft.item.astral.gauntlet;

import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyCell;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode.GauntletMode;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode.ModeBlockBreak;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode.ModeBlockTravel;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode.ModeEnergyAbsorb;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode.ModeFireball;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode.ModeHeal;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode.ModeStrength;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AstralGauntletManager {
    public static GauntletMode[] modeList = new GauntletMode[256];
    
    public static void loadCoreModes()
    {
        registerMode(new ModeBlockTravel());
        registerMode(new ModeEnergyAbsorb());
        registerMode(new ModeFireball());
        registerMode(new ModeBlockBreak());
        registerMode(new ModeHeal());
        registerMode(new ModeStrength());
    }
    
    public static void registerMode(GauntletMode mode)
    {
        int id = mode.id;
        
        if(modeList[id] == null)
        {
            modeList[id] = mode;
        } else {
            throw new RuntimeException("Slot " + id + " is already occupied by mode " + modeList[id].name + " when adding mode " + mode.name + ".");
        }
    }
    
    public static GauntletMode getMode(int id)
    {
        return modeList[id];
    }
    
    public static boolean useGauntlet(String useType, int id, ItemStack stack, EntityPlayer player, EntityLiving living, World world, int x, int y, int z, int side)
    {
        GauntletMode mode = getMode(id);
        
        for(int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack2 = player.inventory.mainInventory[i];
            
            if(stack2 != null)
            {
                if(stack2.getItem() == SCObjects.astralenergycell)
                {
                    ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack2.getItem();
                    
                    if(mode != null)
                    {
                        if(cell.getEnergy(stack2) <= 1000 && (1000 - cell.getEnergy(stack2) >= mode.energyRequired(useType)))
                        {
                            if(mode.onUse(useType, stack, world, player, living, x, y, z, side))
                            {
                                cell.setEnergy(stack2, cell.getEnergy(stack2) + mode.energyRequired(useType));
                                
                                return true;
                            }
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    public static void onGauntletUpdateTick(int id, ItemStack stack, Entity entity, World world, int slot, boolean isCurrentItem)
    {
        GauntletMode mode = getMode(id);
        
        if(mode != null)
        {
            mode.onGauntletItemUpdateTick(stack, (EntityPlayer) (EntityLiving) entity, world, slot, isCurrentItem);
        }
    }
    
    public static int getNextAvailableId()
    {
        if(modeList[255] != null)
        {
            throw new RuntimeException("No more available mode ids.");
        }
        
        for(int i = 0; i < 256; i++)
        {
            if(modeList[i] == null)
            {
                return i;
            }
        }
        
        return 0;
    }
}