package jcm2606.mods.sorcerycraft.client.render.block.psysaic;

import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralPillarCap;
import jcm2606.mods.sorcerycraft.client.model.ModelCube;
import jcm2606.mods.sorcerycraft.client.model.ModelPsyaicTotemTop;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class RenderPsyaicTotemTop extends TileEntitySpecialRenderer
{
    private ModelPsyaicTotemTop model;
    
    public RenderPsyaicTotemTop()
    {
        this.model = new ModelPsyaicTotemTop();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f)
    {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glTranslatef((float) d0 + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "psyaicTotemTop.png");
        this.model.renderModel();
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
        
        TileAstralPillarCap tile = (TileAstralPillarCap) tileentity;
        
        GL11.glTranslatef((float) d0 + 0.5F, (float) d1, (float) d2 + 0.5F);
        
        GL11.glTranslated(0, 0.75, 0);
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        
        GL11.glColor4d(1, 1, 1, 0.95);
        
        ModelCube cube = new ModelCube();
        
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "cubeAstralCrystal.png");
        
        GL11.glRotatef(tile.getTicks() * 2, 0, 1, 0);
        GL11.glRotatef(-(tile.getTicks() * 2), 1, 1, 0);
        GL11.glRotatef(36f + tile.getTicks(), 0, 1, 1);
        GL11.glRotatef(36f + 36f + tile.getTicks() * 2, 1, 1, 0);
        GL11.glRotatef(tile.getTicks(), 1, 1, 1);
        
        double scale = 0.25;
        
        GL11.glScaled(scale, scale, scale);
        
        cube.renderModel();
        
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        
        GL11.glPopMatrix();
    }
}
