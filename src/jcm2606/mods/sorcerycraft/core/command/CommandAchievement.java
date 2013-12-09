package jcm2606.mods.sorcerycraft.core.command;

import jcm2606.mods.sorcerycraft.core.SCAchievements;
import jcm2606.mods.sorcerycraft.core.lib.Commands;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;

public class CommandAchievement
{
    public static void processCommand(ICommandSender commandSender, String[] args)
    {
        if (args[1].equalsIgnoreCase(Commands.COMMAND_ACHIEVEMENT_GIVE))
        {
            if (args[2].equalsIgnoreCase("all"))
            {
                for (Achievement ach : SCAchievements.map.values())
                {
                    ((EntityPlayer) commandSender).triggerAchievement(ach);
                }
            }
        }
    }
}
