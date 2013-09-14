package jcm2606.mods.sorcerycraft.client.render.block.astral;

import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class AstralBlockRender extends TileEntitySpecialRenderer
{
    String texture;
    
    public AstralBlockRender(String s)
    {
        this.texture = s;
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f)
    {
        RenderHandlerSC.drawAstralParticleField(tileentity, x, y, z, f, texture);
    }
}
