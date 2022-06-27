package me.ibe3esh.lavaplayer.commands;

import me.ibe3esh.lavaplayer.GuildMusicManager;
import me.ibe3esh.lavaplayer.PlayerManager;
import me.ibe3esh.utils.ExecuteArgs;
import me.ibe3esh.utils.ICommand;

public class CmdStop implements ICommand {
    @Override
    public void execute(ExecuteArgs event) {
        final GuildMusicManager musicManager = PlayerManager.getINSTANCE().getMusicManager(event.getGuild());

        musicManager.scheduler.audioPlayer.stopTrack();
        musicManager.scheduler.queue.clear();

        event.getTextChannel().sendMessage("The player has been stopped and the queue has been cleared.").queue();
    }

    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public String helpMessage() {
        return "Stops the current song and clears the queue.";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
