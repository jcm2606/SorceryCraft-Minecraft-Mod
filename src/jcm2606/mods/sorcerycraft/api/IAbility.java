package jcm2606.mods.sorcerycraft.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IAbility
{
    public String getName();
    
    public boolean rightClickBlock(World world, EntityPlayer player, ItemStack stack, int x, int y, int z, int side);
    
    public boolean rightClick(EntityPlayer player, ItemStack stack);
    
    /**
     * Only called if you are setting the player's current item in use on right
     * click.
     */
    public boolean itemUseTick(ItemStack stack, EntityPlayer player, int count);
    
    public boolean handleItemUseTick(ItemStack stack, EntityPlayer player);
    
    /**
     * Return 'null' for no action.
     * 
     * @return {@link EnumAction}: Action
     */
    public EnumAction getUseAction(ItemStack stack);
}
