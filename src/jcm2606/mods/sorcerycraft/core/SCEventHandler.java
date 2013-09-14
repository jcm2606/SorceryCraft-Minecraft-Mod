package jcm2606.mods.sorcerycraft.core;

import jcm2606.mods.sorcerycraft.item.charm.ItemCharmMortality;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
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
        
        if (id == SCObjects.dustvordic.itemID)
        {
            event.entityPlayer.addStat(SCAchievements.vordicDustGet, 1);
        }
    }
    
    @ForgeSubscribe
    public void onLivingEntityHurt(LivingHurtEvent event)
    {
    }
    
    @ForgeSubscribe
    public void onLivingEntityJoinWorld(EntityJoinWorldEvent event)
    {
        Entity entity = event.entity;
        
        if (entity instanceof EntityLiving)
        {
            EntityLiving living = (EntityLiving) entity;
            
            if (living instanceof EntityZombie)
            {
                EntityZombie zombie = (EntityZombie) living;
                
                int randInt = living.worldObj.rand.nextInt(100);
                
            }
        }
    }
    
    @ForgeSubscribe
    public void onLivingEntityDeath(LivingDeathEvent event)
    {
        EntityLivingBase living = event.entityLiving;
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
            if (event.entityItem.getEntityItem().getItem() == SCObjects.charmhealth)
            {
                Item item = event.entityItem.getEntityItem().getItem();
                ItemCharmMortality charm = (ItemCharmMortality) item;
                
                int randInt = event.player.worldObj.rand.nextInt(100);
                
                if (charm.getBoundPlayer(event.entityItem.getEntityItem()).equals(event.player.username) && randInt <= 50 && !charm.getCurseName(
                        event.entityItem.getEntityItem()).equals("disarm"))
                {
                    charm.setBoundPlayer(event.entityItem.getEntityItem(), "");
                    
                    int damageAmount = (charm.getStoredHealth(event.entityItem.getEntityItem()) / 2) + event.player.worldObj.rand.nextInt(3);
                    
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
