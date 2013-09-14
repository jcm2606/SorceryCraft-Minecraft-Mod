package jcm2606.mods.sorcerycraft.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class SCOre extends SCBlock
{
    public SCOre(int par1, String par2)
    {
        super(par1, Material.rock, par2);
        this.setResistance(2.0f);
    }
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.blockID;
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }
    
    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i'
     * (inclusive).
     */
    @Override
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        if (par1 > 0 && this.blockID != this.idDropped(0, par2Random, par1))
        {
            int var3 = par2Random.nextInt(par1 + 2) - 1;
            
            if (var3 < 0)
            {
                var3 = 0;
            }
            
            return this.quantityDropped(par2Random) * (var3 + 1);
        } else
        {
            return this.quantityDropped(par2Random);
        }
    }
    
    /**
     * Drops the block items with a specified chance of dropping the specified
     * items
     */
    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
        
        if (this.idDropped(par5, par1World.rand, par7) != this.blockID)
        {
            int var8 = 0;
            
            this.dropXpOnBlockBreak(par1World, par2, par3, par4, var8);
        }
    }
}
