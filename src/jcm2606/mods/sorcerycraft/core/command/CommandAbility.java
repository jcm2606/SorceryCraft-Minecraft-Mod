package jcm2606.mods.sorcerycraft.core.command;

import jcm2606.mods.sorcerycraft.api.IAbility;
import jcm2606.mods.sorcerycraft.astral.ability.AstralAbilityBase;
import jcm2606.mods.sorcerycraft.core.lib.Commands;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;

public class CommandAbility
{
    public static void processCommand(ICommandSender commandSender, String[] args)
    {
        if (args[1].equalsIgnoreCase(Commands.COMMAND_ASTRAL_ABILITY_LEARN))
        {
            if (args[2].equalsIgnoreCase("all"))
            {
                AstralAbilityBase.unlockAllAbilities((EntityPlayer) commandSender);
                
                commandSender.sendChatToPlayer(ChatMessageComponent.createFromText("All astral abilities unlocked!"));
            } else
            {
                
                IAbility ability = AstralAbilityBase.getAbility(args[2]);
                
                if (ability == null)
                {
                    commandSender.sendChatToPlayer(ChatMessageComponent.createFromText("\247cError: Ability is invalid!"));
                } else
                {
                    AstralAbilityBase.learnAbility((EntityPlayer) commandSender, ability);
                    
                    commandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Unlocked astral ability '" + ability.getName() + "'."));
                }
            }
        }
        
        if (args[1].equalsIgnoreCase(Commands.COMMAND_ASTRAL_ABILITY_FORGET))
        {
            if (args[2].equalsIgnoreCase("all"))
            {
                AstralAbilityBase.forgetAllAbilities((EntityPlayer) commandSender);
                
                commandSender.sendChatToPlayer(ChatMessageComponent.createFromText("All astral abilities forgotten!"));
            } else
            {
                String name = args[2].replace("_", " ");
                
                IAbility ability = AstralAbilityBase.getAbility(name);
                
                if (ability == null)
                {
                    commandSender.sendChatToPlayer(ChatMessageComponent.createFromText("\247cError: Ability is invalid!"));
                } else
                {
                    AstralAbilityBase.forgetAbility((EntityPlayer) commandSender, ability);
                    
                    commandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Forgot astral ability '" + ability.getName() + "'."));
                }
            }
        }
    }
}
