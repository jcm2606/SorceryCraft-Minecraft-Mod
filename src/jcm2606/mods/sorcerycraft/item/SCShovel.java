package jcm2606.mods.sorcerycraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;

public class SCShovel extends SCTool
{
    /** an array of the blocks this spade is effective against */
    public static final Block[] blocksEffectiveAgainst = new Block[]
    { Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow, Block.blockSnow, Block.blockClay, Block.tilledField, Block.slowSand,
            Block.mycelium };
    
    public SCShovel(int par1, EnumToolMaterial par2EnumToolMaterial, String par3)
    {
        super(par1, 1, par2EnumToolMaterial, blocksEffectiveAgainst, par3);
    }
    
    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    @Override
    public boolean canHarvestBlock(Block par1Block)
    {
        return par1Block == Block.snow ? true : par1Block == Block.blockSnow;
    }
}
