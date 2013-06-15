package jcm2606.mods.sorcerycraft.fx;

import jcm2606.mods.jccore.EntityFXJC;
import jcm2606.mods.sorcerycraft.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityAstralEnergyFX extends EntityFXJC
{
    float particleScaleOverTime;
    
    private final boolean hasTrail;
    
    private int maxAge;
    
    public double x;
    public double y;
    public double z;

    public EntityAstralEnergyFX(World par1World, double f, double f2, double f4, double par8, double par10, double par12, boolean par13, int par14)
    {
        super(par1World, f, f2, f4, 0.0, 0.0, 0.0);
        this.blendMode = 771;
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.x = par8;
        this.y = par10;
        this.z = par12;
        this.particleScaleOverTime = this.particleScale = 2.5f;
        this.particleRed = this.particleGreen = this.particleBlue = 0.25F;
        this.particleMaxAge = maxAge = 3 * par14;
        this.noClip = true;
        this.setAlphaF(0.75f);
        this.setParticleTextureIndex(32);
        this.hasTrail = par13;
    }

    @Override
    public void renderParticle(Tessellator tessellator, float f, float f1,
            float f2, float f3, float f4, float f5) {
        super.drawParticle(Reference.SPRITE_SHEET_PARTICLES, tessellator, f, f1, f2, f3, f4, f5);
    }

    @Override
    public int getBrightnessForRender(float par1)
    {
        return 100;
    }

    /**
     * Gets how bright this entity is.
     */
    @Override
    public float getBrightness(float par1)
    {
        return 1.0f;
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
        double d = Minecraft.getMinecraft().thePlayer.posX;
        double d1 = Minecraft.getMinecraft().thePlayer.posY;
        double d2 = Minecraft.getMinecraft().thePlayer.posZ;
        double d4 = (d - this.posX) / 5.0D;
        double d5 = (d1 - this.posY) / 5.0D;
        double d6 = (d2 - this.posZ) / 5.0D;

        this.posX += d4;
        this.posY += d5;
        this.posZ += d6;
        
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        
        this.motionY -= 0.04D * this.particleGravity;
        
        moveEntity(this.motionX, this.motionY, this.motionZ);

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }
        
        if(hasTrail)
        {
            EntityAstralEnergyFX fx = new EntityAstralEnergyFX(this.worldObj, this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ, false, 30);
            
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
        }
    }
}
