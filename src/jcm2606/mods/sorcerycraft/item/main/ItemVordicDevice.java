package jcm2606.mods.sorcerycraft.item.main;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemVordicDevice extends SCItem {
	public ItemVordicDevice(int par1, String par2) {
		super(par1, par2);
		this.setNoRepair();
		this.setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * allows items to add custom lines of information to the mouseover description
	 */
	public void addInformation(ItemStack stack, EntityPlayer player, List list,
			boolean par4) {
		
	}

	public int getVordicCharge(ItemStack stack) {
		int damage = stack.getItemDamage();
		int damageLimit = stack.getMaxDamage();

		int i = (int) Math.round(damage * 100.0 / damageLimit);
		return 100 - i;
	}
}
