package jcm2606.mods.sorcerycraft.core.network;

import jcm2606.mods.jccore.compat.container.CompatibilityContainer;
import jcm2606.mods.sorcerycraft.api.compat.HandlerMethodID;
import jcm2606.mods.sorcerycraft.api.compat.eventargs.PacketRecieveEvent;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
    public static final String CHANNEL_SC = "Mod_SC";
    
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        PacketBase packetBase = PacketType.buildPacket(packet.data);
        
        PacketRecieveEvent packetRecievedEvent = new PacketRecieveEvent(packet.channel, packet.data, packetBase);
        CompatibilityContainer.postUpdate(HandlerMethodID.PACKET_RECIEVE, packetRecievedEvent);
        
        packetBase.execute(manager, player);
    }
}
