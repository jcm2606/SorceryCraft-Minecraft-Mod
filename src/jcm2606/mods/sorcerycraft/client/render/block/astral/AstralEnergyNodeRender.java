package jcm2606.mods.sorcerycraft.client.render.block.astral;

import java.awt.Color;

import jcm2606.mods.jccore.core.util.RenderUtil;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralEnergyNode;
import jcm2606.mods.sorcerycraft.client.model.ModelAstralEnergyNode;
import jcm2606.mods.sorcerycraft.client.model.ModelAstralEnergyNodeGuide;
import jcm2606.mods.sorcerycraft.client.model.ModelCube;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

public class AstralEnergyNodeRender extends TileEntitySpecialRenderer
{
    private final ModelBasePlate baseplate = new ModelBasePlate();
    private final ModelSphere sphere = new ModelSphere();
    
    private final ModelAstralEnergyNode node = new ModelAstralEnergyNode();
    private final ModelCube crystalCube = new ModelCube();
    private final ModelAstralEnergyNodeGuide nodeGuide = new ModelAstralEnergyNodeGuide();
    
    public void renderAModelAt(TileAstralEnergyNode tile, double x, double y, double z, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
        GL11.glScalef(1.0F, -1F, -1F);
        
        RenderUtil.instance().setBrightness(tile.worldObj, tile.xCoord, tile.yCoord, tile.zCoord, SCObjects.astralEnergyNode, Tessellator.instance);
        
        this.renderNodeBackground(tile);
        this.renderNode(tile);
        
        this.renderSpinningCube(tile);
        
        GL11.glPopMatrix();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        renderAModelAt((TileAstralEnergyNode) tileentity, d, d1, d2, f);
    }
    
    private void renderSpinningCube(TileAstralEnergyNode tile)
    {
        GL11.glPushMatrix();
        
        GL11.glTranslated(0, 1, 0);
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        
        GL11.glColor4d(1, 1, 1, 0.95);
        
        ModelCube cube = new ModelCube();
        
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "cubeAstralCrystal.png");
        
        GL11.glRotatef(tile.getTicks() * 2, 0, 1, 0);
        GL11.glRotatef(36f + tile.getTicks() * 2, 0, 1, 1);
        
        GL11.glScaled(0.3, 0.3, 0.3);
        
        cube.renderModel();
        
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        
        GL11.glPopMatrix();
    }
    
    private void renderNode(TileAstralEnergyNode tile)
    {
        GL11.glPushMatrix();
        
        GL11.glColor3f(1, 1, 1);
        
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralEnergyNode.png");
        
        int meta = tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
        
        if(meta == 0)
        {
            GL11.glPushMatrix();
            GL11.glRotated(180, 1, 0, 0);
            GL11.glTranslated(0, -2, 0);
            
            node.renderModel();
            GL11.glPopMatrix();
        } else
            if(meta == 2)
            {
                GL11.glPushMatrix();
                GL11.glRotated(-90, 1, 0, 0);
                GL11.glTranslated(0, -1, 1);
                
                node.renderModel();
                GL11.glPopMatrix();
            } else
                if(meta == 3)
                {
                    GL11.glPushMatrix();
                    GL11.glRotated(90, 1, 0, 0);
                    GL11.glTranslated(0, -1, -1);
                    
                    node.renderModel();
                    GL11.glPopMatrix();
                } else
                    if(meta == 4)
                    {
                        GL11.glPushMatrix();
                        GL11.glRotated(-90, 0, 0, 1);
                        GL11.glTranslated(-1, -1, 0);
                        
                        node.renderModel();
                        GL11.glPopMatrix();
                    } else
                        if(meta == 5)
                        {
                            GL11.glPushMatrix();
                            GL11.glRotated(90, 0, 0, 1);
                            GL11.glTranslated(1, -1, 0);
                            
                            node.renderModel();
                            GL11.glPopMatrix();
                        } else {
                            node.renderModel();
                        }
        
        GL11.glPopMatrix();
    }
    
    private void renderNodeBackground(TileAstralEnergyNode tile)
    {
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralEnergyNodeBG.png");
        
        GL11.glPushMatrix();
        Color c = new Color(0x4B0082);
        float r = c.getRed() / 255.0F;
        float g = c.getGreen() / 255.0F;
        float b = c.getBlue() / 255.0F;
        GL11.glColor3f(r, g, b);
        
        int meta = tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
        
        if(meta == 0)
        {
            GL11.glPushMatrix();
            GL11.glRotated(180, 1, 0, 0);
            GL11.glTranslated(0, -2, 0);
            
            node.renderModel();
            GL11.glPopMatrix();
        } else
            if(meta == 2)
            {
                GL11.glPushMatrix();
                GL11.glRotated(-90, 1, 0, 0);
                GL11.glTranslated(0, -1, 1);
                
                node.renderModel();
                GL11.glPopMatrix();
            } else
                if(meta == 3)
                {
                    GL11.glPushMatrix();
                    GL11.glRotated(90, 1, 0, 0);
                    GL11.glTranslated(0, -1, -1);
                    
                    node.renderModel();
                    GL11.glPopMatrix();
                } else
                    if(meta == 4)
                    {
                        GL11.glPushMatrix();
                        GL11.glRotated(-90, 0, 0, 1);
                        GL11.glTranslated(-1, -1, 0);
                        
                        node.renderModel();
                        GL11.glPopMatrix();
                    } else
                        if(meta == 5)
                        {
                            GL11.glPushMatrix();
                            GL11.glRotated(90, 0, 0, 1);
                            GL11.glTranslated(1, -1, 0);
                            
                            node.renderModel();
                            GL11.glPopMatrix();
                        } else {
                            node.renderModel();
                        }
        
        GL11.glPopMatrix();
    }
    
    private static class ModelBasePlate
    {
        private static IModelCustom model;
        
        public ModelBasePlate()
        {
            // model = AdvancedModelLoader.loadModel("/assets/sorcerycraft/" +
            // Reference.PATH_MODELS + "AstralEnergyNodeBaseplatetest.obj");
        }
        
        private static void render()
        {
            model.renderAll();
        }
        
        public static void render(double x, double y, double z)
        {
            render();
        }
    }
    
    private static class ModelSphere
    {
        private static IModelCustom model;
        
        public ModelSphere()
        {
            // model = AdvancedModelLoader.loadModel("/assets/sorcerycraft/" +
            // Reference.PATH_MODELS + "AstralEnergyNodeSphere.obj");
        }
        
        private static void render()
        {
            model.renderAll();
        }
        
        public static void render(double x, double y, double z)
        {
            render();
        }
    }
}
