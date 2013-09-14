package jcm2606.mods.sorcerycraft.element;

import jcm2606.mods.sorcerycraft.api.IElement;
import net.minecraft.world.biome.BiomeGenBase;

public class ElementAir implements IElement
{
    @Override
    public String getName()
    {
        return "Zephyr";
    }
    
    @Override
    public String getDescrambledName()
    {
        return "Air";
    }
    
    @Override
    public BiomeGenBase[] getBiomes()
    {
        return null;
    }
    
    @Override
    public int getColour()
    {
        return 0xFFE87C;
    }
}
