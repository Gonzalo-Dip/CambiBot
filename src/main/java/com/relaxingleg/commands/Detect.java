package com.relaxingleg.commands;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.errors.APIError;
import com.relaxingleg.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Detect implements ICommand {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        // Este método se puede dejar vacío si no es necesario en tu implementación
    }

    @Override
    public String getName() {
        return "detect";  // Nombre del comando en Discord
    }

    @Override
    public String getDescription() {
        return "Detects the language of the provided text";  // Descripción del comando
    }

    @Override
    public List<OptionData> getOptions() {
        // Definimos que el comando aceptará un parámetro de tipo STRING llamado "text"
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "text", "The text to detect the language of", true));
        return options;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        // Obtenemos el texto proporcionado por el usuario en el comando
        OptionMapping textOption = event.getOption("text");
        String text = textOption.getAsString();

        try {
            // Configuramos la clave de la API de DetectLanguage
            DetectLanguage.apiKey = "e2afbe8f9cabf6a3261e13b1cb62d79a";  // Usa tu propia clave API aquí

            // Usamos la API para detectar el idioma del texto proporcionado
            String language = DetectLanguage.simpleDetect(text);

            // Respondemos al usuario con el idioma detectado
            event.reply("The detected language is: " + Language.obtenerValorPorClave(language)).queue();
        } catch (APIError e) {
            // En caso de error, respondemos con un mensaje de error
            event.reply("Error detecting the language: " + e.getMessage()).queue();
        }
    }
}