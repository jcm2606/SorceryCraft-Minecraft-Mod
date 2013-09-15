package jcm2606.mods.sorcerycraft.client.render.item;

import jcm2606.mods.jccore.client.render.ItemRendererBase;
import jcm2606.mods.sorcerycraft.client.model.ModelCrystal;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

public class CrystalRendererItem extends ItemRendererBase
{
    
    private final ModelCrystal model;
    
    public CrystalRendererItem()
    {
        model = new ModelCrystal();
        
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
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_BLOCKS + "crystal.png");
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(posX, posY, posZ); // size
        GL11.glScalef(1.0F, -3.4F, -1F);
        model.renderModel(0.0625F);
        GL11.glPopMatrix(); // end
    }
    
    @Override
    public void renderEquippedFirstPerson(float posX, float posY, float posZ, ItemStack stack)
    {
        Tessellator tesselator = Tessellator.instance;
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_BLOCKS + "crystal.png");
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(posX, posY, posZ); // size
        GL11.glScalef(1.0F, -3.4F, -1F);
        model.renderModel(0.0625F);
        GL11.glPopMatrix(); // end
    }
    
    @Override
    public void renderEntity(float posX, float posY, float posZ, ItemStack stack)
    {
        Tessellator tesselator = Tessellator.instance;
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_BLOCKS + "crystal.png");
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(posX, posY, posZ); // size
        GL11.glScalef(1.0F, -3.4F, -1F);
        model.renderModel(0.0625F);
        GL11.glPopMatrix(); // end
    }
    
    @Override
    public void renderInventory(float posX, float posY, float posZ, ItemStack stack)
    {
        Tessellator tesselator = Tessellator.instance;
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_BLOCKS + "crystal.png");
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(posX, posY, posZ); // size
        GL11.glScalef(1.0F, -3.4F, -1F);
        model.renderModel(0.0625F);
        GL11.glPopMatrix(); // end
    }
}
