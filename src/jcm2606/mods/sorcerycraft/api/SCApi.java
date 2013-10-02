package jcm2606.mods.sorcerycraft.api;

import jcm2606.mods.jccore.compat.ModCompatibility;
import jcm2606.mods.sorcerycraft.astral.AstralManager;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.event.FMLInterModComms;

public final class SCApi
{
    private static SCApi instance;
    
    public static Class<?> scClass;
    public static Class<?> scObjects;
    
    public static AstralManager astralManager;
    
    private final static IExpandedSightHandler[] handlerList = new IExpandedSightHandler[1024];
    
    static
    {
        instance = new SCApi();
    }
    
    public final static SCApi instance()
    {
        return instance;
    }
    
    public SCApi()
    {
        try
        {
            SCApi.scClass = getClass().getClassLoader().loadClass("jcm2606.mods.sorcerycraft.core.SorceryCraft");
            SCApi.scObjects = getClass().getClassLoader().loadClass("jcm2606.mods.sorcerycraft.core.SCObjects");
            
            SCApi.astralManager = new AstralManager();
        }
        catch (Exception e)
        {
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
    
    public static IExpandedSightHandler registerExpandedSightHandler(IExpandedSightHandler handler)
    {
        FMLInterModComms.sendMessage("SorceryCraft", "expanded-sight-handler", handler.getClass().getName());
        
        return handler;
    }
}
