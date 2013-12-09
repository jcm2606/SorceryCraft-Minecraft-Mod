package jcm2606.mods.sorcerycraft.core;

import jcm2606.mods.sorcerycraft.api.IAbility;
import jcm2606.mods.sorcerycraft.astral.ability.AstralAbilityBase;
import jcm2606.mods.sorcerycraft.research.ResearchData;
import jcm2606.mods.sorcerycraft.skill.SkillData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
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
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
        
        if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(AstralAbilityBase.NAME) == null)
        {
            event.entity.registerExtendedProperties(AstralAbilityBase.NAME, new AstralAbilityBase((EntityPlayer) event.entity));
        }
        
        if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(SkillData.NAME) == null)
        {
            event.entity.registerExtendedProperties(SkillData.NAME, new SkillData((EntityPlayer) event.entity));
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
                
                ((ResearchData) player.getExtendedProperties(ResearchData.NAME)).syncExtendedProperties();
                
                playerData = SorceryCraft.proxy.getAbilities(player.username);
                
                if (playerData != null)
                {
                    ((AstralAbilityBase) player.getExtendedProperties(AstralAbilityBase.NAME)).loadNBTData(playerData);
                }
                
                for (IAbility ability : ((AstralAbilityBase) player.getExtendedProperties(AstralAbilityBase.NAME)).list)
                {
                    ((AstralAbilityBase) player.getExtendedProperties(AstralAbilityBase.NAME)).syncLearn(ability);
                }
                
                playerData = SorceryCraft.proxy.getSkillPoints(player.username);
                
                if (playerData != null)
                {
                    ((SkillData) player.getExtendedProperties(SkillData.NAME)).loadNBTData(playerData);
                }
                
                ((SkillData) player.getExtendedProperties(SkillData.NAME)).sync();
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
            
            playerData = new NBTTagCompound();
            
            ((AstralAbilityBase) event.entity.getExtendedProperties(AstralAbilityBase.NAME)).saveNBTData(playerData);
            
            SorceryCraft.proxy.saveAbilities(((EntityPlayer) event.entity).username, playerData);
            
            playerData = new NBTTagCompound();
            
            ((SkillData) event.entity.getExtendedProperties(SkillData.NAME)).saveNBTData(playerData);
            
            SorceryCraft.proxy.saveSkillPoints(((EntityPlayer) event.entity).username, playerData);
        }
    }
    
    @ForgeSubscribe
    public void onPlayerInteract(PlayerInteractEvent event)
    {
    }
    
    @ForgeSubscribe
    public void onItemThrow(ItemTossEvent event)
    {
    }
    
    @ForgeSubscribe
    @SideOnly(Side.CLIENT)
    public void onRenderWorldLast(RenderWorldLastEvent event)
    {
    }
}
