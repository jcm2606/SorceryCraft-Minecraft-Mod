package jcm2606.mods.sorcerycraft.element;

import jcm2606.mods.sorcerycraft.api.IElement;
import net.minecraft.world.biome.BiomeGenBase;

public class ElementFire implements IElement
{
    @Override
    public String getName()
    {
        return "Pyre";
    }
    
    @Override
    public String getDescrambledName()
    {
        return "Fire";
    }
    
    @Override
    public BiomeGenBase[] getBiomes()
    {
        return null;
    }
    
    @Override
    public int getColour()
    {
        return 0xFF4719;
    }
}
