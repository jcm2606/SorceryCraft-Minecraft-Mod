package jcm2606.mods.sorcerycraft.block.tile.astral;

import jcm2606.mods.jccore.tile.TileEntityJC;
import jcm2606.mods.jccore.util.Coord;
import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketAstralEnergyNodeData;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketDrawAstralEnergyBeam;
import jcm2606.mods.sorcerycraft.energy.IEnergyBridge;
import jcm2606.mods.sorcerycraft.energy.IEnergyCreator;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;

public class TileEntityAstralEnergyNode extends TileEntityJC implements IEnergyBridge {
    public int connectedX;
    public int connectedY;
    public int connectedZ;
    public String connectedBlockType = "STANDARD";
    public boolean isTransportingEnergy = false;
    
    public final String CONNECTED_BLOCK_TYPE_CONSUMER = "CONSUMER";
    public final String CONNECTED_BLOCK_TYPE_CREATOR = "CREATOR";
    public final String CONNECTED_BLOCK_TYPE_BRIDGE = "BRIDGE";
    
    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        this.connectedX = tag.getInteger("connectedX");
        this.connectedY = tag.getInteger("connectedY");
        this.connectedZ = tag.getInteger("connectedZ");
        this.connectedBlockType = tag.getString("connectedBlockType");
        this.isTransportingEnergy = tag.getBoolean("isTransportingEnergy");
        
        PacketDispatcher.sendPacketToAllPlayers(PacketType.populatePacket(new PacketAstralEnergyNodeData(new Coord(xCoord, yCoord, zCoord), new Coord(this.connectedX, this.connectedY, this.connectedZ), this.connectedBlockType, this.isTransportingEnergy), PacketHandler.CHANNEL_SC));
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        tag.setInteger("connectedX", connectedX);
        tag.setInteger("connectedY", connectedY);
        tag.setInteger("connectedZ", connectedZ);
        tag.setString("connectedBlockType", connectedBlockType);
        tag.setBoolean("isTransportingEnergy", isTransportingEnergy);
    }
    
    public void setConnectedCoords(Coord coord)
    {
        this.connectedX = (int) coord.x;
        this.connectedY = (int) coord.y;
        this.connectedZ = (int) coord.z;
    }
    
    public Coord getConnectedCoords()
    {
        return new Coord(this.connectedX, this.connectedY, this.connectedZ);
    }
    
    public void setConnectedBlockType(Coord coord)
    {
        if(this.worldObj.getBlockId((int) coord.x, (int) coord.y, (int) coord.z) != 0)
        {
            if(this.worldObj.getBlockTileEntity((int) coord.x, (int) coord.y, (int) coord.z) != null)
            {
                if(this.worldObj.getBlockTileEntity((int) coord.x, (int) coord.y, (int) coord.z) instanceof IEnergyBridge)
                {
                    this.connectedBlockType = this.CONNECTED_BLOCK_TYPE_BRIDGE;
                }
            }
        }
    }
    
    @Override
    public void updateEntity() {
        for(int xC = 0; xC < 21; xC++)
        {
            for(int yC = 0; yC < 21; yC++)
            {
                for(int zC = 0; zC < 21; zC++)
                {
                    int x = xCoord - 11 + xC;
                    int y = yCoord - 11 + yC;
                    int z = zCoord - 11 + zC;
                    
                    if(this.getBlockAtCoords(x, y, z) != null)
                    {
                        if(this.getWorldObj().getBlockTileEntity(x, y, z) != null)
                        {
                            if(this.getWorldObj().getBlockTileEntity(x, y, z) instanceof IEnergyCreator)
                            {
                                this.isTransportingEnergy = true;
                                
                                if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
                                {
                                    PacketDispatcher.sendPacketToAllPlayers(PacketType.populatePacket(new PacketDrawAstralEnergyBeam(x + 0.5, y + 0.5, z + 0.5, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 4), PacketHandler.CHANNEL_SC));
                                }
                            }
                        }
                    }
                }
            }
        }
        
        if(!this.worldObj.blockHasTileEntity(connectedX, connectedY, connectedZ))
        {
            return;
        }
        
        TileEntityJC connectedTE = (TileEntityJC) this.worldObj.getBlockTileEntity(connectedX, connectedY, connectedZ);
        
        if(this.connectedBlockType == this.CONNECTED_BLOCK_TYPE_BRIDGE)
        {
            TileEntityAstralEnergyNode bridge = (TileEntityAstralEnergyNode) connectedTE;
            
            if(this.isTransportingEnergy)
            {
                bridge.isTransportingEnergy = true;
                
                if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
                {
                    PacketDispatcher.sendPacketToAllPlayers(PacketType.populatePacket(new PacketDrawAstralEnergyBeam(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, connectedX + 0.5, connectedY + 0.5, connectedZ + 0.5, 5), PacketHandler.CHANNEL_SC));
                }
            } else {
                bridge.isTransportingEnergy = false;
            }
        }
        
/*        
        for(int xC = 0; xC < 11; xC++)
        {
            for(int yC = 0; yC < 11; yC++)
            {
                for(int zC = 0; zC < 11; zC++)
                {
                    int x = xCoord - 5 + xC;
                    int y = yCoord - 5 + yC;
                    int z = zCoord - 5 + zC;
                    
                    if(this.getBlockAtCoords(x, y, z) != null)
                    {
                        if(this.getWorldObj().getBlockTileEntity(x, y, z) != null)
                        {
                            if(this.getWorldObj().getBlockTileEntity(x, y, z) instanceof IEnergyBridge)
                            {
                                TileEntityBridge bridge = (TileEntityBridge) this.getWorldObj().getBlockTileEntity(x, y, z);
                                
                                if(bridge.hasSupplierNearby)
                                {
                                    if(this.worldObj.isRemote)
                                    {
                                        PacketDispatcher.sendPacketToAllPlayers(PacketType.populatePacket(new PacketDrawAstralEnergyBeam(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, x + 0.5, y + 0.5, z + 0.5, 5), PacketHandler.CHANNEL_SC));
                                    }
                                    
                                    this.hasSupplierNearby = true;
                                }
                            }
                        }
                    }
                }
            }
        }*/
    }
}
