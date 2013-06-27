package jcm2606.mods.sorcerycraft.config;

import java.io.File;

import jcm2606.mods.jccore.compat.ModCompatibility;
import jcm2606.mods.sorcerycraft.SCAchievements;
import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.SorceryCraft;
import jcm2606.mods.sorcerycraft.lib.Reference;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.Mod;

public class Config {
	public static Configuration config;
	public static String CATEGORY_TOOLS = "tool";
	public static String CATEGORY_ACHIEVEMENTS = "achievement";
	public static String CATEGORY_KEYBINDS = "keybind";
	public static String CATEGORY_WORLD = "world";
	public static String CATEGORY_GRAPHICS = "graphics";
	public static String CATEGORY_AUDIO = "audio";
	
	public static String CATEGORY_BLOCK_ID = config.CATEGORY_BLOCK + config.CATEGORY_SPLITTER + "id";
	public static String CATEGORY_ITEM_ID = config.CATEGORY_ITEM + config.CATEGORY_SPLITTER + "id";
	public static String CATEGORY_TOOL_ID = CATEGORY_TOOLS + config.CATEGORY_SPLITTER + "id";
	public static String CATEGORY_WORLD_BIOME = CATEGORY_WORLD + config.CATEGORY_SPLITTER + "biome";
	public static String CATEGORY_WORLD_BIOME_ID = CATEGORY_WORLD_BIOME + config.CATEGORY_SPLITTER + "id";
	public static String CATEGORY_TOOL_ENCHANTMENT_ID = CATEGORY_TOOLS + config.CATEGORY_SPLITTER + "id";
	public static String CATEGORY_ITEM_HERBLORE = config.CATEGORY_ITEM + config.CATEGORY_SPLITTER + "herblore";
	public static String CATEGORY_ITEM_HERBLORE_ID = CATEGORY_ITEM_HERBLORE + "id";
	
	public static void init(File file) {
		config = new Configuration(file);

		try {
			config.load();
			
			ModCompatibility.get().startObjectIdLoadingInClass(SCObjects.class);
			Settings.loadSettings();
			SCAchievements.loadAchievementIDs();
		} catch (Exception e) {
			SorceryCraft.logger.severe(SorceryCraft.class.getAnnotation(Mod.class).modid()
					+ " had a problem loading is configuration!");
		} finally {
			config.save();
		}
	}
	
	public static Property getToolId(String key, int value)
	{
	    Reference.ITEM_TOOL_ID_START_VALUE = value + 1;
	    
		return config.get(CATEGORY_TOOL_ID, key+".id", value);
	}
	
	public static Property getItemId(String key, int value)
	{
	    Reference.ITEM_ID_START_VALUE = value + 1;
	    
		return config.get(CATEGORY_ITEM_ID, key+".id", value);
	}
	
	public static Property getItemWandId(String key, int value)
    {
        Reference.ITEM_WAND_ID_START_VALUE = value + 1;
        
        return config.get(CATEGORY_ITEM_ID, key+".id", value);
    }
	
	public static Property getItemHerbloreId(String key, int value)
	{
		return config.get(CATEGORY_ITEM_HERBLORE_ID, key+".id", value);
	}
	
	public static Property getBlockId(String key, int value)
	{
        Reference.BLOCK_ID_START_VALUE = value + 1;
        
		return config.get(CATEGORY_BLOCK_ID, key+".id", value);
	}
	
	public static Property getAchievementId(String key, int value)
	{
		return config.get(CATEGORY_ACHIEVEMENTS, "achievement."+key+".id", value);
	}
	
	public static Property getBiomeId(String key, int value)
	{
		return config.get(CATEGORY_WORLD_BIOME_ID, key, value);
	}
	
	public static Property getEnchantmentId(String key, int value)
	{
		return config.get(CATEGORY_TOOL_ENCHANTMENT_ID, key, value);
	}
}
