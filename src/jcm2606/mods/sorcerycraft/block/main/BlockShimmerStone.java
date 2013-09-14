package jcm2606.mods.sorcerycraft.block.main;

import jcm2606.mods.sorcerycraft.block.SCBlock;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockShimmerStone extends SCBlock
{
    public BlockShimmerStone(int par1, String par2)
    {
        super(par1, Material.rock, par2, Rarities.BASIC);
        this.setHardness(1.8f);
        this.setResistance(1.4f);
    }
    
    // @Override
    // @SideOnly(Side.CLIENT)
    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied
     * against the blocks color. Note only called when first determining what to
     * render.
     */
    /*
     * public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int
     * par3, int par4) { int var5 = 0; int var6 = 0; int var7 = 0;
     * 
     * for (int var8 = -1; var8 <= 1; ++var8) { for (int var9 = -1; var9 <= 1;
     * ++var9) { int var10 = par1IBlockAccess.getBiomeGenForCoords(par2 + var9,
     * par4 + var8).getBiomeGrassColor(); var5 += (var10 & 16711680) >> 16; var6
     * += (var10 & 65280) >> 8; var7 += var10 & 255; } }
     * 
     * return (var5 / 9 & 255) << 16 | (var6 / 9 & 255) << 8 | var7 / 9 & 255; }
     */
    
    @Override
    public boolean isBeaconBase(World worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return true;
    }
}
