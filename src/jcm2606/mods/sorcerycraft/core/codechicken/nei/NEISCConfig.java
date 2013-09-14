package jcm2606.mods.sorcerycraft.core.codechicken.nei;

import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEISCConfig implements IConfigureNEI
{
    @Override
    public void loadConfig()
    {
        SorceryCraft.logger.info("NEI Module for SorceryCraft loading");
        
        API.registerRecipeHandler(new ArcaneWorkbenchHandler.ShapedRecipe());
        API.registerUsageHandler(new ArcaneWorkbenchHandler.ShapedRecipe());
        API.registerRecipeHandler(new ArcaneWorkbenchHandler.ShapelessRecipe());
        API.registerUsageHandler(new ArcaneWorkbenchHandler.ShapelessRecipe());
        
        SorceryCraft.logger.info("NEI Module for SorceryCraft loaded");
    }
    
    @Override
    public String getName()
    {
        return "SorceryCraft";
    }
    
    @Override
    public String getVersion()
    {
        return SorceryCraft.version;
    }
}
