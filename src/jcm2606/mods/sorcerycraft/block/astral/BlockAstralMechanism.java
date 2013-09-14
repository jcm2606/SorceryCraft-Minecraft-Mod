package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralMechanismBlock;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralMechanism extends BlockAstral
{
    public BlockAstralMechanism(int par1)
    {
        super(par1, Material.iron, "astralMechanismBlock", Rarities.ADVANCED, "astral_mechanism_anim");
        this.useIconIndex = false;
        this.renderAsNormalBlock = false;
        this.isOpaqueCube = false;
        this.renderID = -1;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAstralMechanismBlock();
    }
}
