package jcm2606.mods.sorcerycraft;

import jcm2606.mods.jccore.LoggerBase;
import jcm2606.mods.jccore.compat.ModCompatibility;
import jcm2606.mods.sorcerycraft.config.Config;
import jcm2606.mods.sorcerycraft.config.Settings;
import jcm2606.mods.sorcerycraft.creative.CreativeTabSC;
import jcm2606.mods.sorcerycraft.damagesource.DamageSourceEntityDetector;
import jcm2606.mods.sorcerycraft.handler.AlchemyHandler;
import jcm2606.mods.sorcerycraft.handler.GuiHandler;
import jcm2606.mods.sorcerycraft.handler.ToolHandler;
import jcm2606.mods.sorcerycraft.helper.ForgeHookHelper;
import jcm2606.mods.sorcerycraft.lib.Packets;
import jcm2606.mods.sorcerycraft.network.PacketHandler;
import jcm2606.mods.sorcerycraft.proxy.CommonProxy;
import jcm2606.mods.sorcerycraft.tick.ClientTickHandler;
import jcm2606.mods.sorcerycraft.world.gen.GenCore;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ChestGenHooks;
import apex.util.ApexIconIndexer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(
        modid = "SorceryCraft",
        name = "SorceryCraft",
        version = SorceryCraft.version
        )
@NetworkMod(
        clientSideRequired = true,
        serverSideRequired = false,
        channels = { Packets.CHANNEL_CORE, Packets.CHANNEL_MISC },
        packetHandler = PacketHandler.class,
        versionBounds = SorceryCraft.version
        )
public class SorceryCraft {
	public static final boolean debug = true;
	
	public static final String version = "0.1.0 Closed Beta";

	@Metadata("SorceryCraft")
	public static ModMetadata meta;
	
	@Instance("SorceryCraft")
	public static SorceryCraft instance;

	@SidedProxy(clientSide = "jcm2606.mods.sorcerycraft.proxy.ClientProxy", serverSide = "jcm2606.mods.sorcerycraft.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static CreativeTabs tab;
	
	public static SCParticle particleManager = new SCParticle();
	
	public static LoggerBase logger = new LoggerBase("Sorcerycraft");
	public static ApexIconIndexer index;
	
	public static DamageSource entityDetectorDamageSource = new DamageSourceEntityDetector("entityDetector");
	
	private MinecraftServer server;

	@PreInit
	public void preLoad(FMLPreInitializationEvent event) {
	    event.getModMetadata().version = this.version;
	    
		logger.info("SorceryCraft v" + version + " by jcm2606 installed.");
		logger.info("Mod loading has commenced, be patient!");

		GameRegistry.registerPlayerTracker(new SCPlayerTracker());
		
		logger.info("Loading configuration.");
		Config.init(event.getSuggestedConfigurationFile());

		if (Settings.DOES_CREATIVE_TAB_GET_NEXT_FREE_ID) {
			tab = new CreativeTabSC(CreativeTabs.getNextID(), SorceryCraft.class.getAnnotation(Mod.class).modid());
		} else {
			tab = new CreativeTabSC(Settings.CREATIVE_TAB_ID, SorceryCraft.class.getAnnotation(Mod.class).modid());
		}
		
		proxy.registerHandlers();
		
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
	}

	@Init
	public void load(FMLInitializationEvent event) {
		NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());

		proxy.loadCustomRarities();
		proxy.loadTileEntities();
		
		ModCompatibility.startObjectLoadingInClass(SCObjects.class);
		
		applyLocalisations();
		
/*		
		KeyBindingRegistry.registerKeyBinding(new ClientKeyBindingHandler());
*/		
		AlchemyHandler.loadTransmutableBlocksEntries();
		ToolHandler.loadWorkableBlockEntries();

		loadChestGenHooks();
		
		GameRegistry.registerWorldGenerator(new GenCore());

		SCAchievements.loadAchievements();

		proxy.loadRendering();
	}

