package jcm2606.mods.sorcerycraft.client.render.block.astral;

import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralBattery;
import jcm2606.mods.sorcerycraft.client.model.ModelAstralBattery;
import jcm2606.mods.sorcerycraft.client.model.ModelCube;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class AstralBatteryRender extends TileEntitySpecialRenderer
{
    
    public AstralBatteryRender()
    {
        aModel = new ModelAstralBattery();
    }
    
    public void renderAModelAt(TileAstralBattery tile, double d, double d1, double d2, float f)
    {
        int i = tile.getBlockMetadata(); // this is for rotation
        int j = 0;
        
        if (i == 3)
        {
            j = 0;
        }
        
        if (i == 5)
        {
            j = 90;
        }
        
        if (i == 2)
        {
            j = 180;
        }
        
        if (i == 4)
        {
            j = 270;
        }
        
        GL11.glPushMatrix();
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralBattery.png"); // texture
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F); // size
        GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, -1F, -1F);
        aModel.renderModel(0.0625F);
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
        
        GL11.glTranslated(d + 0.5, d1 + 0.25, d2 + 0.5);
        
        GL11.glScalef(0.25f, 0.25f, 0.25f);
        
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "cubeAstralCrystal.png");
        ModelCube.INSTANCE.renderModel();
        
        GL11.glPopMatrix();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        renderAModelAt((TileAstralBattery) tileentity, d, d1, d2, f);
    }
    
    private final ModelAstralBattery aModel;
}
