package jcm2606.mods.sorcerycraft.proxy;

import jcm2606.mods.jccore.IClientProxy;
import jcm2606.mods.sorcerycraft.IconManager;
import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.entity.EntityQuidge;
import jcm2606.mods.sorcerycraft.event.SoundHandler;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import jcm2606.mods.sorcerycraft.render.block.CrystalRender;
import jcm2606.mods.sorcerycraft.render.block.InfuseTabletRender;
import jcm2606.mods.sorcerycraft.render.block.PodiumRender;
import jcm2606.mods.sorcerycraft.render.block.astral.AstralBlockRender;
import jcm2606.mods.sorcerycraft.render.entity.RenderQuidge;
import jcm2606.mods.sorcerycraft.render.item.InfuseTabletRenderItem;
import jcm2606.mods.sorcerycraft.render.item.PodiumRenderItem;
import jcm2606.mods.sorcerycraft.render.item.astral.AstralBlockRenderItem;
import jcm2606.mods.sorcerycraft.tile.TileEntityAlchPodium;
import jcm2606.mods.sorcerycraft.tile.TileEntityCrystal;
import jcm2606.mods.sorcerycraft.tile.TileEntityInfuseTablet;
import jcm2606.mods.sorcerycraft.tile.TileEntityStonePodium;
import jcm2606.mods.sorcerycraft.tile.astral.TileEntityAstralCrystalBlock;
import jcm2606.mods.sorcerycraft.tile.astral.TileEntityAstralMechanism;
import jcm2606.mods.sorcerycraft.tile.astral.TileEntityAstralObsidian;
import jcm2606.mods.sorcerycraft.tile.astral.TileEntityAstralOre;
import jcm2606.mods.sorcerycraft.tile.astral.TileEntityAstralTotem1;
import jcm2606.mods.sorcerycraft.tile.astral.TileEntityAstralViewer;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * ClientProxy.
 * 
 * The proxy class for the client logic
 * 
 * @author jcm2606
 * 
 */

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy implements IClientProxy {
	public static int renderPass;
	
    @Override
    public void loadRendering() {
        MinecraftForgeClient.registerItemRenderer(SCObjects.ID_ALCHEMICAL_PODIUM, new PodiumRenderItem("alch_podium.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.ID_INFUSE_TABLET, new InfuseTabletRenderItem());
        MinecraftForgeClient.registerItemRenderer(SCObjects.stonepodium.blockID, new PodiumRenderItem("stone_podium.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.oreastral.blockID, new AstralBlockRenderItem("/mods/sorcerycraft/textures/blocks/ore_astral_anim.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralviewer.blockID, new AstralBlockRenderItem("/mods/sorcerycraft/textures/blocks/astral_viewer_anim.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralobsidian.blockID, new AstralBlockRenderItem("/mods/sorcerycraft/textures/blocks/astral_obsidian_anim.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralcrystalblock.blockID, new AstralBlockRenderItem("/mods/sorcerycraft/textures/blocks/astral_crystal_block_anim.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.astralmechanismblock.blockID, new AstralBlockRenderItem("/mods/sorcerycraft/textures/blocks/astral_mechanism_anim.png"));
        MinecraftForgeClient.registerItemRenderer(SCObjects.astraltotem1.blockID, new AstralBlockRenderItem("/mods/sorcerycraft/textures/blocks/astral_totem_1_anim.png"));
        
        RenderingRegistry.registerEntityRenderingHandler(EntityQuidge.class, new RenderQuidge(0.1f));
    }

    @Override
    public void loadTileEntities() {
        super.loadTileEntities();

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInfuseTablet.class, new InfuseTabletRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlchPodium.class, new PodiumRender("alch_podium.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystal.class, new CrystalRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStonePodium.class, new PodiumRender("stone_podium.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAstralOre.class, new AstralBlockRender("/mods/sorcerycraft/textures/blocks/ore_astral_anim.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAstralViewer.class, new AstralBlockRender("/mods/sorcerycraft/textures/blocks/astral_viewer_anim.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAstralObsidian.class, new AstralBlockRender("/mods/sorcerycraft/textures/blocks/astral_obsidian_anim.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAstralCrystalBlock.class, new AstralBlockRender("/mods/sorcerycraft/textures/blocks/astral_crystal_block_anim.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAstralMechanism.class, new AstralBlockRender("/mods/sorcerycraft/textures/blocks/astral_mechanism_anim.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAstralTotem1.class, new AstralBlockRender("/mods/sorcerycraft/textures/blocks/astral_totem_1_anim.png"));
    }

    @Override
    public void loadCustomRarities() {
        EnumHelperClient.addRarity(Rarities.BASIC, Rarities.COLOUR_BASIC, Rarities.BASIC);
        EnumHelperClient.addRarity(Rarities.MAGICAL,Rarities.COLOUR_MAGICAL, Rarities.MAGICAL);
        EnumHelperClient.addRarity(Rarities.EXOTIC, Rarities.COLOUR_EXOTIC ,Rarities.EXOTIC);
        EnumHelperClient.addRarity(Rarities.LEGENDARY, Rarities.COLOUR_LEGENDARY, Rarities.LEGENDARY);
        EnumHelperClient.addRarity(Rarities.EXTRACT, Rarities.COLOUR_EXTRACT, Rarities.EXTRACT);
        EnumHelperClient.addRarity(Rarities.CHARM, Rarities.COLOUR_CHARM, Rarities.CHARM);
    }

    @Override
    public void registerHandlers() {
        super.registerHandlers();
        
        MinecraftForge.EVENT_BUS.register(new SoundHandler());
        MinecraftForge.EVENT_BUS.register(new IconManager());
    }
}