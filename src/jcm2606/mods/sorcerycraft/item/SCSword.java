package jcm2606.mods.sorcerycraft.item;

import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SCSword extends ItemSword
{
    public final EnumToolMaterial toolMaterial;
    public String name;
    
    public SCSword(int par1, EnumToolMaterial par2EnumToolMaterial, String par3)
    {
        super(par1, par2EnumToolMaterial);
        this.toolMaterial = par2EnumToolMaterial;
        this.maxStackSize = 1;
        this.setMaxDamage(par2EnumToolMaterial.getMaxUses());
        this.setCreativeTab(SorceryCraft.tabItems);
        this.setUnlocalizedName(par3);
        this.name = par3;
    }
    
    @Override
    public float func_82803_g()
    {
        return this.toolMaterial.getDamageVsEntity();
    }
    
    /**
     * Returns the strength of the stack against a given block. 1.0F base,
     * (Quality+1)*2 if correct blocktype, 1.5F if sword
     */
    @Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        if (par2Block.blockID == Block.web.blockID)
        {
            return 15.0F;
        } else
        {
            Material var3 = par2Block.blockMaterial;
            return var3 != Material.plants && var3 != Material.vine && var3 != Material.glass && var3 != Material.leaves && var3 != Material.pumpkin ? 1.0F
                    : 1.5F;
        }
    }
    
    /**
     * Current implementations of this method in child classes do not use the
     * entry argument beside ev. They just raise the damage on the stack.
     */
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase living, EntityLivingBase living2)
    {
        stack.damageItem(1, living2);
        /*
         * if(living2 instanceof EntityPlayer) { EntityPlayer player =
         * (EntityPlayer) living2;
         * 
         * if(EnchantmentHelper.getEnchantmentLevel(Enchantments.aeronautics.
         * effectId, player.getCurrentEquippedItem()) == 1) { living.motionY +=
         * 0.5f;
         * 
         * if(player.worldObj.isRemote) { for(int i = 0; i < 16; i++) {
         * player.worldObj.spawnParticle("cloud", living.posX +
         * (player.worldObj.rand.nextGaussian() / 2), living.posY +
         * (player.worldObj.rand.nextGaussian() / 2), living.posZ +
         * (player.worldObj.rand.nextGaussian() / 2), 0, 0.05f, 0);
         * player.worldObj.spawnParticle("cloud", living.posX -
         * (player.worldObj.rand.nextGaussian() / 2), living.posY +
         * (player.worldObj.rand.nextGaussian() / 2), living.posZ -
         * (player.worldObj.rand.nextGaussian() / 2), 0, 0.05f, 0); } } }
         * 
         * if(EnchantmentHelper.getEnchantmentLevel(Enchantments.aeronautics.
         * effectId, player.getCurrentEquippedItem()) == 2) { living.motionY +=
         * 1.0f;
         * 
         * if(player.worldObj.isRemote) { for(int i = 0; i < 16; i++) {
         * player.worldObj.spawnParticle("cloud", living.posX +
         * (player.worldObj.rand.nextGaussian() / 2), living.posY +
         * (player.worldObj.rand.nextGaussian() / 2), living.posZ +
         * (player.worldObj.rand.nextGaussian() / 2), 0, 0.1f, 0);
         * player.worldObj.spawnParticle("cloud", living.posX -
         * (player.worldObj.rand.nextGaussian() / 2), living.posY +
         * (player.worldObj.rand.nextGaussian() / 2), living.posZ -
         * (player.worldObj.rand.nextGaussian() / 2), 0, 0.1f, 0); } } }
         * 
         * if(EnchantmentHelper.getEnchantmentLevel(Enchantments.aeronautics.
         * effectId, player.getCurrentEquippedItem()) >= 3) { living.motionY +=
         * 1.5f;
         * 
         * if(player.worldObj.isRemote) { for(int i = 0; i < 16; i++) {
         * player.worldObj.spawnParticle("cloud", living.posX +
         * (player.worldObj.rand.nextGaussian() / 2), living.posY +
         * (player.worldObj.rand.nextGaussian() / 2), living.posZ +
         * (player.worldObj.rand.nextGaussian() / 2), 0, 0.15f, 0);
         * player.worldObj.spawnParticle("cloud", living.posX -
         * (player.worldObj.rand.nextGaussian() / 2), living.posY +
         * (player.worldObj.rand.nextGaussian() / 2), living.posZ -
         * (player.worldObj.rand.nextGaussian() / 2), 0, 0.15f, 0); } } } }
         */
        if (living instanceof EntityEnderman || living instanceof EntityGhast || living instanceof EntityBlaze || living instanceof EntityMagmaCube || living instanceof EntityDragon)
        {
            for (int i = 0; i < 8; i++)
            {
                living.worldObj.spawnParticle("portal", living.posX + (living.worldObj.rand.nextGaussian() / 3),
                        living.posY + living.height + (living.worldObj.rand.nextGaussian() / 2),
                        living.posZ + (living.worldObj.rand.nextGaussian() / 3), living.worldObj.rand.nextGaussian(), -1.0D, living.worldObj.rand
                                .nextGaussian());
                living.worldObj.spawnParticle("portal", living.posX - (living.worldObj.rand.nextGaussian() / 3),
                        living.posY + living.height + (living.worldObj.rand.nextGaussian() / 2),
                        living.posZ - (living.worldObj.rand.nextGaussian() / 3), living.worldObj.rand.nextGaussian(), -1.0D, living.worldObj.rand
                                .nextGaussian());
            }
        }
        
        return true;
    }
    
    @Override
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6,
            EntityLivingBase par7EntityLiving)
    {
        if (Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D)
        {
            par1ItemStack.damageItem(2, par7EntityLiving);
        }
        
        return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
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
     * Called whenever this item is equipped and the right mouse button is
     * pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }
    
    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    @Override
    public boolean canHarvestBlock(Block par1Block)
    {
        return par1Block.blockID == Block.web.blockID;
    }
    
    /**
     * Return the enchantability factor of the item, most of the time is based
     * on material.
     */
    @Override
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }
    
    @Override
    public String getToolMaterialName()
    {
        return this.toolMaterial.toString();
    }
    
    /**
     * Return whether this item is repairable in an anvil.
     */
    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return this.toolMaterial.getToolCraftingMaterial() == par2ItemStack.itemID ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }
    
    /**
     * Called each tick as long the item is on a player inventory. Uses by maps
     * to check if is on a player hand and update it's contents.
     */
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
    {
        /*
         * 
         * if(entity instanceof EntityLivingBase) { EntityLivingBase living =
         * (EntityLiving) entity;
         * 
         * if(living instanceof EntityPlayer) { EntityPlayer player =
         * (EntityPlayer) living;
         * 
         * if(player.getCurrentEquippedItem() == stack) {
         * if(stack.getItemDamage() > 0) {
         * if(EnchantmentHelper.getEnchantmentLevel
         * (Enchantments.rebuild.effectId, stack) == 1) { int randInt =
         * world.rand.nextInt(2000);
         * 
         * if(randInt <= 5) { stack.damageItem(-1, player); } }
         * 
         * if(EnchantmentHelper.getEnchantmentLevel(Enchantments.rebuild.effectId
         * , stack) >= 2) { int randInt = world.rand.nextInt(2000);
         * 
         * if(randInt <= 40) { stack.damageItem(-1, player); } } } } } }
         */
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("SorceryCraft:" + name);
    }
}
