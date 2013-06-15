package jcm2606.mods.sorcerycraft.item.astral;

import java.util.List;

import jcm2606.mods.jccore.helper.NBTHelper;
import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.item.SCItemShine;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.AstralGauntletManager;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode.GauntletMode;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAstralGauntlet extends SCItemShine {
    public final String NBT_TAG_MODE = "mode";
    
    public ItemAstralGauntlet(int par1) {
        super(par1, "astralGauntlet");
        this.setMaxDamage(200);
        this.setMaxStackSize(1);
        this.setNoRepair();
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.MAGICAL);
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
        return AstralGauntletManager.useGauntlet(GauntletMode.USE_BLOCK_ACTIVATE, getMode(stack), stack, player, null, world, x, y, z, side);
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
        {
            if(AstralGauntletManager.modeList[getMode(stack) + 1] == null)
            {
                setMode(stack, 0);
            } else {
                setMode(stack, getMode(stack) + 1);
            }
        } else {
            if(AstralGauntletManager.getMode(this.getMode(stack)).hasItemUse)
            {
                player.setItemInUse(stack, 72000);
            } else {
                AstralGauntletManager.useGauntlet(GauntletMode.USE_RIGHT_CLICK, getMode(stack), stack, player, null, world, 0, 0, 0, -1);
            }
        }
        
        return stack;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list,
            boolean par4) {
        GauntletMode mode = AstralGauntletManager.getMode(getMode(stack));
        
        if(mode != null)
        {
            list.add(mode.name);
        }
    }
    
    /**
     * returns the action that specifies what animation to play when the items
     * is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return AstralGauntletManager.getMode(getMode(stack)).useAction;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return AstralGauntletManager.getMode(getMode(stack)).useActionDuration;
    }
    
    /**
     * Called each tick while using an item.
     * @param stack The Item being used
     * @param player The Player using the item
     * @param count The amount of time in tick the item has been used for continuously
     */
    @Override
    public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count)
    {
        AstralGauntletManager.useGauntlet(GauntletMode.USE_ITEM_USE, getMode(stack), stack, player, null, player.worldObj, 0, 0, 0, -1);
    }
    
    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    @Override
    public void onUpdate(ItemStack stack, World world, Entity player, int slot, boolean isCurrentItem) {
        AstralGauntletManager.onGauntletUpdateTick(getMode(stack), stack, player, world, slot, isCurrentItem);
    }
    
    /**
     * Current implementations of this method in child classes do not use the
     * entry argument beside ev. They just raise the damage on the stack.
     */
    @Override
    public boolean hitEntity(ItemStack stack, EntityLiving living, EntityLiving living2) {
        return AstralGauntletManager.useGauntlet(GauntletMode.USE_ENTITY_ATTACK, getMode(stack), stack, (EntityPlayer) living2, living, living.worldObj, 0, 0, 0, -1);
    }
}
