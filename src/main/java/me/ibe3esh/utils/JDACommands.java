package me.ibe3esh.utils;

import java.util.ArrayList;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class JDACommands extends ListenerAdapter {
    private ArrayList<ICommand> commands;
    private final String prefix;

    public String getPrefix() {
        return this.prefix;
    }

    public JDACommands(final String prefix) {
        this.commands = new ArrayList<ICommand>();
        this.prefix = prefix;
    }

    public ArrayList<ICommand> getCommands() {
        return this.commands;
    }

    public void setCommands(final ArrayList<ICommand> commands) {
        this.commands = commands;
    }

    public void registerCommand(final ICommand command) {
        this.commands.add(command);
    }

    private void init(final MessageReceivedEvent event) {
        for (final ICommand command : this.commands) {
            if (event.getMessage().getContentRaw().split(" ")[0].equalsIgnoreCase(this.prefix + command.getName())) {
                if (!command.needOwner()) {
                    command.execute(new ExecuteArgs(event));
                    return;
                }
                if (event.getMember().isOwner()) {
                    command.execute(new ExecuteArgs(event));
                    return;
                }
                event.getChannel().sendMessage((CharSequence) "You don't have the required permissions to use this command.");
                return;
            }
        }
        event.getChannel().sendMessage((CharSequence) "This command wasn't recognized.").queue();
    }

    public void onMessageReceived(@NotNull final MessageReceivedEvent event) {
        if (event == null) {
            $$$reportNull$$$0(0);
        }
        if (event.getMessage().getContentRaw().startsWith(this.prefix)) {
            this.init(event);
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(final int n) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "event", "ca/tristan/jdacommands/JDACommands", "onMessageReceived"));
    }
}