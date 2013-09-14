package jcm2606.mods.sorcerycraft.core.handler;

import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundHandler
{
    public static String[] soundFiles =
    { "transmutation", "magic", "magic_fail", "wand_use", "whisper1", "whisper2", "whisper3" };
    
    @ForgeSubscribe
    public void onSound(SoundLoadEvent event)
    {
        for (String soundFile : soundFiles)
        {
            try
            {
                event.manager.soundPoolSounds.addSound(getSound(soundFile));
            }
            catch (Exception e)
            {
                SorceryCraft.logger.warning("Failed loading sound file: " + soundFile);
            }
        }
    }
    
    public static String getSound(String sound)
    {
        return "sorcerycraft:" + sound + ".ogg";
    }
}
