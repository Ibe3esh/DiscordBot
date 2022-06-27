package me.ibe3esh.commands;

import me.ibe3esh.utils.ExecuteArgs;
import me.ibe3esh.utils.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.List;

public class CmdAvatar implements ICommand {
    @Override
    public void execute(ExecuteArgs event) {
        String[] args = event.getMessage().getContentRaw().split("\\s");
        if (args[0].equalsIgnoreCase("+avatar")) {
            List<User> mentionedUser = event.getMessage().getMentionedUsers();
            if (mentionedUser.size() > 0) {
                User userTarget = mentionedUser.get(0);
                event.getGuild().retrieveMember(userTarget).queue(member -> {
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setTitle(userTarget.getName() + " | Avatar");
                    embedBuilder.setColor(Color.magenta);
                    embedBuilder.setImage(userTarget.getAvatarUrl() + "?size=2048");
                    event.getTextChannel().sendMessage(embedBuilder.build()).queue();
                    embedBuilder.clear();
                    event.getMessage().delete().queue();
                });
            }
        }
    }

    @Override
    public String getName() {
        return "avatar";
    }

    public String helpMessage() {
        return "This command is to show avatars.";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}