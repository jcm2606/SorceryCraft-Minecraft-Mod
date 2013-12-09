package jcm2606.mods.sorcerycraft.core.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import jcm2606.mods.sorcerycraft.core.network.PacketBase;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.research.ResearchData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;

public class PacketSyncResearchData extends PacketBase
{
    int points;
    
    public PacketSyncResearchData()
    {
        super(PacketType.SYNC_RESEARCH_DATA, false);
    }
    
    public PacketSyncResearchData(int points)
    {
        super(PacketType.SYNC_RESEARCH_DATA, false);
        
        this.points = points;
    }
    
    @Override
    public void readData(DataInputStream data) throws IOException
    {
        this.points = data.readInt();
    }
    
    @Override
    public void writeData(DataOutputStream dos) throws IOException
    {
        dos.writeInt(this.points);
    }
    
    @Override
    public void execute(INetworkManager network, Player player)
    {
        ResearchData researchProps = ((ResearchData) ((EntityPlayer) player).getExtendedProperties(ResearchData.NAME));
        
        researchProps.setResearchPoints(this.points);
    }
}
