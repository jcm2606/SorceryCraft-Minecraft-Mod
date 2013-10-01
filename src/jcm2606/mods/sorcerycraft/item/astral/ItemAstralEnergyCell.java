package jcm2606.mods.sorcerycraft.item.astral;

import java.util.List;

import jcm2606.mods.jccore.core.helper.NBTHelper;
import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.SCParticle;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAstralEnergyCell extends SCItem
{
    public final String NBT_ENERGY = "energy";
    
    public ItemAstralEnergyCell(int par1)
    {
        super(par1, "astralEnergyCell");
        this.setNoRepair();
        this.setMaxStackSize(1);
        this.setMaxDamage(1000);
    }
    
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return getEnergy(stack) != 0;
    }
    
    @Override
    public void onUpdate(ItemStack stack, World world, Entity player, int slot, boolean isCurrentItem)
    {
        stack.setItemDamage(1000 - this.getEnergy(stack));
    }
    
    public void setEnergy(ItemStack stack, int energy)
    {
        NBTHelper.setInt(NBTHelper.getNBTCompoundForItemStack(stack), NBT_ENERGY, energy);
    }
    
    public int getEnergy(ItemStack stack)
    {
        int energy = 0;
        
        NBTTagCompound tag = NBTHelper.getNBTCompoundForItemStack(stack);
        
        if(tag.hasKey(NBT_ENERGY))
        {
            energy = tag.getInteger(NBT_ENERGY);
        } else {
            energy = 1000;
        }
        
        return energy;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        list.add("Storing " + this.getEnergy(stack) + " Astral energy");
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(int itemID, CreativeTabs tab, List list)
    {
        ItemStack stack = new ItemStack(SCObjects.astralCellEnergy, 1, 0);
        this.setEnergy(stack, 1000);
        list.add(stack);
        
        ItemStack stack2 = new ItemStack(SCObjects.astralCellEnergy, 1, 1000);
        this.setEnergy(stack2, 0);
        list.add(stack2);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (player.isSneaking())
        {
            if (world.isRemote)
            {
                if (this.getEnergy(stack) > 0)
                {
                    for (int i = 0; i < this.getEnergy(stack) / 5; i++)
                    {
                        float r1 = player.worldObj.rand.nextFloat() * 360.0F;
                        float mx = -MathHelper.sin(r1 / 180.0F * 3.141593F) / 5.0F;
                        float mz = MathHelper.cos(r1 / 180.0F * 3.141593F) / 5.0F;
                        
                        double adjAngle = 35.0D;
                        double dist = 0.4D;
                        
                        EntityPlayer center = player;
                        
                        double posX = center.posX - Math.cos((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
                        double posY = center.posY - Math.sin(center.rotationPitch / 540.0F * Math.PI) * dist;
                        double posZ = center.posZ + Math.sin((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
                        
                        SCParticle.spawnAstralEnergyFX(posX, posY, posZ, mx, 0, mz, 500, true, true, false);
                    }
                }
            }
            
            this.setEnergy(stack, 0);
        }
        
        return stack;
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
    }
}
