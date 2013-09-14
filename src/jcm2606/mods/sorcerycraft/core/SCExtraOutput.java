package jcm2606.mods.sorcerycraft.core;

import jcm2606.mods.sorcerycraft.core.config.Settings;

public class SCExtraOutput
{
    public static void output(String message)
    {
        if (Settings.EXTRA_OUTPUT)
        {
            SorceryCraft.logger.info(message);
        }
    }
}
