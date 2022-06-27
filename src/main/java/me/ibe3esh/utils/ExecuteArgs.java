package me.ibe3esh.utils;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.*;

public class ExecuteArgs
{
    private final TextChannel textChannel;
    private final Member selfMember;
    private final Member member;
    private final Guild guild;
    private final JDA jda;
    private final Message message;
    private final String[] args;
    private final GuildVoiceState selfVoiceState;
    private final GuildVoiceState memberVoiceState;

    protected ExecuteArgs(final MessageReceivedEvent event) {
        this.textChannel = event.getTextChannel();
        this.member = event.getMember();
        this.guild = event.getGuild();
        this.jda = event.getJDA();
        this.message = event.getMessage();
        this.selfMember = this.guild.getSelfMember();
        this.args = this.message.getContentRaw().split(" ");
        this.selfVoiceState = this.selfMember.getVoiceState();
        this.memberVoiceState = this.member.getVoiceState();
    }

    public TextChannel getTextChannel() {
        return this.textChannel;
    }

    public Member getSelfMember() {
        return this.selfMember;
    }

    public Member getMember() {
        return this.member;
    }

    public Guild getGuild() {
        return this.guild;
    }

    public JDA getJda() {
        return this.jda;
    }

    public Message getMessage() {
        return this.message;
    }

    public String[] getArgs() {
        return this.args;
    }

    public GuildVoiceState getSelfVoiceState() {
        return this.selfVoiceState;
    }

    public GuildVoiceState getMemberVoiceState() {
        return this.memberVoiceState;
    }
}