package jcm2606.mods.sorcerycraft.core.proxy;

import jcm2606.mods.jccore.core.IClientProxy;
import jcm2606.mods.sorcerycraft.block.render.CrystalRender;
import jcm2606.mods.sorcerycraft.block.render.GlowBrickRender;
import jcm2606.mods.sorcerycraft.block.render.GlowpetalRender;
import jcm2606.mods.sorcerycraft.block.render.InfuseTabletRender;
import jcm2606.mods.sorcerycraft.block.render.VordicOreRender;
import jcm2606.mods.sorcerycraft.block.render.astral.AstralBlockRender;
import jcm2606.mods.sorcerycraft.block.render.astral.AstralCraftingNodeRender;
import jcm2606.mods.sorcerycraft.block.render.astral.AstralEnergyNodeRender;
import jcm2606.mods.sorcerycraft.block.tile.TileEntityCrystal;
import jcm2606.mods.sorcerycraft.block.tile.TileEntityInfuseTablet;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralCraftingNode;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralCrystalBlock;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralEnergyGate;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralEnergyNode;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralMechanism;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralObsidian;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralOre;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralTotem1;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralViewer;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.SCParticle;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import jcm2606.mods.sorcerycraft.core.lib.RenderID;
import jcm2606.mods.sorcerycraft.event.SoundHandler;
import jcm2606.mods.sorcerycraft.gui.overlay.GuiOverlayAstral;
import jcm2606.mods.sorcerycraft.gui.overlay.GuiOverlayInvisCloak;
import jcm2606.mods.sorcerycraft.item.render.InfuseTabletRenderItem;
import jcm2606.mods.sorcerycraft.item.render.astral.AstralBlockRenderItem;
import jcm2606.mods.sorcerycraft.item.render.astral.AstralCraftingNodeRenderItem;
import jcm2606.mods.sorcerycraft.item.render.astral.AstralEnergyNodeRenderItem;
import jcm2606.mods.sorcerycraft.item.render.astral.AstralGauntletRenderItem;
import jcm2606.mods.sorcerycraft.keybind.ClientKeyBindingHandler;
import jcm2606.mods.sorcerycraft.keybind.KeyBindingSC;
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
public class ClientProxy extends CommonProxy implements IClientProxy {
	public static int currentRenderPass;
	
	public static SCParticle particleManager = new SCParticle();
	
    @Override
    public void loadRendering() {
        MinecraftForgeClient.registerItemRenderer(SCObjects.infusetablet.blockID, new InfuseTabletRenderItem());
        MinecraftForgeClient.registerItemRenderer(SCObjects.oreastral.blockID, new AstralBlockRenderItem("/textures/blocks/ore_astral_anim.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralviewer.blockID, new AstralBlockRenderItem("/textures/blocks/astral_viewer_anim.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralobsidian.blockID, new AstralBlockRenderItem("/textures/blocks/astral_obsidian_anim.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralcrystalblock.blockID, new AstralBlockRenderItem("/textures/blocks/astral_crystal_block_anim.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralmechanismblock.blockID, new AstralBlockRenderItem("/textures/blocks/astral_mechanism_anim.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.astraltotem1.blockID, new AstralBlockRenderItem("/textures/blocks/astral_totem_1_anim.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralenergygate.blockID, new AstralBlockRenderItem("/textures/blocks/astralEnergyGate.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralenergynode.blockID, new AstralEnergyNodeRenderItem());
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralgauntlet.itemID, new AstralGauntletRenderItem());
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralcraftingnode.blockID, new AstralCraftingNodeRenderItem());
        
        RenderingRegistry.registerBlockHandler(new VordicOreRender());
        RenderingRegistry.registerBlockHandler(new GlowBrickRender());
        RenderingRegistry.registerBlockHandler(new GlowpetalRender());
    }
    
    @Override
    public void loadRenderIds()
    {
        RenderID.renderIDVordicOre = RenderingRegistry.getNextAvailableRenderId();
        RenderID.renderIDGlowBrick = RenderingRegistry.getNextAvailableRenderId();
        RenderID.renderIDGlowpetal = RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public void loadTileEntities() {
        super.loadTileEntities();

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInfuseTablet.class, new InfuseTabletRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystal.class, new CrystalRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAstralOre.class, new AstralBlockRender("/textures/blocks/ore_astral_anim.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAstralViewer.class, new AstralBlockRender("/textures/blocks/astral_viewer_anim.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAstralObsidian.class, new AstralBlockRender("/textures/blocks/astral_obsidian_anim.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAstralCrystalBlock.class, new AstralBlockRender("/textures/blocks/astral_crystal_block_anim.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAstralMechanism.class, new AstralBlockRender("/textures/blocks/astral_mechanism_anim.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAstralTotem1.class, new AstralBlockRender("/textures/blocks/astral_totem_1_anim.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAstralEnergyGate.class, new AstralBlockRender("/textures/blocks/astralEnergyGate.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAstralEnergyNode.class, new AstralEnergyNodeRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAstralCraftingNode.class, new AstralCraftingNodeRender());
    }

    @Override
    public void loadCustomRarities() {
        EnumHelperClient.addRarity(Rarities.BASIC, Rarities.COLOUR_BASIC, Rarities.BASIC);
        EnumHelperClient.addRarity(Rarities.ADVANCED,Rarities.COLOUR_ADVANCED, Rarities.ADVANCED);
        EnumHelperClient.addRarity(Rarities.EXOTIC, Rarities.COLOUR_EXOTIC ,Rarities.EXOTIC);
        EnumHelperClient.addRarity(Rarities.LEGENDARY, Rarities.COLOUR_LEGENDARY, Rarities.LEGENDARY);
        EnumHelperClient.addRarity(Rarities.EXTRACT, Rarities.COLOUR_EXTRACT, Rarities.EXTRACT);
        EnumHelperClient.addRarity(Rarities.CHARM, Rarities.COLOUR_CHARM, Rarities.CHARM);
    }

    @Override
    public void registerHandlers() {
        super.registerHandlers();
        
        MinecraftForge.EVENT_BUS.register(new SoundHandler());
        TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
        KeyBindingRegistry.registerKeyBinding(new ClientKeyBindingHandler());
        MinecraftForge.EVENT_BUS.register(new GuiOverlayAstral(Minecraft.getMinecraft()));
        MinecraftForge.EVENT_BUS.register(new GuiOverlayInvisCloak(Minecraft.getMinecraft()));
    }
    
    @Override
    public void loadKeyBindings() {
        ClientKeyBindingHandler.addKeyBinding(new KeyBindingSC(Reference.KEY_BIND_INHAND_ITEM_DESC, Keyboard.KEY_F), false);
        ClientKeyBindingHandler.addKeyBinding(new KeyBindingSC(Reference.KEY_BIND_HOTBAR_ITEM_DESC, Keyboard.KEY_G), false);
    }
}
