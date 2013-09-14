package jcm2606.mods.sorcerycraft.core.command;

import jcm2606.mods.jccore.util.ChatUtil;
import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import net.minecraft.command.ICommandSender;

public class CommandVersion
{
    public static void processCommand(ICommandSender commandSender, String[] args)
    {
        ChatUtil.sendTextToCommandSender(commandSender, "SorceryCraft v" + SorceryCraft.version);
    }
}
