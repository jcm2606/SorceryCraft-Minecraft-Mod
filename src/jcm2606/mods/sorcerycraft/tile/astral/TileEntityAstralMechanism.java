package jcm2606.mods.sorcerycraft.tile.astral;

import jcm2606.mods.jccore.tile.TileEntityJC;
import jcm2606.mods.sorcerycraft.SCObjects;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityAstralMechanism extends TileEntityJC {
    public boolean useable = false;
    
    public void setUseable(boolean b)
    {
        this.useable = b;
    }
    
    public boolean getUseable()
    {
        return this.useable;
    }
    
    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        
        this.useable = par1NBTTagCompound.getBoolean("isUseable");
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        
        par1NBTTagCompound.setBoolean("isUseable", this.useable);
    }
    
    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    @Override
    public void updateEntity() {
        if(!getWorldObj().isRemote)
        {
            if(this.getBlockAtCoords(xCoord, yCoord - 1, zCoord) == SCObjects.astralobsidian)
            {
                setUseable(true);
                System.out.println("yesy");
            } else {
                setUseable(false);
            }
        }
    }
}
