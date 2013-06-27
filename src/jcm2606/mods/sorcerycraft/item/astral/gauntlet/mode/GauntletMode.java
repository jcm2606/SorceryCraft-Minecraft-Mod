package jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode;

import java.util.List;

import jcm2606.mods.sorcerycraft.lib.Reference;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class GauntletMode {
    public static final String USE_BLOCK_ACTIVATE = "use:BlockActivate";
    public static final String USE_RIGHT_CLICK = "use:RightClick";
    public static final String USE_ITEM_USE = "use: ItemUse";
    public static final String USE_ENTITY_ATTACK = "use:EntityAttack";
    
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
     * @param living (Only for entity attacking uses)
     * @param x (Only for coord-based uses)
     * @param y (Only for coord-based uses)
     * @param z (Only for coord-based uses)
     * @param side (Only for coord-based uses)
     */
    public abstract boolean onUse(String type, ItemStack stack, World world, EntityPlayer player, EntityLiving living, int x, int y, int z, int side);
    
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
     * @param type The mode type.
     * @param player
     */
    public abstract int energyRequired(String type, EntityPlayer player);
    
    /**
     * Used to add custom lines of information to the Astral Gauntlet's mouseover popup.
     * 
     * @param player
     * @param stack
     * @param isSneaking
     * @param list
     */
    public abstract void addInfoToItemMouseover(EntityPlayer player, ItemStack stack, boolean isSneaking, List list);
}
