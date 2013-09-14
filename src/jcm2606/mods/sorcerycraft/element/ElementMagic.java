package jcm2606.mods.sorcerycraft.element;

import jcm2606.mods.sorcerycraft.api.IElement;
import net.minecraft.world.biome.BiomeGenBase;

public class ElementMagic implements IElement
{
    @Override
    public String getName()
    {
        return "Herm";
    }
    
    @Override
    public String getDescrambledName()
    {
        return "Magic";
    }
    
    @Override
    public BiomeGenBase[] getBiomes()
    {
        return null;
    }
    
    @Override
    public int getColour()
    {
        return 0xB041FF;
    }
}
