package jcm2606.mods.sorcerycraft.core.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import jcm2606.mods.jccore.util.Coord;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralEnergyNode;
import jcm2606.mods.sorcerycraft.core.network.PacketBase;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.Player;

public class PacketAstralEnergyNodeData extends PacketBase {
    public Coord coord;
    public Coord connectedCoord;
    public String connectionType;
    public boolean isTransferringEnergy;
    
    public PacketAstralEnergyNodeData() {
        super(PacketType.ASTRAL_ENERGY_NODE_DATA, true);
    }
    
    public PacketAstralEnergyNodeData(Coord coord, Coord connectedCoord, String connectionType, boolean isTransferring)
    {
        super(PacketType.ASTRAL_ENERGY_NODE_DATA, true);
        this.coord = coord;
        this.connectedCoord = connectedCoord;
        this.connectionType = connectionType;
        this.isTransferringEnergy = isTransferring;
    }

    @Override
    public void readData(DataInputStream data) throws IOException
    {
        int currentX = data.readInt();
        int currentY = data.readInt();
        int currentZ = data.readInt();
        
        this.coord = new Coord(currentX, currentY, currentZ);
        
        int connectedX = data.readInt();
        int connectedY = data.readInt();
        int connectedZ = data.readInt();
        
        this.connectedCoord = new Coord(connectedX, connectedY, connectedZ);
        
        this.connectionType = data.readUTF();
        
        this.isTransferringEnergy = data.readBoolean();
    }

    @Override
    public void writeData(DataOutputStream dos) throws IOException
    {
        dos.writeInt((int) this.coord.x);
        dos.writeInt((int) this.coord.y);
        dos.writeInt((int) this.coord.z);
        dos.writeInt((int) this.connectedCoord.x);
        dos.writeInt((int) this.connectedCoord.y);
        dos.writeInt((int) this.connectedCoord.z);
        dos.writeUTF(this.connectionType);
        dos.writeBoolean(this.isTransferringEnergy);
    }

    @Override
    public void execute(INetworkManager network, Player player)
    {
        EntityPlayer entityPlayer = (EntityPlayer) player;
        
        TileEntity te = entityPlayer.worldObj.getBlockTileEntity((int) this.coord.x, (int) this.coord.x, (int) this.coord.x);
        
        if(te != null)
        {
            if(te instanceof TileEntityAstralEnergyNode)
            {
                TileEntityAstralEnergyNode node = (TileEntityAstralEnergyNode) te;
                
                node.setConnectedCoords(this.coord);
                node.connectedBlockType = this.connectionType;
                node.isTransportingEnergy = this.isTransferringEnergy;
            }
        }
    }
}
