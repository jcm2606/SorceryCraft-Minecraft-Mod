package jcm2606.mods.sorcerycraft.skill;

import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketSyncSkillData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class SkillData implements IExtendedEntityProperties
{
    public static final String NAME = "SCPlayerSkill";
    
    private static EntityPlayer player;
    
    private static int wandSkillPoints;
    
    public SkillData(EntityPlayer player)
    {
        this.player = player;
    }
    
    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound tag = new NBTTagCompound();
        
        tag.setInteger("wandSkillPoints", this.wandSkillPoints);
        
        compound.setCompoundTag(NAME, tag);
    }
    
    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound tag = compound.getCompoundTag(NAME);
        
        System.out.println(tag.getInteger("wandSkillPoints"));
        
        this.wandSkillPoints = tag.getInteger("wandSkillPoints");
    }
    
    @Override
    public void init(Entity entity, World world)
    {
    }
    
    public static final void sync()
    {
        PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketSyncSkillData(wandSkillPoints), PacketHandler.CHANNEL_SC),
                (Player) player);
    }
    
    public void addPointsToWandSkill(int points)
    {
        this.wandSkillPoints += points;
        this.sync();
    }
    
    /**
     * Checks if the stored wand skill points is greater than or equal to parsed
     * amount.
     * 
     * @param points
     * @return If the stored wand skill points is greater than or equal to
     *         parsed amount
     */
    public boolean compareWandSkill(int points)
    {
        return wandSkillPoints >= points;
    }
    
    public void setWandSkill(int amount)
    {
        this.wandSkillPoints = amount;
        sync();
    }
    
    public int getWandSkill()
    {
        return this.wandSkillPoints;
    }
}
