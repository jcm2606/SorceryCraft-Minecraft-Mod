package jcm2606.mods.sorcerycraft.element;

import jcm2606.mods.sorcerycraft.api.IElement;
import net.minecraft.world.biome.BiomeGenBase;

public class ElementEarth implements IElement
{
    @Override
    public String getName()
    {
        return "Marl";
    }
    
    @Override
    public String getDescrambledName()
    {
        return "Earth";
    }
    
    @Override
    public BiomeGenBase[] getBiomes()
    {
        return null;
    }
    
    @Override
    public int getColour()
    {
        return 0x41A317;
    }
}
