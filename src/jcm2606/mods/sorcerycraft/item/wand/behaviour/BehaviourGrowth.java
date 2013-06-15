package jcm2606.mods.sorcerycraft.item.wand.behaviour;

import jcm2606.mods.jccore.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.item.wand.EnumWandBehaviour;
import jcm2606.mods.sorcerycraft.item.wand.WandManager;
import jcm2606.mods.sorcerycraft.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BehaviourGrowth extends Behaviour implements
		IBlockInteractionHandler {
	public BehaviourGrowth() {
		super(WandManager.getNextAvailableBehaviourId(), "Organic Growth", EnumWandBehaviour.blockInteract);
		this.setBehaviourClass(this);
		this.icon = Reference.PATH_TEXTURES_GUI_HUD + "wand/wand_growth.png";
	}

	@Override
	public Block getBlock(ItemStack stack, EntityPlayer player) {
		return null;
	}

	@Override
	public boolean onBlockInteract(ItemStack wand, ItemStack stack,
			EntityPlayer player, World world, int x, int y, int z) {
		if (GeneralUtil.emulateBonemeal(wand, player, world, x, y, z,
				world.rand)) {
			return true;
		}
		return false;
	}

	@Override
	public Item reqItem(ItemStack wand) {
		return null;
	}

	@Override
	public void wandSound(ItemStack wand,
			EntityPlayer player, World world, int x, int y, int z, boolean wasSuccessful) {
	}

	@Override
	public boolean useCustomWandSound() {
		return false;
	}
}
