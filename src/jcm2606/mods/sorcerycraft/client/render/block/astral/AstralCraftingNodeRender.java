package jcm2606.mods.sorcerycraft.client.render.block.astral;

import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class AstralCraftingNodeRender extends TileEntitySpecialRenderer
{
    public String side = "textures/blocks/astralCraftingNodeSide.png";
    public String top = "textures/blocks/astralCraftingNodeTop.png";
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f)
    {
        RenderHandlerSC.drawAstralParticleField(tileentity, x, y, z, f, side, side, side, side, top, side);
    }
}
