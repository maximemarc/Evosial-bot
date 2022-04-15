package fr.evosial.utils.commandTools;

import org.javacord.api.event.message.MessageCreateEvent;

public interface CommandExecutor {
    void run(MessageCreateEvent event, Commands cmd, String[] args);
}
