package jcm2606.mods.sorcerycraft.core.handler;

import jcm2606.mods.sorcerycraft.client.gui.GuiAlchBook;
import jcm2606.mods.sorcerycraft.client.gui.GuiArcaneWorkbench;
import jcm2606.mods.sorcerycraft.client.gui.GuiAstralInfuser;
import jcm2606.mods.sorcerycraft.client.gui.GuiPortableCrafting;
import jcm2606.mods.sorcerycraft.core.lib.GuiIDs;
import jcm2606.mods.sorcerycraft.inventory.ContainerAlchBook;
import jcm2606.mods.sorcerycraft.inventory.ContainerArcaneWorkbench;
import jcm2606.mods.sorcerycraft.inventory.ContainerAstralInfuser;
import jcm2606.mods.sorcerycraft.inventory.ContainerPortableCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        
        if (ID == GuiIDs.ARCANE_WORKBENCH)
        {
            return new ContainerArcaneWorkbench(player.inventory, world, x, y, z);
        }
        
        if (ID == GuiIDs.ALCH_BOOK)
        {
            return new ContainerAlchBook();
        }
        
        if (ID == GuiIDs.PORTABLE_CRAFTING)
        {
            return new ContainerPortableCrafting(player.inventory, world, x, y, z);
        }
        
        if (ID == GuiIDs.ASTRAL_INFUSER)
        {
            return new ContainerAstralInfuser(player.inventory, world, x, y, z);
        }
        
        return null;
    }
    
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        
        if (ID == GuiIDs.ARCANE_WORKBENCH)
        {
            return new GuiArcaneWorkbench(player.inventory, world, x, y, z);
        }
        
        if (ID == GuiIDs.ALCH_BOOK)
        {
            return new GuiAlchBook(new ContainerAlchBook());
        }
        
        if (ID == GuiIDs.PORTABLE_CRAFTING)
        {
            return new GuiPortableCrafting(player, world, x, y, z);
        }
        
        if (ID == GuiIDs.ASTRAL_INFUSER)
        {
            return new GuiAstralInfuser(player.inventory, world, x, y, z);
        }
        
        return null;
    }
}