	@PostInit
	public void postLoad(FMLPostInitializationEvent event) {
		logger.info("Mod loading has completed.");
	}
	
	@SideOnly(Side.CLIENT)
	public void loadCapes()
	{
		if(debug)
		{
			ClientTickHandler.capes.put(Minecraft.getMinecraft().thePlayer.username, "http://farm9.staticflickr.com/8370/8501890532_99c5353cb0_t.jpg");
		}
		
		ClientTickHandler.capes.put("Llamafaggot", "http://farm9.staticflickr.com/8370/8501890532_99c5353cb0_t.jpg");
		ClientTickHandler.capes.put("Asyncronous", "http://farm9.staticflickr.com/8370/8501890532_99c5353cb0_t.jpg");
		ClientTickHandler.capes.put("darkhax", "http://farm9.staticflickr.com/8370/8501890532_99c5353cb0_t.jpg");
		
		ClientTickHandler.capes.put("xNomadSix", "http://farm9.staticflickr.com/8229/8500782793_2cfd2d3698_t.jpg");
		ClientTickHandler.capes.put("ajo196", "http://farm9.staticflickr.com/8229/8500782793_2cfd2d3698_t.jpg");
		ClientTickHandler.capes.put("angrychicken83", "http://i.imgur.com/aiGfGnj.png");
		ClientTickHandler.capes.put("saukawolf", "http://farm9.staticflickr.com/8229/8500782793_2cfd2d3698_t.jpg");
		ClientTickHandler.capes.put("LTCNaruto", "http://farm9.staticflickr.com/8229/8500782793_2cfd2d3698_t.jpg");
		ClientTickHandler.capes.put("flopears", "http://farm9.staticflickr.com/8229/8500782793_2cfd2d3698_t.jpg");
	}
	
	public void loadChestGenHooks() {
		ForgeHookHelper.addCustomChestGenContent(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(SCObjects.alchmetalingot), 1, 7, 030);
		ForgeHookHelper.addCustomChestGenContent(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(SCObjects.alchmetalblock), 1, 2, 010);
		ForgeHookHelper.addCustomChestGenContent(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(SCObjects.vordictool), 1, 1, 001);
		
		ForgeHookHelper.addCustomChestGenContent(ChestGenHooks.MINESHAFT_CORRIDOR, new ItemStack(SCObjects.dustvordic), 1, 5, 040);
		ForgeHookHelper.addCustomChestGenContent(ChestGenHooks.MINESHAFT_CORRIDOR, new ItemStack(SCObjects.vordicgemblock), 1, 3, 010);
	
		ForgeHookHelper.addCustomChestGenContent(ChestGenHooks.STRONGHOLD_CORRIDOR, new ItemStack(SCObjects.alchmatter), 7, 27, 030);
	
		ForgeHookHelper.addCustomChestGenContent(ChestGenHooks.DUNGEON_CHEST, new ItemStack(SCObjects.alchbook), 1, 1, 001);
		ForgeHookHelper.addCustomChestGenContent(ChestGenHooks.DUNGEON_CHEST, new ItemStack(SCObjects.dustvordic), 1, 3, 060);
		ForgeHookHelper.addCustomChestGenContent(ChestGenHooks.DUNGEON_CHEST, new ItemStack(SCObjects.dustvordicrefined), 1, 2, 030);
		ForgeHookHelper.addCustomChestGenContent(ChestGenHooks.DUNGEON_CHEST, new ItemStack(SCObjects.alchstone), 1, 1, 005);
		ForgeHookHelper.addCustomChestGenContent(ChestGenHooks.DUNGEON_CHEST, new ItemStack(SCObjects.alchmatter), 1, 7, 010);
	}
	
	@ServerStarting
	public void serverStarting(FMLServerStartingEvent event){
/*		event.registerServerCommand(SCCommands.commandVersion);
		event.registerServerCommand(SCCommands.commandCharmCurseApply);
		event.registerServerCommand(SCCommands.commandCharmCurseList);*/
	}
	
	public static void applyLocalisations()
	{}
}