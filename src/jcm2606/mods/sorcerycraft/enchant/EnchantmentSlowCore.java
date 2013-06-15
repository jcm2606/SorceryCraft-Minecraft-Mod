package jcm2606.mods.sorcerycraft.enchant;

import jcm2606.mods.sorcerycraft.item.SCSword;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class EnchantmentSlowCore extends Enchantment {

	public EnchantmentSlowCore(int par1, int par2) {
		super(par1, par2, EnumEnchantmentType.weapon);
		this.setName("frozenCore");
	}

	@Override
	public boolean canApply(ItemStack par1ItemStack)
    {
        return this.type.canEnchantItem(par1ItemStack.getItem()) && par1ItemStack.getItem() instanceof SCSword;
    }
	
	@Override
	public int getMinEnchantability(int par1)
	{
		if(par1 == 1)
		{
			return 20;
		} else
		{
			 return 40;
		}
	}

	@Override
	public int getMaxEnchantability(int par1)
	{
         return 200;
	}

	@Override
	public int getMaxLevel()
	{
         return 2;
	}

	@Override
	public String getTranslatedName(int par1)
	{
         String enchantmentName = "Frozen Core";
         return enchantmentName + " " + StatCollector.translateToLocal("enchantment.level." + par1);
	}
	
	/**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    @Override
	public boolean canApplyTogether(Enchantment par1Enchantment)
    {
    	if(par1Enchantment != Enchantment.fireAspect)
    	{
    		return true;
    	} else {
    		return false;
    	}
    }
}
