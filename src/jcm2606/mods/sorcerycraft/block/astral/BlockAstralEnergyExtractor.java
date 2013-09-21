package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.SCBlockMultiblock;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralEnergyExtractor;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralEnergyExtractor extends SCBlockMultiblock
{
    public BlockAstralEnergyExtractor(int par1)
    {
        super(par1, Material.rock, "astralEnergyExtractionCore", Rarities.BASIC);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAstralEnergyExtractor();
    }
}
