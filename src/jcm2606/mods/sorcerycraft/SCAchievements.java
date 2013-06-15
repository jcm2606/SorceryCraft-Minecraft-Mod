package jcm2606.mods.sorcerycraft;

import jcm2606.mods.sorcerycraft.config.Config;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class SCAchievements {
	public static AchievementPage scAchPage;

	public static Achievement modUseFirst;
	public static Achievement vordicDustGet;
	public static Achievement refVordicDustGet;
	public static Achievement wandGet;
	
	public static int START_ID;
	
	public static void loadAchievements() {
	    modUseFirst = new Achievement(START_ID, "modUseFirst", 0, 0, SCObjects.alchbook, null).registerAchievement();
	    vordicDustGet = new Achievement(START_ID + 1, "vordicDustGet", 0, 2, SCObjects.dustvordic, modUseFirst).registerAchievement();
	    refVordicDustGet = new Achievement(START_ID + 2, "refVordicDustGet", 1, 3, SCObjects.dustvordicrefined, vordicDustGet).registerAchievement();
	    wandGet = new Achievement(START_ID + 3, "wandGet", 4, 3, SCObjects.wandcasting, refVordicDustGet).registerAchievement();
	    
	    /*
	     * Ach' Information Setting
	     */
	    addAchInfo("modUseFirst", "Into darkness", "Install and play SorceryCraft for the first time.");
	    addAchInfo("vordicDustGet", "New resources", "Aquire some Vordic Dust.");
	    addAchInfo("refVordicDustGet", "Newly refined resources", "Refine some Vordic Dust in a smelting device.");
	    addAchInfo("wandGet", "Sorcerer's pickaxe", "Aquire a Wand of Mundane Casting.");
	    
		/*
		 * Ach' Page
		 */
	    
	    Achievement[] achList = new Achievement[] {
	            modUseFirst, vordicDustGet, refVordicDustGet, wandGet
	    };
	    
		scAchPage = new AchievementPage("SorceryCraft", achList);
		AchievementPage.registerAchievementPage(scAchPage);
	}

	public static void loadAchievementIDs() {
		START_ID = Config.getAchievementId("achievementIDStartValue", 1000).getInt();
	}
	
	private static void addAchInfo(String ach, String name, String desc)
	{
	    addAchievementName(ach, name);
	    addAchievementDesc(ach, desc);
	}

	/**
	 * Add's a localization for the name of the given achievement.
	 * 
	 * @param ach
	 * @param name
	 */
	private static void addAchievementName(String ach, String name) {
		LanguageRegistry.instance().addStringLocalization("achievement." + ach,
				"en_US", name);
	}

	/**
	 * Add's a localization for the description of the given achievement.
	 * 
	 * @param ach
	 * @param name
	 */
	private static void addAchievementDesc(String ach, String desc) {
		LanguageRegistry.instance().addStringLocalization(
				"achievement." + ach + ".desc", "en_US", desc);
	}
}
