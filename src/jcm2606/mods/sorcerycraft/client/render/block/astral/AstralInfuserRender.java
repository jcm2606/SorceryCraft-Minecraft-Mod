package jcm2606.mods.sorcerycraft.client.render.block.astral;

import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralInfuser;
import jcm2606.mods.sorcerycraft.client.model.ModelAstralInfuser;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class AstralInfuserRender extends TileEntitySpecialRenderer
{
    private float bob = 0f;
    private float angle = 0;
    
    public AstralInfuserRender()
    {
        this.aModel = new ModelAstralInfuser();
    }
    
    private void renderAModelAt(TileAstralInfuser tile, double d, double d1, double d2, float f)
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
        
        Tessellator tessellator = Tessellator.instance;
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_MODELS + "astralInfuser.png"); // texture
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
        GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, -1F, -1F);
        this.aModel.render(0.0625F);
        GL11.glPopMatrix();
        
        if (tile.hasSource())
        {
            this.angle += 0.3f;
            
            // renderBaseItem(tile,
            // (RenderItem)RenderManager.instance.getEntityClassRenderObject(EntityItem.class),
            // d, d1, d2);
        }
        
        int count = Minecraft.getMinecraft().thePlayer.ticksExisted;
        
        if (tile.hasSource())
        {
            this.bob = (MathHelper.sin(count / 10.0F) * 0.05F + 0.05F);
        } else
        {
            this.bob = 0f;
        }
        
        if (tile.hasSource())
        {
            this.renderCircle("astralInfuserCircle2", tessellator, tile, d, d1, d2, true, -28, 0, 771, 771);
            this.renderCircle("astralInfuserCircle5", tessellator, tile, d, d1, d2, true, -28, 0, 771, 1);
            this.renderCircle("astralInfuserCircle3", tessellator, tile, d, d1, d2, true, -this.angle, 0, 771, 1);
            this.renderCircle("astralInfuserCircle4", tessellator, tile, d, d1, d2, false, -this.angle, 0, 771, 1);
            this.renderCircle("astralInfuserCircle1", tessellator, tile, d, d1, d2, true, this.angle / 2, 0, 771, 1);
        } else
        {
            this.renderCircle("astralInfuserCircle2", tessellator, tile, d, d1, d2, true, -28, 0, 771, 771);
            this.renderCircle("astralInfuserCircle5", tessellator, tile, d, d1, d2, true, -28, 0, 771, 1);
            this.renderCircle("astralInfuserCircle3", tessellator, tile, d, d1, d2, false, -this.angle, 0, 771, 1);
            this.renderCircle("astralInfuserCircle4", tessellator, tile, d, d1, d2, false, -this.angle, 0, 771, 1);
            this.renderCircle("astralInfuserCircle1", tessellator, tile, d, d1, d2, false, this.angle, 0, 771, 1);
        }
    }
    
    private void renderCircle(String texture, Tessellator tessellator, TileAstralInfuser tile, double x, double y, double z, boolean doSpin,
            float spinAngle, float bobAmount, int blendMode, int blendModePowered)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y, (float) z + 0.5F);
        GL11.glPushMatrix();
        
        GL11.glDisable(GL11.GL_LIGHTING);
        
        if (doSpin)
        {
            GL11.glRotatef(spinAngle, 0.0F, 1.0F, 0.0F);
        }
        
        GL11.glTranslatef(-0.45F, 0.95F + bobAmount, -0.45F);
        
        GL11.glDepthMask(false);
        GL11.glEnable(3042);
        
        if (tile.hasSource())
        {
            GL11.glBlendFunc(770, blendModePowered);
        } else
        {
            GL11.glBlendFunc(770, blendMode);
        }
        
        RenderHandlerSC.bindTexture("textures/fx/" + texture + ".png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        tessellator.startDrawingQuads();
        if (tile.hasSource())
        {
            tessellator.setBrightness(255);
        }
        // tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.8999999761581421D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(0.8999999761581421D, 0.0D, 0.8999999761581421D, 1.0D, 1.0D);
        tessellator.addVertexWithUV(0.8999999761581421D, 0.0D, 0.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        
        tessellator.draw();
        
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        
        GL11.glEnable(GL11.GL_LIGHTING);
        
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
    
    private void renderBaseItem(TileAstralInfuser tile, RenderItem itemRenderer, double x, double y, double z)
    {
        EntityItem entityItem = new EntityItem(tile.worldObj);
        
        entityItem.worldObj = tile.worldObj;
        entityItem.age = 0;
        entityItem.hoverStart = 0;
        
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 1.0d, z + 0.5D);
        
        RenderHelper.enableStandardItemLighting();
        
        // entityItem.setEntityItemStack(new ItemStack(tile.heldItemID, 1,
        // tile.heldItemMeta));
        
        if (entityItem.getEntityItem() != null)
        {
            if (Minecraft.isFancyGraphicsEnabled() || entityItem.getEntityItem().getItem() instanceof ItemBlock)
            {
                GL11.glRotatef(this.angle * 8, 0.0F, 1.0F, 0.0F);
            }
        }
        
        double space = 0.1875D;
        double scale = 0.6D;
        
        if (entityItem.getEntityItem() != null)
        {
            if (entityItem.getEntityItem().getItem() instanceof ItemBlock)
            {
                scale = 0.8d;
            }
        }
        
        GL11.glScaled(scale, scale, scale);
        
        int var10 = 15728880;
        
        int var11 = var10 % 65536;
        int var12 = var10 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var11 / 1.0F, var12 / 1.0F);
        
        /*
         * if(tile.heldItemID > 0) { itemRenderer.doRenderItem(entityItem, 0, 0,
         * 0, 0, 0); }
         */
        
        GL11.glPopMatrix();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        this.renderAModelAt((TileAstralInfuser) tileentity, d, d1, d2, f);
    }
    
    private final ModelAstralInfuser aModel;
}
