package jcm2606.mods.sorcerycraft.api.ring;

public class CoreType
{
    public int energyModifier = 0;
    public String name = "Wooden";
    
    public CoreType(String name)
    {
        this.name = name;
    }
    
    public CoreType setEnergyModifier(int amount)
    {
        this.energyModifier = amount;
        
        return this;
    }
}
