package me.ibe3esh.commands;

import me.ibe3esh.lavaplayer.PlayerManager;
import me.ibe3esh.utils.ExecuteArgs;
import me.ibe3esh.utils.ICommand;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import java.net.URI;
import java.net.URISyntaxException;

public class CmdPlay implements ICommand {

    @Override
    public void execute(ExecuteArgs event) {

        if (!event.getMemberVoiceState().inVoiceChannel()) {
            event.getTextChannel().sendMessage("You need to be in a voice channel for this command to work.").queue();
            return;
        }

        if (!event.getSelfVoiceState().inVoiceChannel()) {
            final AudioManager audioManager = event.getGuild().getAudioManager();
            final VoiceChannel memberChannel = (VoiceChannel) event.getMemberVoiceState().getChannel();

            audioManager.openAudioConnection(memberChannel);
        }

        String link = String.join(" ", event.getArgs());

        if (!isUrl(link)) {
            link = "ytsearch:" + link + " audio";
        }

        PlayerManager.getINSTANCE().loadAndPlay(event.getTextChannel(), link);
    }

    public boolean isUrl(String url) {
        try {
            new URI(url);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String helpMessage() {
        return "This command is to play music.";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
