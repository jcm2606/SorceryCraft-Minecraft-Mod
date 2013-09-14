package jcm2606.mods.sorcerycraft.element;

import jcm2606.mods.sorcerycraft.api.IElement;
import net.minecraft.world.biome.BiomeGenBase;

public class ElementEnergy implements IElement
{
    @Override
    public String getName()
    {
        return "Vim";
    }
    
    @Override
    public String getDescrambledName()
    {
        return "Energy";
    }
    
    @Override
    public BiomeGenBase[] getBiomes()
    {
        return null;
    }
    
    @Override
    public int getColour()
    {
        return 0xEBF4FA;
    }
}
