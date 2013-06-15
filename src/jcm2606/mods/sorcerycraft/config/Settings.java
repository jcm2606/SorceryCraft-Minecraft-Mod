package jcm2606.mods.sorcerycraft.config;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class Settings {
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

	public static int HYPERBOREAN_STONE_HEALTH_LIMIT;
	public static Property HYPERBOREAN_STONE_HEALTH_LIMIT_PROP;
	
	public static boolean WORLD_LOAD_MESSAGE;
	public static Property WORLD_LOAD_MESSAGE_PROP;
	
	public static int ALCH_STONE_MAX_DURABILITY;
	public static Property ALCH_STONE_MAX_DURABILITY_PROP;
	
	public static boolean WAND_CASTING_BEHAVIOUR_SWITCH_MESSAGE;
	public static Property WAND_CASTING_BEHAVIOUR_SWITCH_MESSAGE_PROP;
	
	public static int WAND_CASTING_BEHAVIOUR_ICON_ALIGNMENT;
	public static Property WAND_CASTING_BEHAVIOUR_ICON_ALIGNMENT_PROP;
	
	public static boolean RING_MAGMA_FANCY_PARTICLES;
	public static Property RING_MAGMA_FANCY_PARTICLES_PROP;

	public static void loadSettings() {
		Configuration config = Config.config;

		EXTRA_OUTPUT_PROP = config.get(config.CATEGORY_GENERAL,"output.extra", true);
		EXTRA_OUTPUT = EXTRA_OUTPUT_PROP.getBoolean(true);
		EXTRA_OUTPUT_PROP.comment = "Should the mod output more information to the console.";

		DOES_CREATIVE_TAB_GET_NEXT_FREE_ID_PROP = config.get(config.CATEGORY_GENERAL, "creativeTab.autoIdSearch",true);
		DOES_CREATIVE_TAB_GET_NEXT_FREE_ID = DOES_CREATIVE_TAB_GET_NEXT_FREE_ID_PROP.getBoolean(true);
		DOES_CREATIVE_TAB_GET_NEXT_FREE_ID_PROP.comment = "Should the SorceryCraft creative tab attempt to get a free ID.";

		CREATIVE_TAB_ID_PROP = config.get(config.CATEGORY_GENERAL,"creativeTab.id", 12);
		CREATIVE_TAB_ID = CREATIVE_TAB_ID_PROP.getInt(12);
		CREATIVE_TAB_ID_PROP.comment = "The ID the creative tab will use when automatic ID searching is disabled. SorceryCraft will use this ID, and the next three. WARNING: Any tab that was in this slot WILL be overwritten!!!";

		HYPERBOREAN_STONE_HEALTH_LIMIT_PROP = config.get(config.CATEGORY_GENERAL, "stoneHyperborean.healthLimit", 1);
		HYPERBOREAN_STONE_HEALTH_LIMIT = HYPERBOREAN_STONE_HEALTH_LIMIT_PROP.getInt(1);
		HYPERBOREAN_STONE_HEALTH_LIMIT_PROP.comment = "The amount of health (in half hearts) that the Hyperborean Stone will start to take damage for you.";
		
		ENTITY_DETECTOR_DAMAGE_PROP = config.get(config.CATEGORY_GENERAL, "entityDetector.damage", 4);
		ENTITY_DETECTOR_DAMAGE = ENTITY_DETECTOR_DAMAGE_PROP.getInt(4);
		ENTITY_DETECTOR_DAMAGE_PROP.comment = "The amount of damage (in half hearts) that the Mundane Entity Detector will deal to living entities walking over it.";
		
		ENTITY_DETECTOR_PLAYER_DAMAGE_PROP = config.get(config.CATEGORY_GENERAL, "entityDetector.damagePlayer", true);
		ENTITY_DETECTOR_PLAYER_DAMAGE = ENTITY_DETECTOR_PLAYER_DAMAGE_PROP.getBoolean(true);
		ENTITY_DETECTOR_PLAYER_DAMAGE_PROP.comment = "Should players be damaged by the Mundane Entity Detector. If enabled, only players with a Revaction Stone in their hotbar will be invulnerable.";
		
		ENTITY_DETECTOR_MESSAGE_REVACTION_STONE_PROP = config.get(config.CATEGORY_GENERAL, "entityDetector.revactionStoneInform", true);
		ENTITY_DETECTOR_MESSAGE_REVACTION_STONE = ENTITY_DETECTOR_MESSAGE_REVACTION_STONE_PROP.getBoolean(true);
		ENTITY_DETECTOR_MESSAGE_REVACTION_STONE_PROP.comment = "Should the Mundane Entity Detector send a message to the player walking over it if that have a Revaction Stone in their hotbar.";
		
		WORLD_LOAD_MESSAGE_PROP = config.get(Config.CATEGORY_WORLD, "output.onWorldLoad", true);
		WORLD_LOAD_MESSAGE = WORLD_LOAD_MESSAGE_PROP.getBoolean(true);
		WORLD_LOAD_MESSAGE_PROP.comment = "Should a message be sent to the client-side player when they load a world.";
	
		WAND_CASTING_BEHAVIOUR_SWITCH_MESSAGE_PROP = config.get(config.CATEGORY_ITEM, "wandCasting.behaviour_onSwitch_message", false);
		WAND_CASTING_BEHAVIOUR_SWITCH_MESSAGE = WAND_CASTING_BEHAVIOUR_SWITCH_MESSAGE_PROP.getBoolean(false);
		WAND_CASTING_BEHAVIOUR_SWITCH_MESSAGE_PROP.comment = "Should the Wand of Mundane Casting send a client side message when you switch behaviours. Fair warning, this can spam your chat rather quickly.";
		
		ALCH_STONE_MAX_DURABILITY_PROP = config.get(config.CATEGORY_ITEM, "alchStone.durability.max", 128);
		ALCH_STONE_MAX_DURABILITY = ALCH_STONE_MAX_DURABILITY_PROP.getInt(128);
		ALCH_STONE_MAX_DURABILITY_PROP.comment = "The maximum durability for the Alchemical Stone. Defaults to 128.";
	
		WAND_CASTING_BEHAVIOUR_ICON_ALIGNMENT_PROP = config.get(config.CATEGORY_ITEM, "wandCasting.behvaiour_icon_alignmentMode", 0);
		WAND_CASTING_BEHAVIOUR_ICON_ALIGNMENT = WAND_CASTING_BEHAVIOUR_ICON_ALIGNMENT_PROP.getInt(0);
		WAND_CASTING_BEHAVIOUR_ICON_ALIGNMENT_PROP.comment = "The mode that the behaviour icon for the Wand of Mundane Casting uses. 0: Auto-align, 1: Left, 2: Centre, 3: right";
	
		RING_MAGMA_FANCY_PARTICLES_PROP = config.get(config.CATEGORY_ITEM, "ringMagma.fancy_particles", false);
		RING_MAGMA_FANCY_PARTICLES = RING_MAGMA_FANCY_PARTICLES_PROP.getBoolean(false);
		RING_MAGMA_FANCY_PARTICLES_PROP.comment = "Should the Ring of Eternal Magma use fancy particles regardless of graphics mode.";
	}
}
