package jcm2606.mods.sorcerycraft.block.tile.astral;

import jcm2606.mods.jccore.core.util.Coord;
import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.api.ILinkCardCoord;
import jcm2606.mods.sorcerycraft.api.IMedallionPerceptionOverlayHandler;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyReadable;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralLinkingCard;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TileAstralCapacitorIOInterface extends TileAstralCapacitorStructure implements IEnergyReadable, ILinkCardCoord, IMedallionPerceptionOverlayHandler
{
    @Override
    public Coord getCoords(ItemAstralLinkingCard card, ItemStack stack, EntityPlayer player, World world, int x, int y, int z)
    {
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
        {
            if (GeneralUtil.doesNeighbourBlockExist(this.xCoord, this.yCoord, this.zCoord, direction, worldObj))
            {
                if (GeneralUtil.getBlockIdFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, worldObj) == SCObjects.astralCapacitorCPU.blockID)
                {
                    TileAstralCapacitorCPU tile = (TileAstralCapacitorCPU) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord,
                            this.zCoord, direction, worldObj);
                    
                    return new Coord(tile.xCoord, tile.yCoord, tile.zCoord);
                }
            }
        }
        
        return null;
    }
    
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
    public void renderMedallionOverlay(Minecraft mc, EntityPlayer player)
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
                        
                        tile.renderMedallionOverlay(mc, player);
                    }
                }
            }
        }
    }
}
