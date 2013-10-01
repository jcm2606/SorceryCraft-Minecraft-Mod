package jcm2606.mods.sorcerycraft.api.astral.gauntlet;

import java.util.List;

import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyCell;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class GauntletMode
{
    public String name = "ERR";
    public int id = -1;
    public String icon = Reference.PATH_TEXTURES_GUI_HUD + "astral/mode_blank";
    public EnumAction useAction;
    public int useActionDuration;
    public boolean hasItemUse;
    public boolean hasErroredName;
    
    public GauntletMode(int i, String s, String s2)
    {
        this.id = i;
        this.name = s;
        this.icon = s2;
        this.useAction = EnumAction.none;
        this.useActionDuration = 0;
        this.hasItemUse = false;
        this.hasErroredName = true;
    }
    
    /**
     * Hook for use of any Astral Gauntlet with this mode set.
     * 
     * @param type
     * @param stack
     * @param world
     * @param player
     * @param living
     *            (Only for entity attacking uses)
     * @param x
     *            (Only for coord-based uses)
     * @param y
     *            (Only for coord-based uses)
     * @param z
     *            (Only for coord-based uses)
     * @param side
     *            (Only for coord-based uses)
     */
    public abstract boolean onUse(EnumUseType type, ItemStack stack, World world, EntityPlayer player, EntityLivingBase living, int x, int y, int z,
            int side);
    
    /**
     * Hook for the update ticks of the Astral Gauntlet item.
     * 
     * @param stack
     * @param player
     * @param world
     * @param slot
     * @param isCurrentItem
     */
    public abstract void onGauntletItemUpdateTick(ItemStack stack, EntityPlayer player, World world, int slot, boolean isCurrentItem);
    
    /**
     * Returns the amount of energy required for this mode.
     * 
     * @param type
     *            The mode type.
     * @param player
     */
    public abstract int energyRequired(EnumUseType type, EntityPlayer player);
    
    /**
     * Used to add custom lines of information to the Astral Gauntlet's
     * mouseover popup.
     * 
     * @param player
     * @param stack
     * @param isSneaking
     * @param list
     */
    public abstract void addInfoToItemMouseover(EntityPlayer player, ItemStack stack, boolean isSneaking, List list);
    
    public void useEnergy(EntityPlayer player, EnumUseType type)
    {
        for (int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack = player.inventory.mainInventory[i];
            
            if (stack != null)
            {
                if (stack.getItem() == SCObjects.astralCellEnergy)
                {
                    ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack.getItem();
                    
                    if ((cell.getEnergy(stack)) >= this.energyRequired(type, player))
                    {
                        cell.setEnergy(stack, cell.getEnergy(stack) - this.energyRequired(type, player));
                        break;
                    }
                }
            }
        }
    }
}
