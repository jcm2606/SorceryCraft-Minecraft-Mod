package jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode;

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
    
    public GauntletMode(int i, String s, String s2)
    {
        this.id = i;
        this.name = s;
        this.icon = s2;
        this.useAction = EnumAction.none;
        this.useActionDuration = 0;
        this.hasItemUse = false;
    }
    
    /**
     * Hook for use of any Astral Gauntlet with this mode set.
     * 
     * @param type The use type of this mode.
     * @param stack The ItemStack that the Astral Gauntlet that is in use occupies.
     * @param world The world.
     * @param player The player.
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
     * @param stack The ItemStack for the Astral Gauntlet.
     * @param player The player holding the Astral Gauntlet.
     * @param world The world the player is in.
     * @param slot The slot in the inv of the player the Astral Gauntlet is in.
     * @param isCurrentItem Is this Astral Gauntlet the current item?
     */
    public abstract void onGauntletItemUpdateTick(ItemStack stack, EntityPlayer player, World world, int slot, boolean isCurrentItem);

    /**
     * Returns the amount of energy required for this mode.
     * 
     * @param type The mode type.
     * @param player The {@link EntityPlayer}
     */
    public abstract int energyRequired(String type, EntityPlayer player);
}
