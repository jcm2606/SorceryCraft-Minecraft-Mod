package jcm2606.mods.sorcerycraft.client;

import jcm2606.mods.jccore.core.IProxyClient;
import jcm2606.mods.sorcerycraft.api.SCApi;
import jcm2606.mods.sorcerycraft.block.tile.TileEntityCrystal;
import jcm2606.mods.sorcerycraft.block.tile.TileEntityInfuseTablet;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralInfuser;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralPillarCap;
import jcm2606.mods.sorcerycraft.block.tile.psyaic.TileAstralEnergyNode;
import jcm2606.mods.sorcerycraft.block.tile.psyonic.TilePsyonicConduit;
import jcm2606.mods.sorcerycraft.client.gui.overlay.GuiOverlayExpandedSight;
import jcm2606.mods.sorcerycraft.client.gui.overlay.TileExpandedSightHandler;
import jcm2606.mods.sorcerycraft.client.keybind.ClientKeyBindingHandler;
import jcm2606.mods.sorcerycraft.client.keybind.KeyBindingSC;
import jcm2606.mods.sorcerycraft.client.render.block.CrystalRender;
import jcm2606.mods.sorcerycraft.client.render.block.GlowBrickRender;
import jcm2606.mods.sorcerycraft.client.render.block.GlowpetalRender;
import jcm2606.mods.sorcerycraft.client.render.block.InfuseTabletRender;
import jcm2606.mods.sorcerycraft.client.render.block.astral.AstralCapacitorCPURender;
import jcm2606.mods.sorcerycraft.client.render.block.astral.AstralCapacitorStructureRender;
import jcm2606.mods.sorcerycraft.client.render.block.astral.AstralEnergyFieldDrainRender;
import jcm2606.mods.sorcerycraft.client.render.block.astral.AstralEnergyNodeRender;
import jcm2606.mods.sorcerycraft.client.render.block.astral.AstralInfuserRender;
import jcm2606.mods.sorcerycraft.client.render.block.astral.AstralStructureRender;
import jcm2606.mods.sorcerycraft.client.render.block.astral.AstralThermalkineticConvertorRender;
import jcm2606.mods.sorcerycraft.client.render.block.astral.AstralZoneChargerRender;
import jcm2606.mods.sorcerycraft.client.render.block.psyonic.PsyonicConduitRender;
import jcm2606.mods.sorcerycraft.client.render.block.psysaic.RenderPsyaicTotemTop;
import jcm2606.mods.sorcerycraft.client.render.item.astral.AstralBlockRenderItem;
import jcm2606.mods.sorcerycraft.core.SCCommonProxy;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.SCParticle;
import jcm2606.mods.sorcerycraft.core.handler.SoundHandler;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import jcm2606.mods.sorcerycraft.core.lib.RenderID;
import jcm2606.mods.sorcerycraft.tick.ClientTickHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SCClientProxy extends SCCommonProxy implements IProxyClient
{
    public static int currentRenderPass;
    
    public static SCParticle particleManager = new SCParticle();
    
    @Override
    public void loadRendering()
    {
        MinecraftForgeClient.registerItemRenderer(SCObjects.oreAstral.blockID, new AstralBlockRenderItem("textures/blocks/ore_astral_anim.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.psyonicConduit.blockID, new PsyonicConduitRender());
        
        RenderingRegistry.registerBlockHandler(new GlowBrickRender());
        RenderingRegistry.registerBlockHandler(new GlowpetalRender());
        RenderingRegistry.registerBlockHandler(new AstralStructureRender());
        RenderingRegistry.registerBlockHandler(new AstralCapacitorStructureRender());
        RenderingRegistry.registerBlockHandler(new AstralCapacitorCPURender());
        RenderingRegistry.registerBlockHandler(new AstralEnergyFieldDrainRender());
        RenderingRegistry.registerBlockHandler(new AstralThermalkineticConvertorRender());
        RenderingRegistry.registerBlockHandler(new AstralZoneChargerRender());
    }
    
    @Override
    public void loadRenderIds()
    {
        RenderID.renderIDGlowBrick = RenderingRegistry.getNextAvailableRenderId();
        RenderID.renderIDGlowpetal = RenderingRegistry.getNextAvailableRenderId();
        RenderID.renderIDAstralStructure = RenderingRegistry.getNextAvailableRenderId();
        RenderID.renderIDAstralCapacitor = RenderingRegistry.getNextAvailableRenderId();
        RenderID.renderIDAstralCapacitorCPU = RenderingRegistry.getNextAvailableRenderId();
        RenderID.renderIDAstralEnergyFieldDrain = RenderingRegistry.getNextAvailableRenderId();
        RenderID.renderIDAstralThermalkineticConvertor = RenderingRegistry.getNextAvailableRenderId();
        RenderID.renderIDAstralZoneCharger = RenderingRegistry.getNextAvailableRenderId();
    }
    
    @Override
    public void loadTileEntities()
    {
        super.loadTileEntities();
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInfuseTablet.class, new InfuseTabletRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystal.class, new CrystalRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAstralInfuser.class, new AstralInfuserRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAstralPillarCap.class, new RenderPsyaicTotemTop());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAstralEnergyNode.class, new AstralEnergyNodeRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TilePsyonicConduit.class, new PsyonicConduitRender());
    }
    
    @Override
    public void loadCustomRarities()
    {
        EnumHelperClient.addRarity(Rarities.BASIC, Rarities.COLOUR_BASIC, Rarities.BASIC);
        EnumHelperClient.addRarity(Rarities.ADVANCED, Rarities.COLOUR_ADVANCED, Rarities.ADVANCED);
        EnumHelperClient.addRarity(Rarities.EXOTIC, Rarities.COLOUR_EXOTIC, Rarities.EXOTIC);
        EnumHelperClient.addRarity(Rarities.LEGENDARY, Rarities.COLOUR_LEGENDARY, Rarities.LEGENDARY);
        EnumHelperClient.addRarity(Rarities.EXTRACT, Rarities.COLOUR_EXTRACT, Rarities.EXTRACT);
        EnumHelperClient.addRarity(Rarities.CHARM, Rarities.COLOUR_CHARM, Rarities.CHARM);
    }
    
    @Override
    public void registerHandlers()
    {
        super.registerHandlers();
        
        MinecraftForge.EVENT_BUS.register(new SoundHandler());
        TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
        KeyBindingRegistry.registerKeyBinding(new ClientKeyBindingHandler());
        MinecraftForge.EVENT_BUS.register(new GuiOverlayExpandedSight(Minecraft.getMinecraft()));
        SCApi.instance().registerExpandedSightHandler(new TileExpandedSightHandler());
        SCApi.instance().registerExpandedSightHandler(new SCOverlayHandler());
    }
    
    @Override
    public void loadKeyBindings()
    {
        ClientKeyBindingHandler.addKeyBinding(new KeyBindingSC(Reference.KEY_BIND_INHAND_ITEM_DESC, Keyboard.KEY_F), false);
        ClientKeyBindingHandler.addKeyBinding(new KeyBindingSC(Reference.KEY_BIND_HOTBAR_ITEM_DESC, Keyboard.KEY_G), false);
    }
    
    @Override
    public void loadMobs()
    {
    }
}
