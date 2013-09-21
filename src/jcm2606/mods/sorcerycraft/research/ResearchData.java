package jcm2606.mods.sorcerycraft.research;

import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketSyncResearchData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class ResearchData implements IExtendedEntityProperties
{
    public static final String NAME = "SCResearch";
    
    protected EntityPlayer player;
    
    private int currentResearchPoints;
    
    public ResearchData(EntityPlayer player)
    {
        this.player = player;
        this.currentResearchPoints = 0;
    }
    
    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound tag = new NBTTagCompound();
        
        tag.setInteger("researchPoints", this.currentResearchPoints);
        
        compound.setTag(NAME, tag);
    }
    
    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound tag = (NBTTagCompound) compound.getTag(NAME);
        
        this.currentResearchPoints = tag.getInteger("researchPoints");
    }
    
    @Override
    public void init(Entity entity, World world)
    {
    }
    
    public final void syncExtendedProperties()
    {
        PacketDispatcher.sendPacketToPlayer(
                PacketType.populatePacket(new PacketSyncResearchData(this.getResearchPoints()), PacketHandler.CHANNEL_SC), (Player) player);
    }
    
    public void setResearchPoints(int points)
    {
        this.currentResearchPoints = points;
        this.syncExtendedProperties();
    }
    
    public int getResearchPoints()
    {
        return this.currentResearchPoints;
    }
}
