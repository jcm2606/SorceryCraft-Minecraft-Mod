package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.lib.Rarities;
import jcm2606.mods.sorcerycraft.tile.astral.TileEntityAstralMechanism;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralMechanism extends BlockAstral {
    public BlockAstralMechanism(int par1) {
        super(par1, Material.iron, "astralMechanismBlock", Rarities.BASIC, "astral_mechanism_anim");
        this.useIconIndex = false;
        this.renderAsNormalBlock = false;
        this.isOpaqueCube = false;
        this.renderID = -1;
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityAstralMechanism();
    }
}
