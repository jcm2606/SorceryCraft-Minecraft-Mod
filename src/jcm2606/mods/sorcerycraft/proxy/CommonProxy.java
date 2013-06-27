package jcm2606.mods.sorcerycraft.proxy;

import jcm2606.mods.jccore.core.ICommonProxy;
import jcm2606.mods.sorcerycraft.event.ItemPickupHandler;
import jcm2606.mods.sorcerycraft.event.LivingEntityHandler;
import jcm2606.mods.sorcerycraft.event.PlayerHandler;
import jcm2606.mods.sorcerycraft.handler.CraftingHandlerAchievement;
import jcm2606.mods.sorcerycraft.handler.CraftingHandlerMain;
import jcm2606.mods.sorcerycraft.tile.TileEntityArcaneWorkbench;
import jcm2606.mods.sorcerycraft.tile.TileEntityCrystal;
import jcm2606.mods.sorcerycraft.tile.TileEntityInfuseTablet;
import jcm2606.mods.sorcerycraft.tile.TileEntityTeleporter;
import jcm2606.mods.sorcerycraft.tile.astral.TileEntityAstralCrystalBlock;
import jcm2606.mods.sorcerycraft.tile.astral.TileEntityAstralEnergyGate;
import jcm2606.mods.sorcerycraft.tile.astral.TileEntityAstralMechanism;
import jcm2606.mods.sorcerycraft.tile.astral.TileEntityAstralObsidian;
import jcm2606.mods.sorcerycraft.tile.astral.TileEntityAstralOre;
import jcm2606.mods.sorcerycraft.tile.astral.TileEntityAstralTotem1;
import jcm2606.mods.sorcerycraft.tile.astral.TileEntityAstralViewer;
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
    }
}
