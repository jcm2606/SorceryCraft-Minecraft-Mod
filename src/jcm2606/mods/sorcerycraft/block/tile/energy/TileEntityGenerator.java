package jcm2606.mods.sorcerycraft.block.tile.energy;

import jcm2606.mods.jccore.tile.TileEntityJC;
import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketDrawAstralEnergyBeam;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.PacketDispatcher;

public abstract class TileEntityGenerator extends TileEntityJC
{
    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
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
                            if(this.getWorldObj().getBlockTileEntity(x, y, z) instanceof TileEntityWirelessLink)
                            {
                                TileEntityWirelessLink link = (TileEntityWirelessLink) worldObj.getBlockTileEntity(x, y, z);
                                
                                link.hasPower = true;
                                link.isConnectedToSource = true;
                                
                                if(this.worldObj.isRemote)
                                {
                                    PacketDispatcher.sendPacketToAllPlayers(PacketType.populatePacket(new PacketDrawAstralEnergyBeam(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, x + 0.5, y + 0.5, z + 0.5, 4), PacketHandler.CHANNEL_SC));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
