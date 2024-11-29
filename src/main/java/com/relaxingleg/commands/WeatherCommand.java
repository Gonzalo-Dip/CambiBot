package com.relaxingleg.commands;

import com.relaxingleg.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherCommand implements ICommand {

    private final com.relaxingleg.commands.WeatherService weatherService;

    public WeatherCommand(String apiKey) {
        this.weatherService = new com.relaxingleg.commands.WeatherService(apiKey);
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

    }

    @Override
    public String getName() {
        return "weather";
    }

    @Override
    public String getDescription() {
        return "Get the weather forecast for a specific city.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "city", "The city to get the weather for", true));
        return options;
    }

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        String city = event.getOption("city").getAsString();

        try {
            String forecast = weatherService.obtenerPronostico(city);

            event.reply("Weather forecast for **" + city + "**:\n" + forecast).queue();
        } catch (IOException e) {
            event.reply("There was an error retrieving the weather data: " + e.getMessage()).queue();
        }
    }
}