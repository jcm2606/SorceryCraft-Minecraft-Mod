package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralCrystalBlock;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralCrystal extends BlockAstral
{
    public BlockAstralCrystal(int par1)
    {
        super(par1, Material.rock, "astralCrystalBlock", Rarities.BASIC, "astral_crystal_anim");
        this.useIconIndex = false;
        this.renderAsNormalBlock = false;
        this.isOpaqueCube = false;
        this.renderID = -1;
        this.setHardness(5.0f);
        this.setResistance(10.0f);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAstralCrystalBlock();
    }
}
