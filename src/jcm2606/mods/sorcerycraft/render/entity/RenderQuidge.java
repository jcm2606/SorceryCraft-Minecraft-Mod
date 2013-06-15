package jcm2606.mods.sorcerycraft.render.entity;

import jcm2606.mods.sorcerycraft.entity.EntityQuidge;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;

public class RenderQuidge extends RenderLiving {

	public RenderQuidge(float par2) {
		super(new ModelVillager(par2), par2);
	}

	public void render(EntityQuidge entity, double i, double j, double k, float l, float m)
	{
		super.doRenderLiving(entity, i, j, k, l, m);
	}
	
	@Override
	public void doRenderLiving(EntityLiving entity, double i, double j, double k, float l, float m)
	{
		this.render((EntityQuidge)entity, i, j, k, l, m);
	}
	
	public void doRender(EntityLiving entity, double i, double j, double k, float l, float m)
	{
		this.render((EntityQuidge)entity, i, j, k, l, m);
	}
}
