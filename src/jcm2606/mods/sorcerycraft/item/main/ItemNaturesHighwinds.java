package jcm2606.mods.sorcerycraft.item.main;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.jccore.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.item.SCItem;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;

public class ItemNaturesHighwinds extends SCItem {
	public ItemNaturesHighwinds(int par1) {
		super(par1, "naturesHighwinds");
		this.setMaxStackSize(1);
		this.setMaxDamage(3600);
		this.setNoRepair();
	}
	
	@Override
    public EnumRarity getRarity(ItemStack stack)
	{
	    return RarityHelper.getCustomRarityType(Rarities.MAGICAL);
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
                                        player.motionY = 0.3f;
                                        for(int j = 0; j < 4; j++)
                                        {
                                            int k = 3;
                                            
                                            world.spawnParticle("smoke", player.posX + (world.rand.nextGaussian() / k), player.posY - 1.5, player.posZ + (world.rand.nextGaussian() / k), 0.0f, 0.05f, 0.0f);
                                            world.spawnParticle("smoke", player.posX - (world.rand.nextGaussian() / k), player.posY - 1.5, player.posZ - (world.rand.nextGaussian() / k), 0.0f, 0.05f, 0.0f);
                                            world.spawnParticle("smoke", player.posX + (world.rand.nextGaussian() / k), player.posY - 1.5, player.posZ + (world.rand.nextGaussian() / k), 0.0f, 0.05f, 0.0f);
                                            world.spawnParticle("smoke", player.posX - (world.rand.nextGaussian() / k), player.posY - 1.5, player.posZ - (world.rand.nextGaussian() / k), 0.0f, 0.05f, 0.0f);
                                        }
                                        stack.damageItem(1, player); 
                                    }
                                    
                                    if(!Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindJump.keyCode) && Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindSneak.keyCode) && player.fallDistance != 0.0f)
                                    {
                                        player.motionY = player.motionY / 2;
                                        player.fallDistance = 0.0f;
                                        for(int j = 0; j < 8; j++)
                                        {
                                            int k = 3;
                                            
                                            world.spawnParticle("smoke", player.posX + (world.rand.nextGaussian() / k), player.posY - 1.5, player.posZ + (world.rand.nextGaussian() / k), 0.0f, -0.1f, 0.0f);
                                            world.spawnParticle("smoke", player.posX - (world.rand.nextGaussian() / k), player.posY - 1.5, player.posZ - (world.rand.nextGaussian() / k), 0.0f, -0.1f, 0.0f);
                                        }
                                        stack.damageItem(1, player);
                                    }
                                    
                                    if(Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindJump.keyCode) && Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindSneak.keyCode))
                                    {
                                        player.motionY = 0.0f;
                                        player.fallDistance = 0.0f;
                                        for(int j = 0; j < 8; j++)
                                        {
                                            int k = 3;
                                            
                                            world.spawnParticle("smoke", player.posX + (world.rand.nextGaussian() / k), player.posY - 1.5, player.posZ + (world.rand.nextGaussian() / k), 0.0f, -0.1f, 0.0f);
                                            world.spawnParticle("smoke", player.posX - (world.rand.nextGaussian() / k), player.posY - 1.5, player.posZ - (world.rand.nextGaussian() / k), 0.0f, -0.1f, 0.0f);
                                        }
                                        stack.damageItem(1, player);
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
	
	/**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(((par1ItemStack.getMaxDamage() / 20) - (par1ItemStack.getItemDamage() / 20)) + "s of flight time left.");
    }
}
