package jcm2606.mods.sorcerycraft.block.tile.astral;

import jcm2606.mods.jccore.block.tile.TileEntityJC;
import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyCapacitor;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyProvider;
import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketDrawAstralEnergyBeam;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TileAstralEnergyGate extends TileEntityJC implements IEnergyProvider
{
    @Override
    public void updateEntity()
    {
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
        {
            if (GeneralUtil.doesNeighbourBlockExist(xCoord, yCoord, zCoord, direction, worldObj))
            {
                if (GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, direction, worldObj) != null)
                {
                    if (GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, direction, worldObj) instanceof IEnergyCapacitor)
                    {
                        TileEntity tile = GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, direction, worldObj);
                        IEnergyCapacitor capacitor = (IEnergyCapacitor) tile;
                        
                        capacitor.capacitorRequestEnergy(this.provide(), false);
                        
                        if (this.worldObj.isRemote)
                        {
                            PacketDispatcher.sendPacketToAllPlayers(PacketType.populatePacket(new PacketDrawAstralEnergyBeam(xCoord + 0.5,
                                    yCoord + 0.5, zCoord + 0.5, tile.xCoord + 0.5, tile.yCoord + 0.5, tile.zCoord + 0.5, 2, true),
                                    PacketHandler.CHANNEL_SC));
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public int provide()
    {
        return 1;
    }
    
    @Override
    public int provide(int amount)
    {
        return amount <= provide() ? amount : 0;
    }
}
