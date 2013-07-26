package jcm2606.mods.sorcerycraft.block.render;

import java.awt.Color;

import jcm2606.mods.jccore.util.RenderUtil;
import jcm2606.mods.sorcerycraft.block.main.BlockOreVord;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.lib.RenderID;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class VordicOreRender implements ISimpleBlockRenderingHandler {
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        GL11.glPushMatrix();
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        renderer.setRenderBoundsFromBlock(block);
        
        Tessellator t = Tessellator.instance;
        t.setBrightness(255);
        
        Color c = new Color(0x07386d);
        float r = c.getRed() / 255.0F;
        float g = c.getGreen() / 255.0F;
        float b = c.getBlue() / 255.0F;
        GL11.glColor3f(r, g, b);
        RenderUtil.instance().drawFaces(renderer, block, ((BlockOreVord) SCObjects.orevordic).textureGlow, true);
        GL11.glColor3f(1f, 1f, 1f);
        
        renderer.clearOverrideBlockTexture();
        
        RenderUtil.instance().drawFaces(renderer, block, renderer.getBlockIcon(block), false);
        
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        renderer.setRenderBoundsFromBlock(block);
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        GL11.glPushMatrix();
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        renderer.setRenderBoundsFromBlock(block);
        
        Tessellator t = Tessellator.instance;
        t.setColorOpaque_I(0x07386d);
        t.setBrightness(255);
        RenderUtil.instance().renderAllSides(world, x, y, z, block, renderer, ((BlockOreVord) SCObjects.orevordic).textureGlow, true);
        
        renderer.clearOverrideBlockTexture();
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setRenderBoundsFromBlock(block);
        GL11.glPopMatrix();
        
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory()
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return RenderID.renderIDVordicOre;
    }
}
