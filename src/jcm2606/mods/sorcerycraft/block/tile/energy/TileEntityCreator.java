package jcm2606.mods.sorcerycraft.block.tile.energy;

import jcm2606.mods.jccore.tile.TileEntityJC;
import jcm2606.mods.sorcerycraft.energy.IEnergyCreator;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityCreator extends TileEntityJC implements IEnergyCreator {
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
    }
    
    @Override
    public void updateEntity() {}
}
