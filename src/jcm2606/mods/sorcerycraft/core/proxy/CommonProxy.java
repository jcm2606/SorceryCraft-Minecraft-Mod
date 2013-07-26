package jcm2606.mods.sorcerycraft.core.proxy;

import jcm2606.mods.jccore.core.ICommonProxy;
import jcm2606.mods.sorcerycraft.astral.gauntlet.ModePyrokenisis;
import jcm2606.mods.sorcerycraft.block.tile.TileEntityArcaneWorkbench;
import jcm2606.mods.sorcerycraft.block.tile.TileEntityCrystal;
import jcm2606.mods.sorcerycraft.block.tile.TileEntityInfuseTablet;
import jcm2606.mods.sorcerycraft.block.tile.TileEntityTeleporter;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralCrystalBlock;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralEnergyGate;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralEnergyNode;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralMechanism;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralObsidian;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralOre;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralStaticCharger;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralTotem1;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralViewer;
import jcm2606.mods.sorcerycraft.core.handler.CraftingHandlerAchievement;
import jcm2606.mods.sorcerycraft.core.handler.CraftingHandlerMain;
import jcm2606.mods.sorcerycraft.event.ItemPickupHandler;
import jcm2606.mods.sorcerycraft.event.LivingEntityHandler;
import jcm2606.mods.sorcerycraft.event.PlayerHandler;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements ICommonProxy {
    @Override
    public void loadRendering() {}

    @Override
    public void loadTileEntities() {
        GameRegistry.registerTileEntity(TileEntityArcaneWorkbench.class, "tileSCArcaneWorkbench");
        GameRegistry.registerTileEntity(TileEntityInfuseTablet.class, "tileSCInfuseTablet");
        GameRegistry.registerTileEntity(TileEntityCrystal.class, "tileSCCrystal");
        GameRegistry.registerTileEntity(TileEntityTeleporter.class, "tileSCTeleporter");
        GameRegistry.registerTileEntity(TileEntityAstralOre.class, "tileSCOreAstral");
        GameRegistry.registerTileEntity(TileEntityAstralViewer.class, "tileSCAstralViewer");
        GameRegistry.registerTileEntity(TileEntityAstralObsidian.class, "tileSCAstralObsidian");
        GameRegistry.registerTileEntity(TileEntityAstralCrystalBlock.class, "tileSCAstralCrystalBlock");
        GameRegistry.registerTileEntity(TileEntityAstralMechanism.class, "tileSCAstralMechanism");
        GameRegistry.registerTileEntity(TileEntityAstralTotem1.class, "tileSCAstralTotem1");
        GameRegistry.registerTileEntity(TileEntityAstralEnergyGate.class, "tileSCAstralEnergyGate");
        GameRegistry.registerTileEntity(TileEntityAstralEnergyNode.class, "tileSCAstralEnergyNode");
        GameRegistry.registerTileEntity(TileEntityAstralStaticCharger.class, "tileSCAstralStaticCharger");
    }

    @Override
    public void loadCustomRarities() {}

    @Override
    public void registerHandlers() {
        MinecraftForge.EVENT_BUS.register(new ItemPickupHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerHandler());
        MinecraftForge.EVENT_BUS.register(new LivingEntityHandler());
        GameRegistry.registerCraftingHandler(new CraftingHandlerMain());
        GameRegistry.registerCraftingHandler(new CraftingHandlerAchievement());
        MinecraftForge.EVENT_BUS.register(new ModePyrokenisis());
    }

    @Override
    public void loadKeyBindings() {}

    @Override
    public void loadRenderIds() {}
}
