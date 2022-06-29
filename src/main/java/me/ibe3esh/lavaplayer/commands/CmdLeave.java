package me.ibe3esh.lavaplayer.commands;

import me.ibe3esh.lavaplayer.GuildMusicManager;
import me.ibe3esh.lavaplayer.PlayerManager;
import me.ibe3esh.utils.ExecuteArgs;
import me.ibe3esh.utils.ICommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class CmdLeave implements ICommand {
    @Override
    public void execute(ExecuteArgs event) {
        final TextChannel channel = event.getTextChannel();
        final Member self = event.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inVoiceChannel()) {
            channel.sendMessage("I need to be in a voice channel for this to work.").queue();
            return;
        }

        final Member member = event.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inVoiceChannel()) {
            channel.sendMessage("You need to be in a voice channel for this command to work.").queue();
            return;
        }

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            channel.sendMessage("You need to be in the same voice channel as me for this to work.").queue();
            return;
        }

        final Guild guild = event.getGuild();

        final GuildMusicManager musicManager = PlayerManager.getINSTANCE().getMusicManager(guild);

        musicManager.scheduler.repeating = false;
        musicManager.scheduler.queue.clear();
        musicManager.audioPlayer.stopTrack();

        final AudioManager audioManager = event.getGuild().getAudioManager();

        audioManager.closeAudioConnection();

        channel.sendMessage("I have left the voice channel.").queue();
    }

    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public String helpMessage() {
        return "Leaves the voice channel that the bot is in.";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
