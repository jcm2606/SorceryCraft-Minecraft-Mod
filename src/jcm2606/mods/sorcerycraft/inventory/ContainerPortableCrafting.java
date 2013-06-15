package jcm2606.mods.sorcerycraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.world.World;

public class ContainerPortableCrafting extends ContainerWorkbench {

    public ContainerPortableCrafting(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
        super(inventoryPlayer, world, x, y, z);
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return true;
    }
    
    @Override
    public void onCraftGuiClosed(EntityPlayer player) {
    	super.onCraftGuiClosed(player);
    }
}
