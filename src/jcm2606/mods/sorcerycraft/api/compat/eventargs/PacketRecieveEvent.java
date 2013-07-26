package jcm2606.mods.sorcerycraft.api.compat.eventargs;

import jcm2606.mods.jccore.compat.container.ContainerEventBase;
import jcm2606.mods.sorcerycraft.api.compat.HandlerMethodID;
import jcm2606.mods.sorcerycraft.core.network.PacketBase;

public class PacketRecieveEvent extends ContainerEventBase {
    public String packetChannel;
    public byte[] packetData;
    public PacketBase packet;
    
    public PacketRecieveEvent(String packetChannel, byte[] packetData, PacketBase packet)
    {
        super(HandlerMethodID.PACKET_RECIEVE);
        this.packetChannel = packetChannel;
        this.packetData = packetData;
        this.packet = packet;
    }
}
