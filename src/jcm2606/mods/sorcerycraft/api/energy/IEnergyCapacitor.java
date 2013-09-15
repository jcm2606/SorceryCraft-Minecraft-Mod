package jcm2606.mods.sorcerycraft.api.energy;

/**
 * Interface for TE-based (TileEntity Based) energy capacitors. Typically
 * implements IProvider along with this to allow an energy transfer rate cap.
 */
public interface IEnergyCapacitor
{
    /**
     * Called when this capacitor requests energy
     * 
     * @param amount
     *            Amount of energy requested
     */
    public int capacitorRequestEnergy(int amount, boolean ignoreLimit);
    
    /**
     * Called when this capacitor provides energy
     * 
     * @param amount
     *            Amount of energy provided
     */
    public int capacitorProvideEnergy(int amount);
    
    /**
     * @return Total energy storage limit
     */
    public int getEnergyLimit();
    
    /**
     * @return Total stored energy
     */
    public int getEnergyStored();
    
    /**
     * @return Does this capacitor have any energy stored within itself
     */
    public boolean hasEnergy();
    
    /**
     * @return Does this capacitor have any space left in its buffer
     */
    public boolean hasSpace();
}
