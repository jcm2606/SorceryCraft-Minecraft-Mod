package jcm2606.mods.sorcerycraft.item.tool;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCSword;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemSwordEnd extends SCSword {
    public ItemSwordEnd(int par1) {
        super(par1, SCObjects.SWORD_END, "toolSwordMagic");
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.LEGENDARY);
    }

    /**
     * Current implementations of this method in child classes do not use the
     * entry argument beside ev. They just raise the damage on the stack.
     */
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase living,
            EntityLivingBase living2)
    {
        super.hitEntity(stack, living, living2);
        return true;
    }
}
