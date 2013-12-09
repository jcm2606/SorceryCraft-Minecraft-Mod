package jcm2606.mods.sorcerycraft.client.gui.overlay;

import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.api.IExpandedSightHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class TileExpandedSightHandler implements IExpandedSightHandler
{
    @Override
    public void renderOverlay(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        MovingObjectPosition mop = GeneralUtil.getTargetBlock(player.worldObj, player, false, 5.0f);
        
        if (mop == null)
        {
            return;
        }
        
        int x = mop.blockX;
        int y = mop.blockY;
        int z = mop.blockZ;
        
        if (mc.theWorld.getBlockTileEntity(x, y, z) instanceof IExpandedSightHandler)
        {
            ((IExpandedSightHandler) mc.theWorld.getBlockTileEntity(x, y, z)).renderOverlay(mc, player, hasMedallion);
        }
    }
    
    @Override
    public boolean canRender(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        MovingObjectPosition mop = GeneralUtil.getTargetBlock(player.worldObj, player, false, 5.0f);
        
        if (mop == null)
        {
            return false;
        }
        
        int x = mop.blockX;
        int y = mop.blockY;
        int z = mop.blockZ;
        
        if (mc.theWorld.getBlockTileEntity(x, y, z) instanceof IExpandedSightHandler)
        {
            return ((IExpandedSightHandler) mc.theWorld.getBlockTileEntity(x, y, z)).canRender(mc, player, hasMedallion);
        }
        
        return false;
    }
}
