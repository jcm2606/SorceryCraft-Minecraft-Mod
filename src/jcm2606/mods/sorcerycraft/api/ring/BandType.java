package jcm2606.mods.sorcerycraft.api.ring;

public class BandType
{
    public int energyModifier = 0;
    public String name = "Wooden";
    
    public BandType(String name)
    {
        this.name = name;
    }
    
    public BandType setEnergyModifier(int amount)
    {
        this.energyModifier = amount;
        
        return this;
    }
}
