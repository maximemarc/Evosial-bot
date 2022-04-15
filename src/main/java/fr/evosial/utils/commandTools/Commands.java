package fr.evosial.utils.commandTools;

public class Commands {

    private final String id;
    private final String description;
    private final String[] alias;

    private final CommandExecutor commandExecutor;

    public Commands(String id, String description, CommandExecutor commandExecutor, String... alias) {
        this.id = id;
        this.description = description;
        this.alias = alias;
        this.commandExecutor = commandExecutor;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAlias() {
        return alias;
    }

    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }
}
