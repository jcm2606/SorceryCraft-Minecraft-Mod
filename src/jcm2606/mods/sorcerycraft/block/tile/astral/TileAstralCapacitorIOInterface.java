package jcm2606.mods.sorcerycraft.block.tile.astral;

import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.api.IExpandedSightHandler;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyCapacitor;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyProvider;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyReadable;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeDirection;

public class TileAstralCapacitorIOInterface extends TileAstralCapacitorStructure implements IEnergyReadable,
IExpandedSightHandler, IEnergyCapacitor, IEnergyProvider
{
    @Override
    public String getMessage()
    {
        if (this.isFormed)
        {
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
            {
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, worldObj) == SCObjects.astralCapacitorCPU.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, worldObj);
                        
                        return tile.getEnergyStored() + " / " + tile.getEnergyLimit();
                    }
                }
            }
        }
        
        return "";
    }
    
    @Override
    public void renderOverlay(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        if (this.isFormed)
        {
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
            {
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, worldObj) == SCObjects.astralCapacitorCPU.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, worldObj);
                        
                        tile.renderOverlay(mc, player, hasMedallion);
                    }
                }
            }
        }
    }
    
    @Override
    public boolean canRender(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        return hasMedallion || player.capabilities.isCreativeMode;
    }
    
    @Override
    public int capacitorRequestEnergy(int amount, boolean ignoreLimit)
    {
        if (this.isFormed)
        {
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
            {
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, worldObj) == SCObjects.astralCapacitorCPU.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, worldObj);
                        
                        return tile.capacitorRequestEnergy(amount, ignoreLimit);
                    }
                }
            }
        }
        
        return 0;
    }
    
    @Override
    public int capacitorProvideEnergy(int amount)
    {
        if (this.isFormed)
        {
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
            {
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, worldObj) == SCObjects.astralCapacitorCPU.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, worldObj);
                        
                        return tile.capacitorProvideEnergy(amount);
                    }
                }
            }
        }
        
        return 0;
    }
    
    @Override
    public int getEnergyLimit()
    {
        if (this.isFormed)
        {
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
            {
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, worldObj) == SCObjects.astralCapacitorCPU.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, worldObj);
                        
                        return tile.getEnergyLimit();
                    }
                }
            }
        }
        
        return 0;
    }
    
    @Override
    public int getEnergyStored()
    {
        if (this.isFormed)
        {
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
            {
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, worldObj) == SCObjects.astralCapacitorCPU.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, worldObj);
                        
                        return tile.getEnergyStored();
                    }
                }
            }
        }
        
        return 0;
    }
    
    @Override
    public boolean hasEnergy()
    {
        if (this.isFormed)
        {
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
            {
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, worldObj) == SCObjects.astralCapacitorCPU.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, worldObj);
                        
                        return tile.hasEnergy();
                    }
                }
            }
        }
        
        return false;
    }
    
    @Override
    public boolean hasSpace()
    {
        if (this.isFormed)
        {
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
            {
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, worldObj) == SCObjects.astralCapacitorCPU.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, worldObj);
                        
                        return tile.hasSpace();
                    }
                }
            }
        }
        
        return false;
    }
    
    @Override
    public boolean hasSpace(int amount)
    {
        if (this.isFormed)
        {
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
            {
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, worldObj) == SCObjects.astralCapacitorCPU.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, worldObj);
                        
                        return tile.hasSpace(amount);
                    }
                }
            }
        }
        
        return false;
    }
    
    @Override
    public int provide()
    {
        return this.capacitorProvideEnergy(1);
    }
    
    @Override
    public int provide(int amount)
    {
        return this.provide();
    }
}
