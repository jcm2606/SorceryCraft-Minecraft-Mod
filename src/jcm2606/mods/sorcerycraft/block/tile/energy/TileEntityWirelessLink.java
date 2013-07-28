package jcm2606.mods.sorcerycraft.block.tile.energy;

import jcm2606.mods.jccore.tile.TileEntityJC;
import jcm2606.mods.jccore.util.Coord;
import jcm2606.mods.jccore.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketAstralEnergyNodeData;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketDrawAstralEnergyBeam;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TileEntityWirelessLink extends TileEntityJC
{
    public Coord connectedCoordSet;
    public boolean isConnectedToSource;
    public boolean hasPower;
    
    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        int x = tag.getInteger("connectedX");
        int y = tag.getInteger("connectedY");
        int z = tag.getInteger("connectedZ");
        isConnectedToSource = tag.getBoolean("hasSource");
        hasPower = tag.getBoolean("hasPower");
        
        connectedCoordSet = new Coord(x, y, z);
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        tag.setInteger("connectedX", (int) connectedCoordSet.x);
        tag.setInteger("connectedY", (int) connectedCoordSet.y);
        tag.setInteger("connectedZ", (int) connectedCoordSet.z);
        tag.setBoolean("hasSource", this.isConnectedToSource);
        tag.setBoolean("hasPower", this.hasPower);
    }
    
    @Override
    public void updateEntity() {
        if(connectedCoordSet != null)
        {
            if(hasPower)
            {
                if(worldObj.blockHasTileEntity((int) this.connectedCoordSet.x, (int) this.connectedCoordSet.y, (int) this.connectedCoordSet.z))
                {
                    TileEntityWirelessLink link = (TileEntityWirelessLink) worldObj.getBlockTileEntity((int) this.connectedCoordSet.x, (int) this.connectedCoordSet.y, (int) this.connectedCoordSet.z);
                    
                    link.hasPower = true;
                    
                    if(GeneralUtil.isClient())
                    {
                        PacketDispatcher.sendPacketToAllPlayers(PacketType.populatePacket(new PacketDrawAstralEnergyBeam(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5,  this.connectedCoordSet.x + 0.5,  this.connectedCoordSet.y + 0.5,  this.connectedCoordSet.z + 0.5, 5), PacketHandler.CHANNEL_SC));
                        
                        PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketAstralEnergyNodeData(new Coord(link.xCoord, link.yCoord, link.zCoord), link.connectedCoordSet, true), PacketHandler.CHANNEL_SC));
                    }
                    
                    return;
                }
            }
        }
        
        if(!this.isConnectedToSource)
        {
            this.hasPower = false;
        }
    }
}
