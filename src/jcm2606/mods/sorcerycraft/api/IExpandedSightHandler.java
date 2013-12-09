package jcm2606.mods.sorcerycraft.api;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public interface IExpandedSightHandler
{
    public void renderOverlay(Minecraft mc, EntityPlayer player, boolean hasMedallion);
    
    public boolean canRender(Minecraft mc, EntityPlayer player, boolean hasMedallion);
}
