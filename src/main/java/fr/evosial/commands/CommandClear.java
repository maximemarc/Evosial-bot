package fr.evosial.commands;

import fr.evosial.utils.commandTools.CommandExecutor;
import fr.evosial.utils.commandTools.Commands;
import org.javacord.api.event.message.MessageCreateEvent;


public class CommandClear implements CommandExecutor
    {
    @Override
    public void run(MessageCreateEvent event, Commands cmd, String[] args)
        {
        event.getChannel().bulkDelete(100);
        event.getChannel().deleteMessages("!cl");
        event.getChannel().sendMessage("suppression de Message");
        }
    }
