package jcm2606.mods.sorcerycraft.element;

import jcm2606.mods.sorcerycraft.api.IElement;
import net.minecraft.world.biome.BiomeGenBase;

public class ElementLight implements IElement
{
    @Override
    public String getName()
    {
        return "Lucent";
    }
    
    @Override
    public String getDescrambledName()
    {
        return "Light";
    }
    
    @Override
    public BiomeGenBase[] getBiomes()
    {
        return null;
    }
    
    @Override
    public int getColour()
    {
        return 0xFFFFCC;
    }
}
