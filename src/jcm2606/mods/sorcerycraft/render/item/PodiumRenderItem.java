package jcm2606.mods.sorcerycraft.render.item;

import jcm2606.mods.jccore.ItemRendererBase;
import jcm2606.mods.sorcerycraft.lib.Reference;
import jcm2606.mods.sorcerycraft.model.ModelAlchemicalLectern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

public class PodiumRenderItem extends ItemRendererBase {
	private final ModelAlchemicalLectern model;
	public String texture;

	public PodiumRenderItem(String s) {
		model = new ModelAlchemicalLectern();
		this.texture = s;
		
		this.invX = 0;
		this.invY = 1;
		this.invZ = 0;
		
		this.equippedX = this.equippedZ = 0.5f;
		this.equippedY = 1.2f;
		
		this.equippedFirstPersonX = this.equippedFirstPersonZ = 0.5f;
        this.equippedFirstPersonY = 1.2f;
	}
	
	private void render(float x, float y, float z) {
		Tessellator tesselator = Tessellator.instance;
		RenderEngine renderEngine = Minecraft.getMinecraft().renderEngine;
		renderEngine.bindTexture(Reference.PATH_TEXTURES_BLOCKS
				+ texture);
		GL11.glPushMatrix(); // start
		GL11.glTranslatef(x, y, z); // size
		GL11.glScalef(1.0F, -1F, -1F);
		model.renderModel(0.0625F);
		GL11.glPopMatrix(); // end
	}

    @Override
    public void renderEquipped(float posX, float posY, float posZ, ItemStack stack)
    {
        Tessellator tesselator = Tessellator.instance;
        RenderEngine renderEngine = Minecraft.getMinecraft().renderEngine;
        renderEngine.bindTexture(Reference.PATH_TEXTURES_BLOCKS
                + texture);
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(posX, posY, posZ); // size
        GL11.glScalef(1.0F, -1F, -1F);
        model.renderModel(0.0625F);
        GL11.glPopMatrix(); // end
    }

    @Override
    public void renderEquippedFirstPerson(float posX, float posY, float posZ, ItemStack stack)
    {
        Tessellator tesselator = Tessellator.instance;
        RenderEngine renderEngine = Minecraft.getMinecraft().renderEngine;
        renderEngine.bindTexture(Reference.PATH_TEXTURES_BLOCKS
                + texture);
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(posX, posY, posZ); // size
        GL11.glScalef(1.0F, -1F, -1F);
        model.renderModel(0.0625F);
        GL11.glPopMatrix(); // end
    }

    @Override
    public void renderEntity(float posX, float posY, float posZ, ItemStack stack)
    {
        Tessellator tesselator = Tessellator.instance;
        RenderEngine renderEngine = Minecraft.getMinecraft().renderEngine;
        renderEngine.bindTexture(Reference.PATH_TEXTURES_BLOCKS
                + texture);
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(posX, posY, posZ); // size
        GL11.glScalef(1.0F, -1F, -1F);
        model.renderModel(0.0625F);
        GL11.glPopMatrix(); // end
    }

    @Override
    public void renderInventory(float posX, float posY, float posZ, ItemStack stack)
    {
        Tessellator tesselator = Tessellator.instance;
        RenderEngine renderEngine = Minecraft.getMinecraft().renderEngine;
        renderEngine.bindTexture(Reference.PATH_TEXTURES_BLOCKS
                + texture);
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(posX, posY, posZ); // size
        GL11.glScalef(1.0F, -1F, -1F);
        model.renderModel(0.0625F);
        GL11.glPopMatrix(); // end
    }
}
