package jcm2606.mods.sorcerycraft.block.tile.psyaic;

import jcm2606.mods.sorcerycraft.api.IExpandedSightHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class TileAstralCapacitorHousing extends TileAstralCapacitorStructure implements IExpandedSightHandler
{
    @Override
    public void renderOverlay(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
    }
    
    @Override
    public boolean canRender(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        return hasMedallion || player.capabilities.isCreativeMode;
    }
}
