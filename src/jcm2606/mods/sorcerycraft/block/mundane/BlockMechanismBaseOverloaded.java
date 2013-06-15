package jcm2606.mods.sorcerycraft.block.mundane;

import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.block.ITransmutable;
import jcm2606.mods.sorcerycraft.block.SCBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockMechanismBaseOverloaded extends SCBlock implements ITransmutable {
	public BlockMechanismBaseOverloaded(int par1, Material par3Material) {
		super(par1, par3Material, "mundaneMechanismBaseOverloaded");
		this.setHardness(5.0f);
		this.setResistance(99999.0f);
	}

	@Override
	public Block getRequiredBlock(ItemStack stack) {
		return SCObjects.mechanismbase;
	}

	@Override
	public int getTransmuteCost(ItemStack stack, Block block) {
		return 2;
	}

	@Override
	public void onTransmute(ItemStack stack, Block block, EntityPlayer player,
			World world, int x, int y, int z) {}
}
