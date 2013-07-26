package jcm2606.mods.sorcerycraft.item.main;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemTomeKnowledge extends SCItem {
    public ItemTomeKnowledge(int par1) {
        super(par1, "bookKnowledge");
        this.setMaxStackSize(1);
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
    }
}
