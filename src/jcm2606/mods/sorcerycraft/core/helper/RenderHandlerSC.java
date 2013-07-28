package jcm2606.mods.sorcerycraft.core.helper;

import java.nio.FloatBuffer;
import java.util.Random;

import jcm2606.mods.jccore.util.RenderUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class RenderHandlerSC {
    public static void drawAstralParticleField(TileEntity te, double par2, double par4, double par6, float f, String textureNorth, String textureSouth, String textureEast, String textureWest, String textureTop, String textureBottom)
    {
        float var9 = (float)Minecraft.getMinecraft().thePlayer.posX;
        float var10 = (float)Minecraft.getMinecraft().thePlayer.posY;
        float var11 = (float)Minecraft.getMinecraft().thePlayer.posZ;
        GL11.glDisable(GL11.GL_LIGHTING);
        Random var12 = new Random(31000L);
        float var13 = 0.75F;
        
        for (int var14 = 0; var14 < 16; ++var14)
        {
            GL11.glPushMatrix();
            float var15 = 16 - var14;
            float var16 = 0.1625F;
            float var17 = 1.0F / (var15 + 1.0F);

            if (var14 == 0)
            {
                RenderUtil.instance().bindTexture("minecraft", "textures/environment/end_sky.png");
                var17 = 0.1F;
                var15 = 65.0F;
                var16 = 0.125F;
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            }

            if (var14 == 1)
            {
                RenderUtil.instance().bindTexture("minecraft", "textures/entity/end_portal.png");
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                var16 = 0.5F;
            }

            float var18 = (float)(-(par4 + var13));
            float var19 = var18 - ActiveRenderInfo.objectY;
            float var20 = var18 + var15 - ActiveRenderInfo.objectY;
            float var21 = var19 / var20;
            var21 += (float)(par4 + var13);
            GL11.glTranslatef(var9, var21, var11);
            GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
            GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
            GL11.glTexGeni(GL11.GL_R, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
            GL11.glTexGeni(GL11.GL_Q, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_EYE_LINEAR);
            GL11.glTexGen(GL11.GL_S, GL11.GL_OBJECT_PLANE, func_76907_a(1.0F, 0.0F, 0.0F, 0.0F));
            GL11.glTexGen(GL11.GL_T, GL11.GL_OBJECT_PLANE, func_76907_a(0.0F, 0.0F, 1.0F, 0.0F));
            GL11.glTexGen(GL11.GL_R, GL11.GL_OBJECT_PLANE, func_76907_a(0.0F, 0.0F, 0.0F, 1.0F));
            GL11.glTexGen(GL11.GL_Q, GL11.GL_EYE_PLANE, func_76907_a(0.0F, 1.0F, 0.0F, 0.0F));
            GL11.glEnable(GL11.GL_TEXTURE_GEN_S);
            GL11.glEnable(GL11.GL_TEXTURE_GEN_T);
            GL11.glEnable(GL11.GL_TEXTURE_GEN_R);
            GL11.glEnable(GL11.GL_TEXTURE_GEN_Q);
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            GL11.glTranslatef(0, Minecraft.getSystemTime() % 700000L / 700000.0F, 0);
            GL11.glScalef(var16, var16, var16);
            GL11.glTranslatef(0.5F, 0.5F, 0.0F);
            GL11.glRotatef((var14 * var14 / 2 + var14 * 9), 0.0F, 0.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
            int l = 2;
            
            GL11.glTranslatef(-var9 / l, -var11 / l, -var10);
            var19 = ActiveRenderInfo.objectY;
            Tessellator var24 = Tessellator.instance;
            var24.startDrawingQuads();
            var21 = var12.nextFloat() * 0.5F + 0.1F;
            float var22 = var12.nextFloat() * 0.5F + 0.4F;
            float var23 = var12.nextFloat() * 0.5F + 0.5F;

            if (var14 == 0)
            {
                var23 = 1.0F;
                var22 = 1.0F;
                var21 = 1.0F;
            }

            var24.setColorRGBA_F(var21 * var17, var22 * var17, var23 * var17, 1F);
            
            final double D = 0.99;
            par2 += 0.5-D/2;
            par4 += 0.5-D/2;
            par6 += 0.5-D/2;
            
            // +Y
            var24.addVertex(par2, par4 + D, par6);
            var24.addVertex(par2, par4 + D, par6 + D);
            var24.addVertex(par2 + D, par4 + D, par6 + D);
            var24.addVertex(par2 + D, par4 + D, par6);
            
            // -Y
            var24.addVertex(par2 + D, par4, par6);
            var24.addVertex(par2 + D, par4, par6 + D);
            var24.addVertex(par2, par4, par6 + D);
            var24.addVertex(par2, par4, par6);
            
            // +X
            var24.addVertex(par2 + D, par4 + D, par6);
            var24.addVertex(par2 + D, par4 + D, par6 + D);
            var24.addVertex(par2 + D, par4, par6 + D);
            var24.addVertex(par2 + D, par4, par6);
            
            // -X
            var24.addVertex(par2, par4, par6);
            var24.addVertex(par2, par4, par6 + D);
            var24.addVertex(par2, par4 + D, par6 + D);
            var24.addVertex(par2, par4 + D, par6);
            
            // +Z
            var24.addVertex(par2 + D, par4, par6 + D);
            var24.addVertex(par2 + D, par4 + D, par6 + D);
            var24.addVertex(par2, par4 + D, par6 + D);
            var24.addVertex(par2, par4, par6 + D);
            
            // -Z
            var24.addVertex(par2, par4, par6);
            var24.addVertex(par2, par4 + D, par6);
            var24.addVertex(par2 + D, par4 + D, par6);
            var24.addVertex(par2 + D, par4, par6);
            
            par2 -= 0.5-D/2;
            par4 -= 0.5-D/2;
            par6 -= 0.5-D/2;
            
            var24.draw();
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
        }
        
        GL11.glDisable(GL11.GL_TEXTURE_GEN_S);
        GL11.glDisable(GL11.GL_TEXTURE_GEN_T);
        GL11.glDisable(GL11.GL_TEXTURE_GEN_R);
        GL11.glDisable(GL11.GL_TEXTURE_GEN_Q);
        
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        Tessellator t = Tessellator.instance;
        renderUnitBoxWithUV((float)par2, (float)par4, (float)par6, t, 0, 0, 1, 1, textureNorth, textureSouth, textureEast, textureWest, textureTop, textureBottom);
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
    }
    
    public static void drawAstralParticleFieldStationary(double par2, double par4, double par6, float f, String textureNorth, String textureSouth, String textureEast, String textureWest, String textureTop, String textureBottom)
    {
        float var9 = (float)Minecraft.getMinecraft().thePlayer.posX;
        float var10 = (float)Minecraft.getMinecraft().thePlayer.posY;
        float var11 = (float)Minecraft.getMinecraft().thePlayer.posZ;
        GL11.glDisable(GL11.GL_LIGHTING);
        Random var12 = new Random(31000L);
        float var13 = 0.75F;
        
        for (int var14 = 0; var14 < 16; ++var14)
        {
            GL11.glPushMatrix();
            float var15 = 16 - var14;
            float var16 = 0.1625F;
            float var17 = 1.5F / (var15 + 2.0F);

            if (var14 == 0)
            {
                RenderUtil.instance().bindTexture("minecraft", "textures/environment/end_sky.png");
                var17 = 0.1F;
                var15 = 65.0F;
                var16 = 0.125F;
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            }

            if (var14 == 1)
            {
                RenderUtil.instance().bindTexture("minecraft", "textures/entity/end_portal.png");
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                var16 = 6.5F;
            }

            float var18 = (float)(-(par4 + var13));
            float var19 = var18 - ActiveRenderInfo.objectY;
            float var20 = var18 + var15 - ActiveRenderInfo.objectY;
            float var21 = var19 / var20;
            var21 += (float)(par4 + var13);
            GL11.glTranslatef(var9, var21, var11);
            GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
            GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
            GL11.glTexGeni(GL11.GL_R, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
            GL11.glTexGeni(GL11.GL_Q, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_EYE_LINEAR);
            GL11.glTexGen(GL11.GL_S, GL11.GL_OBJECT_PLANE, func_76907_a(1.0F, 0.0F, 0.0F, 0.0F));
            GL11.glTexGen(GL11.GL_T, GL11.GL_OBJECT_PLANE, func_76907_a(0.0F, 0.0F, 1.0F, 0.0F));
            GL11.glTexGen(GL11.GL_R, GL11.GL_OBJECT_PLANE, func_76907_a(0.0F, 0.0F, 0.0F, 1.0F));
            GL11.glTexGen(GL11.GL_Q, GL11.GL_EYE_PLANE, func_76907_a(0.0F, 1.0F, 0.0F, 0.0F));
            GL11.glEnable(GL11.GL_TEXTURE_GEN_S);
            GL11.glEnable(GL11.GL_TEXTURE_GEN_T);
            GL11.glEnable(GL11.GL_TEXTURE_GEN_R);
            GL11.glEnable(GL11.GL_TEXTURE_GEN_Q);
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            GL11.glTranslatef(0, Minecraft.getSystemTime() % 700000L / 300000.0F, 0);
            GL11.glScalef(var16, var16, var16);
            GL11.glTranslatef(0.5F, 0.5F, 0.0F);
            GL11.glRotatef(var14 * var14 / 2 + var14 * 9, 0.0F, 0.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
//            GL11.glTranslatef(-var9, -var11, -var10);
            var19 = ActiveRenderInfo.objectY;
            Tessellator var24 = Tessellator.instance;
            var24.setBrightness(180);
            var24.startDrawingQuads();
            var21 = var12.nextFloat() * 0.5F + 0.1F;
            float var22 = var12.nextFloat() * 0.5F + 0.4F;
            float var23 = var12.nextFloat() * 0.5F + 0.5F;

            if (var14 == 0)
            {
                var23 = 1.0F;
                var22 = 1.0F;
                var21 = 1.0F;
            }

            var24.setColorRGBA_F(var21 * var17, var22 * var17, var23 * var17, 1F);
            
            final double D = 0.99;
            par2 += 0.5-D/2;
            par4 += 0.5-D/2;
            par6 += 0.5-D/2;
            
            // +Y
            var24.addVertex(par2, par4 + D, par6);
            var24.addVertex(par2, par4 + D, par6 + D);
            var24.addVertex(par2 + D, par4 + D, par6 + D);
            var24.addVertex(par2 + D, par4 + D, par6);
            
            // -Y
            var24.addVertex(par2 + D, par4, par6);
            var24.addVertex(par2 + D, par4, par6 + D);
            var24.addVertex(par2, par4, par6 + D);
            var24.addVertex(par2, par4, par6);
            
            // +X
            var24.addVertex(par2 + D, par4 + D, par6);
            var24.addVertex(par2 + D, par4 + D, par6 + D);
            var24.addVertex(par2 + D, par4, par6 + D);
            var24.addVertex(par2 + D, par4, par6);
            
            // -X
            var24.addVertex(par2, par4, par6);
            var24.addVertex(par2, par4, par6 + D);
            var24.addVertex(par2, par4 + D, par6 + D);
            var24.addVertex(par2, par4 + D, par6);
            
            // +Z
            var24.addVertex(par2 + D, par4, par6 + D);
            var24.addVertex(par2 + D, par4 + D, par6 + D);
            var24.addVertex(par2, par4 + D, par6 + D);
            var24.addVertex(par2, par4, par6 + D);
            
            // -Z
            var24.addVertex(par2, par4, par6);
            var24.addVertex(par2, par4 + D, par6);
            var24.addVertex(par2 + D, par4 + D, par6);
            var24.addVertex(par2 + D, par4, par6);
            
            par2 -= 0.5-D/2;
            par4 -= 0.5-D/2;
            par6 -= 0.5-D/2;
            
            var24.draw();
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
        }
        
        GL11.glDisable(GL11.GL_TEXTURE_GEN_S);
        GL11.glDisable(GL11.GL_TEXTURE_GEN_T);
        GL11.glDisable(GL11.GL_TEXTURE_GEN_R);
        GL11.glDisable(GL11.GL_TEXTURE_GEN_Q);
        
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        
        Tessellator t = Tessellator.instance;
        renderUnitBoxWithUV((float)par2, (float)par4, (float)par6, t, 0, 0, 1, 1, textureNorth, textureSouth, textureEast, textureWest, textureTop, textureBottom);
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
    }
    
    public static void drawAstralParticleField(TileEntity te, double par2, double par4, double par6, float f, String textureSheet)
    {
        drawAstralParticleField(te, par2, par4, par6, f, textureSheet, textureSheet, textureSheet, textureSheet, textureSheet, textureSheet);
    }
    
    public static void drawAstralParticleFieldStationary(double par2, double par4, double par6, float f, String textureSheet)
    {
        drawAstralParticleFieldStationary(par2, par4, par6, f, textureSheet, textureSheet, textureSheet, textureSheet, textureSheet, textureSheet);
    }
    
    private static void renderUnitBoxWithUV(float paramFloat1, float paramFloat2, float paramFloat3, Tessellator paramTessellator, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, String texture) {
        renderUnitBoxWithUV(paramFloat1, paramFloat2, paramFloat3, paramTessellator, paramFloat4, paramFloat5, paramFloat6, paramFloat7, texture, texture, texture, texture, texture, texture);
    }
    
    private static void renderUnitBoxWithUV(float paramFloat1, float paramFloat2, float paramFloat3, Tessellator paramTessellator, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, String textureNorth, String textureSouth, String textureEast, String textureWest, String textureTop, String textureBottom) {
        // North
        paramTessellator.startDrawingQuads();
        paramTessellator.setColorRGBA(255, 255, 255, 255);
        RenderHandlerSC.bindTexture(textureNorth);
        paramTessellator.addVertexWithUV(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5);
        paramTessellator.addVertexWithUV(paramFloat1, paramFloat2 + 1.0F, paramFloat3, paramFloat4, paramFloat7);
        paramTessellator.addVertexWithUV(paramFloat1 + 1.0F, paramFloat2 + 1.0F, paramFloat3, paramFloat6, paramFloat7);
        paramTessellator.addVertexWithUV(paramFloat1 + 1.0F, paramFloat2, paramFloat3, paramFloat6, paramFloat5);
        paramTessellator.draw();

        // South
        paramTessellator.startDrawingQuads();
        paramTessellator.setColorRGBA(255, 255, 255, 255);
        RenderHandlerSC.bindTexture(textureSouth);
        paramTessellator.addVertexWithUV(paramFloat1, paramFloat2, paramFloat3 + 1.0F, paramFloat4, paramFloat5);
        paramTessellator.addVertexWithUV(paramFloat1 + 1.0F, paramFloat2, paramFloat3 + 1.0F, paramFloat4, paramFloat7);
        paramTessellator.addVertexWithUV(paramFloat1 + 1.0F, paramFloat2 + 1.0F, paramFloat3 + 1.0F, paramFloat6, paramFloat7);
        paramTessellator.addVertexWithUV(paramFloat1, paramFloat2 + 1.0F, paramFloat3 + 1.0F, paramFloat6, paramFloat5);
        paramTessellator.draw();

        // East
        paramTessellator.startDrawingQuads();
        paramTessellator.setColorRGBA(255, 255, 255, 255);
        RenderHandlerSC.bindTexture(textureEast);
        paramTessellator.addVertexWithUV(paramFloat1 + 1.0F, paramFloat2, paramFloat3, paramFloat4, paramFloat5);
        paramTessellator.addVertexWithUV(paramFloat1 + 1.0F, paramFloat2 + 1.0F, paramFloat3, paramFloat4, paramFloat7);
        paramTessellator.addVertexWithUV(paramFloat1 + 1.0F, paramFloat2 + 1.0F, paramFloat3 + 1.0F, paramFloat6, paramFloat7);
        paramTessellator.addVertexWithUV(paramFloat1 + 1.0F, paramFloat2, paramFloat3 + 1.0F, paramFloat6, paramFloat5);
        paramTessellator.draw();

        // West
        paramTessellator.startDrawingQuads();
        paramTessellator.setColorRGBA(255, 255, 255, 255);
        RenderHandlerSC.bindTexture(textureWest);
        paramTessellator.addVertexWithUV(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5);
        paramTessellator.addVertexWithUV(paramFloat1, paramFloat2, paramFloat3 + 1.0F, paramFloat4, paramFloat7);
        paramTessellator.addVertexWithUV(paramFloat1, paramFloat2 + 1.0F, paramFloat3 + 1.0F, paramFloat6, paramFloat7);
        paramTessellator.addVertexWithUV(paramFloat1, paramFloat2 + 1.0F, paramFloat3, paramFloat6, paramFloat5);
        paramTessellator.draw();

        // Bottom
        paramTessellator.startDrawingQuads();
        paramTessellator.setColorRGBA(255, 255, 255, 255);
        RenderHandlerSC.bindTexture(textureBottom);
        paramTessellator.addVertexWithUV(paramFloat1 + 1.0F, paramFloat2, paramFloat3, paramFloat4, paramFloat5);
        paramTessellator.addVertexWithUV(paramFloat1 + 1.0F, paramFloat2, paramFloat3 + 1.0F, paramFloat4, paramFloat7);
        paramTessellator.addVertexWithUV(paramFloat1, paramFloat2, paramFloat3 + 1.0F, paramFloat6, paramFloat7);
        paramTessellator.addVertexWithUV(paramFloat1, paramFloat2, paramFloat3, paramFloat6, paramFloat5);
        paramTessellator.draw();

        // Top
        paramTessellator.startDrawingQuads();
        paramTessellator.setColorRGBA(255, 255, 255, 255);
        RenderHandlerSC.bindTexture(textureTop);
        paramTessellator.addVertexWithUV(paramFloat1, paramFloat2 + 1.0F, paramFloat3 + 1.0F, paramFloat4, paramFloat5);
        paramTessellator.addVertexWithUV(paramFloat1 + 1.0F, paramFloat2 + 1.0F, paramFloat3 + 1.0F, paramFloat4, paramFloat7);
        paramTessellator.addVertexWithUV(paramFloat1 + 1.0F, paramFloat2 + 1.0F, paramFloat3, paramFloat6, paramFloat7);
        paramTessellator.addVertexWithUV(paramFloat1, paramFloat2 + 1.0F, paramFloat3, paramFloat6, paramFloat5);
        paramTessellator.draw();
      }
    
    private static FloatBuffer func_76907_a(float par1, float par2, float par3, float par4)
    {
        field_76908_a.clear();
        field_76908_a.put(par1).put(par2).put(par3).put(par4);
        field_76908_a.flip();
        return field_76908_a;
    }
    
    static FloatBuffer field_76908_a = GLAllocation.createDirectFloatBuffer(16);

    // method to populate a FloatBuffer with 4 values.
    private static FloatBuffer floatBuffer(float a, float b, float c, float d) {
        float[] data = new float[] { a, b, c, d };
        FloatBuffer fb = BufferUtils.createFloatBuffer(data.length);
        fb.put(data);
        fb.flip();
        return fb;
    }
    
    public static void drawFaces(RenderBlocks renderblocks, Block block, Icon icon, boolean st)
    {
      drawFaces(renderblocks, block, icon, icon, icon, icon, icon, icon, st);
    }

    public static void drawFaces(RenderBlocks renderblocks, Block block, Icon i1, Icon i2, Icon i3, Icon i4, Icon i5, Icon i6, boolean solidtop)
    {
      Tessellator tessellator = Tessellator.instance;
      GL11.glPushMatrix();
      GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, -1.0F, 0.0F);
      renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, i1);
      tessellator.draw();
      if (solidtop) {
        GL11.glDisable(3008);
    }
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 1.0F, 0.0F);
      renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, i2);
      tessellator.draw();
      if (solidtop) {
        GL11.glEnable(3008);
    }
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 0.0F, 1.0F);
      renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, i3);
      tessellator.draw();
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 0.0F, -1.0F);
      renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, i4);
      tessellator.draw();
      tessellator.startDrawingQuads();
      tessellator.setNormal(1.0F, 0.0F, 0.0F);
      renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, i5);
      tessellator.draw();
      tessellator.startDrawingQuads();
      tessellator.setNormal(-1.0F, 0.0F, 0.0F);
      renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, i6);
      tessellator.draw();
      GL11.glTranslatef(0.5F, 0.5F, 0.5F);
      GL11.glPopMatrix();
    }
    
    public static void bindTexture(String texture)
    {
        RenderUtil.instance().bindTexture("sorcerycraft", texture);
    }
}
