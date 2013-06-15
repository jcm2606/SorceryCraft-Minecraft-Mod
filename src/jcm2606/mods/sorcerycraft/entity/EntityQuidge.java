package jcm2606.mods.sorcerycraft.entity;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class EntityQuidge extends EntityMob {
	public EntityQuidge(World par1World) {
		super(par1World);
		this.texture = "/mob/villager/villager.png";
		this.moveSpeed = 0.5f;
	}

	@Override
	public int getMaxHealth() {
		return 10;
	}
}
