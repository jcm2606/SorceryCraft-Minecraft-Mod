package jcm2606.mods.sorcerycraft.core.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import jcm2606.mods.sorcerycraft.core.network.PacketBase;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.skill.SkillData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;

public class PacketSyncSkillData extends PacketBase
{
    int points;
    
    public PacketSyncSkillData()
    {
        super(PacketType.SYNC_SKILL_DATA, false);
    }
    
    public PacketSyncSkillData(int points)
    {
        super(PacketType.SYNC_SKILL_DATA, false);
        
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
        SkillData data = ((SkillData) ((EntityPlayer) player).getExtendedProperties(SkillData.NAME));
    }
}
