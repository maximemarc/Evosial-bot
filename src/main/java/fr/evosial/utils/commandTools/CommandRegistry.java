package fr.evosial.utils.commandTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class CommandRegistry
    {
    private final ArrayList<Commands> commands;

    public CommandRegistry()
        {
        this.commands = new ArrayList<>();
        }

    public void addCommand(Commands cmd)
        {
        commands.add(cmd);
        }

    public Optional<Commands> getByAlias(String alias)
        {
        for (Commands command:commands)
            {
            if (Arrays.asList(command.getAlias()).contains(alias))
                {
                return Optional.of(command);
                }
            }
        return Optional.empty();
        }
    }
