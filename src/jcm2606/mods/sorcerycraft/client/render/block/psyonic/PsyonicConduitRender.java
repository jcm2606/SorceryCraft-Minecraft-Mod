package jcm2606.mods.sorcerycraft.client.render.block.psyonic;

import jcm2606.mods.sorcerycraft.block.tile.psyonic.TilePsyonicConduit;
import jcm2606.mods.sorcerycraft.client.model.ModelPsyonicConduit;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class PsyonicConduitRender extends TileEntitySpecialRenderer implements IItemRenderer
{
    private ModelPsyonicConduit model;
    
    public PsyonicConduitRender()
    {
        this.model = new ModelPsyonicConduit();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f)
    {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glTranslatef((float) d0 + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "psyonicConduit_" + ((TilePsyonicConduit) tileentity).mode.name() + ".png");
        this.model.renderModel((TilePsyonicConduit) tileentity);
        GL11.glPopMatrix();
    }
    
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }
    
    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }
    
    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        if(type.equals(type.INVENTORY))
        {
            GL11.glScalef(1.5f, 1.5f, 1.5f);
            GL11.glTranslatef(0f, -1f, 0f);
        }
        
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "psyonicConduit_" + "INV" + ".png");
        this.model.getRectForSide(null).render(0.0625f);
        
        if(type.equals(type.INVENTORY))
        {
            this.model.getRectForSide(ForgeDirection.EAST).render(0.0625f);
            this.model.getRectForSide(ForgeDirection.WEST).render(0.0625f);
        }
    }
}
