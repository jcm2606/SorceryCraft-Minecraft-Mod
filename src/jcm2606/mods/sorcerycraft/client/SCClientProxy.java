package jcm2606.mods.sorcerycraft.client;

import jcm2606.mods.jccore.core.IProxyClient;
import jcm2606.mods.sorcerycraft.block.tile.TileEntityCrystal;
import jcm2606.mods.sorcerycraft.block.tile.TileEntityInfuseTablet;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralCraftingNode;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralCrystalBlock;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralEnergyGate;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralEnergyNode;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralInfuser;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralMechanismBlock;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralObsidian;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralOre;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralTotem1;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralViewer;
import jcm2606.mods.sorcerycraft.client.gui.overlay.GuiOverlayAstral;
import jcm2606.mods.sorcerycraft.client.gui.overlay.GuiOverlayInvisCloak;
import jcm2606.mods.sorcerycraft.client.keybind.ClientKeyBindingHandler;
import jcm2606.mods.sorcerycraft.client.keybind.KeyBindingSC;
import jcm2606.mods.sorcerycraft.client.render.block.CrystalRender;
import jcm2606.mods.sorcerycraft.client.render.block.GlowBrickRender;
import jcm2606.mods.sorcerycraft.client.render.block.GlowpetalRender;
import jcm2606.mods.sorcerycraft.client.render.block.InfuseTabletRender;
import jcm2606.mods.sorcerycraft.client.render.block.VordicOreRender;
import jcm2606.mods.sorcerycraft.client.render.block.astral.AstralBlockRender;
import jcm2606.mods.sorcerycraft.client.render.block.astral.AstralCapacitorCPURender;
import jcm2606.mods.sorcerycraft.client.render.block.astral.AstralCapacitorStructureRender;
import jcm2606.mods.sorcerycraft.client.render.block.astral.AstralCraftingNodeRender;
import jcm2606.mods.sorcerycraft.client.render.block.astral.AstralEnergyFieldDrainRender;
import jcm2606.mods.sorcerycraft.client.render.block.astral.AstralEnergyNodeRender;
import jcm2606.mods.sorcerycraft.client.render.block.astral.AstralInfuserRender;
import jcm2606.mods.sorcerycraft.client.render.block.astral.AstralStructureRender;
import jcm2606.mods.sorcerycraft.client.render.item.astral.AstralBlockRenderItem;
import jcm2606.mods.sorcerycraft.client.render.item.astral.AstralCraftingNodeRenderItem;
import jcm2606.mods.sorcerycraft.client.render.item.astral.AstralEnergyNodeRenderItem;
import jcm2606.mods.sorcerycraft.client.render.item.astral.AstralGauntletRenderItem;
import jcm2606.mods.sorcerycraft.client.render.item.astral.AstralInfuserRenderItem;
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
        MinecraftForgeClient
                .registerItemRenderer(SCObjects.astralViewer.blockID, new AstralBlockRenderItem("textures/blocks/astral_viewer_anim.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralObsidian.blockID, new AstralBlockRenderItem(
                "textures/blocks/astral_obsidian_anim.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.blockAstralCrystal.blockID, new AstralBlockRenderItem(
                "textures/blocks/astral_crystal_block_anim.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralMechansimBlock.blockID, new AstralBlockRenderItem(
                "textures/blocks/astral_mechanism_anim.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralTotem1.blockID,
                new AstralBlockRenderItem("textures/blocks/astral_totem_1_anim.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralEnergyGate.blockID, new AstralBlockRenderItem(
                "textures/blocks/astralEnergyGate.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralEnergyNode.blockID, new AstralEnergyNodeRenderItem());
        MinecraftForgeClient.registerItemRenderer(SCObjects.gauntletAstral.itemID, new AstralGauntletRenderItem());
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralCraftingNode.blockID, new AstralCraftingNodeRenderItem());
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralInfuser.blockID, new AstralInfuserRenderItem());
        
        RenderingRegistry.registerBlockHandler(new VordicOreRender());
        RenderingRegistry.registerBlockHandler(new GlowBrickRender());
        RenderingRegistry.registerBlockHandler(new GlowpetalRender());
        RenderingRegistry.registerBlockHandler(new AstralStructureRender());
        RenderingRegistry.registerBlockHandler(new AstralCapacitorStructureRender());
        RenderingRegistry.registerBlockHandler(new AstralCapacitorCPURender());
        RenderingRegistry.registerBlockHandler(new AstralEnergyFieldDrainRender());
    }
    
    @Override
    public void loadRenderIds()
    {
        RenderID.renderIDVordicOre = RenderingRegistry.getNextAvailableRenderId();
        RenderID.renderIDGlowBrick = RenderingRegistry.getNextAvailableRenderId();
        RenderID.renderIDGlowpetal = RenderingRegistry.getNextAvailableRenderId();
        RenderID.renderIDAstralStructure = RenderingRegistry.getNextAvailableRenderId();
        RenderID.renderIDAstralCapacitor = RenderingRegistry.getNextAvailableRenderId();
        RenderID.renderIDAstralCapacitorCPU = RenderingRegistry.getNextAvailableRenderId();
        RenderID.renderIDAstralEnergyFieldDrain = RenderingRegistry.getNextAvailableRenderId();
    }
    
    @Override
    public void loadTileEntities()
    {
        super.loadTileEntities();
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInfuseTablet.class, new InfuseTabletRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystal.class, new CrystalRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAstralOre.class, new AstralBlockRender("textures/blocks/ore_astral_anim.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileAstralViewer.class, new AstralBlockRender("textures/blocks/astral_viewer_anim.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileAstralObsidian.class, new AstralBlockRender("textures/blocks/astral_obsidian_anim.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileAstralCrystalBlock.class, new AstralBlockRender(
                "textures/blocks/astral_crystal_block_anim.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileAstralMechanismBlock.class, new AstralBlockRender(
                "textures/blocks/astral_mechanism_anim.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileAstralTotem1.class, new AstralBlockRender("textures/blocks/astral_totem_1_anim.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileAstralEnergyGate.class, new AstralBlockRender("textures/blocks/astralEnergyGate.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileAstralEnergyNode.class, new AstralEnergyNodeRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAstralCraftingNode.class, new AstralCraftingNodeRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAstralInfuser.class, new AstralInfuserRender());
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
        MinecraftForge.EVENT_BUS.register(new GuiOverlayAstral(Minecraft.getMinecraft()));
        MinecraftForge.EVENT_BUS.register(new GuiOverlayInvisCloak(Minecraft.getMinecraft()));
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
