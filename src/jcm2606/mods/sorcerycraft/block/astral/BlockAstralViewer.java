package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.lib.Rarities;
import jcm2606.mods.sorcerycraft.tile.astral.TileEntityAstralViewer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralViewer extends BlockAstral {
	public BlockAstralViewer(int par1) {
		super(par1, Material.glass, "astralViewer", Rarities.BASIC, "astral_viewer_anim");
		this.useIconIndex = false;
		this.renderAsNormalBlock = false;
		this.isOpaqueCube = false;
		this.renderID = -1;
		this.setHardness(2.5f);
		this.setResistance(6.0f);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityAstralViewer();
	}
}
