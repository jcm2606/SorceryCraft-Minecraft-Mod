package jcm2606.mods.sorcerycraft.element;

import jcm2606.mods.sorcerycraft.api.IElement;
import net.minecraft.world.biome.BiomeGenBase;

public class ElementDark implements IElement
{
    @Override
    public String getName()
    {
        return "Mar";
    }
    
    @Override
    public String getDescrambledName()
    {
        return "Dark";
    }
    
    @Override
    public BiomeGenBase[] getBiomes()
    {
        return null;
    }
    
    @Override
    public int getColour()
    {
        return 0x2B1B17;
    }
}
