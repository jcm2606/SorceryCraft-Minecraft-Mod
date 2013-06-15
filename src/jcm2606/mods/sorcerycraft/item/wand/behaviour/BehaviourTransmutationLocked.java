package jcm2606.mods.sorcerycraft.item.wand.behaviour;

import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.handler.AlchemyHandler;
import jcm2606.mods.sorcerycraft.item.wand.EnumWandBehaviour;
import jcm2606.mods.sorcerycraft.item.wand.ItemWandCasting;
import jcm2606.mods.sorcerycraft.item.wand.WandManager;
import jcm2606.mods.sorcerycraft.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BehaviourTransmutationLocked extends Behaviour implements IBlockInteractionHandler {
	public BehaviourTransmutationLocked() {
		super(WandManager.getNextAvailableBehaviourId(), "Locked Transmutation", EnumWandBehaviour.blockInteract);
		this.setBehaviourClass(this);
		this.icon = Reference.PATH_TEXTURES_GUI_HUD + "wand/wand_transmutation_locked.png";
	}

	@Override
	public Block getBlock(ItemStack stack, EntityPlayer player) {
		return null;
	}

	@Override
	public boolean onBlockInteract(ItemStack wand, ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z) {
		if(stack != null)
		{
			if(AlchemyHandler.performInWorldTransmutation(wand, stack, player, world, x, y, z))
			{
				ItemWandCasting castingWand = (ItemWandCasting) wand.getItem();
				
				castingWand.playWandSound("sorcerycraft.wand_use", player, world, 0.25f, 1.0f);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public Item reqItem(ItemStack wand) {
		return SCObjects.alchstone;
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
