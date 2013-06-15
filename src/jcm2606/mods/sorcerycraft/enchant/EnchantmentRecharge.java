package jcm2606.mods.sorcerycraft.enchant;

import jcm2606.mods.sorcerycraft.item.main.ItemInvisCloak;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class EnchantmentRecharge extends Enchantment {

	public EnchantmentRecharge(int par1, int par2) {
		super(par1, par2, EnumEnchantmentType.all);
		this.setName("recharge");
	}

	@Override
	public int getMinEnchantability(int par1)
	{
		return 10;
	}

	@Override
	public int getMaxEnchantability(int par1)
	{
         return 200;
	}

	@Override
	public int getMaxLevel()
	{
         return 1;
	}
	
	@Override
	public String getTranslatedName(int par1)
	{
         String enchantmentName = "Ender Recharging";
         return enchantmentName + " " + StatCollector.translateToLocal("enchantment.level." + par1);
	}
	
    @Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
    	if(stack.getItem() instanceof ItemInvisCloak || stack.getItem() instanceof ItemBook)
		{
			return true;
		} else {
			return false;
		}
    }
    
    @Override
	public boolean canApply(ItemStack stack)
    {
		if(stack.getItem() instanceof ItemInvisCloak || stack.getItem() instanceof ItemBook)
		{
			return true;
		} else {
			return false;
		}
    }
}
