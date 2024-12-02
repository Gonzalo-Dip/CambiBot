package com.relaxingleg.listeners;

import com.relaxingleg.utils.ImageSearcher;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;

public class ButtonListener extends ListenerAdapter {

    private final ImageSearcher imageSearcher;

    public ButtonListener(ImageSearcher imageSearcher) {
        this.imageSearcher = imageSearcher;
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        String buttonId = event.getComponentId(); // ID del botón presionado

        if (buttonId.equals("search_again")) {
            // Extraer la palabra clave de la interacción original
            String keyword = event.getMessage().getContentRaw().split("for \\*\\*")[1].split("\\*\\*")[0];
            try {
                String newImageUrl = imageSearcher.buscarImagen(keyword);
                if (newImageUrl != null) {
                    event.reply("Here is another image for **" + keyword + "**: " + newImageUrl)
                            .addActionRow(
                                    net.dv8tion.jda.api.interactions.components.buttons.Button.primary("search_again", "Search Again"),
                                    net.dv8tion.jda.api.interactions.components.buttons.Button.danger("stop_search", "Stop")
                            ).queue();
                } else {
                    event.reply("No more images found for **" + keyword + "**.").setEphemeral(true).queue();
                }
            } catch (IOException e) {
                event.reply("Error searching for another image: " + e.getMessage()).setEphemeral(true).queue();
            }
        } else if (buttonId.equals("stop_search")) {
            // Finalizar la interacción
            event.reply("Image search stopped.").setEphemeral(true).queue();
        }
    }
}
