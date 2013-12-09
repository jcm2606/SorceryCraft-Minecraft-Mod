package jcm2606.mods.sorcerycraft.client.fx;

import java.awt.Color;
import java.util.Iterator;

import jcm2606.mods.jccore.core.util.RenderUtil;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import codechicken.lib.vec.Vector3;

public class FXLightningBolt extends EntityFX
{
    private int type = 0;
    
    private float width = 0.03F;
    private FXLightningBoltCommon main;
    
    private float colourOutsideR = 0.8f;
    private float colourOutsideG = 0.8f;
    private float colourOutsideB = 1f;
    
    private float colourInsideR = 0.9f;
    private float colourInsideG = 0.9f;
    private float colourInsideB = 0.9f;
    
    private int blendModeOutside = 1;
    private int blendModeInside = 771;
    
    public FXLightningBolt(World world, WRVector3 jammervec, WRVector3 targetvec, long seed)
    {
        super(world, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.main = new FXLightningBoltCommon(world, jammervec, targetvec, seed);
        setupFromMain();
    }
    
    public FXLightningBolt(World world, Entity detonator, Entity target, long seed)
    {
        super(world, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.main = new FXLightningBoltCommon(world, detonator, target, seed);
        setupFromMain();
    }
    
    public FXLightningBolt(World world, Entity detonator, Entity target, long seed, int speed)
    {
        super(world, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.main = new FXLightningBoltCommon(world, detonator, target, seed, speed);
        setupFromMain();
    }
    
    public FXLightningBolt(World world, TileEntity detonator, Entity target, long seed)
    {
        super(world, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.main = new FXLightningBoltCommon(world, detonator, target, seed);
        setupFromMain();
    }
    
    public FXLightningBolt(World world, double x1, double y1, double z1, double x, double y, double z, long seed, int duration, float multi)
    {
        super(world, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.main = new FXLightningBoltCommon(world, x1, y1, z1, x, y, z, seed, duration, multi);
        setupFromMain();
    }
    
    public FXLightningBolt(World world, double x1, double y1, double z1, double x, double y, double z, long seed, int duration, float multi, int speed)
    {
        super(world, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.main = new FXLightningBoltCommon(world, x1, y1, z1, x, y, z, seed, duration, multi, speed);
        setupFromMain();
    }
    
    public FXLightningBolt(World world, Vector3 startVec, Vector3 endVec, int duration, int speed, float multi)
    {
        super(world, 0, 0, 0, 0, 0, 0);
        this.main = new FXLightningBoltCommon(world, startVec, endVec, duration, speed, multi);
        this.setupFromMain();
    }
    
    public FXLightningBolt(World world, double x1, double y1, double z1, double x, double y, double z, long seed, int duration)
    {
        super(world, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.main = new FXLightningBoltCommon(world, x1, y1, z1, x, y, z, seed, duration, 1.0F);
        setupFromMain();
    }
    
    public FXLightningBolt(World world, TileEntity detonator, double x, double y, double z, long seed)
    {
        super(world, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.main = new FXLightningBoltCommon(world, detonator, x, y, z, seed);
        setupFromMain();
    }
    
    private void setupFromMain()
    {
        this.particleAge = this.main.particleMaxAge;
        setPosition(this.main.start.x, this.main.start.y, this.main.start.z);
        setVelocity(0.0D, 0.0D, 0.0D);
    }
    
    public void setColours(float oR, float oG, float oB, float iR, float iG, float iB)
    {
        this.colourOutsideR = oR;
        this.colourOutsideG = oG;
        this.colourOutsideB = oB;
        
        this.colourInsideR = iR;
        this.colourInsideG = iG;
        this.colourInsideB = iB;
    }
    
    public void setColours(int outter, int inner)
    {
        Color outterC = new Color(outter);
        Color innerC = new Color(inner);
        
        this.setColours(outterC.getRed() / 255f, outterC.getGreen() / 255f, outterC.getBlue() / 255f, innerC.getRed() / 255f, innerC.getGreen() / 255f, innerC.getBlue() / 255f);
    }
    
    public void setBlendMode(int mode, int mode2)
    {
        this.blendModeOutside = mode;
        this.blendModeInside = mode2;
    }
    
    public void defaultFractal()
    {
        this.main.defaultFractal();
    }
    
    public void fractal(int splits, float amount, float splitchance, float splitlength, float splitangle)
    {
        this.main.fractal(splits, amount, splitchance, splitlength, splitangle);
    }
    
    public void realisticFractal()
    {
        this.main.realisticFractal();
    }
    
    public float getLength()
    {
        return this.main.length;
    }
    
    public void finalizeBolt()
    {
        this.main.finalizeBolt();
        ModLoader.getMinecraftInstance().effectRenderer.addEffect(this);
    }
    
    public void setType(int type)
    {
        this.type = type;
        this.main.type = type;
    }
    
    public void setMultiplier(float m) {
        this.main.multiplier = m;
    }
    
    public void setWidth(float m) {
        this.width = m;
    }
    
    @Override
    public void onUpdate()
    {
        this.main.onUpdate();
        if (this.main.particleAge >= this.main.particleMaxAge)
        {
            setDead();
        }
    }
    
    private static WRVector3 getRelativeViewVector(WRVector3 pos)
    {
        EntityPlayer renderentity = ModLoader.getMinecraftInstance().thePlayer;
        return new WRVector3((float)renderentity.posX - pos.x, (float)renderentity.posY - pos.y, (float)renderentity.posZ - pos.z);
    }
    
    private void renderBolt(Tessellator tessellator, float partialframe, float cosyaw, float cospitch, float sinyaw, float cossinpitch, int pass)
    {
        WRVector3 playervec = new WRVector3(sinyaw * -cospitch, -cossinpitch / cosyaw, cosyaw * cospitch);
        float boltage = this.main.particleAge >= 0 ? this.main.particleAge / this.main.particleMaxAge : 0.0F;
        float mainalpha = 1.0F;
        if (pass == 0)
        {
            mainalpha = (1.0F - boltage) * 0.4F;
        } else
        {
            mainalpha = 1.0F - boltage * 0.5F;
        }
        int renderlength = (int)((this.main.particleAge + partialframe + (int)(this.main.length * 3.0F)) / (int)(this.main.length * 3.0F) * this.main.numsegments0);
        for (Iterator iterator = this.main.segments.iterator(); iterator.hasNext(); )
        {
            FXLightningBoltCommon.Segment rendersegment = (FXLightningBoltCommon.Segment)iterator.next();
            if (rendersegment.segmentno <= renderlength)
            {
                float width = this.width * (getRelativeViewVector(rendersegment.startpoint.point).length() / 5.0F + 1.0F) * (1.0F + rendersegment.light) * 0.5F;
                WRVector3 diff1 = WRVector3.crossProduct(playervec, rendersegment.prevdiff).scale(width / rendersegment.sinprev);
                WRVector3 diff2 = WRVector3.crossProduct(playervec, rendersegment.nextdiff).scale(width / rendersegment.sinnext);
                WRVector3 startvec = rendersegment.startpoint.point;
                WRVector3 endvec = rendersegment.endpoint.point;
                float rx1 = (float)(startvec.x - interpPosX);
                float ry1 = (float)(startvec.y - interpPosY);
                float rz1 = (float)(startvec.z - interpPosZ);
                float rx2 = (float)(endvec.x - interpPosX);
                float ry2 = (float)(endvec.y - interpPosY);
                float rz2 = (float)(endvec.z - interpPosZ);
                tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, mainalpha * rendersegment.light);
                tessellator.addVertexWithUV(rx2 - diff2.x, ry2 - diff2.y, rz2 - diff2.z, 0.5D, 0.0D);
                tessellator.addVertexWithUV(rx1 - diff1.x, ry1 - diff1.y, rz1 - diff1.z, 0.5D, 0.0D);
                tessellator.addVertexWithUV(rx1 + diff1.x, ry1 + diff1.y, rz1 + diff1.z, 0.5D, 1.0D);
                tessellator.addVertexWithUV(rx2 + diff2.x, ry2 + diff2.y, rz2 + diff2.z, 0.5D, 1.0D);
                if (rendersegment.next == null)
                {
                    WRVector3 roundend = rendersegment.endpoint.point.copy().add(rendersegment.diff.copy().normalize().scale(width));
                    float rx3 = (float)(roundend.x - interpPosX);
                    float ry3 = (float)(roundend.y - interpPosY);
                    float rz3 = (float)(roundend.z - interpPosZ);
                    tessellator.addVertexWithUV(rx3 - diff2.x, ry3 - diff2.y, rz3 - diff2.z, 0.0D, 0.0D);
                    tessellator.addVertexWithUV(rx2 - diff2.x, ry2 - diff2.y, rz2 - diff2.z, 0.5D, 0.0D);
                    tessellator.addVertexWithUV(rx2 + diff2.x, ry2 + diff2.y, rz2 + diff2.z, 0.5D, 1.0D);
                    tessellator.addVertexWithUV(rx3 + diff2.x, ry3 + diff2.y, rz3 + diff2.z, 0.0D, 1.0D);
                }
                if (rendersegment.prev == null)
                {
                    WRVector3 roundend = rendersegment.startpoint.point.copy().sub(rendersegment.diff.copy().normalize().scale(width));
                    float rx3 = (float)(roundend.x - interpPosX);
                    float ry3 = (float)(roundend.y - interpPosY);
                    float rz3 = (float)(roundend.z - interpPosZ);
                    tessellator.addVertexWithUV(rx1 - diff1.x, ry1 - diff1.y, rz1 - diff1.z, 0.5D, 0.0D);
                    tessellator.addVertexWithUV(rx3 - diff1.x, ry3 - diff1.y, rz3 - diff1.z, 0.0D, 0.0D);
                    tessellator.addVertexWithUV(rx3 + diff1.x, ry3 + diff1.y, rz3 + diff1.z, 0.0D, 1.0D);
                    tessellator.addVertexWithUV(rx1 + diff1.x, ry1 + diff1.y, rz1 + diff1.z, 0.5D, 1.0D);
                }
            }
        }
    }
    
    @Override
    public void renderParticle(Tessellator tessellator, float partialframe, float cosyaw, float cospitch, float sinyaw, float sinsinpitch, float cossinpitch)
    {
        EntityPlayer renderentity = ModLoader.getMinecraftInstance().thePlayer;
        int visibleDistance = 100;
        if (!ModLoader.getMinecraftInstance().gameSettings.fancyGraphics)
        {
            visibleDistance = 50;
        }
        if (renderentity.getDistance(this.posX, this.posY, this.posZ) > visibleDistance)
        {
            return;
        }
        
        tessellator.draw();
        GL11.glPushMatrix();
        
        GL11.glDepthMask(false);
        GL11.glEnable(3042);
        
        this.particleRed = this.colourOutsideR;
        this.particleGreen = this.colourOutsideG;
        this.particleBlue = this.colourOutsideB;
        GL11.glBlendFunc(770, this.blendModeOutside);
        
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_FX + "lightning_big.png");
        tessellator.startDrawingQuads();
        tessellator.setBrightness(983280);
        renderBolt(tessellator, partialframe, cosyaw, cospitch, sinyaw, cossinpitch, 0);
        tessellator.draw();
        
        this.particleRed = this.colourInsideR;
        this.particleGreen = this.colourInsideG;
        this.particleBlue = this.colourInsideB;
        GL11.glBlendFunc(770, this.blendModeInside);
        
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_FX + "lightning_small.png");
        tessellator.startDrawingQuads();
        tessellator.setBrightness(983280);
        renderBolt(tessellator, partialframe, cosyaw, cospitch, sinyaw, cossinpitch, 1);
        tessellator.draw();
        
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
        
        RenderUtil.instance().bindTexture("minecraft", "textures/particle/particles.png");
        
        tessellator.startDrawingQuads();
    }
    
    public int getRenderPass()
    {
        return 2;
    }
}