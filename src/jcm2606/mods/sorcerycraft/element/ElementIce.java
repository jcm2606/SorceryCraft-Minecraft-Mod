package jcm2606.mods.sorcerycraft.element;

import jcm2606.mods.sorcerycraft.api.IElement;
import net.minecraft.world.biome.BiomeGenBase;

public class ElementIce implements IElement
{
    @Override
    public String getName()
    {
        return "Floe";
    }
    
    @Override
    public String getDescrambledName()
    {
        return "Ice";
    }
    
    @Override
    public BiomeGenBase[] getBiomes()
    {
        return null;
    }
    
    @Override
    public int getColour()
    {
        return 0x95B9C7;
    }
}
