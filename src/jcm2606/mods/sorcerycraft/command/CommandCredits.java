package jcm2606.mods.sorcerycraft.command;

import net.minecraft.command.ICommandSender;

public class CommandCredits {
    public static void processCommand(ICommandSender commandSender, String[] args) {
        commandSender.sendChatToPlayer("Creator: Jcm2606");
        commandSender.sendChatToPlayer("Authors: Jcm2606, Asyncronous");
        commandSender.sendChatToPlayer("Textures: Jcm2606, Saukawolf, Vydax, Ajo196");
    }
}
