package jcm2606.mods.sorcerycraft.core.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import jcm2606.mods.sorcerycraft.api.IWand;
import jcm2606.mods.sorcerycraft.core.network.PacketBase;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;

public class PacketWandUse extends PacketBase
{
    public PacketWandUse()
    {
        super(PacketType.WAND_USE, false);
    }
    
    public PacketWandUse(ItemStack stack, EntityPlayer player)
    {
        super(PacketType.WAND_USE, false);
    }
    
    @Override
    public void readData(DataInputStream data) throws IOException
    {
    }
    
    @Override
    public void writeData(DataOutputStream dos) throws IOException
    {
    }
    
    @Override
    public void execute(INetworkManager network, Player player)
    {
        EntityPlayer thePlayer = (EntityPlayer) player;
        
        if (thePlayer.getCurrentEquippedItem() != null && thePlayer.getCurrentEquippedItem().getItem() instanceof IWand)
        {
            ((IWand) thePlayer.getCurrentEquippedItem().getItem()).onWandUse(thePlayer.getCurrentEquippedItem(), thePlayer);
        }
    }
}
