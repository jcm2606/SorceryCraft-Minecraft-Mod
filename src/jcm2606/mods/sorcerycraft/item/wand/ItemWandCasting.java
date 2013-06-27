package jcm2606.mods.sorcerycraft.item.wand;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWandCasting extends ItemWand {
	public ItemWandCasting(int par1) {
		super(par1, "wandCasting");
		this.setMaxDamage(120);
	}

	@Override
    public boolean shouldRotateAroundWhenRendering() {
		return false;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return RarityHelper.getCustomRarityType(Rarities.BASIC);
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
			int x, int y, int z, int par7, float par8, float par9, float par10) {
		return WandManager.performCastingWandBlockInteractionEffect(stack, world, player, x, y, z);
	}

	@Override
    public boolean itemInteractionForEntity(ItemStack stack,
			EntityLiving entity) {
            return WandManager.performCastingWandEntityInteractionEffect(stack, entity.worldObj, entity);
	}

	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps
	 * to check if is on a player hand and update it's contents.
	 */
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World,
			Entity par3Entity, int par4, boolean par5) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * allows items to add custom lines of information to the mouseover description
	 */
	public void addInformation(ItemStack stack, EntityPlayer player, List list,
			boolean par4) {
		if (stack.getItemDamage() != 0) {
			list.add("Damaged (" + stack.getItemDamage() + ")");
		}
	}
}
