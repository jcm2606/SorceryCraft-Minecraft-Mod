package jcm2606.mods.sorcerycraft.core.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import jcm2606.mods.sorcerycraft.core.network.packet.PacketItemUseTick;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketKeyPress;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketPlayerForgetAstralAbilitySync;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketPlayerForgetAbilitySync;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketSyncResearchData;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketSyncSkillData;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketWandUse;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 * Thanks to the EE3 source for this class!
 * 
 * @author pahimar
 */
public enum PacketType
{
    KEY(PacketKeyPress.class), WAND_USE(PacketWandUse.class), SYNC_RESEARCH_DATA(PacketSyncResearchData.class), ITEM_USE_TICK(PacketItemUseTick.class), SYNC_PLAYER_ABILITY_LEARN(
            PacketPlayerForgetAbilitySync.class), SYNC_PLAYER_ABILITY_FORGET(PacketPlayerForgetAstralAbilitySync.class), SYNC_SKILL_DATA(
                    PacketSyncSkillData.class);
    
    private Class<? extends PacketBase> clazz;
    
    PacketType(Class<? extends PacketBase> clazz)
    {
        this.clazz = clazz;
    }
    
    public static PacketBase buildPacket(byte[] data)
    {
        
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        int selector = bis.read();
        DataInputStream dis = new DataInputStream(bis);
        
        PacketBase packet = null;
        
        try
        {
            packet = values()[selector].clazz.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);
        }
        
        packet.readPopulate(dis);
        
        return packet;
    }
    
    public static PacketBase buildPacket(PacketType type)
    {
        
        PacketBase packet = null;
        
        try
        {
            packet = values()[type.ordinal()].clazz.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);
        }
        
        return packet;
    }
    
    public static Packet populatePacket(PacketBase packetBase, String channel)
    {
        
        byte[] data = packetBase.populate();
        
        Packet250CustomPayload packet250 = new Packet250CustomPayload();
        packet250.channel = channel;
        packet250.data = data;
        packet250.length = data.length;
        packet250.isChunkDataPacket = packetBase.isChunkDataPacket;
        
        return packet250;
    }
}
