package jcm2606.mods.sorcerycraft.api.compat;

import jcm2606.mods.jccore.compat.container.CompatibilityContainer;
import jcm2606.mods.sorcerycraft.api.AstralManager;
import jcm2606.mods.sorcerycraft.core.SorceryCraft;

public class CompatContainerSC extends CompatibilityContainer {
    private static final AstralManager astralManager = new AstralManager();
    
    @Override
    public Object getModClass()
    {
        return SorceryCraft.instance;
    }
    
    public static AstralManager getAstralManager()
    {
        return astralManager;
    }

    @Override
    public String getContainerName()
    {
        return "Mod_SC";
    }
}
