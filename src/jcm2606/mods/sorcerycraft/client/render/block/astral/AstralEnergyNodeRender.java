package jcm2606.mods.sorcerycraft.client.render.block.astral;

import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyConsumer;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralEnergyNode;
import jcm2606.mods.sorcerycraft.client.model.ModelAstralEnergyNode;
import jcm2606.mods.sorcerycraft.client.model.ModelAstralEnergyNodeGuide;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class AstralEnergyNodeRender extends TileEntitySpecialRenderer
{
    private final ModelBasePlate baseplate = new ModelBasePlate();
    private final ModelSphere sphere = new ModelSphere();
    
    private final ModelAstralEnergyNode node = new ModelAstralEnergyNode();
    private final ModelAstralEnergyNodeGuide nodeGuide = new ModelAstralEnergyNodeGuide();
    
    public void renderAModelAt(TileAstralEnergyNode tile, double x, double y, double z, float f)
    {
        GL11.glPushMatrix();
        
        GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
        
        GL11.glPushMatrix();
        
        GL11.glPushMatrix();
        GL11.glTranslated(0, -1, 0);
        
        if (GeneralUtil.getBlockTileFromNeighbour(tile.xCoord, tile.yCoord, tile.zCoord, ForgeDirection.NORTH, tile.worldObj) instanceof IEnergyConsumer)
        {
            GL11.glPushMatrix();
            GL11.glTranslated(0, 0, 1);
            GL11.glRotatef(90, 1, 0, 0);
            GL11.glScalef(1.0F, -1F, -1F);
            
            RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralEnergyNodeGuide.png");
            nodeGuide.renderModel(0.0625f);
            GL11.glPopMatrix();
        }
        
        if (GeneralUtil.getBlockTileFromNeighbour(tile.xCoord, tile.yCoord, tile.zCoord, ForgeDirection.SOUTH, tile.worldObj) instanceof IEnergyConsumer)
        {
            GL11.glPushMatrix();
            GL11.glTranslated(0, 0, -1);
            GL11.glRotatef(-90, 1, 0, 0);
            GL11.glScalef(1.0F, -1F, -1F);
            
            RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralEnergyNodeGuide.png");
            nodeGuide.renderModel(0.0625f);
            GL11.glPopMatrix();
        }
        
        if (GeneralUtil.getBlockTileFromNeighbour(tile.xCoord, tile.yCoord, tile.zCoord, ForgeDirection.EAST, tile.worldObj) instanceof IEnergyConsumer)
        {
            GL11.glPushMatrix();
            GL11.glTranslated(-1, 0, 0);
            GL11.glRotatef(90, 0, 0, 1);
            GL11.glScalef(1.0F, -1F, -1F);
            
            RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralEnergyNodeGuide.png");
            nodeGuide.renderModel(0.0625f);
            GL11.glPopMatrix();
        }
        
        if (GeneralUtil.getBlockTileFromNeighbour(tile.xCoord, tile.yCoord, tile.zCoord, ForgeDirection.WEST, tile.worldObj) instanceof IEnergyConsumer)
        {
            GL11.glPushMatrix();
            GL11.glTranslated(1, 0, 0);
            GL11.glRotatef(-90, 0, 0, 1);
            GL11.glScalef(1.0F, -1F, -1F);
            
            RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralEnergyNodeGuide.png");
            nodeGuide.renderModel(0.0625f);
            GL11.glPopMatrix();
        }
        
        if (GeneralUtil.getBlockTileFromNeighbour(tile.xCoord, tile.yCoord, tile.zCoord, ForgeDirection.UP, tile.worldObj) instanceof IEnergyConsumer)
        {
            GL11.glPushMatrix();
            GL11.glTranslated(0, 1.9, 0);
            GL11.glScalef(1.0F, -1F, -1F);
            
            RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralEnergyNodeGuide.png");
            nodeGuide.renderModel(0.0625f);
            GL11.glPopMatrix();
        }
        
        if (GeneralUtil.getBlockTileFromNeighbour(tile.xCoord, tile.yCoord, tile.zCoord, ForgeDirection.DOWN, tile.worldObj) instanceof IEnergyConsumer)
        {
            GL11.glPushMatrix();
            GL11.glTranslated(0, 1, 0);
            GL11.glScalef(1.0F, -1F, -1F);
            
            RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralEnergyNodeGuide.png");
            nodeGuide.renderModel(0.0625f);
            GL11.glPopMatrix();
        }
        
        GL11.glPopMatrix();
        
        GL11.glScalef(1.0F, -1F, -1F);
        
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralEnergyNode.png");
        node.renderModel(0.0625f);
        
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        renderAModelAt((TileAstralEnergyNode) tileentity, d, d1, d2, f);
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
