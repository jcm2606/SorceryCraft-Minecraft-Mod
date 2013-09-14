package jcm2606.mods.sorcerycraft.api.energy;

/**
 * Interface for TE-based (TileEntity Based) energy providers.
 */
public interface IEnergyProvider
{
    /**
     * @return Amount of energy this provider can provide
     */
    public int provide();
    
    /**
     * Allows custom energy amounts to be provided
     * 
     * @return Amount of energy this provider can provide
     */
    public int provide(int amount);
}
