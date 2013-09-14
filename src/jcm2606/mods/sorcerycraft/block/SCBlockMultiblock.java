package jcm2606.mods.sorcerycraft.block;

import jcm2606.mods.jccore.block.BlockMultiblock;
import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class SCBlockMultiblock extends BlockMultiblock
{
    protected Icon[] iconBuffer;
    public String name;
    public boolean useIconIndex;
    public int renderID = 0;
    public boolean renderAsNormalBlock = true;
    public boolean isOpaqueCube = true;
    
    public SCBlockMultiblock(int par1, Material par2Material, String par3)
    {
        super(par1, par2Material);
        setCreativeTab(SorceryCraft.tabBlocks);
        this.setUnlocalizedName("sc" + par3);
        this.name = par3;
        this.useIconIndex = true;
        this.setTickRandomly(true);
    }
    
    public SCBlockMultiblock(int par1, Material par2Material, String par3, String par4)
    {
        super(par1, par2Material, par4);
        setCreativeTab(SorceryCraft.tabBlocks);
        this.setUnlocalizedName("sc" + par3);
        this.name = par3;
        this.useIconIndex = true;
        this.setTickRandomly(true);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        if (useIconIndex)
        {
            this.blockIcon = par1IconRegister.registerIcon("SorceryCraft:" + name);
        }
    }
    
    @Override
    public int getRenderType()
    {
        return renderID;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return isOpaqueCube;
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return renderAsNormalBlock;
    }
}
