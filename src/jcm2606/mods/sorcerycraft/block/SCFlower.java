package jcm2606.mods.sorcerycraft.block;

import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SCFlower extends BlockFlower
{
    protected Icon[] iconBuffer;
    public String name;
    public boolean useIconIndex;
    public int renderID = 0;
    public boolean renderAsNormalBlock = true;
    public boolean isOpaqueCube = true;
    
    public SCFlower(int par1, String name)
    {
        super(par1, Material.plants);
        this.name = name;
        this.setUnlocalizedName("sc" + name);
        this.setStepSound(soundGrassFootstep);
        this.useIconIndex = true;
        this.setCreativeTab(SorceryCraft.tabBlocks);
        this.isOpaqueCube = false;
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
