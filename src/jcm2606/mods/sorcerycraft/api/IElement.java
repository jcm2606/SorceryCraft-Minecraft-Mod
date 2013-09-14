package jcm2606.mods.sorcerycraft.api;

import net.minecraft.world.biome.BiomeGenBase;

public interface IElement
{
    public String getName();
    
    public String getDescrambledName();
    
    public BiomeGenBase[] getBiomes();
    
    public int getColour();
}
