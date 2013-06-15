package jcm2606.mods.sorcerycraft.handler;

import jcm2606.mods.sorcerycraft.gui.GuiAlchBook;
import jcm2606.mods.sorcerycraft.gui.GuiAlchLectern;
import jcm2606.mods.sorcerycraft.gui.GuiInfuseTablet;
import jcm2606.mods.sorcerycraft.gui.GuiPortableCrafting;
import jcm2606.mods.sorcerycraft.gui.note.GuiNoteCharm;
import jcm2606.mods.sorcerycraft.inventory.ContainerAlchBook;
import jcm2606.mods.sorcerycraft.inventory.ContainerAlchLectern;
import jcm2606.mods.sorcerycraft.inventory.ContainerChestSorcerer;
import jcm2606.mods.sorcerycraft.inventory.ContainerInfuseTablet;
import jcm2606.mods.sorcerycraft.inventory.ContainerNoteCharms;
import jcm2606.mods.sorcerycraft.inventory.ContainerPortableCrafting;
import jcm2606.mods.sorcerycraft.lib.GuiIDs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
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

		if (ID == GuiIDs.ALCH_LECTERN) {
			return new ContainerAlchLectern(player.inventory, world, x, y, z);
		}
		
		if (ID == GuiIDs.ALCH_BOOK) {
			return new ContainerAlchBook();
		}
		
		if (ID == GuiIDs.PORTABLE_CRAFTING)
		{
			return new ContainerPortableCrafting(player.inventory, world, x, y, z);
		}
		
		if (ID == GuiIDs.CHEST_SORCERER)
		{
			return new ContainerChestSorcerer(player.inventory, (IInventory) world.getBlockTileEntity(x, y, z));
		}
		
		if (ID == GuiIDs.NOTE_CHARMS)
		{
			return new ContainerNoteCharms();
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

		if (ID == GuiIDs.ALCH_LECTERN) {
			return new GuiAlchLectern(player.inventory, world, x, y, z);
		}
		
		if (ID == GuiIDs.ALCH_BOOK) {
			return new GuiAlchBook(new ContainerAlchBook());
		}
		
		if (ID == GuiIDs.PORTABLE_CRAFTING)
		{
			return new GuiPortableCrafting(player, world, x, y, z);
		}
		
		if (ID == GuiIDs.NOTE_CHARMS)
		{
			return new GuiNoteCharm(new ContainerNoteCharms());
		}

		return null;
	}
}
