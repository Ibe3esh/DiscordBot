package me.ibe3esh.commands;

import me.ibe3esh.api.CommandManager;
import me.ibe3esh.utils.ExecuteArgs;
import me.ibe3esh.utils.ICommand;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Arrays;
import java.util.List;

public class CmdHelp implements ICommand {

    public static CommandManager manager;

    @Override
    public void execute(ExecuteArgs event) {
        List<String> args = Arrays.asList(event.getArgs());
        TextChannel channel = event.getTextChannel();

        if (args.isEmpty()) {
            StringBuffer builder = new StringBuffer();

            builder.append("List of commands\n");

            manager.getCommands().stream().map(ICommand::getName).forEach(
                    (it) -> builder.append("`").append("!").append(it).append("`\n")
            );

            channel.sendMessage(builder.toString()).queue();
            return;
        }

        String search = args.get(0);
        ICommand command = manager.getCommand(search);

        if (command == null) {
            channel.sendMessage("Nothing found for " + search).queue();
            return;
        }

        channel.sendMessage(command.helpMessage()).queue();
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
