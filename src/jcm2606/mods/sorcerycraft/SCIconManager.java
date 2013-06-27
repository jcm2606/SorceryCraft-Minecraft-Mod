package jcm2606.mods.sorcerycraft;

import java.util.HashMap;

import jcm2606.mods.jccore.compat.ModCompatibility;
import jcm2606.mods.jccore.util.IconIndexer;
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SCIconManager {
    public static HashMap<String, Icon> iconMap = new HashMap<String, Icon>();
    
    @ForgeSubscribe
    public void loadIcons(TextureStitchEvent.Pre event)
    {
        SorceryCraft.index = new IconIndexer("sorcerycraft", event);
        
        ModCompatibility.get().startTextureLoadingInClass(SCObjects.class, SorceryCraft.index, iconMap);
    }
    
    public static void registerIcon(String name, boolean isBlock)
    {
        iconMap.put(name, SorceryCraft.index.getIcon(name, isBlock));
    }
    
    public static void registerIcon(String name, String texture, boolean isBlock)
    {
        iconMap.put(name, SorceryCraft.index.getIcon(texture, isBlock));
    }
    
    public static Icon getIcon(String name)
    {
        return iconMap.get(name);
    }
}
