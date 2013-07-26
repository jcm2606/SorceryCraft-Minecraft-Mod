package jcm2606.mods.sorcerycraft.core.handler;

import jcm2606.mods.sorcerycraft.core.lib.GuiIDs;
import jcm2606.mods.sorcerycraft.gui.GuiAlchBook;
import jcm2606.mods.sorcerycraft.gui.GuiArcaneWorkbench;
import jcm2606.mods.sorcerycraft.gui.GuiInfuseTablet;
import jcm2606.mods.sorcerycraft.gui.GuiPortableCrafting;
import jcm2606.mods.sorcerycraft.inventory.ContainerAlchBook;
import jcm2606.mods.sorcerycraft.inventory.ContainerArcaneWorkbench;
import jcm2606.mods.sorcerycraft.inventory.ContainerInfuseTablet;
import jcm2606.mods.sorcerycraft.inventory.ContainerPortableCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		
		if (ID == GuiIDs.INFUSE_TABLET) {
			return new ContainerInfuseTablet(player.inventory, world, x, y, z);
		}

		if (ID == GuiIDs.ARCANE_WORKBENCH) {
			return new ContainerArcaneWorkbench(player.inventory, world, x, y, z);
		}
		
		if (ID == GuiIDs.ALCH_BOOK) {
			return new ContainerAlchBook();
		}
		
		if (ID == GuiIDs.PORTABLE_CRAFTING)
		{
			return new ContainerPortableCrafting(player.inventory, world, x, y, z);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		
		if (ID == GuiIDs.INFUSE_TABLET) {
			return new GuiInfuseTablet(player.inventory, world, x, y, z);
		}

		if (ID == GuiIDs.ARCANE_WORKBENCH) {
			return new GuiArcaneWorkbench(player.inventory, world, x, y, z);
		}
		
		if (ID == GuiIDs.ALCH_BOOK) {
			return new GuiAlchBook(new ContainerAlchBook());
		}
		
		if (ID == GuiIDs.PORTABLE_CRAFTING)
		{
			return new GuiPortableCrafting(player, world, x, y, z);
		}

		return null;
	}
}
