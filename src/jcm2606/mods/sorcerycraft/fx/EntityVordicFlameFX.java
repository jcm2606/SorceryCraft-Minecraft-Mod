package jcm2606.mods.sorcerycraft.fx;

import jcm2606.mods.jccore.EntityFXJC;
import jcm2606.mods.sorcerycraft.lib.Reference;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityVordicFlameFX extends EntityFXJC
{
    float particleScaleOverTime;

    public EntityVordicFlameFX(World par1World, double f, double f2, double f4, double par8, double par10, double par12)
    {
        super(par1World, f, f2, f4, par8, par10, par12);
        this.motionX = this.motionX * 0.009999999776482582D + par8;
        this.motionY = this.motionY * 0.009999999776482582D + par10;
        this.motionZ = this.motionZ * 0.009999999776482582D + par12;
        double var10000 = f + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
        var10000 = f2 + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
        var10000 = f4 + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
        this.particleScaleOverTime = this.particleScale = (this.rand.nextFloat() * 0.5F + 0.5F) * 1.5f;
        this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
        this.particleMaxAge = 20;
        this.noClip = true;
        this.blendMode = 1;
        this.setParticleTextureIndex(16);
    }

    @Override
	public void renderParticle(Tessellator tessellator, float f, float f1,
			float f2, float f3, float f4, float f5) {
        this.setAlphaF(1.0f - ((float) ((this.particleAge / 2.0) / 10.0)));
        super.drawParticle(Reference.SPRITE_SHEET_PARTICLES, tessellator, f, f1, f2, f3, f4, f5);
    }

    @Override
	public int getBrightnessForRender(float par1)
    {
        float var2 = (this.particleAge + par1) / this.particleMaxAge;

        if (var2 < 0.0F)
        {
            var2 = 0.0F;
        }

        if (var2 > 1.0F)
        {
            var2 = 1.0F;
        }

        int var3 = super.getBrightnessForRender(par1);
        int var4 = var3 & 255;
        int var5 = var3 >> 16 & 255;
        var4 += (int)(var2 * 15.0F * 16.0F);

        if (var4 > 240)
        {
            var4 = 240;
        }

        return var4 | var5 << 16;
    }

    /**
     * Gets how bright this entity is.
     */
    @Override
	public float getBrightness(float par1)
    {
        float var2 = (this.particleAge + par1) / this.particleMaxAge;

        if (var2 < 0.0F)
        {
            var2 = 0.0F;
        }

        if (var2 > 1.0F)
        {
            var2 = 1.0F;
        }

        float var3 = super.getBrightness(par1);
        return var3 * var2 + (1.0F - var2);
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
	public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9599999785423279D;
        this.motionY *= 0.9599999785423279D;
        this.motionZ *= 0.9599999785423279D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}
