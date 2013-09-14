package jcm2606.mods.sorcerycraft.element;

import jcm2606.mods.sorcerycraft.api.IElement;
import net.minecraft.world.biome.BiomeGenBase;

public class ElementWater implements IElement
{
    @Override
    public String getName()
    {
        return "Agua";
    }
    
    @Override
    public String getDescrambledName()
    {
        return "Water";
    }
    
    @Override
    public BiomeGenBase[] getBiomes()
    {
        return null;
    }
    
    @Override
    public int getColour()
    {
        return 0x2B65EC;
    }
}
