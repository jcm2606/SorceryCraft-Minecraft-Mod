package jcm2606.mods.sorcerycraft.event;

import jcm2606.mods.sorcerycraft.SorceryCraft;
import jcm2606.mods.sorcerycraft.lib.Reference;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundHandler {
	public static String[] soundFiles = {
		"transmutation.ogg",
		"magic.ogg",
		"magic_fail.ogg",
		"wand_use.ogg",
		"whisper1.ogg",
		"whisper2.ogg",
		"whisper3.ogg"
	};
	
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		for (String soundFile : soundFiles) {
            // Try to add the custom sound file to the pool of sounds
            try {
                event.manager.soundPoolSounds.addSound("sorcerycraft/" + soundFile, SorceryCraft.instance.getClass().getResource(Reference.PATH_SOUNDS + soundFile));
            }
            // If we cannot add the custom sound file to the pool, log the exception
            catch (Exception e) {
                SorceryCraft.logger.warning("Failed loading sound file: " + soundFile);
            }
        }
	}
}
