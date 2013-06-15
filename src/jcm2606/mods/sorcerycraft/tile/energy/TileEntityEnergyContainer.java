package jcm2606.mods.sorcerycraft.tile.energy;

import jcm2606.mods.jccore.TileEntityJC;
import jcm2606.mods.sorcerycraft.energy.IEnergyContainer;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityEnergyContainer extends TileEntityJC {
    public Block block;
    public int energyStored = 0;
    public int maxEnergyStorage;
    public int maxRange;
    public int maxEnergyOutput;
    
    public TileEntityEnergyContainer(Block par1, int par2, int par3, int par4)
    {
        this.block = par1;
        this.maxEnergyOutput = par2;
        this.maxEnergyStorage = par3;
        this.maxRange = par4;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        this.energyStored = tag.getInteger("energyStored");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        tag.setInteger("energyStored", energyStored);
    }
    
    public void setEnergyStored(int i)
    {
        this.energyStored = i;
    }
    
    public int getEnergyStored()
    {
        return this.energyStored;
    }
    
    @Override
    public void updateEntity() {
        IEnergyContainer container = (IEnergyContainer) block;
        
        if(this.energyStored < container.maxEnergyStorage())
        {
            this.setEnergyStored(this.getEnergyStored() + 1);
        }
    }
}
