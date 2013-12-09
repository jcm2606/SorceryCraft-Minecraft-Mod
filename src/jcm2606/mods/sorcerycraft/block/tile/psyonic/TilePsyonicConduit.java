package jcm2606.mods.sorcerycraft.block.tile.psyonic;

import jcm2606.mods.jccore.block.tile.TileEntityJC;
import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.jccore.library.FXLibrary;
import jcm2606.mods.sorcerycraft.api.IConduitConnector;
import jcm2606.mods.sorcerycraft.api.IExpandedSightHandler;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyCapacitor;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyConduit;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyProvider;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyReciever;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import codechicken.lib.vec.Vector3;

public class TilePsyonicConduit extends TileEntityJC implements IEnergyCapacitor, IExpandedSightHandler, IEnergyConduit, IConduitConnector
{
    public EnumConduitMode mode = EnumConduitMode.OUTPUT;
    public int energyStored;
    
    public static enum EnumConduitMode {
        INPUT,
        OUTPUT;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        this.energyStored = tag.getInteger("energyStored");
        this.mode = EnumConduitMode.values()[tag.getInteger("mode")];
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        tag.setInteger("energyStored", this.energyStored);
        tag.setInteger("mode", mode.ordinal());
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9)
    {
        if (player.getCurrentEquippedItem() != null)
        {
            return false;
        }
        
        if(this.mode == EnumConduitMode.INPUT)
        {
            this.mode = EnumConduitMode.OUTPUT;
        } else {
            this.mode = EnumConduitMode.INPUT;
        }
        
        if(world.isRemote)
        {
            player.addChatMessage("Mode: " + this.mode.name());
        }
        
        return true;
    }
    
