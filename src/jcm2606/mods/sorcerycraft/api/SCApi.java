package jcm2606.mods.sorcerycraft.api;

import jcm2606.mods.jccore.compat.ModCompatibility;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public final class SCApi {
    private static SCApi instance;
    
    public static Class<?> scClass;
    public static Class<?> scObjects;
    
    public static AstralManager astralManager;
    
    static {
        instance = new SCApi();
    }
    
    public final static SCApi instance()
    {
        return instance;
    }
    
    public SCApi()
    {
        try {
            this.scClass = getClass().getClassLoader().loadClass("jcm2606.mods.sorcerycraft.core.SorceryCraft");
            this.scObjects = getClass().getClassLoader().loadClass("jcm2606.mods.sorcerycraft.core.SCObjects");
            
            this.astralManager = new AstralManager();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Item getItem(String valueName)
    {
        return ModCompatibility.get().getItem(scObjects.getName(), valueName);
    }
    
    public static Block getBlock(String valueName)
    {
        return ModCompatibility.get().getBlock(scObjects.getName(), valueName);
    }
}
