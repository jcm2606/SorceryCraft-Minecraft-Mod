package jcm2606.mods.sorcerycraft.core.config;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class Settings
{
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
    
    public static boolean LOW_FX;
    public static Property LOW_FX_PROP;
    
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
        
        LOW_FX_PROP = config.get(Config.CATEGORY_GRAPHICS, "fx.low", false);
        LOW_FX = LOW_FX_PROP.getBoolean(false);
        LOW_FX_PROP.comment = "Should SorceryCraft use low graphics settings?";
    }
}
