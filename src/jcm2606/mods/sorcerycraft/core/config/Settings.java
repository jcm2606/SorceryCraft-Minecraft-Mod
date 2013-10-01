package jcm2606.mods.sorcerycraft.core.config;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class Settings
{
    public static boolean ENTITY_DETECTOR_PLAYER_DAMAGE;
    public static Property ENTITY_DETECTOR_PLAYER_DAMAGE_PROP;
    
    public static int ENTITY_DETECTOR_DAMAGE;
    public static Property ENTITY_DETECTOR_DAMAGE_PROP;
    
    public static boolean ENTITY_DETECTOR_MESSAGE_REVACTION_STONE;
    public static Property ENTITY_DETECTOR_MESSAGE_REVACTION_STONE_PROP;
    
    public static boolean EXTRA_OUTPUT;
    public static Property EXTRA_OUTPUT_PROP;
    
    public static boolean DOES_CREATIVE_TAB_GET_NEXT_FREE_ID;
    public static Property DOES_CREATIVE_TAB_GET_NEXT_FREE_ID_PROP;
    
    public static Property CREATIVE_TAB_ID_PROP;
    public static int CREATIVE_TAB_ID;
    
    public static boolean WORLD_LOAD_MESSAGE;
    public static Property WORLD_LOAD_MESSAGE_PROP;
    
    public static boolean RING_MAGMA_FANCY_PARTICLES;
    public static Property RING_MAGMA_FANCY_PARTICLES_PROP;
    
    public static double SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_RED;
    public static Property SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_RED_PROP;
    
    public static double SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_GREEN;
    public static Property SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_GREEN_PROP;
    
    public static double SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_BLUE;
    public static Property SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_BLUE_PROP;
    
    public static void loadSettings()
    {
        Configuration config = Config.config;
        
        EXTRA_OUTPUT_PROP = config.get(Configuration.CATEGORY_GENERAL, "output.extra", true);
        EXTRA_OUTPUT = EXTRA_OUTPUT_PROP.getBoolean(true);
        EXTRA_OUTPUT_PROP.comment = "Should the mod output more information to the console.";
        
        DOES_CREATIVE_TAB_GET_NEXT_FREE_ID_PROP = config.get(Configuration.CATEGORY_GENERAL, "creativeTab.autoIdSearch", true);
        DOES_CREATIVE_TAB_GET_NEXT_FREE_ID = DOES_CREATIVE_TAB_GET_NEXT_FREE_ID_PROP.getBoolean(true);
        DOES_CREATIVE_TAB_GET_NEXT_FREE_ID_PROP.comment = "Should the SorceryCraft creative tab attempt to get a free ID.";
        
        CREATIVE_TAB_ID_PROP = config.get(Configuration.CATEGORY_GENERAL, "creativeTab.id", 12);
        CREATIVE_TAB_ID = CREATIVE_TAB_ID_PROP.getInt(12);
        CREATIVE_TAB_ID_PROP.comment = "The ID the creative tab will use when automatic ID searching is disabled. WARNING: Any tab that was in this slot WILL be overwritten!!!";
        
        ENTITY_DETECTOR_DAMAGE_PROP = config.get(Configuration.CATEGORY_GENERAL, "entityDetector.damage", 4);
        ENTITY_DETECTOR_DAMAGE = ENTITY_DETECTOR_DAMAGE_PROP.getInt(4);
        ENTITY_DETECTOR_DAMAGE_PROP.comment = "The amount of damage (in half hearts) that the Mundane Entity Detector will deal to living entities walking over it.";
        
        ENTITY_DETECTOR_PLAYER_DAMAGE_PROP = config.get(Configuration.CATEGORY_GENERAL, "entityDetector.damagePlayer", true);
        ENTITY_DETECTOR_PLAYER_DAMAGE = ENTITY_DETECTOR_PLAYER_DAMAGE_PROP.getBoolean(true);
        ENTITY_DETECTOR_PLAYER_DAMAGE_PROP.comment = "Should players be damaged by the Mundane Entity Detector. If enabled, only players with a Revaction Stone in their hotbar will be invulnerable.";
        
        ENTITY_DETECTOR_MESSAGE_REVACTION_STONE_PROP = config.get(Configuration.CATEGORY_GENERAL, "entityDetector.revactionStoneInform", true);
        ENTITY_DETECTOR_MESSAGE_REVACTION_STONE = ENTITY_DETECTOR_MESSAGE_REVACTION_STONE_PROP.getBoolean(true);
        ENTITY_DETECTOR_MESSAGE_REVACTION_STONE_PROP.comment = "Should the Mundane Entity Detector send a message to the player walking over it if that have a Revaction Stone in their hotbar.";
        
        WORLD_LOAD_MESSAGE_PROP = config.get(Config.CATEGORY_WORLD, "output.onWorldLoad", true);
        WORLD_LOAD_MESSAGE = WORLD_LOAD_MESSAGE_PROP.getBoolean(true);
        WORLD_LOAD_MESSAGE_PROP.comment = "Should a message be sent to the client-side player when they load a world.";
        
        RING_MAGMA_FANCY_PARTICLES_PROP = config.get(Configuration.CATEGORY_ITEM, "ringMagma.fancy_particles", false);
        RING_MAGMA_FANCY_PARTICLES = RING_MAGMA_FANCY_PARTICLES_PROP.getBoolean(false);
        RING_MAGMA_FANCY_PARTICLES_PROP.comment = "Should the Ring of Eternal Magma use fancy particles regardless of graphics mode.";
        
        SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_RED_PROP = config.get(Config.CATEGORY_SPECIAL_USER,
                "astralEnergy.overlay.orb.depleted.colour.red", 0.5);
        SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_RED = SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_RED_PROP.getDouble(0.5);
        SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_RED_PROP.comment = "The red value for the colour used for depleted Astral energy orbs on the overlay for special users.";
        
        SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_GREEN_PROP = config.get(Config.CATEGORY_SPECIAL_USER,
                "astralEnergy.overlay.orb.depleted.colour.green", 0.5);
        SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_GREEN = SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_GREEN_PROP.getDouble(0.5);
        SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_GREEN_PROP.comment = "The green value for the colour used for depleted Astral energy orbs on the overlay for special users.";
        
        SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_BLUE_PROP = config.get(Config.CATEGORY_SPECIAL_USER,
                "astralEnergy.overlay.orb.depleted.colour.blue", 0.5);
        SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_BLUE = SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_BLUE_PROP.getDouble(0.5);
        SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_BLUE_PROP.comment = "The blue value for the colour used for depleted Astral energy orbs on the overlay for special users.";
    }
}
