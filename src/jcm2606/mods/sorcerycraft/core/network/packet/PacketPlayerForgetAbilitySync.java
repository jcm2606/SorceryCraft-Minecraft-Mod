package jcm2606.mods.sorcerycraft.core.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import jcm2606.mods.sorcerycraft.astral.ability.AstralAbilityBase;
import jcm2606.mods.sorcerycraft.core.network.PacketBase;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;


public class PacketPlayerForgetAbilitySync extends PacketBase
{
    public PacketPlayerForgetAbilitySync()
    {
        super(PacketType.SYNC_PLAYER_ABILITY_LEARN, false);
    }
    
    public PacketPlayerForgetAbilitySync(String name)
    {
        super(PacketType.SYNC_PLAYER_ABILITY_LEARN, false);
        
        this.name = name;
    }
    
    private String name;
    
    @Override
    public void readData(DataInputStream data) throws IOException
    {
        this.name = data.readUTF();
    }
    
    @Override
    public void writeData(DataOutputStream dos) throws IOException
    {
        dos.writeUTF(name);
    }
    
    @Override
    public void execute(INetworkManager network, Player player)
    {
        EntityPlayer ePlayer = ((EntityPlayer) player);
        AstralAbilityBase base = ((AstralAbilityBase) ePlayer.getExtendedProperties(AstralAbilityBase.NAME));
        
        base.list.add(base.getAbility(name));
    }
}
