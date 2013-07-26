package jcm2606.mods.sorcerycraft.item.astral;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemAstralCrystalPlate extends SCItem {
    public ItemAstralCrystalPlate(int par1) {
        super(par1, "astralCrystalPlate");
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.BASIC);
    }
    
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return false;
    }
}