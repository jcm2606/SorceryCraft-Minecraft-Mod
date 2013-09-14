package jcm2606.mods.sorcerycraft.enchant;

import jcm2606.mods.sorcerycraft.item.tool.ItemSwordElement;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class EnchantmentElementalDamage extends Enchantment
{
    public EnchantmentElementalDamage(int par1)
    {
        super(par1, 2, EnumEnchantmentType.weapon);
    }
    
    /**
     * Returns the minimal value of enchantability needed on the enchantment
     * level passed.
     */
    @Override
    public int getMinEnchantability(int par1)
    {
        return 1;
    }
    
    /**
     * Returns the maximum value of enchantability nedded on the enchantment
     * level passed.
     */
    @Override
    public int getMaxEnchantability(int par1)
    {
        return 120;
    }
    
    /**
     * Returns the correct traslated name of the enchantment and the level in
     * roman numbers.
     */
    @Override
    public String getTranslatedName(int par1)
    {
        String s = StatCollector.translateToLocal("Elemental Damaging");
        return s + " " + StatCollector.translateToLocal("enchantment.level." + par1);
    }
    
    /**
     * Determines if the enchantment passed can be applyied together with this
     * enchantment.
     */
    @Override
    public boolean canApplyTogether(Enchantment par1Enchantment)
    {
        return this != par1Enchantment && this != par1Enchantment.sharpness;
    }
    
    @Override
    public boolean canApply(ItemStack par1ItemStack)
    {
        return this.type.canEnchantItem(par1ItemStack.getItem()) && par1ItemStack.getItem() instanceof ItemSwordElement;
    }
    
    /**
     * This applies specifically to applying at the enchanting table. The other
     * method {@link #func_92037_a(ItemStack)} applies for <i>all possible</i>
     * enchantments.
     * 
     * @param stack
     * @return
     */
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return this.type.canEnchantItem(stack.getItem()) && stack.getItem() instanceof ItemSwordElement;
    }
}
