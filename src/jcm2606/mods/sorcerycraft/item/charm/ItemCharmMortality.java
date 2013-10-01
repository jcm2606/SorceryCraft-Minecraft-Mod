package jcm2606.mods.sorcerycraft.item.charm;

import java.util.List;

import jcm2606.mods.jccore.core.helper.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

public class ItemCharmMortality extends ItemCharm
{
    public ItemCharmMortality(int par1)
    {
        super(par1, "charmMortality");
        this.setMaxDamage(800);
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is
     * pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            if (!Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && !par3EntityPlayer.isSneaking())
            {
                if (getStoredHealth(par1ItemStack) < 20 && par3EntityPlayer.prevHealth > 1 && getBoundPlayer(par1ItemStack).equals(
                        par3EntityPlayer.username))
                {
                    setStoredHealth(par1ItemStack, getStoredHealth(par1ItemStack) + 1);
                    par1ItemStack.damageItem(1, par3EntityPlayer);
                    par3EntityPlayer.setEntityHealth(par3EntityPlayer.prevHealth - 1);
                }
            } else
            {
                if (getStoredHealth(par1ItemStack) != 0 && par3EntityPlayer.prevHealth < 20 && getBoundPlayer(par1ItemStack).equals(
                        par3EntityPlayer.username))
                {
                    setStoredHealth(par1ItemStack, getStoredHealth(par1ItemStack) - 1);
                    par1ItemStack.damageItem(1, par3EntityPlayer);
                    par3EntityPlayer.setEntityHealth(par3EntityPlayer.prevHealth + 1);
                }
            }
            
            if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && !par3EntityPlayer.isSneaking() && getBoundPlayer(par1ItemStack).equals(
                    par3EntityPlayer.username))
            {
                setStoredHealth(par1ItemStack, 0);
                par1ItemStack.damageItem(20, par3EntityPlayer);
            }
        }
        
        if (getBoundPlayer(par1ItemStack).equals(""))
        {
            setBoundPlayer(par1ItemStack, par3EntityPlayer.username);
        }
        
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        
        return par1ItemStack;
    }
    
    public static void setStoredHealth(ItemStack itemStack, int health)
    {
        NBTTagCompound nbtTagCompound = NBTHelper.getNBTCompoundForItemStack(itemStack);
        nbtTagCompound.setInteger("StoredHealth", health);
    }
    
    public static int getStoredHealth(ItemStack itemstack)
    {
        NBTTagCompound nbtTagCompound = NBTHelper.getNBTCompoundForItemStack(itemstack);
        
        if (nbtTagCompound != null)
        {
            return nbtTagCompound.getInteger("StoredHealth");
        }
        
        return 0;
    }
    
    public static void setBoundPlayer(ItemStack itemStack, String name)
    {
        NBTTagCompound nbtTagCompound = NBTHelper.getNBTCompoundForItemStack(itemStack);
        nbtTagCompound.setString("BoundPlayerName", name);
    }
    
    public static String getBoundPlayer(ItemStack itemstack)
    {
        NBTTagCompound nbtTagCompound = NBTHelper.getNBTCompoundForItemStack(itemstack);
        
        if (nbtTagCompound != null)
        {
            return nbtTagCompound.getString("BoundPlayerName");
        }
        
        return "";
    }
    
    /**
     * returns the action that specifies what animation to play when the items
     * is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }
    
    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }
    
    /**
     * Retrieves the normal 'lifespan' of this item when it is dropped on the
     * ground as a EntityItem. This is in ticks, standard result is 6000, or 5
     * mins.
     * 
     * @param itemStack
     *            The current ItemStack
     * @param world
     *            The world the entity is in
     * @return The normal lifespan in ticks.
     */
    @Override
    public int getEntityLifespan(ItemStack itemStack, World world)
    {
        if (ItemCharmMortality.getStoredHealth(itemStack) == 0)
        {
            return 1200;
        }
        
        return 1200 * (ItemCharmMortality.getStoredHealth(itemStack) / 4);
    }
    
    /**
     * Called each tick while using an item.
     * 
     * @param stack
     *            The Item being used
     * @param player
     *            The Player using the item
     * @param count
     *            The amount of time in tick the item has been used for
     *            continuously
     */
    @Override
    public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count)
    {
        this.onCursedUse(stack, player, player.worldObj, ItemCharm.getCurseName(stack));
    }
    
    @Override
    public void getSubtext(ItemStack stack, EntityPlayer player, World world, List list)
    {
        if (getBoundPlayer(stack).equals(""))
        {
            list.add("This charm is not bound.");
        } else
        {
            list.add("This charm is bound to \247o" + getBoundPlayer(stack) + " \247r\2477while");
            list.add("it's in their possession.");
        }
        
        if (getStoredHealth(stack) != 0)
        {
            list.add("");
            list.add("This charm is currently storing " + getStoredHealth(stack) + " HP.");
        }
        
        if (getCurseOwner(stack).equals(player.username))
        {
            if (!getCurseName(stack).equals(""))
            {
                list.add("");
            }
        }
    }
}
