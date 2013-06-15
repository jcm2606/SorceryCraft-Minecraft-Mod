package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.IconManager;
import jcm2606.mods.sorcerycraft.block.SCBlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockAstral extends SCBlockContainer {
    String texture;
    
    protected BlockAstral(int par1, Material par2Material, String name, String par4, String par5) {
        super(par1, par2Material, name, par4);
        this.texture = par5;
    }

    @Override
    public abstract TileEntity createNewTileEntity(World world);
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = IconManager.getIcon(this.texture);
    }
}
