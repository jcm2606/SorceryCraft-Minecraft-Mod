package jcm2606.mods.sorcerycraft.item.main;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMagmaStone extends ItemVordicDevice {
	public ItemMagmaStone(int par1) {
		super(par1, "stoneScoria");
		this.setMaxStackSize(1);
		this.setNoRepair();
		this.setMaxDamage(256);
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		return false;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean j)
	{
		if(entity instanceof EntityLiving)
		{
			EntityLiving living = (EntityLiving) entity;
			if(entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) living;
				
				if(player.dimension == -1)
				{
					stack.damageItem(-1, player);
				}
			}
		}
	}
	
	public void performEffects(World world, EntityPlayer player, int x, int y, int z)
	{
		if(!world.isRemote)
		{
			world.playSoundAtEntity(player, "random.fizz", 0.25f, 1.0f);
		}
		
		Random rand = new Random();
		
		for (int k = 0; k < 8; k++) {
			world.spawnParticle("smoke", x + 0.5 + (rand.nextGaussian() / 4),
					y + 0.9,
					z + 0.5 + (rand.nextGaussian() / 4), 0, 0.00000001,
					0);
			world.spawnParticle("smoke", x + 0.5 - (rand.nextGaussian() / 4),
					y + 0.9,
					z + 0.5 - (rand.nextGaussian() / 4), 0, 0.00000001, 0);
		}
		
		world.spawnParticle("largeexplode", x + 0.5,
				y + 0.5,
				z + 0.5, 0, 0, 0);
	}
}
