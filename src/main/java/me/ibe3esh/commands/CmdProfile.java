package me.ibe3esh.commands;

import me.ibe3esh.utils.ExecuteArgs;
import me.ibe3esh.utils.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CmdProfile implements ICommand {

    @Override
    public void execute(ExecuteArgs event) {
        String[] args = event.getMessage().getContentRaw().split("\\s");
        if (args[0].equalsIgnoreCase("+profile")) {
            List<User> mentionedUser = event.getMessage().getMentionedUsers();
            if (mentionedUser.size() > 0) {
                User userTarget = mentionedUser.get(0);
                event.getGuild().retrieveMember(userTarget).queue(member -> {
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setTitle(userTarget.getName() + " | Profile");
                    embedBuilder.addField("Name", userTarget.getAsTag(), false);
                    embedBuilder.addField("ID", userTarget.getId(), false);
                    embedBuilder.addField("Account Created", userTarget.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME), false);
                    embedBuilder.addField("Guild Joined", member.getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME), false);
                    embedBuilder.setColor(Color.pink);
                    embedBuilder.setThumbnail(userTarget.getAvatarUrl());
                    event.getTextChannel().sendMessage(embedBuilder.build()).queue();
                    embedBuilder.clear();
                    event.getMessage().delete().queue();
                });
            }
        }
    }

    @Override
    public String getName() {
        return "profile";
    }

    public String helpMessage() {
        return "This command is to show profiles.";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
