package jcm2606.mods.sorcerycraft.block.tile.energy;

import jcm2606.mods.jccore.tile.TileEntityJC;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralEnergyNode;
import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketDrawAstralEnergyBeam;
import jcm2606.mods.sorcerycraft.energy.IEnergyBridge;
import jcm2606.mods.sorcerycraft.energy.IEnergyConsumer;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TileEntityConsumer extends TileEntityJC implements IEnergyConsumer {
    public boolean isPowered = false;
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        this.isPowered = tag.getBoolean("isPowered");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        tag.setBoolean("isPowered", this.isPowered);
    }
    
    public void setPowered(boolean b)
    {
        this.isPowered = b;
    }
    
    public boolean getPowered()
    {
        return this.isPowered;
    }
    
    @Override
    public void updateEntity() {
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
                                TileEntityAstralEnergyNode bridge = (TileEntityAstralEnergyNode) this.getWorldObj().getBlockTileEntity(x, y, z);
                                
                                this.setPowered(bridge.isTransportingEnergy);
                                
                                if(bridge.isTransportingEnergy)
                                {
                                    if(this.worldObj.isRemote)
                                    {
                                        PacketDispatcher.sendPacketToAllPlayers(PacketType.populatePacket(new PacketDrawAstralEnergyBeam(x + 0.5, y + 0.5, z + 0.5, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 5), PacketHandler.CHANNEL_SC));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
