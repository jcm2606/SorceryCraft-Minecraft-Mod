package jcm2606.mods.sorcerycraft.network;

import jcm2606.mods.sorcerycraft.SorceryCraft;
import jcm2606.mods.sorcerycraft.network.packet.PacketBase;
import jcm2606.mods.sorcerycraft.network.packet.PacketBase.ProtocolException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler implements IPacketHandler {
	@Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
            try {
                EntityPlayer entityPlayer = (EntityPlayer)player;
                ByteArrayDataInput in = ByteStreams.newDataInput(packet.data);
                int packetId = in.readUnsignedByte();
                PacketBase packetBase = PacketBase.constructPacket(packetId);
                packetBase.read(in);
                packetBase.execute(entityPlayer, entityPlayer.worldObj.isRemote ? Side.CLIENT : Side.SERVER);
        } catch (ProtocolException e) {
                if (player instanceof EntityPlayerMP) {
                    ((EntityPlayerMP) player).playerNetServerHandler.kickPlayerFromServer("Protocol Exception!");
                    SorceryCraft.logger.warning( "Player " + ((EntityPlayer)player).username + " caused a Protocol Exception!");
                }
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException("Unexpected Reflection exception during Packet construction!", e);
            }
    }
}
