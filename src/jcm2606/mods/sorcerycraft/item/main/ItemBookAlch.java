package jcm2606.mods.sorcerycraft.item.main;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.SorceryCraft;
import jcm2606.mods.sorcerycraft.item.SCItem;
import jcm2606.mods.sorcerycraft.lib.GuiIDs;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBookAlch extends SCItem {
	public ItemBookAlch(int par1) {
		super(par1, "bookAlch");
		setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Return an item rarity from EnumRarity
	 */
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return RarityHelper.getCustomRarityType(Rarities.BASIC);
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		if (!player.isSneaking()) {
			player.openGui(SorceryCraft.instance, GuiIDs.ALCH_BOOK, world, (int) player.posX, (int) player.posY, (int) player.posZ);
		}

		return stack;
	}
}
