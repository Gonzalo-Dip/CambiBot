package com.relaxingleg.commands;

import com.relaxingleg.commands.CotizacionService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.*;

import java.util.HashMap;
import java.util.Map;

public class CotizacionCommand extends ListenerAdapter {

    private static final Map<String, String> userEndpoints = new HashMap<>();
    private static final String COTIZACION_DOLAR = "cotizacion_dolar";

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("cotizacion")) {
            event.reply("Seleccione la moneda que desea consultar:").setEphemeral(true).queue();

            // Agregar botones para seleccionar la moneda
            event.getHook().editOriginal("Seleccione la moneda que desea consultar:")
                    .setActionRow(
                            Button.primary("moneda_dolar", "Dólar"),
                            Button.primary("moneda_euro", "Euro"),
                            Button.primary("moneda_real", "Real")
                    ).queue();
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        String endpoint = "";

        // Determinar qué botón fue presionado
        switch (event.getButton().getId()) {
            case "moneda_dolar":
                askDolarType(event);
                return; // Salimos de este método, ya que no necesitamos continuar con la ejecución

            case "moneda_euro":
                endpoint = "v1/cotizaciones/eur";
                break;

            case "moneda_real":
                endpoint = "v1/cotizaciones/brl";
                break;

            // Si el botón de Dólar fue presionado, no hay un endpoint aún.
            default:
                event.reply("Opción no válida.").setEphemeral(true).queue();
                return;
        }

        // Si tenemos un endpoint, obtenemos y respondemos
        if (!endpoint.isEmpty()) {
            try {
                String respuesta = CotizacionService.get(endpoint);
                event.reply("Respuesta para el endpoint seleccionado: " + respuesta).setEphemeral(true).queue();
            } catch (RuntimeException e) {
                event.reply("Error: " + e.getMessage()).setEphemeral(true).queue();
            }
        }
    }

    private void askDolarType(ButtonInteractionEvent event) {
        // Preguntar el tipo de cotización del Dólar
        event.reply("Seleccione el tipo de cotización del Dólar:").setEphemeral(true).queue();

        event.getHook().editOriginal("Seleccione el tipo de cotización del Dólar:")
                .setActionRow(
                        Button.primary("tipo_dolar_blue", "Dólar Blue"),
                        Button.primary("tipo_dolar_oficial", "Dólar Oficial"),
                        Button.primary("tipo_dolar_bolsa", "Dólar Bolsa")
                ).queue();
    }

    // Puedes manejar la lógica de los botones de tipo de Dólar aquí también
    private void handleDolarType(ButtonInteractionEvent event) {
        String endpoint = "";

        switch (event.getButton().getId()) {
            case "tipo_dolar_blue":
                endpoint = "v1/dolares/blue";
                break;
            case "tipo_dolar_oficial":
                endpoint = "v1/dolares/oficial";
                break;
            case "tipo_dolar_bolsa":
                endpoint = "v1/dolares/bolsa";
                break;
        }

        if (!endpoint.isEmpty()) {
            try {
                String respuesta = CotizacionService.get(endpoint);
                event.reply("Respuesta para el endpoint Dólar: " + respuesta).setEphemeral(true).queue();
            } catch (RuntimeException e) {
                event.reply("Error: " + e.getMessage()).setEphemeral(true).queue();
            }

        }
    }
}