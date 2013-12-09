package jcm2606.mods.sorcerycraft.world.gen;

import java.util.Random;

import jcm2606.mods.jccore.core.util.GenerationUtil;
import jcm2606.mods.jccore.core.util.GeneratorBase;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import net.minecraft.world.World;

public class GenCore extends GeneratorBase
{
    @Override
    public void generateSurface(World world, Random rand, int blockX, int blockZ)
    {
        GenerationUtil.addOreGen(32, SCObjects.oreAstral.blockID, 8, 2, blockX, blockZ, rand, world);
    }
    
    @Override
    public void generateNether(World world, Random rand, int blockX, int blockZ)
    {
    }
    
    @Override
    public void generateEnd(World world, Random rand, int blockX, int blockZ)
    {
    }
    
    @Override
    public void generateOther(int dimensionID, World world, Random rand, int blockX, int blockZ)
    {
    }
}
