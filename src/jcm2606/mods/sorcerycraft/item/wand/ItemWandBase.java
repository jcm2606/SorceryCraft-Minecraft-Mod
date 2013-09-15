package jcm2606.mods.sorcerycraft.item.wand;

import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.api.IWand;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public abstract class ItemWandBase extends SCItem implements IWand
{
    public int chargeCount;
    
    public ItemWandBase(int par1, String par2)
    {
        super(par1, par2);
        this.setMaxStackSize(1);
        this.setMaxDamage(1200);
        this.setNoRepair();
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
    }
}
