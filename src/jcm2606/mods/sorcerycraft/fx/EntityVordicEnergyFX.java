package jcm2606.mods.sorcerycraft.fx;

import jcm2606.mods.jccore.EntityFXJC;
import jcm2606.mods.sorcerycraft.lib.Reference;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntityVordicEnergyFX extends EntityFXJC {
	float particleScaleOverTime;

	public EntityVordicEnergyFX(World par1World, double par2, double par4,
			double par6, double par8, double par10, double par12) {
		this(par1World, par2, par4, par6, par8, par10, par12, 2.0F);
	}

	public EntityVordicEnergyFX(World par1World, double par2, double par4,
			double par6, double par8, double par10, double par12, float par14) {
		super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
		this.motionX = par8;
		this.motionY = par10;
		this.motionZ = par12;
		this.particleScale *= 0.75F;
		this.particleScale *= par14;
		this.particleScaleOverTime = this.particleScale;
		this.particleMaxAge = 22;
		this.noClip = false;
		this.setParticleTextureIndex(0);
	}

	@Override
	public void renderParticle(Tessellator tessellator, float f, float f1,
			float f2, float f3, float f4, float f5) {
    	super.drawParticle(Reference.SPRITE_SHEET_PARTICLES, tessellator, f, f1, f2, f3, f4, f5);
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (this.particleAge++ >= this.particleMaxAge) {
			this.setDead();
		}
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		if (this.posY == this.prevPosY) {
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}
		this.motionX *= 0.8600000143051147D;
		this.motionY *= 0.8600000143051147D;
		this.motionZ *= 0.8600000143051147D;
		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
		
		if(this.particleAge >= 15)
		{
			this.setParticleTextureIndex(1);
		}
		
		if(this.particleAge >= 18)
		{
			this.setParticleTextureIndex(2);
		}
		
		if(this.particleAge >= 20)
		{
			this.setParticleTextureIndex(3);
		}
	}

}