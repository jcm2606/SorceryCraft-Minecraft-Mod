package jcm2606.mods.sorcerycraft.block.tile.astral;

import java.util.ArrayList;

import jcm2606.mods.jccore.block.tile.TileEntityJC;
import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.jccore.core.util.RenderUtil;
import jcm2606.mods.sorcerycraft.api.IExpandedSightHandler;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyCapacitor;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyReciever;
import jcm2606.mods.sorcerycraft.client.fx.FXFissure;
import jcm2606.mods.sorcerycraft.manager.CraftingManagerAstralInfuser;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class TileAstralInfuser extends TileEntityJC implements IInventory, IEnergyReciever, IExpandedSightHandler
{
    public ItemStack[] stacks;
    
    public TileAstralInfuser()
    {
        this.stacks = new ItemStack[6];
    }
    
    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        NBTTagList tagList = tag.getTagList("StoredItems");
        
        this.stacks = new ItemStack[this.getSizeInventory()];
        
        for (int i = 0; i < tagList.tagCount(); i++)
        {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            
            byte b = tagCompound.getByte("Item");
            
            if (b >= 0 && (b < this.stacks.length))
            {
                this.stacks[b] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
    }
    
    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        NBTTagList tagList = new NBTTagList();
        
        for (int i = 0; i < this.stacks.length; i++)
        {
            if (this.stacks[i] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                
                tagCompound.setByte("Item", (byte) i);
                
                this.stacks[i].writeToNBT(tagCompound);
                
                tagList.appendTag(tagCompound);
            }
        }
        
        tag.setTag("StoredItems", tagList);
    }
    
    @Override
    public void updateEntity()
    {
        super.updateEntity();
        
        if (this.hasSource())
        {
            if (GeneralUtil.isClient())
            {
                FXFissure fx = new FXFissure(this.worldObj, this.xCoord + 0.5f, this.yCoord + 0.5f, this.zCoord + 0.5f, 2);
                fx.ignoreAge = true;
                fx.scale = 0.0125f;
                
                Minecraft.getMinecraft().effectRenderer.addEffect(fx);
            }
        }
        
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS)
        {
            if (GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord, this.zCoord, side, this.worldObj) instanceof IEnergyCapacitor)
            {
                IEnergyCapacitor capacitor = ((IEnergyCapacitor) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord, this.zCoord, side,
                        this.worldObj));
                
                if (capacitor.hasEnergy() && capacitor.getEnergyStored() >= this.getEnergyRequirement())
                {
                    capacitor.takeEnergy(this.getEnergyRequirement());
                    
                    break;
                }
            }
        }
    }
    
    @Override
    public int getSizeInventory()
    {
        return this.stacks.length;
    }
    
    @Override
    public ItemStack getStackInSlot(int i)
    {
        return this.stacks[i];
    }
    
    @Override
    public ItemStack decrStackSize(int slot, int amt)
    {
        ItemStack stack = this.getStackInSlot(slot);
        if (stack != null)
        {
            if (stack.stackSize <= amt)
            {
                this.setInventorySlotContents(slot, null);
            } else
            {
                stack = stack.splitStack(amt);
                if (stack.stackSize == 0)
                {
                    this.setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(int i)
    {
        return this.stacks[i];
    }
    
    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack)
    {
        this.stacks[i] = itemstack;
        if ((itemstack != null) && (itemstack.stackSize > this.getInventoryStackLimit()))
        {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }
    
    @Override
    public String getInvName()
    {
        return "Astral Infuser";
    }
    
    @Override
    public boolean isInvNameLocalized()
    {
        return false;
    }
    
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return true;
    }
    
    @Override
    public void openChest()
    {
    }
    
    @Override
    public void closeChest()
    {
    }
    
    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
        return true;
    }
    
    public ItemStack getResult()
    {
        ArrayList list = new ArrayList();
        
        for (int i = 0; i < 5; i++)
        {
            if (this.stacks[i] != null)
            {
                ItemStack stack = new ItemStack(this.stacks[i].itemID, 1, this.stacks[i].getItemDamage());
                
                list.add(stack);
            }
        }
        
        if (list.size() > 0)
        {
            return CraftingManagerAstralInfuser.getInstance().getResult(list.toArray());
        }
        
        return null;
    }
    
    public boolean hasSource()
    {
        boolean foundSource = false;
        
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS)
        {
            if (GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord, this.zCoord, side, this.worldObj) instanceof IEnergyCapacitor)
            {
                IEnergyCapacitor capacitor = ((IEnergyCapacitor) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord, this.zCoord, side,
                        this.worldObj));
                
                if (capacitor.hasEnergy() && capacitor.getEnergyStored() >= this.getEnergyRequirement())
                {
                    foundSource = true;
                }
            }
        }
        
        return foundSource;
    }
    
    @Override
    public void recieveEnergy(int energy)
    {
    }
    
    @Override
    public int getEnergyRequirement()
    {
        return 1;
    }
    
    @Override
    public void renderOverlay(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        MovingObjectPosition mop = GeneralUtil.getTargetBlock(player.worldObj, player, false, 5.0f);
        
        if (mop == null)
        {
            return;
        }
        
        int x = mop.blockX;
        int y = mop.blockY;
        int z = mop.blockZ;
        
        GL11.glPushMatrix();
        GL11.glScaled(0.5, 0.5, 0.5);
        Minecraft.getMinecraft().fontRenderer.drawString("\2477-" + this.getEnergyRequirement() + " Psy/t", RenderUtil.width + 8,
                RenderUtil.height + 8, 0xffffff);
        GL11.glScaled(1, 1, 1);
        GL11.glPopMatrix();
    }
    
    @Override
    public boolean canRender(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        return hasMedallion || player.capabilities.isCreativeMode;
    }
}
