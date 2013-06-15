package jcm2606.mods.sorcerycraft.event;

import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.enchant.Enchantments;
import jcm2606.mods.sorcerycraft.item.charm.ItemCharmMortality;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class PlayerHandler {
	@ForgeSubscribe
	public void onPlayerInteract(PlayerInteractEvent event) {}
	
	@ForgeSubscribe
	public void onPlayerAttackEntity(AttackEntityEvent event) {
		EntityPlayer player = event.entityPlayer;
		
		if(event.target instanceof EntityLiving)
		{
			enchantmentHandling(player, (EntityLiving) event.target);
		}
	}
	
	public void enchantmentHandling(EntityPlayer player, EntityLiving target)
	{
	    // Frozen Core
	    
	    if(EnchantmentHelper.getEnchantmentLevel(Enchantments.frozencore.effectId, player.getCurrentEquippedItem()) == 1)
        {
	        target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 400, 1));
        }
        
        if(EnchantmentHelper.getEnchantmentLevel(Enchantments.frozencore.effectId, player.getCurrentEquippedItem()) >= 2)
        {
            target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 800, 2));
        }
        
        // Venom Aspect
        
        if(EnchantmentHelper.getEnchantmentLevel(Enchantments.venom.effectId, player.getCurrentEquippedItem()) == 1)
        {
            target.addPotionEffect(new PotionEffect(Potion.poison.getId(), 100, 1));
        }
        
        if(EnchantmentHelper.getEnchantmentLevel(Enchantments.venom.effectId, player.getCurrentEquippedItem()) == 2)
        {
            target.addPotionEffect(new PotionEffect(Potion.poison.getId(), 200, 1));
        }
        
        if(EnchantmentHelper.getEnchantmentLevel(Enchantments.venom.effectId, player.getCurrentEquippedItem()) >= 3)
        {
            target.addPotionEffect(new PotionEffect(Potion.poison.getId(), 400, 2));
        }
	}
	
	@ForgeSubscribe
	public void onItemThrow(ItemTossEvent event)
	{
		if(event.entityItem.getEntityItem() != null)
		{
			if(event.entityItem.getEntityItem().getItem() == SCObjects.charmhealth)
			{
				Item item = event.entityItem.getEntityItem().getItem();
				ItemCharmMortality charm = (ItemCharmMortality) item;
				
				int randInt = event.player.worldObj.rand.nextInt(100);
				
				if(charm.getBoundPlayer(event.entityItem.getEntityItem()).equals(event.player.username) && randInt <= 50 && !charm.getCurseName(event.entityItem.getEntityItem()).equals("disarm"))
				{
					charm.setBoundPlayer(event.entityItem.getEntityItem(), "");
					
					int damageAmount = (charm.getStoredHealth(event.entityItem.getEntityItem()) / 2) + event.player.worldObj.rand.nextInt(3);
					
					event.player.attackEntityFrom(DamageSource.magic, damageAmount);
					System.out.println("PLAYER '" + event.player.username.toUpperCase() + "' DAMAGED BY THROWING CHARM @ SLOT " + event.player.inventory.currentItem + " BY " + damageAmount + "HP");
					
					if(!event.player.worldObj.isRemote)
					{
						event.player.addChatMessage("\247oYou feel a strange surge of energy being ripped from within you.");
					}
				}
			}
		}
	}
}
