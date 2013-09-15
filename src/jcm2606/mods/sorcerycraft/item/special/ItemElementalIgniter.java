package jcm2606.mods.sorcerycraft.item.special;

import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCItemShine;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemElementalIgniter extends SCItemShine
{
    public ItemElementalIgniter(int par1)
    {
        super(par1, "elementalIgniter");
        this.maxStackSize = 1;
        this.setMaxDamage(512);
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
    }
    
    /**
     * Callback for item usage. If the item does something special on right
     * clicking, he will have one of those. Return True if something happen and
     * false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7,
            float par8, float par9, float par10)
    {
        if (par7 == 0)
        {
            --par5;
        }
        
        if (par7 == 1)
        {
            ++par5;
        }
        
        if (par7 == 2)
        {
            --par6;
        }
        
        if (par7 == 3)
        {
            ++par6;
        }
        
        if (par7 == 4)
        {
            --par4;
        }
        
        if (par7 == 5)
        {
            ++par4;
        }
        
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            return false;
        } else
        {
            if (par3World.isAirBlock(par4, par5, par6))
            {
                par3World.playSoundEffect(par4 + 0.5D, par5 + 0.5D, par6 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                par3World.setBlock(par4, par5, par6, Block.fire.blockID);
            }
            
            par1ItemStack.damageItem(1, par2EntityPlayer);
            return true;
        }
    }
}
