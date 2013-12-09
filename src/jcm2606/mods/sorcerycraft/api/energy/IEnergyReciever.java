package jcm2606.mods.sorcerycraft.api.energy;

/**
 * Interface for any energy recievers.
 */
public interface IEnergyReciever
{
    /**
     * Called when an energy provider sends energy to this reciever.
     * 
     * @param energy
     */
    public void recieveEnergy(int energy);
    
    /**
     * @return 0 for any amount
     */
    public int getEnergyRequirement();
}
