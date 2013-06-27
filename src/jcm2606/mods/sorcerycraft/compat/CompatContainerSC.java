package jcm2606.mods.sorcerycraft.compat;

import jcm2606.mods.jccore.compat.container.CompatibilityContainer;
import jcm2606.mods.sorcerycraft.SorceryCraft;
import jcm2606.mods.sorcerycraft.astral.AstralManager;

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
