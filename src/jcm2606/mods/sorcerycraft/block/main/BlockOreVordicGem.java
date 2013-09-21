package jcm2606.mods.sorcerycraft.block.main;

import java.util.Random;

import jcm2606.mods.sorcerycraft.block.SCOre;
import jcm2606.mods.sorcerycraft.core.SCObjects;

public class BlockOreVordicGem extends SCOre
{
    
    public BlockOreVordicGem(int par1)
    {
        super(par1, "oreVordicGem");
        this.setHardness(2.0f);
    }
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return SCObjects.blockVordicGem.blockID;
    }
}
