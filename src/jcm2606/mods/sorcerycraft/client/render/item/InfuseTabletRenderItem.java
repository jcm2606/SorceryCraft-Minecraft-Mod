package jcm2606.mods.sorcerycraft.client.render.item;

import jcm2606.mods.jccore.client.render.ItemRendererBase;
import jcm2606.mods.sorcerycraft.client.model.ModelInfuseTablet;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

public class InfuseTabletRenderItem extends ItemRendererBase
{
    private final ModelInfuseTablet model;
    
    public InfuseTabletRenderItem()
    {
        model = new ModelInfuseTablet();
        
        this.invY = 1;
        
        this.equippedX = this.equippedZ = 0.5f;
        this.equippedY = 1.5f;
        
        this.equippedFirstPersonX = this.equippedFirstPersonZ = 0.5f;
        this.equippedFirstPersonY = 2f;
    }
    
    @Override
    public void renderEquipped(float posX, float posY, float posZ, ItemStack stack)
    {
        Tessellator tesselator = Tessellator.instance;
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_BLOCKS + "infusion_tablet.png");
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
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_BLOCKS + "infusion_tablet.png");
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
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_BLOCKS + "infusion_tablet.png");
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
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_BLOCKS + "infusion_tablet.png");
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(posX, posY, posZ); // size
        GL11.glScalef(1.0F, -1F, -1F);
        model.renderModel(0.0625F);
        GL11.glPopMatrix(); // end
    }
}
