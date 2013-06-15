package jcm2606.mods.sorcerycraft.enchant;

import jcm2606.mods.sorcerycraft.item.SCSword;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class EnchantmentVenom extends Enchantment {

	public EnchantmentVenom(int par1, int par2) {
		super(par1, par2, EnumEnchantmentType.weapon);
		this.setName("venomousAspect");
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
		}
		
		if(par1 == 2)
		{
			 return 30;
		}
		
		return 50;
	}

	@Override
	public int getMaxEnchantability(int par1)
	{
         return 200;
	}

	@Override
	public int getMaxLevel()
	{
         return 3;
	}

	@Override
	public String getTranslatedName(int par1)
	{
         String enchantmentName = "Venomous Aspect";
         return enchantmentName + " " + StatCollector.translateToLocal("enchantment.level." + par1);
	}
}
