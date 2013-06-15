package jcm2606.mods.sorcerycraft.item.wand;

import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.item.wand.behaviour.Behaviour;
import jcm2606.mods.sorcerycraft.item.wand.behaviour.BehaviourGrowth;
import jcm2606.mods.sorcerycraft.item.wand.behaviour.BehaviourTool;
import jcm2606.mods.sorcerycraft.item.wand.behaviour.BehaviourTransmutationLocked;
import jcm2606.mods.sorcerycraft.item.wand.behaviour.BehaviourVordicTool;
import jcm2606.mods.sorcerycraft.item.wand.behaviour.IBlockInteractionHandler;
import jcm2606.mods.sorcerycraft.item.wand.behaviour.IEntityInteractionHandler;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WandManager {
	public static Behaviour[] behaviourList = new Behaviour[256];
	
	public static void loadBehaviours()
	{
		registerWandBehaviour(BehaviourTransmutationLocked.class);
		registerWandBehaviour(BehaviourTool.class);
		registerWandBehaviour(BehaviourGrowth.class);
		registerWandBehaviour(BehaviourVordicTool.class);
	}
	
	public static int getNextAvailableBehaviourId()
	{
	    if(behaviourList[255] != null)
	    {
	        throw new RuntimeException("No more available wand behaviour ids.");
	    }
	    
	    for(int i = 0; i < 256; i++)
	    {
	        if(behaviourList[i + 1] == null)
	        {
	            return i;
	        }
	    }
	    
	    return 0;
	}
	
	public static void registerWandBehaviour(Class<?>[] clas)
	{
		for(Class<?> clazz : clas)
		{
			registerWandBehaviour(clazz);
		}
	}
	
	public static void registerWandBehaviour(Class<?> clas)
	{
		Object obj = null;
		try {
			obj = clas.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		if(obj == null)
		{
			return;
		}
		
		if(obj instanceof Behaviour)
		{
			Behaviour behaviour = (Behaviour) obj;
			if(behaviourList[behaviour.id] == null)
			{
				behaviourList[behaviour.id] = behaviour;
			} else {
				throw new RuntimeException("Behaviour slot " + behaviour.id + " already occupied by behaviour " + behaviourList[behaviour.id].getBehaviourClass().getClass().getSimpleName() + " when adding " + behaviour.getBehaviourClass().getClass().getSimpleName() + ".");
			}
		}
	}
	
	public static Behaviour getBehaviour(int id)
	{
		if(behaviourList[id] != null)
		{
			return behaviourList[id];
		}
		
		return null;
	}
	
	public static boolean performCastingWandBlockInteractionEffect(ItemStack stack, World world, EntityPlayer player, int x, int y, int z)
	{
		if(world.getBlockId(x, y, z) == SCObjects.stonepodium.blockID)
		{
			world.setBlock(x, y, z, SCObjects.alchpodium.blockID);
			stack.damageItem(1, player);
			
			int var6 = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

	        if (var6 == 0) {
	            world.setBlockMetadataWithNotify(x, y, z, 2, 0);
	        }

	        if (var6 == 1) {
	            world.setBlockMetadataWithNotify(x, y, z, 5, 0);
	        }

	        if (var6 == 2) {
	            world.setBlockMetadataWithNotify(x, y, z, 3, 0);
	        }

	        if (var6 == 3) {
	            world.setBlockMetadataWithNotify(x, y, z, 4, 0);
	        }
			
			return true;
		}
		
		return false;
	}
	
	public static boolean performCastingWandEntityInteractionEffect(ItemStack stack, World world, EntityLiving entity)
	{
		return false;
	}
	
	public static boolean performBehaviour(ItemStack stack, World world, EntityPlayer player, int x, int y, int z, Behaviour behaviour)
	{
		// Block interaction handling
		if(behaviour.getBehaviourType() == EnumWandBehaviour.blockInteract)
		{
			IBlockInteractionHandler blockInteractHandler = (IBlockInteractionHandler) behaviour.getBehaviourClass();
			
			if(blockInteractHandler.getBlock(stack, player) != null)
			{
				if(world.getBlockId(x, y, z) == blockInteractHandler.getBlock(stack, player).blockID)
				{
					if(blockInteractHandler.reqItem(stack) != null)
					{
						if(player.inventory.hasItem(blockInteractHandler.reqItem(stack).itemID))
						{
							for(int i = 0; i < player.inventory.getHotbarSize(); i++)
							{
								ItemStack itemStack = player.inventory.getStackInSlot(i);
								
								if(itemStack != null)
								{
									if(itemStack.getItem() == blockInteractHandler.reqItem(stack))
									{
										if(blockInteractHandler.reqItem(stack).isDamageable())
										{
											Item reqItem = blockInteractHandler.reqItem(stack);
											
											if(!reqItem.getHasSubtypes())
											{
												if(itemStack.getItemDamage() == itemStack.getMaxDamage() && reqItem != SCObjects.alchstone)
												{
													player.inventory.consumeInventoryItem(reqItem.itemID);
												}
											}
										}
										
										return blockInteractHandler.onBlockInteract(stack, itemStack, player, world, x, y, z);
									}
								}
							}
						}
					} else {
						return blockInteractHandler.onBlockInteract(stack, null, player, world, x, y, z);
					}
				}
			} else {
				if(blockInteractHandler.reqItem(stack) != null)
				{
					if(player.inventory.hasItem(blockInteractHandler.reqItem(stack).itemID))
					{
						for(int i = 0; i < player.inventory.getHotbarSize(); i++)
						{
							ItemStack itemStack = player.inventory.getStackInSlot(i);
							
							if(itemStack != null)
							{
								if(itemStack.getItem() == blockInteractHandler.reqItem(stack))
								{
									if(blockInteractHandler.reqItem(stack).isDamageable())
									{
										Item reqItem = blockInteractHandler.reqItem(stack);
										
										if(!reqItem.getHasSubtypes())
										{
											if(itemStack.getItemDamage() == itemStack.getMaxDamage() && reqItem != SCObjects.alchstone)
											{
												player.inventory.consumeInventoryItem(reqItem.itemID);
											}
										}
									}
									
									return blockInteractHandler.onBlockInteract(stack, itemStack, player, world, x, y, z);
								}
							}
						}
					}
				} else {
					return blockInteractHandler.onBlockInteract(stack, null, player, world, x, y, z);
				}
			}
		}
				
		return false;
	}
	
	public static boolean performBehaviourEntity(ItemStack stack, EntityLiving living, Behaviour behaviour, World world)
	{
		if(behaviour.getBehaviourType() == EnumWandBehaviour.entityInteract)
		{
			IEntityInteractionHandler entityInteractHandler = (IEntityInteractionHandler) behaviour.getBehaviourClass();
		
			if(living != null)
			{
				if(entityInteractHandler.getEntity(stack, world) != null)
				{
					if(living == entityInteractHandler.getEntity(stack, world))
					{
						entityInteractHandler.onEntityInteract(stack, world, living);
					}
				} else {
					entityInteractHandler.onEntityInteract(stack, world, living);
				}
			}
			
			return false;
		}
				
		return false;
	}
}
