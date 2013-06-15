package jcm2606.mods.sorcerycraft.block;

import java.util.List;
import java.util.Random;

import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEntityTracker extends SCBlock {
	public BlockEntityTracker(int par1) {
		super(par1, Material.rock, "entityTracker", Rarities.BASIC);
	}
	
	@Override
    @SideOnly(Side.CLIENT)

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		List entities = world.getEntitiesWithinAABBExcludingEntity(Minecraft.getMinecraft().thePlayer, AxisAlignedBB.getBoundingBox((double) x - 10, (double) y - 4, (double) z - 10, (double) x + 10, (double) y + 4, (double) z + 10));
	
		for(int i = 0; i < entities.size(); i++)
		{
			Object obj = entities.get(i);
			
			if(obj instanceof Entity)
			{
				Entity entity = (Entity) obj;
				
				if(entity instanceof EntityLiving)
				{
					EntityLiving living = (EntityLiving) entity;
					
					for(int j = 0; j < 2; j++)
                    {
                        double px = living.posX;
                        double py = living.posY + living.height + 0.2;
                        double pz = living.posZ;
                        double vx = x + 0.5 - px + rand.nextFloat() / 2.0F;
                        double vy = y - py + rand.nextFloat() / 2.0F;
                        double vz = z + 0.5 - pz + rand.nextFloat() / 2.0F;
                        world.spawnParticle("portal", px, py, pz, vx, vy, vz);
                    }
				}
			}
		}
	}
}
