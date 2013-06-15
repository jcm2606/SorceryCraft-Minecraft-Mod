package jcm2606.mods.sorcerycraft.event;

import jcm2606.mods.sorcerycraft.SCObjects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class LivingEntityHandler {
	@ForgeSubscribe
	public void onLivingEntityHurt(LivingHurtEvent event) {}
	
	@ForgeSubscribe
	public void onLivingEntityJoinWorld(EntityJoinWorldEvent event)
	{
		Entity entity = event.entity;
		
		if(entity instanceof EntityLiving)
		{
			EntityLiving living = (EntityLiving) entity;
			
			if(living instanceof EntityZombie)
			{
				EntityZombie zombie = (EntityZombie) living;
				
				int randInt = living.worldObj.rand.nextInt(100);
				
				if(randInt == 7)
				{
					zombie.setCurrentItemOrArmor(0, new ItemStack(SCObjects.swordend, 1));
				}
				
				randInt = living.worldObj.rand.nextInt(100);
				
				if(randInt == 7)
				{
					zombie.setCurrentItemOrArmor(0, new ItemStack(SCObjects.swordendowment, 1));
				}
			}
		}
	}
	
	@ForgeSubscribe
	public void onLivingEntityDeath(LivingDeathEvent event)
	{
		EntityLiving living = event.entityLiving;
	}
}
