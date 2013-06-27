package jcm2606.mods.sorcerycraft.item.astral;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.jccore.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.item.SCItemShine;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;

public class ItemAstralFlightDriveFire extends SCItemShine {
	public ItemAstralFlightDriveFire(int par1) {
		super(par1, "astralFlightDriveFire");
		this.setMaxStackSize(1);
		this.setMaxDamage(3600);
		this.setNoRepair();
	}
	
	@Override
    public EnumRarity getRarity(ItemStack stack)
	{
	    return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
	}
	
	@Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4,
            boolean par5) {
        if (entity instanceof EntityLiving) {
            EntityLiving living = (EntityLiving) entity;

            if (living instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) living;

                for (int i = 0; i <= 8; i++) {
                    ItemStack item = player.inventory.getStackInSlot(i);

                    if (item != null && item == stack
                            && item.getItemDamage() <= item.getMaxDamage()) {
                        if(GeneralUtil.isClient())
                        {
                            if(player.inventory.hasItem(SCObjects.astralenergycell.itemID))
                            {
                                if(!player.capabilities.isFlying)
                                {
                                    if(FMLClientHandler.instance().getClient().inGameHasFocus)
                                    {
                                        if(stack.getItemDamage() >= stack.getMaxDamage())
                                        {
                                            player.inventory.consumeInventoryItem(stack.itemID);
                                        }
                                        
                                        if(Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindJump.keyCode) && !Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindSneak.keyCode))
                                        {
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
                                                            
                                                            if(cell.getEnergy(stack2) < 999)
                                                            {
                                                                player.motionY = 0.3f;
                                                                for(int k = 0; k < 4; k++)
                                                                {
                                                                    int l = 3;
                                                                    
                                                                    world.spawnParticle("smoke", player.posX + (world.rand.nextGaussian() / l), player.posY - 1.5, player.posZ + (world.rand.nextGaussian() / l), 0.0f, 0.05f, 0.0f);
                                                                    world.spawnParticle("smoke", player.posX - (world.rand.nextGaussian() / l), player.posY - 1.5, player.posZ - (world.rand.nextGaussian() / l), 0.0f, 0.05f, 0.0f);
                                                                    world.spawnParticle("smoke", player.posX + (world.rand.nextGaussian() / l), player.posY - 1.5, player.posZ + (world.rand.nextGaussian() / l), 0.0f, 0.05f, 0.0f);
                                                                    world.spawnParticle("smoke", player.posX - (world.rand.nextGaussian() / l), player.posY - 1.5, player.posZ - (world.rand.nextGaussian() / l), 0.0f, 0.05f, 0.0f);
                                                                }
                                                                
                                                                cell.setEnergy(stack2, cell.getEnergy(stack2) + 2);
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        
                                        if(!Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindJump.keyCode) && Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindSneak.keyCode) && player.fallDistance != 0.0f)
                                        {
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
                                                            
                                                            if(cell.getEnergy(stack2) < 1000)
                                                            {
                                                                player.motionY = player.motionY / 2;
                                                                player.fallDistance = 0.0f;
                                                                for(int k = 0; k < 4; k++)
                                                                {
                                                                    int l = 3;
                                                                    
                                                                    world.spawnParticle("smoke", player.posX + (world.rand.nextGaussian() / l), player.posY - 1.5, player.posZ + (world.rand.nextGaussian() / l), 0.0f, 0.05f, 0.0f);
                                                                    world.spawnParticle("smoke", player.posX - (world.rand.nextGaussian() / l), player.posY - 1.5, player.posZ - (world.rand.nextGaussian() / l), 0.0f, 0.05f, 0.0f);
                                                                    world.spawnParticle("smoke", player.posX + (world.rand.nextGaussian() / l), player.posY - 1.5, player.posZ + (world.rand.nextGaussian() / l), 0.0f, 0.05f, 0.0f);
                                                                    world.spawnParticle("smoke", player.posX - (world.rand.nextGaussian() / l), player.posY - 1.5, player.posZ - (world.rand.nextGaussian() / l), 0.0f, 0.05f, 0.0f);
                                                                }
                                                                
                                                                cell.setEnergy(stack2, cell.getEnergy(stack2) + 1);
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        
                                        if(Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindJump.keyCode) && Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindSneak.keyCode))
                                        {
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
                                                            
                                                            if(cell.getEnergy(stack2) < 1000)
                                                            {
                                                                player.motionY = 0.0f;
                                                                player.fallDistance = 0.0f;
                                                                for(int k = 0; k < 4; k++)
                                                                {
                                                                    int l = 3;
                                                                    
                                                                    world.spawnParticle("smoke", player.posX + (world.rand.nextGaussian() / l), player.posY - 1.5, player.posZ + (world.rand.nextGaussian() / l), 0.0f, 0.05f, 0.0f);
                                                                    world.spawnParticle("smoke", player.posX - (world.rand.nextGaussian() / l), player.posY - 1.5, player.posZ - (world.rand.nextGaussian() / l), 0.0f, 0.05f, 0.0f);
                                                                    world.spawnParticle("smoke", player.posX + (world.rand.nextGaussian() / l), player.posY - 1.5, player.posZ + (world.rand.nextGaussian() / l), 0.0f, 0.05f, 0.0f);
                                                                    world.spawnParticle("smoke", player.posX - (world.rand.nextGaussian() / l), player.posY - 1.5, player.posZ - (world.rand.nextGaussian() / l), 0.0f, 0.05f, 0.0f);
                                                                }
                                                                
                                                                cell.setEnergy(stack2, cell.getEnergy(stack2) + 1);
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                
                                if(player.fallDistance != 0.0f && !Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindJump.keyCode))
                                {
                                    int k = 4;
                                    
                                    world.spawnParticle("smoke", player.posX + (world.rand.nextGaussian() / k), player.posY + (world.rand.nextGaussian() / 2), player.posZ + (world.rand.nextGaussian() / k), 0.0f, 0.0f, 0.0f);
                                    world.spawnParticle("smoke", player.posX - (world.rand.nextGaussian() / k), player.posY - (world.rand.nextGaussian() / 2), player.posZ - (world.rand.nextGaussian() / k), 0.0f, 0.0f, 0.0f);
                                }
                                
                                break;
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
        par3List.add("Fire (Momentum)");
    }
}
