package com.relaxingleg.commands;

import com.relaxingleg.ICommand;
import com.relaxingleg.utils.ImageSearcher;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageSearchCommand implements ICommand {

    private final ImageSearcher imageSearcher;


    public ImageSearchCommand(String apiKey) {
        this.imageSearcher = new ImageSearcher(apiKey);
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

    }

    @Override
    public String getName() {
        return "searchimage";
    }

    @Override
    public String getDescription() {
        return "Search for an image based on a keyword and return the URL.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "keyword", "The keyword to search for", true));
        return options;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) throws IOException {
        String keyword = event.getOption("keyword").getAsString();
        event.deferReply().queue();

        try {
            String imageUrl = imageSearcher.buscarImagen(keyword);

            if (imageUrl != null) {
                // Responder con botones
                event.getHook().sendMessage("Here is an image for **" + keyword + "**: " + imageUrl)
                        .addActionRow(
                                Button.primary("search_again", "Search Again"), // Botón para buscar otra imagen
                                Button.danger("stop_search", "Stop") // Botón para finalizar
                        ).queue();
            } else {
                event.getHook().sendMessage("No images found for the keyword: **" + keyword + "**.").queue();
            }
        } catch (IOException e) {
            event.getHook().sendMessage("There was an error searching for the image: " + e.getMessage()).queue();

            String imageUrl = imageSearcher.buscarImagen(keyword);

            if (imageUrl != null) {
                event.reply("Here is an image for **" + keyword + "**: " + imageUrl).queue();
            } else {
                event.reply("No images found for the keyword: **" + keyword + "**.").queue();
            }

        }
    }
}

