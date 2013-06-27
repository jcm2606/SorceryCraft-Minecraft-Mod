package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.lib.Rarities;
import jcm2606.mods.sorcerycraft.tile.astral.TileEntityAstralEnergyGate;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralEnergyGate extends BlockAstral {
    public BlockAstralEnergyGate(int par1) {
        super(par1, Material.rock, "astralEnergyGate", Rarities.ADVANCED, "astralEnergyGate");
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
        return new TileEntityAstralEnergyGate();
    }
}
