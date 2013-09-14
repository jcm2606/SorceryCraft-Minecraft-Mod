package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralTotem1;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralTotem1 extends BlockAstral
{
    public BlockAstralTotem1(int par1)
    {
        super(par1, Material.rock, "astralTotem1", Rarities.BASIC, "astral_totem_1_anim");
        this.useIconIndex = false;
        this.renderAsNormalBlock = false;
        this.isOpaqueCube = false;
        this.renderID = -1;
        this.setHardness(3.5f);
        this.setResistance(10.0f);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAstralTotem1();
    }
}
