package jcm2606.mods.sorcerycraft.item.main;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCItemShine;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemElementalStar extends SCItemShine
{
    public ItemElementalStar(int par1)
    {
        super(par1, "elementalStar");
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.BASIC);
    }
}
