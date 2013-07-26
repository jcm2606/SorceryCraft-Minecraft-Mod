package jcm2606.mods.sorcerycraft.command;

import java.util.Set;

import jcm2606.mods.jccore.util.ChatUtil;
import jcm2606.mods.jccore.util.ServerUtil;
import jcm2606.mods.sorcerycraft.core.lib.Commands;
import jcm2606.mods.sorcerycraft.item.charm.ItemCharm;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.item.ItemStack;

public class CommandCharmCurse {
    public static void processCommand(ICommandSender commandSender, String[] args)
    {
        if(args[1].equalsIgnoreCase(Commands.COMMAND_CHARM_CURSE_SET))
        {
            processCurseApply(commandSender, args);
        } else
            if(args[1].equalsIgnoreCase(Commands.COMMAND_CHARM_CURSE_REMOVE))
            {
                processCurseRemove(commandSender, args);
            } else
                if(args[1].equalsIgnoreCase(Commands.COMMAND_CHARM_CURSE_LIST))
                {
                    processCurseList(commandSender, args);
                } else {
                    throw new WrongUsageException("This command has been used incorrectly. This command has to be entered like: /sc curse <command> <command args>");
                }
    }
    
    private static void processCurseApply(ICommandSender commandSender, String[] args)
    {
        if(commandSender.getCommandSenderName().equals("Server"))
        {
            ChatUtil.sendTextToCommandSender(commandSender, "ERROR: This command cannot be performed via server console.");
            return;
        }
        
        if(ItemCharm.curseList.get(args[2]) != null)
        {
            ItemStack currentItem = ServerUtil.getPlayer(commandSender.getCommandSenderName()).getCurrentEquippedItem();
            
            if(currentItem != null)
            {
                if(currentItem.getItem() instanceof ItemCharm)
                {
                    ItemCharm charm = (ItemCharm) currentItem.getItem();
                    
                    if(charm.getCurseName(currentItem).equals(args[2]))
                    {
                        ChatUtil.sendTextToCommandSender(commandSender, "That charm already has the curse '" + args[2] + "'.");
                    } else {
                        charm.setCurseName(currentItem, args[2]);
                        charm.setCurseOwner(currentItem, commandSender.getCommandSenderName());
                        ChatUtil.sendTextToCommandSender(commandSender, "Charm has successfully been cursed with '" + args[2] + "'.");
                        System.out.println("CHARM @ SLOT " + ServerUtil.getPlayer(commandSender.getCommandSenderName()).inventory.currentItem + " ON PLAYER '" + commandSender.getCommandSenderName().toUpperCase() + "' HAD CURSE '" + args[2].toUpperCase() + "' APPLIED.");
                    }
                } else {
                    throw new WrongUsageException("You must be holding a charm to curse it.");
                }
            } else {
                throw new WrongUsageException("You must be holding a charm to curse it.");
            }
        } else {
            throw new WrongUsageException("Curse '" + args[2] + "' is not valid!");
        }
    }
    
    private static void processCurseRemove(ICommandSender commandSender, String[] args)
    {
        if(commandSender.getCommandSenderName().equals("Server"))
        {
            ChatUtil.sendTextToCommandSender(commandSender, "ERROR: This command cannot be performed via server console.");
            return;
        }
        
        ItemStack currentItem = ServerUtil.getPlayer(commandSender.getCommandSenderName()).getCurrentEquippedItem();
        
        if(currentItem != null)
        {
            if(currentItem.getItem() instanceof ItemCharm)
            {
                ItemCharm charm = (ItemCharm) currentItem.getItem();
                
                if(!charm.getCurseName(currentItem).equals(""))
                {
                    charm.setCurseName(currentItem, "");
                    charm.setCurseOwner(currentItem, "");
                    ChatUtil.sendTextToCommandSender(commandSender, "Charm has successfully been cleansed of all curses.");
                    System.out.println("CHARM @ SLOT " + ServerUtil.getPlayer(commandSender.getCommandSenderName()).inventory.currentItem + " ON PLAYER '" + commandSender.getCommandSenderName().toUpperCase() + "' WAS CLEANSED.");
                } else {
                    throw new WrongUsageException("Charm must be cursed to remove the curse.");
                }
            } else {
                throw new WrongUsageException("You must be holding a charm to remove the curse.");
            }
        } else {
            throw new WrongUsageException("You must be holding a charm to remove the curse.");
        }
    }
    
    private static void processCurseList(ICommandSender commandSender, String[] args)
    {
        Set<String> curseNameList = ItemCharm.curseList.keySet();
        String splitter = "";
        String message;
        
        message = curseNameList.toString().replace("[", "").replace("]", "");
        
        ChatUtil.sendTextToCommandSender(commandSender, "Curses currently available: " + message);
    }
}
