package jcm2606.mods.sorcerycraft.core;

import java.util.HashMap;
import java.util.Map;

import jcm2606.mods.jccore.core.IProxyCommon;
import jcm2606.mods.sorcerycraft.block.tile.TileEntityArcaneWorkbench;
import jcm2606.mods.sorcerycraft.block.tile.TileEntityCrystal;
import jcm2606.mods.sorcerycraft.block.tile.TileEntityInfuseTablet;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralInfuser;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralPillarBase;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralPillarCap;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralStructure;
import jcm2606.mods.sorcerycraft.block.tile.psyaic.TileAstralCapacitorCPU;
import jcm2606.mods.sorcerycraft.block.tile.psyaic.TileAstralCapacitorHousing;
import jcm2606.mods.sorcerycraft.block.tile.psyaic.TileAstralCapacitorIOInterface;
import jcm2606.mods.sorcerycraft.block.tile.psyaic.TileAstralEnergyExtractor;
import jcm2606.mods.sorcerycraft.block.tile.psyaic.TileAstralEnergyNode;
import jcm2606.mods.sorcerycraft.block.tile.psyaic.TileCreativeGenerator;
import jcm2606.mods.sorcerycraft.block.tile.psyaic.TilePsyaicZoneCharger;
import jcm2606.mods.sorcerycraft.block.tile.psyonic.TilePsyonicConduit;
import jcm2606.mods.sorcerycraft.core.handler.CraftingHandlerAchievement;
import jcm2606.mods.sorcerycraft.core.handler.CraftingHandlerMain;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;

public class SCCommonProxy implements IProxyCommon
{
    public static final Map<String, NBTTagCompound> researchPointsDeathMap = new HashMap<String, NBTTagCompound>();
    public static final Map<String, NBTTagCompound> abilitiesDeathMap = new HashMap<String, NBTTagCompound>();
    public static final Map<String, NBTTagCompound> skillPointsDeathMap = new HashMap<String, NBTTagCompound>();
    
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
        GameRegistry.registerTileEntity(TileAstralEnergyNode.class, "tileSCAstralEnergyNode");
        GameRegistry.registerTileEntity(TileAstralInfuser.class, "tileSCAstralInfuser");
        GameRegistry.registerTileEntity(TileAstralCapacitorCPU.class, "tileSCAstralBatteryCore");
        GameRegistry.registerTileEntity(TileAstralStructure.class, "tileSCAstralStructure");
        GameRegistry.registerTileEntity(TileAstralCapacitorHousing.class, "tileSCAstralBatteryHousing");
        GameRegistry.registerTileEntity(TileAstralCapacitorIOInterface.class, "tileSCAstralCapacitorIOInterface");
        GameRegistry.registerTileEntity(TileAstralEnergyExtractor.class, "tileSCAstralEnergyExtractor");
        GameRegistry.registerTileEntity(TilePsyaicZoneCharger.class, "tileSCAstralZoneCharger");
        GameRegistry.registerTileEntity(TileCreativeGenerator.class, "tileSCCreativeGenerator");
        GameRegistry.registerTileEntity(TileAstralPillarCap.class, "tileSCAstralPillarCap");
        GameRegistry.registerTileEntity(TileAstralPillarBase.class, "tileSCAstralPillarBase");
        GameRegistry.registerTileEntity(TilePsyonicConduit.class, "tileSCPsyonicConduit");
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
    
    public void saveSkillPoints(String name, NBTTagCompound tag)
    {
        SCCommonProxy.skillPointsDeathMap.put(name, tag);
    }
    
    public NBTTagCompound getSkillPoints(String name)
    {
        return SCCommonProxy.skillPointsDeathMap.remove(name);
    }
}
