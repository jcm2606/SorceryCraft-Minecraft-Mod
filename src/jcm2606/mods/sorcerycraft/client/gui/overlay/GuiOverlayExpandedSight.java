package jcm2606.mods.sorcerycraft.client.gui.overlay;

import jcm2606.mods.sorcerycraft.api.IExpandedSightHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;

public class GuiOverlayExpandedSight extends Gui
{
    public static IExpandedSightHandler[] handlerList = new IExpandedSightHandler[1024];
    
    public Minecraft minecraft;
    
    public GuiOverlayExpandedSight(Minecraft minecraft)
    {
        this.minecraft = minecraft;
    }
    
    @ForgeSubscribe(priority = EventPriority.NORMAL)
    public void drawOverlay(RenderGameOverlayEvent event)
    {
        if (event.isCancelable() || event.type != ElementType.HOTBAR || this.minecraft.gameSettings.showDebugInfo)
        {
            return;
        }
        
        for (IExpandedSightHandler handler : handlerList)
        {
            if (handler != null)
            {
                if (handler.canRender(this.minecraft, this.minecraft.thePlayer, true))
                {
                    handler.renderOverlay(this.minecraft, this.minecraft.thePlayer, true);
                }
            }
        }
    }
}
