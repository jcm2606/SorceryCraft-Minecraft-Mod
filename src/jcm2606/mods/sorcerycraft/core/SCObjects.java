package jcm2606.mods.sorcerycraft.core;

import jcm2606.mods.jccore.core.IObjectCore;
import jcm2606.mods.jccore.util.BiomeUtil;
import jcm2606.mods.jccore.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.api.SCApi;
import jcm2606.mods.sorcerycraft.astral.gauntlet.ModeHailkenisis;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralCrystal;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralEnergyGate;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralEnergyNode;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralMechanism;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralObsidian;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralStaticCharger;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralSteel;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralTotem1;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralViewer;
import jcm2606.mods.sorcerycraft.block.fluid.BlockFluidVordic;
import jcm2606.mods.sorcerycraft.block.main.BlockAlchMetal;
import jcm2606.mods.sorcerycraft.block.main.BlockAncientBookshelf;
import jcm2606.mods.sorcerycraft.block.main.BlockArcaneWorkbench;
import jcm2606.mods.sorcerycraft.block.main.BlockCrystal;
import jcm2606.mods.sorcerycraft.block.main.BlockDarkQuartz;
import jcm2606.mods.sorcerycraft.block.main.BlockDarkQuartzBrick;
import jcm2606.mods.sorcerycraft.block.main.BlockEmberstone;
import jcm2606.mods.sorcerycraft.block.main.BlockEntityTracker;
import jcm2606.mods.sorcerycraft.block.main.BlockFlowerGlow;
import jcm2606.mods.sorcerycraft.block.main.BlockGlowBrick;
import jcm2606.mods.sorcerycraft.block.main.BlockGlowBrickItem;
import jcm2606.mods.sorcerycraft.block.main.BlockInfuseTablet;
import jcm2606.mods.sorcerycraft.block.main.BlockObsidianFalse;
import jcm2606.mods.sorcerycraft.block.main.BlockOreAstral;
import jcm2606.mods.sorcerycraft.block.main.BlockOreDarkQuartz;
import jcm2606.mods.sorcerycraft.block.main.BlockOreVord;
import jcm2606.mods.sorcerycraft.block.main.BlockOreVordicGem;
import jcm2606.mods.sorcerycraft.block.main.BlockSearTorch;
import jcm2606.mods.sorcerycraft.block.main.BlockShimmerStone;
import jcm2606.mods.sorcerycraft.block.main.BlockStoneResistant;
import jcm2606.mods.sorcerycraft.block.main.BlockVordicTorch;
import jcm2606.mods.sorcerycraft.core.config.Config;
import jcm2606.mods.sorcerycraft.core.helper.RecipeHelper;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import jcm2606.mods.sorcerycraft.enchant.EnchantmentRottingTouch;
import jcm2606.mods.sorcerycraft.enchant.Enchantments;
import jcm2606.mods.sorcerycraft.fluid.FluidVordic;
import jcm2606.mods.sorcerycraft.item.SCItem;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralControlSceptor;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralCrystal;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralCrystalPlate;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralCrystalShard;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralDust;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyCell;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyNetworkLinkCreator;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyPearl;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralGauntlet;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralMechanismDrive;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralTablet;
import jcm2606.mods.sorcerycraft.item.astral.ItemIngotAstralSteel;
import jcm2606.mods.sorcerycraft.item.charm.ItemCharm;
import jcm2606.mods.sorcerycraft.item.charm.ItemCharmMortality;
import jcm2606.mods.sorcerycraft.item.main.ItemAlchMatter;
import jcm2606.mods.sorcerycraft.item.main.ItemAlchStone;
import jcm2606.mods.sorcerycraft.item.main.ItemBookAlch;
import jcm2606.mods.sorcerycraft.item.main.ItemCog;
import jcm2606.mods.sorcerycraft.item.main.ItemDarkQuartz;
import jcm2606.mods.sorcerycraft.item.main.ItemDenseMatter;
import jcm2606.mods.sorcerycraft.item.main.ItemDustEnergy;
import jcm2606.mods.sorcerycraft.item.main.ItemDustVordic;
import jcm2606.mods.sorcerycraft.item.main.ItemDustVordicStablized;
import jcm2606.mods.sorcerycraft.item.main.ItemEndEssence;
import jcm2606.mods.sorcerycraft.item.main.ItemFirePowder;
import jcm2606.mods.sorcerycraft.item.main.ItemGlassFlask;
import jcm2606.mods.sorcerycraft.item.main.ItemIllusionFabric;
import jcm2606.mods.sorcerycraft.item.main.ItemIngotAlchMetal;
import jcm2606.mods.sorcerycraft.item.main.ItemTomeKnowledge;
import jcm2606.mods.sorcerycraft.item.main.ItemVordicTool;
import jcm2606.mods.sorcerycraft.item.main.ItemWaterFlask;
import jcm2606.mods.sorcerycraft.item.special.ItemInvisCloak;
import jcm2606.mods.sorcerycraft.item.special.ItemMedallionPerception;
import jcm2606.mods.sorcerycraft.item.special.ItemProtectionStone;
import jcm2606.mods.sorcerycraft.item.special.ItemRingMagma;
import jcm2606.mods.sorcerycraft.item.special.ItemSceptorAscension;
import jcm2606.mods.sorcerycraft.item.special.ItemSceptorLightningStrike;
import jcm2606.mods.sorcerycraft.item.special.ItemTabletCreation;
import jcm2606.mods.sorcerycraft.item.tool.ItemAxeAlchMetal;
import jcm2606.mods.sorcerycraft.item.tool.ItemHoeAlchMetal;
import jcm2606.mods.sorcerycraft.item.tool.ItemOmniPickaxe;
import jcm2606.mods.sorcerycraft.item.tool.ItemPickAlchMetal;
import jcm2606.mods.sorcerycraft.item.tool.ItemShovelAlchMetal;
import jcm2606.mods.sorcerycraft.item.tool.ItemSwordAlchMetal;
import jcm2606.mods.sorcerycraft.item.tool.ItemSwordEnd;
import jcm2606.mods.sorcerycraft.item.tool.ItemSwordEndowment;
import jcm2606.mods.sorcerycraft.item.tool.ItemSwordFire;
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

    // VARIABLES
    public static Item dustvordic;
    public static Item dustvordicrefined;
    public static Item alchstone;
    public static Item stoneorb;
    public static Item dustenergy;
    public static Item alchbook;
    public static Item alchmatter;
    public static Item protectionstone;
    public static Item alchmetalingot;
    public static Item ringmagma;
    public static Item creationtablet;
    public static Item vordictool;
    public static Item medallionperception;
    public static Item obsidianingot;
    public static Item inviscloak;
    public static Item fabricillusion;
    public static Item endessence;
    public static Item wovensilk;
    public static Item firepowder;
    public static Item glassflask;
    public static Item waterflask;
    public static Item charmhealth;
    public static Item astralorecrystal;
    public static Item tomeknowledge;
    public static Item cog;
    public static Item ingotastralsteel;
    public static Item astralcrystalshard;
    public static Item astraldust;
    public static Item astralcontrolsceptor;
    public static Item elementsceptorlightning;
    public static Item sceptorascension;
    public static Item astralgauntlet;
    public static Item astraltablet;
    public static Item astralenergycell;
    public static Item astralenergypearl;
    public static Item astralmechanismdrive;
    public static Item darkquartz;
    public static Item astralenergynetworklinkcreator;
    public static Item astralcrystalplate;
    public static Item densematter;

    public static Block orevordic;
    public static Block alchmetalblock;
    public static Block infusetablet;
    public static Block orevordicgem;
    public static Block shimmerstone;
    public static Block chestsorcerer;
    public static Block shimmerstoneluminous;
    public static Block vordicgemblock;
    public static Block stoneresistant;
    public static Block vordictorch;
    public static Block obsidianfalse;
    public static Block entitytracker;
    public static Block magicskull;
    public static Block astralviewer;
    public static Block oreastral;
    public static Block astralobsidian;
    public static Block emberstone;
    public static Block astralcrystalblock;
    public static Block ancientbookshelf;
    public static Block astralmechanismblock;
    public static Block astraltotem1;
    public static Block seartorch;
    public static Block astralsteelblock;
    public static Block arcaneworkbench;
    public static Block darkquartzblock;
    public static Block darkquartzbrick;
    public static Block astralenergygate;
    public static Block astralenergynode;
    public static Block astralstaticcharger;
    public static Block oredarkquartz;
    public static Block glowbrick1;
    public static Block glowbrick2;
    public static Block flowerglowpetal;
    public static Block fluidvordic;

    public static Item swordfire;
    public static Item swordendowment;
    public static Item omnipick;
    public static Item alchmetalpick;
    public static Item alchmetalshovel;
    public static Item alchmetalaxe;
    public static Item alchmetalsword;
    public static Item alchmetalhoe;
    public static Item swordend;
    
    public static BiomeGenBase valerianforest;

    // IDS
    public static int ID_DUST_VORDIC;
    public static int ID_DUST_VORDIC_REFINED;
    public static int ID_STONE_ALCH;
    public static int ID_STONE_WEIGHT;
    public static int ID_DUST_ENERGY;
    public static int ID_ALCH_BOOK;
    public static int ID_ALCH_MATTER;
    public static int ID_STONE_PROTECTION;
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
    public static int ID_GLASS_FLASK;
    public static int ID_WATER_FLASK;
    public static int ID_CHARM_HEALTH;
    public static int ID_ASTRAL_ORE_CRYSTAL;
    public static int ID_TOME_KNOWLEDGE;
    public static int ID_COG;
    public static int ID_INGOT_ASTRAL_STEEL;
    public static int ID_ASTRAL_CRYSTAL_SHARD;
    public static int ID_ASTRAL_DUST;
    public static int ID_ASTRAL_CONTROL_SCEPTOR;
    public static int ID_ELEMENT_SCEPTOR_LIGHTNING;
    public static int ID_SCEPTOR_ASCENSION;
    public static int ID_ASTRAL_GAUNTLET;
    public static int ID_ASTRAL_TABLET;
    public static int ID_ASTRAL_ENERGY_CELL;
    public static int ID_ASTRAL_ENERGY_PEARL;
    public static int ID_ASTRAL_MECHANISM_DRIVE;
    public static int ID_DARK_QUARTZ;
    public static int ID_ASTRAL_ENERGY_NETWORK_LINK_CREATOR;
    public static int ID_ASTRAL_CRYSTAL_PLATE;
    public static int ID_DENSE_MATTER;

    public static int ID_ORE_VORDIC;
    public static int ID_ALCH_METAL;
    public static int ID_INFUSE_TABLET;
    public static int ID_ORE_VORDIC_GEM;
    public static int ID_SHIMMER_STONE;
    public static int ID_SHIMMER_STONE_LUMINOUS;
    public static int ID_VORDIC_GEM_BLOCK;
    public static int ID_STONE_RESISTANT;
    public static int ID_VORDIC_TORCH;
    public static int ID_OBSIDIAN_FALSE;
    public static int ID_ENTITY_TRACKER;
    public static int ID_ASTRAL_VIEWER;
    public static int ID_ORE_ASTRAL;
    public static int ID_ASTRAL_OBSIDIAN;
    public static int ID_EMBER_STONE;
    public static int ID_ASTRAL_CRYSTAL_BLOCK;
    public static int ID_ANCIENT_BOOKSHELF;
    public static int ID_ASTRAL_MECHANISM_BLOCK;
    public static int ID_ASTRAL_TOTEM_1;
    public static int ID_SEAR_TORCH;
    public static int ID_ASTRAL_STEEL_BLOCK;
    public static int ID_ARCANE_WORKBENCH;
    public static int ID_DARK_QUARTZ_BLOCK;
    public static int ID_DARK_QUARTZ_BRICK;
    public static int ID_ASTRAL_ENERGY_GATE;
    public static int ID_ASTRAL_ENERGY_NODE;
    public static int ID_ASTRAL_STATIC_CHARGER;
    public static int ID_ORE_DARK_QUARTZ;
    public static int ID_GLOW_BRICK_1;
    public static int ID_GLOW_BRICK_2;
    public static int ID_FLOWER_GLOW_PETAL;
    public static int ID_FLUID_VORDIC;

    public static int ID_SWORD_FIRE;
    public static int ID_SWORD_ENDOWMENT;
    public static int ID_OMNI_PICK;
    public static int ID_ALCH_METAL_PICK;
    public static int ID_ALCH_METAL_SHOVEL;
    public static int ID_ALCH_METAL_AXE;
    public static int ID_ALCH_METAL_SWORD;
    public static int ID_ALCH_METAL_HOE;
    public static int ID_SWORD_END;

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

        vordicfluid = new FluidVordic();

        // Items
        dustvordic = new ItemDustVordic(ID_DUST_VORDIC);
        dustvordicrefined = new ItemDustVordicStablized(ID_DUST_VORDIC_REFINED);
        alchstone = new ItemAlchStone(ID_STONE_ALCH);
        stoneorb = new SCItem(ID_STONE_WEIGHT, "weightedStoneSphere");
        dustenergy = new ItemDustEnergy(ID_DUST_ENERGY);
        alchbook = new ItemBookAlch(ID_ALCH_BOOK);
        alchmatter = new ItemAlchMatter(ID_ALCH_MATTER);
        protectionstone = new ItemProtectionStone(ID_STONE_PROTECTION);
        alchmetalingot = new ItemIngotAlchMetal(ID_ALCH_METAL_INGOT);
        ringmagma = new ItemRingMagma(ID_RING_MAGMA);
        creationtablet = new ItemTabletCreation(ID_CREATION_TABLET);
        vordictool = new ItemVordicTool(ID_VORDIC_TOOL);
        medallionperception = new ItemMedallionPerception(ID_MEDALLION_PERCEPTION);
        obsidianingot = new SCItem(ID_OBSIDIAN_INGOT, "ingotObsidian");
        inviscloak = new ItemInvisCloak(ID_INVIS_CLOAK);
        fabricillusion = new ItemIllusionFabric(ID_FABRIC_ILLUSION);
        endessence = new ItemEndEssence(ID_END_ESSENCE);
        wovensilk = new SCItem(ID_WOVEN_SILK, "wovenSilk");
        firepowder = new ItemFirePowder(ID_FIRE_POWDER);
        glassflask = new ItemGlassFlask(ID_GLASS_FLASK);
        waterflask = new ItemWaterFlask(ID_WATER_FLASK);
        charmhealth = new ItemCharmMortality(ID_CHARM_HEALTH);
        astralorecrystal = new ItemAstralCrystal(ID_ASTRAL_ORE_CRYSTAL);
        tomeknowledge = new ItemTomeKnowledge(ID_TOME_KNOWLEDGE);
        cog = new ItemCog(ID_COG);
        ingotastralsteel = new ItemIngotAstralSteel(ID_INGOT_ASTRAL_STEEL);
        astralcrystalshard = new ItemAstralCrystalShard(ID_ASTRAL_CRYSTAL_SHARD);
        astraldust = new ItemAstralDust(ID_ASTRAL_DUST);
        astralcontrolsceptor = new ItemAstralControlSceptor(ID_ASTRAL_CONTROL_SCEPTOR);
        elementsceptorlightning = new ItemSceptorLightningStrike(ID_ELEMENT_SCEPTOR_LIGHTNING);
        sceptorascension = new ItemSceptorAscension(ID_SCEPTOR_ASCENSION);
        astralgauntlet = new ItemAstralGauntlet(ID_ASTRAL_GAUNTLET);
        astraltablet = new ItemAstralTablet(ID_ASTRAL_TABLET);
        astralenergycell = new ItemAstralEnergyCell(ID_ASTRAL_ENERGY_CELL);
        astralenergypearl = new ItemAstralEnergyPearl(ID_ASTRAL_ENERGY_PEARL);
        astralmechanismdrive = new ItemAstralMechanismDrive(ID_ASTRAL_MECHANISM_DRIVE);
        darkquartz = new ItemDarkQuartz(ID_DARK_QUARTZ);
        astralenergynetworklinkcreator = new ItemAstralEnergyNetworkLinkCreator(ID_ASTRAL_ENERGY_NETWORK_LINK_CREATOR);
        astralcrystalplate = new ItemAstralCrystalPlate(ID_ASTRAL_CRYSTAL_PLATE);
        densematter = new ItemDenseMatter(ID_DENSE_MATTER);

        // Blocks
        orevordic = new BlockOreVord(ID_ORE_VORDIC);
        alchmetalblock = new BlockAlchMetal(ID_ALCH_METAL);
        infusetablet = new BlockInfuseTablet(ID_INFUSE_TABLET, Material.rock);
        orevordicgem = new BlockOreVordicGem(ID_ORE_VORDIC_GEM);
        shimmerstone = new BlockShimmerStone(ID_SHIMMER_STONE, "shimmerStone");
        shimmerstoneluminous = new BlockShimmerStone(ID_SHIMMER_STONE_LUMINOUS, "shimmerStoneLuminous").setLightValue(1.0F);
        vordicgemblock = new BlockCrystal(ID_VORDIC_GEM_BLOCK, Material.glass);
        stoneresistant = new BlockStoneResistant(ID_STONE_RESISTANT);
        vordictorch = new BlockVordicTorch(ID_VORDIC_TORCH);
        obsidianfalse = new BlockObsidianFalse(ID_OBSIDIAN_FALSE, Material.rock);
        entitytracker = new BlockEntityTracker(ID_ENTITY_TRACKER);
        astralviewer = new BlockAstralViewer(ID_ASTRAL_VIEWER);
        oreastral = new BlockOreAstral(ID_ORE_ASTRAL);
        astralobsidian = new BlockAstralObsidian(ID_ASTRAL_OBSIDIAN);
        emberstone = new BlockEmberstone(ID_EMBER_STONE);
        astralcrystalblock = new BlockAstralCrystal(ID_ASTRAL_CRYSTAL_BLOCK);
        ancientbookshelf = new BlockAncientBookshelf(ID_ANCIENT_BOOKSHELF);
        astralmechanismblock = new BlockAstralMechanism(ID_ASTRAL_MECHANISM_BLOCK);
        astraltotem1 = new BlockAstralTotem1(ID_ASTRAL_TOTEM_1);
        seartorch = new BlockSearTorch(ID_SEAR_TORCH);
        astralsteelblock = new BlockAstralSteel(ID_ASTRAL_STEEL_BLOCK);
        arcaneworkbench = new BlockArcaneWorkbench(ID_ARCANE_WORKBENCH);
        darkquartzblock = new BlockDarkQuartz(ID_DARK_QUARTZ_BLOCK);
        darkquartzbrick = new BlockDarkQuartzBrick(ID_DARK_QUARTZ_BRICK);
        astralenergygate = new BlockAstralEnergyGate(ID_ASTRAL_ENERGY_GATE);
        astralenergynode = new BlockAstralEnergyNode(ID_ASTRAL_ENERGY_NODE);
        astralstaticcharger = new BlockAstralStaticCharger(ID_ASTRAL_STATIC_CHARGER);
        oredarkquartz = new BlockOreDarkQuartz(ID_ORE_DARK_QUARTZ);
        glowbrick1 = new BlockGlowBrick(ID_GLOW_BRICK_1, "glowBrick1", "glowBrick1");
        glowbrick2 = new BlockGlowBrick(ID_GLOW_BRICK_2, "glowBrick2", "glowBrick2");
        flowerglowpetal = new BlockFlowerGlow(ID_FLOWER_GLOW_PETAL);
        fluidvordic = new BlockFluidVordic(ID_FLUID_VORDIC);

        // Tools
        swordfire = new ItemSwordFire(ID_SWORD_FIRE);
        swordendowment = new ItemSwordEndowment(ID_SWORD_ENDOWMENT);
        omnipick = new ItemOmniPickaxe(ID_OMNI_PICK);
        alchmetalpick = new ItemPickAlchMetal(ID_ALCH_METAL_PICK);
        alchmetalshovel = new ItemShovelAlchMetal(ID_ALCH_METAL_SHOVEL);
        alchmetalaxe = new ItemAxeAlchMetal(ID_ALCH_METAL_AXE);
        alchmetalsword = new ItemSwordAlchMetal(ID_ALCH_METAL_SWORD);
        alchmetalhoe = new ItemHoeAlchMetal(ID_ALCH_METAL_HOE);
        swordend = new ItemSwordEnd(ID_SWORD_END);

        // Biomes
        valerianforest = new BiomeGenValerianForest(BiomeUtil.getFreeBiomeID(Reference.BIOME_ID_START_VALUE));

        ItemCharm.registerCurses();
        SCApi.astralManager.loadCoreModes();
        ModeHailkenisis.loadCoolingEntries();

        registerBlocks();
        registerFluids();
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
        GeneralUtil.registerBlock(orevordic);
        GeneralUtil.registerBlock(alchmetalblock);
        GeneralUtil.registerBlock(infusetablet);
        GeneralUtil.registerBlock(orevordicgem);
        GeneralUtil.registerBlock(shimmerstone);
        GeneralUtil.registerBlock(shimmerstoneluminous);
        GeneralUtil.registerBlock(vordicgemblock);
        GeneralUtil.registerBlock(stoneresistant);
        GeneralUtil.registerBlock(vordictorch);
        GeneralUtil.registerBlock(obsidianfalse);
        GeneralUtil.registerBlock(entitytracker);
        GeneralUtil.registerBlock(astralviewer);
        GeneralUtil.registerBlock(oreastral);
        GeneralUtil.registerBlock(astralobsidian);
        GeneralUtil.registerBlock(emberstone);
        GeneralUtil.registerBlock(astralcrystalblock);
        GeneralUtil.registerBlock(ancientbookshelf);
        GeneralUtil.registerBlock(astralmechanismblock);
        GeneralUtil.registerBlock(astraltotem1);
        GeneralUtil.registerBlock(seartorch);
        GeneralUtil.registerBlock(astralsteelblock);
        GeneralUtil.registerBlock(arcaneworkbench);
        GeneralUtil.registerBlock(darkquartzblock);
        GeneralUtil.registerBlock(darkquartzbrick);
        GeneralUtil.registerBlock(astralenergygate);
        GeneralUtil.registerBlock(astralenergynode);
        GeneralUtil.registerBlock(astralstaticcharger);
        GeneralUtil.registerBlock(oredarkquartz);
        GeneralUtil.registerBlock(glowbrick1, BlockGlowBrickItem.class);
        GeneralUtil.registerBlock(glowbrick2, BlockGlowBrickItem.class);
        GeneralUtil.registerBlock(flowerglowpetal);
        GeneralUtil.registerBlock(fluidvordic);
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
        ID_STONE_PROTECTION = Config.getItemId("stoneAegis", Reference.ITEM_ID_START_VALUE).getInt();
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
        ID_GLASS_FLASK = Config.getItemId("glassFlask", Reference.ITEM_ID_START_VALUE).getInt();
        ID_WATER_FLASK = Config.getItemId("waterFlask", Reference.ITEM_ID_START_VALUE).getInt();
        ID_CHARM_HEALTH = Config.getItemId("charmImmortality", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_ORE_CRYSTAL = Config.getItemId("astralCrystal", Reference.ITEM_ID_START_VALUE).getInt();
        ID_TOME_KNOWLEDGE = Config.getItemId("bookKnowledge", Reference.ITEM_ID_START_VALUE).getInt();
        ID_COG = Config.getItemId("cog", Reference.ITEM_ID_START_VALUE).getInt();
        ID_INGOT_ASTRAL_STEEL = Config.getItemId("astralSteelIngot", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_CRYSTAL_SHARD = Config.getItemId("astralCrystalShard", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_DUST = Config.getItemId("astralDust", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_CONTROL_SCEPTOR = Config.getItemId("astralControlSceptor", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ELEMENT_SCEPTOR_LIGHTNING = Config.getItemId("elementSceptorLightning", Reference.ITEM_ID_START_VALUE).getInt();
        ID_SCEPTOR_ASCENSION = Config.getItemId("sceptorAscension", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_GAUNTLET = Config.getItemId("astralGantlet", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_TABLET = Config.getItemId("astralTablet", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_ENERGY_CELL = Config.getItemId("astralEnergyCell", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_ENERGY_PEARL = Config.getItemId("astralEnergyPearl", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_MECHANISM_DRIVE = Config.getItemId("astralMechanismDrive", Reference.ITEM_ID_START_VALUE).getInt();
        ID_DARK_QUARTZ = Config.getItemId("darkQuartz", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_ENERGY_NETWORK_LINK_CREATOR = Config.getItemId("astralEnergyNetworkLinkCreator", Reference.ITEM_ID_START_VALUE).getInt();
        ID_ASTRAL_CRYSTAL_PLATE = Config.getItemId("astralCrystalPlate", Reference.ITEM_ID_START_VALUE).getInt();
        ID_DENSE_MATTER = Config.getItemId("denseMatter", Reference.ITEM_ID_START_VALUE).getInt();

        // Blocks
        ID_ORE_VORDIC = Config.getBlockId("oreVordic", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ALCH_METAL = Config.getBlockId("alchMetalBlock", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_INFUSE_TABLET = Config.getBlockId("infusionTablet", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ORE_VORDIC_GEM = Config.getBlockId("oreVordicGem", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_SHIMMER_STONE = Config.getBlockId("shimmerStone", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_SHIMMER_STONE_LUMINOUS = Config.getBlockId("shimmerStoneLuminous", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_VORDIC_GEM_BLOCK = Config.getBlockId("vordGemBlock", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_STONE_RESISTANT = Config.getBlockId("stoneResistance", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_VORDIC_TORCH = Config.getBlockId("vordciTorch", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_OBSIDIAN_FALSE = Config.getBlockId("obsidianFalse", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ENTITY_TRACKER = Config.getBlockId("entityTracer", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_VIEWER = Config.getBlockId("astralViewer", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ORE_ASTRAL = Config.getBlockId("oreAstral", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_OBSIDIAN = Config.getBlockId("astralObsidian", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_EMBER_STONE = Config.getBlockId("emberStone", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_CRYSTAL_BLOCK = Config.getBlockId("astralCrystalBlock", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ANCIENT_BOOKSHELF = Config.getBlockId("ancientBookshelf", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_MECHANISM_BLOCK = Config.getBlockId("astralMechanism", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_TOTEM_1 = Config.getBlockId("astralTotem1", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_SEAR_TORCH = Config.getBlockId("searTorch", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_STEEL_BLOCK = Config.getBlockId("astralSteelBlock", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ARCANE_WORKBENCH = Config.getBlockId("arcaneWorkbench", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_DARK_QUARTZ_BLOCK = Config.getBlockId("darkQuartz", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_DARK_QUARTZ_BRICK = Config.getBlockId("darkQuartzBrick", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_ENERGY_GATE = Config.getBlockId("astralEnergyGate", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_ENERGY_NODE = Config.getBlockId("astralEnergyNode", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ASTRAL_STATIC_CHARGER = Config.getBlockId("astralStaticCharger", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ORE_DARK_QUARTZ = Config.getBlockId("oreDarkQuartz", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_GLOW_BRICK_1 = Config.getBlockId("glowBrickDesign1", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_GLOW_BRICK_2 = Config.getBlockId("glowBrickDesign2", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_FLOWER_GLOW_PETAL = Config.getBlockId("glowPetal", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_FLUID_VORDIC = Config.getBlockId("fluidVordic", Reference.BLOCK_ID_START_VALUE).getInt();

        // Tools
        ID_SWORD_FIRE = Config.getToolId("swordFire", Reference.ITEM_TOOL_ID_START_VALUE).getInt();
        ID_SWORD_ENDOWMENT = Config.getToolId("swordEndowment", Reference.ITEM_TOOL_ID_START_VALUE).getInt();
        ID_OMNI_PICK = Config.getToolId("omniPickaxe", Reference.ITEM_TOOL_ID_START_VALUE).getInt();
        ID_ALCH_METAL_PICK = Config.getToolId("alchMetalPickaxe", Reference.ITEM_TOOL_ID_START_VALUE).getInt();
        ID_ALCH_METAL_SHOVEL = Config.getToolId("alchMetalShovel", Reference.ITEM_TOOL_ID_START_VALUE).getInt();
        ID_ALCH_METAL_AXE = Config.getToolId("alchMetalAxe", Reference.ITEM_TOOL_ID_START_VALUE).getInt();
        ID_ALCH_METAL_SWORD = Config.getToolId("alchMetalSword", Reference.ITEM_TOOL_ID_START_VALUE).getInt();
        ID_ALCH_METAL_HOE = Config.getToolId("alchMetalHoe", Reference.ITEM_TOOL_ID_START_VALUE).getInt();
        ID_SWORD_END = Config.getToolId("swordEnd", Reference.ITEM_TOOL_ID_START_VALUE).getInt();

        // Enchantments
        ID_ENCHANTMENT = Config.getEnchantmentId("enchantment", 52).getInt();
    }

    @Override
    public void loadEnchantments()
    {
        Enchantments i = new Enchantments();
        
        i.rottingTouch = new EnchantmentRottingTouch(ID_ENCHANTMENT);
    }

    @Override
    public void registerFluids()
    {
    }

    @Override
    public void addBlockHarvestLevels()
    {
        MinecraftForge.setBlockHarvestLevel(vordicgemblock, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(stoneresistant, "pickaxe", 3);
        MinecraftForge.setBlockHarvestLevel(obsidianfalse, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(orevordic, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(orevordicgem, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(shimmerstone, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(shimmerstoneluminous, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(oreastral, "pickaxe", 4);
        MinecraftForge.setBlockHarvestLevel(astralviewer, "pickaxe", 3);
        MinecraftForge.setBlockHarvestLevel(astralobsidian, "pickaxe", 3);
        MinecraftForge.setBlockHarvestLevel(emberstone, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(astralcrystalblock, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(oredarkquartz, "pickaxe", 4);
        MinecraftForge.setBlockHarvestLevel(darkquartzblock, "pickaxe", 3);
        MinecraftForge.setBlockHarvestLevel(darkquartzbrick, "pickaxe", 3);
    }

    @Override
    public void addNames()
    {
        LanguageRegistry.addName(alchstone, "Arcane Stone");
        LanguageRegistry.addName(dustenergy, "Vim Powder");
        LanguageRegistry.addName(dustvordicrefined, "Stabilised Vordic Powder");
        LanguageRegistry.addName(stoneorb, "Weighted Stone Sphere");
        LanguageRegistry.addName(dustvordic, "Vordic Powder");
        LanguageRegistry.addName(alchbook, "Arcane Compendium");
        LanguageRegistry.addName(alchmatter, "Arcane Matter");
        LanguageRegistry.addName(protectionstone, "Aegis Stone");
        LanguageRegistry.addName(alchmetalingot, "Arcane Steel");
        LanguageRegistry.addName(ringmagma, "Ring of Eternal Magma");
        LanguageRegistry.addName(creationtablet, "Formulation Tablet");
        LanguageRegistry.addName(vordictool, "Vordic Working Tool");
        LanguageRegistry.addName(medallionperception, "Medallion of Dual Perceptions");
        LanguageRegistry.addName(obsidianingot, "Obsidian Ingot");
        LanguageRegistry.addName(inviscloak, "Cloak of Invisibility");
        LanguageRegistry.addName(fabricillusion, "Illusionist's Fabric");
        LanguageRegistry.addName(endessence, "End Essence");
        LanguageRegistry.addName(wovensilk, "Woven Silk");
        LanguageRegistry.addName(firepowder, "Sear Powder");
        LanguageRegistry.addName(glassflask, "Glass Flask");
        LanguageRegistry.addName(waterflask, "Water Flask");
        LanguageRegistry.addName(charmhealth, "Charm of Finite Immortality");
        LanguageRegistry.addName(astralorecrystal, "Astral Crystal");
        LanguageRegistry.addName(tomeknowledge, "Tome of Knowledge");
        for (int i = 0; i < ItemCog.names.length; i++)
        {
            LanguageRegistry.addName(new ItemStack(cog, 1, i), ItemCog.localisedNames[i] + " Cog");
        }
        LanguageRegistry.addName(ingotastralsteel, "Astral Steel");
        LanguageRegistry.addName(astralcrystalshard, "Astral Crystal Shard");
        LanguageRegistry.addName(astraldust, "Astral Dust");
        LanguageRegistry.addName(astralcontrolsceptor, "Astral Control Sceptor");
        LanguageRegistry.addName(elementsceptorlightning, "Sceptor of Lightning Striking");
        LanguageRegistry.addName(sceptorascension, "Sceptor of Ascension");
        LanguageRegistry.addName(astralgauntlet, "Astral Gauntlet");
        LanguageRegistry.addName(astraltablet, "Astral Rune Tablet");
        LanguageRegistry.addName(astralenergycell, "Astral Energy Cell");
        LanguageRegistry.addName(astralenergypearl, "Astral Energy Pearl");
        LanguageRegistry.addName(astralmechanismdrive, "Astral Mechanism Drive");
        LanguageRegistry.addName(darkquartz, "Dark Quartz");
        LanguageRegistry.addName(astralcrystalplate, "Astral Crystal Plate");
        LanguageRegistry.addName(densematter, "Dense Matter");
        LanguageRegistry.addName(astralenergynetworklinkcreator, "Astral Energy Network Linking Unit");

        LanguageRegistry.addName(orevordic, "Solid Vord Stone");
        LanguageRegistry.addName(alchmetalblock, "Arcane Steel Block");
        LanguageRegistry.addName(infusetablet, "Imbusion Tablet");
        LanguageRegistry.addName(shimmerstone, "Shimmerstone");
        LanguageRegistry.addName(shimmerstoneluminous, "Luminous Shimmerstone");
        LanguageRegistry.addName(vordicgemblock, "Vord Crystal");
        LanguageRegistry.addName(orevordicgem, "Vord Crystal Ore");
        LanguageRegistry.addName(stoneresistant, "Resistance Stone");
        LanguageRegistry.addName(vordictorch, "Vordic Powder Torch");
        LanguageRegistry.addName(obsidianfalse, "False Obsidian");
        LanguageRegistry.addName(entitytracker, "Arcane Entity Tracer");
        LanguageRegistry.addName(astralviewer, "Astral Viewer");
        LanguageRegistry.addName(oreastral, "Astral Crystal Ore");
        LanguageRegistry.addName(astralobsidian, "Astral Marked Obsidian");
        LanguageRegistry.addName(emberstone, "Emberstone");
        LanguageRegistry.addName(astralcrystalblock, "Astral Crystal Block");
        LanguageRegistry.addName(ancientbookshelf, "Ancient Bookshelf");
        LanguageRegistry.addName(astralmechanismblock, "Astral Mechanism");
        LanguageRegistry.addName(astraltotem1, "Astral Totem");
        LanguageRegistry.addName(seartorch, "Sear Torch");
        LanguageRegistry.addName(astralsteelblock, "Astral Steel Block");
        LanguageRegistry.addName(arcaneworkbench, "Arcane Workbench");
        LanguageRegistry.addName(darkquartzblock, "Dark Quartz Block");
        LanguageRegistry.addName(darkquartzbrick, "Dark Quartz Brick");
        LanguageRegistry.addName(astralenergygate, "Astral Energy Gate");
        LanguageRegistry.addName(astralenergynode, "Astral Energy Node");
        LanguageRegistry.addName(astralstaticcharger, "Astral Static Charger");
        LanguageRegistry.addName(oredarkquartz, "Dark Quartz Ore");
        LanguageRegistry.addName(glowbrick1, "Glowing Brick");
        LanguageRegistry.addName(glowbrick2, "Carved Glowing Brick");
        LanguageRegistry.addName(flowerglowpetal, "Glowpetal");

        LanguageRegistry.addName(swordfire, "Sword of the Phoenix");
        LanguageRegistry.addName(swordendowment, "Sword of Endowment");
        LanguageRegistry.addName(omnipick, "Pickaxe of the Omniverse");
        LanguageRegistry.addName(alchmetalpick, "Arcane Steel Pickaxe");
        LanguageRegistry.addName(alchmetalshovel, "Arcane Steel Shovel");
        LanguageRegistry.addName(alchmetalaxe, "Arcane Steel Axe");
        LanguageRegistry.addName(alchmetalsword, "Arcane Steel Sword");
        LanguageRegistry.addName(alchmetalhoe, "Arcane Steel Hoe");
        LanguageRegistry.addName(swordend, "Sword of the End Realms");
    }

    @Override
    public void addRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(alchstone, 1), new Object[]
        { "ABA", "BCB", "ABA", 'A', dustvordicrefined, 'B', dustenergy, 'C', stoneorb });

        GameRegistry.addRecipe(new ItemStack(alchstone, 1), new Object[]
        { "ABA", "BCB", "ABA", 'A', dustenergy, 'B', dustvordicrefined, 'C', stoneorb });

        GameRegistry.addRecipe(new ItemStack(stoneorb, 1), new Object[]
        { " A ", "ABA", " A ", 'A', Block.stone, 'B', Item.goldNugget });

        GameRegistry.addShapelessRecipe(new ItemStack(dustenergy, 3), new Object[]
        { dustvordic, Item.redstone, dustvordicrefined });

        GameRegistry.addShapelessRecipe(new ItemStack(alchmetalingot, 9), new Object[]
        { alchmetalblock });

        GameRegistry.addRecipe(new ItemStack(shimmerstone, 4), new Object[]
        { "BBB", "BAB", "BBB", 'A', Block.stoneBrick, 'B', vordicgemblock });

        RecipeHelper.ArcaneWorkbenchHelper.addRecipe(new ItemStack(shimmerstoneluminous, 1), new Object[]
        { "BBB", "BAB", "BBB", 'A', shimmerstone, 'B', Item.glowstone });

        GameRegistry.addRecipe(new ItemStack(stoneresistant, 2), new Object[]
        { "ABA", "BCB", "ABA", 'A', Block.stone, 'B', obsidianingot, 'C', new ItemStack(Block.stoneBrick, 1, 0) });

        RecipeHelper.ArcaneWorkbenchHelper.addRecipe(new ItemStack(vordictool, 1), new Object[]
        { "A A", "ABA", " C ", 'A', Item.ingotIron, 'B', vordicgemblock, 'C', alchmetalingot });

        GameRegistry.addRecipe(new ItemStack(alchmetalpick, 1), new Object[]
        { "AAA", " B ", " B ", 'A', alchmetalingot, 'B', Item.stick });

        GameRegistry.addRecipe(new ItemStack(alchmetalshovel, 1), new Object[]
        { "A", "B", "B", 'A', alchmetalingot, 'B', Item.stick });

        GameRegistry.addRecipe(new ItemStack(alchmetalaxe, 1), new Object[]
        { "AA", "BA", "B ", 'A', alchmetalingot, 'B', Item.stick });

        GameRegistry.addRecipe(new ItemStack(alchmetalsword, 1), new Object[]
        { "A", "A", "B", 'A', alchmetalingot, 'B', Item.stick });

        GameRegistry.addRecipe(new ItemStack(alchmetalhoe, 1), new Object[]
        { "AA", "B ", "B ", 'A', alchmetalingot, 'B', Item.stick });

        GameRegistry.addRecipe(new ItemStack(vordictorch, 1), new Object[]
        { "B", "A", 'A', Item.stick, 'B', dustvordic });

        GameRegistry.addShapelessRecipe(new ItemStack(Item.gunpowder, 1), new Object[]
        { Item.coal, new ItemStack(ringmagma, 1, -1) });

        GameRegistry.addShapelessRecipe(new ItemStack(Item.redstone, 1), new Object[]
        { Item.gunpowder, new ItemStack(ringmagma, 1, -1) });

        RecipeHelper.ArcaneWorkbenchHelper.addRecipe(new ItemStack(inviscloak, 1), new Object[]
        { "ACA", "ABA", "AAA", 'A', fabricillusion, 'B', Item.enderPearl, 'C', Item.silk });

        GameRegistry.addRecipe(new ItemStack(wovensilk, 2), new Object[]
        { "A ", " A", 'A', Item.silk });

        GameRegistry.addRecipe(new ItemStack(wovensilk, 2), new Object[]
        { " A", "A ", 'A', Item.silk });

        RecipeHelper.ArcaneWorkbenchHelper.addRecipe(new ItemStack(fabricillusion, 1), new Object[]
        { "AAA", "ABA", "AAA", 'A', wovensilk, 'B', endessence });

        RecipeHelper.ArcaneWorkbenchHelper.addRecipe(new ItemStack(inviscloak, 1), new Object[]
        { "ACA", "ABA", "AAA", 'A', fabricillusion, 'B', Item.enderPearl, 'C', Item.silk });

        GameRegistry.addShapelessRecipe(new ItemStack(firepowder, 1), new Object[]
        { Item.redstone, new ItemStack(ringmagma, 1, -1) });

        RecipeHelper.InfusionTabletHelper.addRecipe(new ItemStack(Item.blazeRod, 1), new Object[]
        { "CAC", "ABA", "CAC", 'A', firepowder, 'B', Item.stick, 'C', dustvordicrefined });

        GameRegistry.addRecipe(new ItemStack(Block.torchWood, 1), new Object[]
        { "A", "B", 'A', firepowder, 'B', Item.stick });

        RecipeHelper.InfusionTabletHelper.addShapelessRecipe(new ItemStack(Item.blazePowder, 2), new Object[]
        { firepowder, dustvordic });

        RecipeHelper.ArcaneWorkbenchHelper.addRecipe(new ItemStack(swordfire, 1), new Object[]
        { "CD ", "CD ", "BAB", 'A', Item.ingotIron, 'B', Item.leather, 'C', firepowder, 'D', obsidianingot });

        GameRegistry.addRecipe(new ItemStack(glassflask, 1), new Object[]
        { "ABA", "A A", " A ", 'A', Block.thinGlass, 'B', Item.clay });

        GameRegistry.addRecipe(new ItemStack(Block.obsidian), new Object[]
        { "AAA", "AAA", "AAA", 'A', obsidianingot });

        RecipeHelper.ArcaneWorkbenchHelper.addRecipe(new ItemStack(obsidianfalse, 1), new Object[]
        { " A ", "ABA", " A ", 'A', obsidianingot, 'B', Block.stone });

        RecipeHelper.ArcaneWorkbenchHelper.addRecipe(new ItemStack(obsidianfalse, 1), new Object[]
        { " A ", "ABA", " A ", 'A', obsidianingot, 'B', Block.cobblestone });

        RecipeHelper.ArcaneWorkbenchHelper.addRecipe(new ItemStack(obsidianfalse, 1), new Object[]
        { " A ", "ABA", " A ", 'A', obsidianingot, 'B', Block.stoneBrick });

        GameRegistry.addRecipe(new ItemStack(astralcrystalblock, 1), new Object[]
        { "AAA", "AAA", "AAA", 'A', astralorecrystal });

        GameRegistry.addRecipe(new ItemStack(astralviewer, 1), new Object[]
        { "ABA", "BCB", "ABA", 'A', obsidianingot, 'B', Block.glass, 'C', astralcrystalblock });

        GameRegistry.addShapelessRecipe(new ItemStack(astralorecrystal, 9), new Object[]
        { astralcrystalblock });

        GameRegistry.addRecipe(new ItemStack(cog, 1, 0), new Object[]
        { " B ", "BAB", " B ", 'A', Block.planks, 'B', Block.cobblestone });

        GameRegistry.addRecipe(new ItemStack(cog, 1, 0), new Object[]
        { " B ", "BAB", " B ", 'A', Block.planks, 'B', Block.stone });

        GameRegistry.addRecipe(new ItemStack(cog, 1, 1), new Object[]
        { " B ", "BAB", " B ", 'A', Block.planks, 'B', Item.ingotIron });

        GameRegistry.addRecipe(new ItemStack(cog, 1, 2), new Object[]
        { " B ", "BAB", " B ", 'A', new ItemStack(cog, 1, 1), 'B', astralorecrystal });

        GameRegistry.addRecipe(new ItemStack(cog, 1, 3), new Object[]
        { " B ", "BAB", " B ", 'A', new ItemStack(cog, 1, 1), 'B', alchmetalingot });

        GameRegistry.addRecipe(new ItemStack(astralmechanismblock, 1), new Object[]
        { "ADA", "BCB", "ADA", 'A', ingotastralsteel, 'B', astralmechanismdrive, 'C', Block.blockIron, 'D', new ItemStack(cog, 1, 2) });

        GameRegistry.addShapelessRecipe(new ItemStack(astralcrystalshard, 3), new Object[]
        { astralorecrystal });

        GameRegistry.addRecipe(new ItemStack(astralorecrystal, 1), new Object[]
        { "AAA", "AAA", "AAA", 'A', astralcrystalshard });

        GameRegistry.addRecipe(new ItemStack(Block.tnt, 1), new Object[]
        { "BAB", "ABA", "BAB", 'A', Block.sand, 'B', firepowder });

        GameRegistry.addRecipe(new ItemStack(alchmetalblock, 1), new Object[]
        { "AAA", "AAA", "AAA", 'A', alchmetalingot });

        GameRegistry.addRecipe(new ItemStack(astralsteelblock, 1), new Object[]
        { "AAA", "AAA", "AAA", 'A', ingotastralsteel });

        RecipeHelper.ArcaneWorkbenchHelper.addRecipe(new ItemStack(astralmechanismdrive, 1), new Object[]
        { "CBC", "BAB", "CBC", 'A', ingotastralsteel, 'B', new ItemStack(cog, 1, 2), 'C', astralorecrystal });

        GameRegistry.addRecipe(new ItemStack(darkquartzblock, 1), new Object[]
        { "AA", "AA", 'A', darkquartz });

        GameRegistry.addRecipe(new ItemStack(darkquartzbrick, 1), new Object[]
        { "AA", "AA", 'A', darkquartzblock });

        GameRegistry.addRecipe(new ItemStack(arcaneworkbench, 1), new Object[]
        { "BAB", "BCB", "DDD", 'A', alchstone, 'B', dustvordicrefined, 'C', Block.workbench, 'D', Block.planks });

        GameRegistry.addRecipe(new ItemStack(astralcrystalplate, 1), new Object[]
        { "AAA", "ABA", "AAA", 'A', astralorecrystal, 'B', ingotastralsteel });

        GameRegistry.addRecipe(new ItemStack(densematter, 1), new Object[]
        { "AAA", "ABA", "AAA", 'A', astralcrystalplate, 'B', alchmatter });

        GameRegistry.addRecipe(new ItemStack(darkquartz, 1), new Object[]
        { "AAA", "ABA", "AAA", 'A', densematter, 'B', Item.netherQuartz });
    }

    @Override
    public void addSmeltingRecipes()
    {
        GameRegistry.addSmelting(dustvordic.itemID, new ItemStack(dustvordicrefined, 1), 1.75f);
        GameRegistry.addSmelting(waterflask.itemID, new ItemStack(glassflask, 1), 0.0f);
        GameRegistry.addSmelting(astraldust.itemID, new ItemStack(astralcrystalshard, 1), 3.0f);
    }

    @Override
    public void loadBiomes()
    {
        GameRegistry.addBiome(valerianforest);
        BiomeManager.addSpawnBiome(valerianforest);
        BiomeDictionary.registerBiomeType(valerianforest, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.MUSHROOM, BiomeDictionary.Type.FOREST);
    }
}
