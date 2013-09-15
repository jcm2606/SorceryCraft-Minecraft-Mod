package jcm2606.mods.sorcerycraft.item.main;

import java.util.List;

import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCItemShine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemIngotAlchMetal extends SCItemShine
{
    public ItemIngotAlchMetal(int par1)
    {
        super(par1, "ingotAlchMetal");
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.BASIC);
    }
    
    @Override
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
    }
}
