package me.ibe3esh.commands;

import me.ibe3esh.utils.ExecuteArgs;
import me.ibe3esh.utils.ICommand;

public class CmdHelp implements ICommand {

    @Override
    public void execute(ExecuteArgs event) {
        event.getTextChannel().sendMessage("`!avatar - This command is to show avatars.`").queue();
        event.getTextChannel().sendMessage("`!help - Shows the list with commands in the bot.`").queue();
        event.getTextChannel().sendMessage("`!profile - This command is to show profiles.`").queue();
        event.getTextChannel().sendMessage("`!nowplaying - Shows the currently playing song.`").queue();
        event.getTextChannel().sendMessage("`!play - This command is to play music.`").queue();
        event.getTextChannel().sendMessage("`!queue - Shows the queued up songs.`").queue();
        event.getTextChannel().sendMessage("`!repeat - Loops the current song.`").queue();
        event.getTextChannel().sendMessage("`!skip - Skips to current track.`").queue();
        event.getTextChannel().sendMessage("`!stop - Stops the current song and clears the queue.`").queue();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String helpMessage() {
        return "Shows the list with commands in the bot.";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
