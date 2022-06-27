package me.ibe3esh.listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CounterListener extends ListenerAdapter {

    int counter = 0;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] message = event.getMessage().getContentRaw().split(" ");
        for (String s : message) {
            if (s.equalsIgnoreCase("kanker")) {
                counter++;
                event.getChannel().sendMessage("Sukkel MEME L\nKanker Counter: " + counter).queue();
            }
        }
    }
}
