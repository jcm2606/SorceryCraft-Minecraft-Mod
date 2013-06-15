package jcm2606.mods.sorcerycraft.enchant;

import jcm2606.mods.sorcerycraft.item.tool.ItemSwordFire;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class EnchantmentFireswordInternalHeat extends Enchantment {

	public EnchantmentFireswordInternalHeat(int par1, int par2) {
		super(par1, par2, EnumEnchantmentType.weapon);
		this.setName("internalHeat");
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
    	if(stack.getItem() instanceof ItemSwordFire)
		{
			return true;
		} else {
			return false;
		}
    }
    
    @Override
	public boolean canApply(ItemStack stack)
    {
		if(stack.getItem() instanceof ItemSwordFire)
		{
			return true;
		} else {
			return false;
		}
    }
	
	@Override
	public int getMinEnchantability(int par1)
	{
		return 20;
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
         String enchantmentName = "Internal Magma Core";
         return enchantmentName + " " + StatCollector.translateToLocal("enchantment.level." + par1);
	}
}