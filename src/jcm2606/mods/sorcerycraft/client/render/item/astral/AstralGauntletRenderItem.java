package jcm2606.mods.sorcerycraft.client.render.item.astral;

import jcm2606.mods.jccore.render.ItemRendererBase;
import jcm2606.mods.sorcerycraft.client.model.ModelAstralGauntlet;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

public class AstralGauntletRenderItem extends ItemRendererBase
{
    private final ModelAstralGauntlet gauntletModel = new ModelAstralGauntlet();
    
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
    }
    
    @Override
    public void renderEquipped(float posX, float posY, float posZ, ItemStack stack)
    {
        GL11.glPushMatrix();
        
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralGauntlet.png");
        
        GL11.glScalef(0.2f, 0.2f, 0.25f);
        
        if (stack.getItemUseAction() == EnumAction.bow)
        {
            if (Minecraft.getMinecraft().thePlayer.isUsingItem())
            {
                GL11.glTranslatef(4f, 1f, 2.75f);
                
                GL11.glRotatef(-135f, 1, 0, 0);
            } else
            {
                GL11.glTranslatef(4f, 0.3f, 2f);
                
                GL11.glRotatef(-135f, 1, 0, 0);
            }
        } else
        {
            GL11.glTranslatef(4f, 0.3f, 3f);
            
            GL11.glRotatef(-135f, 1, 0, 0);
        }
        
        gauntletModel.render(0.0, 0.0, 0.0);
        
        GL11.glPopMatrix();
    }
    
    @Override
    public void renderEquippedFirstPerson(float posX, float posY, float posZ, ItemStack stack)
    {
        GL11.glPushMatrix();
        
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralGauntlet.png");
        
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        
        GL11.glScalef(0.05f, 0.2f, 0.2f);
        GL11.glTranslatef(5f, 5f, 5f);
        
        GL11.glRotatef(-50f, 0, 1, 0);
        GL11.glRotatef(2f, 1, 0, 0);
        
        if (stack.getItemUseAction() == EnumAction.bow)
        {
            if (Minecraft.getMinecraft().thePlayer.isUsingItem())
            {
                GL11.glRotatef(30f, 1, 0, 0);
                GL11.glRotatef(-15f, 1, 0, 2);
            }
        }
        
        gauntletModel.render(0.0, 0.0, 0.0);
        
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
    
    @Override
    public void renderEntity(float posX, float posY, float posZ, ItemStack stack)
    {
    }
    
    @Override
    public void renderInventory(float posX, float posY, float posZ, ItemStack stack)
    {
    }
}
