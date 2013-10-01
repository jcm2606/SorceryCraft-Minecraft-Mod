package jcm2606.mods.sorcerycraft.core;

import jcm2606.mods.sorcerycraft.item.charm.ItemCharm;
import jcm2606.mods.sorcerycraft.item.charm.ItemCharmMortality;
import jcm2606.mods.sorcerycraft.research.ResearchData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;

public class SCEventHandler
{
    public static NBTTagCompound worldNBT;
    
    @ForgeSubscribe
    public void onBlockHighlightRender(DrawBlockHighlightEvent event)
    {
    }
    
    @ForgeSubscribe
    public void onEntityInteract(EntityInteractEvent event)
    {
    }
    
    @ForgeSubscribe
    public void onWorldLoad(WorldEvent.Load event)
    {
    }
    
    @ForgeSubscribe
    public void onWorldSave(WorldEvent.Load event)
    {
    }
    
    @ForgeSubscribe
    public void onItemPickup(EntityItemPickupEvent event)
    {
        EntityItem item = event.item;
        int id = item.getEntityItem().itemID;
        
        if (id == SCObjects.dustVordic.itemID)
        {
            event.entityPlayer.addStat(SCAchievements.vordicDustGet, 1);
        }
    }
    
    @ForgeSubscribe
    public void onLivingEntityHurt(LivingHurtEvent event)
    {
    }
    
    @ForgeSubscribe
    public void onEntityConstructing(EntityConstructing event)
    {
        if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(ResearchData.NAME) == null)
        {
            event.entity.registerExtendedProperties(ResearchData.NAME, new ResearchData((EntityPlayer) event.entity));
        }
    }
    
    @ForgeSubscribe
    public void onLivingEntityJoinWorld(EntityJoinWorldEvent event)
    {
        Entity entity = event.entity;
        
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase living = (EntityLivingBase) entity;
            
            if (living instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) living;
                
                NBTTagCompound playerData = SorceryCraft.proxy.getResearchPoints(player.username);
                
                if (playerData != null)
                {
                    ((ResearchData) player.getExtendedProperties(ResearchData.NAME)).loadNBTData(playerData);
                }
                
                if (player.worldObj.isRemote)
                {
                    ((ResearchData) player.getExtendedProperties(ResearchData.NAME)).syncExtendedProperties();
                }
            }
        }
    }
    
    @ForgeSubscribe
    public void onLivingEntityDeath(LivingDeathEvent event)
    {
        EntityLivingBase living = event.entityLiving;
        
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            NBTTagCompound playerData = new NBTTagCompound();
            
            ((ResearchData) event.entity.getExtendedProperties(ResearchData.NAME)).saveNBTData(playerData);
            
            SorceryCraft.proxy.saveResearchPoints(((EntityPlayer) event.entity).username, playerData);
        }
    }
    
    @ForgeSubscribe
    public void onPlayerInteract(PlayerInteractEvent event)
    {
    }
    
    @ForgeSubscribe
    public void onItemThrow(ItemTossEvent event)
    {
        if (event.entityItem.getEntityItem() != null)
        {
            if (event.entityItem.getEntityItem().getItem() == SCObjects.charmHealing)
            {
                Item item = event.entityItem.getEntityItem().getItem();
                ItemCharmMortality charm = (ItemCharmMortality) item;
                
                int randInt = event.player.worldObj.rand.nextInt(100);
                
                if (ItemCharmMortality.getBoundPlayer(event.entityItem.getEntityItem()).equals(event.player.username) && randInt <= 50 && !ItemCharm.getCurseName(
                        event.entityItem.getEntityItem()).equals("disarm"))
                {
                    ItemCharmMortality.setBoundPlayer(event.entityItem.getEntityItem(), "");
                    
                    int damageAmount = (ItemCharmMortality.getStoredHealth(event.entityItem.getEntityItem()) / 2) + event.player.worldObj.rand.nextInt(3);
                    
                    event.player.attackEntityFrom(DamageSource.magic, damageAmount);
                    System.out
                    .println("PLAYER '" + event.player.username.toUpperCase() + "' DAMAGED BY THROWING CHARM @ SLOT " + event.player.inventory.currentItem + " BY " + damageAmount + "HP");
                    
                    if (!event.player.worldObj.isRemote)
                    {
                        event.player.addChatMessage("\247oYou feel a strange surge of energy being ripped from within you.");
                    }
                }
            }
        }
    }
}
