package jcm2606.mods.sorcerycraft.block.astral;

import java.awt.Color;

import jcm2606.mods.jccore.client.render.IBlockRenderHandler;
import jcm2606.mods.jccore.core.JCCore;
import jcm2606.mods.jccore.core.util.RenderUtil;
import jcm2606.mods.sorcerycraft.block.SCBlockContainer;
import jcm2606.mods.sorcerycraft.block.main.BlockGlowBrick;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralPillarBase;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAstralPillarBase extends SCBlockContainer implements IBlockRenderHandler
{
    public Icon icon;
    
    public BlockAstralPillarBase(int par1)
    {
        super(par1, Material.rock, "astralPillarBase", Rarities.BASIC);
        this.renderID = JCCore.renderIDUniversal;
        this.renderAsNormalBlock = false;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir)
    {
        this.blockIcon = ir.registerIcon("SorceryCraft:" + this.name);
        this.icon = ir.registerIcon("SorceryCraft:" + "astralPillarBlockBase");
    }
    
    @Override
    public Icon getIcon(int blockSide, int par2)
    {
        return blockSide == 0 || blockSide == 1 ? this.icon : this.blockIcon;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAstralPillarBase();
    }
    
    @Override
    public void renderInv(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        GL11.glPushMatrix();
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        renderer.setRenderBoundsFromBlock(block);
        
        Tessellator t = Tessellator.instance;
        t.setBrightness(255);
        
        Color c = new Color(0x4B0082);
        float r = c.getRed() / 255.0F;
        float g = c.getGreen() / 255.0F;
        float b = c.getBlue() / 255.0F;
        GL11.glColor3f(r, g, b);
        RenderUtil.instance();
        RenderUtil.drawFaces(renderer, block, ((BlockGlowBrick) SCObjects.glowBrick1).textureBackground, true);
        GL11.glColor3f(1f, 1f, 1f);
        
        renderer.clearOverrideBlockTexture();
        
        RenderUtil.instance();
        RenderUtil.drawFaces(renderer, block, renderer.getBlockIcon(block), false);
        
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        renderer.setRenderBoundsFromBlock(block);
        GL11.glPopMatrix();
    }
    
    @Override
    public boolean renderWorld(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        GL11.glPushMatrix();
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        renderer.setRenderBoundsFromBlock(block);
        
        Tessellator t = Tessellator.instance;
        t.setColorOpaque_I(0x4B0082);
        
        if(world.getBlockTileEntity(x, y, z) != null)
        {
            if(world.getBlockTileEntity(x, y, z) instanceof TileAstralPillarBase)
            {
                TileAstralPillarBase baseTE = (TileAstralPillarBase) world.getBlockTileEntity(x, y, z);
                
                if(baseTE.valid)
                {
                    t.setBrightness(255);
                }
            }
        }
        
        RenderUtil.instance().renderAllSides(world, x, y, z, block, renderer, ((BlockGlowBrick) SCObjects.glowBrick1).textureBackground, true);
        
        renderer.clearOverrideBlockTexture();
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setRenderBoundsFromBlock(block);
        GL11.glPopMatrix();
        
        return true;
    }
}
