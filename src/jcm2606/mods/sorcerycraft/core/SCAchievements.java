package jcm2606.mods.sorcerycraft.core;

import java.util.HashMap;
import java.util.Map;

import jcm2606.mods.sorcerycraft.core.config.Config;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class SCAchievements
{
    public static AchievementPage scAchPage;
    
    public static Map<String, Achievement> map = new HashMap();
    
    public static int START_ID;
    public static int ID;
    
    private static int initX = 0;
    private static int initY = -6;
    
    public static void loadAchievements()
    {
        ID = START_ID;
        
        scAchPage = new AchievementPage("SorceryCraft", map.values().toArray(new Achievement[map.size()]));
        AchievementPage.registerAchievementPage(scAchPage);
    }
    
    private static Achievement registerAchievement(String name, int x, int y, ItemStack icon, String requirement, boolean special)
    {
        Achievement ach = new Achievement(ID, name, initX + x, initY + y, icon, map.get(requirement));
        
        if (special)
        {
            ach.setSpecial();
        }
        
        ach.registerAchievement();
        
        map.put(name, ach);
        
        ID++;
        
        return ach;
    }
    
    private static Achievement registerAchievement(String name, int x, int y, Item icon, String requirement, boolean special)
    {
        return registerAchievement(name, x, y, new ItemStack(icon), requirement, special);
    }
    
    public static void loadAchievementIDs()
    {
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
    private static void addAchievementName(String ach, String name)
    {
        LanguageRegistry.instance().addStringLocalization("achievement." + ach, "en_US", name);
    }
    
    /**
     * Add's a localization for the description of the given achievement.
     * 
     * @param ach
     * @param name
     */
    private static void addAchievementDesc(String ach, String desc)
    {
        LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
    }
}
