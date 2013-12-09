package jcm2606.mods.sorcerycraft.block.tile.astral;

import jcm2606.mods.jccore.block.tile.TileEntityJC;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import net.minecraft.nbt.NBTTagCompound;

public class TileAstralPillarBase extends TileEntityJC
{
    private int energyStored = 0;
    public boolean valid = false;
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        tag.setInteger("energyStored", this.energyStored);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        this.energyStored = tag.getInteger("energyStored");
    }
    
    @Override
    public void updateEntity()
    {
        this.valid = this.checkValidity();
    }
    
    public boolean checkValidity()
    {
        if(worldObj.getBlockId(xCoord, yCoord + 3, zCoord) == SCObjects.astralPillarCap.blockID &&
                worldObj.getBlockId(xCoord, yCoord + 2, zCoord) == SCObjects.astralPillarStructure.blockID &&
                worldObj.getBlockId(xCoord, yCoord + 1, zCoord) == SCObjects.astralPillarStructure.blockID
                )
        {
            return true;
        } else {
            return false;
        }
    }
    
    public int addEnergy(int amount)
    {
        if(this.isWithinLimit(amount))
        {
            this.energyStored += amount;
        }
        
        return this.energyStored;
    }
    
    public int removeEnergy(int amount)
    {
        if(this.energyStored > 0 && this.energyStored - amount >= 0)
        {
            this.energyStored -= amount;
        }
        
        return this.energyStored;
    }
    
    public int getEnergy()
    {
        return this.energyStored;
    }
    
    public boolean isWithinLimit(int amount)
    {
        return this.energyStored < 1000 || this.energyStored <= 1000;
    }
}
