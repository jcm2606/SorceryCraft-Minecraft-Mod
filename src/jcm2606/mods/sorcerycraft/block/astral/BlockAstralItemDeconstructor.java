package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.SCBlockContainer;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import jcm2606.mods.sorcerycraft.tile.astral.TileEntityAstralMechanism;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralItemDeconstructor extends SCBlockContainer {
    public BlockAstralItemDeconstructor(int par1) {
        super(par1, Material.rock, "astralCrystalBlock", Rarities.BASIC);
        this.useIconIndex = false;
        this.renderAsNormalBlock = false;
        this.isOpaqueCube = false;
        this.renderID = -1;
        this.setHardness(5.0f);
        this.setResistance(10.0f);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityAstralMechanism();
    }
}
