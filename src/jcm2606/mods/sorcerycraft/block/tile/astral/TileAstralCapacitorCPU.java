package jcm2606.mods.sorcerycraft.block.tile.astral;

import jcm2606.mods.jccore.block.tile.TileEntityMultiblock;
import jcm2606.mods.jccore.core.util.Coord;
import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.jccore.core.util.RenderUtil;
import jcm2606.mods.sorcerycraft.api.IExpandedSightHandler;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyCapacitor;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralCapacitorCPU;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class TileAstralCapacitorCPU extends TileEntityMultiblock implements IEnergyCapacitor, IExpandedSightHandler
{
    public int energyStored;
    public int timer;
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        energyStored = tag.getInteger("energyStored");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        tag.setInteger("energyStored", this.energyStored);
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }
    
    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side)
    {
        return 0;
    }
    
    @Override
    public void updateEntity()
    {
        super.updateEntity();
        
        for (int x = -1; x < 2; x++)
        {
            for (int y = -1; y < 2; y++)
            {
                for (int z = -1; z < 2; z++)
                {
                    int x2 = xCoord + x;
                    int y2 = yCoord + y;
                    int z2 = zCoord + z;
                    
                    if (x == 0 && y == 0 && z == 0)
                    {
                        continue;
                    }
                    
                    if (this.worldObj.getBlockTileEntity(x2, y2, z2) != null)
                    {
                        if (this.worldObj.getBlockTileEntity(x2, y2, z2) instanceof TileAstralCapacitorStructure)
                        {
                            TileAstralCapacitorStructure structure = (TileAstralCapacitorStructure) this.worldObj.getBlockTileEntity(x2, y2, z2);
                            
                            structure.setCoreTileEntity(worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord));
                            structure.isFormed = this.isValid;
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        this.readFromNBT(pkt.data);
    }
    
    @Override
    public int getEnergyLimit()
    {
        return BlockAstralCapacitorCPU.capacityIntList[this.getWorldObj().getBlockMetadata(xCoord, yCoord, zCoord)];
    }
    
    @Override
    public int getEnergyStored()
    {
        return this.energyStored;
    }
    
    @Override
    public int capacitorRequestEnergy(int amount, boolean ignoreLimit)
    {
        if (!this.isValid)
        {
            return 0;
        }
        
        if (this.getEnergyStored() < this.getEnergyLimit())
        {
            this.energyStored += amount;
            
            return amount;
        }
        
        return 0;
    }
    
    @Override
    public int capacitorProvideEnergy(int amount)
    {
        if (!this.isValid)
        {
            return 0;
        }
        
        if (this.getEnergyStored() > 0)
        {
            this.energyStored -= amount;
            
            return amount;
        } else
        {
            return 0;
        }
    }
    
    @Override
    public boolean hasEnergy()
    {
        return this.energyStored > 0;
    }
    
    @Override
    public boolean hasSpace()
    {
        return this.energyStored < this.getEnergyLimit();
    }
    
    @Override
    public boolean hasSpace(int amount)
    {
        return this.energyStored < this.getEnergyLimit() && this.energyStored + amount <= this.getEnergyLimit();
    }
    
    @Override
    public boolean isFormedCorrectly()
    {
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
        {
            if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, worldObj))
            {
                if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, worldObj) != SCObjects.astralCapacitorIOInterface.blockID)
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        
        for (int x = -1; x < 2; x++)
        {
            for (int y = -1; y < 2; y++)
            {
                for (int z = -1; z < 2; z++)
                {
                    int x2 = xCoord + x;
                    int y2 = yCoord + y;
                    int z2 = zCoord + z;
                    
                    if (x == 0 && y == 0 && z == 0)
                    {
                        continue;
                    }
                    
                    if (GeneralUtil.isNeighbouringBlock(new Coord(xCoord, yCoord, zCoord), new Coord(x2, y2, z2), this.worldObj))
                    {
                        continue;
                    }
                    
                    if (this.worldObj.getBlockId(x2, y2, z2) != SCObjects.astralCapacitorHousing.blockID)
                    {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    @Override
    public void renderOverlay(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        if (!this.isValid)
        {
            return;
        }
        
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_GUI_HUD + "overlay.png");
        
        RenderUtil.instance().drawTextureRect(RenderUtil.width / 4 - 19, RenderUtil.height / 2 + 3, 0, 28, 25, 34, 2.5f, 1.0f, 1.0f);
        
        IEnergyCapacitor capacitor = (IEnergyCapacitor) player.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
        
        GL11.glPushMatrix();
        GL11.glScaled(0.5, 0.5, 0.5);
        Minecraft.getMinecraft().fontRenderer.drawString("\2477" + capacitor.getEnergyStored() + " AE / " + capacitor.getEnergyLimit() + " AE",
                RenderUtil.width + 25, RenderUtil.height + 10, 0xffffff);
        GL11.glScaled(1, 1, 1);
        GL11.glPopMatrix();
    }
    
    @Override
    public boolean canRender(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        return hasMedallion || player.capabilities.isCreativeMode;
    }
}
