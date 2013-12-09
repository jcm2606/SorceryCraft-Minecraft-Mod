package jcm2606.mods.sorcerycraft.client.render.item.astral;

import jcm2606.mods.jccore.client.render.ItemRendererBase;
import jcm2606.mods.sorcerycraft.client.model.ModelAstralInfuser;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

public class AstralInfuserRenderItem extends ItemRendererBase
{
    private final ModelAstralInfuser aModel;
    
    public AstralInfuserRenderItem()
    {
        this.aModel = new ModelAstralInfuser();
    }
    
    @Override
    public void renderEquipped(float posX, float posY, float posZ, ItemStack stack)
    {
        this.render(posX, posY, posZ, stack);
    }
    
    @Override
    public void renderEquippedFirstPerson(float posX, float posY, float posZ, ItemStack stack)
    {
        this.render(posX, posY, posZ, stack);
    }
    
    @Override
    public void renderEntity(float posX, float posY, float posZ, ItemStack stack)
    {
        this.render(posX + 0.5f, posY + 0.5f, posZ + 0.5f, stack);
    }
    
    @Override
    public void renderInventory(float posX, float posY, float posZ, ItemStack stack)
    {
        this.render(posX, posY, posZ, stack);
    }
    
    private void render(float posX, float posY, float posZ, ItemStack stack)
    {
        int j = 0;
        
        Tessellator tessellator = Tessellator.instance;
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralInfuser.png"); // texture
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glTranslatef(posX + 0.5F, posY + 1.5F, posZ + 0.5F);
        GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, -1F, -1F);
        this.aModel.render(0.0625F);
        GL11.glPopMatrix();
        
        this.renderCircle("astralInfuserCircle2", tessellator, posX, posY, posZ);
        this.renderCircle("astralInfuserCircle5", tessellator, posX, posY, posZ);
        this.renderCircle("astralInfuserCircle3", tessellator, posX, posY, posZ);
        this.renderCircle("astralInfuserCircle4", tessellator, posX, posY, posZ);
        this.renderCircle("astralInfuserCircle1", tessellator, posX, posY, posZ);
    }
    
    private void renderCircle(String texture, Tessellator tessellator, double x, double y, double z)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y, (float) z + 0.5F);
        GL11.glPushMatrix();
        
        GL11.glDisable(GL11.GL_LIGHTING);
        
        GL11.glTranslatef(-0.45F, 0.95F, -0.45F);
        
        GL11.glDepthMask(false);
        GL11.glEnable(3042);
        
        GL11.glBlendFunc(770, 771);
        
        RenderHandlerSC.bindTexture("textures/fx/" + texture + ".png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        tessellator.startDrawingQuads();
        // tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.8999999761581421D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(0.8999999761581421D, 0.0D, 0.8999999761581421D, 1.0D, 1.0D);
        tessellator.addVertexWithUV(0.8999999761581421D, 0.0D, 0.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        
        tessellator.draw();
        
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        
        GL11.glEnable(GL11.GL_LIGHTING);
        
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
