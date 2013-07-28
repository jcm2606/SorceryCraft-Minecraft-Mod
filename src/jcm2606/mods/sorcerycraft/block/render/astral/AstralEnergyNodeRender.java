package jcm2606.mods.sorcerycraft.block.render.astral;

import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralEnergyNode;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import jcm2606.mods.sorcerycraft.model.ModelAstralEnergyNode;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class AstralEnergyNodeRender extends TileEntitySpecialRenderer {
    public AstralEnergyNodeRender() {
        aModel = new ModelAstralEnergyNode();
    }

    public void renderAModelAt(TileEntityAstralEnergyNode tile, double d,
            double d1, double d2, float f) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_BLOCKS + "astral_energy_node.png"); // texture
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F,
                (float) d2 + 0.5F); // size
        GL11.glRotatef(90, 0.0F, 1.0F, 0.0F); // rotate based on metadata
        GL11.glScalef(1.0F, -1F, -1F); // if you read this comment out this line
        // and you can see what happens
        aModel.renderModel(0.0625F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1,
            double d2, float f) {
        renderAModelAt((TileEntityAstralEnergyNode) tileentity, d, d1, d2, f);
    }

    private final ModelAstralEnergyNode aModel;
}