package jcm2606.mods.sorcerycraft.api;

public interface IEnergyInfused
{
    public boolean destroyBlockWhenExtracted();
    
    public int getCharge();
    
    /**
     * @return Chance out of 10000
     */
    public int getDestroyChance();
}
