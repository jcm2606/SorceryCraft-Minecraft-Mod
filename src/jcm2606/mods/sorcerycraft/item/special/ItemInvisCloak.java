package jcm2606.mods.sorcerycraft.item.special;

import jcm2606.mods.jccore.helper.NBTHelper;
import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.api.IKeyBound;
import jcm2606.mods.sorcerycraft.api.compat.CompatContainerSC;
import jcm2606.mods.sorcerycraft.api.compat.HandlerMethodID;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemInvisCloak extends SCItem implements IKeyBound
{
    
    public ItemInvisCloak(int par1)
    {
        super(par1, "invisCloak");
        this.setMaxDamage(1024);
    }
    
    @Override
    public int getItemEnchantability()
    {
        return 20;
    }
    
    /**
     * Called each tick as long the item is on a player inventory. Uses by maps
     * to check if is on a player hand and update it's contents.
     */
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
    {
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase living = (EntityLivingBase) entity;
            
            if (living instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) living;
                
                if (getState(stack))
                {
                    if (stack.getItemDamage() != stack.getMaxDamage() && par4 <= 8)
                    {
                        stack.damageItem(1, player);
                        
                        player.addPotionEffect(new PotionEffect(Potion.invisibility.getId(), 20, 50));
                    } else
                    {
                        setState(stack, false);
                    }
                } else
                {
                    if (stack.getItemDamage() != 0)
                    {
                        stack.damageItem(-1, player);
                        
                        player.curePotionEffects(new ItemStack(Item.bucketMilk));
                    }
                }
            }
        }
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is
     * pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par2World.isRemote)
        {
            if (!getState(par1ItemStack) && par1ItemStack.getItemDamage() <= par1ItemStack.getMaxDamage() / 2)
            {
                setState(par1ItemStack, true);
            } else
            {
                setState(par1ItemStack, false);
            }
        }
        
        CompatContainerSC.postUpdate(HandlerMethodID.INVIS_CLOAK_TOGGLE, null);
        
        return par1ItemStack;
    }
    
    public static void setState(ItemStack itemStack, boolean state)
    {
        NBTTagCompound nbtTagCompound = NBTHelper.getNBTCompoundForItemStack(itemStack);
        nbtTagCompound.setBoolean("CloakState", state);
    }
    
    public static boolean getState(ItemStack itemstack)
    {
        NBTTagCompound nbtTagCompound = NBTHelper.getNBTCompoundForItemStack(itemstack);
        
        if (nbtTagCompound != null)
        {
            return nbtTagCompound.getBoolean("CloakState");
        }
        
        return false;
    }
    
    @Override
    public void doKeyBindingAction(EntityPlayer player, ItemStack stack, String keyBinding)
    {
        if (!getState(stack))
        {
            setState(stack, true);
        } else
        {
            setState(stack, false);
        }
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.LEGENDARY);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return getState(par1ItemStack);
    }
}
