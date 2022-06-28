package me.ibe3esh.api;

import me.ibe3esh.utils.ICommand;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private final List<ICommand> commands = new ArrayList<>();

    public List<ICommand> getCommands() {
        return commands;
    }

    @Nullable
    public ICommand getCommand(String search) {
        String searchLower = search.toLowerCase();

        for (ICommand cmd : this.commands) {
            if (cmd.getName().equals(searchLower) || cmd.getName().contains(searchLower)) {
                return cmd;
            }
        }

        return null;
    }
}