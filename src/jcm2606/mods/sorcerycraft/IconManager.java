package jcm2606.mods.sorcerycraft;

import java.util.HashMap;

import jcm2606.mods.jccore.compat.ModCompatibility;
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;
import apex.util.ApexIconIndexer;

public class IconManager {
    public static HashMap<String, Icon> iconMap = new HashMap<String, Icon>();
    
    @ForgeSubscribe
    public void loadIcons(TextureStitchEvent.Pre event)
    {
        SorceryCraft.index = new ApexIconIndexer("sorcerycraft", event);
        
        ModCompatibility.startTextureLoadingInClass(SCObjects.class, SorceryCraft.index, iconMap);
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