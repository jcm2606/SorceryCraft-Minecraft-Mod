package jcm2606.mods.sorcerycraft.enchant;

import jcm2606.mods.sorcerycraft.item.SCSword;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class EnchantmentAeronautics extends Enchantment {

	public EnchantmentAeronautics(int par1, int par2) {
		super(par1, par2, EnumEnchantmentType.weapon);
		this.setName("aeronautics");
	}

	@Override
	public int getMinEnchantability(int par1)
	{
		if(par1 == 1)
		{
			return 15;
		} else
			if(par1 == 2)
			{
				 return 20;
			} else
			{
				return 30;
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
         return 3;
	}
	
	@Override
	public String getTranslatedName(int par1)
	{
         String enchantmentName = "Aeronautics";
         return enchantmentName + " " + StatCollector.translateToLocal("enchantment.level." + par1);
	}
	
    @Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
    	if(stack.getItem() instanceof SCSword)
		{
			return true;
		} else {
			return false;
		}
    }
    
    @Override
	public boolean canApply(ItemStack stack)
    {
		if(stack.getItem() instanceof SCSword)
		{
			return true;
		} else {
			return false;
		}
    }
}
