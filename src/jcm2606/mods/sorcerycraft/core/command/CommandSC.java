package jcm2606.mods.sorcerycraft.core.command;

import java.util.List;

import jcm2606.mods.sorcerycraft.core.lib.Commands;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

public class CommandSC extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "sc";
    }
    
    @Override
    public void processCommand(ICommandSender icommandsender, String[] args)
    {
        if (args.length > 0)
        {
            if (args[0].equalsIgnoreCase(Commands.COMMAND_VERSION))
            {
                CommandVersion.processCommand(icommandsender, args);
            } else
                if (args[0].equalsIgnoreCase(Commands.COMMAND_CHARM_CURSE))
                {
                    CommandCharmCurse.processCommand(icommandsender, args);
                } else
                    if (args[0].equalsIgnoreCase(Commands.COMMAND_CREDITS))
                    {
                        CommandCredits.processCommand(icommandsender, args);
                    } else
                    {
                        throw new WrongUsageException("Command entered is not valid.");
                    }
        } else
        {
            throw new WrongUsageException("This command has been used incorrectly. Commands have to be entered like: /sc <command> <command args>");
        }
    }
    
    @Override
    @SuppressWarnings("rawtypes")
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {
        switch (args.length)
        {
            case 1:
            {
                return getListOfStringsMatchingLastWord(args, new String[]
                { Commands.COMMAND_VERSION, Commands.COMMAND_CHARM_CURSE, Commands.COMMAND_CREDITS });
            }
            
            case 2:
            {
                if (args[0].equals(Commands.COMMAND_CHARM_CURSE))
                {
                    return getListOfStringsMatchingLastWord(args, new String[]
                    { Commands.COMMAND_CHARM_CURSE_SET, Commands.COMMAND_CHARM_CURSE_REMOVE, Commands.COMMAND_CHARM_CURSE_LIST });
                }
            }
            
            case 3:
            {
                return null;
            }
            
            default:
            {
                return null;
            }
        }
    }
    
    @Override
    public String getCommandUsage(ICommandSender icommandsender)
    {
        return null;
    }
}
