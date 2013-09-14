package jcm2606.mods.sorcerycraft.core.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import jcm2606.mods.sorcerycraft.api.IKeyBound;
import jcm2606.mods.sorcerycraft.core.network.PacketBase;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;

public class PacketKeyPress extends PacketBase
{
    String key;
    
    public PacketKeyPress()
    {
        super(PacketType.KEY, false);
    }
    
    public PacketKeyPress(String key)
    {
        super(PacketType.KEY, false);
        this.key = key;
    }
    
    @Override
    public void readData(DataInputStream data) throws IOException
    {
        key = data.readUTF();
    }
    
    @Override
    public void writeData(DataOutputStream dos) throws IOException
    {
        dos.writeUTF(key);
    }
    
    @Override
    public void execute(INetworkManager network, Player player)
    {
        EntityPlayer thePlayer = (EntityPlayer) player;
        
        if (thePlayer.getCurrentEquippedItem() != null && thePlayer.getCurrentEquippedItem().getItem() instanceof IKeyBound)
        {
            ((IKeyBound) thePlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(thePlayer, thePlayer.getCurrentEquippedItem(), key);
        }
    }
}
