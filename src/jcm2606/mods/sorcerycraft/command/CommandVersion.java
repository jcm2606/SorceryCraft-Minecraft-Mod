package jcm2606.mods.sorcerycraft.command;

import jcm2606.mods.sorcerycraft.SorceryCraft;
import net.minecraft.command.ICommandSender;

public class CommandVersion {
    public static void processCommand(ICommandSender commandSender, String[] args) {
        commandSender.sendChatToPlayer("SorceryCraft v" + SorceryCraft.version);
    }
}
