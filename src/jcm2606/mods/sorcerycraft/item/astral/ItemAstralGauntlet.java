package jcm2606.mods.sorcerycraft.item.astral;

import java.util.List;

import jcm2606.mods.jccore.compat.container.CompatibilityContainer;
import jcm2606.mods.jccore.core.helper.NBTHelper;
import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.api.AstralManager;
import jcm2606.mods.sorcerycraft.api.IKeyBound;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.EnumUseType;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.GauntletMode;
import jcm2606.mods.sorcerycraft.api.compat.HandlerMethodID;
import jcm2606.mods.sorcerycraft.core.helper.SCHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAstralGauntlet extends SCItem implements IKeyBound
{
    public final String NBT_TAG_MODE = "mode";
    
    public ItemAstralGauntlet(int par1)
    {
        super(par1, "astralGauntlet");
        this.setMaxDamage(200);
        this.setMaxStackSize(1);
        this.setNoRepair();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return false;
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
    }
    
    public void setMode(ItemStack stack, int mode)
    {
        NBTHelper.setInt(NBTHelper.getNBTCompoundForItemStack(stack), NBT_TAG_MODE, mode);
    }
    
    public int getMode(ItemStack stack)
    {
        return NBTHelper.getInt(NBTHelper.getNBTCompoundForItemStack(stack), NBT_TAG_MODE);
    }
    
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
        return AstralManager.useGauntlet(EnumUseType.BLOCK_RIGHT_CLICK, getMode(stack), stack, player, null, world, x, y, z, side);
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is
     * pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (AstralManager.getMode(this.getMode(stack)).hasItemUse)
        {
            player.setItemInUse(stack, AstralManager.getMode(this.getMode(stack)).useActionDuration);
        } else
        {
            AstralManager.useGauntlet(EnumUseType.ITEM_RIGHT_CLICK, getMode(stack), stack, player, null, world, 0, 0, 0, -1);
        }
        
        return stack;
    }
    
    @Override
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        GauntletMode mode = AstralManager.getMode(getMode(stack));
        
        if (mode != null)
        {
            list.add("\247o" + mode.name);
            
            if (stack.getItemDamage() > 0)
            {
                list.add("\247oDamaged (" + stack.getItemDamage() + ")");
            }
            
            if (SCHelper.playerHasPerceptionMedallion(player))
            {
                if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode))
                {
                    mode.addInfoToItemMouseover(player, stack, Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode), list);
                } else
                {
                    list.add("<Hold SHIFT>");
                }
            }
        }
    }
    
    /**
     * returns the action that specifies what animation to play when the items
     * is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return AstralManager.getMode(getMode(stack)).useAction;
    }
    
    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return AstralManager.getMode(getMode(stack)).useActionDuration;
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
        AstralManager.useGauntlet(EnumUseType.ITEM_USE, getMode(stack), stack, player, null, player.worldObj, 0, 0, 0, -1);
    }
    
    /**
     * Called each tick as long the item is on a player inventory. Uses by maps
     * to check if is on a player hand and update it's contents.
     */
    @Override
    public void onUpdate(ItemStack stack, World world, Entity player, int slot, boolean isCurrentItem)
    {
        AstralManager.onGauntletUpdateTick(getMode(stack), stack, player, world, slot, isCurrentItem);
    }
    
    /**
     * Current implementations of this method in child classes do not use the
     * entry argument beside ev. They just raise the damage on the stack.
     */
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase living, EntityLivingBase living2)
    {
        return AstralManager.useGauntlet(EnumUseType.ENTITY_HIT, getMode(stack), stack, (EntityPlayer) living2, living, living.worldObj, 0, 0, 0, -1);
    }
    
    @Override
    public void doKeyBindingAction(EntityPlayer player, ItemStack stack, String keyBinding)
    {
        if (keyBinding.equals(Reference.KEY_BIND_INHAND_ITEM_DESC))
        {
            if (AstralManager.modeList[getMode(stack) + 1] == null)
            {
                setMode(stack, 0);
            } else
            {
                setMode(stack, getMode(stack) + 1);
            }
            
            CompatibilityContainer.postUpdate(HandlerMethodID.ASTRAL_GAUNTLET_MODE_SWITCH, null);
        }
    }
    
    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player)
    {
        return AstralManager.useGauntlet(EnumUseType.BLOCK_LEFT_CLICK, getMode(stack), stack, player, player, player.worldObj, x, y, z, 0);
    }
}
