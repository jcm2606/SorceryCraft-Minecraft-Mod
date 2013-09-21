package jcm2606.mods.sorcerycraft.core;

import jcm2606.mods.jccore.core.IObjectCore;
import jcm2606.mods.jccore.core.util.BiomeUtil;
import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.api.ElementManager;
import jcm2606.mods.sorcerycraft.api.SCApi;
import jcm2606.mods.sorcerycraft.astral.gauntlet.ModeHailkenisis;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralCapacitorCPU;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralCapacitorCPUItem;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralCapacitorHousing;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralCapacitorIOInterface;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralCraftingNode;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralCrystal;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralEnergyExtractor;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralEnergyFieldDrain;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralEnergyGate;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralEnergyNode;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralGraphMatrix;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralInfuser;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralKineticGenerator;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralMechanism;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralObsidian;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralSteel;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralSteelItem;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralStructure;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralThermalkineticConvertor;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralTotem1;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralViewer;
import jcm2606.mods.sorcerycraft.block.fluid.BlockFluidVordic;
import jcm2606.mods.sorcerycraft.block.main.BlockAlchMetal;
import jcm2606.mods.sorcerycraft.block.main.BlockArcaneWorkbench;
import jcm2606.mods.sorcerycraft.block.main.BlockCrystal;
import jcm2606.mods.sorcerycraft.block.main.BlockDarkQuartz;
import jcm2606.mods.sorcerycraft.block.main.BlockDarkQuartzBrick;
import jcm2606.mods.sorcerycraft.block.main.BlockDarkQuartzBrickItem;
import jcm2606.mods.sorcerycraft.block.main.BlockEmberstone;
import jcm2606.mods.sorcerycraft.block.main.BlockFlowerGlow;
import jcm2606.mods.sorcerycraft.block.main.BlockGlowBrick;
import jcm2606.mods.sorcerycraft.block.main.BlockGlowBrickItem;
import jcm2606.mods.sorcerycraft.block.main.BlockObsidianFalse;
import jcm2606.mods.sorcerycraft.block.main.BlockOreAstral;
import jcm2606.mods.sorcerycraft.block.main.BlockOreAuric;
import jcm2606.mods.sorcerycraft.block.main.BlockOreDarkQuartz;
import jcm2606.mods.sorcerycraft.block.main.BlockOreVord;
import jcm2606.mods.sorcerycraft.block.main.BlockOreVordicGem;
import jcm2606.mods.sorcerycraft.block.main.BlockSearTorch;
import jcm2606.mods.sorcerycraft.block.main.BlockShimmerStone;
import jcm2606.mods.sorcerycraft.block.main.BlockStoneResistant;
import jcm2606.mods.sorcerycraft.block.main.BlockVordicTorch;
import jcm2606.mods.sorcerycraft.core.config.Config;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import jcm2606.mods.sorcerycraft.enchant.EnchantmentAstralTransmutation;
import jcm2606.mods.sorcerycraft.enchant.EnchantmentElementalDamage;
import jcm2606.mods.sorcerycraft.enchant.EnchantmentRottingTouch;
import jcm2606.mods.sorcerycraft.enchant.Enchantments;
import jcm2606.mods.sorcerycraft.fluid.FluidVordic;
import jcm2606.mods.sorcerycraft.item.SCItem;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralCrystal;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralCrystalPlate;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralCrystalShard;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralDust;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyCell;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyPearl;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralGauntlet;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralLinkingCard;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralMechanismDrive;
import jcm2606.mods.sorcerycraft.item.astral.ItemIngotAstralSteel;
import jcm2606.mods.sorcerycraft.item.charm.ItemCharm;
import jcm2606.mods.sorcerycraft.item.charm.ItemCharmMortality;
import jcm2606.mods.sorcerycraft.item.main.ItemAlchMatter;
import jcm2606.mods.sorcerycraft.item.main.ItemAuraGem;
import jcm2606.mods.sorcerycraft.item.main.ItemAuricPlate;
import jcm2606.mods.sorcerycraft.item.main.ItemBookAlch;
import jcm2606.mods.sorcerycraft.item.main.ItemCog;
import jcm2606.mods.sorcerycraft.item.main.ItemDarkQuartz;
import jcm2606.mods.sorcerycraft.item.main.ItemDenseMatter;
import jcm2606.mods.sorcerycraft.item.main.ItemDustEnergy;
import jcm2606.mods.sorcerycraft.item.main.ItemDustVordic;
import jcm2606.mods.sorcerycraft.item.main.ItemDustVordicStablized;
import jcm2606.mods.sorcerycraft.item.main.ItemElementalGem;
import jcm2606.mods.sorcerycraft.item.main.ItemElementalStar;
import jcm2606.mods.sorcerycraft.item.main.ItemEndEssence;
import jcm2606.mods.sorcerycraft.item.main.ItemFirePowder;
import jcm2606.mods.sorcerycraft.item.main.ItemIllusionFabric;
import jcm2606.mods.sorcerycraft.item.main.ItemIngotAlchMetal;
import jcm2606.mods.sorcerycraft.item.main.ItemStoneAlch;
import jcm2606.mods.sorcerycraft.item.main.ItemStoneAstral;
import jcm2606.mods.sorcerycraft.item.main.ItemVordicTool;
import jcm2606.mods.sorcerycraft.item.special.ItemElementalIgniter;
import jcm2606.mods.sorcerycraft.item.special.ItemInvisCloak;
import jcm2606.mods.sorcerycraft.item.special.ItemMedallionPerception;
import jcm2606.mods.sorcerycraft.item.special.ItemRingMagma;
import jcm2606.mods.sorcerycraft.item.special.ItemSceptorAscension;
import jcm2606.mods.sorcerycraft.item.special.ItemTabletCreation;
import jcm2606.mods.sorcerycraft.item.tool.ItemAxeAlchMetal;
import jcm2606.mods.sorcerycraft.item.tool.ItemHoeAlchMetal;
import jcm2606.mods.sorcerycraft.item.tool.ItemPickAlchMetal;
import jcm2606.mods.sorcerycraft.item.tool.ItemShovelAlchMetal;
import jcm2606.mods.sorcerycraft.item.tool.ItemSwordAlchMetal;
import jcm2606.mods.sorcerycraft.item.tool.ItemSwordElement;
import jcm2606.mods.sorcerycraft.item.tool.ItemSwordFire;
import jcm2606.mods.sorcerycraft.item.wand.ItemWandAir;
import jcm2606.mods.sorcerycraft.item.wand.ItemWandLightning;
import jcm2606.mods.sorcerycraft.world.biome.BiomeGenValerianForest;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class SCObjects implements IObjectCore
{
    public static Fluid vordicfluid;
    
    public static EnumToolMaterial SWORD_ENDOWMENT;
    public static EnumToolMaterial SWORD_GRIFFIN;
    public static EnumToolMaterial PICKAXE_OMNI;
    public static EnumToolMaterial ALCH_METAL;
    public static EnumToolMaterial SWORD_VORPAL;
    public static EnumToolMaterial SWORD_END;
    public static EnumToolMaterial SWORD_ELEMENT;
    
    // VARIABLES
    public static Item dustVordic;
    public static Item dustVordicStabilised;
    public static Item stoneArcane;
    public static Item stoneSphereWeighted;
    public static Item dustVim;
    public static Item arcaneCompendium;
    public static Item arcaneMatter;
    public static Item ingotArcaneSteel;
    public static Item ringMagma;
    public static Item tabletCrafting;
    public static Item toolVordic;
    public static Item medallionDualPerception;
    public static Item ingotObsidian;
    public static Item cloakInvis;
    public static Item fabricIllusion;
    public static Item essenceEnd;
    public static Item silkWoven;
    public static Item dustSear;
    public static Item charmHealing;
    public static Item astralCrystal;
    public static Item cog;
    public static Item ingotAstralSteel;
    public static Item astralCrystalShard;
    public static Item dustAstral;
    public static Item wandLightning;
    public static Item wandAscension;
    public static Item gauntletAstral;
    public static Item astralCellEnergy;
    public static Item astralPearlEnergy;
    public static Item astralMechanismDrive;
    public static Item quartzDark;
    public static Item plateAstralCrystal;
    public static Item matterDense;
    public static Item gemElemental;
    public static Item gemAura;
    public static Item plateAuric;
    public static Item stoneAstral;
    public static Item elementalIgniter;
    public static Item wandAir;
    public static Item starElemental;
    public static Item astralLinkingCard;
    
    public static Block oreVordic;
    public static Block blockArcaneSteel;
    public static Block oreVordicGem;
    public static Block shimmerstone;
    public static Block shimmerstoneluminous;
    public static Block blockVordicGem;
    public static Block stoneResistant;
    public static Block torchVordic;
    public static Block obsidianFalse;
    public static Block astralViewer;
    public static Block oreAstral;
    public static Block astralObsidian;
    public static Block emberstone;
    public static Block blockAstralCrystal;
    public static Block astralMechansimBlock;
    public static Block astralTotem1;
    public static Block torchSear;
    public static Block blockAstralSteel;
    public static Block workbenchArcane;
    public static Block blockDarkQuartz;
    public static Block brickDarkQuartz;
    public static Block astralEnergyGate;
    public static Block astralEnergyNode;
    public static Block oreDarkQuartz;
    public static Block glowBrick1;
    public static Block glowBrick2;
    public static Block flowerGlowpetal;
    public static Block fluidVordic;
    public static Block astralCraftingNode;
    public static Block oreAuric;
    public static Block astralInfuser;
    public static Block astralGraphMatrix;
    public static Block astralCapacitorCPU;
    public static Block astralStructure;
    public static Block astralCapacitorHousing;
    public static Block astralCapacitorIOInterface;
    public static Block astralEnergyExtractionGridCore;
    public static Block astralEnergyFieldDrain;
    public static Block astralKineticGenerator;
    public static Block astralThermalKineticConvertor;
    
    public static Item swordfire;
    public static Item alchmetalpick;
    public static Item alchmetalshovel;
    public static Item alchmetalaxe;
    public static Item alchmetalsword;
    public static Item alchmetalhoe;
    public static Item swordelement;
    
    public static BiomeGenBase valerianforest;
    
    // IDS
    public static int ID_DUST_VORDIC;
    public static int ID_DUST_VORDIC_REFINED;
    public static int ID_STONE_ALCH;
    public static int ID_STONE_WEIGHT;
    public static int ID_DUST_ENERGY;
    public static int ID_ALCH_BOOK;
    public static int ID_ALCH_MATTER;
    public static int ID_ALCH_METAL_INGOT;
    public static int ID_RING_MAGMA;
    public static int ID_CREATION_TABLET;
    public static int ID_VORDIC_TOOL;
    public static int ID_MEDALLION_PERCEPTION;
    public static int ID_OBSIDIAN_INGOT;
    public static int ID_INVIS_CLOAK;
    public static int ID_FABRIC_ILLUSION;
    public static int ID_END_ESSENCE;
    public static int ID_WOVEN_SILK;
    public static int ID_FIRE_POWDER;
    public static int ID_CHARM_HEALTH;
    public static int ID_ASTRAL_ORE_CRYSTAL;
    public static int ID_COG;
    public static int ID_INGOT_ASTRAL_STEEL;
    public static int ID_ASTRAL_CRYSTAL_SHARD;
    public static int ID_ASTRAL_DUST;
    public static int ID_ELEMENT_SCEPTOR_LIGHTNING;
    public static int ID_SCEPTOR_ASCENSION;
    public static int ID_ASTRAL_GAUNTLET;
    public static int ID_ASTRAL_ENERGY_CELL;
    public static int ID_ASTRAL_ENERGY_PEARL;
    public static int ID_ASTRAL_MECHANISM_DRIVE;
    public static int ID_DARK_QUARTZ;
    public static int ID_ASTRAL_CRYSTAL_PLATE;
    public static int ID_DENSE_MATTER;
    public static int ID_ELEMENTAL_GEM;
    public static int ID_AURA_GEM;
    public static int ID_AURIC_PLATE;
    public static int ID_ASTRAL_STONE;
    public static int ID_ELEMENTAL_IGNITER;
    public static int ID_WAND_AIR;
    public static int ID_ELEMENTAL_STAR;
    public static int ID_ASTRAL_LINKING_CARD;
    
    public static int ID_ORE_VORDIC;
    public static int ID_ALCH_METAL;
    public static int ID_ORE_VORDIC_GEM;
    public static int ID_SHIMMER_STONE;
    public static int ID_SHIMMER_STONE_LUMINOUS;
    public static int ID_VORDIC_GEM_BLOCK;
    public static int ID_STONE_RESISTANT;
    public static int ID_VORDIC_TORCH;
    public static int ID_OBSIDIAN_FALSE;
    public static int ID_ASTRAL_VIEWER;
    public static int ID_ORE_ASTRAL;
    public static int ID_ASTRAL_OBSIDIAN;
    public static int ID_EMBER_STONE;
    public static int ID_ASTRAL_CRYSTAL_BLOCK;
    public static int ID_ASTRAL_MECHANISM_BLOCK;
    public static int ID_ASTRAL_TOTEM_1;
    public static int ID_SEAR_TORCH;
    public static int ID_ASTRAL_STEEL_BLOCK;
    public static int ID_ARCANE_WORKBENCH;
    public static int ID_DARK_QUARTZ_BLOCK;
    public static int ID_DARK_QUARTZ_BRICK;
    public static int ID_ASTRAL_ENERGY_GATE;
    public static int ID_ASTRAL_ENERGY_NODE;
    public static int ID_ORE_DARK_QUARTZ;
    public static int ID_GLOW_BRICK_1;
    public static int ID_GLOW_BRICK_2;
    public static int ID_FLOWER_GLOW_PETAL;
    public static int ID_FLUID_VORDIC;
    public static int ID_ASTRAL_CRAFTING_NODE;
    public static int ID_ORE_AURIC;
    public static int ID_ASTRAL_INFUSER;
    public static int ID_ASTRAL_GRAPH_MATRIX;
    public static int ID_ASTRAL_CAPACITOR_CPU;
    public static int ID_ASTRAL_STRUCTURE;
    public static int ID_ASTRAL_CAPACITOR_HOUSING;
    public static int ID_ASTRAL_CAPACITOR_IO_INTERFACE;
    public static int ID_ASTRAL_ENERGY_EXTRACTOR;
    public static int ID_ASTRAL_ENERGY_FIELD_DRAIN;
    public static int ID_ASTRAL_KINETIC_GENERATOR;
    public static int ID_ASTRAL_THERMALKINETIC_CONVERTOR;
    
    public static int ID_SWORD_FIRE;
    public static int ID_ALCH_METAL_PICK;
    public static int ID_ALCH_METAL_SHOVEL;
    public static int ID_ALCH_METAL_AXE;
    public static int ID_ALCH_METAL_SWORD;
    public static int ID_ALCH_METAL_HOE;
    public static int ID_SWORD_ELEMENT;
    
    public static int ID_BIOME_VALERIAN_FOREST;
    
    public static int ID_ENCHANTMENT;
    
    private static Configuration config = Config.config;
    
    @Override
    public void loadObjects()
    {
        PICKAXE_OMNI = EnumHelper.addToolMaterial("PICKAXE_OMNI", 4, 820, 8.0f, 3, 40);
        SWORD_GRIFFIN = EnumHelper.addToolMaterial("SWORD_GRIFFIN", 2, 420, 2.0f, 4, 33);
        SWORD_ENDOWMENT = EnumHelper.addToolMaterial("SWORD_ENDOWMENT", 3, 500, 2.0f, 10, 36);
        ALCH_METAL = EnumHelper.addToolMaterial("ALCH_METAL", 4, 600, 8.5f, 2, 30);
        SWORD_VORPAL = EnumHelper.addToolMaterial("SWORD_VORPAL", 0, 1020, 1.0f, 7, 50);
        SWORD_END = EnumHelper.addToolMaterial("SWORD_END", 2, 820, 2.0f, 10, 40);
        SWORD_ELEMENT = EnumHelper.addToolMaterial("SWORD_ELEMENT", 1, 512000, 8.0f, 20, 40);
        
        vordicfluid = new FluidVordic();
        
        // Items
        dustVordic = new ItemDustVordic(ID_DUST_VORDIC);
        dustVordicStabilised = new ItemDustVordicStablized(ID_DUST_VORDIC_REFINED);
        stoneArcane = new ItemStoneAlch(ID_STONE_ALCH);
        stoneSphereWeighted = new SCItem(ID_STONE_WEIGHT, "weightedStoneSphere");
        dustVim = new ItemDustEnergy(ID_DUST_ENERGY);
        arcaneCompendium = new ItemBookAlch(ID_ALCH_BOOK);
        arcaneMatter = new ItemAlchMatter(ID_ALCH_MATTER);
        ingotArcaneSteel = new ItemIngotAlchMetal(ID_ALCH_METAL_INGOT);
        ringMagma = new ItemRingMagma(ID_RING_MAGMA);
        tabletCrafting = new ItemTabletCreation(ID_CREATION_TABLET);
        toolVordic = new ItemVordicTool(ID_VORDIC_TOOL);
        medallionDualPerception = new ItemMedallionPerception(ID_MEDALLION_PERCEPTION);
        ingotObsidian = new SCItem(ID_OBSIDIAN_INGOT, "ingotObsidian");
        cloakInvis = new ItemInvisCloak(ID_INVIS_CLOAK);
        fabricIllusion = new ItemIllusionFabric(ID_FABRIC_ILLUSION);
        essenceEnd = new ItemEndEssence(ID_END_ESSENCE);
        silkWoven = new SCItem(ID_WOVEN_SILK, "wovenSilk");
        dustSear = new ItemFirePowder(ID_FIRE_POWDER);
        charmHealing = new ItemCharmMortality(ID_CHARM_HEALTH);
        astralCrystal = new ItemAstralCrystal(ID_ASTRAL_ORE_CRYSTAL);
        cog = new ItemCog(ID_COG);
        ingotAstralSteel = new ItemIngotAstralSteel(ID_INGOT_ASTRAL_STEEL);
        astralCrystalShard = new ItemAstralCrystalShard(ID_ASTRAL_CRYSTAL_SHARD);
        dustAstral = new ItemAstralDust(ID_ASTRAL_DUST);
        wandLightning = new ItemWandLightning(ID_ELEMENT_SCEPTOR_LIGHTNING);
        wandAscension = new ItemSceptorAscension(ID_SCEPTOR_ASCENSION);
        gauntletAstral = new ItemAstralGauntlet(ID_ASTRAL_GAUNTLET);
        astralCellEnergy = new ItemAstralEnergyCell(ID_ASTRAL_ENERGY_CELL);
        astralPearlEnergy = new ItemAstralEnergyPearl(ID_ASTRAL_ENERGY_PEARL);
        astralMechanismDrive = new ItemAstralMechanismDrive(ID_ASTRAL_MECHANISM_DRIVE);
        quartzDark = new ItemDarkQuartz(ID_DARK_QUARTZ);
        plateAstralCrystal = new ItemAstralCrystalPlate(ID_ASTRAL_CRYSTAL_PLATE);
        matterDense = new ItemDenseMatter(ID_DENSE_MATTER);
        gemElemental = new ItemElementalGem(ID_ELEMENTAL_GEM);
        gemAura = new ItemAuraGem(ID_AURA_GEM);
        plateAuric = new ItemAuricPlate(ID_AURIC_PLATE);
        stoneAstral = new ItemStoneAstral(ID_ASTRAL_STONE);
        elementalIgniter = new ItemElementalIgniter(ID_ELEMENTAL_IGNITER);
        wandAir = new ItemWandAir(ID_WAND_AIR);
        starElemental = new ItemElementalStar(ID_ELEMENTAL_STAR);
        astralLinkingCard = new ItemAstralLinkingCard(ID_ASTRAL_LINKING_CARD);
        
        // Blocks
        oreVordic = new BlockOreVord(ID_ORE_VORDIC);
        blockArcaneSteel = new BlockAlchMetal(ID_ALCH_METAL);
        oreVordicGem = new BlockOreVordicGem(ID_ORE_VORDIC_GEM);
        shimmerstone = new BlockShimmerStone(ID_SHIMMER_STONE, "shimmerStone");
        shimmerstoneluminous = new BlockShimmerStone(ID_SHIMMER_STONE_LUMINOUS, "shimmerStoneLuminous").setLightValue(1.0F);
        blockVordicGem = new BlockCrystal(ID_VORDIC_GEM_BLOCK, Material.glass);
        stoneResistant = new BlockStoneResistant(ID_STONE_RESISTANT);
        torchVordic = new BlockVordicTorch(ID_VORDIC_TORCH);
        obsidianFalse = new BlockObsidianFalse(ID_OBSIDIAN_FALSE, Material.rock);
        astralViewer = new BlockAstralViewer(ID_ASTRAL_VIEWER);
        oreAstral = new BlockOreAstral(ID_ORE_ASTRAL);
        astralObsidian = new BlockAstralObsidian(ID_ASTRAL_OBSIDIAN);
        emberstone = new BlockEmberstone(ID_EMBER_STONE);
        blockAstralCrystal = new BlockAstralCrystal(ID_ASTRAL_CRYSTAL_BLOCK);
        astralMechansimBlock = new BlockAstralMechanism(ID_ASTRAL_MECHANISM_BLOCK);
        astralTotem1 = new BlockAstralTotem1(ID_ASTRAL_TOTEM_1);
        torchSear = new BlockSearTorch(ID_SEAR_TORCH);
        blockAstralSteel = new BlockAstralSteel(ID_ASTRAL_STEEL_BLOCK);
        workbenchArcane = new BlockArcaneWorkbench(ID_ARCANE_WORKBENCH);
        blockDarkQuartz = new BlockDarkQuartz(ID_DARK_QUARTZ_BLOCK);
        brickDarkQuartz = new BlockDarkQuartzBrick(ID_DARK_QUARTZ_BRICK);
        astralEnergyGate = new BlockAstralEnergyGate(ID_ASTRAL_ENERGY_GATE);
        astralEnergyNode = new BlockAstralEnergyNode(ID_ASTRAL_ENERGY_NODE);
        oreDarkQuartz = new BlockOreDarkQuartz(ID_ORE_DARK_QUARTZ);
        glowBrick1 = new BlockGlowBrick(ID_GLOW_BRICK_1, "glowBrick1", "glowBrick1");
        glowBrick2 = new BlockGlowBrick(ID_GLOW_BRICK_2, "glowBrick2", "glowBrick2");
        flowerGlowpetal = new BlockFlowerGlow(ID_FLOWER_GLOW_PETAL);
        fluidVordic = new BlockFluidVordic(ID_FLUID_VORDIC);
        astralCraftingNode = new BlockAstralCraftingNode(ID_ASTRAL_CRAFTING_NODE);
        oreAuric = new BlockOreAuric(ID_ORE_AURIC);
        astralInfuser = new BlockAstralInfuser(ID_ASTRAL_INFUSER);
        astralGraphMatrix = new BlockAstralGraphMatrix(ID_ASTRAL_GRAPH_MATRIX);
        astralCapacitorCPU = new BlockAstralCapacitorCPU(ID_ASTRAL_CAPACITOR_CPU);
        astralStructure = new BlockAstralStructure(ID_ASTRAL_STRUCTURE);
        astralCapacitorHousing = new BlockAstralCapacitorHousing(ID_ASTRAL_CAPACITOR_HOUSING);
        astralCapacitorIOInterface = new BlockAstralCapacitorIOInterface(ID_ASTRAL_CAPACITOR_IO_INTERFACE);
        astralEnergyExtractionGridCore = new BlockAstralEnergyExtractor(ID_ASTRAL_ENERGY_EXTRACTOR);
        astralEnergyFieldDrain = new BlockAstralEnergyFieldDrain(ID_ASTRAL_ENERGY_FIELD_DRAIN);
        astralKineticGenerator = new BlockAstralKineticGenerator(ID_ASTRAL_KINETIC_GENERATOR);
        astralThermalKineticConvertor = new BlockAstralThermalkineticConvertor(ID_ASTRAL_THERMALKINETIC_CONVERTOR);
        
        // Tools
        swordfire = new ItemSwordFire(ID_SWORD_FIRE);
        alchmetalpick = new ItemPickAlchMetal(ID_ALCH_METAL_PICK);
        alchmetalshovel = new ItemShovelAlchMetal(ID_ALCH_METAL_SHOVEL);
        alchmetalaxe = new ItemAxeAlchMetal(ID_ALCH_METAL_AXE);
        alchmetalsword = new ItemSwordAlchMetal(ID_ALCH_METAL_SWORD);
        alchmetalhoe = new ItemHoeAlchMetal(ID_ALCH_METAL_HOE);
        swordelement = new ItemSwordElement(ID_SWORD_ELEMENT);
        
        // Biomes
        valerianforest = new BiomeGenValerianForest(BiomeUtil.getFreeBiomeID(Reference.BIOME_ID_START_VALUE));
        
        ItemCharm.registerCurses();
        SCApi.astralManager.loadCoreModes();
        ModeHailkenisis.loadCoolingEntries();
        
        registerBlocks();
        loadEnchantments();
        addBlockHarvestLevels();
        addRecipes();
        addSmeltingRecipes();
        addNames();
        loadBiomes();
    }
    
    @Override
    public void registerBlocks()
    {
        GeneralUtil.registerBlock(oreVordic);
        GeneralUtil.registerBlock(blockArcaneSteel);
        GeneralUtil.registerBlock(oreVordicGem);
        GeneralUtil.registerBlock(shimmerstone);
        GeneralUtil.registerBlock(shimmerstoneluminous);
        GeneralUtil.registerBlock(blockVordicGem);
        GeneralUtil.registerBlock(stoneResistant);
        GeneralUtil.registerBlock(torchVordic);
        GeneralUtil.registerBlock(obsidianFalse);
        GeneralUtil.registerBlock(astralViewer);
        GeneralUtil.registerBlock(oreAstral);
        GeneralUtil.registerBlock(astralObsidian);
        GeneralUtil.registerBlock(emberstone);
        GeneralUtil.registerBlock(blockAstralCrystal);
        GeneralUtil.registerBlock(astralMechansimBlock);
        GeneralUtil.registerBlock(astralTotem1);
        GeneralUtil.registerBlock(torchSear);
        GeneralUtil.registerBlock(blockAstralSteel, BlockAstralSteelItem.class);
        GeneralUtil.registerBlock(workbenchArcane);
        GeneralUtil.registerBlock(blockDarkQuartz);
        GeneralUtil.registerBlock(brickDarkQuartz, BlockDarkQuartzBrickItem.class);
        GeneralUtil.registerBlock(astralEnergyGate);
        GeneralUtil.registerBlock(astralEnergyNode);
        GeneralUtil.registerBlock(oreDarkQuartz);
        GeneralUtil.registerBlock(glowBrick1, BlockGlowBrickItem.class);
        GeneralUtil.registerBlock(glowBrick2, BlockGlowBrickItem.class);
        GeneralUtil.registerBlock(flowerGlowpetal);
        GeneralUtil.registerBlock(fluidVordic);
        GeneralUtil.registerBlock(astralCraftingNode);
        GeneralUtil.registerBlock(oreAuric);
        GeneralUtil.registerBlock(astralInfuser);
        GeneralUtil.registerBlock(astralGraphMatrix);
        GeneralUtil.registerBlock(astralCapacitorCPU, BlockAstralCapacitorCPUItem.class);
        GeneralUtil.registerBlock(astralStructure);
        GeneralUtil.registerBlock(astralCapacitorHousing);
        GeneralUtil.registerBlock(astralCapacitorIOInterface);
        GeneralUtil.registerBlock(astralEnergyExtractionGridCore);
        GeneralUtil.registerBlock(astralEnergyFieldDrain);
        GeneralUtil.registerBlock(astralKineticGenerator);
        GeneralUtil.registerBlock(astralThermalKineticConvertor);
    }
    
    @Override
    public void loadIDs()
    {
        // Items
        ID_DUST_VORDIC = Config.getItemId("dustVordic", Reference.ITEM_ID_START_VALUE).getInt();
        ID_DUST_VORDIC_REFINED = Config.getItemId("dustVordicStabilized", Reference.ITEM_ID_START_VALUE).getInt();
        ID_STONE_ALCH = Config.getItemId("stoneAlch", Reference.ITEM_ID_START_VALUE).getInt();
        ID_STONE_WEIGHT = Config.getItemId("stoneWeighted", Reference.ITEM_ID_START_VALUE).getInt();
        ID_DUST_ENERGY = Config.getItemId("dustVim", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ALCH_BOOK = Config.getItemId("alchBook", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ALCH_MATTER = Config.getItemId("alchMatter", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ALCH_METAL_INGOT = Config.getItemId("alchMetalIngot", Reference.ITEM_ID_START_VALUE).getInt();
        ID_RING_MAGMA = Config.getItemId("ringMagma", Reference.ITEM_ID_START_VALUE).getInt();
        ID_CREATION_TABLET = Config.getItemId("tabletCreation", Reference.ITEM_ID_START_VALUE).getInt();
        ID_VORDIC_TOOL = Config.getItemId("vordicTool", Reference.ITEM_ID_START_VALUE).getInt();
        ID_MEDALLION_PERCEPTION = Config.getItemId("medallionPerception", Reference.ITEM_ID_START_VALUE).getInt();
        ID_OBSIDIAN_INGOT = Config.getItemId("obsidianIngot", Reference.ITEM_ID_START_VALUE).getInt();
        ID_INVIS_CLOAK = Config.getItemId("cloakInvis", Reference.ITEM_ID_START_VALUE).getInt();
        ID_FABRIC_ILLUSION = Config.getItemId("fabricIllusionists", Reference.ITEM_ID_START_VALUE).getInt();
        ID_END_ESSENCE = Config.getItemId("endEssence", Reference.ITEM_ID_START_VALUE).getInt();
        ID_WOVEN_SILK = Config.getItemId("wovenSilk", Reference.ITEM_ID_START_VALUE).getInt();
        ID_FIRE_POWDER = Config.getItemId("firePowder", Reference.ITEM_ID_START_VALUE).getInt();
        ID_CHARM_HEALTH = Config.getItemId("charmImmortality", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_ORE_CRYSTAL = Config.getItemId("astralCrystal", Reference.ITEM_ID_START_VALUE).getInt();
        ID_COG = Config.getItemId("cog", Reference.ITEM_ID_START_VALUE).getInt();
        ID_INGOT_ASTRAL_STEEL = Config.getItemId("astralSteelIngot", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_CRYSTAL_SHARD = Config.getItemId("astralCrystalShard", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_DUST = Config.getItemId("astralDust", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ELEMENT_SCEPTOR_LIGHTNING = Config.getWandId("elementSceptorLightning", Reference.ITEM_ID_START_VALUE).getInt();
        ID_SCEPTOR_ASCENSION = Config.getItemId("sceptorAscension", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_GAUNTLET = Config.getItemId("astralGantlet", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_ENERGY_CELL = Config.getItemId("astralEnergyCell", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_ENERGY_PEARL = Config.getItemId("astralEnergyPearl", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_MECHANISM_DRIVE = Config.getItemId("astralMechanismDrive", Reference.ITEM_ID_START_VALUE).getInt();
        ID_DARK_QUARTZ = Config.getItemId("darkQuartz", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_CRYSTAL_PLATE = Config.getItemId("astralCrystalPlate", Reference.ITEM_ID_START_VALUE).getInt();
        ID_DENSE_MATTER = Config.getItemId("denseMatter", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ELEMENTAL_GEM = Config.getItemId("elementalGem", Reference.ITEM_ID_START_VALUE).getInt();
        ID_AURA_GEM = Config.getItemId("auraGem", Reference.ITEM_ID_START_VALUE).getInt();
        ID_AURIC_PLATE = Config.getItemId("auricPlate", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_STONE = Config.getItemId("astralStone", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ELEMENTAL_IGNITER = Config.getItemId("elementalIgniter", Reference.ITEM_ID_START_VALUE).getInt();
        ID_WAND_AIR = Config.getWandId("wandAir", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ELEMENTAL_STAR = Config.getItemId("elementalStar", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_LINKING_CARD = Config.getItemId("astralLinkingCard", Reference.ITEM_ID_START_VALUE).getInt();
        
        // Blocks
        ID_ORE_VORDIC = Config.getBlockId("oreVordic", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ALCH_METAL = Config.getBlockId("alchMetalBlock", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ORE_VORDIC_GEM = Config.getBlockId("oreVordicGem", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_SHIMMER_STONE = Config.getBlockId("shimmerStone", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_SHIMMER_STONE_LUMINOUS = Config.getBlockId("shimmerStoneLuminous", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_VORDIC_GEM_BLOCK = Config.getBlockId("vordGemBlock", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_STONE_RESISTANT = Config.getBlockId("stoneResistance", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_VORDIC_TORCH = Config.getBlockId("vordciTorch", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_OBSIDIAN_FALSE = Config.getBlockId("obsidianFalse", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_VIEWER = Config.getBlockId("astralViewer", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ORE_ASTRAL = Config.getBlockId("oreAstral", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_OBSIDIAN = Config.getBlockId("astralObsidian", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_EMBER_STONE = Config.getBlockId("emberStone", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_CRYSTAL_BLOCK = Config.getBlockId("astralCrystalBlock", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_MECHANISM_BLOCK = Config.getBlockId("astralMechanism", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_TOTEM_1 = Config.getBlockId("astralTotem1", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_SEAR_TORCH = Config.getBlockId("searTorch", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_STEEL_BLOCK = Config.getBlockId("astralSteelBlock", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ARCANE_WORKBENCH = Config.getBlockId("arcaneWorkbench", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_DARK_QUARTZ_BLOCK = Config.getBlockId("darkQuartz", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_DARK_QUARTZ_BRICK = Config.getBlockId("darkQuartzBrick", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_ENERGY_GATE = Config.getBlockId("astralEnergyGate", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_ENERGY_NODE = Config.getBlockId("astralEnergyNode", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ORE_DARK_QUARTZ = Config.getBlockId("oreDarkQuartz", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_GLOW_BRICK_1 = Config.getBlockId("glowBrickDesign1", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_GLOW_BRICK_2 = Config.getBlockId("glowBrickDesign2", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_FLOWER_GLOW_PETAL = Config.getBlockId("glowPetal", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_FLUID_VORDIC = Config.getBlockId("fluidVordic", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_CRAFTING_NODE = Config.getBlockId("astralCraftingNode", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ORE_AURIC = Config.getBlockId("oreAuric", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_INFUSER = Config.getBlockId("astralInfuser", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_GRAPH_MATRIX = Config.getBlockId("astralGraphMatrix", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_CAPACITOR_CPU = Config.getBlockId("astralCapacitorCPU", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_STRUCTURE = Config.getBlockId("astralStructure", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_CAPACITOR_HOUSING = Config.getBlockId("astralCapacitorHousing", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_CAPACITOR_IO_INTERFACE = Config.getBlockId("astralCapacitorIOInterface", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_ENERGY_EXTRACTOR = Config.getBlockId("astralEnergyExtractionGridCore", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_ENERGY_FIELD_DRAIN = Config.getBlockId("astralEnergyFieldDrain", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_KINETIC_GENERATOR = Config.getBlockId("astralKineticGenerator", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_THERMALKINETIC_CONVERTOR = Config.getBlockId("astralThermalkineticConvertor", Reference.BLOCK_ID_START_VALUE).getInt();
        
        // Tools
        ID_SWORD_FIRE = Config.getToolId("swordFire", Reference.ITEM_TOOL_ID_START_VALUE).getInt();
        ID_ALCH_METAL_PICK = Config.getToolId("alchMetalPickaxe", Reference.ITEM_TOOL_ID_START_VALUE).getInt();
        ID_ALCH_METAL_SHOVEL = Config.getToolId("alchMetalShovel", Reference.ITEM_TOOL_ID_START_VALUE).getInt();
        ID_ALCH_METAL_AXE = Config.getToolId("alchMetalAxe", Reference.ITEM_TOOL_ID_START_VALUE).getInt();
        ID_ALCH_METAL_SWORD = Config.getToolId("alchMetalSword", Reference.ITEM_TOOL_ID_START_VALUE).getInt();
        ID_ALCH_METAL_HOE = Config.getToolId("alchMetalHoe", Reference.ITEM_TOOL_ID_START_VALUE).getInt();
        ID_SWORD_ELEMENT = Config.getToolId("swordElement", Reference.ITEM_TOOL_ID_START_VALUE).getInt();
        
        // Enchantments
        ID_ENCHANTMENT = Config.getEnchantmentId("enchantment", 52).getInt();
    }
    
    @Override
    public void loadEnchantments()
    {
        Enchantments i = new Enchantments();
        
        i.rottingTouch = new EnchantmentRottingTouch(ID_ENCHANTMENT);
        i.elementalDamage = new EnchantmentElementalDamage(ID_ENCHANTMENT + 1);
        i.astralTransmutation = new EnchantmentAstralTransmutation(ID_ENCHANTMENT + 2);
    }
    
    @Override
    public void addBlockHarvestLevels()
    {
        MinecraftForge.setBlockHarvestLevel(blockVordicGem, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(stoneResistant, "pickaxe", 3);
        MinecraftForge.setBlockHarvestLevel(obsidianFalse, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(oreVordic, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(oreVordicGem, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(shimmerstone, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(shimmerstoneluminous, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(oreAstral, "pickaxe", 4);
        MinecraftForge.setBlockHarvestLevel(astralViewer, "pickaxe", 3);
        MinecraftForge.setBlockHarvestLevel(astralObsidian, "pickaxe", 3);
        MinecraftForge.setBlockHarvestLevel(emberstone, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(blockAstralCrystal, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(oreDarkQuartz, "pickaxe", 4);
        MinecraftForge.setBlockHarvestLevel(blockDarkQuartz, "pickaxe", 3);
        MinecraftForge.setBlockHarvestLevel(brickDarkQuartz, "pickaxe", 3);
    }
    
    @Override
    public void addNames()
    {
        LanguageRegistry.addName(stoneArcane, "Arcane Transmutation Stone");
        LanguageRegistry.addName(dustVim, "Vim Powder");
        LanguageRegistry.addName(dustVordicStabilised, "Stabilised Vordic Powder");
        LanguageRegistry.addName(stoneSphereWeighted, "Weighted Stone Sphere");
        LanguageRegistry.addName(dustVordic, "Vordic Powder");
        LanguageRegistry.addName(arcaneCompendium, "Arcane Compendium");
        LanguageRegistry.addName(arcaneMatter, "Arcane Matter");
        LanguageRegistry.addName(ingotArcaneSteel, "Arcane Steel");
        LanguageRegistry.addName(ringMagma, "Ring of Eternal Magma");
        LanguageRegistry.addName(tabletCrafting, "Formulation Tablet");
        LanguageRegistry.addName(toolVordic, "Vordic Working Tool");
        LanguageRegistry.addName(medallionDualPerception, "Medallion of Dual Perceptions");
        LanguageRegistry.addName(ingotObsidian, "Obsidian Ingot");
        LanguageRegistry.addName(cloakInvis, "Cloak of Invisibility");
        LanguageRegistry.addName(fabricIllusion, "Illusionist's Fabric");
        LanguageRegistry.addName(essenceEnd, "End Essence");
        LanguageRegistry.addName(silkWoven, "Woven Silk");
        LanguageRegistry.addName(dustSear, "Sear Powder");
        LanguageRegistry.addName(charmHealing, "Charm of Finite Immortality");
        LanguageRegistry.addName(astralCrystal, "Astral Crystal");
        for (int i = 0; i < ItemCog.names.length; i++)
        {
            LanguageRegistry.addName(new ItemStack(cog, 1, i), ItemCog.localisedNames[i] + " Cog");
        }
        LanguageRegistry.addName(ingotAstralSteel, "Astral Steel");
        LanguageRegistry.addName(astralCrystalShard, "Astral Crystal Shard");
        LanguageRegistry.addName(dustAstral, "Astral Dust");
        LanguageRegistry.addName(wandLightning, "Sceptor of Lightning Striking");
        LanguageRegistry.addName(wandAscension, "Sceptor of Ascension");
        LanguageRegistry.addName(gauntletAstral, "Astral Gauntlet");
        LanguageRegistry.addName(astralCellEnergy, "Astral Energy Cell");
        LanguageRegistry.addName(astralPearlEnergy, "Astral Energy Pearl");
        LanguageRegistry.addName(astralMechanismDrive, "Astral Mechanism Drive");
        LanguageRegistry.addName(quartzDark, "Dark Quartz");
        LanguageRegistry.addName(plateAstralCrystal, "Astral Crystal Plate");
        LanguageRegistry.addName(matterDense, "Dense Matter");
        for (int i = 0; i < ElementManager.getElementList().length; i++)
        {
            LanguageRegistry.addName(new ItemStack(gemElemental, 1, i), "Elemental Gem");
        }
        for (int i = 0; i < ItemAuraGem.types.length; i++)
        {
            LanguageRegistry.addName(new ItemStack(gemAura, 1, i), "Auric Gem");
        }
        LanguageRegistry.addName(plateAuric, "Auric Plate");
        LanguageRegistry.addName(stoneAstral, "Astral Transmutation Stone");
        LanguageRegistry.addName(elementalIgniter, "Elemental Igniter");
        LanguageRegistry.addName(wandAir, "Wand of Streaming Air");
        LanguageRegistry.addName(starElemental, "Elemental Star");
        LanguageRegistry.addName(astralLinkingCard, "Astral Linking Card");
        
        LanguageRegistry.addName(oreVordic, "Solid Vord Stone");
        LanguageRegistry.addName(blockArcaneSteel, "Arcane Steel Block");
        LanguageRegistry.addName(shimmerstone, "Shimmerstone");
        LanguageRegistry.addName(shimmerstoneluminous, "Luminous Shimmerstone");
        LanguageRegistry.addName(blockVordicGem, "Vord Crystal");
        LanguageRegistry.addName(oreVordicGem, "Vord Crystal Ore");
        LanguageRegistry.addName(stoneResistant, "Resistance Stone");
        LanguageRegistry.addName(torchVordic, "Vordic Powder Torch");
        LanguageRegistry.addName(obsidianFalse, "False Obsidian");
        LanguageRegistry.addName(astralViewer, "Astral Viewer");
        LanguageRegistry.addName(oreAstral, "Astral Crystal Ore");
        LanguageRegistry.addName(astralObsidian, "Astral Marked Obsidian");
        LanguageRegistry.addName(emberstone, "Emberstone");
        LanguageRegistry.addName(blockAstralCrystal, "Astral Crystal Block");
        LanguageRegistry.addName(astralMechansimBlock, "Astral Mechanism");
        LanguageRegistry.addName(astralTotem1, "Astral Totem");
        LanguageRegistry.addName(torchSear, "Sear Torch");
        LanguageRegistry.addName(blockAstralSteel, "Astral Steel Block");
        LanguageRegistry.addName(workbenchArcane, "Arcane Workbench");
        LanguageRegistry.addName(blockDarkQuartz, "Dark Quartz Block");
        LanguageRegistry.addName(brickDarkQuartz, "Dark Quartz Brick");
        LanguageRegistry.addName(astralEnergyGate, "Astral Energy Gate");
        LanguageRegistry.addName(astralEnergyNode, "Astral Energy Node");
        LanguageRegistry.addName(oreDarkQuartz, "Dark Quartz Ore");
        LanguageRegistry.addName(glowBrick1, "Glowing Brick");
        LanguageRegistry.addName(glowBrick2, "Carved Glowing Brick");
        LanguageRegistry.addName(flowerGlowpetal, "Glowpetal");
        LanguageRegistry.addName(astralCraftingNode, "Astral Crafting Node");
        LanguageRegistry.addName(oreAuric, "Auric Ore");
        LanguageRegistry.addName(astralInfuser, "Astral Infuser");
        LanguageRegistry.addName(astralGraphMatrix, "Astral Graph Matrix");
        LanguageRegistry.addName(astralCapacitorCPU, "Astral Capacitor CPU");
        LanguageRegistry.addName(astralStructure, "Astral Structure");
        LanguageRegistry.addName(astralCapacitorHousing, "Astral Capacitor Housing");
        LanguageRegistry.addName(astralCapacitorIOInterface, "Astral Capacitor IO Interfacing Port");
        LanguageRegistry.addName(astralEnergyExtractionGridCore, "Astral Energy Extraction Grid Core");
        LanguageRegistry.addName(astralEnergyFieldDrain, "Astral Energy Field Point Drain");
        LanguageRegistry.addName(astralKineticGenerator, "Astral Kinetic Generator");
        LanguageRegistry.addName(this.astralThermalKineticConvertor, "Astral Thermalkinetic Convertor");
        
        LanguageRegistry.addName(swordfire, "Sword of Incandescence");
        LanguageRegistry.addName(alchmetalpick, "Arcane Steel Pickaxe");
        LanguageRegistry.addName(alchmetalshovel, "Arcane Steel Shovel");
        LanguageRegistry.addName(alchmetalaxe, "Arcane Steel Axe");
        LanguageRegistry.addName(alchmetalsword, "Arcane Steel Sword");
        LanguageRegistry.addName(alchmetalhoe, "Arcane Steel Hoe");
        LanguageRegistry.addName(swordelement, "Sword of Elements");
    }
    
    @Override
    public void addRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(this.stoneSphereWeighted, 1), new Object[] {
            "AAA",
            "ABA",
            "AAA",
            'A', Block.stone,
            'B', Item.goldNugget
        });
        
        GameRegistry.addRecipe(new ItemStack(this.stoneSphereWeighted, 1), new Object[] {
            "AAA",
            "ABA",
            "AAA",
            'A', Block.cobblestone,
            'B', Item.goldNugget
        });
        
        GameRegistry.addShapelessRecipe(new ItemStack(this.dustVim, 3), new Object[] {
            this.dustVordic,
            this.dustVordicStabilised,
            Item.redstone
        });
        
        GameRegistry.addRecipe(new ItemStack(this.stoneArcane, 1), new Object[] {
            "ABA",
            "BCB",
            "ABA",
            'A', this.dustVim,
            'B', this.dustVordicStabilised,
            'C', this.stoneSphereWeighted
        });
        
        GameRegistry.addRecipe(new ItemStack(this.stoneArcane, 1), new Object[] {
            "ABA",
            "BCB",
            "ABA",
            'B', this.dustVim,
            'A', this.dustVordicStabilised,
            'C', this.stoneSphereWeighted
        });
        
        GameRegistry.addRecipe(new ItemStack(this.workbenchArcane, 1), new Object[] {
            "BAB",
            "CBC",
            "CDC",
            'A', this.stoneArcane,
            'B', this.dustVordicStabilised,
            'C', this.dustVordic,
            'D', Block.workbench
        });
        
        GameRegistry.addShapelessRecipe(new ItemStack(this.ingotArcaneSteel, 9), new Object[] {
            this.blockArcaneSteel
        });
    }
    
    @Override
    public void addSmeltingRecipes()
    {
        GameRegistry.addSmelting(dustVordic.itemID, new ItemStack(dustVordicStabilised, 1), 1.75f);
        GameRegistry.addSmelting(dustAstral.itemID, new ItemStack(astralCrystalShard, 1), 3.0f);
    }
    
    @Override
    public void loadBiomes()
    {
        GameRegistry.addBiome(valerianforest);
        BiomeManager.addSpawnBiome(valerianforest);
        BiomeDictionary.registerBiomeType(valerianforest, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.MUSHROOM, BiomeDictionary.Type.FOREST);
    }
}
