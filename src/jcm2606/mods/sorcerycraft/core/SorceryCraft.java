package jcm2606.mods.sorcerycraft.core;

import java.util.HashMap;

import jcm2606.mods.jccore.compat.ModCompatibility;
import jcm2606.mods.jccore.compat.container.CompatibilityContainer;
import jcm2606.mods.jccore.util.LoggerBase;
import jcm2606.mods.sorcerycraft.api.SCApi;
import jcm2606.mods.sorcerycraft.api.compat.CompatContainerSC;
import jcm2606.mods.sorcerycraft.command.CommandSC;
import jcm2606.mods.sorcerycraft.compat.TestContainer;
import jcm2606.mods.sorcerycraft.core.config.Config;
import jcm2606.mods.sorcerycraft.core.config.Settings;
import jcm2606.mods.sorcerycraft.core.handler.AlchemyHandler;
import jcm2606.mods.sorcerycraft.core.handler.GuiHandler;
import jcm2606.mods.sorcerycraft.core.handler.ToolHandler;
import jcm2606.mods.sorcerycraft.core.helper.ForgeHookHandler;
import jcm2606.mods.sorcerycraft.core.lib.CapeTypes;
import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.proxy.CommonProxy;
import jcm2606.mods.sorcerycraft.util.SpecialPlayer;
import jcm2606.mods.sorcerycraft.world.gen.GenCore;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = "SorceryCraft", name = "SorceryCraft", version = SorceryCraft.version)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { PacketHandler.CHANNEL_SC }, packetHandler = PacketHandler.class, versionBounds = SorceryCraft.version)
public class SorceryCraft {
    public static final boolean debug = true;

    public static final String version = "0.1.0 Closed Beta";

    @Instance("SorceryCraft")
    public static SorceryCraft instance;

    @SidedProxy(clientSide = "jcm2606.mods.sorcerycraft.core.proxy.ClientProxy", serverSide = "jcm2606.mods.sorcerycraft.core.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static CreativeTabs tab;

    public static LoggerBase logger = new LoggerBase("Sorcerycraft");

    private MinecraftServer server;

    public static HashMap<String, SpecialPlayer> specialPlayers = new HashMap<String, SpecialPlayer>();

    @EventHandler
    public void preLoad(FMLPreInitializationEvent event)
    {
        new SCApi();
        
        event.getModMetadata().version = SorceryCraft.version;

        logger.info("SorceryCraft v" + version + " by jcm2606 installed.");
        logger.info("Mod loading has commenced, be patient!");

        CompatibilityContainer.registerContainer(new CompatContainerSC());

        GameRegistry.registerPlayerTracker(new SCPlayerTracker());

        logger.info("Loading configuration.");
        Config.init(event.getSuggestedConfigurationFile());

        if (Settings.DOES_CREATIVE_TAB_GET_NEXT_FREE_ID) {
            tab = new SCCreativeTab(CreativeTabs.getNextID(), SorceryCraft.class.getAnnotation(Mod.class).modid());
        } else {
            tab = new SCCreativeTab(Settings.CREATIVE_TAB_ID, SorceryCraft.class.getAnnotation(Mod.class).modid());
        }

        proxy.loadKeyBindings();
        proxy.loadRenderIds();
        proxy.registerHandlers();
        CompatibilityContainer.registerSubContainer(TestContainer.class);
    }

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());

        proxy.loadCustomRarities();
        proxy.loadTileEntities();

        ModCompatibility.get().startObjectLoadingInClass(SCObjects.class);

        AlchemyHandler.loadTransmutableBlocksEntries();
        ToolHandler.loadWorkableBlockEntries();

        loadChestGenHooks();

        GameRegistry.registerWorldGenerator(new GenCore());

        MinecraftForge.addGrassPlant(SCObjects.flowerglowpetal, 0, 5);

        SCAchievements.loadAchievements();
    }

    @EventHandler
    public void postLoad(FMLPostInitializationEvent event)
    {
        proxy.loadRendering();

        logger.info("Mod loading has completed.");
    }

    @SideOnly(Side.CLIENT)
    public void loadSpecialPlayers()
    {
        if (SorceryCraft.debug) {
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
        ForgeHookHandler.addCustomChestGenContent(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(SCObjects.alchmetalingot), 1, 7, 030);
        ForgeHookHandler.addCustomChestGenContent(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(SCObjects.alchmetalblock), 1, 2, 010);
        ForgeHookHandler.addCustomChestGenContent(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(SCObjects.vordictool), 1, 1, 001);

        ForgeHookHandler.addCustomChestGenContent(ChestGenHooks.MINESHAFT_CORRIDOR, new ItemStack(SCObjects.dustvordic), 1, 5, 040);
        ForgeHookHandler.addCustomChestGenContent(ChestGenHooks.MINESHAFT_CORRIDOR, new ItemStack(SCObjects.vordicgemblock), 1, 3, 010);

        ForgeHookHandler.addCustomChestGenContent(ChestGenHooks.STRONGHOLD_CORRIDOR, new ItemStack(SCObjects.alchmatter), 7, 27, 030);

        ForgeHookHandler.addCustomChestGenContent(ChestGenHooks.DUNGEON_CHEST, new ItemStack(SCObjects.alchbook), 1, 1, 001);
        ForgeHookHandler.addCustomChestGenContent(ChestGenHooks.DUNGEON_CHEST, new ItemStack(SCObjects.dustvordic), 1, 3, 060);
        ForgeHookHandler.addCustomChestGenContent(ChestGenHooks.DUNGEON_CHEST, new ItemStack(SCObjects.dustvordicrefined), 1, 2, 030);
        ForgeHookHandler.addCustomChestGenContent(ChestGenHooks.DUNGEON_CHEST, new ItemStack(SCObjects.alchstone), 1, 1, 005);
        ForgeHookHandler.addCustomChestGenContent(ChestGenHooks.DUNGEON_CHEST, new ItemStack(SCObjects.alchmatter), 1, 7, 010);
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandSC());
    }
}
