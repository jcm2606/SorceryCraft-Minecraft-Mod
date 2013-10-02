package jcm2606.mods.sorcerycraft.block.tile.astral;

import jcm2606.mods.jccore.block.tile.TileEntityJC;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileAstralCapacitorStructure extends TileEntityJC
{
    public boolean isFormed = false;
    
    public TileEntity coreTileEntity;
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        this.isFormed = tag.getBoolean("isFormed");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        tag.setBoolean("isFormed", this.isFormed);
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        this.readFromNBT(pkt.data);
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }
    
    @Override
    public void updateEntity()
    {
        super.updateEntity();
        
        if (coreTileEntity == null)
        {
            this.isFormed = false;
        } else
        {
            this.coreTileEntity = this.worldObj.getBlockTileEntity(coreTileEntity.xCoord, coreTileEntity.yCoord, coreTileEntity.zCoord);
        }
    }
    
    public void setCoreTileEntity(TileEntity tile)
    {
        this.coreTileEntity = tile;
    }
}
