package jcm2606.mods.sorcerycraft.core;

import java.util.HashMap;
import java.util.Map;

import jcm2606.mods.jccore.core.IProxyCommon;
import jcm2606.mods.sorcerycraft.block.tile.TileEntityArcaneWorkbench;
import jcm2606.mods.sorcerycraft.block.tile.TileEntityCrystal;
import jcm2606.mods.sorcerycraft.block.tile.TileEntityInfuseTablet;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralCapacitorCPU;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralCapacitorHousing;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralCapacitorIOInterface;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralCraftingNode;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralCrystalBlock;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralEnergyExtractor;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralEnergyGate;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralEnergyNode;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralInfuser;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralKineticGenerator;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralMechanismBlock;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralObsidian;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralOre;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralStructure;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralThermalkineticConvertor;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralTotem1;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralViewer;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralZoneCharger;
import jcm2606.mods.sorcerycraft.core.handler.CraftingHandlerAchievement;
import jcm2606.mods.sorcerycraft.core.handler.CraftingHandlerMain;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;

public class SCCommonProxy implements IProxyCommon
{
    public static final Map<String, NBTTagCompound> researchPointsDeathMap = new HashMap<String, NBTTagCompound>();
    public static final Map<String, NBTTagCompound> abilitiesDeathMap = new HashMap<String, NBTTagCompound>();
    
    @Override
    public void loadRendering()
    {
    }
    
    @Override
    public void loadTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityArcaneWorkbench.class, "tileSCArcaneWorkbench");
        GameRegistry.registerTileEntity(TileEntityInfuseTablet.class, "tileSCInfuseTablet");
        GameRegistry.registerTileEntity(TileEntityCrystal.class, "tileSCCrystal");
        GameRegistry.registerTileEntity(TileAstralOre.class, "tileSCOreAstral");
        GameRegistry.registerTileEntity(TileAstralViewer.class, "tileSCAstralViewer");
        GameRegistry.registerTileEntity(TileAstralObsidian.class, "tileSCAstralObsidian");
        GameRegistry.registerTileEntity(TileAstralCrystalBlock.class, "tileSCAstralCrystalBlock");
        GameRegistry.registerTileEntity(TileAstralMechanismBlock.class, "tileSCAstralMechanism");
        GameRegistry.registerTileEntity(TileAstralTotem1.class, "tileSCAstralTotem1");
        GameRegistry.registerTileEntity(TileAstralEnergyGate.class, "tileSCAstralEnergyGate");
        GameRegistry.registerTileEntity(TileAstralEnergyNode.class, "tileSCAstralEnergyNode");
        GameRegistry.registerTileEntity(TileAstralCraftingNode.class, "tileSCAstralCraftingNode");
        GameRegistry.registerTileEntity(TileAstralInfuser.class, "tileSCAstralInfuser");
        GameRegistry.registerTileEntity(TileAstralCapacitorCPU.class, "tileSCAstralBatteryCore");
        GameRegistry.registerTileEntity(TileAstralStructure.class, "tileSCAstralStructure");
        GameRegistry.registerTileEntity(TileAstralCapacitorHousing.class, "tileSCAstralBatteryHousing");
        GameRegistry.registerTileEntity(TileAstralCapacitorIOInterface.class, "tileSCAstralCapacitorIOInterface");
        GameRegistry.registerTileEntity(TileAstralEnergyExtractor.class, "tileSCAstralEnergyExtractor");
        GameRegistry.registerTileEntity(TileAstralKineticGenerator.class, "tileSCAstralKineticGenerator");
        GameRegistry.registerTileEntity(TileAstralThermalkineticConvertor.class, "tileSCAstralThermalkineticGenerator");
        GameRegistry.registerTileEntity(TileAstralZoneCharger.class, "tileSCAstralZoneCharger");
    }
    
    @Override
    public void loadCustomRarities()
    {
    }
    
    @Override
    public void registerHandlers()
    {
        GameRegistry.registerCraftingHandler(new CraftingHandlerMain());
        GameRegistry.registerCraftingHandler(new CraftingHandlerAchievement());
        MinecraftForge.EVENT_BUS.register(new SCEventHandler());
    }
    
    @Override
    public void loadKeyBindings()
    {
    }
    
    @Override
    public void loadRenderIds()
    {
    }
    
    @Override
    public void loadMobs()
    {
    }
    
    public void saveResearchPoints(String name, NBTTagCompound tag)
    {
        SCCommonProxy.researchPointsDeathMap.put(name, tag);
    }
    
    public NBTTagCompound getResearchPoints(String name)
    {
        return SCCommonProxy.researchPointsDeathMap.remove(name);
    }
    
    public void saveAbilities(String name, NBTTagCompound tag)
    {
        SCCommonProxy.abilitiesDeathMap.put(name, tag);
    }
    
    public NBTTagCompound getAbilities(String name)
    {
        return SCCommonProxy.abilitiesDeathMap.remove(name);
    }
}
