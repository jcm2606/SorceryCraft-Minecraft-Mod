package jcm2606.mods.sorcerycraft.api.research;

import java.util.HashMap;
import java.util.Map;

public class ResearchManager
{
    private static Map<String, ResearchBase> researchMap = new HashMap<String, ResearchBase>();
    
    public static void registerResearch(ResearchBase research)
    {
        researchMap.put(research.getName(), research);
    }
    
    public static ResearchBase getResearch(String name)
    {
        return researchMap.get(name);
    }
}
