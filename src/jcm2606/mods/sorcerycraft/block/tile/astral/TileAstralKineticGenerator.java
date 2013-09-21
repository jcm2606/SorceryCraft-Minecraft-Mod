package jcm2606.mods.sorcerycraft.block.tile.astral;

import jcm2606.mods.jccore.block.tile.TileEntityMultiblock;
import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.jccore.core.util.RenderUtil;
import jcm2606.mods.sorcerycraft.api.IMedallionPerceptionOverlayHandler;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class TileAstralKineticGenerator extends TileEntityMultiblock implements IEnergyProvider, IMedallionPerceptionOverlayHandler
{
    @Override
    public boolean isFormedCorrectly()
    {
        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
        {
            if(GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, direction, worldObj) != null)
            {
                if(GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, direction, worldObj) instanceof TileAstralKineticConvertorBase)
                {
                    return true;
                }
            }
        }
        
        return false;
    }

    @Override
    public int provide()
    {
        int energy = 0;
        
        if(!this.isValid)
        {
            return 0;
        }
        
        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
        {
            if(GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, direction, worldObj) != null)
            {
                if(GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, direction, worldObj) instanceof TileAstralKineticConvertorBase)
                {
                    energy += ((TileAstralKineticConvertorBase) GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, direction, worldObj)).provide();
                }
            }
        }
        
        return 0;
    }

    @Override
    public int provide(int amount)
    {
        return this.provide();
    }

    @Override
    public void renderMedallionOverlay(Minecraft mc, EntityPlayer player)
    {
        MovingObjectPosition mop = GeneralUtil.getTargetBlock(player.worldObj, player, false, 5.0f);
        
        if (mop == null)
        {
            return;
        }
        
        int x = mop.blockX;
        int y = mop.blockY;
        int z = mop.blockZ;
        
        int generating = 0;
        
        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
        {
            if(GeneralUtil.getBlockTileFromNeighbour(x, y, z, direction, worldObj) != null)
            {
                if(GeneralUtil.getBlockTileFromNeighbour(x, y, z, direction, worldObj) instanceof TileAstralKineticConvertorBase)
                {
                    generating = ((TileAstralKineticConvertorBase) GeneralUtil.getBlockTileFromNeighbour(x, y, z, direction, worldObj)).provide();
                }
            }
        }
        
        if(player.worldObj.getBlockTileEntity(x, y, z) != null)
        {
            GL11.glPushMatrix();
            GL11.glScaled(0.5, 0.5, 0.5);
            Minecraft.getMinecraft().fontRenderer.drawString("\2477+" + this.provide() + " AE/t", RenderUtil.width + 8, RenderUtil.height + 8, 0xffffff);
            GL11.glScaled(1, 1, 1);
            GL11.glPopMatrix();
        }
    }
}
