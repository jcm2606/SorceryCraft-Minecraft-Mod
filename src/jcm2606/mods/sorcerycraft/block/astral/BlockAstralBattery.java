package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.SCBlockContainer;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralBattery;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralBattery extends SCBlockContainer
{
    public BlockAstralBattery(int par1)
    {
        super(par1, Material.iron, "astralBattery", Rarities.ADVANCED);
        this.setHardness(4.0f);
        this.setResistance(4.0f);
        this.isOpaqueCube = false;
        this.renderAsNormalBlock = false;
        this.renderID = -1;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAstralBattery();
    }
}
