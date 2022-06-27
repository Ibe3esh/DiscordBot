package me.ibe3esh;

import io.github.cdimascio.dotenv.Dotenv;
import me.ibe3esh.commands.*;
import me.ibe3esh.listeners.CounterListener;
import me.ibe3esh.utils.JDACommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.Arrays;

public class DiscordBot {

    public static Dotenv config;

    public static final GatewayIntent[] INTENTS = {
            GatewayIntent.DIRECT_MESSAGES,
            GatewayIntent.GUILD_MESSAGES,
            GatewayIntent.GUILD_MESSAGE_REACTIONS,
            GatewayIntent.GUILD_VOICE_STATES,
            GatewayIntent.GUILD_MEMBERS,
            GatewayIntent.GUILD_PRESENCES};

    public static void main(String[] args) throws LoginException {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        JDACommands jdaCommands = new JDACommands("+");
        jdaCommands.registerCommand(new CmdPlay());
        jdaCommands.registerCommand(new CmdStop());
        jdaCommands.registerCommand(new CmdSkip());
        jdaCommands.registerCommand(new CmdNowPlaying());
        jdaCommands.registerCommand(new CmdQueue());
        jdaCommands.registerCommand(new CmdProfile());
        jdaCommands.registerCommand(new CmdAvatar());

        JDA bot = JDABuilder.create(token, Arrays.asList(INTENTS))
                .enableCache(CacheFlag.VOICE_STATE)
                .setActivity(Activity.playing("met je moeder"))
                .setStatus(OnlineStatus.ONLINE)
                .addEventListeners(jdaCommands)
                .addEventListeners(new CounterListener())
                .build();
    }

    private Dotenv getConfig() {
        return config;
    }
}
