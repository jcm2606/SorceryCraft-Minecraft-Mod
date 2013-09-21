package jcm2606.mods.sorcerycraft.client.render.block.astral;

import java.awt.Color;

import jcm2606.mods.jccore.core.util.RenderUtil;
import jcm2606.mods.sorcerycraft.block.main.BlockGlowBrick;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralCapacitorStructure;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.lib.RenderID;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class AstralCapacitorStructureRender implements ISimpleBlockRenderingHandler
{
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        GL11.glPushMatrix();
        renderer.setRenderBoundsFromBlock(block);
        
        Tessellator t = Tessellator.instance;
        
        Color c = new Color(0x4B0082);
        float r = c.getRed() / 255.0F;
        float g = c.getGreen() / 255.0F;
        float b = c.getBlue() / 255.0F;
        GL11.glColor3f(r, g, b);
        RenderUtil.instance().drawFaces(renderer, block, ((BlockGlowBrick) SCObjects.glowBrick1).textureBackground, true);
        GL11.glColor3f(1f, 1f, 1f);
        
        renderer.clearOverrideBlockTexture();
        
        RenderUtil.instance().drawFaces(renderer, block, renderer.getBlockIcon(block), false);
        
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
        t.setColorOpaque_I(0x4B0082);
        
        if (world.getBlockTileEntity(x, y, z) != null)
        {
            if (world.getBlockTileEntity(x, y, z) instanceof TileAstralCapacitorStructure)
            {
                if (((TileAstralCapacitorStructure) world.getBlockTileEntity(x, y, z)).isFormed)
                {
                    t.setBrightness(255);
                }
            }
        }
        
        RenderUtil.instance().renderAllSides(world, x, y, z, block, renderer, ((BlockGlowBrick) SCObjects.glowBrick1).textureBackground, true);
        
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
        return RenderID.renderIDAstralCapacitor;
    }
}
