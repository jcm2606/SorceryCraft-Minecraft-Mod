package jcm2606.mods.sorcerycraft;

import java.util.HashMap;

import jcm2606.mods.jccore.core.IObjectCore;
import jcm2606.mods.jccore.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.block.BlockAlchMetal;
import jcm2606.mods.sorcerycraft.block.BlockAlchPodium;
import jcm2606.mods.sorcerycraft.block.BlockAncientBookshelf;
import jcm2606.mods.sorcerycraft.block.BlockCrystal;
import jcm2606.mods.sorcerycraft.block.BlockEmberstone;
import jcm2606.mods.sorcerycraft.block.BlockEntityTracker;
import jcm2606.mods.sorcerycraft.block.BlockInfuseTablet;
import jcm2606.mods.sorcerycraft.block.BlockObsidianFalse;
import jcm2606.mods.sorcerycraft.block.BlockOreAstral;
import jcm2606.mods.sorcerycraft.block.BlockOreVord;
import jcm2606.mods.sorcerycraft.block.BlockOreVordicGem;
import jcm2606.mods.sorcerycraft.block.BlockSearTorch;
import jcm2606.mods.sorcerycraft.block.BlockShimmerStone;
import jcm2606.mods.sorcerycraft.block.BlockStonePodium;
import jcm2606.mods.sorcerycraft.block.BlockStoneResistant;
import jcm2606.mods.sorcerycraft.block.BlockVordicTorch;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralCrystal;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralMechanism;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralObsidian;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralSteel;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralTotem1;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralViewer;
import jcm2606.mods.sorcerycraft.block.mundane.BlockBouncer;
import jcm2606.mods.sorcerycraft.block.mundane.BlockEntityDetector;
import jcm2606.mods.sorcerycraft.block.mundane.BlockMechanismBase;
import jcm2606.mods.sorcerycraft.block.mundane.BlockMechanismBaseOverloaded;
import jcm2606.mods.sorcerycraft.block.mundane.BlockTeleporter;
import jcm2606.mods.sorcerycraft.config.Config;
import jcm2606.mods.sorcerycraft.enchant.EnchantmentAeronautics;
import jcm2606.mods.sorcerycraft.enchant.EnchantmentFireswordInternalHeat;
import jcm2606.mods.sorcerycraft.enchant.EnchantmentRebuild;
import jcm2606.mods.sorcerycraft.enchant.EnchantmentRecharge;
import jcm2606.mods.sorcerycraft.enchant.EnchantmentSlowCore;
import jcm2606.mods.sorcerycraft.enchant.EnchantmentVenom;
import jcm2606.mods.sorcerycraft.enchant.Enchantments;
import jcm2606.mods.sorcerycraft.helper.RecipeHelper;
import jcm2606.mods.sorcerycraft.item.SCItem;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralControlSceptor;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralCrystal;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralCrystalShard;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralDust;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyCell;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyPearl;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralTablet;
import jcm2606.mods.sorcerycraft.item.astral.ItemIngotAstralSteel;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.AstralGauntletManager;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.ItemAstralGauntlet;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode.ModeCooling;
import jcm2606.mods.sorcerycraft.item.charm.ItemCharm;
import jcm2606.mods.sorcerycraft.item.charm.ItemCharmMortality;
import jcm2606.mods.sorcerycraft.item.charm.ItemCharmSight;
import jcm2606.mods.sorcerycraft.item.herblore.ItemDustNeroll;
import jcm2606.mods.sorcerycraft.item.herblore.ItemNerollRoot;
import jcm2606.mods.sorcerycraft.item.main.ItemAlchMatter;
import jcm2606.mods.sorcerycraft.item.main.ItemAlchStone;
import jcm2606.mods.sorcerycraft.item.main.ItemBookAlch;
import jcm2606.mods.sorcerycraft.item.main.ItemCog;
import jcm2606.mods.sorcerycraft.item.main.ItemDustEnergy;
import jcm2606.mods.sorcerycraft.item.main.ItemDustVordic;
import jcm2606.mods.sorcerycraft.item.main.ItemDustVordicStablized;
import jcm2606.mods.sorcerycraft.item.main.ItemEndEssence;
import jcm2606.mods.sorcerycraft.item.main.ItemFirePowder;
import jcm2606.mods.sorcerycraft.item.main.ItemGlassFlask;
import jcm2606.mods.sorcerycraft.item.main.ItemIllusionFabric;
import jcm2606.mods.sorcerycraft.item.main.ItemIngotAlchMetal;
import jcm2606.mods.sorcerycraft.item.main.ItemInvisCloak;
import jcm2606.mods.sorcerycraft.item.main.ItemNaturesHighwinds;
import jcm2606.mods.sorcerycraft.item.main.ItemProtectionStone;
import jcm2606.mods.sorcerycraft.item.main.ItemTabletCreation;
import jcm2606.mods.sorcerycraft.item.main.ItemTomeKnowledge;
import jcm2606.mods.sorcerycraft.item.main.ItemVordicTool;
import jcm2606.mods.sorcerycraft.item.main.ItemWaterFlask;
import jcm2606.mods.sorcerycraft.item.special.ItemElementSceptorLightning;
import jcm2606.mods.sorcerycraft.item.special.ItemRingMagma;
import jcm2606.mods.sorcerycraft.item.special.ItemTabletAscension;
import jcm2606.mods.sorcerycraft.item.tool.ItemAxeAlchMetal;
import jcm2606.mods.sorcerycraft.item.tool.ItemHoeAlchMetal;
import jcm2606.mods.sorcerycraft.item.tool.ItemOmniPickaxe;
import jcm2606.mods.sorcerycraft.item.tool.ItemPickAlchMetal;
import jcm2606.mods.sorcerycraft.item.tool.ItemShovelAlchMetal;
import jcm2606.mods.sorcerycraft.item.tool.ItemSwordAlchMetal;
import jcm2606.mods.sorcerycraft.item.tool.ItemSwordEnd;
import jcm2606.mods.sorcerycraft.item.tool.ItemSwordEndowment;
import jcm2606.mods.sorcerycraft.item.tool.ItemSwordFire;
import jcm2606.mods.sorcerycraft.item.wand.ItemWandCasting;
import jcm2606.mods.sorcerycraft.item.wand.ItemWandSorcery;
import jcm2606.mods.sorcerycraft.item.wand.WandManager;
import jcm2606.mods.sorcerycraft.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import apex.util.ApexIconIndexer;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class SCObjects implements IObjectCore {
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
    public static Item charmvision;
    public static Item obsidianingot;
    public static Item inviscloak;
    public static Item fabricillusion;
    public static Item endessence;
    public static Item wovensilk;
    public static Item firepowder;
    public static Item glassflask;
    public static Item waterflask;
    public static Item charmhealth;
    public static Item wandcasting;
    public static Item wandsorcery;
    public static Item astralorecrystal;
    public static Item natureshighwinds;
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
    
    public static Block orevordic;
    public static Block alchpodium;
    public static Block alchmetalblock;
    public static Block infusetablet;
    public static Block entitydetector;
    public static Block mechanismbase;
    public static Block mechanismbaseoverloaded;
    public static Block orevordicgem;
    public static Block shimmerstone;
    public static Block chestsorcerer;
    public static Block shimmerstoneluminous;
    public static Block vordicgemblock;
    public static Block stoneresistant;
    public static Block bouncer;
    public static Block teleporter;
    public static Block vordictorch;
    public static Block obsidianfalse;
    public static Block entitytracker;
    public static Block magicskull;
    public static Block stonepodium;
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
    
    public static Item swordfire;
    public static Item swordendowment;
    public static Item omnipick;
    public static Item alchmetalpick;
    public static Item alchmetalshovel;
    public static Item alchmetalaxe;
    public static Item alchmetalsword;
    public static Item alchmetalhoe;
    public static Item swordend;
    
    public static Item nerollroot;
    public static Item dustneroll;
    
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
    public static int ID_CHARM_VISION;
    public static int ID_OBSIDIAN_INGOT;
    public static int ID_INVIS_CLOAK;
    public static int ID_FABRIC_ILLUSION;
    public static int ID_END_ESSENCE;
    public static int ID_WOVEN_SILK;
    public static int ID_FIRE_POWDER;
    public static int ID_GLASS_FLASK;
    public static int ID_WATER_FLASK;
    public static int ID_CHARM_HEALTH;
    public static int ID_WAND_CASTING;
    public static int ID_WAND_SORCERY;
    public static int ID_ASTRAL_ORE_CRYSTAL;
    public static int ID_NATURES_HIGHWINDS;
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
    
    public static int ID_ORE_VORDIC;
    public static int ID_ALCHEMICAL_PODIUM;
    public static int ID_ALCH_METAL;
    public static int ID_INFUSE_TABLET;
    public static int ID_SLAB_ALCH_SINGLE;
    public static int ID_SLAB_ALCH_DOUBLE;
    public static int ID_REVERSE_ENGINEER_TABLET;
    public static int ID_ENTITY_DETECTOR;
    public static int ID_MECHANISM_BASE;
    public static int ID_MECHANISM_BASE_OVERLOADED;
    public static int ID_ORE_VORDIC_GEM;
    public static int ID_SHIMMER_STONE;
    public static int ID_CHEST_SORCERER;
    public static int ID_SHIMMER_STONE_LUMINOUS;
    public static int ID_VORDIC_GEM_BLOCK;
    public static int ID_STONE_RESISTANT;
    public static int ID_VORDIC_FURNACE_IDLE;
    public static int ID_VORDIC_FURNACE_ACTIVE;
    public static int ID_BOUNCER;
    public static int ID_TELEPORTER;
    public static int ID_ESSENCE_CHAMBER;
    public static int ID_VORDIC_TORCH;
    public static int ID_OBSIDIAN_FALSE;
    public static int ID_ENTITY_TRACKER;
    public static int ID_MAGIC_SKULL;
    public static int ID_STONE_PODIUM;
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
    
    public static int ID_SWORD_FIRE;
    public static int ID_SWORD_ENDOWMENT;
    public static int ID_OMNI_PICK;
    public static int ID_ALCH_METAL_PICK;
    public static int ID_ALCH_METAL_SHOVEL;
    public static int ID_ALCH_METAL_AXE;
    public static int ID_ALCH_METAL_SWORD;
    public static int ID_ALCH_METAL_HOE;
    public static int ID_SWORD_END;
    
    public static int ID_NEROLL_ROOT;
    public static int ID_DUST_NEROLL;
    
    public static int ID_BIOME_VALERIAN_FOREST;
    
    public static int ID_ENCHANTMENT_FROZEN_CORE;
    public static int ID_ENCHANTMENT_AERONAUTICS;
    public static int ID_ENCHANTMENT_VENOM;
    public static int ID_ENCHANTMENT_REBUILD;
    public static int ID_ENCHANTMENT_RECHARGE;
    public static int ID_ENCHANTMENT_ITNERNAL_HEAT;

    private static Configuration config = Config.config;
    
    @Override
    public void loadObjects()
    {
        PICKAXE_OMNI = EnumHelper.addToolMaterial("PICKAXE_OMNI", 3, 820, 8.0f, 3, 40);
        SWORD_GRIFFIN = EnumHelper.addToolMaterial("SWORD_GRIFFIN", 2, 420, 2.0f, 4, 33);
        SWORD_ENDOWMENT = EnumHelper.addToolMaterial("SWORD_ENDOWMENT", 3, 500, 2.0f, 10, 36);
        ALCH_METAL = EnumHelper.addToolMaterial("ALCH_METAL", 3, 600, 8.5f, 2, 30);
        SWORD_VORPAL = EnumHelper.addToolMaterial("SWORD_VORPAL", 0, 1020, 1.0f, 7, 50);
        SWORD_END = EnumHelper.addToolMaterial("SWORD_END", 2, 820, 2.0f, 10, 40);
        
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
        charmvision = new ItemCharmSight(ID_CHARM_VISION);
        obsidianingot = new SCItem(ID_OBSIDIAN_INGOT, "ingotObsidian");
        inviscloak = new ItemInvisCloak(ID_INVIS_CLOAK);
        fabricillusion = new ItemIllusionFabric(ID_FABRIC_ILLUSION);
        endessence = new ItemEndEssence(ID_END_ESSENCE);
        wovensilk = new SCItem(ID_WOVEN_SILK, "wovenSilk");
        firepowder = new ItemFirePowder(ID_FIRE_POWDER);
        glassflask = new ItemGlassFlask(ID_GLASS_FLASK);
        waterflask = new ItemWaterFlask(ID_WATER_FLASK);
        charmhealth = new ItemCharmMortality(ID_CHARM_HEALTH);
        wandcasting = new ItemWandCasting(ID_WAND_CASTING);
        wandsorcery = new ItemWandSorcery(ID_WAND_SORCERY);
        astralorecrystal = new ItemAstralCrystal(ID_ASTRAL_ORE_CRYSTAL);
        natureshighwinds = new ItemNaturesHighwinds(ID_NATURES_HIGHWINDS);
        tomeknowledge = new ItemTomeKnowledge(ID_TOME_KNOWLEDGE);
        cog = new ItemCog(ID_COG);
        ingotastralsteel = new ItemIngotAstralSteel(ID_INGOT_ASTRAL_STEEL);
        astralcrystalshard = new ItemAstralCrystalShard(ID_ASTRAL_CRYSTAL_SHARD);
        astraldust = new ItemAstralDust(ID_ASTRAL_DUST);
        astralcontrolsceptor = new ItemAstralControlSceptor(ID_ASTRAL_CONTROL_SCEPTOR);
        elementsceptorlightning = new ItemElementSceptorLightning(ID_ELEMENT_SCEPTOR_LIGHTNING);
        sceptorascension = new ItemTabletAscension(ID_SCEPTOR_ASCENSION);
        astralgauntlet = new ItemAstralGauntlet(ID_ASTRAL_GAUNTLET);
        astraltablet = new ItemAstralTablet(ID_ASTRAL_TABLET);
        astralenergycell = new ItemAstralEnergyCell(ID_ASTRAL_ENERGY_CELL);
        astralenergypearl = new ItemAstralEnergyPearl(ID_ASTRAL_ENERGY_PEARL);
        
        // Blocks
        orevordic = new BlockOreVord(ID_ORE_VORDIC).setHardness(2.0f).setUnlocalizedName("oreVord");
        alchpodium = new BlockAlchPodium(ID_ALCHEMICAL_PODIUM,Material.rock).setUnlocalizedName("alchPodium");
        alchmetalblock = new BlockAlchMetal(ID_ALCH_METAL).setUnlocalizedName("alchMetalBlock");
        infusetablet = new BlockInfuseTablet(ID_INFUSE_TABLET, Material.rock).setUnlocalizedName("infuseTablet");
        entitydetector = new BlockEntityDetector(ID_ENTITY_DETECTOR).setUnlocalizedName("mundaneEntityDetector");
        mechanismbase = new BlockMechanismBase(ID_MECHANISM_BASE, Material.iron).setUnlocalizedName("mundaneMechanismBase");
        mechanismbaseoverloaded = new BlockMechanismBaseOverloaded(ID_MECHANISM_BASE_OVERLOADED, Material.iron).setUnlocalizedName("mundaneMechanismBaseOverloaded");
        orevordicgem = new BlockOreVordicGem(ID_ORE_VORDIC_GEM).setUnlocalizedName("oreVordicGem");
        shimmerstone = new BlockShimmerStone(ID_SHIMMER_STONE, "shimmerStone").setUnlocalizedName("shimmerStone");
        shimmerstoneluminous = new BlockShimmerStone(ID_SHIMMER_STONE_LUMINOUS, "shimmerStoneLuminous").setLightValue(1.0F).setUnlocalizedName("shimmerStoneLuminous");
        vordicgemblock = new BlockCrystal(ID_VORDIC_GEM_BLOCK, Material.glass).setUnlocalizedName("vordicGem");
        stoneresistant = new BlockStoneResistant(ID_STONE_RESISTANT).setUnlocalizedName("stoneResistant");
        bouncer = new BlockBouncer(ID_BOUNCER, 1.0).setUnlocalizedName("mundaneBouncer");
        teleporter = new BlockTeleporter(ID_TELEPORTER, Material.iron).setUnlocalizedName("mundaneTeleporter");
        vordictorch = new BlockVordicTorch(ID_VORDIC_TORCH).setUnlocalizedName("vordicTorch");
        obsidianfalse = new BlockObsidianFalse(ID_OBSIDIAN_FALSE, Material.rock).setUnlocalizedName("obsidianFalse");
        entitytracker = new BlockEntityTracker(ID_ENTITY_TRACKER);
        stonepodium = new BlockStonePodium(ID_STONE_PODIUM);
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
        
        // Herblore Items
        nerollroot = new ItemNerollRoot(ID_NEROLL_ROOT);
        dustneroll = new ItemDustNeroll(ID_DUST_NEROLL);
        
        ItemCharm.registerCurses();
        WandManager.loadBehaviours();
        AstralGauntletManager.loadCoreModes();
        ModeCooling.loadCoolingEntries();
        
        registerBlocks();
        registerFluids();
        loadEnchantments();
        addBlockHarvestLevels();
        addRecipes();
        addSmeltingRecipes();
        addNames();
    }
    
    @Override
    public void registerBlocks()
    {
        GeneralUtil.registerBlock(orevordic);
        GeneralUtil.registerBlock(alchpodium);
        GeneralUtil.registerBlock(alchmetalblock);
        GeneralUtil.registerBlock(infusetablet);
        GeneralUtil.registerBlock(entitydetector);
        GeneralUtil.registerBlock(mechanismbase);
        GeneralUtil.registerBlock(mechanismbaseoverloaded);
        GeneralUtil.registerBlock(orevordicgem);
        GeneralUtil.registerBlock(shimmerstone);
//      GeneralUtil.registerBlock(chestsorcerer);
        GeneralUtil.registerBlock(shimmerstoneluminous);
        GeneralUtil.registerBlock(vordicgemblock);
        GeneralUtil.registerBlock(stoneresistant);
        GeneralUtil.registerBlock(bouncer);
//      GeneralUtil.registerBlock(teleporter);
        GeneralUtil.registerBlock(vordictorch);
        GeneralUtil.registerBlock(obsidianfalse);
        GeneralUtil.registerBlock(entitytracker);
//      GeneralUtil.registerBlock(magicskull);
        GeneralUtil.registerBlock(stonepodium);
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
        ID_CHARM_VISION = Config.getItemId("charmVision", Reference.ITEM_ID_START_VALUE).getInt();
        ID_OBSIDIAN_INGOT = Config.getItemId("obsidianIngot", Reference.ITEM_ID_START_VALUE).getInt();
        ID_INVIS_CLOAK = Config.getItemId("cloakInvis", Reference.ITEM_ID_START_VALUE).getInt();
        ID_FABRIC_ILLUSION = Config.getItemId("fabricIllusionists", Reference.ITEM_ID_START_VALUE).getInt();
        ID_END_ESSENCE = Config.getItemId("endEssence", Reference.ITEM_ID_START_VALUE).getInt();
        ID_WOVEN_SILK = Config.getItemId("wovenSilk", Reference.ITEM_ID_START_VALUE).getInt();
        ID_FIRE_POWDER = Config.getItemId("firePowder", Reference.ITEM_ID_START_VALUE).getInt();
        ID_GLASS_FLASK = Config.getItemId("glassFlask", Reference.ITEM_ID_START_VALUE).getInt();
        ID_WATER_FLASK = Config.getItemId("waterFlask", Reference.ITEM_ID_START_VALUE).getInt();
        ID_CHARM_HEALTH = Config.getItemId("charmImmortality", Reference.ITEM_ID_START_VALUE).getInt();
        ID_WAND_CASTING = Config.getItemWandId("wandCasting", Reference.ITEM_WAND_ID_START_VALUE).getInt();
        ID_WAND_SORCERY = Config.getItemWandId("wandSorcery", Reference.ITEM_WAND_ID_START_VALUE).getInt();
        ID_ASTRAL_ORE_CRYSTAL = Config.getItemId("astralCrystal", Reference.ITEM_ID_START_VALUE).getInt();
        ID_NATURES_HIGHWINDS = Config.getItemId("naturesHighwinds", Reference.ITEM_ID_START_VALUE).getInt();
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
        
        // Blocks
        ID_ORE_VORDIC = Config.getBlockId("oreVordic", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ALCHEMICAL_PODIUM = Config.getBlockId("alchPodium", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ALCH_METAL = Config.getBlockId("alchMetalBlock", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_INFUSE_TABLET = Config.getBlockId("infusionTablet", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ENTITY_DETECTOR = Config.getBlockId("entityDetector", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_MECHANISM_BASE = Config.getBlockId("mechanismBase", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_MECHANISM_BASE_OVERLOADED = Config.getBlockId("mechanismBaseOverloaded", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ORE_VORDIC_GEM = Config.getBlockId("oreVordicGem", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_SHIMMER_STONE = Config.getBlockId("shimmerStone", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_SHIMMER_STONE_LUMINOUS = Config.getBlockId("shimmerStoneLuminous", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_VORDIC_GEM_BLOCK = Config.getBlockId("vordGemBlock", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_STONE_RESISTANT = Config.getBlockId("stoneResistance", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_BOUNCER = Config.getBlockId("mundaneBouncer", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_TELEPORTER = Config.getBlockId("mundaneTeleporter", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_VORDIC_TORCH = Config.getBlockId("vordciTorch", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_OBSIDIAN_FALSE = Config.getBlockId("obsidianFalse", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_ENTITY_TRACKER = Config.getBlockId("entityTracer", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_MAGIC_SKULL = Config.getBlockId("magicalSkull", Reference.BLOCK_ID_START_VALUE).getInt();
        ID_STONE_PODIUM = Config.getBlockId("stonePodium", Reference.BLOCK_ID_START_VALUE).getInt();
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
        
        ID_NEROLL_ROOT = Config.getItemHerbloreId("nerollRoot", 10600).getInt();
        ID_DUST_NEROLL = Config.getItemHerbloreId("dustNeroll", 10601).getInt();
        
        // Biomes
        ID_BIOME_VALERIAN_FOREST = Config.getBiomeId("valerianForest", 23).getInt(23);
        
        // Enchantments
        ID_ENCHANTMENT_FROZEN_CORE = Config.getEnchantmentId("frozenCore", 52).getInt(52);
        ID_ENCHANTMENT_AERONAUTICS = Config.getEnchantmentId("aeronautics", 53).getInt(53);
        ID_ENCHANTMENT_VENOM = Config.getEnchantmentId("venomAspect", 54).getInt(54);
        ID_ENCHANTMENT_REBUILD = Config.getEnchantmentId("rebuiling", 55).getInt(55);
        ID_ENCHANTMENT_RECHARGE = Config.getEnchantmentId("enderRecharge", 56).getInt(56);
        ID_ENCHANTMENT_ITNERNAL_HEAT = Config.getEnchantmentId("internalMagmaCore", 57).getInt(57);
    }
    
    @Override
    public void loadEnchantments()
    {
        Enchantments i = new Enchantments();
        
        i.frozencore = new EnchantmentSlowCore(ID_ENCHANTMENT_FROZEN_CORE, 50);
        i.venom = new EnchantmentVenom(ID_ENCHANTMENT_VENOM, 30);
        i.aeronautics = new EnchantmentAeronautics(ID_ENCHANTMENT_AERONAUTICS, 40);
        i.rebuild = new EnchantmentRebuild(ID_ENCHANTMENT_REBUILD, 20);
        i.recharge = new EnchantmentRecharge(ID_ENCHANTMENT_RECHARGE, 30);
        i.internalheat = new EnchantmentFireswordInternalHeat(ID_ENCHANTMENT_ITNERNAL_HEAT, 20);
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
        MinecraftForge.setBlockHarvestLevel(oreastral, "pickaxe", 3);
        MinecraftForge.setBlockHarvestLevel(astralviewer, "pickaxe", 3);
        MinecraftForge.setBlockHarvestLevel(astralobsidian, "pickaxe", 3);
        MinecraftForge.setBlockHarvestLevel(emberstone, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(astralcrystalblock, "pickaxe", 1);
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
        LanguageRegistry.addName(charmvision, "Charm of Expanded Perception");
        LanguageRegistry.addName(obsidianingot, "Obsidian Ingot");
        LanguageRegistry.addName(inviscloak, "Cloak of Invisibility");
        LanguageRegistry.addName(fabricillusion, "Illusionist's Fabric");
        LanguageRegistry.addName(endessence, "End Essence");
        LanguageRegistry.addName(wovensilk, "Woven Silk");
        LanguageRegistry.addName(firepowder, "Sear Powder");
        LanguageRegistry.addName(glassflask, "Glass Flask");
        LanguageRegistry.addName(waterflask, "Water Flask");
        LanguageRegistry.addName(charmhealth, "Charm of Finite Immortality");
        LanguageRegistry.addName(wandcasting, "Wand of Mundane Casting");
        LanguageRegistry.addName(wandsorcery, "Wand of Advanced Sorcery");
        LanguageRegistry.addName(astralorecrystal, "Astral Crystal");
        LanguageRegistry.addName(natureshighwinds, "Nature's Highwinds");
        LanguageRegistry.addName(tomeknowledge, "Tome of Knowledge");
        for(int i = 0; i < ItemCog.names.length; i++)
        {
            LanguageRegistry.addName(new ItemStack(cog, 1, i), ItemCog.localisedNames[i] + " Cog");
        }
        LanguageRegistry.addName(ingotastralsteel, "Astral Steel");
        LanguageRegistry.addName(astralcrystalshard, "Astral Crystal Shard");
        LanguageRegistry.addName(astraldust, "Astral Dust");
        LanguageRegistry.addName(astralcontrolsceptor, "Astral Control Sceptor");
        LanguageRegistry.addName(elementsceptorlightning, "Sceptor of Lightning");
        LanguageRegistry.addName(sceptorascension, "Sceptor of Ascension");
        LanguageRegistry.addName(astralgauntlet, "Astral Gauntlet");
        LanguageRegistry.addName(astraltablet, "Astral Rune Tablet");
        LanguageRegistry.addName(astralenergycell, "Astral Energy Cell");
        LanguageRegistry.addName(astralenergypearl, "Astral Energy Pearl");
        
        LanguageRegistry.addName(orevordic, "Solid Vord Stone");
        LanguageRegistry.addName(alchpodium, "Arcane Podium");
        LanguageRegistry.addName(alchmetalblock, "Arcane Steel Block");
        LanguageRegistry.addName(infusetablet, "Imbusion Tablet");
        LanguageRegistry.addName(entitydetector, "Mundane Entity Detector");
        LanguageRegistry.addName(mechanismbase,  "Mundane Mechanism Base");
        LanguageRegistry.addName(mechanismbaseoverloaded, "Overloaded Mechanism Base");
        LanguageRegistry.addName(shimmerstone, "Shimmerstone");
        LanguageRegistry.addName(shimmerstoneluminous, "Luminous Shimmerstone");
        LanguageRegistry.addName(vordicgemblock, "Vord Crystal");
        LanguageRegistry.addName(orevordicgem, "Vord Crystal Ore");
        LanguageRegistry.addName(stoneresistant, "Stone of Resistance");
        LanguageRegistry.addName(bouncer, "Mundane Entity Bouncer");
        LanguageRegistry.addName(vordictorch, "Vordic Powder Torch");
        LanguageRegistry.addName(obsidianfalse, "False Obsidian");
        LanguageRegistry.addName(entitytracker, "Arcane Entity Tracer");
        LanguageRegistry.addName(stonepodium, "Stone Podium");
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
        
        LanguageRegistry.addName(swordfire, "Sword of the Phoenix");
        LanguageRegistry.addName(swordendowment, "Sword of Endowment");
        LanguageRegistry.addName(omnipick, "Pickaxe of the Omniverse");
        LanguageRegistry.addName(alchmetalpick, "Arcane Steel Pickaxe");
        LanguageRegistry.addName(alchmetalshovel, "Arcane Steel Shovel");
        LanguageRegistry.addName(alchmetalaxe, "Arcane Steel Axe");
        LanguageRegistry.addName(alchmetalsword, "Arcane Steel Sword");
        LanguageRegistry.addName(alchmetalhoe, "Arcane Steel Hoe");
        LanguageRegistry.addName(swordend, "Sword of the End Realms");
        
        LanguageRegistry.addName(nerollroot, "Neroll Root");
        LanguageRegistry.addName(dustneroll, "Neroll Powder");
    }

    @Override
    public void addRecipes()
    {
        RecipeHelper.addUniversalRecipe(new ItemStack(alchstone, 1), new Object[] {
            "AAA",
            "BCB",
            "AAA",
            'A', dustvordicrefined,
            'B', dustenergy,
            'C', stoneorb
        });

        RecipeHelper.addUniversalRecipe(new ItemStack(stoneorb, 1), new Object[] {
            "AAA",
            "ABA",
            "AAA",
            'A', Block.stone,
            'B', Item.goldNugget
        });

        RecipeHelper.addUniversalShapelessRecipe(new ItemStack(dustenergy, 1), new Object[] {
            dustvordic,
            Item.redstone,
            dustvordicrefined
        });

        RecipeHelper.AlchemicalPodiumHelper.addRecipe(new ItemStack(infusetablet, 1), new Object[] {
            "ABA",
            "CDC",
            "EEE",
            'A', dustenergy,
            'B', Item.enderPearl,
            'C', dustvordicrefined,
            'D', alchstone,
            'E', Block.stoneSingleSlab,
        });

        RecipeHelper.AlchemicalPodiumHelper.addShapelessRecipe(new ItemStack(alchbook, 1), new Object[] {
            Item.book,
            Item.goldNugget
        });
        
        GameRegistry.addShapelessRecipe(new ItemStack(alchmetalingot, 9), new Object []{
            alchmetalblock
        });
        
        GameRegistry.addRecipe(new ItemStack(mechanismbase, 1), new Object []{
            "ABA",
            "CDC",
            "ABA",
            'A', Block.stone,
            'B', Item.redstone,
            'C', new ItemStack(cog, 1, 0),
            'D', Item.ingotIron
        });
        
        GameRegistry.addRecipe(new ItemStack(mechanismbase, 2), new Object []{
            "ABA",
            "CDC",
            "ABA",
            'A', Block.stone,
            'B', Item.redstone,
            'C', new ItemStack(cog, 1, 1),
            'D', Item.ingotIron
        });
        
        GameRegistry.addRecipe(new ItemStack(entitydetector, 1), new Object []{
            "DBD",
            "DCD",
            "EAE",
            'A', mechanismbase,
            'B', Block.pressurePlateStone,
            'C', Item.redstone,
            'D', Block.stone,
            'E', dustenergy
        });
        
        RecipeHelper.AlchemicalPodiumHelper.addShapelessRecipe(new ItemStack(creationtablet, 1), new Object []{
            alchmatter,
            alchstone,
            dustenergy,
            dustenergy,
            Block.workbench
        });
        
        RecipeHelper.AlchemicalPodiumHelper.addRecipe(new ItemStack(alchmatter, 1), new Object[] {
            "AAA",
            "ABA",
            "AAA",
            'A',Item.diamond,
            'B',creationtablet
        });
        
        GameRegistry.addRecipe(new ItemStack(shimmerstone, 4), new Object[] {
            "BBB",
            "BAB",
            "BBB",
            'A', Block.stoneBrick,
            'B', vordicgemblock
        });
        
        RecipeHelper.AlchemicalPodiumHelper.addRecipe(new ItemStack(shimmerstoneluminous, 1), new Object[] {
            "BBB",
            "BAB",
            "BBB",
            'A', shimmerstone,
            'B', Item.lightStoneDust
        });
        
        GameRegistry.addRecipe(new ItemStack(stoneresistant, 2), new Object[] {
            "ABA",
            "BCB",
            "ABA",
            'A', Block.stone,
            'B', obsidianingot,
            'C', new ItemStack(Block.stoneBrick, 1, 0)
        });
        
        RecipeHelper.AlchemicalPodiumHelper.addRecipe(new ItemStack(vordictool, 1), new Object[] {
            "A A",
            "ABA",
            " C ",
            'A', Item.ingotIron,
            'B', vordicgemblock,
            'C', alchmetalingot
        });
        
        GameRegistry.addRecipe(new ItemStack(alchmetalpick, 1), new Object[] {
            "AAA",
            " B ",
            " B ",
            'A', alchmetalingot,
            'B', Item.stick
        });
        
        GameRegistry.addRecipe(new ItemStack(alchmetalshovel, 1), new Object[] {
            "A",
            "B",
            "B",
            'A', alchmetalingot,
            'B', Item.stick
        });
        
        GameRegistry.addRecipe(new ItemStack(alchmetalaxe, 1), new Object[] {
            "AA",
            "BA",
            "B ",
            'A', alchmetalingot,
            'B', Item.stick
        });
        
        GameRegistry.addRecipe(new ItemStack(alchmetalsword, 1), new Object[] {
            "A",
            "A",
            "B",
            'A', alchmetalingot,
            'B', Item.stick
        });
        
        GameRegistry.addRecipe(new ItemStack(alchmetalhoe, 1), new Object[] {
            "AA",
            "B ",
            "B ",
            'A', alchmetalingot,
            'B', Item.stick
        });
        
        GameRegistry.addRecipe(new ItemStack(vordictorch, 1), new Object[] {
            "B",
            "A",
            'A',Item.stick,
            'B',dustvordic
        });
        
        GameRegistry.addShapelessRecipe(new ItemStack(Item.gunpowder, 1), new Object[] {
            Item.coal,
            new ItemStack(ringmagma, 1, -1)
        });
        
        GameRegistry.addShapelessRecipe(new ItemStack(Item.redstone, 1), new Object[] {
            Item.gunpowder,
            new ItemStack(ringmagma, 1, -1)
        });
        
        RecipeHelper.AlchemicalPodiumHelper.addRecipe(new ItemStack(inviscloak, 1), new Object[] {
            "ACA",
            "ABA",
            "AAA",
            'A', fabricillusion,
            'B', Item.enderPearl,
            'C', Item.silk
        });
        
        GameRegistry.addRecipe(new ItemStack(wovensilk, 2), new Object[] {
            "A ",
            " A",
            'A', Item.silk
        });
        
        GameRegistry.addRecipe(new ItemStack(wovensilk, 2), new Object[] {
            " A",
            "A ",
            'A', Item.silk
        });
        
        RecipeHelper.AlchemicalPodiumHelper.addRecipe(new ItemStack(fabricillusion, 1), new Object[] {
            "AAA",
            "ABA",
            "AAA",
            'A', wovensilk,
            'B', endessence
        });
        
        RecipeHelper.AlchemicalPodiumHelper.addRecipe(new ItemStack(inviscloak, 1), new Object[] {
            "ACA",
            "ABA",
            "AAA",
            'A', fabricillusion,
            'B', Item.enderPearl,
            'C', Item.silk
        });
        
        GameRegistry.addShapelessRecipe(new ItemStack(firepowder, 1), new Object[] {
            Item.redstone,
            new ItemStack(ringmagma, 1, -1)
        });
        
        RecipeHelper.InfusionTabletHelper.addRecipe(new ItemStack(Item.blazeRod, 1), new Object[] {
            "CAC",
            "ABA",
            "CAC",
            'A', firepowder,
            'B', Item.stick,
            'C', dustvordicrefined
        });
        
        GameRegistry.addRecipe(new ItemStack(Block.torchWood, 1), new Object[] {
            "A",
            "B",
            'A', firepowder,
            'B', Item.stick
        });
        
        RecipeHelper.InfusionTabletHelper.addShapelessRecipe(new ItemStack(Item.blazePowder, 2), new Object[] {
            firepowder,
            dustvordic
        });
        
        RecipeHelper.AlchemicalPodiumHelper.addRecipe(new ItemStack(swordfire, 1), new Object[] {
            "CD ",
            "CD ",
            "BAB",
            'A', Item.ingotIron,
            'B', Item.leather,
            'C', firepowder,
            'D', obsidianingot
        });
        
        GameRegistry.addShapelessRecipe(new ItemStack(dustneroll), new Object[] {
            nerollroot
        });
        
        GameRegistry.addRecipe(new ItemStack(glassflask, 1), new Object[] {
            "ABA",
            "A A",
            " A ",
            'A', Block.thinGlass,
            'B', Item.clay
        });
        
        GameRegistry.addRecipe(new ItemStack(Block.obsidian), new Object[] {
            "AAA",
            "AAA",
            "AAA",
            'A', obsidianingot
        });
        
        RecipeHelper.AlchemicalPodiumHelper.addRecipe(new ItemStack(obsidianfalse, 1), new Object[] {
            " A ",
            "ABA",
            " A ",
            'A', obsidianingot,
            'B', Block.stone
        });
        
        RecipeHelper.AlchemicalPodiumHelper.addRecipe(new ItemStack(obsidianfalse, 1), new Object[] {
            " A ",
            "ABA",
            " A ",
            'A', obsidianingot,
            'B', Block.cobblestone
        });
        
        RecipeHelper.AlchemicalPodiumHelper.addRecipe(new ItemStack(obsidianfalse, 1), new Object[] {
            " A ",
            "ABA",
            " A ",
            'A', obsidianingot,
            'B', Block.stoneBrick
        });
        
        GameRegistry.addRecipe(new ItemStack(astralcrystalblock, 1), new Object[] {
            "AAA",
            "AAA",
            "AAA",
            'A', astralorecrystal
        });
        
        GameRegistry.addRecipe(new ItemStack(astralviewer, 1), new Object[] {
            "ABA",
            "BCB",
            "ABA",
            'A', obsidianingot,
            'B', Block.glass,
            'C', astralcrystalblock
        });
        
        GameRegistry.addShapelessRecipe(new ItemStack(astralorecrystal, 9), new Object[] {
            astralcrystalblock
        });
        
        GameRegistry.addRecipe(new ItemStack(cog, 1, 0), new Object[] {
           " B ",
           "BAB",
           " B ",
           'A', Block.planks,
           'B', Block.cobblestone
        });
        
        GameRegistry.addRecipe(new ItemStack(cog, 1, 0), new Object[] {
               " B ",
               "BAB",
               " B ",
               'A', Block.planks,
               'B', Block.stone
        });
        
        GameRegistry.addRecipe(new ItemStack(cog, 1, 1), new Object[] {
               " B ",
               "BAB",
               " B ",
               'A', Block.planks,
               'B', Item.ingotIron
        });
        
        GameRegistry.addRecipe(new ItemStack(cog, 1, 2), new Object[] {
               " B ",
               "BAB",
               " B ",
               'A', new ItemStack(cog, 1, 1),
               'B', astralorecrystal
        });
        
        GameRegistry.addRecipe(new ItemStack(cog, 1, 3), new Object[] {
            " B ",
            "BAB",
            " B ",
            'A', new ItemStack(cog, 1, 1),
            'B', alchmetalingot
        });
        
        GameRegistry.addRecipe(new ItemStack(astralmechanismblock, 1), new Object[] {
            "ADA",
            "BCB",
            "ADA",
            'A', ingotastralsteel,
            'B', new ItemStack(cog, 1, 2),
            'C', mechanismbase,
            'D', astralorecrystal
        });
        
        GameRegistry.addShapelessRecipe(new ItemStack(astralcrystalshard, 3), new Object[] {
            astralorecrystal
        });
        
        GameRegistry.addRecipe(new ItemStack(astralorecrystal, 1), new Object[] {
            "AAA",
            "AAA",
            "AAA",
            'A', astralcrystalshard
        });
        
        GameRegistry.addRecipe(new ItemStack(Block.tnt, 1), new Object[] {
            "BAB",
            "ABA",
            "BAB",
            'A', Block.sand,
            'B', firepowder
        });
        
        GameRegistry.addRecipe(new ItemStack(alchmetalblock, 1), new Object[] {
            "AAA",
            "AAA",
            "AAA",
            'A', alchmetalingot
        });
        
        GameRegistry.addRecipe(new ItemStack(astralsteelblock, 1), new Object[] {
            "AAA",
            "AAA",
            "AAA",
            'A', ingotastralsteel
        });
    }

    @Override
    public void addSmeltingRecipes()
    {
        GameRegistry.addSmelting(dustvordic.itemID, new ItemStack(dustvordicrefined, 1), 1.75f);
        GameRegistry.addSmelting(waterflask.itemID, new ItemStack(glassflask, 1), 0.0f);
        GameRegistry.addSmelting(astraldust.itemID, new ItemStack(astralcrystalshard, 1), 3.0f);
    }

    @Override
    public void loadTextures(HashMap<String, Icon> iconMap, ApexIconIndexer index)
    {
        System.out.println(index.getEvent().map);
        
        if(index.getEvent().map == Minecraft.getMinecraft().renderEngine.textureMapBlocks)
        {
            SCIconManager.registerIcon("oreVord", true);
            SCIconManager.registerIcon("alchMetalBlock", true);
            SCIconManager.registerIcon("falseObsidian", true);
            SCIconManager.registerIcon("mundaneBouncerTop", true);
            SCIconManager.registerIcon("mundaneEntityDetectorTop", true);
            SCIconManager.registerIcon("mundaneMechanismBase", true);
            SCIconManager.registerIcon("mundaneMechanismBaseOverloaded", true);
            SCIconManager.registerIcon("oreVordicGem", true);
            SCIconManager.registerIcon("shimmerStone", true);
            SCIconManager.registerIcon("shimmerStoneLuminous", true);
            SCIconManager.registerIcon("stoneMachineBase", true);
            SCIconManager.registerIcon("stoneMachineSide", true);
            SCIconManager.registerIcon("stoneResistant", true);
            SCIconManager.registerIcon("vordicGem", true);
            SCIconManager.registerIcon("vordicTorch", true);
            SCIconManager.registerIcon("entityTracker", true);
            SCIconManager.registerIcon("emberStone", true);
            SCIconManager.registerIcon("ancientBookshelf", true);
            SCIconManager.registerIcon("searTorch", true);
            SCIconManager.registerIcon("astralSteelBlock", true);
            
            SCIconManager.registerIcon("astral_viewer_anim", false);
            SCIconManager.registerIcon("astral_crystal_anim", false);
            SCIconManager.registerIcon("astral_mechanism_anim", false);
        }
        
        if(index.getEvent().map == Minecraft.getMinecraft().renderEngine.textureMapItems)
        {
            SCIconManager.registerIcon("aegisStone", false);
            SCIconManager.registerIcon("bookAlch", "alchBook", false);
            SCIconManager.registerIcon("matterAlch", false);
            SCIconManager.registerIcon("alchStone", false);
            SCIconManager.registerIcon("amuletRepair", false);
            SCIconManager.registerIcon("charmSight", false);
            SCIconManager.registerIcon("invisCloak", false);
            SCIconManager.registerIcon("dustVim", false);
            SCIconManager.registerIcon("dustFire", false);
            SCIconManager.registerIcon("dustVordic", false);
            SCIconManager.registerIcon("dustVordicStabilized", false);
            SCIconManager.registerIcon("enchantedHilt", false);
            SCIconManager.registerIcon("endEssence", false);
            SCIconManager.registerIcon("essenceDark", false);
            SCIconManager.registerIcon("essenceEarth", false);
            SCIconManager.registerIcon("essenceEnergy", false);
            SCIconManager.registerIcon("essenceFlame", false);
            SCIconManager.registerIcon("essenceMagic", false);
            SCIconManager.registerIcon("essenceWater", false);
            SCIconManager.registerIcon("fabricIllusion", false);
            SCIconManager.registerIcon("flaskGlass", false);
            SCIconManager.registerIcon("flaskWater", false);
            SCIconManager.registerIcon("ingotAlchMetal", false);
            SCIconManager.registerIcon("ingotAlchMetalSoftened", false);
            SCIconManager.registerIcon("ingotObsidian", false);
            SCIconManager.registerIcon("noteBlank", false);
            SCIconManager.registerIcon("parchment", false);
            SCIconManager.registerIcon("noteWritten", false);
            SCIconManager.registerIcon("perspectiveGlassOrb", false);
            SCIconManager.registerIcon("ringMagma", false);
            SCIconManager.registerIcon("tabletFormulation", false);
            SCIconManager.registerIcon("toolVordic", false);
            SCIconManager.registerIcon("weightedStoneSphere", false);
            SCIconManager.registerIcon("wovenSilk", false);
            SCIconManager.registerIcon("endEye", false);
            SCIconManager.registerIcon("charmMortality", false);
            SCIconManager.registerIcon("lifeStone", false);
            SCIconManager.registerIcon("flaskLiquidFervor", false);
            SCIconManager.registerIcon("wandCasting", false);
            SCIconManager.registerIcon("wandSorcery", "wandCasting", false);
            SCIconManager.registerIcon("astralCrystal", false);
            SCIconManager.registerIcon("naturesHighwinds", false);
            SCIconManager.registerIcon("bookKnowledge", false);
            SCIconManager.registerIcon("cogStone", false);
            SCIconManager.registerIcon("cogMetal", false);
            SCIconManager.registerIcon("cogAstral", false);
            SCIconManager.registerIcon("cogArcane", false);
            SCIconManager.registerIcon("ingotAstralSteel", false);
            SCIconManager.registerIcon("astralCrystalShard", false);
            SCIconManager.registerIcon("astralDust", false);
            SCIconManager.registerIcon("astralControlSceptor", false);
            SCIconManager.registerIcon("elementSceptorLightning", false);
            SCIconManager.registerIcon("sceptorAscension", false);
            SCIconManager.registerIcon("astralGauntlet", false);
            SCIconManager.registerIcon("astralTablet", false);
            SCIconManager.registerIcon("astralEnergyCell", false);
            SCIconManager.registerIcon("astralEnergyPearl", false);
            
            SCIconManager.registerIcon("nerollRoot", false);
            SCIconManager.registerIcon("dustNeroll", false);
            SCIconManager.registerIcon("nerollWater", false);
            
            SCIconManager.registerIcon("toolSwordFire", false);
            SCIconManager.registerIcon("toolSwordPower", false);
            SCIconManager.registerIcon("toolSwordMagic", false);
            SCIconManager.registerIcon("toolSwordAlchMetal", false);
            SCIconManager.registerIcon("toolPickAlchMetal", false);
            SCIconManager.registerIcon("toolPickOmni", false);
            SCIconManager.registerIcon("toolShovelAlchMetal", false);
            SCIconManager.registerIcon("toolAxeAlchMetal", false);
            SCIconManager.registerIcon("toolHoeAlchMetal", false);
        }
    }
}
