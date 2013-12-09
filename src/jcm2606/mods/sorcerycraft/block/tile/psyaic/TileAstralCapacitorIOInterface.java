package jcm2606.mods.sorcerycraft.block.tile.psyaic;

import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.api.IConduitConnector;
import jcm2606.mods.sorcerycraft.api.IExpandedSightHandler;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyCapacitor;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyProvider;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyReadable;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyReciever;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeDirection;
import codechicken.lib.vec.Vector3;

public class TileAstralCapacitorIOInterface extends TileAstralCapacitorStructure implements IEnergyReadable, IExpandedSightHandler, IEnergyCapacitor, IEnergyReciever, IEnergyProvider, IConduitConnector
{
    @Override
    public String getMessage()
    {
        if (this.isFormed)
        {
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
            {
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj) == SCObjects.psyonicCapacitorCore.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, this.worldObj);
                        
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
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj) == SCObjects.psyonicCapacitorCore.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, this.worldObj);
                        
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
    public int addEnergy(int amount, boolean ignoreLimit)
    {
        if (this.isFormed)
        {
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
            {
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj) == SCObjects.psyonicCapacitorCore.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, this.worldObj);
                        
                        return tile.addEnergy(amount, ignoreLimit);
                    }
                }
            }
        }
        
        return 0;
    }
    
    @Override
    public int takeEnergy(int amount)
    {
        if (this.isFormed)
        {
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
            {
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj) == SCObjects.psyonicCapacitorCore.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, this.worldObj);
                        
                        return tile.takeEnergy(amount);
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
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj) == SCObjects.psyonicCapacitorCore.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, this.worldObj);
                        
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
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj) == SCObjects.psyonicCapacitorCore.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, this.worldObj);
                        
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
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj) == SCObjects.psyonicCapacitorCore.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, this.worldObj);
                        
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
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj) == SCObjects.psyonicCapacitorCore.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, this.worldObj);
                        
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
                if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj))
                {
                    if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj) == SCObjects.psyonicCapacitorCore.blockID)
                    {
                        TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                                this.zCoord, direction, this.worldObj);
                        
                        return tile.hasSpace(amount);
                    }
                }
            }
        }
        
        return false;
    }
    
    @Override
    public int addEnergy(int amount, boolean ignoreLimit, Vector3 vec3)
    {
        return this.addEnergy(amount, ignoreLimit);
    }
    
    @Override
    public int takeEnergy(int amount, Vector3 vec3)
    {
        return this.takeEnergy(amount);
    }
    
    @Override
    public void recieveEnergy(int energy)
    {
        this.addEnergy(energy, false);
    }
    
    @Override
    public int getEnergyRequirement()
    {
        return 0;
    }
    
    @Override
    public int provide()
    {
        return this.takeEnergy(1);
    }
    
    @Override
    public int provide(int amount)
    {
        return this.takeEnergy(amount);
    }
}
