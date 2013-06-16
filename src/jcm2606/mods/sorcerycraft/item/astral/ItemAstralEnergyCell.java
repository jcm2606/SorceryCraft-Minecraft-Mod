package jcm2606.mods.sorcerycraft.item.astral;

import java.util.List;

import jcm2606.mods.jccore.helper.NBTHelper;
import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.fx.EntityAstralEnergyFX;
import jcm2606.mods.sorcerycraft.item.SCItem;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAstralEnergyCell extends SCItem {
    public final String NBT_ENERGY = "energy";
    
    public ItemAstralEnergyCell(int par1) {
        super(par1, "astralEnergyCell");
        this.setNoRepair();
        this.setMaxStackSize(1);
        this.setMaxDamage(1000);
    }
    
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return getEnergy(stack) != 1000;
    }
    
    @Override
    public void onUpdate(ItemStack stack, World world, Entity player, int slot, boolean isCurrentItem) {
        stack.setItemDamage(this.getEnergy(stack));
    }
    
    public void setEnergy(ItemStack stack, int energy)
    {
        NBTHelper.setInt(NBTHelper.getNBTCompoundForItemStack(stack), NBT_ENERGY, energy);
    }
    
    public int getEnergy(ItemStack stack)
    {
        return NBTHelper.getInt(NBTHelper.getNBTCompoundForItemStack(stack), NBT_ENERGY);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list,
            boolean par4) {
        list.add("Storing" + (1000 - this.getEnergy(stack)) + " Astral energy.");
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(int itemID, CreativeTabs tab, List list) {
        ItemStack stack = new ItemStack(SCObjects.astralenergycell, 1, 0);
        this.setEnergy(stack, 0);
        list.add(stack);
        
        ItemStack stack2 = new ItemStack(SCObjects.astralenergycell, 1, 1000);
        this.setEnergy(stack2, 1000);
        list.add(stack2);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if(player.isSneaking())
        {
            if(world.isRemote)
            {
                if(this.getEnergy(stack) < 1000)
                {
                    for(int i = 0; i < (1000 - this.getEnergy(stack)) / 10; i++)
                    {
                        EntityFX fx = new EntityAstralEnergyFX(world, player.posX, player.posY, player.posZ, player.posX + (Minecraft.getMinecraft().theWorld.rand.nextFloat() - Minecraft.getMinecraft().theWorld.rand.nextFloat()) * 2F, player.posY - 2D, player.posZ + (Minecraft.getMinecraft().theWorld.rand.nextFloat() - Minecraft.getMinecraft().theWorld.rand.nextFloat()) * 2F, false, 10);
                        
                        Minecraft.getMinecraft().effectRenderer.addEffect(fx);
                    }
                }
            }
            
            this.setEnergy(stack, 1000);
        }
        
        return stack;
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.MAGICAL);
    }
}
