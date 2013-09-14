package jcm2606.mods.sorcerycraft.client.render.block;

import jcm2606.mods.sorcerycraft.block.tile.TileEntityInfuseTablet;
import jcm2606.mods.sorcerycraft.client.model.ModelInfuseTablet;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class InfuseTabletRender extends TileEntitySpecialRenderer
{
    
    public InfuseTabletRender()
    {
        aModel = new ModelInfuseTablet();
    }
    
    public void renderAModelAt(TileEntityInfuseTablet tile, double d, double d1, double d2, float f)
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
        
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_BLOCKS + "infusion_tablet.png"); // texture
        GL11.glPushMatrix(); // start
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F); // size
        GL11.glRotatef(j, 0.0F, 1.0F, 0.0F); // rotate based on metadata
        GL11.glScalef(1.0F, -1F, -1F); // if you read this comment out this line
                                       // and you can see what happens
        aModel.renderModel(0.0625F); // renders and yes 0.0625 is a random
                                     // number
        GL11.glPopMatrix(); // end
        
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        renderAModelAt((TileEntityInfuseTablet) tileentity, d, d1, d2, f);
    }
    
    private final ModelInfuseTablet aModel;
}
