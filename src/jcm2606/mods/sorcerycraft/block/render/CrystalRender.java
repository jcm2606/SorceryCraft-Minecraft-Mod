package jcm2606.mods.sorcerycraft.block.render;

import jcm2606.mods.sorcerycraft.block.tile.TileEntityCrystal;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import jcm2606.mods.sorcerycraft.model.ModelCrystal;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CrystalRender extends TileEntitySpecialRenderer {

	public CrystalRender() {
		aModel = new ModelCrystal();
	}

	public void renderAModelAt(TileEntityCrystal tile, double d,
			double d1, double d2, float f) {
		int i = tile.getBlockMetadata(); // this is for rotation
		int j = 0;

		if (i == 3) {
			j = 0;
		}

		if (i == 5) {
			j = 90;
		}

		if (i == 2) {
			j = 180;
		}

		if (i == 4) {
			j = 270;
		}

		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_BLOCKS + "crystal.png"); // texture
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.4F, (float) d2 + 0.5F); // size
		GL11.glColor4d(1.0, 1.0, 1.0, 0.75);
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.1F, -3.5f, -1.0f);
		aModel.renderModel(0.0625F);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1,
			double d2, float f) {
		renderAModelAt((TileEntityCrystal) tileentity, d, d1, d2, f);
	}

	private final ModelCrystal aModel;
}