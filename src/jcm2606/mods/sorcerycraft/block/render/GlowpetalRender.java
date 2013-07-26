package jcm2606.mods.sorcerycraft.block.render;

import jcm2606.mods.jccore.util.RenderUtil;
import jcm2606.mods.sorcerycraft.block.main.BlockFlowerGlow;
import jcm2606.mods.sorcerycraft.core.lib.RenderID;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class GlowpetalRender implements ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {}

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        renderer.setRenderBoundsFromBlock(block);
        
        renderer.renderCrossedSquares(block, x, y, z);
        
        Tessellator t = Tessellator.instance;
        t.setColorOpaque_I(0xC8A2C8);
        t.setBrightness(255);
        
        RenderUtil.instance().drawCrossedSquares(((BlockFlowerGlow) block).textureGlow, x, y, z, 1.0f);
        
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory()
    {
        return false;
    }

    @Override
    public int getRenderId()
    {
        return RenderID.renderIDGlowpetal;
    }
}
