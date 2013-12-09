package jcm2606.mods.sorcerycraft.block.astral;

import java.util.Random;

import jcm2606.mods.sorcerycraft.api.IEnergyInfused;
import jcm2606.mods.sorcerycraft.block.SCBlock;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.SCParticle;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOreAstral extends SCBlock implements IEnergyInfused
{
    public BlockOreAstral(int par1)
    {
        super(par1, Material.rock, "oreAstral");
        this.setHardness(2.5f);
        this.useIconIndex = false;
        this.renderID = -1;
        this.renderAsNormalBlock = false;
        this.isOpaqueCube = false;
    }
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return SCObjects.astralCrystal.itemID;
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random par1Random)
    {
        return 1 + par1Random.nextInt(2);
    }
    
    /**
     * Called when the block is attempted to be harvested
     */
    @Override
    public void onBlockHarvested(World world, int x, int y, int z, int par5, EntityPlayer player)
    {
        /*
         * for(int i = 0; i < 16; i++) { Random rand = new Random();
         * 
         * SCParticle.spawnParticle("vordicEnergy", x + 0.5 +
         * (rand.nextGaussian() / 3), y + 0.5 + (rand.nextGaussian() / 3), z +
         * 0.5 + (rand.nextGaussian() / 3), 0, -0.05, 0); }
         */
    }
    
    /**
     * Return true from this function if the player with silk touch can harvest
     * this block directly, and not it's normal drops.
     * 
     * @param world
     *            The world
     * @param player
     *            The player doing the harvesting
     * @param x
     *            X Position
     * @param y
     *            Y Position
     * @param z
     *            Z Position
     * @param metadata
     *            The metadata
     * @return True if the block can be directly harvested using silk touch
     */
    @Override
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        return true;
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
    
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random rand)
    {
        if (rand.nextDouble() < 0.5)
        {
            SCParticle.spawnAstralEnergyFX(par2 + 0.5 + (rand.nextGaussian()), par3 + 0.5 + (rand.nextGaussian()),
                    par4 + 0.5 + (rand.nextGaussian()), par2 + 0.5, par3 + 0.5, par4 + 0.5, 30, true, false, true, true);
            SCParticle.spawnAstralEnergyFX(par2 + 0.5 - (rand.nextGaussian()), par3 + 0.5 - (rand.nextGaussian()),
                    par4 + 0.5 - (rand.nextGaussian()), par2 + 0.5, par3 + 0.5, par4 + 0.5, 30, true, false, true, true);
        }
    }
    
    @Override
    public boolean destroyBlockWhenExtracted()
    {
        return true;
    }
    
    @Override
    public int getCharge()
    {
        return 1;
    }
    
    @Override
    public int getDestroyChance()
    {
        return 2;
    }
}
