package jcm2606.mods.sorcerycraft.block.tile.astral;

import jcm2606.mods.jccore.block.tile.TileEntityJC;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyProvider;

public abstract class TileAstralKineticConvertorBase extends TileEntityJC implements IEnergyProvider
{
    @Override
    public int provide(int amount)
    {
        return this.provide();
    }
}