    @Override
    public void updateEntity()
    {
        super.updateEntity();
        
        this.performConduitEnergyTransfers();
        
        if(this.mode == EnumConduitMode.OUTPUT)
        {
            for(ForgeDirection side : ForgeDirection.VALID_DIRECTIONS)
            {
                if(GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, side, worldObj) instanceof IEnergyReciever)
                {
                    IEnergyReciever  reciever = ((IEnergyReciever) GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, side, worldObj));
                    
                    reciever.recieveEnergy(this.takeEnergy(1));
                }
            }
        } else {
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
            {
                if (GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj) != null)
                {
                    if (GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj) instanceof IEnergyProvider)
                    {
                        if (this.hasSpace())
                        {
                            this.addEnergy(((IEnergyProvider) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord, this.zCoord,
                                    direction, this.worldObj)).provide(this.getEnergyLimit() - this.getEnergyStored()), false);
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }
    
    public void performConduitEnergyTransfers()
    {
        ForgeDirection side_ = ForgeDirection.DOWN;
        
        for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
        {
            if(GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, dir, worldObj) instanceof TilePsyonicConduit)
            {
                if(((TilePsyonicConduit) GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, dir, worldObj)).hasValidChargingConduitAdjacent())
                {
                    side_ = dir;
                }
            }
        }
        
        for(ForgeDirection side : ForgeDirection.VALID_DIRECTIONS)
        {
            if(GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, side, worldObj) instanceof TilePsyonicConduit)
            {
                TilePsyonicConduit conduit = (TilePsyonicConduit) GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, side, worldObj);
                TilePsyonicConduit conduit_ = (TilePsyonicConduit) GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, side_, worldObj);
                
                if(conduit_ == null)
                {
                    continue;
                }
                
                if(conduit_.getEnergyStored() < conduit.getEnergyStored())
                {
                    side_ = side;
                }
            }
        }
        
        TilePsyonicConduit conduit = (TilePsyonicConduit) GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, side_, worldObj);
        
        if(conduit == null)
        {
            return;
        }
        
        int amount = 1;
        
        if(this.isBeingCharged() || (!conduit.hasEnergy() || !this.hasSpace()))
        {
            return;
        }
        
        if(conduit.getEnergyStored() <= this.getEnergyStored())
        {
            return;
        }
        
        this.addEnergy(conduit.takeEnergy(amount), false);
        
        if(worldObj.isRemote)
        {
            double modifier = 2.5;
            
            FXLibrary.spawn(FXLibrary.redstone.getName(), 
                    new Vector3(xCoord + 0.25 + (worldObj.rand.nextDouble() / modifier) + side_.offsetX,
                            yCoord + 0.25 + (worldObj.rand.nextDouble() / modifier) + side_.offsetY,
                            zCoord + (worldObj.rand.nextDouble() / modifier) + 0.25 + side_.offsetZ), new Vector3(0, 0.5, 1));
            
            FXLibrary.spawn(FXLibrary.redstone.getName(), 
                    new Vector3(xCoord + 0.25 + (worldObj.rand.nextDouble() / modifier),
                            yCoord + 0.25 + (worldObj.rand.nextDouble() / modifier),
                            zCoord + (worldObj.rand.nextDouble() / modifier) + 0.25), new Vector3(0, 0, 0));
            
            /*if(worldObj.rand.nextInt(100) <= 30)
            {
                FXLightningBolt bolt = new FXLightningBolt(worldObj, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, xCoord + side_.offsetX + 0.5, yCoord + side_.offsetY + 0.5, zCoord + side_.offsetZ + 0.5, worldObj.rand.nextLong(), 3);
                bolt.setMultiplier(10);
                bolt.setColours(0x7D0552, 0x141414);
                bolt.defaultFractal();
                bolt.finalizeBolt();
            }*/
        }
    }
    
    public boolean hasValidChargingConduitAdjacent()
    {
        boolean b = false;
        
        for(ForgeDirection side : ForgeDirection.VALID_DIRECTIONS)
        {
            if(GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, side, worldObj) instanceof TilePsyonicConduit)
            {
                TilePsyonicConduit conduit = (TilePsyonicConduit) GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, side, worldObj);
                
                for(ForgeDirection side2 : ForgeDirection.VALID_DIRECTIONS)
                {
                    if(GeneralUtil.getBlockTileFromNeighbour(conduit.xCoord, conduit.yCoord, conduit.zCoord, side2, worldObj) instanceof TilePsyonicConduit)
                    {
                        TilePsyonicConduit conduit2 = (TilePsyonicConduit) GeneralUtil.getBlockTileFromNeighbour(conduit.xCoord, conduit.yCoord, conduit.zCoord, side2, worldObj);
                        
                        if(conduit2.getEnergyStored() > conduit.getEnergyStored())
                        {
                            b = true;
                        }
                    }
                }
            }
        }
        
        return b;
    }
    
    public int getMaxEnergyOutputLevel()
    {
        return this.getEnergyStored();
    }
    
    public int getEnergyAmountNeededForFull()
    {
        return this.getEnergyLimit() - this.getEnergyStored();
    }
    
    public boolean isBeingCharged()
    {
        boolean b = false;
        
        for(ForgeDirection side : ForgeDirection.VALID_DIRECTIONS)
        {
            if(GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, side, worldObj) instanceof IEnergyProvider)
            {
                if(this.mode.equals(EnumConduitMode.INPUT))
                {
                    b = true;
                    break;
                }
            }
        }
        
        return b;
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        this.readFromNBT(pkt.data);
    }
    
    @Override
    public int addEnergy(int amount, boolean ignoreLimit)
    {
        this.energyStored += amount;
        
        return amount;
    }
    
    public int takeEnergyFromBuffer(int amount)
    {
        if (this.getEnergyStored() > 0 && this.getEnergyStored() - amount >= 0)
        {
            this.energyStored -= amount;
            
            return amount;
        } else
        {
            return 0;
        }
    }
    
    @Override
    public int takeEnergy(int amount)
    {
        if (this.getEnergyStored() > 0 && this.getEnergyStored() - amount >= 0)
        {
            this.energyStored -= amount;
            
            return amount;
        } else
        {
            return 0;
        }
        
        /*int energy = 0;
        
        for(ForgeDirection side : ForgeDirection.VALID_DIRECTIONS)
        {
            if(GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, side, worldObj) instanceof TilePsyonicConduit)
            {
                TilePsyonicConduit conduit = (TilePsyonicConduit) GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, side, worldObj);
                
                if(conduit.hasEnergy())
                {
                    energy = conduit.takeEnergyFromBuffer(amount);
                } else {
                    energy = conduit.takeEnergy(amount);
                }
                
                if(worldObj.isRemote)
                {
                    worldObj.spawnParticle("reddust", xCoord + 0.5 + side.offsetX, yCoord + 0.5 + side.offsetY, zCoord + 0.5 + side.offsetZ, 0, 0, 0);
                }
            }
        }
        
        return energy;*/
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
    public int getEnergyLimit()
    {
        return 5;
    }
    
    @Override
    public int getEnergyStored()
    {
        return this.energyStored;
    }
    
    @Override
    public boolean hasEnergy()
    {
        return this.getEnergyStored() > 0;
    }
    
    @Override
    public boolean hasSpace()
    {
        return this.getEnergyStored() < this.getEnergyLimit();
    }
    
    @Override
    public boolean hasSpace(int amount)
    {
        return this.getEnergyStored() + amount <= this.getEnergyLimit();
    }
    
    @Override
    public void renderOverlay(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        mc.fontRenderer.drawStringWithShadow("\2477" + this.energyStored + "", 0, 0, 0xffffff);
    }
    
    @Override
    public boolean canRender(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        return hasMedallion || player.capabilities.isCreativeMode;
    }
}
