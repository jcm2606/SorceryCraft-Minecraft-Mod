package jcm2606.mods.sorcerycraft.block.main;

import java.util.Random;

import jcm2606.mods.sorcerycraft.block.SCOre;
import jcm2606.mods.sorcerycraft.core.SCObjects;

public class BlockOreDarkQuartz extends SCOre
{
    public BlockOreDarkQuartz(int par1)
    {
        super(par1, "oreDarkQuartz");
        this.setHardness(3f);
    }
    
    @Override
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return SCObjects.darkquartz.itemID;
    }
}
