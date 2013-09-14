package jcm2606.mods.sorcerycraft.entity.properties;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ResearchPoints implements IExtendedEntityProperties
{
    protected EntityPlayer player;
    
    public int currentResearchPoints;
    
    public ResearchPoints(EntityPlayer player)
    {
        this.player = player;
        this.currentResearchPoints = 0;
    }
    
    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound tag = new NBTTagCompound();
        
        tag.setInteger("researchPoints", this.currentResearchPoints);
        
        compound.setTag("SCResearch", tag);
    }
    
    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound tag = (NBTTagCompound) compound.getTag("SCResearch");
        
        this.currentResearchPoints = tag.getInteger("researchPoints");
    }
    
    @Override
    public void init(Entity entity, World world)
    {
    }
}
