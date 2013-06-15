package jcm2606.mods.sorcerycraft.block;

import jcm2606.mods.sorcerycraft.tile.TileEntityStonePodium;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockStonePodium extends SCBlockContainer {
	public BlockStonePodium(int par1) {
		super(par1, Material.rock, "stonePodium");
		this.useIconIndex = false;
		this.setHardness(2.4f);
		this.setBlockBounds(0f, 0f, 0f, 1f, 0.95f, 1f);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityStonePodium();
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
		this.setDefaultDirection(par1World, par2, par3, par4);
	}

	/**
	 * set a blocks direction
	 */
	private void setDefaultDirection(World par1World, int par2, int par3,
			int par4) {
		if (!par1World.isRemote) {
			int var5 = par1World.getBlockId(par2, par3, par4 - 1);
			int var6 = par1World.getBlockId(par2, par3, par4 + 1);
			int var7 = par1World.getBlockId(par2 - 1, par3, par4);
			int var8 = par1World.getBlockId(par2 + 1, par3, par4);
			byte var9 = 3;

			if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6]) {
				var9 = 3;
			}

			if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5]) {
				var9 = 2;
			}

			if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8]) {
				var9 = 5;
			}

			if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7]) {
				var9 = 4;
			}

			par1World.setBlockMetadataWithNotify(par2, par3, par4, var9, 0);
		}
	}

	/**
	 * Called when the block is placed in the world.
	 */
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLiving par5EntityLiving, ItemStack par6ItemStack) {
		int var6 = MathHelper
				.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (var6 == 0) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 0);
		}

		if (var6 == 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 0);
		}

		if (var6 == 2) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 0);
		}

		if (var6 == 3) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 0);
		}
	}
}
