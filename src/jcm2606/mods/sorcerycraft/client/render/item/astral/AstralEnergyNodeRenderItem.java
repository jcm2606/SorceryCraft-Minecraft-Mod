package jcm2606.mods.sorcerycraft.client.render.item.astral;

import jcm2606.mods.jccore.client.render.ItemRendererBase;
import jcm2606.mods.sorcerycraft.client.model.ModelAstralEnergyNode;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

public class AstralEnergyNodeRenderItem extends ItemRendererBase
{
    private final ModelAstralEnergyNode model;
    
    public AstralEnergyNodeRenderItem()
    {
        model = new ModelAstralEnergyNode();
        
        this.entityX = 0;
        this.entityY = 1;
        this.entityZ = 0;
        
        this.equippedX = 0.6f;
        this.equippedY = 2;
        this.equippedZ = 0.6f;
        
        this.equippedFirstPersonX = 0.6f;
        this.equippedFirstPersonY = 2;
        this.equippedFirstPersonZ = 0.6f;
        
        this.invX = 0.2f;
        this.invY = 1;
        this.invZ = 0.3f;
    }
    
    @Override
    public void renderEquipped(float posX, float posY, float posZ, ItemStack stack)
    {
        Tessellator tesselator = Tessellator.instance;
        GL11.glPushMatrix();
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralEnergyNode.png");
        GL11.glTranslatef(0.5f, 2f, 0.5f);
        GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, -1F, -1F);
        model.renderModel();
        GL11.glPopMatrix();
    }
    
    @Override
    public void renderEquippedFirstPerson(float posX, float posY, float posZ, ItemStack stack)
    {
        Tessellator tesselator = Tessellator.instance;
        GL11.glPushMatrix();
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralEnergyNode.png");
        GL11.glTranslatef(0.5f, 2f, 0.5f);
        GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, -1F, -1F);
        model.renderModel();
        GL11.glPopMatrix();
    }
    
    @Override
    public void renderEntity(float posX, float posY, float posZ, ItemStack stack)
    {
        Tessellator tesselator = Tessellator.instance;
        GL11.glPushMatrix();
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralEnergyNode.png");
        GL11.glTranslatef(0, 1, 0);
        GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, -1F, -1F);
        model.renderModel();
        GL11.glPopMatrix();
    }
    
    @Override
    public void renderInventory(float posX, float posY, float posZ, ItemStack stack)
    {
        Tessellator tesselator = Tessellator.instance;
        GL11.glPushMatrix();
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralEnergyNode.png");
        GL11.glTranslatef(0.3f, 1.2f, 0.3f);
        GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, -1F, -1F);
        model.renderModel();
        GL11.glPopMatrix();
    }
}
