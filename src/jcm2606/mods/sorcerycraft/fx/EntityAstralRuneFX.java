package jcm2606.mods.sorcerycraft.fx;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityAstralRuneFX extends EntityFX
{
    private final float field_70565_a;
    private final double field_70568_aq;
    private final double field_70567_ar;
    private final double field_70566_as;

    public EntityAstralRuneFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        super(par1World, par2, par4, par6, par8, par10, par12);
        this.motionX = par8;
        this.motionY = par10;
        this.motionZ = par12;
        this.field_70568_aq = this.posX = par2;
        this.field_70567_ar = this.posY = par4;
        this.field_70566_as = this.posZ = par6;
        float f = this.rand.nextFloat() * 0.6F + 0.4F;
        this.field_70565_a = this.rand.nextFloat() * 0.5F + 0.2F;
        this.particleRed = 0.1f;
        this.particleGreen = 0.4f;
        this.particleBlue = 1.0f;
        this.particleMaxAge = (int)(Math.random() * 10.0D) + 40;
        this.setSize(0.005F, 0.005F);
        this.noClip = true;
        this.setParticleTextureIndex((int)(Math.random() * 26.0D + 1.0D + 224.0D));
    }
    
    @Override
    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        GL11.glPushMatrix();
        GL11.glDepthMask(false);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 1);
        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, this.particleAlpha / 2.0F);
        
        float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * par2 - interpPosX);
        float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * par2 - interpPosY);
        float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * par2 - interpPosZ);
        
        float f6 = this.particleTextureIndexX / 16.0F;
        float f7 = f6 + 0.0624375F;
        float f8 = this.particleTextureIndexY / 16.0F;
        float f9 = f8 + 0.0624375F;
        float f10 = 0.04F * this.particleScale;

        if (this.particleIcon != null)
        {
            f6 = this.particleIcon.getMinU();
            f7 = this.particleIcon.getMaxU();
            f8 = this.particleIcon.getMinV();
            f9 = this.particleIcon.getMaxV();
        }
        
        float f14 = 0.2F;
        par1Tessellator.setBrightness(240);
        par1Tessellator.setColorRGBA_F(this.particleRed * f14, this.particleGreen * f14, this.particleBlue * f14, this.particleAlpha / 2.0F);
        par1Tessellator.addVertexWithUV(f11 - par3 * f10 - par6 * f10, f12 - par4 * f10, f13 - par5 * f10 - par7 * f10, f7, f9);
        par1Tessellator.addVertexWithUV(f11 - par3 * f10 + par6 * f10, f12 + par4 * f10, f13 - par5 * f10 + par7 * f10, f7, f8);
        par1Tessellator.addVertexWithUV(f11 + par3 * f10 + par6 * f10, f12 + par4 * f10, f13 + par5 * f10 + par7 * f10, f6, f8);
        par1Tessellator.addVertexWithUV(f11 + par3 * f10 - par6 * f10, f12 - par4 * f10, f13 + par5 * f10 - par7 * f10, f6, f9);
    
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
    }

    @Override
    public int getBrightnessForRender(float par1)
    {
        int i = super.getBrightnessForRender(par1);
        float f1 = (float)this.particleAge / (float)this.particleMaxAge;
        f1 *= f1;
        f1 *= f1;
        int j = i & 255;
        int k = i >> 16 & 255;
        k += (int)(f1 * 15.0F * 16.0F);

        if (k > 240)
        {
            k = 240;
        }

        return j | k << 16;
    }

    /**
     * Gets how bright this entity is.
     */
    @Override
    public float getBrightness(float par1)
    {
        float f1 = super.getBrightness(par1);
        float f2 = (float)this.particleAge / (float)this.particleMaxAge;
        f2 *= f2;
        f2 *= f2;
        return f1 * (1.0F - f2) + f2;
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
        float f = (float)this.particleAge / (float)this.particleMaxAge;
        f = 1.0F - f;
        float f1 = 1.0F - f;
        f1 *= f1;
        f1 *= f1;
        this.posX = this.field_70568_aq + this.motionX * f;
        this.posY = this.field_70567_ar + this.motionY * f - f1;
        this.posZ = this.field_70566_as + this.motionZ * f;
        this.setAlphaF(f);

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }
    }
}
