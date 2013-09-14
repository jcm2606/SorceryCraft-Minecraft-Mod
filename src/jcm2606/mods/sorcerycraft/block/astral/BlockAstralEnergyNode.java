package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralEnergyNode;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralEnergyNode extends BlockAstral
{
    public BlockAstralEnergyNode(int par1)
    {
        super(par1, Material.iron, "astralEnergyNode", Rarities.BASIC, "astral_energy_node");
        this.isOpaqueCube = false;
        this.renderAsNormalBlock = false;
        this.renderID = -1;
        this.useIconIndex = false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAstralEnergyNode();
    }
}
