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

    }

    @Override
    public String getName() {
        return "detect";
    }

    @Override
    public String getDescription() {
        return "Detects the language of the provided text";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "text", "The text to detect the language of", true));
        return options;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping textOption = event.getOption("text");
        String text = textOption.getAsString();

        try {
            DetectLanguage.apiKey = "e2afbe8f9cabf6a3261e13b1cb62d79a";

            String language = DetectLanguage.simpleDetect(text);

            event.reply("The detected language is: " + Language.obtenerValorPorClave(language)).queue();
        } catch (APIError e) {

            event.reply("Error detecting the language: " + e.getMessage()).queue();
        }
    }
}