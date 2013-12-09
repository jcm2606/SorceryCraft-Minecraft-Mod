package jcm2606.mods.sorcerycraft.block;

import java.util.Random;

import jcm2606.mods.jccore.block.BlockJC;
import jcm2606.mods.sorcerycraft.client.SCClientProxy;
import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SCBlock extends BlockJC
{
    protected Icon[] iconBuffer;
    public String name;
    public boolean useIconIndex;
    public int renderID = 0;
    public boolean renderAsNormalBlock = true;
    public boolean isOpaqueCube = true;
    
    public SCBlock(int par1, Material par3Material, String par4, String par5)
    {
        super(par1, par3Material, par5);
        this.setCreativeTab(SorceryCraft.tabBlocks);
        this.setUnlocalizedName("sc" + par4);
        this.name = par4;
        this.useIconIndex = true;
        this.setTickRandomly(true);
    }
    
    public SCBlock(int par1, Material par3Material, String par4)
    {
        super(par1, par3Material);
        this.setCreativeTab(SorceryCraft.tabBlocks);
        this.setUnlocalizedName("sc" + par4);
        this.name = par4;
        this.useIconIndex = true;
        this.setTickRandomly(true);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        if (this.useIconIndex)
        {
            this.blockIcon = par1IconRegister.registerIcon("SorceryCraft:" + this.name);
        }
    }
    
    @Override
    public int getRenderType()
    {
        return this.renderID;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return this.isOpaqueCube;
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return this.renderAsNormalBlock;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    }
    
    @Override
    public boolean canRenderInPass(int pass)
    {
        SCClientProxy.currentRenderPass = pass;
        
        return true;
    }
}
