package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.jccore.item.ItemBlockJC;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockAstralEnergyNodeItem extends ItemBlockJC
{
    public BlockAstralEnergyNodeItem(int par1)
    {
        super(par1);
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.BASIC);
    }
    
    /**
     * Called to actually place the block, after the location is determined
     * and all permission checks have been made.
     *
     * @param stack The item stack that was used to place the block. This can be changed inside the method.
     * @param player The player who is placing the block. Can be null if the block is not being placed by a player.
     * @param side The side the player (or machine) right-clicked on.
     */
    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
        if (!world.setBlock(x, y, z, this.getBlockID(), metadata, 3))
        {
            return false;
        }
        
        if (world.getBlockId(x, y, z) == this.getBlockID())
        {
            Block.blocksList[this.getBlockID()].onBlockPlacedBy(world, x, y, z, player, stack);
            
            world.setBlockMetadataWithNotify(x, y, z, side, 2);
            
            Block.blocksList[this.getBlockID()].onPostBlockPlaced(world, x, y, z, metadata);
        }
        
        return true;
    }
}
