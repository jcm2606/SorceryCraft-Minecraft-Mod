package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.api.IEnergyInfused;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralCrystalBlock;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralCrystal extends BlockAstral implements IEnergyInfused
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
    
    @Override
    public boolean destroyBlockWhenExtracted()
    {
        return true;
    }
    
    @Override
    public int getCharge()
    {
        return 10;
    }
    
    @Override
    public int getDestroyChance()
    {
        return 20;
    }
}
