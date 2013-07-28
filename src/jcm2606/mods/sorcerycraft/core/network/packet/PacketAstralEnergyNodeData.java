package jcm2606.mods.sorcerycraft.core.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import jcm2606.mods.jccore.util.Coord;
import jcm2606.mods.sorcerycraft.block.tile.energy.TileEntityWirelessLink;
import jcm2606.mods.sorcerycraft.core.network.PacketBase;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.Player;

public class PacketAstralEnergyNodeData extends PacketBase {
    public Coord coord;
    public Coord connectedCoord;
    public boolean hasEnergy;
    
    public PacketAstralEnergyNodeData() {
        super(PacketType.ASTRAL_ENERGY_NODE_DATA, true);
    }
    
    public PacketAstralEnergyNodeData(Coord coord, Coord connectedCoord, boolean hasEnergy)
    {
        super(PacketType.ASTRAL_ENERGY_NODE_DATA, true);
        this.coord = coord;
        this.connectedCoord = connectedCoord;
        this.hasEnergy = hasEnergy;
    }

    @Override
    public void readData(DataInputStream data) throws IOException
    {
        int currentX = data.readInt();
        int currentY = data.readInt();
        int currentZ = data.readInt();
        
        this.coord = new Coord(currentX, currentY, currentZ);
        
        if(this.connectedCoord != null)
        {
            int connectedX = data.readInt();
            int connectedY = data.readInt();
            int connectedZ = data.readInt();
            
            this.connectedCoord = new Coord(connectedX, connectedY, connectedZ);
        }
        
        this.hasEnergy = data.readBoolean();
    }

    @Override
    public void writeData(DataOutputStream dos) throws IOException
    {
        dos.writeInt((int) this.coord.x);
        dos.writeInt((int) this.coord.y);
        dos.writeInt((int) this.coord.z);
        if(this.connectedCoord != null)
        {
            dos.writeInt((int) this.connectedCoord.x);
            dos.writeInt((int) this.connectedCoord.y);
            dos.writeInt((int) this.connectedCoord.z);
        }
        dos.writeBoolean(this.hasEnergy);
    }

    @Override
    public void execute(INetworkManager network, Player player)
    {
        EntityPlayer entityPlayer = (EntityPlayer) player;
        
        TileEntity te = entityPlayer.worldObj.getBlockTileEntity((int) this.coord.x, (int) this.coord.x, (int) this.coord.x);
        
        if(te != null)
        {
            if(te instanceof TileEntityWirelessLink)
            {
                TileEntityWirelessLink link = (TileEntityWirelessLink) te;
                
                link.connectedCoordSet = this.connectedCoord;
                link.hasPower = hasEnergy;
            }
        }
    }
}
