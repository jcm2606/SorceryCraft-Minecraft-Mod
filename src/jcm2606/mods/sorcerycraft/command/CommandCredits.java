package jcm2606.mods.sorcerycraft.command;

import jcm2606.mods.jccore.util.ChatUtil;
import net.minecraft.command.ICommandSender;

public class CommandCredits {
    public static void processCommand(ICommandSender commandSender, String[] args) {
        ChatUtil.sendTextToCommandSender(commandSender, "Creator: Jcm2606");
        ChatUtil.sendTextToCommandSender(commandSender, "Authors: Jcm2606, Asyncronous");
        ChatUtil.sendTextToCommandSender(commandSender, "Textures: Jcm2606, Saukawolf, VydaX, Ajo196");
    }
}
