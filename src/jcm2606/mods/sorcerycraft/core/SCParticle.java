package jcm2606.mods.sorcerycraft.core;

import jcm2606.mods.jccore.core.util.Coord;
import jcm2606.mods.sorcerycraft.client.fx.FXAstralEnergy;
import jcm2606.mods.sorcerycraft.client.fx.FXAstralEnergyBeam;
import jcm2606.mods.sorcerycraft.client.fx.FXSearFlame;
import jcm2606.mods.sorcerycraft.client.fx.FXVordicFlame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SCParticle
{
    private static Minecraft mc = Minecraft.getMinecraft();
    
    public static EntityFX spawnVordicFlameFX(double x, double y, double z, double motX, double motY, double motZ, boolean handleParticleSettings)
    {
        EntityFX fx = new FXVordicFlame(Minecraft.getMinecraft().theWorld, x, y, z, motX, motY, motZ);
        
        spawnParticle(fx, handleParticleSettings);
        return fx;
    }
    
    public static EntityFX spawnAstralEnergyFX(double x, double y, double z, double motX, double motY, double motZ, int age, boolean noClip,
            boolean fade, boolean dieOnTravelCompletion, boolean handleParticleSettings)
    {
        FXAstralEnergy fx = new FXAstralEnergy(Minecraft.getMinecraft().theWorld, x, y, z, motX, motY, motZ, age);
        fx.noClip = noClip;
        fx.fade = fade;
        fx.dieOnTravelCompletion = dieOnTravelCompletion;
        
        spawnParticle(fx, handleParticleSettings);
        return fx;
    }
    
    public static EntityFX spawnAstralEnergyFX(double x, double y, double z, double motX, double motY, double motZ, int age, boolean noClip,
            boolean fade, boolean handleParticleSettings)
    {
        FXAstralEnergy fx = new FXAstralEnergy(Minecraft.getMinecraft().theWorld, x, y, z, 0, 0, 0, age);
        fx.motionX = motX;
        fx.motionY = motY;
        fx.motionZ = motZ;
        fx.noClip = noClip;
        fx.fade = fade;
        fx.dieOnTravelCompletion = false;
        
        spawnParticle(fx, handleParticleSettings);
        return fx;
    }
    
    public static EntityFX spawnAstralEnergyFX(double x, double y, double z, double motX, double motY, double motZ, int age, int speed,
            boolean noClip, boolean fade, boolean dieOnTravelCompletion, boolean handleParticleSettings)
    {
        FXAstralEnergy fx = new FXAstralEnergy(Minecraft.getMinecraft().theWorld, x, y, z, motX, motY, motZ, age, speed);
        fx.noClip = noClip;
        fx.fade = fade;
        fx.dieOnTravelCompletion = dieOnTravelCompletion;
        
        spawnParticle(fx, handleParticleSettings);
        return fx;
    }
    
    public static EntityFX spawnAstralEnergyBeamFX(Coord coordStart, Coord coordEnd, int age, boolean noClip, boolean fade,
            boolean handleParticleSettings)
    {
        FXAstralEnergyBeam fx = new FXAstralEnergyBeam(Minecraft.getMinecraft().theWorld, coordStart.x, coordStart.y, coordStart.z, coordEnd.x,
                coordEnd.y, coordEnd.z, age);
        
        spawnParticle(fx, handleParticleSettings);
        return fx;
    }
    
    public static void spawnSearFlameFX(double x, double y, double z, double motX, double motY, double motZ, boolean handleParticleSettings)
    {
        EntityFX fx = new FXSearFlame(Minecraft.getMinecraft().theWorld, x, y, z, motX, motY, motZ);
        
        spawnParticle(fx, handleParticleSettings);
    }
    
    private static void spawnParticle(EntityFX fx, boolean handleParticleSettings)
    {
        mc.effectRenderer.addEffect(fx);
    }
}
