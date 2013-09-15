package jcm2606.mods.sorcerycraft.tick;

import jcm2606.mods.jccore.tick.TickHandlerClientBase;
import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import jcm2606.mods.sorcerycraft.core.config.Settings;
import jcm2606.mods.sorcerycraft.research.ResearchData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ClientTickHandler extends TickHandlerClientBase
{
    @Override
    public void onClientWorldLoad(Minecraft mc, World world)
    {
        SorceryCraft.instance.loadSpecialPlayers();
        
        if (Settings.WORLD_LOAD_MESSAGE)
        {
            mc.thePlayer.addChatMessage("SorceryCraft v" + SorceryCraft.version + " loaded.");
        }
    }
    
    @Override
    public void onClientWorldUnload(Minecraft mc, World world)
    {
    }
    
    @Override
    public void onClientWorldTick(Minecraft mc, World world)
    {
    }
    
    @Override
    public void onHUDTick(Minecraft mc)
    {
        if (mc.currentScreen == null)
        {
            ItemStack currentItem = mc.thePlayer.inventory.getCurrentItem();
            
            ResearchData researchProps = (ResearchData) mc.thePlayer.getExtendedProperties(ResearchData.NAME);
            
            System.out.println(researchProps.getResearchPoints());
        }
    }
    
    @Override
    public void onGUITick(Minecraft mc, GuiScreen guiscreen)
    {
    }
}
