package jcm2606.mods.sorcerycraft.inventory;

import jcm2606.mods.jccore.client.slot.SlotLocked;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerAstralInfuser extends Container
{
    private final World worldObj;
    private final int posX;
    private final int posY;
    private final int posZ;
    private final TileAstralInfuser tile;
    
    public ContainerAstralInfuser(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5)
    {
        this.worldObj = par2World;
        this.posX = par3;
        this.posY = par4;
        this.posZ = par5;
        this.tile = (TileAstralInfuser) par2World.getBlockTileEntity(par3, par4, par5);
        
        this.addSlotToContainer(new SlotLocked(this.tile, 5, 79, 71));
        
        this.addSlotToContainer(new Slot(this.tile, 0, 80, 16));
        this.addSlotToContainer(new Slot(this.tile, 1, 132, 54));
        this.addSlotToContainer(new Slot(this.tile, 2, 111, 118));
        this.addSlotToContainer(new Slot(this.tile, 3, 49, 117));
        this.addSlotToContainer(new Slot(this.tile, 4, 25, 54));
        
        for (int i = 0; i < 3; i++)
        {
            for (int k = 0; k < 9; k++)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, k + i * 9 + 9, 8 + k * 18, 158 + i * 18));
            }
        }
        
        for (int j = 0; j < 9; j++)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, j, 8 + j * 18, 216));
        }
        
        this.detectAndSendChanges();
    }
    
    /**
     * Callback for when the crafting matrix is changed.
     */
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        
        ((TileAstralInfuser) this.worldObj.getBlockTileEntity(this.posX, this.posY, this.posZ)).setInventorySlotContents(5,
                ((TileAstralInfuser) this.worldObj.getBlockTileEntity(this.posX, this.posY, this.posZ)).getResult());
    }
    
    /**
     * Callback for when the crafting gui is closed.
     */
    @Override
    public void onContainerClosed(EntityPlayer par1EntityPlayer)
    {
        super.onContainerClosed(par1EntityPlayer);
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return true;
    }
    
    @Override
    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer player, int i)
    {
        return null;
    }
}
