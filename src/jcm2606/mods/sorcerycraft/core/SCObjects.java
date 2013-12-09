package jcm2606.mods.sorcerycraft.core;

import jcm2606.mods.jccore.core.IObjectCore;
import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralPillarBase;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralPillarCap;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralPillarStructure;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralSteel;
import jcm2606.mods.sorcerycraft.block.astral.BlockAstralStructure;
import jcm2606.mods.sorcerycraft.block.astral.BlockOreAstral;
import jcm2606.mods.sorcerycraft.block.main.BlockGlowBrick;
import jcm2606.mods.sorcerycraft.block.main.BlockGlowBrickItem;
import jcm2606.mods.sorcerycraft.block.psyaic.BlockCreativeGenerator;
import jcm2606.mods.sorcerycraft.block.psyaic.BlockPsyaicNodeItem;
import jcm2606.mods.sorcerycraft.block.psyaic.BlockPsyonicCapacitorCore;
import jcm2606.mods.sorcerycraft.block.psyaic.BlockPsyonicCapacitorCoreItem;
import jcm2606.mods.sorcerycraft.block.psyaic.BlockPsyonicCapacitorHousing;
import jcm2606.mods.sorcerycraft.block.psyaic.BlockPsyonicCapacitorIOInterface;
import jcm2606.mods.sorcerycraft.block.psyaic.BlockPsyonicNode;
import jcm2606.mods.sorcerycraft.block.psyonic.BlockChannelingTablet;
import jcm2606.mods.sorcerycraft.block.psyonic.BlockOrePsyonic;
import jcm2606.mods.sorcerycraft.block.psyonic.BlockPsyonicConduit;
import jcm2606.mods.sorcerycraft.core.config.Config;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralCrystal;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralLinkingCard;
import jcm2606.mods.sorcerycraft.item.astral.ItemPsyonicEnergyCell;
import jcm2606.mods.sorcerycraft.item.psyonic.ItemPsyonicCrystal;
import jcm2606.mods.sorcerycraft.item.staff.ItemChannelingStaff;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class SCObjects implements IObjectCore
{
    // Items
    public static Item astralCrystal;
    public static Item psyonicCrystal;
    public static Item channelingStaff;
    public static Item psyonicCellEnergy;
    public static Item astralLinkingCard;
    
    // Blocks
    public static Block oreAstral;
    public static Block orePsyonic;
    public static Block channelingTablet;
    public static Block psyonicNode;
    public static Block psyonicCapacitorCore;
    public static Block psyonicCapacitorIOInterface;
    public static Block psyonicCapacitorHousing;
    public static Block astralPillarCap;
    public static Block astralPillarBase;
    public static Block astralPillarStructure;
    public static Block glowBrick1;
    public static Block glowBrick2;
    public static Block blockAstralSteel;
    public static Block astralStructure;
    public static Block creativeGenerator;
    public static Block psyonicConduit;
    
    @Override
    public void loadObjects()
    {
        // Items
        astralCrystal = new ItemAstralCrystal(Config.getItemId("astralCrystal").getInt());
        psyonicCrystal = new ItemPsyonicCrystal(Config.getItemId("psyonicCrystal").getInt());
        channelingStaff = new ItemChannelingStaff(Config.getItemId("channelingStaff").getInt());
        psyonicCellEnergy = new ItemPsyonicEnergyCell(Config.getItemId("psyonicEnergyCell").getInt());
        astralLinkingCard = new ItemAstralLinkingCard(Config.getItemId("astralLinkingCard").getInt());
        
        // Blocks
        oreAstral = new BlockOreAstral(Config.getBlockId("oreAstral").getInt());
        orePsyonic = new BlockOrePsyonic(Config.getBlockId("orePsyonic").getInt());
        channelingTablet = new BlockChannelingTablet(Config.getBlockId("channelingTablet").getInt());
        psyonicNode = new BlockPsyonicNode(Config.getBlockId("psyonicNode").getInt());
        psyonicCapacitorCore = new BlockPsyonicCapacitorCore(Config.getBlockId("psyonicCapacitorCore").getInt());        
        psyonicCapacitorIOInterface = new BlockPsyonicCapacitorIOInterface(Config.getBlockId("psyonicCapacitorIOInterface").getInt());        
        psyonicCapacitorHousing = new BlockPsyonicCapacitorHousing(Config.getBlockId("psyonicCapacitorHousing").getInt());
        glowBrick1 = new BlockGlowBrick(Config.getBlockId("glowBrickDesign1").getInt(), "glowBrick1", "glowBrick1");
        glowBrick2 = new BlockGlowBrick(Config.getBlockId("glowBrickDesign2").getInt(), "glowBrick2", "glowBrick2");
        blockAstralSteel = new BlockAstralSteel(Config.getBlockId("blockAstralSteel").getInt());
        astralStructure = new BlockAstralStructure(Config.getBlockId("astralStructure").getInt());
        astralPillarBase = new BlockAstralPillarBase(Config.getBlockId("astralPillarBase").getInt());
        astralPillarStructure = new BlockAstralPillarStructure(Config.getBlockId("astralPillarStructure").getInt());
        astralPillarCap = new BlockAstralPillarCap(Config.getBlockId("astralPillarCap").getInt());
        creativeGenerator = new BlockCreativeGenerator(Config.getBlockId("creativeGenerator").getInt());
        psyonicConduit = new BlockPsyonicConduit(Config.getBlockId("psyonicConduit").getInt());
        
        this.registerBlocks();
        this.loadBiomes();
        this.loadEnchantments();
        this.addNames();
        this.addRecipes();
        this.addSmeltingRecipes();
        this.addBlockHarvestLevels();
    }
    
    @Override
    public void registerBlocks()
    {
        GeneralUtil.registerBlock(glowBrick1, BlockGlowBrickItem.class);
        GeneralUtil.registerBlock(glowBrick2, BlockGlowBrickItem.class);
        GeneralUtil.registerBlock(psyonicCapacitorCore, BlockPsyonicCapacitorCoreItem.class);
        GeneralUtil.registerBlock(astralPillarBase);
        GeneralUtil.registerBlock(astralPillarCap);
        GeneralUtil.registerBlock(astralPillarStructure);
        GeneralUtil.registerBlock(oreAstral);
        GeneralUtil.registerBlock(orePsyonic);
        GeneralUtil.registerBlock(channelingTablet);
        GeneralUtil.registerBlock(psyonicNode, BlockPsyaicNodeItem.class);
        GeneralUtil.registerBlock(psyonicCapacitorHousing);
        GeneralUtil.registerBlock(psyonicCapacitorIOInterface);
        GeneralUtil.registerBlock(blockAstralSteel);
        GeneralUtil.registerBlock(astralStructure);
        GeneralUtil.registerBlock(creativeGenerator);
        GeneralUtil.registerBlock(psyonicConduit);
    }
    
    @Override
    public void loadIDs()
    {
        // Not used
    }
    
    @Override
    public void loadEnchantments()
    {
        
    }
    
    @Override
    public void addBlockHarvestLevels()
    {
        // Not used
    }
    
    @Override
    public void addNames()
    {
        // Items
        LanguageRegistry.addName(astralCrystal, "Astral Crystal");
        LanguageRegistry.addName(channelingStaff, "Channeling Staff");
        LanguageRegistry.addName(psyonicCellEnergy, "Psyonic Energy Cell");
        LanguageRegistry.addName(psyonicCrystal, "Psyonic Crystal");
        LanguageRegistry.addName(astralLinkingCard, "Astral Linking Card");
        
        // Blocks
        LanguageRegistry.addName(astralPillarBase, "Astral Pillar Base");
        LanguageRegistry.addName(astralPillarCap, "Astral Pillar Cap");
        LanguageRegistry.addName(astralPillarStructure, "Astral Pillar Structure");
        LanguageRegistry.addName(channelingTablet, "Channeling Tablet");
        LanguageRegistry.addName(glowBrick1, "Glowing Brick");
        LanguageRegistry.addName(glowBrick2, "Glowing Brick");
        LanguageRegistry.addName(oreAstral, "Astral Crystal Ore");
        LanguageRegistry.addName(orePsyonic, "Psyonic Crystal Ore");
        LanguageRegistry.addName(psyonicCapacitorCore, "Psyonic Capacitor Core");
        LanguageRegistry.addName(psyonicCapacitorHousing, "Psyonic Capacitor Housing");
        LanguageRegistry.addName(psyonicCapacitorIOInterface, "Psyonic Capacitor I/O Interface");
        LanguageRegistry.addName(psyonicNode, "Psyonic Energy Node");
        LanguageRegistry.addName(astralStructure, "Astral Structure");
        LanguageRegistry.addName(blockAstralSteel, "Astral Steel Block");
        LanguageRegistry.addName(psyonicConduit, "Psyonic Conduit");
    }
    
    @Override
    public void addRecipes()
    {
        
    }
    
    @Override
    public void addSmeltingRecipes()
    {
        
    }
    
    @Override
    public void loadBiomes()
    {
        
    }
}
