package com.relaxingleg.listeners;

import com.relaxingleg.utils.ImageSearcher;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;


import java.io.IOException;

public class ButtonListener extends ListenerAdapter {

    private final ImageSearcher imageSearcher;

    public ButtonListener(ImageSearcher imageSearcher) {
        this.imageSearcher = imageSearcher;
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        System.out.println("Button clicked: " + event.getComponentId());

        if (event.getComponentId().equals("search_again")) {

            Modal modal = Modal.create("new_keyword_modal", "Search a New Keyword")
                    .addActionRow(
                            TextInput.create("keyword_input", "Enter Keyword", TextInputStyle.SHORT)
                                    .setRequired(true)
                                    .build()
                    ).build();

            event.replyModal(modal).queue();
        } else {

            event.reply("Opción no válida.").setEphemeral(true).queue();
        }
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        System.out.println("Modal interaction received: " + event.getModalId());

        if (event.getModalId().equals("new_keyword_modal")) {
            String newKeyword = event.getValue("keyword_input").getAsString();
            System.out.println("New keyword entered: " + newKeyword);

            try {

                String imageUrl = imageSearcher.buscarImagen(newKeyword);
                if (imageUrl != null) {
                    event.reply("Here is an image for **" + newKeyword + "**: " + imageUrl)
                            .addActionRow(
                                    Button.primary("search_again", "Search Again")
                            ).queue();
                } else {
                    event.reply("No images found for the keyword: **" + newKeyword + "**.").queue();
                }
            } catch (IOException e) {
                event.reply("There was an error searching for the image: " + e.getMessage()).queue();
            }
        } else {
            event.reply("Opción no válida.").setEphemeral(true).queue();
        }
    }
}