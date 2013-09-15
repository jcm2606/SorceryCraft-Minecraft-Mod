package jcm2606.mods.sorcerycraft.item.astral;

import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAstralCrystal extends SCItem
{
    public ItemAstralCrystal(int par1)
    {
        super(par1, "astralCrystal");
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.BASIC);
    }
    
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
        if (world.getBlockId(x, y, z) == Block.blockIron.blockID)
        {
            world.setBlock(x, y, z, SCObjects.astralsteelblock.blockID, 1, 2);
            
            if (!player.capabilities.isCreativeMode)
            {
                stack.stackSize--;
            }
            
            return true;
        }
        
        return false;
    }
}
