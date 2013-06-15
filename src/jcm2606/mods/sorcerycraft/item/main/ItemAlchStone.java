package jcm2606.mods.sorcerycraft.item.main;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.item.SCItem;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAlchStone extends SCItem {
	public ItemAlchStone(int par1) {
		super(par1, "alchStone");
		setFull3D();
		setNoRepair();
		setMaxDamage(256);
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

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack) {
		return false;
	}

	@Override
	public boolean getShareTag() {
		return true;
	}

	/*
	 * @Override
	 * 
	 * @SideOnly(Side.CLIENT) public boolean requiresMultipleRenderPasses() {
	 * return true; }
	 * 
	 * @Override
	 * 
	 * @SideOnly(Side.CLIENT) public int func_82790_a(ItemStack par1ItemStack,
	 * int par2) { return 0xB1FB17; }
	 */

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		return false;
	}

	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps
	 * to check if is on a player hand and update it's contents.
	 */
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {}
}
