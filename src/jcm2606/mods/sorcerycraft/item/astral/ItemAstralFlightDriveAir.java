package jcm2606.mods.sorcerycraft.item.astral;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.api.AstralManager;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCItemShine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemAstralFlightDriveAir extends SCItemShine {
    public int ticksUsedFor = 0;
    
    public ItemAstralFlightDriveAir(int par1) {
        super(par1, "astralFlightDriveAir");
        this.setMaxStackSize(1);
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
    }
    
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4,
            boolean par5) {
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase living = (EntityLiving) entity;

            if (living instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) living;

                for (int i = 0; i <= 8; i++) {
                    ItemStack item = player.inventory.getStackInSlot(i);

                    if (item != null && item == stack) {
                        
                        if(!(AstralManager.getTotalEnergyForPlayer(player) > 0) && !player.capabilities.isCreativeMode)
                        {
                            return;
                        }
                        
                        player.capabilities.allowFlying = true;
                        player.fallDistance = 0.0f;
                        
                        if(player.capabilities.isFlying)
                        {
                            for(int j = 0; j < 4; j++)
                            {
                                int divider = 2;
                                
                                float r1 = player.worldObj.rand.nextFloat() * 360.0F;
                                float mx = -MathHelper.sin(r1 / 180.0F * 3.141593F) / 5.0F;
                                float mz = MathHelper.cos(r1 / 180.0F * 3.141593F) / 5.0F;
                                
                                world.spawnParticle("cloud", player.posX, player.posY - 1.8, player.posZ, mx, 0, mz);
                                world.spawnParticle("cloud", player.posX, player.posY - 1.8, player.posZ, mx, 0, mz);
                            }
                            
                            if(player.inventory.hasItem(SCObjects.astralenergycell.itemID))
                            {
                                for(int j = 0; j < player.inventory.mainInventory.length; j++)
                                {
                                    ItemStack stack2 = player.inventory.mainInventory[j];
                                    
                                    if(stack2 != null)
                                    {
                                        if(stack2.getItem() == SCObjects.astralenergycell)
                                        {
                                            ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack2.getItem();
                                            
                                            if(cell.getEnergy(stack2) <= 999)
                                            {
                                                cell.setEnergy(stack2, cell.getEnergy(stack2) + 1);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("Air (Free Flight)");
    }
}
