package jcm2606.mods.sorcerycraft.block.tile.astral;

import java.util.List;

import jcm2606.mods.jccore.block.tile.TileEntityMultiblock;
import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.jccore.core.util.RenderUtil;
import jcm2606.mods.sorcerycraft.api.IExpandedSightHandler;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyCapacitor;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyCell;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;

import org.lwjgl.opengl.GL11;

public class TileAstralZoneCharger extends TileEntityMultiblock implements IEnergyCapacitor, IExpandedSightHandler
{
    public int energy;
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        energy = tag.getInteger("energyStored");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        tag.setInteger("energyStored", this.energy);
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        this.readFromNBT(pkt.data);
    }
    
    @Override
    public void updateEntity()
    {
        super.updateEntity();
        
        if(this.isValid)
        {
            for(int i = 0; i < 2; i++)
            {
                int divide = 1;
                
                if(this.worldObj.isRemote)
                {
                    this.worldObj.spawnParticle("portal", this.xCoord + 0.5 + (this.worldObj.rand.nextGaussian() / divide), this.yCoord + 0.5 + 1 + (this.worldObj.rand.nextGaussian() / divide), this.zCoord + 0.5 + (this.worldObj.rand.nextGaussian() / divide), 0, 0, 0);
                    this.worldObj.spawnParticle("portal", this.xCoord + 0.5 - (this.worldObj.rand.nextGaussian() / divide), this.yCoord + 0.5 + 1 - (this.worldObj.rand.nextGaussian() / divide), this.zCoord + 0.5 - (this.worldObj.rand.nextGaussian() / divide), 0, 0, 0);
                }
            }
            
            List playerEntities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(
                    this.xCoord - 1.5, this.yCoord + 1, this.zCoord - 1.5,
                    this.xCoord + 1.5, this.yCoord + 3, this.zCoord + 1.5));
            
            int amount = 1;
            
            for (int x = -2; x < 3; x++)
            {
                for (int y = 1; y < 4; y++)
                {
                    for (int z = -2; z < 3; z++)
                    {
                        int x2 = xCoord + x;
                        int y2 = yCoord + y;
                        int z2 = zCoord + z;
                        
                        if ((x > -2 && x < 2) && (z > -2 && z < 2))
                        {
                            continue;
                        }
                        
                        if ((x == 2 && z == 2) || (x == -2 && z == -2) || (x == -2 && z == 2) || (x == 2 && -z == 2))
                        {
                            if(y > 2)
                            {
                                if (worldObj.getBlockId(x2, y2, z2) == SCObjects.astralEnergyFieldDrain.blockID)
                                {
                                    amount = 5;
                                }
                            }
                        }
                    }
                }
            }
            
            for (Object obj : playerEntities) {
                EntityPlayer player = (EntityPlayer) obj;
                
                if(player.inventory.hasItem(SCObjects.astralCellEnergy.itemID))
                {
                    for(int i = 0; i < player.inventory.mainInventory.length; i++)
                    {
                        ItemStack stack = player.inventory.mainInventory[i];
                        
                        if(stack != null)
                        {
                            if(stack.getItem() instanceof ItemAstralEnergyCell)
                            {
                                ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack.getItem();
                                
                                if(cell.getEnergy(stack) < 1000 && cell.getEnergy(stack) + amount <= 1000)
                                {
                                    cell.setEnergy(stack, cell.getEnergy(stack) + this.capacitorProvideEnergy(amount));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public boolean isFormedCorrectly()
    {
        for (int x = -2; x < 3; x++)
        {
            for (int y = 1; y < 4; y++)
            {
                for (int z = -2; z < 3; z++)
                {
                    int x2 = xCoord + x;
                    int y2 = yCoord + y;
                    int z2 = zCoord + z;
                    
                    if ((x > -2 && x < 2) && (z > -2 && z < 2))
                    {
                        continue;
                    }
                    
                    if ((x == 2 && z == 2) || (x == -2 && z == -2) || (x == -2 && z == 2) || (x == 2 && -z == 2))
                    {
                        if(y < 3)
                        {
                            if (worldObj.getBlockId(x2, y2, z2) != SCObjects.astralStructure.blockID)
                            {
                                return false;
                            }
                        } else {
                            if (worldObj.getBlockId(x2, y2, z2) != Block.obsidian.blockID && worldObj.getBlockId(x2, y2, z2) != SCObjects.astralEnergyFieldDrain.blockID)
                            {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        
        return true;
    }
    
    @Override
    public int capacitorRequestEnergy(int amount, boolean ignoreLimit)
    {
        if (ignoreLimit)
        {
            this.energy += amount;
            
            return amount;
        }
        
        if (this.getEnergyStored() < this.getEnergyLimit())
        {
            this.energy += amount;
            
            return amount;
        }
        
        return 0;
    }
    
    @Override
    public int capacitorProvideEnergy(int amount)
    {
        if (this.getEnergyStored() > 0)
        {
            this.energy -= amount;
            
            return amount;
        } else
        {
            return 0;
        }
    }
    
    @Override
    public int getEnergyLimit()
    {
        return 100;
    }
    
    @Override
    public int getEnergyStored()
    {
        return this.energy;
    }
    
    @Override
    public boolean hasEnergy()
    {
        return this.getEnergyStored() > 0;
    }
    
    @Override
    public boolean hasSpace()
    {
        return this.getEnergyStored() < this.getEnergyLimit();
    }
    
    @Override
    public boolean hasSpace(int amount)
    {
        return this.energy < this.getEnergyLimit() && this.energy + amount <= this.getEnergyLimit();
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
        
        if (player.worldObj.getBlockTileEntity(x, y, z) != null)
        {
            if (player.worldObj.getBlockTileEntity(x, y, z) instanceof IEnergyCapacitor)
            {
                IEnergyCapacitor capacitor = (IEnergyCapacitor) player.worldObj.getBlockTileEntity(x, y, z);
                
                GL11.glPushMatrix();
                GL11.glScaled(0.5, 0.5, 0.5);
                Minecraft.getMinecraft().fontRenderer.drawString(
                        "\2477" + capacitor.getEnergyStored() + " AE / " + capacitor.getEnergyLimit() + " AE", RenderUtil.width + 8,
                        RenderUtil.height + 8, 0xffffff);
                GL11.glScaled(1, 1, 1);
                GL11.glPopMatrix();
            }
        }
    }
    
    @Override
    public boolean canRender(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        return this.isValid && (hasMedallion || player.capabilities.isCreativeMode);
    }
}
