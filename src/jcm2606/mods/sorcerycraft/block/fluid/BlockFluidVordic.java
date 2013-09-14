package jcm2606.mods.sorcerycraft.block.fluid;

import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidVordic extends BlockFluidClassic
{
    public BlockFluidVordic(int id)
    {
        super(id, SCObjects.vordicfluid, Material.water);
        SCObjects.vordicfluid.setBlockID(id);
        this.setCreativeTab(SorceryCraft.tabBlocks);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        return Block.waterMoving.getIcon(side, meta);
    }
    
    @Override
    public int colorMultiplier(IBlockAccess iblockaccess, int x, int y, int z)
    {
        return 0x151B54;
    }
}
