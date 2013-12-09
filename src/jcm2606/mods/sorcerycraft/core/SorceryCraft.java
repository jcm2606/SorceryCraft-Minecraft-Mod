package jcm2606.mods.sorcerycraft.core;

import java.util.Arrays;
import java.util.HashMap;

import jcm2606.mods.jccore.compat.ModCompatibility;
import jcm2606.mods.jccore.compat.container.CompatibilityContainer;
import jcm2606.mods.jccore.core.util.LoggerBase;
import jcm2606.mods.sorcerycraft.api.IExpandedSightHandler;
import jcm2606.mods.sorcerycraft.api.SCApi;
import jcm2606.mods.sorcerycraft.api.book.BookTab;
import jcm2606.mods.sorcerycraft.api.compat.CompatContainerSC;
import jcm2606.mods.sorcerycraft.astral.ability.AstralAbilityBase;
import jcm2606.mods.sorcerycraft.client.gui.overlay.GuiOverlayExpandedSight;
import jcm2606.mods.sorcerycraft.core.command.CommandSC;
import jcm2606.mods.sorcerycraft.core.config.Config;
import jcm2606.mods.sorcerycraft.core.handler.GuiHandler;
import jcm2606.mods.sorcerycraft.core.lib.CapeTypes;
import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.util.SpecialPlayer;
import jcm2606.mods.sorcerycraft.world.gen.GenCore;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = "SorceryCraft", name = "SorceryCraft", version = SorceryCraft.version)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels =
{ PacketHandler.CHANNEL_SC }, packetHandler = PacketHandler.class, versionBounds = SorceryCraft.version)
public class SorceryCraft
{
    public static final boolean debug = true;
    
    public static final String version = "0.1.0 Closed Beta";
    
    @Instance("SorceryCraft")
    public static SorceryCraft instance;
    
    @SidedProxy(clientSide = "jcm2606.mods.sorcerycraft.client.SCClientProxy", serverSide = "jcm2606.mods.sorcerycraft.core.SCCommonProxy")
    public static SCCommonProxy proxy;
    
    public static CreativeTabs tabBlocks;
    public static CreativeTabs tabBlocksDeco;
    public static CreativeTabs tabItems;
    
    public static LoggerBase logger = new LoggerBase("Sorcerycraft");
    
    private MinecraftServer server;
    
    public static HashMap<String, SpecialPlayer> specialPlayers = new HashMap<String, SpecialPlayer>();
    
    @EventHandler
    public void preLoad(FMLPreInitializationEvent event)
    {
        new SCApi();
        
        event.getModMetadata().autogenerated = false;
        event.getModMetadata().authorList = Arrays.asList("Jcm2606", "Asyncronous", "Darkhax");
        event.getModMetadata().description = "Using various types of mysterious magic, take control of the very land you've thrived in.";
        
        logger.info("SorceryCraft v" + version + " by jcm2606 installed.");
        logger.info("Mod loading has commenced, be patient!");
        
        CompatibilityContainer.registerContainer(new CompatContainerSC());
        
        GameRegistry.registerPlayerTracker(new SCPlayerTracker());
        
        AstralAbilityBase.registerAbilities();
        BookTab.loadDefaultTabs();
        
        logger.info("Loading configuration.");
        Config.init(event.getSuggestedConfigurationFile());
        
        tabBlocks = new SCCreativeTab.TabBlocks(CreativeTabs.getNextID(), "SorceryCraft");
        tabBlocksDeco = new SCCreativeTab.TabBlocksDeco(CreativeTabs.getNextID(), "SorceryCraft");
        tabItems = new SCCreativeTab.TabItems(CreativeTabs.getNextID(), "SorceryCraft");
        
        proxy.loadKeyBindings();
        proxy.loadRenderIds();
        proxy.registerHandlers();
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());
        
        proxy.loadCustomRarities();
        proxy.loadTileEntities();
        
        ModCompatibility.get().startObjectLoadingInClass(SCObjects.class);
        
        this.loadChestGenHooks();
        
        GameRegistry.registerWorldGenerator(new GenCore());
        
        SCAchievements.loadAchievements();
    }
    
    @EventHandler
    public void postLoad(FMLPostInitializationEvent event)
    {
        proxy.loadRendering();
        
        logger.info("Mod loading has completed.");
    }
    
    @EventHandler
    public void imcCallback(FMLInterModComms.IMCEvent event)
    {
        for (final FMLInterModComms.IMCMessage message : event.getMessages())
        {
            if (message.key.equalsIgnoreCase("expanded-sight-handler-register"))
            {
                if (message.isStringMessage())
                {
                    String classPath = message.getStringValue();
                    
                    Class<?> clazz = null;
                    Object obj = null;
                    
                    try
                    {
                        clazz = Class.forName(classPath);
                    }
                    catch (ClassNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                    
                    try
                    {
                        obj = clazz.newInstance();
                    }
                    catch (InstantiationException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }
                    
                    if (obj == null)
                    {
                        throw new NullPointerException("Attempted register of expanded sight handler failed.");
                    }
                    
                    if (obj instanceof IExpandedSightHandler)
                    {
                        IExpandedSightHandler handler = (IExpandedSightHandler) obj;
                        
                        for (int i = 0; i < 1024; i++)
                        {
                            if (GuiOverlayExpandedSight.handlerList[i] == null)
                            {
                                GuiOverlayExpandedSight.handlerList[i] = handler;
                                break;
                            }
                        }
                    } else
                    {
                        throw new RuntimeException("Recieved invalid handler.");
                    }
                }
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void loadSpecialPlayers()
    {
        if (SorceryCraft.debug)
        {
            SorceryCraft.specialPlayers.put(Minecraft.getMinecraft().thePlayer.username, new SpecialPlayer(
                    Minecraft.getMinecraft().thePlayer.username, CapeTypes.CAPE_TYPE_BETA_TESTER));
        }
        
        SorceryCraft.specialPlayers.put("Llamafaggot", new SpecialPlayer("Llamafaggot", CapeTypes.CAPE_TYPE_DEV));
        SorceryCraft.specialPlayers.put("Asyncronous", new SpecialPlayer("Asyncronous", CapeTypes.CAPE_TYPE_DEV));
        
        SorceryCraft.specialPlayers.put("angrychicken83", new SpecialPlayer("angrychicken83", CapeTypes.CAPE_TYPE_BETA_TESTER));
        SorceryCraft.specialPlayers.put("saukawolf", new SpecialPlayer("saukawolf", CapeTypes.CAPE_TYPE_BETA_TESTER));
    }
    
    public void loadChestGenHooks()
    {
    }
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandSC());
    }
}
