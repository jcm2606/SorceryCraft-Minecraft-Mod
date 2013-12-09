package jcm2606.mods.sorcerycraft.api.energy;

import codechicken.lib.vec.Vector3;

/**
 * Interface for any energy capacitors.
 */
public interface IEnergyCapacitor
{
    /**
     * Called when this capacitor requests energy.
     * 
     * @param amount
     *            Amount of energy requested.
     */
    public int addEnergy(int amount, boolean ignoreLimit);
    
    /**
     * Called when this capacitor provides energy.
     * 
     * @param amount
     *            Amount of energy provided.
     */
    public int takeEnergy(int amount);
    
    /**
     * Called when this capacitor requests energy.
     * 
     * @param amount
     *            Amount of energy requested.
     * @param vec3
     *            {@link Vector3} instance of the coordinate location of the device that this capacitor is requesting energy from
     */
    public int addEnergy(int amount, boolean ignoreLimit, Vector3 vec3);
    
    /**
     * Called when this capacitor provides energy.
     * 
     * @param amount
     *            Amount of energy provided.
     * @param vec3
     *            {@link Vector3} instance of the coordinate location of the device that is calling this method
     */
    public int takeEnergy(int amount, Vector3 vec3);
    
    /**
     * @return Total energy storage limit.
     */
    public int getEnergyLimit();
    
    /**
     * @return Total stored energy.
     */
    public int getEnergyStored();
    
    /**
     * @return Does this capacitor have any energy stored within itself.
     */
    public boolean hasEnergy();
    
    /**
     * @return Does this capacitor have any space left in its buffer.
     */
    public boolean hasSpace();
    
    /**
     * This one is used in situations where an overload may occur. You can use
     * this method instead to also make sure the buffer has enough space while
     * not overloading itself.
     * 
     * @param amount
     * @return Does this capacitor have any space left in it's buffer for the
     *         amount required.
     */
    public boolean hasSpace(int amount);
}
