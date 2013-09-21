package jcm2606.mods.sorcerycraft.block.tile.astral;

import jcm2606.mods.jccore.core.util.GeneralUtil;
import net.minecraft.block.Block;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.ForgeDirection;

public class TileAstralThermalkineticConvertor extends TileAstralKineticConvertorBase
{
    @Override
    public int provide()
    {
        int num = 0;
        
        if(BiomeDictionary.isBiomeOfType(this.worldObj.getBiomeGenForCoords(xCoord, zCoord), Type.DESERT) || BiomeDictionary.isBiomeOfType(this.worldObj.getBiomeGenForCoords(xCoord, zCoord), Type.NETHER))
        {
            num += 1;
        }
        
        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
        {
            if(GeneralUtil.getBlockIdFromNeighbour(xCoord, yCoord, zCoord, direction, worldObj) == Block.furnaceBurning.blockID)
            {
                num += 1;
            }
        }
        
        return num;
    }
}
