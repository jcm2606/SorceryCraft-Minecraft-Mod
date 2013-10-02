package jcm2606.mods.sorcerycraft.api.energy;

public enum EnumEnergyType
{
    LOW(1, 10),
    MID(10, 50),
    HIGH(50, 200),
    ULTRA(200, 1000);
    
    public int min;
    public int max;
    
    EnumEnergyType(int min, int max)
    {
        this.min = min;
        this.max = max;
    }
    
    public int getEnergyMinimum()
    {
        return this.min;
    }
    
    public int getEnergyMaximum()
    {
        return this.max;
    }
    
    public boolean isInRange(int amount)
    {
        return amount >= min && amount <= max;
    }
}
