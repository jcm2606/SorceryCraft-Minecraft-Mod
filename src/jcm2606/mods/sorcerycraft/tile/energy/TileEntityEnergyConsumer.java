package jcm2606.mods.sorcerycraft.tile.energy;

import jcm2606.mods.jccore.tile.TileEntityJC;
import jcm2606.mods.sorcerycraft.energy.IEnergyConsumer;
import jcm2606.mods.sorcerycraft.energy.IEnergyContainer;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityEnergyConsumer extends TileEntityJC {
    public int maxRange;
    public int energyRequired;
    
    public boolean isActive;
    public boolean canWork;
    
    public TileEntityEnergyConsumer(int par1, int par2)
    {
        this.energyRequired = par1;
        this.maxRange = par2;
    }
    
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
    public void updateEntity() {
        if(this.blockType instanceof IEnergyConsumer)
        {
            IEnergyConsumer consumer = (IEnergyConsumer) blockType;
            
            for(int xa = 0; xa < this.maxRange * 2; xa++)
            {
                for(int ya = 0; ya < this.maxRange * 2; ya++)
                {
                    for(int za = 0; za < this.maxRange * 2; za++)
                    {
                        int x = this.xCoord;
                        int y = this.yCoord;
                        int z = this.zCoord;
                        
                        int scanX = x - (this.maxRange * 2 / 2 / 2) + xa;
                        int scanY = y - (this.maxRange * 2 / 2 / 2) + ya;
                        int scanZ = z - (this.maxRange * 2 / 2 / 2) + za;
                        
                        if(scanX != x && scanY!= y && scanZ != z)
                        {
                            if(this.getBlockAtCoords(scanX, scanY, scanZ) instanceof IEnergyContainer)
                            {
                                IEnergyContainer container = (IEnergyContainer) this.getBlockAtCoords(scanX, scanY, scanZ);
                                TileEntityEnergyContainer teContainer = (TileEntityEnergyContainer) this.worldObj.getBlockTileEntity(scanX, scanY, scanZ);
                                
                                if(this.isActive)
                                {
                                    if(teContainer.energyStored >= this.energyRequired)
                                    {
                                        teContainer.setEnergyStored(teContainer.getEnergyStored() - this.energyRequired);
                                        this.canWork = true;
                                    } else {
                                        this.canWork = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
