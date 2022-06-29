package me.ibe3esh.commands;

import me.ibe3esh.utils.ExecuteArgs;
import me.ibe3esh.utils.ICommand;

public class CmdHelp implements ICommand {

    @Override
    public void execute(ExecuteArgs event) {
        event.getTextChannel().sendMessage(
                "`!avatar - This command is to show avatars.`\n"
                        +
                        "`!help - Shows the list with commands in the bot.`\n"
                        +
                        "`!meme - Shows you a horrible meme.`\n"
                        +
                        "`!profile - This command is to show profiles.`\n"
                        +
                        "`!join - Makes the bot join your voice channel.`\n"
                        +
                        "`!leave - Leaves the voice channel that the bot is in.`\n"
                        +
                        "`!nowplaying - Shows the currently playing song.`\n"
                        +
                        "`!play - This command is to play music.`\n"
                        +
                        "`!queue - Shows the queued up songs.`\n"
                        +
                        "`!repeat - Loops the current song.`\n"
                        +
                        "`!skip - Skips to current track.`\n"
                        +
                        "`!stop - Stops the current song and clears the queue.`\n").queue();
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
