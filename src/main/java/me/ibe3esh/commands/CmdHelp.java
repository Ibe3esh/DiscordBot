package me.ibe3esh.commands;

import me.ibe3esh.utils.ExecuteArgs;
import me.ibe3esh.utils.ICommand;


public class CmdHelp implements ICommand {

    @Override
    public void execute(ExecuteArgs event) {

    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String helpMessage() {
        return "Shows the list with commands in the bot\n" +
                "Usage: `!help [command]`";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
