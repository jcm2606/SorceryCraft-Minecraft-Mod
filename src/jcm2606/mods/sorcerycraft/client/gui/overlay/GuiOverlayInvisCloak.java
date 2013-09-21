package jcm2606.mods.sorcerycraft.client.gui.overlay;

import jcm2606.mods.jccore.core.util.RenderUtil;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.item.special.ItemInvisCloak;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;

public class GuiOverlayInvisCloak extends Gui
{
    private final Minecraft mc;
    
    public GuiOverlayInvisCloak(Minecraft mc)
    {
        super();
        this.mc = mc;
    }
    
    @ForgeSubscribe(priority = EventPriority.NORMAL)
    public void drawOverlay(RenderGameOverlayEvent event)
    {
        if (event.isCancelable() || event.type != ElementType.HOTBAR || mc.gameSettings.showDebugInfo)
        {
            return;
        }
        
        for (int i = 0; i < mc.thePlayer.inventory.getHotbarSize(); i++)
        {
            ItemStack stack = mc.thePlayer.inventory.getStackInSlot(i);
            
            if (mc.thePlayer.inventory.hasItem(SCObjects.cloakInvis.itemID))
            {
                if (ItemInvisCloak.getState(stack))
                {
                    renderCloakInvisOverlay(mc, stack);
                }
            }
        }
    }
    
    private void renderCloakInvisOverlay(Minecraft minecraft, ItemStack stack)
    {
        RenderUtil.instance().renderVignette(0.0f, Minecraft.getMinecraft().displayWidth / 2, Minecraft.getMinecraft().displayHeight / 2);
    }
}
