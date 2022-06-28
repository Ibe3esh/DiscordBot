package me.ibe3esh.commands;

import me.ibe3esh.utils.ExecuteArgs;
import me.ibe3esh.utils.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class CmdMeme implements ICommand {
    @Override
    public void execute(ExecuteArgs event) {
        JSONParser parser = new JSONParser();
        String postLink = "";
        String title = "";
        String url = "";

        try {
            URL memeURL = new URL("https://meme-api.herokuapp.com/gimme/memes");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(memeURL.openConnection().getInputStream()));

            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                JSONArray array = new JSONArray();
                array.add(parser.parse(lines));

                for (Object o : array) {
                    JSONObject jsonObject = (JSONObject) o;

                    postLink = (String) jsonObject.get("postLink");
                    title = (String) jsonObject.get("title");
                    url = (String) jsonObject.get("url");
                }
            }
            bufferedReader.close();

            event.getMessage().delete().queue();
            EmbedBuilder builder = new EmbedBuilder()
                    .setTitle(title, postLink)
                    .setImage(url)
                    .setColor(Color.magenta);
            event.getTextChannel().sendMessage(builder.build()).queue();

        } catch (Exception e) {
            event.getTextChannel().sendMessage(":no_entry: **Hey, something went wrong. Please try again later!**").queue();
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "meme";
    }

    @Override
    public String helpMessage() {
        return "Shows you a horrible meme.";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
