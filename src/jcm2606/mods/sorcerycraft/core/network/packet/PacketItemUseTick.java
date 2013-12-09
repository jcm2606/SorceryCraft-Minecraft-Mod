package jcm2606.mods.sorcerycraft.core.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import jcm2606.mods.sorcerycraft.api.IItemUseTickHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketBase;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;

public class PacketItemUseTick extends PacketBase
{
    int count;
    
    public PacketItemUseTick()
    {
        super(PacketType.ITEM_USE_TICK, false);
    }
    
    public PacketItemUseTick(int count)
    {
        super(PacketType.ITEM_USE_TICK, false);
    }
    
    @Override
    public void readData(DataInputStream data) throws IOException
    {
        this.count = data.readInt();
    }
    
    @Override
    public void writeData(DataOutputStream dos) throws IOException
    {
        dos.writeInt(this.count);
    }
    
    @Override
    public void execute(INetworkManager network, Player player)
    {
        EntityPlayer thePlayer = (EntityPlayer) player;
        
        if (thePlayer.getCurrentEquippedItem() != null && thePlayer.getCurrentEquippedItem().getItem() instanceof IItemUseTickHandler)
        {
            ((IItemUseTickHandler) thePlayer.getCurrentEquippedItem().getItem()).serverItemUseTick(thePlayer.getCurrentEquippedItem(), thePlayer,
                    this.count);
        }
    }
}
