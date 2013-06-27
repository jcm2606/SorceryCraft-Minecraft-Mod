package jcm2606.mods.sorcerycraft;

import jcm2606.mods.sorcerycraft.fx.EntityAstralEnergyFX;
import jcm2606.mods.sorcerycraft.fx.EntitySearFlameFX;
import jcm2606.mods.sorcerycraft.fx.EntityVordicEnergyFX;
import jcm2606.mods.sorcerycraft.fx.EntityVordicFlameFX;
import jcm2606.mods.sorcerycraft.fx.FXAstralEnergyBeam;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderEngine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SCParticle
{
	private static Minecraft mc = Minecraft.getMinecraft();
	private static RenderEngine renderEngine = mc.renderEngine;
	
	public static EntityFX spawnVordicEnergyFX(double x, double y, double z, double motX, double motY, double motZ, boolean handleParticleSettings)
	{
	    EntityFX fx = new EntityVordicEnergyFX(Minecraft.getMinecraft().theWorld, x, y, z, motX, motY, motZ);
	    
	    spawnParticle(fx, handleParticleSettings);
	    return fx;
	}
	
	public static EntityFX spawnVordicFlameFX(double x, double y, double z, double motX, double motY, double motZ, boolean handleParticleSettings)
    {
        EntityFX fx = new EntityVordicFlameFX(Minecraft.getMinecraft().theWorld, x, y, z, motX, motY, motZ);
        
        spawnParticle(fx, handleParticleSettings);
        return fx;
    }
	
	public static EntityFX spawnAstralEnergyFX(double x, double y, double z, double motX, double motY, double motZ, int age, boolean noClip, boolean fade, boolean dieOnTravelCompletion, boolean handleParticleSettings)
    {
	    EntityAstralEnergyFX fx = new EntityAstralEnergyFX(Minecraft.getMinecraft().theWorld, x, y, z, motX, motY, motZ, age);
        fx.noClip = noClip;
        fx.fade = fade;
        fx.dieOnTravelCompletion = dieOnTravelCompletion;
        
        spawnParticle(fx, handleParticleSettings);
        return fx;
    }
	
	public static EntityFX spawnAstralEnergyFX(double x, double y, double z, double motX, double motY, double motZ, int age, int speed, boolean noClip, boolean fade, boolean dieOnTravelCompletion, boolean handleParticleSettings)
	{
	    EntityAstralEnergyFX fx = new EntityAstralEnergyFX(Minecraft.getMinecraft().theWorld, x, y, z, motX, motY, motZ, age, speed);
        fx.noClip = noClip;
        fx.fade = fade;
        fx.dieOnTravelCompletion = dieOnTravelCompletion;
	    
        spawnParticle(fx, handleParticleSettings);
        return fx;
	}
	
	public static EntityFX spawnAstralEnergyBeamFX(double x, double y, double z, double motX, double motY, double motZ, int age, boolean noClip, boolean fade, boolean handleParticleSettings)
    {
        FXAstralEnergyBeam fx = new FXAstralEnergyBeam(Minecraft.getMinecraft().theWorld, x, y, z, motX, motY, motZ, age);
        
        spawnParticle(fx, handleParticleSettings);
        return fx;
    }
	
	public static void spawnSearFlameFX(double x, double y, double z, double motX, double motY, double motZ, boolean handleParticleSettings)
    {
        EntityFX fx = new EntitySearFlameFX(Minecraft.getMinecraft().theWorld, x, y, z, motX, motY, motZ);
        
        spawnParticle(fx, handleParticleSettings);
    }
	
	private static void spawnParticle(EntityFX fx, boolean handleParticleSettings)
	{
	    mc.effectRenderer.addEffect(fx);
	}
}