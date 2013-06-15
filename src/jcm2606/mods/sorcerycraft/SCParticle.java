package jcm2606.mods.sorcerycraft;

import jcm2606.mods.sorcerycraft.fx.EntityAstralEnergyFX;
import jcm2606.mods.sorcerycraft.fx.EntitySearFlameFX;
import jcm2606.mods.sorcerycraft.fx.EntityVordicEnergyFX;
import jcm2606.mods.sorcerycraft.fx.EntityVordicFlameFX;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderEngine;

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
	
	public static EntityFX spawnAstralEnergyFX(double x, double y, double z, double motX, double motY, double motZ, boolean trailing, int age, boolean handleParticleSettings)
    {
        EntityFX fx = new EntityAstralEnergyFX(Minecraft.getMinecraft().theWorld, x, y, z, motX, motY, motZ, trailing, age);
        
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