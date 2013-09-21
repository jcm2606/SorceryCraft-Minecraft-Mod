package jcm2606.mods.sorcerycraft.inventory;

import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.manager.CraftingManagerArcaneWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

public class ContainerArcaneWorkbench extends Container
{
    /** The crafting matrix inventory (3x3). */
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public IInventory craftResult = new InventoryCraftResult();
    private final World worldObj;
    private final int posX;
    private final int posY;
    private final int posZ;
    
    public ContainerArcaneWorkbench(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5)
    {
        worldObj = par2World;
        posX = par3;
        posY = par4;
        posZ = par5;
        addSlotToContainer(new SlotCrafting(par1InventoryPlayer.player, craftMatrix, craftResult, 0, 124, 35));
        int var6;
        int var7;
        
        for (var6 = 0; var6 < 3; ++var6)
        {
            for (var7 = 0; var7 < 3; ++var7)
            {
                addSlotToContainer(new Slot(craftMatrix, var7 + var6 * 3, 30 + var7 * 18, 17 + var6 * 18));
            }
        }
        
        for (var6 = 0; var6 < 3; ++var6)
        {
            for (var7 = 0; var7 < 9; ++var7)
            {
                addSlotToContainer(new Slot(par1InventoryPlayer, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
            }
        }
        
        for (var6 = 0; var6 < 9; ++var6)
        {
            addSlotToContainer(new Slot(par1InventoryPlayer, var6, 8 + var6 * 18, 142));
        }
        
        onCraftMatrixChanged(craftMatrix);
    }
    
    /**
     * Callback for when the crafting matrix is changed.
     */
    @Override
    public void onCraftMatrixChanged(IInventory par1IInventory)
    {
        this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
        
        if (this.craftResult.getStackInSlot(0) == null)
        {
            this.craftResult.setInventorySlotContents(0, CraftingManagerArcaneWorkbench.getInstance().findMatchingRecipe(this.craftMatrix,
                    this.worldObj));
        }
    }
    
    /**
     * Callback for when the crafting gui is closed.
     */
    @Override
    public void onContainerClosed(EntityPlayer par1EntityPlayer)
    {
        super.onContainerClosed(par1EntityPlayer);
        
        if (!worldObj.isRemote)
        {
            for (int var2 = 0; var2 < 9; ++var2)
            {
                ItemStack var3 = craftMatrix.getStackInSlotOnClosing(var2);
                
                if (var3 != null)
                {
                    par1EntityPlayer.dropPlayerItem(var3);
                }
            }
        }
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return worldObj.getBlockId(posX, posY, posZ) != SCObjects.workbenchArcane.blockID ? false : par1EntityPlayer.getDistanceSq(posX + 0.5D,
                posY + 0.5D, posZ + 0.5D) <= 64.0D;
    }
    
    @Override
    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer player, int i)
    {
        ItemStack stack = null;
        Slot slot = (Slot) inventorySlots.get(i);
        
        if (slot != null && slot.getHasStack())
        {
            ItemStack var5 = slot.getStack();
            stack = var5.copy();
            
            if (i == 0)
            {
                if (!mergeItemStack(var5, 10, 46, true))
                {
                    return null;
                }
                
                slot.onSlotChange(var5, stack);
            } else
                if (i >= 10 && i < 37)
                {
                    if (!mergeItemStack(var5, 37, 46, false))
                    {
                        return null;
                    }
                } else
                    if (i >= 37 && i < 46)
                    {
                        if (!mergeItemStack(var5, 10, 37, false))
                        {
                            return null;
                        }
                    } else
                        if (!mergeItemStack(var5, 10, 46, false))
                        {
                            return null;
                        }
            
            if (var5.stackSize == 0)
            {
                slot.putStack((ItemStack) null);
            } else
            {
                slot.onSlotChanged();
            }
            
            if (var5.stackSize == stack.stackSize)
            {
                return null;
            }
            
            slot.onPickupFromSlot(player, var5);
        }
        
        return stack;
    }
}
