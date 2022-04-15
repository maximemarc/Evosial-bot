package fr.evosial.utils.commandTools;


import fr.evosial.commands.CommandClear;
import fr.evosial.commands.CommandPing;
import fr.evosial.utils.ConfigManager;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.IOException;
import java.util.Arrays;

public class MessageManager
    {

    private static final CommandRegistry registry = new CommandRegistry();

    static
        {
        registry.addCommand(new Commands(
                "ping",
                "ping the bot",
                new CommandPing(),
                "ping","p?"
                ));
        registry.addCommand(new Commands(
                "clear",
                "clear message",
                new CommandClear(),
                "clear","cl"
            ));
        }

    public static void create(MessageCreateEvent event)
        {
        String p = null;
        ConfigManager configManager = null;
        try
            {
            configManager = new ConfigManager("config.json");
            p = String.valueOf(configManager.map().get("prefix"));
            }
        catch (IOException ignored)
            {
        System.out.println("une erreur est surevenu");
            }

        if(p == null)
            {
            System.out.println("valeur mise par default : !");
            try
                {
                configManager.addEntryValue("prefix","!");
                }
            catch (IOException ignored)
                {
                System.out.println("une erreur est surevenu");
                }
            }
        else
            {
            if (p.equals(" ") || p.equals(""))
                {
                try
                    {
                    configManager.setValue("prefix","!");
                    }
                catch (IOException ignored)
                    {
                    System.out.println("une erreur est surevenu");
                    }
                }
            }

        if (p != null && event.getMessageContent().startsWith(p))
            {
            String[] args = event.getMessageContent().split(" ");
            String commandName = args[0].substring(p.length());
            args = args.length == 1 ? new String[0] : Arrays.copyOfRange(args, 1, args.length);

            String[] finalArgs = args;
            registry.getByAlias(commandName).ifPresent((cmd) ->
                    cmd.getCommandExecutor().run(event, cmd, finalArgs));
            }
        }
    }
